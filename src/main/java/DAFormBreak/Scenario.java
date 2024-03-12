package DAFormBreak;

import java.io.FileWriter;
import java.io.IOException;
import jdk.jfr.Description;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public class Scenario {

  RandomGenerator r;
  NetworkAnalyzer na;

  boolean isNotConverged = true;
  boolean isLearningConverged = false;

  int socialMechanism;

  boolean isRewiring;
  boolean isHomophilyOnChar;
  boolean isHomophilyOnStat;
  boolean isNetworkClosure;
  boolean isPreferentialAttachment;
  boolean isRandomRewiring;

  int[] focalIndexArray;
  int[] targetIndexArray;
  int[] dyadIndexArray;
  int[][] dyad2DIndexArray;

  int observationScope;

  double strength;      //Strength of social behavior
  int span;          //Span of control
  double enforcement;   //E
  double connectivity;

  boolean[] reality;
  int[] realityBundleID;
  boolean[][] beliefOf;
  boolean[][] typeOf;

  int[] performance;

  int[] levelOf;
  double levelRange;
  boolean[][] network;
  boolean[][] networkEnforced;
  boolean[][] networkFlexible;
  boolean[][] observationStructure;
  boolean[][] isBridge;
  int DFSTimer;
  int[] degree;
  int[] degreeEnforced;
  int[] degreeFlexible;
  double[][] differenceOf;
  double[] differenceSum;

  double[][] neighborScore;
  double[] neighborhoodScore;
  boolean[] satisfied;

  int numFormation = 0;
  int numBreak = 0;
  double performanceAvg;
  double disagreementAvg;

  double diameter;
  double averagePathLength;
  double networkEfficiency;
  double overallClustering;
  double overallCentralization;

  double satisfactionRate;

  Scenario(int socialMechanism, double strength, int span, double connectivity, double enforcement) {
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

    dyadIndexArray = new int[Main.N * Main.N];
    dyad2DIndexArray = new int[Main.N * Main.N][2];
    for (int d = 0; d < Main.N * Main.N; d++) {
      dyadIndexArray[d] = d;
      dyad2DIndexArray[d][0] = d / Main.N;
      dyad2DIndexArray[d][1] = d % Main.N;
    }

    this.strength = strength;
    this.span = span;
    this.enforcement = enforcement;
    this.connectivity = connectivity;

    this.observationScope = Main.OBSERVATION_SCOPE;

    isRewiring = true;
    isRandomRewiring = false;

    initialize();
  }

  public Scenario getClone() {
    Scenario clone = new Scenario(this.socialMechanism, this.strength, this.span, this.enforcement, this.connectivity);

    clone.reality = this.reality.clone();
    clone.realityBundleID = this.realityBundleID.clone();
    clone.beliefOf = new boolean[Main.N][];
    clone.typeOf = new boolean[Main.N][];
    clone.performance = this.performance.clone();

    clone.networkEnforced = new boolean[Main.N][];
    clone.networkFlexible = new boolean[Main.N][];
    clone.network = new boolean[Main.N][];
    clone.na = new NetworkAnalyzer(clone.network);

    clone.degreeEnforced = this.degreeEnforced.clone();
    clone.degreeFlexible = this.degreeFlexible.clone();
    clone.degree = this.degree.clone();

    clone.differenceOf = new double[Main.N][];
    clone.differenceSum = this.differenceSum.clone();
    clone.satisfied = this.satisfied.clone();

    clone.numFormation = this.numFormation;
    clone.numBreak = this.numBreak;
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
    network = new boolean[Main.N][Main.N];
    networkEnforced = new boolean[Main.N][Main.N];
    networkFlexible = new boolean[Main.N][Main.N];
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
    int levelNow = 1;
    for (; ; ) {
      for (int i = levelStart; i < levelEnd; i++) {
        levelOf[i] = levelNow;
      }
      levelStart = levelEnd;
      levelEnd = FastMath.min(Main.N, levelEnd + (int) FastMath.pow(span, levelNow));
      if (levelStart == Main.N) {
        levelRange = levelNow;
        break;
      }
      levelNow++;
    }
    levelRange -= levelOf[0];

    //Additional Connection
    for (int focal = 0; focal < Main.N; focal++) {
      for (int target = focal; target < Main.N; target++) {
        if (!network[focal][target] &&
            degree[focal] < Main.MAX_DEGREE &&
            degree[target] < Main.MAX_DEGREE &&
            focal != target) {
          if (r.nextDouble() < connectivity) {
            network[focal][target] = true;
            network[target][focal] = true;
            degree[focal]++;
            degree[target]++;
          }
        }
      }
    }

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

    na = new NetworkAnalyzer(network);
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
    if (isNotConverged) {
      if (isRewiring) {
        doEvaluateNeighbor();
        setObservationStructure();
        doRewiring();
      }
      doLearning();
      setOutcome();
      isNotConverged = !(isLearningConverged && numFormation == 0 && numBreak == 0); // We are uncertain if rewiring will happen
    }
  }

  void stepForward(int numFormation, int numBreak, boolean sourceIsNotConverged) {
    if (isNotConverged && sourceIsNotConverged) {
      if (isRewiring) {
        setObservationStructure();
        doRewiring(numFormation, numBreak);
      }
      doLearning();
      setOutcome();
      isNotConverged = !isLearningConverged && sourceIsNotConverged;
//      isNotConverged = !isLearningConverged || sourceIsNotConverged; // sourceIsNotConverged is guaranteed.
    }
  }

  void setOutcome() {
    performanceAvg = 0;
    disagreementAvg = 0;
    satisfactionRate = 0;
    na.setNetworkMetrics();
    diameter = na.getDiameter();
    averagePathLength = na.getAveragePathLength();
    networkEfficiency = na.getNetworkEfficiency();
    overallClustering = na.getOverallClustering();
    overallCentralization = na.getOverallClosenessCentralization();

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
    performanceAvg /= Main.M_N;
    disagreementAvg /= Main.M_N_DYAD;
    satisfactionRate /= Main.N;
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
    return 1D - FastMath.abs(levelOf[focal] - levelOf[target]) / levelRange;
  }

  double getNeighborScoreNetworkClosure(int focal, int target) {
    double neighborScore = 0;
    for (int i = 0; i < Main.N; i++) {
      if (focal == i || target == i) {
        continue;
      }
      if (network[focal][i] && network[target][i]) {
        neighborScore++;
//        System.out.println(
//            focal+ " " + target + " to " + i +
//            network[focal][i] + " " + network[target][i] + " -> "+ neighborScore
//        );
      }
    }
    return neighborScore / (double) (degree[focal] - 1D);
  }

  double getNeighborScoreNetworkClosure(int focal, int target, boolean[][] network) {
    double neighborScore = 0;
    for (int i = 0; i < Main.N; i++) {
      if (focal == i || target == i) {
        continue;
      }
      if (network[focal][i] && network[target][i]) {
        neighborScore++;
      }
    }
    return neighborScore / (double) (degree[focal] - 1D); // Fixed 231223. Never used before.
  }

  double getNeighborScorePreferentialAttachement(int focal, int target) {
    int degreeMin = degree[focal];
    int degreeMax = degree[focal];
    for (int observable = 0; observable < Main.N; observable++) {
      if (observationStructure[focal][observable]) {
        if (degree[observable] > degreeMax) {
          degreeMax = degree[observable];
        } else if (degree[observable] < degreeMin) {
          degreeMin = degree[observable];
        }
      }
    }
    return (double) (degree[target] - degreeMin) / (double) (degreeMax - degreeMin);
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
          neighborScore[target][focal] = neighborScore[focal][target] * (degree[focal] - 1D) / (degree[target] - 1D);
          neighborhoodScore[focal] += neighborScore[focal][target];
          neighborhoodScore[target] += neighborScore[target][focal];
        }
      }
    } else if (isPreferentialAttachment) {
      for (int focal : focalIndexArray) {
        for (int target = focal; target < Main.N; target++) {
          if (!network[focal][target]) {
            continue;
          } else {
            neighborScore[focal][target] = getNeighborScorePreferentialAttachement(focal, target);
            neighborScore[target][focal] = getNeighborScorePreferentialAttachement(target, focal);
            neighborhoodScore[focal] += neighborScore[focal][target];
            neighborhoodScore[target] += neighborScore[target][focal];
          }
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
    boolean[][] networkInDegreeLeft = new boolean[Main.N][];
    boolean[][] networkInDegreeRight;
    for (int focal = 0; focal < Main.N; focal++) {
      networkInDegreeLeft[focal] = network[focal].clone();
      observationStructure[focal] = network[focal].clone();
    }
    for (int d = 1; d < observationScope; d++) { // Degree
      networkInDegreeRight = new boolean[Main.N][Main.N];
      for (int row = 0; row < Main.N; row++) {
        for (int col = 0; col < Main.N; col++) {
          for (int i = 0; i < Main.N; i++) {
            if (networkInDegreeLeft[row][i] && network[i][col]) {
              networkInDegreeRight[row][col] = true;
              observationStructure[row][col] = true;
              break;
            }
          }
        }
      }
      networkInDegreeLeft = networkInDegreeRight;
    }
  }

  void doRewiring() {
    numFormation = 0;
    numBreak = 0;
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      if (satisfied[focal]) {
        continue;
      }

      int target2Form = -1;
      int target2Break = -1;

      // Searching for a tie to form
      if (degree[focal] <= Main.MAX_DEGREE) {
        //Selection of a new neighbor to connect
        shuffleFisherYates(targetIndexArray);
        for (int target : targetIndexArray) {
          if (satisfied[target] || // If the target is already satisfied
              network[focal][target] || // If the target is already a neighbor of the focal
              !observationStructure[focal][target] || // If the target is outside the observation structure
              degree[target] >= Main.MAX_DEGREE ||
              focal == target // If the target is focal herself
          ) {
            continue;
          }
          double targetScore = Double.NaN;
          if (isHomophilyOnChar) {
            targetScore = getNeighborScoreHomophilyOnChar(focal, target);
          } else if (isHomophilyOnStat) {
            targetScore = getNeighborScoreHomophilyOnStatus(focal, target);
          } else if (isNetworkClosure) {
            targetScore = getNeighborScoreNetworkClosure(focal, target);
          } else if (isPreferentialAttachment) {
            targetScore = getNeighborScorePreferentialAttachement(focal, target);
          }
          if (targetScore > neighborhoodScore[focal]) {
            //Checking for mutual agreement
            double focalScore = Double.NaN;
            if (isHomophilyOnChar) {
              focalScore = targetScore;
            } else if (isHomophilyOnStat) {
              focalScore = targetScore;
            } else if (isNetworkClosure) {
              focalScore = targetScore / (degree[focal] - 1D) * (degree[target] - 1D);
            } else if (isPreferentialAttachment) {
              focalScore = getNeighborScorePreferentialAttachement(target, focal);
            }
            if (focalScore > neighborhoodScore[target]) {
              //Potential score of focal is higher than average neighborhoods core
              target2Form = target;
              break;
            }
          }
        }
      }

      // Searching for a tie to break
      if (degreeFlexible[focal] > 0 &&
          degree[focal] >= Main.MAX_DEGREE
//          degree[focal] > 1
      ) {
        double worstNeighborScore = Double.MAX_VALUE;
        shuffleFisherYates(targetIndexArray);
        for (int target : targetIndexArray) {
          if (networkFlexible[focal][target] &&
              degree[target] > 1
          ) {
            if (degree[target] == 1) {
              continue;
            }
            if (neighborScore[focal][target] < worstNeighborScore) {
              target2Break = target;
              worstNeighborScore = neighborScore[focal][target];
            }
          }
        }
      }

      // Forming tie
      if (target2Form != -1) {
        numFormation ++;
        network[focal][target2Form] = true;
        network[target2Form][focal] = true;
        networkFlexible[focal][target2Form] = true;
        networkFlexible[target2Form][focal] = true;
        degree[focal]++;
        degreeFlexible[focal]++;
        degree[target2Form]++;
        degreeFlexible[target2Form]++;
      }

      // Breaking tie
      if (target2Break != -1) {
        numBreak ++;
        network[focal][target2Break] = false;
        network[target2Break][focal] = false;
        networkFlexible[focal][target2Break] = false;
        networkFlexible[target2Break][focal] = false;
        degree[focal]--;
        degreeFlexible[focal]--;
        degree[target2Break]--;
        degreeFlexible[target2Break]--;
      }

      // Reset Neighborhood Score
      doEvaluateNeighbor();
    }
  }

  void doRewiring(int numFormation, int numBreak) {
    //Random rewiring
    int numFormationLeft = numFormation;
    int numBreakLeft = numBreak;
    shuffleFisherYates(dyadIndexArray);
    for (int dyad : dyadIndexArray) {
      int focal = dyad2DIndexArray[dyad][0];
      int target = dyad2DIndexArray[dyad][1];
      if (numBreakLeft > 0 &&
          networkFlexible[focal][target]) {
        network[focal][target] = false;
        network[target][focal] = false;
        networkFlexible[focal][target] = false;
        networkFlexible[target][focal] = false;
        degree[focal]--;
        degreeFlexible[focal]--;
        degree[target]--;
        degreeFlexible[target]--;
        numBreakLeft--;
      } else if (
          numFormationLeft > 0 &&
              !network[focal][target] &&
              degree[focal] < Main.MAX_DEGREE &&
              degree[target] < Main.MAX_DEGREE
      ) {
        network[focal][target] = true;
        network[target][focal] = true;
        networkFlexible[focal][target] = true;
        networkFlexible[target][focal] = true;
        degree[focal]++;
        degreeFlexible[focal]++;
        degree[target]++;
        degreeFlexible[target]++;
        numFormationLeft--;
      }
      if (numFormationLeft == 0 && numBreakLeft == 0) {
        break;
      }
    }
  }

  void doLearning() {
    isLearningConverged = true;
    boolean[][] beliefOfBuffer = new boolean[Main.N][];
    for (int focal = 0; focal < Main.N; focal++) {
      int[] majorityOpinionCount = new int[Main.M];
      beliefOfBuffer[focal] = beliefOf[focal].clone();
      for (int target = 0; target < Main.N; target++) {
        if (network[focal][target] && performance[target] > performance[focal]) {
          for (int m = 0; m < Main.M; m++) {
            majorityOpinionCount[m] += beliefOf[target][m] ? 1 : -1;
          }
        }
      }
      for (int m = 0; m < Main.M; m++) {
        if (majorityOpinionCount[m] > 0) {
          beliefOfBuffer[focal][m] = true;
        } else if (majorityOpinionCount[m] < 0) {
          beliefOfBuffer[focal][m] = false;
        }
      }
    }
    for (int focal = 0; focal < Main.N; focal++) {
      for (int m = 0; m < Main.M; m++) {
        if (beliefOf[focal][m] != beliefOfBuffer[focal][m]) {
          isLearningConverged = false;
          if (r.nextDouble() < Main.P_LEARNING) {
            beliefOf[focal][m] = beliefOfBuffer[focal][m];
          }
        }
      }
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
