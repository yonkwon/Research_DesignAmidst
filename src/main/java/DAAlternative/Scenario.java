package DAAlternative;

import java.io.FileWriter;
import java.io.IOException;
import jdk.jfr.Description;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;

public class Scenario {

  RandomGenerator r;

  int socialMechanism;

  boolean isRewiring;
  boolean isHomophily;
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

  int[] isInUnit;
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
  double[] neighborhoodScoreScaled01;
  boolean[] satisfied;

  double maxSumDegree, minSumDegree, rangeSumDegree;

  int numRewiring;
  double performanceAvg;
  double disagreementAvg;
  double clusteringCoefficient;
  double satisfactionRate;

  Scenario(int socialMechanism, double strength, int span, double enforcement) {
    r = new MersenneTwister();

    this.socialMechanism = socialMechanism;
    switch (socialMechanism) {
      case 0 -> isHomophily = true;
      case 1 -> isNetworkClosure = true;
      case 2 -> isPreferentialAttachment = true;
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

    degreeEnforced = new int[Main.N];
    degreeFlexible = new int[Main.N];
    degree = new int[Main.N];
    isInUnit = new int[Main.N];

    int managerStart = 1;
    int managerEnd = span+1;
    for( int manager = managerStart; manager < managerEnd; manager ++ ){
      network[0][manager] = true;
      network[manager][0] = true;
      degree[manager] ++;
      degree[0] ++;
    }
    int workerStart = managerEnd;
    int workerEnd = Main.N;
    for( int worker = workerStart; worker < workerEnd; worker ++ ){
      int manager2Assign = r.nextInt(span)+1;
      network[manager2Assign][worker] = true;
      network[worker][manager2Assign] = true;
      degree[manager2Assign]++;
      degree[worker]++;
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
    setNeighborScore();
    setOutcome();
  }

  void stepForward() {
    if (isRewiring) {
      setNeighborScore();
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
    clusteringCoefficient /= Main.NUM_TRIPLET;
  }


  void setNeighborScore() {
    neighborScore = new double[Main.N][Main.N];
    neighborhoodScore = new double[Main.N];
    neighborhoodScoreScaled01 = new double[Main.N];
    if (isHomophily) {
      for (int focal : focalIndexArray) {
        for (int target : targetIndexArray) {
          if (network[focal][target]) {
            neighborScore[focal][target] = getHomogeneityBetween(focal, target);
            neighborhoodScore[focal] += neighborScore[focal][target];
          }
        }
        neighborhoodScoreScaled01[focal] = neighborhoodScore[focal] / degree[focal];
      }
    } else if (isNetworkClosure) {
      for (int row = 0; row < Main.N; row++) {
        for (int col = 0; col < Main.N; col++) {
          if (row == col) {
            continue;
          }
          for (int i = 0; i < Main.N; i++) {
            if (network[row][i] && network[i][col]) {
              neighborScore[row][col]++;
              neighborhoodScore[row]++;
            }
          }
        }
        neighborhoodScoreScaled01[row] =
            neighborhoodScore[row] / (degree[row] * (degree[row] - 1D) / 2D);
      }
    } else if (isPreferentialAttachment) {
      for (int focal : focalIndexArray) {
        for (int target : targetIndexArray) {
          if (network[focal][target]) {
            neighborScore[focal][target] = degree[target];
            neighborhoodScore[focal] += degree[target];
          }
        }
      }
      maxSumDegree = neighborhoodScore[0];
      minSumDegree = neighborhoodScore[0];
      for (int focal = 1; focal < Main.N; focal++) {
        if (neighborhoodScore[focal] > maxSumDegree) {
          maxSumDegree = neighborhoodScore[focal];
        }
        if (neighborhoodScore[focal] < minSumDegree) {
          minSumDegree = neighborhoodScore[focal];
        }
      }
      rangeSumDegree = maxSumDegree - minSumDegree;
      for (int focal : focalIndexArray) {
        neighborhoodScoreScaled01[focal] = (neighborhoodScore[focal] - minSumDegree) / rangeSumDegree;
      }
    }

    for (int focal = 0; focal < Main.N; focal++) {
      satisfied[focal] = neighborhoodScoreScaled01[focal] < strength;
    }
  }

  @Description("Returns similarity in a fraction [0, 1]")
  double getHomogeneityBetween(int focal, int target) {
    double homogeneity;
    int homogeneityCharacteristic = 0;
    int homogeneityBelief = 0;
    if (Main.WEIGHT_ON_CHARACTERISTIC > 0D) {
      for (int l = 0; l < Main.L; l++) {
        if (typeOf[focal][l] == typeOf[target][l]) {
          homogeneityCharacteristic++;
        }
      }
    }
    if (Main.WEIGHT_ON_BELIEF > 0D) {
      for (int m = 0; m < Main.M; m++) {
        if (beliefOf[focal][m] == beliefOf[target][m]) {
          homogeneityBelief++;
        }
      }
    }
    homogeneity
        = (Main.WEIGHT_ON_CHARACTERISTIC > 0 ? Main.WEIGHT_ON_CHARACTERISTIC * homogeneityCharacteristic / (double) Main.L : 0)
        + (Main.WEIGHT_ON_BELIEF > 0 ? Main.WEIGHT_ON_BELIEF * homogeneityBelief / (double) Main.M : 0);
    return homogeneity;
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
      for (int target = 0; target < Main.N; target++) {
        if (degree[target] == 1) {
          continue;
        }
        if (networkFlexible[focal][target]) {
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
        if (isHomophily) {
          target2LinkScore = getHomogeneityBetween(focal, target2Link);
        } else if (isNetworkClosure) {
          target2LinkScore = 0;
          for (int contact = 0; contact < Main.N; contact++) {
            if (contact == target2Cut) {
              continue;
            }
            if (network[focal][contact] && network[target2Link][contact]) {
              target2LinkScore++;
            }
          }
        } else if (isPreferentialAttachment) {
          target2LinkScore = degree[target2Link];
        }
        if (target2LinkScore > worstNeighborScore) {
          //Checking for mutual agreement
          //230910 This check didn't occur for network closure ...
          double focalScore = Double.NaN;
          double neighborhoodScoreAverageOfTarget = 0;
          if (isHomophily || isNetworkClosure) {
            focalScore = target2LinkScore;
          } else if (isPreferentialAttachment) {
            focalScore = degree[focal];
          }
          for (int targetTarget = 0; targetTarget < Main.N; targetTarget++) {
            if (network[target2Link][targetTarget]) {
              neighborhoodScoreAverageOfTarget += neighborScore[target2Link][targetTarget];
            }
          }
          neighborhoodScoreAverageOfTarget /= degree[target2Link];
          if (focalScore < neighborhoodScoreAverageOfTarget) {
            //Potential score of focal is higher than average neighborhoods core
            continue;
          }
          numRewiring++;
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
          // Updating Neighbor score and satisfaction
          neighborScore[focal][target2Link] = target2LinkScore;
          neighborScore[target2Link][focal] = focalScore;
          neighborhoodScore[focal] -= neighborScore[focal][target2Cut];
          neighborScore[focal][target2Cut] = 0;
          neighborhoodScore[focal] += neighborScore[focal][target2Link];
          neighborhoodScore[target2Link] += neighborScore[target2Link][focal];
          if (isHomophily) {
            neighborhoodScoreScaled01[focal] /= degree[focal];
            neighborhoodScoreScaled01[target2Cut] /= degree[target2Cut];
            neighborhoodScoreScaled01[target2Link] /= degree[target2Link];
          } else if (isNetworkClosure) {
            neighborhoodScoreScaled01[focal] /= (degree[focal] * (degree[focal] - 1D) / 2D);
            neighborhoodScoreScaled01[target2Cut] /= (degree[target2Cut] * (degree[target2Cut] - 1D) / 2D);
            neighborhoodScoreScaled01[target2Link] /= (degree[target2Link] * (degree[target2Link] - 1D) / 2D);
          } else if (isPreferentialAttachment) {
            if (degree[target2Link] > maxSumDegree) {
              maxSumDegree = degree[target2Link];
            }
            if (degree[target2Link] < minSumDegree) {
              minSumDegree = degree[target2Link];
            }
            if (degree[target2Cut] > maxSumDegree) {
              maxSumDegree = degree[target2Cut];
            }
            if (degree[target2Cut] < minSumDegree) {
              minSumDegree = degree[target2Cut];
            }
            neighborhoodScoreScaled01[focal] = (neighborhoodScore[focal] - minSumDegree) / rangeSumDegree;
            neighborhoodScoreScaled01[target2Cut] = (neighborhoodScore[target2Cut] - minSumDegree) / rangeSumDegree;
            neighborhoodScoreScaled01[target2Link] = (neighborhoodScore[target2Link] - minSumDegree) / rangeSumDegree;
          }
          satisfied[focal] = neighborhoodScoreScaled01[focal] < strength;
          satisfied[target2Cut] = neighborhoodScoreScaled01[focal] < strength; //@ 230910 Update: This line didn't exist in the previous code...
          satisfied[target2Link] = neighborhoodScoreScaled01[focal] < strength;
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
      csvWriter.append("SOURCE_UNIT");
      csvWriter.append(",");
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

//            csvWriter.append("SOURCE_UNIT");
            csvWriter.append(Integer.toString(isInUnit[focal]));
            csvWriter.append(",");

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

//        csvWriter.append("SOURCE_UNIT");
        csvWriter.append(Integer.toString(isInUnit[focal]));
        csvWriter.append(",");

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
