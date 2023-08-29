package HPBaseline;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;

public class ADScenario {

  RandomGenerator r;

  boolean isRewiring = false;
  boolean isRandomRewiring = false;

  int[] focalIndexArray;
  int[] targetIndexArray;

  double homophily;               //H
  double beta;                    //Beta
  double enforcement;             //E
  double assortativity; //A

  double turbulenceStrengthValue;
  double turbulenceStrengthDependence;

  boolean[] reality;
  int[] realityBundleID;
  boolean[][] beliefOf;
  boolean[][] typeOf;

  int[] performance;

  boolean[][] networkEnforced;
  boolean[][] networkFlexible;
  boolean[][] network;
  int[] isInUnit;
  int[] degreeEnforced;
  int[] degreeFlexible;
  int[] degree;
  double[][] differenceOf;
  double[] differenceSum;
  boolean[] satisfied;

  int numRewiring;
  double performanceAvg;
  double disagreementAvg;
  double dissimilarityAvg;
  double clusteringCoefficient;
  double satisfactionRate;

  ADScenario(double h, double beta, double enforcement, double assortativity, double turbulenceStrengthValue, double turbulenceStrengthDependence) {
    r = new MersenneTwister();
    focalIndexArray = new int[ADMain.N];
    for (int n = 0; n < ADMain.N; n++) {
      focalIndexArray[n] = n;
    }
    targetIndexArray = focalIndexArray.clone();

    this.homophily = h;
    this.beta = beta;
    this.enforcement = enforcement;
    this.assortativity = assortativity;

    this.turbulenceStrengthValue = turbulenceStrengthValue;
    this.turbulenceStrengthDependence = turbulenceStrengthDependence;

    isRewiring = true;
    isRandomRewiring = false;

    initialize();
  }

  public ADScenario getClone(){
    ADScenario clone = new ADScenario(this.homophily, this.beta, this.enforcement, this.assortativity, this.turbulenceStrengthValue, this.turbulenceStrengthDependence);

    clone.reality = this.reality.clone();
    clone.realityBundleID = this.realityBundleID.clone();
    clone.beliefOf = new boolean[ADMain.N][];
    clone.typeOf = new boolean[ADMain.N][];
    performance = this.performance;

    clone.networkEnforced = new boolean[ADMain.N][];
    clone.networkFlexible = new boolean[ADMain.N][];
    clone.network = new boolean[ADMain.N][];

    clone.degreeEnforced = this.degreeEnforced.clone();
    clone.degreeFlexible = this.degreeFlexible.clone();
    clone.degree = this.degree.clone();

    clone.differenceOf = new double[ADMain.N][];
    clone.differenceSum = this.differenceSum.clone();
    clone.satisfied = this.satisfied.clone();

    clone.numRewiring = this.numRewiring;
    clone.performanceAvg = this.performanceAvg;
    clone.disagreementAvg = this.disagreementAvg;
    clone.dissimilarityAvg = this.dissimilarityAvg;
    clone.satisfactionRate = this.satisfactionRate;

    for(int focal = 0; focal < ADMain.N; focal++){
      clone.beliefOf[focal] = this.beliefOf[focal].clone();
      clone.typeOf[focal] = this.typeOf[focal].clone();

      clone.networkEnforced[focal] = this.networkEnforced[focal].clone();
      clone.networkFlexible[focal] = this.networkFlexible[focal].clone();
      clone.network[focal] = this.network[focal].clone();

      clone.differenceOf[focal] = this.differenceOf[focal].clone();
    }

    return clone;
  }

  public ADScenario getClone(boolean isRewiring, boolean isRandomRewiring){
    ADScenario clone = getClone();
    clone.setNetworkParams(isRewiring, isRandomRewiring);
    return clone;
  }

  void setNetworkParams(boolean isRewiring, boolean isRandomRewiring){
    this.isRewiring = isRewiring;
    this.isRandomRewiring = isRandomRewiring;
  }

