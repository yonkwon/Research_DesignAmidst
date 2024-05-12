package DAFormBreak;

import org.apache.commons.math3.util.FastMath;

public class NetworkAnalyzer {

  // Pen-and-paper solution:
  // Assume a star graph with N individuals
  // The central individual's centrality is equal to N-1.
  // The other individuals' centrality is identical as 1 + 1/2 * (N-2) = .5N
  // Thus, the denominator is equal to (N-1){(N-1) - .5N} = (N-1)(.5N-1)
  static final double CLOSENESS_CENTRALIZATION_DENOMINATOR = (Main.N - 1D) * (.5 * Main.N - 1D);

  boolean[][] network2Analyze;
  int[][] shortestDistance;

  double density;
  double diameter;
  double averagePathLength;
  double networkEfficiency;
  double globalClustering;
  double globalClusteringWattsStrogatz;
  double globalClosenessCentralization;
  double betweennessCentralityVariance;

  double[] betweennessCentrality = new double[Main.N];
  int[][] numIsBetween = new int[Main.N][Main.N]; // Count of shortest paths passing through node
  int[][] paths = new int[Main.N][Main.N];
  public double getDensity() {
    return density;
  }

  public double getDiameter() {
    return diameter;
  }

  public double getAveragePathLength() {
    return averagePathLength;
  }

  public double getNetworkEfficiency() {
    return networkEfficiency;
  }

  public double getGlobalClustering() {
    return globalClustering;
  }

  public double getGlobalClusteringWattsStrogatz() {
    return globalClusteringWattsStrogatz;
  }

  public double getGlobalClosenessCentralization() {
    return globalClosenessCentralization;
  }

  public double getBetweennessCentralityVariance() {
    return betweennessCentralityVariance;
  }

  public NetworkAnalyzer() {

  }

  public NetworkAnalyzer(boolean[][] network2Analyze) {
    this.network2Analyze = network2Analyze;
  }

  public void setNetwork2Analyze(boolean[][] network2Analyze) {
    this.network2Analyze = network2Analyze;
  }

  public void setShortestDistance() {
    //Code imported from:
    //https://bard.google.com/chat/acb7fceb30ea4ef0
    shortestDistance = new int[Main.N][Main.N];
    numIsBetween = new int[Main.N][Main.N];
    for (int i = 0; i < Main.N; i++) {
      for (int j = 0; j < Main.N; j++) {
        if (network2Analyze[i][j]) {
          shortestDistance[i][j] = 1;
          numIsBetween[i][j] = 1; // Direct path is the shortest path
          numIsBetween[j][i] = 1; // Direct path is the shortest path
        } else {
          shortestDistance[i][j] = Main.N; // Impossible distance
          //Do not try to work with it 0 = INF.
        }
      }
      shortestDistance[i][i] = 0; // Do not delete this line
    }

    for (int k = 0; k < Main.N; k++) {
      for (int i = 0; i < Main.N; i++) {
        for (int j = 0; j < Main.N; j++) {
          if (shortestDistance[i][k] + shortestDistance[k][j] < shortestDistance[i][j]) {
            shortestDistance[i][j] = shortestDistance[i][k] + shortestDistance[k][j];
            numIsBetween[i][j] = numIsBetween[j][i] = numIsBetween[i][k] * numIsBetween[k][j];
          }else if(
              i != j &&
              k != i &&
              k != j &&
              shortestDistance[i][k] + shortestDistance[k][j] == shortestDistance[i][j]
          ){
            numIsBetween[i][j] = numIsBetween[j][i] = numIsBetween[i][k] * numIsBetween[k][j];
          }
        }
      }
    }

    for (int i = 0; i < Main.N; i++) {
      for (int j = 0; j < Main.N; j++) {
        if (shortestDistance[i][j] == Main.N) {
          shortestDistance[i][j] = 0;
        }
        if( i != j ){
          for (int k = 0; k < Main.N; k++) { // Node through which the path goes
            if (i != k && j != k && shortestDistance[i][k] + shortestDistance[k][j] == shortestDistance[i][j]) {
              betweennessCentrality[k] += (double)(numIsBetween[i][k] * numIsBetween[k][j]) / (double)numIsBetween[i][j];
            }
          }
        }
      }
    }
  }

  public void setNetworkMetrics() {
    setShortestDistance();
    density = 0;
    diameter = Double.MIN_VALUE;
    averagePathLength = 0;
    networkEfficiency = 0;
    globalClosenessCentralization = 0;
    betweennessCentralityVariance = 0;
    double[] closenessCentrality = new double[Main.N];
    double closenessCentralityMax = Double.MIN_VALUE;
    int globalClusteringNumerator = 0;
    int globalClusteringDenominator = 0;
    for (int i = 0; i < Main.N; i++) {
      for (int j = i; j < Main.N; j++) {
        if (i != j) {
          if( network2Analyze[i][j] ){
            density++;
          }
          double d = shortestDistance[i][j];
          if (d > diameter) {
            diameter = d;
          }
          averagePathLength += d;
          if (d > 0) { // Self or disconnected
            double inverseD = 1D / (double) d;
            networkEfficiency += inverseD;
            closenessCentrality[i] += inverseD;
            closenessCentrality[j] += inverseD;
          }
        }
      }
    }
    for (int i = 0; i < Main.N; i++) {
      globalClosenessCentralization -= closenessCentrality[i];
      double localClusteringNumerator = 0;
      double localClusteringDenominator = 0;
      if (closenessCentrality[i] > closenessCentralityMax) {
        closenessCentralityMax = closenessCentrality[i];
      }
      for (int j = i; j < Main.N; j++) {
        if( network2Analyze[i][j] ) {
          for (int k = j; k < Main.N; k++) {
            if (network2Analyze[i][k]) {
              localClusteringDenominator++;
              globalClusteringDenominator++;
              if (network2Analyze[j][k]) {
                localClusteringNumerator++;
                globalClusteringNumerator++;
              }
            }
          }
        }
      }
      globalClusteringWattsStrogatz += localClusteringNumerator / localClusteringDenominator;
    }

    double sumBetweennessCentrality = 0;
    double sumBetweennessCentralitySq = 0;
    for (int i = 0; i < Main.N; i++) {
      sumBetweennessCentrality += betweennessCentrality[i];
      sumBetweennessCentralitySq += FastMath.pow(betweennessCentrality[i],2);
    }

    density /= (double) Main.N_DYAD;
    averagePathLength /= (double) Main.N_DYAD;
    networkEfficiency /= (double) Main.N_DYAD;
    globalClosenessCentralization += closenessCentralityMax * (double) Main.N;
    globalClosenessCentralization /= CLOSENESS_CENTRALIZATION_DENOMINATOR;
    globalClustering = (double) globalClusteringNumerator / (double) globalClusteringDenominator;
    globalClusteringWattsStrogatz /= (double) Main.N;
    betweennessCentralityVariance = sumBetweennessCentralitySq/(double)Main.N - FastMath.pow(sumBetweennessCentrality/(double)Main.N ,2);
  }

}
