package DATurbulenceTurnover;

import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public class Scenario {

  RandomGenerator r;
  NetworkAnalyzer na;

  int socialMechanism;

  boolean isRewiring;
  boolean isNetworkClosure;
  boolean isPreferentialAttachment;
  boolean isRandomRewiring;

  int[] focalIndexArray;
  int[] targetIndexArray;
  int[] dyadIndexArray;
  int[][] dyad2DIndexArray;

  int span;          // Span of control
  double enforcement;   // E

  // Removed: double density;
  // Removed: double diameter;
  // Removed: double globalClustering;
  // Removed: double betweennessCentralityVariance;

  // New global variables (requirement #5)
  double turbulenceRate;
  double turnoverRate;

  // Reality is now stored in a BitSet (requirement #1)
  BitSet reality;
  int[] realityBundleID;

  // Beliefs are now stored in a BitSet array (requirement #1)
  BitSet[] beliefOf;

  int[] performance;
  int[] levelOf;
  double levelRange;

  // Networks are now stored in BitSet arrays (requirement #1)
  BitSet[] network;
  BitSet[] networkFormal;
  BitSet[] networkInformal;
  BitSet[] networkLimited;

  int[] degree;
  int[] degreeFormal;
  int[] degreeInformal;

  double[][] differenceOf;
  double[] differenceSum;

  double[][] preferenceScore;
  double[] preferenceScoreAvg;

  double performanceAvg;

  double averagePathLength;
  double networkEfficiency;
  double globalClusteringWattsStrogatz;
  double overallCentralization;
  double shortestPathVariance;

  /**
   * Updated constructor to include turbulenceRate, turbulenceInterval, and turnoverRate
   */
  Scenario(int socialDynamics, int span, double enforcement, double turbulenceRate, double turnoverRate) {
    r = new MersenneTwister();

    this.socialMechanism = socialDynamics;
    switch (socialDynamics) {
      case 0 -> isNetworkClosure = true;
      case 1 -> isPreferentialAttachment = true;
    }

    this.turbulenceRate = turbulenceRate;         // new global variable
    this.turnoverRate = turnoverRate;             // new global variable

    focalIndexArray = new int[Main.N];
    for (int n = 0; n < Main.N; n++) {
      focalIndexArray[n] = n;
    }
    targetIndexArray = focalIndexArray.clone();

    int nDyad = Main.N * (Main.N - 1) / 2;
    dyadIndexArray = new int[nDyad];
    dyad2DIndexArray = new int[nDyad][2];
    int d = 0;
    for (int i = 0; i < Main.N; i++) {
      for (int j = i; j < Main.N; j++) {
        if (i == j) {
          continue;
        }
        dyadIndexArray[d] = d;
        dyad2DIndexArray[d][0] = i;
        dyad2DIndexArray[d][1] = j;
        d++;
      }
    }

    this.span = span;
    this.enforcement = enforcement;

    isRewiring = true;
    isRandomRewiring = false;

    initialize();
  }

  public Scenario getClone() {
    // Use the new constructor with the saved turbulenceRate, etc.
    Scenario clone = new Scenario(this.socialMechanism, this.span, this.enforcement, this.turbulenceRate, this.turnoverRate);

    // Clone reality
    clone.reality = (BitSet) this.reality.clone();
    clone.realityBundleID = this.realityBundleID.clone();

    // Clone beliefOf
    clone.beliefOf = new BitSet[Main.N];
    for (int focal = 0; focal < Main.N; focal++) {
      clone.beliefOf[focal] = (BitSet) this.beliefOf[focal].clone();
    }

    // Clone performance
    clone.performance = this.performance.clone();

    // Clone networks
    clone.networkFormal = new BitSet[Main.N];
    clone.networkInformal = new BitSet[Main.N];
    clone.network = new BitSet[Main.N];
    for (int focal = 0; focal < Main.N; focal++) {
      clone.networkFormal[focal] = (BitSet) this.networkFormal[focal].clone();
      clone.networkInformal[focal] = (BitSet) this.networkInformal[focal].clone();
      clone.network[focal] = (BitSet) this.network[focal].clone();
    }

    // Clone degrees
    clone.degreeFormal = this.degreeFormal.clone();
    clone.degreeInformal = this.degreeInformal.clone();
    clone.degree = this.degree.clone();

    // Clone differenceOf
    clone.differenceOf = new double[Main.N][];
    for (int focal = 0; focal < Main.N; focal++) {
      clone.differenceOf[focal] = this.differenceOf[focal].clone();
    }
    clone.differenceSum = this.differenceSum.clone();

    clone.performanceAvg = this.performanceAvg;

    clone.na = new NetworkAnalyzer(clone.network);

    clone.setOutcome();

    return clone;
  }

  public Scenario getClone(boolean isRewiring, boolean isRandomRewiring) {
    Scenario clone = getClone();
    clone.setNetworkParams(isRewiring, isRandomRewiring);
    return clone;
  }

  void setNetworkParams(boolean isRewiring, boolean isRandomRewiring) {
    this.isRewiring = isRewiring;
    this.isRandomRewiring = isRandomRewiring;
  }

  private void initialize() {
    initializeNetwork();
    initializeEntity(); // TypeOf depends on the network (membership)
    initializeOutcome();
  }

  private void initializeNetwork() {
    // Use BitSet for networks
    network = new BitSet[Main.N];
    networkFormal = new BitSet[Main.N];
    networkInformal = new BitSet[Main.N];
    networkLimited = new BitSet[Main.N];
    levelOf = new int[Main.N];

    degreeFormal = new int[Main.N];
    degreeInformal = new int[Main.N];
    degree = new int[Main.N];

    for (int i = 0; i < Main.N; i++) {
      network[i] = new BitSet(Main.N);
      networkFormal[i] = new BitSet(Main.N);
      networkInformal[i] = new BitSet(Main.N);
      networkLimited[i] = new BitSet(Main.N);
    }

    int levelNow = 1;
    int upperStart = 0;
    int upperEnd = 1;
    int lowerStart = upperEnd;
    int lowerEnd = lowerStart + span;
    levelOf[0] = 1;
    //@ 230910 This code is generated by ChatGPT - Check validity
    for (; ; ) {
      levelNow++;
      // For all nodes at the current depth, assign subordinates
      for (int upper = upperStart; upper < upperEnd; upper++) {
        for (int lower = lowerStart; lower < lowerEnd; lower++) {
          network[upper].set(lower);
          network[lower].set(upper);
          degree[upper]++;
          degree[lower]++;
          levelOf[lower] = levelNow;
        }
        if (Main.LINK_LEVEL) {
          int lowerNum = lowerEnd - lowerStart;
          if (lowerStart == lowerEnd) {
            continue;
          }
          for (int i = 0; i < lowerNum; i++) {
            int focal = lowerStart + i;
            int target = lowerStart + (i + 1) % lowerNum;
            if (focal == target) {
              break;
            }
            network[focal].set(target);
            network[target].set(focal);
            degree[focal]++;
            degree[target]++;
          }
        }
        lowerStart = lowerEnd;
        lowerEnd = FastMath.min(lowerStart + span, Main.N);
      }
      if (lowerStart == Main.N) {
        break;
      }
      upperStart = upperEnd;
      upperEnd = upperStart + (int) FastMath.pow(span, levelNow - 1);
    }
    levelRange = levelNow - levelOf[0];

    // Tie Enforcement
    for (int focal = 0; focal < Main.N; focal++) {
      for (int target = focal; target < Main.N; target++) {
        if (network[focal].get(target)) {
          if (r.nextDouble() < enforcement) {
            // Enforced
            networkFormal[focal].set(target);
            networkFormal[target].set(focal);
            degreeFormal[focal]++;
            degreeFormal[target]++;
          } else {
            // Flexible
            networkInformal[focal].set(target);
            networkInformal[target].set(focal);
            degreeInformal[focal]++;
            degreeInformal[target]++;
          }
        }
      }
    }

    int numAdditionLeft = (int) (Main.N_DYAD_INT * Main.LINK_ADD);
    if (numAdditionLeft > 0) {
      shuffleFisherYates(dyadIndexArray);
      for (; numAdditionLeft > 0; ) {
        for (int dyad : dyadIndexArray) {
          int focal = dyad2DIndexArray[dyad][0];
          int target = dyad2DIndexArray[dyad][1];
          if (!network[focal].get(target) && (degreeInformal[focal] < Main.INFORMAL_MAX_NUM || degreeInformal[target] < Main.INFORMAL_MAX_NUM) && numAdditionLeft > 0) {
            if (r.nextDouble() < enforcement) {
              // Enforced
              networkFormal[focal].set(target);
              networkFormal[target].set(focal);
              degreeFormal[focal]++;
              degreeFormal[target]++;
            } else {
              // Flexible
              networkInformal[focal].set(target);
              networkInformal[target].set(focal);
              degreeInformal[focal]++;
              degreeInformal[target]++;
            }
            network[focal].set(target);
            network[target].set(focal);
            degree[focal]++;
            degree[target]++;
            numAdditionLeft--;
          }
          if (numAdditionLeft == 0) {
            break;
          }
        }
      }
    }

    if (Main.LIMIT_LEVEL) {
      for (int focal : focalIndexArray) {
        networkLimited[focal].set(focal);
        for (int target = focal; target < Main.N; target++) {
          if (FastMath.abs(levelOf[focal] - levelOf[target]) > 1) {
            networkLimited[focal].set(focal);
          }
        }
      }
    }

    na = new NetworkAnalyzer(network);
  }

  private void initializeEntity() {
    reality = new BitSet(Main.M);
    realityBundleID = new int[Main.M];
    beliefOf = new BitSet[Main.N];

    for (int bundle = 0; bundle < Main.M_OF_BUNDLE; bundle++) {
      int baseIndex = bundle * Main.M_IN_BUNDLE;
      int endIndex = (bundle + 1) * Main.M_IN_BUNDLE;
      for (int m = baseIndex; m < endIndex; m++) {
        if (r.nextBoolean()) {
          reality.set(m);
        } else {
          reality.clear(m);
        }
        realityBundleID[m] = bundle;
      }
    }

    for (int focal = 0; focal < Main.N; focal++) {
      beliefOf[focal] = new BitSet(Main.M);
      for (int m = 0; m < Main.M; m++) {
        if (r.nextBoolean()) {
          beliefOf[focal].set(m);
        } else {
          beliefOf[focal].clear(m);
        }
      }
    }
  }

  private void initializeOutcome() {
    performance = new int[Main.N];
    differenceOf = new double[Main.N][Main.N];
    differenceSum = new double[Main.N];
    setPerformance();
    setOutcome();
  }

  void stepForward(int numFormation, int numBreak) {
    if (Main.DO_POST_REWIRING) {
      if (isRewiring) {
        if (!isRandomRewiring) {
          doRewiring(numFormation, numBreak);
        } else {
          doRandomRewiring(numFormation, numBreak);
        }
      }
    }
    stepForward();
  }

  void stepForward(int tieTurnover) {
    if (Main.DO_POST_REWIRING) {
      if (isRewiring) {
        if (!isRandomRewiring) {
          doRewiring(tieTurnover, tieTurnover);
        } else {
          doRandomRewiring(tieTurnover, tieTurnover);
        }
      }
    }
    stepForward();
  }

  void stepForward() {
    doLearning();
    setOutcome();
    if (turnoverRate > 0) {
      doTurnover();
    }
  }

  /**
   * We remove the four instance variables from the class, but keep their usage here as local variables to avoid removing any methods.
   */
  void setOutcome() {
    performanceAvg = 0;
    na.setNetworkMetrics();

    averagePathLength = na.getAveragePathLength();
    networkEfficiency = na.getNetworkEfficiency();
    globalClusteringWattsStrogatz = na.getGlobalClusteringWattsStrogatz();
    overallCentralization = na.getGlobalClosenessCentralization();
    shortestPathVariance = na.getShortestPathVariance();

    for (int focal = 0; focal < Main.N; focal++) {
      performanceAvg += performance[focal];
    }
    performanceAvg /= Main.M_N;
  }

  void setPreferenceScore() {
    preferenceScore = new double[Main.N][Main.N];
    preferenceScoreAvg = new double[Main.N];

    if (isNetworkClosure) {
      setPreferenceScoreRawNetworkClosure();
    } else if (isPreferentialAttachment) {
      setPreferenceScoreRawPreferentialAttachment();
    }
  }

  void setPreferenceScoreRawNetworkClosure() {
    for (int focal : focalIndexArray) {
      for (int target = focal; target < Main.N; target++) {
        for (int i = 0; i < Main.N; i++) {
          if (network[focal].get(i) && network[target].get(i)) {
            preferenceScore[focal][target]++;
            preferenceScore[target][focal]++;
          }
        }
      }
    }
    for (int focal : focalIndexArray) {
      preferenceScore[focal][focal] = 0;
      for (int target : targetIndexArray) {
        preferenceScore[focal][target] /= network[focal].get(target) ? degree[focal] : degree[focal] + 1;
        preferenceScoreAvg[focal] += preferenceScore[focal][target];
      }
      preferenceScoreAvg[focal] /= degree[focal];
    }
  }

  void setPreferenceScoreRawPreferentialAttachment() {
    for (int focal : focalIndexArray) {
      for (int target = focal; target < Main.N; target++) {
        preferenceScore[focal][target] = degree[target];
        preferenceScore[target][focal] = degree[focal];
      }
    }
    for (int focal : focalIndexArray) {
      preferenceScore[focal][focal] = 0;
      for (int target : targetIndexArray) {
        preferenceScoreAvg[focal] += preferenceScore[focal][target];
      }
      preferenceScoreAvg[focal] /= degree[focal];
    }
  }

  double getRewiringWeight(int focal, int target) {
    double rewiringWeight = 0;
    if (isNetworkClosure) {
      rewiringWeight = getRewiringWeightNetworkClosure(focal, target);
    } else if (isPreferentialAttachment) {
      rewiringWeight = getRewiringWeightPreferentialAttachment(focal, target);
    }
    return rewiringWeight;
  }

  double getRewiringWeightNetworkClosure(int focal, int target) {
    double preferenceScore = 1D; // To avoid weight of 0
    double denominator;
    for (int shared : targetIndexArray) {
      if (network[focal].get(shared) && network[target].get(shared)) {
        preferenceScore++;
      }
    }
    // Choose larger degree as a denominator
    denominator = FastMath.max(degree[focal], degree[target]) + (network[focal].get(target) ? 0 : 1D);
    return preferenceScore / denominator;
  }

  double getRewiringWeightPreferentialAttachment(int focal, int target) {
    return FastMath.min(degree[focal], degree[target]);
  }

  void doRewiring(int numFormation, int numBreak) {
    if (isRandomRewiring) {
      doRandomRewiring(numFormation, numBreak);
    } else {
      doTieBreak(numBreak);
      doTieFormation(numFormation);
    }
  }

  void doTieBreak(int numBreak) {
    while (numBreak > 0) {
      double[] probability = new double[Main.N_DYAD_INT];
      double dyad2CutWeightMax = Double.MIN_VALUE;
      for (int d : dyadIndexArray) {
        int focal = dyad2DIndexArray[d][0];
        int target = dyad2DIndexArray[d][1];
        if (networkInformal[focal].get(target)) {
          probability[d] = getRewiringWeight(focal, target);
          if (probability[d] > dyad2CutWeightMax) {
            dyad2CutWeightMax = probability[d];
          }
        }
      }
      double probabilityDenominator = 0;
      for (int d : dyadIndexArray) {
        if (probability[d] != 0) {
          probability[d] = dyad2CutWeightMax - probability[d];
          probabilityDenominator += probability[d];
        }
      }
      if (probabilityDenominator == 0) {
        shuffleFisherYates(dyadIndexArray);
        for (int d : dyadIndexArray) {
          probability[d] = 1D;
        }
        probabilityDenominator = 1D;
      }
      double marker = r.nextDouble();
      double probabilityCum = 0;
      for (int d : dyadIndexArray) {
        if (probability[d] != 0) {
          probabilityCum += probability[d] / probabilityDenominator;
          if (probabilityCum >= marker) {
            int focal = dyad2DIndexArray[d][0];
            int target = dyad2DIndexArray[d][1];
            network[focal].clear(target);
            network[target].clear(focal);
            networkInformal[focal].clear(target);
            networkInformal[target].clear(focal);
            degree[focal]--;
            degreeInformal[focal]--;
            degree[target]--;
            degreeInformal[target]--;
            numBreak--;
            break;
          }
        }
      }
    }
  }

  void doTieFormation(int numFormation) {
    while (numFormation > 0) {
      double[] probability = new double[Main.N_DYAD_INT];
      double probabilityDenominator = 0;
      for (int d : dyadIndexArray) {
        int focal = dyad2DIndexArray[d][0];
        int target = dyad2DIndexArray[d][1];
        if (!network[focal].get(target) && focal != target && !networkLimited[focal].get(target)) {
          probability[d] = getRewiringWeight(focal, target);
          probabilityDenominator += probability[d];
        }
      }
      if (probabilityDenominator == 0) {
        shuffleFisherYates(dyadIndexArray);
        for (int d : dyadIndexArray) {
          probability[d] = 1D;
        }
        probabilityDenominator = 1D;
      }
      double marker = r.nextDouble();
      double probabilityCum = 0;
      for (int d : dyadIndexArray) {
        if (probability[d] != 0) {
          probabilityCum += probability[d] / probabilityDenominator;
          if (probabilityCum >= marker) {
            int focal = dyad2DIndexArray[d][0];
            int target = dyad2DIndexArray[d][1];
            network[focal].set(target);
            network[target].set(focal);
            networkInformal[focal].set(target);
            networkInformal[target].set(focal);
            degree[focal]++;
            degreeInformal[focal]++;
            degree[target]++;
            degreeInformal[target]++;
            numFormation--;
            break;
          }
        }
      }
    }
  }

  void doRandomRewiring(int numFormation, int numBreak) {
    int numFormationLeft = numFormation;
    int numBreakLeft = numBreak;
    // Random rewiring
    shuffleFisherYates(dyadIndexArray);
    for (; ; ) {
      for (int dyad : dyadIndexArray) {
        int focal = dyad2DIndexArray[dyad][0];
        int target = dyad2DIndexArray[dyad][1];
        if (networkInformal[focal].get(target) && numBreakLeft > 0) {
          network[focal].clear(target);
          network[target].clear(focal);
          networkInformal[focal].clear(target);
          networkInformal[target].clear(focal);
          degree[focal]--;
          degreeInformal[focal]--;
          degree[target]--;
          degreeInformal[target]--;
          numBreakLeft--;
        } else if (numFormationLeft > 0 && !network[focal].get(target) && focal != target && (degreeInformal[focal] < Main.INFORMAL_MAX_NUM || degreeInformal[target] < Main.INFORMAL_MAX_NUM) && !networkLimited[focal].get(target)) {
          network[focal].set(target);
          network[target].set(focal);
          networkInformal[focal].set(target);
          networkInformal[target].set(focal);
          degree[focal]++;
          degreeInformal[focal]++;
          degree[target]++;
          degreeInformal[target]++;
          numFormationLeft--;
        }
        if (numFormationLeft == 0 && numBreakLeft == 0) {
          break;
        }
      }
      if (numFormationLeft == 0 && numBreakLeft == 0) {
        break;
      } else {
        System.out.println("\tRandom Rewiring Reiterated");
      }
    }
  }

  void doLearning() {
    // We'll create a temporary buffer of BitSets
    BitSet[] beliefOfBuffer = new BitSet[Main.N];
    for (int focal = 0; focal < Main.N; focal++) {
      beliefOfBuffer[focal] = (BitSet) beliefOf[focal].clone();
      int[] majorityOpinionCount = new int[Main.M];
      for (int target = 0; target < Main.N; target++) {
        if (network[focal].get(target) && performance[target] > performance[focal]) {
          for (int m = 0; m < Main.M; m++) {
            if (beliefOf[target].get(m)) {
              majorityOpinionCount[m]++;
            } else {
              majorityOpinionCount[m]--;
            }
          }
        }
      }
      for (int m = 0; m < Main.M; m++) {
        if (majorityOpinionCount[m] > 0) {
          beliefOfBuffer[focal].set(m);
        } else if (majorityOpinionCount[m] < 0) {
          beliefOfBuffer[focal].clear(m);
        }
      }
    }
    for (int focal = 0; focal < Main.N; focal++) {
      for (int m = 0; m < Main.M; m++) {
        if (beliefOf[focal].get(m) != beliefOfBuffer[focal].get(m)) {
          if (r.nextDouble() < Main.P_LEARNING) {
            if (beliefOfBuffer[focal].get(m)) {
              beliefOf[focal].set(m);
            } else {
              beliefOf[focal].clear(m);
            }
          }
        }
      }
      setPerformance(focal);
    }
  }

  int getPerformance(int focal) {
    int performanceNow = 0;
    BitSet beliefOfFocal = beliefOf[focal];
    for (int bundle = 0; bundle < Main.M_OF_BUNDLE; bundle++) {
      boolean matchAll = true;
      int start = bundle * Main.M_IN_BUNDLE;
      int end = start + Main.M_IN_BUNDLE;
      for (int m = start; m < end; m++) {
        if (beliefOfFocal.get(m) != reality.get(m)) {
          matchAll = false;
          break;
        }
      }
      if (matchAll) {
        performanceNow += Main.M_IN_BUNDLE;
      }
    }
    return performanceNow;
  }

  int getPerformanceS1(int focal) {
    int performanceNow = 0;
    for (int m = 0; m < Main.M; m++) {
      if (beliefOf[focal].get(m) == reality.get(m)) {
        performanceNow++;
      }
    }
    return performanceNow;
  }

  void setPerformance(int focal) {
    if (Main.M_IN_BUNDLE == 1) {
      performance[focal] = getPerformanceS1(focal);
    } else {
      performance[focal] = getPerformance(focal);
    }
  }

  void setPerformance() {
    for (int focal = 0; focal < Main.N; focal++) {
      setPerformance(focal);
    }
  }

  void printCSV(String fileName) {
    try {
      FileWriter csvWriter;
      csvWriter = new FileWriter(fileName + ".csv");
      csvWriter.append("SOURCE");
      csvWriter.append(",");
      csvWriter.append("TARGET");
      csvWriter.append(",");
      csvWriter.append("TIE_ENFORCED");
      csvWriter.append("\n");

      // Edge
      for (int focal = 0; focal < Main.N; focal++) {
        for (int target = focal; target < Main.N; target++) {
          if (focal == target) {
            continue;
          }
          if (network[focal].get(target)) {
            csvWriter.append(Integer.toString(focal));
            csvWriter.append(",");
            csvWriter.append(Integer.toString(target));
            csvWriter.append(",");
            csvWriter.append(Boolean.toString(networkFormal[focal].get(target)));
            csvWriter.append("\n");
          }
        }
      }

      // Individual
      for (int focal = 0; focal < Main.N; focal++) {
        csvWriter.append(Integer.toString(focal));
        csvWriter.append(",");
        csvWriter.append(",");
        csvWriter.append("\n");
      }

      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void shuffleFisherYates(int[] nArray) {
    for (int i = nArray.length - 1; i > 0; i--) {
      int j = r.nextInt(i + 1);
      int temp = nArray[i];
      nArray[i] = nArray[j];
      nArray[j] = temp;
    }
  }

  // ------------------------------------------------------------------------
  // New methods for turnover and turbulence (requirement #6)
  // ------------------------------------------------------------------------

  /**
   * doTurnover(): Each individual will leave the organization at each time period with probability turnoverRate. When an individual leaves, her position is filled by a new member who starts with randomly assigned beliefs.
   */
  void doTurnover() {
    for (int i = 0; i < Main.N; i++) {
      if (r.nextDouble() < turnoverRate) {
        // The individual i leaves; fill with new random beliefs
        for (int m = 0; m < Main.M; m++) {
          if (r.nextBoolean()) {
            beliefOf[i].set(m);
          } else {
            beliefOf[i].clear(m);
          }
        }
        // Recompute performance for this individual
        setPerformance(i);
      }
    }
  }

  /**
   * doTurbulence(): We perturb each of M dimensions of reality with probability = turbulenceRate. If a dimension is perturbed, we flip its value.
   */
  void doTurbulence() {
    for (int m = 0; m < Main.M; m++) {
      if (r.nextDouble() < turbulenceRate) {
        reality.flip(m);  // flip the bit
      }
    }
  }
}