  public void copyRealityOf(ADScenario src) {
    reality = src.reality.clone();
    realityBundleID = src.realityBundleID.clone();
  }

  private void initialize() {
    initializeNetwork();
    initializeEntity(); //TypeOf depends on the network (membership)
    initializeOutcome();
  }

  private void initializeNetwork() {
    networkEnforced = new boolean[ADMain.N][ADMain.N];
    networkFlexible = new boolean[ADMain.N][ADMain.N];
    network = new boolean[ADMain.N][ADMain.N];

    degreeEnforced = new int[ADMain.N];
    degreeFlexible = new int[ADMain.N];
    degree = new int[ADMain.N];
    isInUnit = new int[ADMain.N];

    //All-channel within each unit
    for (int unitID = 0; unitID < ADMain.N_OF_UNIT; unitID++) {
      for (int focal = 0; focal < ADMain.N_IN_UNIT; focal++) {
        int focalNow = unitID * ADMain.N_IN_UNIT + focal;
        isInUnit[focalNow] = unitID;
        for (int target = focal; target < ADMain.N_IN_UNIT; target++) {
          if (focal == target) {
            continue;
          }
          int targetNow = unitID * ADMain.N_IN_UNIT + target;
          network[focalNow][targetNow] = true;
          network[targetNow][focalNow] = true;
          degree[focalNow]++;
          degree[targetNow]++;
        }
      }
    }

    //Limited spanning between unit
    //This does not change the difference the degree
    for (int unitID = 0; unitID < ADMain.N_OF_UNIT; unitID++) {
      int toBeCut = unitID * ADMain.N_IN_UNIT; // First one in each unit
      int toRewire = toBeCut + 1; // Second one in each unit
      int toBeRewired = (toBeCut + ADMain.N_IN_UNIT) % ADMain.N; //First one in the next unit
      network[toRewire][toBeCut] = false;
      network[toBeCut][toRewire] = false;
      network[toRewire][toBeRewired] = true;
      network[toBeRewired][toRewire] = true;
      degree[toBeCut]--;
      degree[toBeRewired]++;
    }

    // Rewiring (Fang Lee Schilling 2010: B.2.1.)
    // 210218 FIX: Eta = Eta^2 by new sampleEta() method; Cancelling "mutual agreement"
    // 210222 FIX: Eta = Eta^2; Connecting first and break larger one
    // 210222 21:31 FIX: Lost segregation effect --> Retrieved from RF
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      int focalUnitID = isInUnit[focal];
      int inUnitLast = (focalUnitID + 1) * ADMain.N_IN_UNIT;
      for (int targetInUnit = focalUnitID * ADMain.N_IN_UNIT; targetInUnit < inUnitLast;
          targetInUnit++) { // 210218
        if (network[focal][targetInUnit] && degree[targetInUnit] > 1
            && r.nextDouble() < beta) { //@210222 02:16
//            if( network[focal][targetInUnit] && r.nextDouble() < eta ) { //@210222 21:31 Retrieved from RF
          shuffleFisherYates(targetIndexArray);
          for (int targetOutUnit : targetIndexArray) {
            if (!network[focal][targetOutUnit] && focalUnitID != isInUnit[targetOutUnit]) {
              network[focal][targetInUnit] = false;
              network[targetInUnit][focal] = false;
              network[focal][targetOutUnit] = true;
              network[targetOutUnit][focal] = true;
              degree[targetInUnit]--;
              degree[targetOutUnit]++;
              break;
            }
          }
        }
      }
    }

