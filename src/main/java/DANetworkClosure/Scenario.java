package DANetworkClosure;

import java.io.FileWriter;
import java.io.IOException;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;
import org.apache.commons.math3.util.FastMath;

public class Scenario {

  RandomGenerator r;
  NetworkAnalyzer na;

  boolean isNotConverged = true;
  boolean isNetworkConverged = false;
  boolean isLearningConverged = false;

  boolean isRewiring;
  boolean isRandomRewiring;
  int networkClosureDegree;

  int[] focalIndexArray;
  int[] targetIndexArray;
  int[] dyadIndexArray;
  int[][] dyad2DIndexArray;

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
  int[][] numPathsWithinNetworkClosureDegree;
  int[] degree;
  int[] degreeEnforced;
  int[] degreeFlexible;
  double[][] differenceOf;
  double[] differenceSum;

  double[][] neighborScore;
  double[] neighborhoodScore;
  int numFormation;
  int numBreak;
  int numFormationLeft;
  int numBreakLeft;
  double performanceAvg;
  double performanceMax;
  double performanceMin;
  double performanceRange;
  double disagreementAvg;

  double diameter;
  double density;
  double averagePathLength;
  double networkEfficiency;
  double globalClustering;
  double globalClusteringWattsStrogatz;
  double overallCentralization;
  double betweennessCentralityVariance;

  double satisfactionRate;

  Scenario(int networkClosureDegree, int span, double connectivity, double enforcement) {
    r = new MersenneTwister();

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

    this.networkClosureDegree = networkClosureDegree;
    this.span = span;
    this.enforcement = enforcement;
    this.connectivity = connectivity;

    isRewiring = true;
    isRandomRewiring = false;

    initialize();
  }

