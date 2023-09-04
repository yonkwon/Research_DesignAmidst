package HPClosure;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;

public class Scenario {

  RandomGenerator r;

  boolean isRewiring;
  boolean isRandomRewiring;

  int[] focalIndexArray;
  int[] targetIndexArray;

  double closure;     //H
  double beta;          //Beta
  double enforcement;   //E

  boolean[] reality;
  int[] realityBundleID;
  boolean[][] beliefOf;

  int[] performance;

  boolean[][] networkEnforced;
  boolean[][] networkFlexible;
  boolean[][] network;
  int[] isInUnit;
  int[] degreeEnforced;
  int[] degreeFlexible;
  int[] degree;

  int numRewiring;
  double performanceAvg;
  double disagreementAvg;
  double closureCoefficient;
  double clusteringCoefficient;

  Scenario(double c, double beta, double enforcement) {
    r = new MersenneTwister();
    focalIndexArray = new int[Main.N];
    for (int n = 0; n < Main.N; n++) {
      focalIndexArray[n] = n;
    }
    targetIndexArray = focalIndexArray.clone();

    this.closure = c;
    this.beta = beta;
    this.enforcement = enforcement;

    isRewiring = true;
    isRandomRewiring = false;

    initialize();
  }

  public Scenario getClone() {
    Scenario clone = new Scenario(this.closure, this.beta, this.enforcement);

    clone.reality = this.reality.clone(); //230902 Fix
    clone.realityBundleID = this.realityBundleID.clone(); //230902 Fix
    clone.beliefOf = new boolean[Main.N][];
    clone.performance = this.performance.clone();

    clone.networkEnforced = new boolean[Main.N][];
    clone.networkFlexible = new boolean[Main.N][];
    clone.network = new boolean[Main.N][];

    clone.degreeEnforced = this.degreeEnforced.clone();
    clone.degreeFlexible = this.degreeFlexible.clone();
    clone.degree = this.degree.clone();
    clone.numRewiring = this.numRewiring;
    clone.performanceAvg = this.performanceAvg;
    clone.disagreementAvg = this.disagreementAvg;

    for (int focal = 0; focal < Main.N; focal++) {
      clone.beliefOf[focal] = this.beliefOf[focal].clone();

      clone.networkEnforced[focal] = this.networkEnforced[focal].clone();
      clone.networkFlexible[focal] = this.networkFlexible[focal].clone();
      clone.network[focal] = this.network[focal].clone();
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

    //All-channel within each unit
    for (int unitID = 0; unitID < Main.N_OF_UNIT; unitID++) {
      for (int focal = 0; focal < Main.N_IN_UNIT; focal++) {
        int focalNow = unitID * Main.N_IN_UNIT + focal;
        isInUnit[focalNow] = unitID;
        for (int target = focal; target < Main.N_IN_UNIT; target++) {
          if (focal == target) {
            continue;
          }
          int targetNow = unitID * Main.N_IN_UNIT + target;
          network[focalNow][targetNow] = true;
          network[targetNow][focalNow] = true;
          degree[focalNow]++;
          degree[targetNow]++;
        }
      }
    }

    //Limited spanning between unit
    //This does not change the difference the degree
    for (int unitID = 0; unitID < Main.N_OF_UNIT; unitID++) {
      int toBeCut = unitID * Main.N_IN_UNIT; // First one in each unit
      int toRewire = toBeCut + 1; // Second one in each unit
      int toBeRewired = (toBeCut + Main.N_IN_UNIT) % Main.N; //First one in the next unit
      network[toRewire][toBeCut] = false;
      network[toBeCut][toRewire] = false;
      network[toRewire][toBeRewired] = true;
      network[toBeRewired][toRewire] = true;
      degree[toBeCut]--;
      degree[toBeRewired]++;
    }

    // Rewiring (Fang Lee Schilling 2010: B.2.1.)
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      int focalUnitID = isInUnit[focal];
      int inUnitLast = (focalUnitID + 1) * Main.N_IN_UNIT;
      for (int targetInUnit = focalUnitID * Main.N_IN_UNIT; targetInUnit < inUnitLast; targetInUnit++) {
        if (network[focal][targetInUnit] && degree[targetInUnit] > 1 && r.nextDouble() < beta) {
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
  }

  private void initializeEntity() {
    reality = new boolean[Main.M];
    realityBundleID = new int[Main.M];
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
      for (int m = 0; m < Main.M; m++) {
        beliefOf[focal][m] = r.nextBoolean();
      }
    }
  }

  private void initializeOutcome() {
    performance = new int[Main.N];
    setPerformance();
    setOutcome();
  }

  void stepForward() {
    //Step 1. Rewiring ... among dissatisfied individuals
    if (isRewiring) {
      doRewiring();
    }
    //Step 2. Learning
    doLearning();
  }

  void stepForward(int numRewiring) {
    //Step 1. Rewiring ... among dissatisfied individuals
    if (isRewiring) {
      doRewiring(numRewiring);
    }
    //Step 2. Learning
    doLearning();
  }

  void setOutcome() {
    performanceAvg = 0;
    disagreementAvg = 0;
    clusteringCoefficient = 0;
    for (int focal = 0; focal < Main.N; focal++) {
      performanceAvg += performance[focal];
      for (int target = focal; target < Main.N; target++) {
        if (network[focal][target]) {
          for (int m = 0; m < Main.M; m++) {
            if (beliefOf[focal][m] != beliefOf[target][m]) {
              disagreementAvg++;
            }
          }
        }
      }
    }

    // Global Clustering Coefficient
    // https://en.wikipedia.org/wiki/Clustering_coefficient#Global_clustering_coefficient
    for( int ind1 : focalIndexArray ){
      for( int ind2 : targetIndexArray ){
        if( ind1 == ind2 ||
            !network[ind1][ind2]
        ){
          continue;
        }
        for( int ind3 = 0; ind3 < Main.N; ind3 ++ ){
          if( ind1 == ind3 ||
              ind2 == ind3 ||
              !network[ind1][ind3] ||
              !network[ind2][ind3]
          ){
            continue;
          }
          //ind 1, 2, 3 are different and form a closed triplet
          clusteringCoefficient ++;
        }
      }
    }

    clusteringCoefficient /= Main.NUM_TRIPLET;
    performanceAvg /= Main.M_N;
    disagreementAvg /= Main.M_N_DYAD;
  }

  void doRewiring() {
    numRewiring = 0;
    boolean[] hasNewTie = new boolean[Main.N];
    shuffleFisherYates(focalIndexArray);
    for (int focal : focalIndexArray) {
      //C as rewiring probability
      if (hasNewTie[focal] || degreeFlexible[focal] == 0 || r.nextDouble() < closure) {
        continue;
      }
      //Selection of a target to cut out among informal others
      int target2Cut = -1;
      int target2CutNumSharedContact = Integer.MAX_VALUE;
      shuffleFisherYates(targetIndexArray);
      for (int target : targetIndexArray) {
        if (degree[target] == 1) {
          continue;
        }
        if (networkFlexible[focal][target]) {
          int numSharedContact = getNumSharedContactBetween(focal, target);
          if (numSharedContact < target2CutNumSharedContact) {
            target2CutNumSharedContact = numSharedContact;
            target2Cut = target;
          }
        }
      }
      //Selection of a target to connect to among strangers
      shuffleFisherYates(targetIndexArray);
      for (int target : targetIndexArray) {
        if (hasNewTie[target] || // If the target already has a new tie
            network[focal][target] || // If the target is already a neighbor of the focal
            focal == target // If the target is focal
        ) {
          continue;
        }
        if( target2CutNumSharedContact < getNumSharedContactBetween(focal, target)){
          numRewiring++;
          hasNewTie[focal] = true;
          hasNewTie[target] = true;
          //Breaking the tie with the farthest informal neighbor
          networkFlexible[focal][target2Cut] = false;
          networkFlexible[target2Cut][focal] = false;
          network[focal][target2Cut] = false;
          network[target2Cut][focal] = false;
          degreeFlexible[target2Cut]--;
          degree[target2Cut]--;
          //Building a new tie with the target
          networkFlexible[focal][target] = true;
          networkFlexible[target][focal] = true;
          network[focal][target] = true;
          network[target][focal] = true;
          degreeFlexible[target]++;
          degree[target]++;
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
        numRewiring++;
        hasNewTie[focal] = true;
        hasNewTie[target2See] = true;
        //Breaking the tie with the farthest informal neighbor
        network[focal][target2Cut] = false;
        network[target2Cut][focal] = false;
        networkFlexible[focal][target2Cut] = false;
        networkFlexible[target2Cut][focal] = false;
        degreeFlexible[target2Cut]--;
        degree[target2Cut]--;
        //Building a new tie with the target
        network[focal][target2See] = true;
        network[target2See][focal] = true;
        networkFlexible[focal][target2See] = true;
        networkFlexible[target2See][focal] = true;
        degreeFlexible[target2See]++;
        degree[target2See]++;
        numRewiringLeft--;
        //Occurs at most once for each individual
        break;
      }
    }
  }

  int getNumSharedContactBetween(int focal, int target) {
    int numSharedContact = 0;
    for (int contact : focalIndexArray) {
      if (focal == contact || target == contact) {
        continue;
      }
      if (network[focal][contact] && network[target][contact]) {
        numSharedContact++;
      }
    }
    return numSharedContact;
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
    setPerformance();
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
      csvWriter.append("SOURCE_UNIT");
      csvWriter.append(",");
      csvWriter.append("TIE_ENFORCED");
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

//            csvWriter.append("SOURCE_UNIT");
            csvWriter.append(Integer.toString(isInUnit[focal]));
            csvWriter.append(",");

//            csvWriter.append("TIE_ENFORCED");
            csvWriter.append(Boolean.toString(networkEnforced[focal][target]));
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

//        csvWriter.append("SOURCE_UNIT");
        csvWriter.append(Integer.toString(isInUnit[focal]));
        csvWriter.append(",");

//        csvWriter.append("TIE_ENFORCED");
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
