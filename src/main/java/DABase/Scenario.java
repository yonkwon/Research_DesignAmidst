package DABase;

import java.io.FileWriter;
import java.io.IOException;
import jdk.jfr.Description;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public class Scenario {

  RandomGenerator r;

  int socialMechanism;

  boolean isRewiring;
  boolean isHomophilyOnChar;
  boolean isHomophilyOnStat;
  boolean isNetworkClosure;
  boolean isPreferentialAttachment;
  boolean isRandomRewiring;

  int[] focalIndexArray;
  int[] targetIndexArray;

  int observationScope;

  double strength;      //Strength of social behavior
  int span;          //Span of control
  double enforcement;   //E

  boolean[] reality;
  int[] realityBundleID;
  boolean[][] beliefOf;
  boolean[][] typeOf;

  int[] performance;

  int[] levelOf;
  double levelMax;
  boolean[][] network;
  boolean[][] networkEnforced;
  boolean[][] networkFlexible;
  boolean[][] observationStructure;
  int[] degree;
  int[] degreeEnforced;
  int[] degreeFlexible;
  double[][] differenceOf;
  double[] differenceSum;

  double[][] neighborScore;
  double[] neighborhoodScore;
  boolean[] satisfied;

  double degreeMax, degreeMin, degreeRange;

  int numRewiring;
  double performanceAvg;
  double disagreementAvg;
  double clusteringCoefficient;
  double satisfactionRate;

  Scenario(int socialMechanism, double strength, int span, double enforcement) {
    r = new MersenneTwister();

    this.socialMechanism = socialMechanism;
    switch (socialMechanism) {
      case 0 -> isHomophilyOnChar = true;
      case 1 -> isHomophilyOnStat = true;
      case 2 -> isNetworkClosure = true;
      case 3 -> isPreferentialAttachment = true;
    }

    focalIndexArray = new int[Main.N];
    for (int n = 0; n < Main.N; n++) {
      focalIndexArray[n] = n;
    }
    targetIndexArray = focalIndexArray.clone();

    this.strength = strength;
    this.span = span;
    this.enforcement = enforcement;

    this.observationScope = Main.OBSERVATION_SCOPE;

    isRewiring = true;
    isRandomRewiring = false;

    initialize();
  }

  public Scenario getClone() {
    Scenario clone = new Scenario(this.socialMechanism, this.strength, this.span, this.enforcement);

    clone.reality = this.reality.clone();
    clone.realityBundleID = this.realityBundleID.clone();
    clone.beliefOf = new boolean[Main.N][];
    clone.typeOf = new boolean[Main.N][];
    clone.performance = this.performance.clone();

    clone.networkEnforced = new boolean[Main.N][];
    clone.networkFlexible = new boolean[Main.N][];
    clone.network = new boolean[Main.N][];

    clone.degreeEnforced = this.degreeEnforced.clone();
    clone.degreeFlexible = this.degreeFlexible.clone();
    clone.degree = this.degree.clone();

    clone.differenceOf = new double[Main.N][];
    clone.differenceSum = this.differenceSum.clone();
    clone.satisfied = this.satisfied.clone();

    clone.numRewiring = this.numRewiring;
    clone.performanceAvg = this.performanceAvg;
    clone.disagreementAvg = this.disagreementAvg;
    clone.satisfactionRate = this.satisfactionRate;

    for (int focal = 0; focal < Main.N; focal++) {
      clone.beliefOf[focal] = this.beliefOf[focal].clone();
      clone.typeOf[focal] = this.typeOf[focal].clone();

      clone.networkEnforced[focal] = this.networkEnforced[focal].clone();
      clone.networkFlexible[focal] = this.networkFlexible[focal].clone();
      clone.network[focal] = this.network[focal].clone();

      clone.differenceOf[focal] = this.differenceOf[focal].clone();
    }

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

  public void copyRealityOf(Scenario src) {
    reality = src.reality.clone();
    realityBundleID = src.realityBundleID.clone();
  }

  private void initialize() {
    initializeNetwork();
    initializeEntity(); //TypeOf depends on the network (membership)
    initializeOutcome();
  }

  private void initializeNetwork() {
    networkEnforced = new boolean[Main.N][Main.N];
    networkFlexible = new boolean[Main.N][Main.N];
    network = new boolean[Main.N][Main.N];
    levelOf = new int[Main.N];

    degreeEnforced = new int[Main.N];
    degreeFlexible = new int[Main.N];
    degree = new int[Main.N];

    int upperStart = 0;
    int upperEnd;
    int lowerStart = 1;
    //@ 230910 This code is generated by ChatGPT - Check validity
    for (; ; ) {
      upperEnd = FastMath.min(upperStart + (int) FastMath.pow(span, upperStart / span), Main.N);
      // For all nodes at the current depth, assign subordinates
      for (int upper = upperStart; upper < upperEnd; upper++) {
        int lowerEnd = FastMath.min(lowerStart + span, Main.N);
        for (int lower = lowerStart; lower < lowerEnd; lower++) {
          network[upper][lower] = true;
          network[lower][upper] = true;
          degree[upper]++;
          degree[lower]++;
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
            network[focal][target] = true;
            network[target][focal] = true;
            degree[focal]++;
            degree[target]++;
          }
        }
        lowerStart = lowerEnd;
      }
      if (lowerStart == Main.N) {
        break;
      }
      upperStart = upperEnd;
    }

    int levelStart = 0;
    int levelEnd = 1;
    int levelNow = 0;
    for (; ; ) {
      for (int i = levelStart; i < levelEnd; i++) {
        levelOf[i] = levelNow;
      }
      levelStart = levelEnd;
      levelEnd = FastMath.min(Main.N, levelEnd + (int) FastMath.pow(span, levelNow));
      if (levelStart == Main.N) {
        levelMax = levelNow;
        break;
      }
      levelNow++;
    }

    //Print the graph for visualization
//    System.out.println("Network Visualization: Span " + span);
//    for (int i = 0; i < Main.N; i++) {
//      System.out.print(i + " lv. " + levelOf[i] + " dg. " + degree[i] + ":\t");
//      for (int j = 0; j < Main.N; j++) {
//        System.out.print(network[i][j] ? "1 " : "0 ");
//      }
//      System.out.println();
//    }

    //Tie Enforcement
    for (int focal = 0; focal < Main.N; focal++) {
      for (int target = focal; target < Main.N; target++) {
        if (network[focal][target]) {
          if (r.nextDouble() < enforcement) { // This choice has little effect on the result
            //Enforced
            networkEnforced[focal][target] = true;
            networkEnforced[target][focal] = true;
            degreeEnforced[focal]++;
            degreeEnforced[target]++;
          } else {
            //Flexible
            networkFlexible[focal][target] = true;
            networkFlexible[target][focal] = true;
            degreeFlexible[focal]++;
            degreeFlexible[target]++;
          }
        }
      }
    }

    setObservationStructure();
  }

  private void initializeEntity() {
    reality = new boolean[Main.M];
    realityBundleID = new int[Main.M];
    typeOf = new boolean[Main.N][Main.L];
    beliefOf = new boolean[Main.N][Main.M];

    for (int bundle = 0; bundle < Main.M_OF_BUNDLE; bundle++) {
      int baseIndex = bundle * Main.M_IN_BUNDLE;
      int endIndex = (bundle + 1) * Main.M_IN_BUNDLE;
      for (int m = baseIndex; m < endIndex; m++) {
        reality[m] = r.nextBoolean();
        realityBundleID[m] = bundle;
      }
    }

    for (int focal = 0; focal < Main.N; focal++) {
      for (int l = 0; l < Main.L; l++) {
        typeOf[focal][l] = r.nextBoolean();
      }
      for (int m = 0; m < Main.M; m++) {
        beliefOf[focal][m] = r.nextBoolean();
      }
    }
  }

  private void initializeOutcome() {
    performance = new int[Main.N];

    differenceOf = new double[Main.N][Main.N];
    differenceSum = new double[Main.N];
    satisfied = new boolean[Main.N];

    setPerformance();
    doEvaluateNeighbor();
    setOutcome();
  }

  void stepForward() {
    if (isRewiring) {
      doEvaluateNeighbor();
      setObservationStructure();
      doRewiring();
    }
    doLearning();
  }

  void stepForward(int numRewiring) {
    if (isRewiring) {
      setObservationStructure();
      doRewiring(numRewiring);
    }
    doLearning();
  }

  void setOutcome() {
    performanceAvg = 0;
    disagreementAvg = 0;
    satisfactionRate = 0;
    clusteringCoefficient = 0;
    for (int focal = 0; focal < Main.N; focal++) {
      performanceAvg += performance[focal];
      satisfactionRate += satisfied[focal] ? 1 : 0;
      for (int target = focal; target < Main.N; target++) {
        for (int m = 0; m < Main.M; m++) {
          if (beliefOf[focal][m] != beliefOf[target][m]) {
            disagreementAvg++;
          }
        }

      }
    }
    // Global Clustering Coefficient
    // https://en.wikipedia.org/wiki/Clustering_coefficient#Global_clustering_coefficient
    for (int ind1 : focalIndexArray) {
      for (int ind2 : targetIndexArray) {
        if (network[ind1][ind2]) {
          for (int ind3 = 0; ind3 < Main.N; ind3++) {
            if (network[ind1][ind3] && network[ind2][ind3]
            ) {
              //ind 1, 2, 3 are different and form a closed triplet
              clusteringCoefficient++;
            }
          }
        }
      }
    }
    performanceAvg /= Main.M_N;
    disagreementAvg /= Main.M_N_DYAD;
    satisfactionRate /= Main.N;
    clusteringCoefficient /= Main.N_TRIPLET;
  }

  double getNeighborScoreHomophilyOnChar(int focal, int target) {
    double neighborScore = 0;
    for (int ch = 0; ch < Main.L; ch++) {
      if (typeOf[focal][ch] == typeOf[target][ch]) {
        neighborScore++;
      }
    }
    return neighborScore / (double) Main.L;
  }

  double getNeighborScoreHomophilyOnStatus(int focal, int target) {
    return 1D - FastMath.abs(levelOf[focal] - levelOf[target]) / levelMax;
  }

  double getNeighborScoreNetworkClosure(int focal, int target) {
    double neighborScore = 0;
    for (int i = 0; i < Main.N; i++) {
      if (focal == i || target == i) {
        continue;
      }
      if (network[focal][i] && network[i][target]) {
        neighborScore++;
      }
    }
    return neighborScore / (double) degree[focal];
  }

  double getNeighborScoreNetworkClosure(int focal, int target, boolean[][] network) {
    double neighborScore = 0;
    for (int i = 0; i < Main.N; i++) {
      if (focal == i || target == i) {
        continue;
      }
      if (network[focal][i] && network[i][target]) {
        neighborScore++;
      }
    }
    return neighborScore / (double) degree[focal];
  }

  double getNeighborScorePreferentialAttachement(int focal, int target) {
    return (degree[target] - degreeMin) / (double) degreeRange;
  }

  void doEvaluateNeighbor() {
    neighborScore = new double[Main.N][Main.N];
    neighborhoodScore = new double[Main.N];
    if (isHomophilyOnChar) {
      for (int focal : focalIndexArray) {
        for (int target = focal; target < Main.N; target++) {
          if (!network[focal][target]) {
            continue;
          }
          neighborScore[focal][target] = getNeighborScoreHomophilyOnChar(focal, target);
          neighborScore[target][focal] = neighborScore[focal][target];
          neighborhoodScore[focal] += neighborScore[focal][target];
          neighborhoodScore[target] += neighborScore[target][focal];
        }
      }
    } else if (isHomophilyOnStat) {
      for (int focal : focalIndexArray) {
        for (int target = focal; target < Main.N; target++) {
          if (!network[focal][target]) {
            continue;
          }
          neighborScore[focal][target] = getNeighborScoreHomophilyOnStatus(focal, target);
          neighborScore[target][focal] = neighborScore[focal][target];
          neighborhoodScore[focal] += neighborScore[focal][target];
          neighborhoodScore[target] += neighborScore[target][focal];
        }
      }
    } else if (isNetworkClosure) {
      for (int focal : focalIndexArray) {
        for (int target = focal; target < Main.N; target++) {
          if (!network[focal][target]) {
            continue;
          }
          neighborScore[focal][target] = getNeighborScoreNetworkClosure(focal, target);
          neighborScore[target][focal] = neighborScore[focal][target] * (degree[focal] - 1) / (degree[target] - 1);
          neighborhoodScore[focal] += neighborScore[focal][target];
          neighborhoodScore[target] += neighborScore[target][focal];
        }
      }
    } else if (isPreferentialAttachment) {
      degreeMax = neighborhoodScore[0];
      degreeMin = neighborhoodScore[0];
      for (int focal = 1; focal < Main.N; focal++) {
        if (neighborhoodScore[focal] > degreeMax) {
          degreeMax = neighborhoodScore[focal];
        } else if (neighborhoodScore[focal] < degreeMin) {
          degreeMin = neighborhoodScore[focal];
        }
      }
      degreeRange = degreeMax - degreeMin;
      for (int target : targetIndexArray) {
        double score = getNeighborScorePreferentialAttachement(-1, target);
        for (int focal : focalIndexArray) {
          if (!network[focal][target]) {
            continue;
          }
          neighborScore[focal][target] = score;
          neighborhoodScore[focal] += neighborScore[focal][target];
        }
      }
    }
    for (int focal = 0; focal < Main.N; focal++) {
      neighborhoodScore[focal] /= (double) degree[focal];
      // Neighborhood score is higher than desired score = strength
      satisfied[focal] = neighborhoodScore[focal] >= strength;
    }
  }

  @Description("Returns similarity in a fraction [0, 1]")
  double getHomogeneityBetween(int focal, int target) {
    double homogeneityCharacteristic = 0;
    double homogeneityBelief = 0;
    if (Main.WEIGHT_ON_CHARACTERISTIC > 0D) {
      for (int l = 0; l < Main.L; l++) {
        if (typeOf[focal][l] == typeOf[target][l]) {
          homogeneityCharacteristic++;
        }
      }
      homogeneityCharacteristic = Main.WEIGHT_ON_CHARACTERISTIC * homogeneityCharacteristic / (double) Main.L;
    }
    if (Main.WEIGHT_ON_BELIEF > 0D) {
      for (int m = 0; m < Main.M; m++) {
        if (beliefOf[focal][m] == beliefOf[target][m]) {
          homogeneityBelief++;
        }
      }
      homogeneityBelief /= Main.WEIGHT_ON_BELIEF * homogeneityBelief / (double) Main.M;
    }
    return homogeneityCharacteristic + homogeneityBelief;
  }

  void setObservationStructure() {
    //Revised with matrix multiplication - Test its validity
    observationStructure = new boolean[Main.N][];
    boolean[][] networkInDegree = new boolean[Main.N][];
    for (int focal = 0; focal < Main.N; focal++) {
      networkInDegree[focal] = network[focal].clone();
      observationStructure[focal] = network[focal].clone();
    }
    for (int d = 1; d < observationScope; d++) { // Degree
      for (int row = 0; row < Main.N; row++) {
        for (int col = 0; col < Main.N; col++) {
          for (int i = 0; i < Main.N; i++) {
            networkInDegree[row][col] = networkInDegree[row][i] && network[i][col];
            if (networkInDegree[row][col]) {
              observationStructure[row][col] = true;
            }
          }
        }
      }
    }
  }

  void doRewiring() {
    numRewiring = 0;
    boolean[] hasNewTie = new boolean[Main.N];
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      if (satisfied[focal] ||
          hasNewTie[focal] ||
          degreeFlexible[focal] == 0) {
        continue;
      }
      //Selection of a neighbor to be cut out
      int target2Cut = -1;
      double worstNeighborScore = Double.MAX_VALUE;
      shuffleFisherYates(targetIndexArray);
      for (int target : targetIndexArray) {
        if (networkFlexible[focal][target]) {
          if (degree[target] == 1) {
            continue;
          }
          if (neighborScore[focal][target] < worstNeighborScore) {
            target2Cut = target;
            worstNeighborScore = neighborScore[focal][target];
          }
        }
      }
      if (target2Cut == -1) {
        continue;
      }
      //Selection of a new neighbor to connect
      shuffleFisherYates(targetIndexArray);
      for (int target2Link : targetIndexArray) {
        if (satisfied[target2Link] || // If the target is already satisfied
            hasNewTie[target2Link] || // If the target already has a new tie
            network[focal][target2Link] || // If the target is already a neighbor of the focal
            !observationStructure[focal][target2Link] || // If the target is outside the observation structure
            focal == target2Link // If the target is focal herself
        ) {
          continue;
        }
        double target2LinkScore = Double.NaN;
        boolean[][] networkAlt = new boolean[Main.N][];
        if (isHomophilyOnChar) {
          target2LinkScore = getNeighborScoreHomophilyOnChar(focal, target2Link);
        } else if (isHomophilyOnStat) {
          target2LinkScore = getNeighborScoreHomophilyOnStatus(focal, target2Link);
        } else if (isNetworkClosure) {
          target2LinkScore = getNeighborScoreNetworkClosure(focal, target2Link);
        } else if (isPreferentialAttachment) {
          target2LinkScore = getNeighborScorePreferentialAttachement(focal, target2Link);
        }
        if (target2LinkScore > worstNeighborScore) {
          //Checking for mutual agreement
          double focalScore = Double.NaN;
          if (isHomophilyOnChar) {
            focalScore = target2LinkScore;
          } else if (isHomophilyOnStat) {
            focalScore = target2LinkScore;
          } else if (isNetworkClosure) {
            focalScore = target2LinkScore / (degree[focal] - 1D) * (degree[target2Link] - 1D);
          } else if (isPreferentialAttachment) {
            focalScore = getNeighborScorePreferentialAttachement(target2Link, focal);
          }
          if (focalScore < neighborhoodScore[target2Link]) {
            //Potential score of focal is higher than average neighborhoods core
            continue;
          }
          // Rewiring tie
          network[focal][target2Cut] = false;
          network[target2Cut][focal] = false;
          networkFlexible[focal][target2Cut] = false;
          networkFlexible[target2Cut][focal] = false;
          degree[target2Cut]--;
          degreeFlexible[target2Cut]--;
          network[focal][target2Link] = true;
          network[target2Link][focal] = true;
          networkFlexible[focal][target2Link] = true;
          networkFlexible[target2Link][focal] = true;
          degree[target2Link]++;
          degreeFlexible[target2Link]++;
          hasNewTie[focal] = true;
          hasNewTie[target2Link] = true;
          numRewiring++;
          // Update degree max min range
          if (degree[target2Link] > degreeMax) {
            degreeMax = degree[target2Link];
          }
          if (degree[target2Link] < degreeMin) {
            degreeMin = degree[target2Link];
          }
          if (degree[target2Cut] > degreeMax) {
            degreeMax = degree[target2Cut];
          }
          if (degree[target2Cut] < degreeMin) {
            degreeMin = degree[target2Cut];
          }
          // Reset Neighborhood Score
          doEvaluateNeighbor();
          //Occurs at most once for each individual
          break;
        }
      }
    }
  }

  void doRewiring(int numRewiring) {
    //Random rewiring
    int numRewiringLeft = numRewiring;
    boolean[] hasNewTie = new boolean[Main.N];
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      if (numRewiringLeft == 0) {
        break;
      }
      if (hasNewTie[focal] ||
          degreeFlexible[focal] == 0) {
        continue;
      }
      int target2Cut = -1;
      shuffleFisherYates(targetIndexArray);
      for (int target : targetIndexArray) {
        if (degree[target] == 1) {
          continue;
        }
        if (networkFlexible[focal][target]) {
          target2Cut = target;
          break;
        }
      }
      if (target2Cut == -1) {
        continue;
      }
      shuffleFisherYates(targetIndexArray);
      for (int target2Link : targetIndexArray) {
        if (network[focal][target2Link] || // If the target is already a neighbor of the focal
            //@@@ WE MAY NEED TO REVISIT THE THEORETICAL VALIDITY OF THESE
            hasNewTie[target2Link] || // If the target already has a new tie
            !observationStructure[focal][target2Link] || // If the target is outside the observation structure
            //@@@
            focal == target2Link // If the target is focal herself
        ) {
          continue;
        }
        hasNewTie[focal] = true;
        hasNewTie[target2Link] = true;
        // Rewiring tie
        networkFlexible[focal][target2Cut] = false;
        networkFlexible[target2Cut][focal] = false;
        network[focal][target2Cut] = false;
        network[target2Cut][focal] = false;
        degreeFlexible[target2Cut]--;
        degree[target2Cut]--;
        networkFlexible[focal][target2Link] = true;
        networkFlexible[target2Link][focal] = true;
        network[focal][target2Link] = true;
        network[target2Link][focal] = true;
        degreeFlexible[target2Link]++;
        degree[target2Link]++;
        numRewiringLeft--;
        break;
      }
    }
  }

  void doLearning() {
    boolean[][] beliefOfUpdated = new boolean[Main.N][Main.M];
    for (int focal = 0; focal < Main.N; focal++) {
      int[] majorityOpinion = new int[Main.M];
      for (int target = 0; target < Main.N; target++) {
        if (network[focal][target] && performance[target] > performance[focal]) {
          for (int m = 0; m < Main.M; m++) {
            majorityOpinion[m] += beliefOf[target][m] ? 1 : -1;
          }
        }
      }
      for (int m = 0; m < Main.M; m++) {
        if (r.nextDouble() < Main.P_LEARNING) {
          if (majorityOpinion[m] > 0) {
            beliefOfUpdated[focal][m] = true;
          } else if (majorityOpinion[m] < 0) {
            beliefOfUpdated[focal][m] = false;
          } else {
            beliefOfUpdated[focal][m] = beliefOf[focal][m];
          }
        } else {
          beliefOfUpdated[focal][m] = beliefOf[focal][m];
        }
      }
    }
    beliefOf = beliefOfUpdated;
    for (int focal = 0; focal < Main.N; focal++) {
      setPerformance(focal);
    }
  }

  int getPerformance(int focal) {
    //VERRY WEIRD... COPY FROM ALTRUISM MODEL AND TEST
    int performanceNow = 0;
    boolean[] beliefOfFocal = beliefOf[focal];
    for (int bundle = 0; bundle < Main.M_OF_BUNDLE; bundle++) {
      int countMInBundle = 0;
      boolean matchAll = true;
      for (int m = 0; m < Main.M; m++) {
        if (realityBundleID[m] == bundle) {
          countMInBundle++;
          if (beliefOfFocal[m] != reality[m]) {
            matchAll = false;
            break;
          }
        }
      }
      if (matchAll) {
        performanceNow += countMInBundle;
      }
    }
    return performanceNow;
  }

  void setPerformance(int focal) {
    performance[focal] = getPerformance(focal);
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
      for (int l = 0; l < Main.L; l++) {
        csvWriter.append("SOURCE_TYPE" + l);
        csvWriter.append(",");
      }
      csvWriter.append("TIE_ENFORCED");
      csvWriter.append(",");
      csvWriter.append("L");
      csvWriter.append("\n");

      //Edge
      for (int focal = 0; focal < Main.N; focal++) {
        for (int target = focal; target < Main.N; target++) {
          if (focal == target) {
            continue;
          }
          if (network[focal][target]) {
//            csvWriter.append("SOURCE");
            csvWriter.append(Integer.toString(focal));
            csvWriter.append(",");

//            csvWriter.append("TARGET");
            csvWriter.append(Integer.toString(target));
            csvWriter.append(",");

//            csvWriter.append("SOURCE_TYPE");
            for (int l = 0; l < Main.L; l++) {
              csvWriter.append(typeOf[focal][l] ? "true" : "false");
              csvWriter.append(",");
            }

//            csvWriter.append("TIE_ENFORCED");
            csvWriter.append(Boolean.toString(networkEnforced[focal][target]));
            csvWriter.append(",");

//            csvWriter.append("L");
            csvWriter.append(Integer.toString(Main.L));
            csvWriter.append("\n");
          }
        }
      }

      //Individual
      for (int focal = 0; focal < Main.N; focal++) {
//        csvWriter.append("SOURCE");
        csvWriter.append(Integer.toString(focal));
        csvWriter.append(",");

//        csvWriter.append("TARGET");
        csvWriter.append(",");

//        csvWriter.append("SOURCE_TYPE");
        for (int l = 0; l < Main.L; l++) {
          csvWriter.append(typeOf[focal][l] ? "true" : "false");
          csvWriter.append(",");
        }

//        csvWriter.append("TIE_ENFORCED");
        csvWriter.append(",");

//        csvWriter.append("L");
        csvWriter.append(Integer.toString(Main.L));
        csvWriter.append("\n");
      }

      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void shuffleFisherYates(int[] nArray) {
    for (int i = Main.N - 1; i > 0; i--) {
      int j = r.nextInt(i + 1);
      int temp = nArray[i];
      nArray[i] = nArray[j];
      nArray[j] = temp;
    }
  }
}