    //Tie Enforcement
    //210212 Fix: At Least One Enforced Tie
    //210216 Fix: Rollback
    for (int focal = 0; focal < ADMain.N; focal++) {
      for (int target = focal; target < ADMain.N; target++) {
        if (network[focal][target]) {
//            if (degreeEnforced[focal] == 0 || degreeEnforced[target] == 0 || r.nextDouble() < gamma ) { //210212, 210222
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
  }

  private void initializeEntity() {
    reality = new boolean[ADMain.M];
    realityBundleID = new int[ADMain.M];
    typeOf = new boolean[ADMain.N][ADMain.L];
    beliefOf = new boolean[ADMain.N][ADMain.M];

    for (int bundle = 0; bundle < ADMain.M_OF_BUNDLE; bundle++) {
      int baseIndex = bundle * ADMain.M_IN_BUNDLE;
      int endIndex = (bundle + 1) * ADMain.M_IN_BUNDLE;
      for (int m = baseIndex; m < endIndex; m++) {
        reality[m] = r.nextBoolean();
        realityBundleID[m] = bundle;
      }
    }

    boolean[][] typeOfUnit = new boolean[ADMain.N_OF_UNIT][ADMain.L];
    for(int unit = 0; unit < ADMain.N_OF_UNIT; unit ++){
      for( int l = 0; l < ADMain.L; l ++ ){
        typeOfUnit[unit][l] = r.nextBoolean();
      }
    }

    for (int focal = 0; focal < ADMain.N; focal++) {
      for (int l = 0; l < ADMain.L; l++) {
        if( r.nextDouble() < assortativity ){
          typeOf[focal][l] = typeOfUnit[isInUnit[focal]][l];
        }else {
          typeOf[focal][l] = r.nextBoolean();
        }
      }
      for (int m = 0; m < ADMain.M; m++) {
        beliefOf[focal][m] = r.nextBoolean();
      }
    }
  }

  private void initializeOutcome() {
    performance = new int[ADMain.N];

    differenceOf = new double[ADMain.N][ADMain.N];
    differenceSum = new double[ADMain.N];
    satisfied = new boolean[ADMain.N];

    setPerformance();
    setSatisfied();
    setOutcome();
  }

  public void shakeReality() {
    for (int m = 0; m < ADMain.M; m++) {
      if (r.nextDouble() < turbulenceStrengthValue) {
        reality[m] = !reality[m];
      }
      if (r.nextDouble() < turbulenceStrengthDependence) {
        int changeSeed = r.nextInt(ADMain.M_OF_BUNDLE - 1);
        if (changeSeed >= realityBundleID[m]) {
          realityBundleID[m] = changeSeed + 1;
        } else {
          realityBundleID[m] = changeSeed;
        }
      }
    }
  }

  void stepForward() {
    //Step 1. Evaluate Neighbor
    doEvaluateNeighbor();
    //Step 2. Rewiring ... among dissatisfied individuals
    if( isRewiring ) {
      doRewiring();
    }
    //Step 3. Learning
    doLearning();
  }

  void stepForward(int numRewiring) {
    //Step 1. Evaluate Neighbor
    doEvaluateNeighbor();
    //Step 2. Rewiring ... among dissatisfied individuals
    if( isRewiring ) {
      doRewiring(numRewiring);
    }
    //Step 3. Learning
    doLearning();
  }

  void setOutcome() {
    performanceAvg = 0;
    disagreementAvg = 0;
    dissimilarityAvg = 0;
    satisfactionRate = 0;
    clusteringCoefficient = 0;
    for (int focal = 0; focal < ADMain.N; focal++) {
      performanceAvg += performance[focal];
      satisfactionRate += satisfied[focal] ? 1 : 0;
      for (int target = focal; target < ADMain.N; target++) {
        if (network[focal][target]) {
          for (int l = 0; l < ADMain.L; l++) {
            if (typeOf[focal][l] != typeOf[target][l]) {
              dissimilarityAvg++;
            }
          }
          for (int m = 0; m < ADMain.M; m++) {
            if (beliefOf[focal][m] != beliefOf[target][m]) {
              disagreementAvg++;
            }
          }
        }
      }
    }

//      // Global clustering coefficient ~ Transitivity = #Triangle/#Triple
//      for( int focal = 0; focal < TBMain.N; focal ++ ){
//        double focalClusteringCoefficient = 0;
//        boolean[] isFocalNeighbor = network[focal];
//        if( degree[focal] < 2 ) { continue; }
//        for( int target1 = 0; target1 < TBMain.N; target1 ++ ){
//          if( !isFocalNeighbor[target1] ) { continue; }
//          for( int target2 = target1; target2 < TBMain.N; target2 ++ ){
//            if( !isFocalNeighbor[target2] ) { continue; }
//              //A triple; is this a closed triangle?
//            if( network[target1][target2] ){
//              focalClusteringCoefficient++;
//            }
//          }
//        }
//        //Ratio of closed triangles among triples centered on focal.
//        clusteringCoefficient += focalClusteringCoefficient / (
//            degree[focal]*(degree[focal]-1)/2
//        );
//      }

    // Average Efficiency
    // https://en.wikipedia.org/wiki/Efficiency_(network_science)
    // Very inefficient; solving the 'all pairs short path' problem as n-iteration of 'single source short path' problem.
    // https://youtu.be/0XgVhsMOcQM
    double distanceInverseSum = 0;
    for (int source = 0; source < ADMain.N; source++) {
      int[] distanceFromSource = new int[ADMain.N];
      Arrays.fill(distanceFromSource, -1);
      distanceFromSource[source] = 0;

      LinkedList<Integer> queue = new LinkedList<Integer>();
      queue.add(source);

      while (!queue.isEmpty()) {
        int focal = queue.poll();
        for (int target = 0; target < ADMain.N; target++) {
          if (network[focal][target]) {
            if (distanceFromSource[target] == -1) {
              distanceFromSource[target] = distanceFromSource[focal] + 1;
              distanceInverseSum += 1D / distanceFromSource[target];
              queue.add(target);
            }
          }
        }
      }
    }

    clusteringCoefficient = (double) distanceInverseSum / (double) (ADMain.N * (ADMain.N - 1));

    performanceAvg /= ADMain.M_N;
    disagreementAvg /= ADMain.M_N_DYAD;
    dissimilarityAvg /= ADMain.L * ADMain.DENSITY;
    satisfactionRate /= ADMain.N;
  }

  void doEvaluateNeighbor() {
    differenceSum = new double[ADMain.N];
    for (int focal = 0; focal < ADMain.N; focal++) {
      for (int target = focal; target < ADMain.N; target++) {
        if (network[focal][target]) {
          //The target is a neighbor of the focal (Implying they are not the same)
          double differenceNow = getAbsoluteDifference(focal, target);
          differenceOf[focal][target] = differenceNow;
          differenceOf[target][focal] = differenceNow;
          differenceSum[focal] += differenceNow;
          differenceSum[target] += differenceNow;
        }
      }
    }
    for (int focal = 0; focal < ADMain.N; focal++) {
      satisfied[focal] = differenceSum[focal] / degree[focal] <= 1 - homophily;
    }
  }

  void setSatisfied() {
    doEvaluateNeighbor();
  }

  void doRewiring() {
    numRewiring = 0;
    boolean[] hasNewTie = new boolean[ADMain.N];
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      //Only dissatisfied individuals initiate rewiring
      if (satisfied[focal] || hasNewTie[focal]) {
        continue;
      }
//        if( r.nextDouble() < AGMain.R_LEFT ) { continue; } // 210222 // 210718 NOT WORKING
      //Selection of a target to cut out among informal others
      int farthestFlexibleNeighborIndex = -1;
      double farthestFlexibleNeighborDifference = Double.MIN_VALUE;
      for (int target = 0; target < ADMain.N; target++) {
        if (degree[target] == 1) {
          continue;
        } //FIX: 210222 No Isolation
        if (networkFlexible[focal][target]) {
          double differenceNow = getAbsoluteDifference(focal, target);
          if (differenceNow > farthestFlexibleNeighborDifference) {
            farthestFlexibleNeighborIndex = target;
            farthestFlexibleNeighborDifference = differenceNow;
          }
        }
      }
      if (farthestFlexibleNeighborIndex == -1) {
        continue;
      }
      //Selection of a target to connect to among strangers
      shuffleFisherYates(targetIndexArray);
      for (int target : targetIndexArray) {
        if (satisfied[target] || // If the target is already satisfied
            hasNewTie[target] || // If the target already has a new tie
            network[focal][target] || // If the target is already a neighbor of the focal
            focal == target // If the target is focal
        ) {
          continue;
        }
        double differenceNow = getAbsoluteDifference(focal, target);
        if (differenceNow < farthestFlexibleNeighborDifference) {
          //Added in 201217: Mutual agreement!//
          double differenceAvgOfTarget = 0;
          for (int n = 0; n < ADMain.N; n++) {
            differenceAvgOfTarget += getAbsoluteDifference(target, n);
          }
          differenceAvgOfTarget /= degree[target];
          if (differenceNow > differenceAvgOfTarget) {
            continue;
          }
          //Added in 201217: Mutual agreement!//
          numRewiring++;
          hasNewTie[focal] = true;
          hasNewTie[target] = true;
          //Breaking the tie with the farthest informal neighbor
          networkFlexible[focal][farthestFlexibleNeighborIndex] = false;
          networkFlexible[farthestFlexibleNeighborIndex][focal] = false;
          network[focal][farthestFlexibleNeighborIndex] = false;
          network[farthestFlexibleNeighborIndex][focal] = false;
          degreeFlexible[farthestFlexibleNeighborIndex]--;
          degree[farthestFlexibleNeighborIndex]--;
          differenceSum[focal] -= farthestFlexibleNeighborDifference;
          differenceSum[farthestFlexibleNeighborIndex] -= farthestFlexibleNeighborDifference;
          //Building a new tie with the target
          networkFlexible[focal][target] = true;
          networkFlexible[target][focal] = true;
          network[focal][target] = true;
          network[target][focal] = true;
          degreeFlexible[target]++;
          degree[target]++;
          differenceSum[focal] += differenceNow;
          differenceSum[target] += differenceNow;
          //Satisfaction check; No consequence on the remaining rewiring process
          satisfied[focal] = differenceSum[focal] / degree[focal] <= 1 - homophily;
          satisfied[target] = differenceSum[target] / degree[target] <= 1 - homophily;
          //Occurs at most once for each individual
          break;
        }
      }
    }
  }

  void doRewiring(int numRewiring) {
    //Random rewiring
    int numRewiringLeft = numRewiring;
    boolean[] hasNewTie = new boolean[ADMain.N];
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      if (numRewiringLeft == 0) {
        break;
      }
      if (hasNewTie[focal] || degreeFlexible[focal] == 0) {
        continue;
      }
      int target2Cut = -1;
      shuffleFisherYates(targetIndexArray);
      for (int target : targetIndexArray) {
        if (degree[target] == 1) {
          continue;
        } //FIX: 210222 No Isolation
        if (networkFlexible[focal][target]) {
          target2Cut = target;
          break;
        }
      }
      if (target2Cut == -1) {
        continue;
      }
      shuffleFisherYates(targetIndexArray);
      for (int target2See : targetIndexArray) {
        if (hasNewTie[target2See] || network[focal][target2See]) {
          continue;
        }
        network[focal][target2Cut] = false;
        network[target2Cut][focal] = false;
        networkFlexible[focal][target2Cut] = false;
        networkFlexible[target2Cut][focal] = false;

        network[focal][target2See] = true;
        network[target2See][focal] = true;
        networkFlexible[focal][target2See] = true;
        networkFlexible[target2See][focal] = true;

        degree[target2Cut]--;
        degreeFlexible[target2Cut]--;
        degree[target2See]++;
        degreeFlexible[target2See]++;

        numRewiringLeft--;
        break;
      }
    }
  }