  public Scenario getClone() {
    Scenario clone = new Scenario(this.networkClosureDegree, this.span, this.enforcement, this.connectivity);

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

    clone.na = new NetworkAnalyzer(clone.network);
    clone.na.setNetworkMetrics();

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
          network[upper][lower] = true;
          network[lower][upper] = true;
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
            network[focal][target] = true;
            network[target][focal] = true;
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

    //Additional Connection
    for (int focal = 0; focal < Main.N; focal++) {
      for (int target = focal; target < Main.N; target++) {
        if (!network[focal][target] &&
            degreeFlexible[focal] < Main.MAX_INFORMAL &&
            degreeFlexible[target] < Main.MAX_INFORMAL &&
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
    performanceMax = Double.MIN_VALUE;
    performanceMin = Double.MAX_VALUE;
    differenceOf = new double[Main.N][Main.N];
    differenceSum = new double[Main.N];
    setPerformance();
    setOutcome();
  }

  void stepForward() {
    if (isNotConverged) {
      if (isRewiring && !isNetworkConverged) {
        setNumPathsWithinNetworkClosureDegree();
        doEvaluateNeighbor();
        doRewiring();
      }
      doLearning();
      setOutcome();
      isNotConverged = !isLearningConverged && !isNetworkConverged;
    }
  }

  void stepForward(int numFormation, int numBreak, boolean sourceIsNotConverged) {
    if (isNotConverged && sourceIsNotConverged) {
      if (isRewiring) {
        doRewiring(numFormation, numBreak);
      }
      doLearning();
      setOutcome();
      isNotConverged = !isLearningConverged && !isNetworkConverged && sourceIsNotConverged;
//      isNotConverged = !isLearningConverged || sourceIsNotConverged; // sourceIsNotConverged is guaranteed.
    }
  }

  void setOutcome() {
    performanceAvg = 0;
    disagreementAvg = 0;
    satisfactionRate = 0;
    na.setNetworkMetrics();
    density = na.getDensity();
    diameter = na.getDiameter();
    averagePathLength = na.getAveragePathLength();
    networkEfficiency = na.getNetworkEfficiency();
    globalClustering = na.getGlobalClustering();
    globalClusteringWattsStrogatz = na.getGlobalClusteringWattsStrogatz();
    overallCentralization = na.getGlobalClosenessCentralization();
    betweennessCentralityVariance = na.getBetweennessCentralityVariance();

    for (int focal = 0; focal < Main.N; focal++) {
      performanceAvg += performance[focal];
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

  double getNeighborScoreNetworkClosure(int focal, int target) {
//    double denominator = network[focal][target] ? degree[focal] : degree[focal] + 1; //Fixed 240504
//    return numPathsWithinNetworkClosureDegree[focal][target] / denominator;
    return numPathsWithinNetworkClosureDegree[focal][target];
  }

  void doEvaluateNeighbor() {
    neighborScore = new double[Main.N][Main.N];
    neighborhoodScore = new double[Main.N];

    for (int focal : focalIndexArray) {
      for (int target = focal; target < Main.N; target++) {
        if (!network[focal][target]) {
          continue;
        }
        neighborScore[focal][target] = getNeighborScoreNetworkClosure(focal, target);
        neighborScore[target][focal] = getNeighborScoreNetworkClosure(target, focal);
        neighborhoodScore[focal] += neighborScore[focal][target];
        neighborhoodScore[target] += neighborScore[target][focal];
      }
    }

    for (int focal = 0; focal < Main.N; focal++) {
      neighborhoodScore[focal] /= (double) degree[focal];
      // Neighborhood score is higher than desired score = strength
    }
  }

  void setNumPathsWithinNetworkClosureDegree() {
    boolean[] visited;
    numPathsWithinNetworkClosureDegree = new int[Main.N][Main.N];
    for (int focal : focalIndexArray) {
      visited = new boolean[Main.N];
      depthLmitedCount(focal, focal, 1, visited);
    }
//    System.out.println(networkClosureDegree+"\t"+Arrays.deepToString(numPathsWithinNetworkClosureDegree));
  }

  void depthLmitedCount(int start, int focal, int depthNow, boolean[] visited) {
    if (depthNow > networkClosureDegree) {
      return;
    }
    visited[focal] = true;
    for (int n = 0; n < Main.N; n++) {
      if (network[focal][n] && !visited[n]) {
        if (depthNow >= 2) {
          numPathsWithinNetworkClosureDegree[start][n]++;
        }
        depthLmitedCount(start, n, depthNow + 1, visited);
      }
    }
    visited[focal] = false; // Unmark the focal individual before backtracking
  }

  void doRewiring() {
    isNetworkConverged = true;
    numFormation = 0;
    numBreak = 0;
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      int target2Form = -1;
      int target2Break = -1;
      // Searching for a tie to form
      if (
          degreeFlexible[focal] < Main.MAX_INFORMAL
      ) {
        //Selection of a new neighbor to connect
        shuffleFisherYates(targetIndexArray);
        for (int target : targetIndexArray) {
          if (!network[focal][target] &&
              degreeFlexible[target] < Main.MAX_INFORMAL &&
              focal != target
          ) {
            double targetScore = getNeighborScoreNetworkClosure(focal, target);
//            System.out.println(span+"\t"+networkClosureDegree+"\t\t"+targetScore+"\t"+neighborhoodScore[focal]);
            if (targetScore > neighborhoodScore[focal]) {
//          if (targetScore >= neighborhoodScore[focal]) { //ALWAYSFORM
              //Checking for mutual agreement
              double focalScore = getNeighborScoreNetworkClosure(target, focal);
              if (focalScore >= neighborhoodScore[target]) { // this makes a huuuge difference in resulting network pattern
                //Potential score of focal is higher than average neighborhoods core
                target2Form = target;
                break;
              }
            }
          }
        }
      }
      // Searching for a tie to break
      if (
//          degreeFlexible[focal] > Main.MAX_INFORMAL //@240504
          degreeFlexible[focal] > Main.MIN_INFORMAL //@240508
      ) {
        double worstNeighborScore = Double.MAX_VALUE;
        shuffleFisherYates(targetIndexArray);
        for (int target : targetIndexArray) {
          if (networkFlexible[focal][target] &&
              degree[target] > 1
          ) {
            if (neighborScore[focal][target] < worstNeighborScore
                &&
                neighborScore[focal][target] < neighborhoodScore[focal] //@@@@ THIS IS AN IMPORTANT ASSUMPTION FOR DEGREE[FOCAL]>1
            ) {
              target2Break = target;
              worstNeighborScore = neighborScore[focal][target];
            }
          }
        }
      }
      // Forming tie
      if (target2Form != -1) {
        isNetworkConverged = false;
        numFormation++;
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
        isNetworkConverged = false;
        numBreak++;
        network[focal][target2Break] = false;
        network[target2Break][focal] = false;
        networkFlexible[focal][target2Break] = false;
        networkFlexible[target2Break][focal] = false;
        degree[focal]--;
        degreeFlexible[focal]--;
        degree[target2Break]--;
        degreeFlexible[target2Break]--;
      }
    }
  }

  void doRewiring(int numFormation, int numBreak) {
    this.numFormationLeft = numFormation;
    this.numBreakLeft = numBreak;
    //Random rewiring
    shuffleFisherYates(dyadIndexArray);
    for (int dyad : dyadIndexArray) {
      int focal = dyad2DIndexArray[dyad][0];
      int target = dyad2DIndexArray[dyad][1];
      if (networkFlexible[focal][target] &&
          numBreakLeft > 0
      ) {
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
          !network[focal][target] &&
//          observationStructure[focal][target] &&
//          degreeFlexible[focal] < Main.MAX_INFORMAL &&
//          degreeFlexible[target] < Main.MAX_INFORMAL && //240507
              numFormationLeft > 0
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
    if (numFormationLeft != 0 || numBreakLeft != 0) {
      doRewiring(numFormationLeft, numBreakLeft);
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
    if (performance[focal] > performanceMax) {
      performanceMax = performance[focal];
      performanceRange = performanceMax - performanceMax;
    }
    if (performance[focal] < performanceMin) {
      performanceMin = performance[focal];
      performanceRange = performanceMax - performanceMax;
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
    for (int i = nArray.length - 1; i > 0; i--) {
      int j = r.nextInt(i + 1);
      int temp = nArray[i];
      nArray[i] = nArray[j];
      nArray[j] = temp;
    }
  }
}