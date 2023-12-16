package DARewiring;

public class NetworkAnalyzer {

  // Pen-and-paper solution:
  // Assume a star graph with N individuals
  // The central individual's centrality is equal to N-1.
  // The other individuals' centrality is identical as 1 + 1/2 * (N-2) = .5N
  // Thus, the denominator is equal to (N-1){(N-1) - .5N} = (N-1)(.5N-1)
  static final double CLOSENESS_CENTRALIZATION_DENOMINATOR = (Main.N - 1D) * (.5 * Main.N - 1D);

  boolean[][] network2Analyze;
  int[][] shortestDistance;

  double diameter;
  double averagePathLength;
  double networkEfficiency;
  double overallClustering;
  double overallClosenessCentralization;

  public double getDiameter() {
    return diameter;
  }

  public double getAveragePathLength() {
    return averagePathLength;
  }

  public double getNetworkEfficiency() {
    return networkEfficiency;
  }

  public double getOverallClustering() {
    return overallClustering;
  }

  public double getOverallClosenessCentralization() {
    return overallClosenessCentralization;
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
    for (int i = 0; i < Main.N; i++) {
      for (int j = 0; j < Main.N; j++) {
        if (network2Analyze[i][j]) {
          shortestDistance[i][j] = 1;
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
          }
        }
      }
    }

    for (int i = 0; i < Main.N; i++) {
      for (int j = 0; j < Main.N; j++) {
        if (shortestDistance[i][j] == Main.N) {
          shortestDistance[i][j] = 0;
        }
      }
    }
  }

  public void setNetworkMetrics() {
    setShortestDistance();
    diameter = Double.MIN_VALUE;
    averagePathLength = 0;
    networkEfficiency = 0;
    overallClosenessCentralization = 0;
    double[] closenessCentrality = new double[Main.N];
    double closenessCentralityMax = Double.MIN_VALUE;
    int overallClusteringNumerator = 0;
    int overallClusteringDenominator = 0;
    for (int i = 0; i < Main.N; i++) {
      for (int j = i; j < Main.N; j++) {
        if (i != j) {
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
    averagePathLength /= (double) Main.N_DYAD;
    networkEfficiency /= (double) Main.N_DYAD;
    for (int i = 0; i < Main.N; i++) {
      overallClosenessCentralization -= closenessCentrality[i];
      if (closenessCentrality[i] > closenessCentralityMax) {
        closenessCentralityMax = closenessCentrality[i];
      }
      for (int j = i; j < Main.N; j++) {
        for (int k = j; k < Main.N; k++) {
          if (network2Analyze[i][j] && network2Analyze[i][k]) {
            overallClusteringDenominator++;
            if (network2Analyze[j][k]) {
              overallClusteringNumerator++;
            }
          }
        }
      }
    }

    overallClosenessCentralization += closenessCentralityMax * (double) Main.N;
    overallClosenessCentralization /= CLOSENESS_CENTRALIZATION_DENOMINATOR;
    overallClustering = (double) overallClusteringNumerator / (double) overallClusteringDenominator;
  }

}