  void doLearning() {
    boolean[][] beliefOfUpdated = new boolean[ADMain.N][ADMain.M];
    for (int focal = 0; focal < ADMain.N; focal++) {
      int[] majorityOpinion = new int[ADMain.M];
      for (int target = 0; target < ADMain.N; target++) {
        if (network[focal][target] && performance[target] > performance[focal]) {
          for (int m = 0; m < ADMain.M; m++) {
            majorityOpinion[m] += beliefOf[target][m] ? 1 : -1;
          }
        }
      }
      for (int m = 0; m < ADMain.M; m++) {
        if (r.nextDouble() < ADMain.P_LEARNING) {
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
    for (int focal = 0; focal < ADMain.N; focal++) {
      setPerformance(focal);
    }
  }

  double getAbsoluteDifference(int focal, int target) {
    double difference;
    int differenceCharacteristic = 0;
    int differenceBelief = 0;
    if (ADMain.WEIGHT_ON_CHARACTERISTIC > 0D) {
      for (int l = 0; l < ADMain.L; l++) {
        if (typeOf[focal][l] != typeOf[target][l]) {
          differenceCharacteristic++;
        }
      }
    }
    if (ADMain.WEIGHT_ON_BELIEF > 0D) {
      for (int m = 0; m < ADMain.M; m++) {
        if (beliefOf[focal][m] != beliefOf[target][m]) {
          differenceBelief++;
        }
      }
    }
    difference
        = ADMain.WEIGHT_ON_CHARACTERISTIC > 0 ?
        ADMain.WEIGHT_ON_CHARACTERISTIC * differenceCharacteristic / (double) ADMain.L : 0
        + ADMain.WEIGHT_ON_BELIEF > 0 ? ADMain.WEIGHT_ON_BELIEF * differenceBelief
        / (double) ADMain.M : 0;

    return difference;
  }

  int getPerformance(int focal) {
    //VERRY WEIRD... COPY FROM ALTRUISM MODEL AND TEST
    int performanceNow = 0;
    boolean[] beliefOfFocal = beliefOf[focal];
    for (int bundle = 0; bundle < ADMain.M_OF_BUNDLE; bundle++) {
      int countMInBundle = 0;
      boolean matchAll = true;
      for (int m = 0; m < ADMain.M; m++) {
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
    for (int focal = 0; focal < ADMain.N; focal++) {
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
      for (int l = 0; l < ADMain.L; l++) {
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
      for (int focal = 0; focal < ADMain.N; focal++) {
        for (int target = focal; target < ADMain.N; target++) {
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
            for (int l = 0; l < ADMain.L; l++) {
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
            csvWriter.append(Integer.toString(ADMain.L));
            csvWriter.append("\n");
          }
        }
      }

      //Individual
      for (int focal = 0; focal < ADMain.N; focal++) {
//        csvWriter.append("SOURCE");
        csvWriter.append(Integer.toString(focal));
        csvWriter.append(",");

//        csvWriter.append("TARGET");
        csvWriter.append(",");

//        csvWriter.append("SOURCE_TYPE");
        for (int l = 0; l < ADMain.L; l++) {
          csvWriter.append(typeOf[focal][l] ? "true" : "false");
          csvWriter.append(",");
        }

//        csvWriter.append("SOURCE_UNIT");
        csvWriter.append(Integer.toString(isInUnit[focal]));
        csvWriter.append(",");

//        csvWriter.append("TIE_ENFORCED");
        csvWriter.append(",");

//        csvWriter.append("L");
        csvWriter.append(Integer.toString(ADMain.L));
        csvWriter.append("\n");
      }

      csvWriter.flush();
      csvWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  void shuffleFisherYates(int[] nArray) {
    for (int i = ADMain.N - 1; i > 0; i--) {
      int j = r.nextInt(i + 1);
      int temp = nArray[i];
      nArray[i] = nArray[j];
      nArray[j] = temp;
    }
  }

}
