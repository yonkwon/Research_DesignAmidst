package DATurbulenceTurnover;

import java.util.*;

public class NetworkAnalyzer {

  // Constant for centralization denominator
  static final double CLOSENESS_CENTRALIZATION_DENOMINATOR = (Main.N - 1D) * (Main.N - 2D) / (2 * Main.N - 3D);

  BitSet[] network2Analyze; // Changed to BitSet
  int[][] shortestPath;
  double[] betweennessCentrality;

  private List<Integer>[] adjList;

  double averagePathLength;
  double networkEfficiency;
  double globalClusteringWattsStrogatz;
  double globalClosenessCentralization;
  double shortestPathVariance;

  public double getAveragePathLength() {
    return averagePathLength;
  }

  public double getNetworkEfficiency() {
    return networkEfficiency;
  }

  public double getGlobalClusteringWattsStrogatz() {
    return globalClusteringWattsStrogatz;
  }

  public double getGlobalClosenessCentralization() {
    return globalClosenessCentralization;
  }

  public double getShortestPathVariance() {
    return shortestPathVariance;
  }

  public NetworkAnalyzer() {
  }

  public NetworkAnalyzer(BitSet[] network2Analyze) {
    this.network2Analyze = network2Analyze;
  }

  public void setNetwork2Analyze(BitSet[] network2Analyze) {
    this.network2Analyze = network2Analyze;
  }

  public void setNetworkMetrics() {
    setAdjList();
    setShortestPathAndBetweennessCentrality();
    double diameter = Double.MIN_VALUE;
    averagePathLength = 0;
    networkEfficiency = 0;
    globalClosenessCentralization = 0;
    shortestPathVariance = 0;

    double closenessCentralityMax = Double.MIN_VALUE;
    double[] closenessCentrality = new double[Main.N];
    double[] shortestPathSum = new double[Main.N];
    double[] shortestPathSquaredSum = new double[Main.N];

    for (int i = 0; i < Main.N; i++) {
      int degreeI = adjList[i].size();
      for (int j = i; j < Main.N; j++) {
        if (i != j) {
          double dist = shortestPath[i][j];
          if (dist > 0) { // Connected
            averagePathLength += dist;
            networkEfficiency += 1D / dist;
            closenessCentrality[i] += dist;
            closenessCentrality[j] += dist;
            shortestPathSum[i] += dist;
            shortestPathSquaredSum[i] += dist * dist;
            if (dist > diameter) {
              diameter = dist;
            }
          }
        }
      }
      closenessCentrality[i] = (Main.N - 1D) / closenessCentrality[i];
      globalClosenessCentralization -= closenessCentrality[i];
      if (closenessCentrality[i] > closenessCentralityMax) {
        closenessCentralityMax = closenessCentrality[i];
      }
      if (degreeI >= 2) {
        int localClusteringNumerator = 0;
        int localClusteringDenominator = degreeI * (degreeI - 1) / 2;
        for (int j : adjList[i]) {
          for (int k : adjList[i]) {
            if (j < k && network2Analyze[j].get(k)) {
              localClusteringNumerator++;
            }
          }
        }
        globalClusteringWattsStrogatz += (double) localClusteringNumerator / localClusteringDenominator;
      }
    }
    for (int i = 0; i < Main.N; i++) {
      double shortestPathMean = shortestPathSum[i] / Main.N;
      shortestPathVariance += shortestPathSquaredSum[i] / Main.N - shortestPathMean * shortestPathMean;
    }
    averagePathLength /= (double) Main.N_DYAD;
    networkEfficiency /= (double) Main.N_DYAD;
    globalClosenessCentralization += closenessCentralityMax * Main.N;
    globalClosenessCentralization /= CLOSENESS_CENTRALIZATION_DENOMINATOR;
    globalClusteringWattsStrogatz /= Main.N;
    shortestPathVariance /= Main.N;

    adjList = null;
  }

  private void setAdjList() {
    adjList = new ArrayList[Main.N];
    for (int i = 0; i < Main.N; i++) {
      adjList[i] = new ArrayList<>();
      for (int j = 0; j < Main.N; j++) {
        if (network2Analyze[i].get(j)) {
          adjList[i].add(j);
        }
      }
    }
  }

  private void setShortestPathAndBetweennessCentrality() {
    shortestPath = new int[Main.N][Main.N];
    betweennessCentrality = new double[Main.N];
    Arrays.fill(betweennessCentrality, 0.0);

    for (int s = 0; s < Main.N; s++) {
      Stack<Integer> stack = new Stack<>();
      List<List<Integer>> predecessors = new ArrayList<>();
      double[] sigma = new double[Main.N];
      double[] delta = new double[Main.N];
      int[] distance = new int[Main.N];
      Arrays.fill(distance, -1);
      Arrays.fill(delta, 0.0);
      Arrays.fill(sigma, 0.0);

      for (int i = 0; i < Main.N; i++) {
        predecessors.add(new ArrayList<>());
      }

      sigma[s] = 1.0;
      distance[s] = 0;

      Queue<Integer> queue = new ArrayDeque<>();
      queue.add(s);

      while (!queue.isEmpty()) {
        int v = queue.poll();
        stack.push(v);

        for (int w : adjList[v]) {
          if (distance[w] < 0) {
            distance[w] = distance[v] + 1;
            queue.add(w);
          }
          if (distance[w] == distance[v] + 1) {
            sigma[w] += sigma[v];
            predecessors.get(w).add(v);
          }
        }
      }

      while (!stack.isEmpty()) {
        int w = stack.pop();
        for (int v : predecessors.get(w)) {
          delta[v] += (sigma[v] / sigma[w]) * (1.0 + delta[w]);
        }
        if (w != s) {
          betweennessCentrality[w] += delta[w];
        }
      }

      for (int i = 0; i < Main.N; i++) {
        shortestPath[s][i] = distance[i];
        shortestPath[i][s] = distance[i];
      }
    }

    double scale = 2.0 / ((Main.N - 1) * (Main.N - 2));
    for (int i = 0; i < Main.N; i++) {
      betweennessCentrality[i] *= scale;
    }
  }
}
