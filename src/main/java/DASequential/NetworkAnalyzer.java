package DASequential;

import java.util.*;

public class NetworkAnalyzer {

  // Pen-and-paper solution:
  // Assume a star graph with N individuals
  // The central individual's centrality is equal to N-1.
  // The other individuals' centrality is identical as 1 + 1/2 * (N-2) = .5N
  // Thus, the denominator is equal to (N-1){(N-1) - .5N} = (N-1)(.5N-1)
  static final double CLOSENESS_CENTRALIZATION_DENOMINATOR = (Main.N - 1D) * (Main.N - 2D) / (2 * Main.N - 3D);

  boolean[][] network2Analyze;
  int[][] shortestDistance;
  double[] betweennessCentrality;

  private List<Integer>[] adjList;  // Adjacency List for efficiency

  double density;
  double diameter;
  double averagePathLength;
  double networkEfficiency;
  double globalClustering;
  double globalClusteringWattsStrogatz;
  double globalClosenessCentralization;
  double shortestPathVariance;
  double betweennessCentralityVariance;

  double[] shortestPath = new double[Main.N];
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

  public double getshortestPathVariance() {
    return shortestPathVariance;
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

  public void setNetworkMetrics() {
    setAdjList();
    setShortestDistanceAndBetweennessCentrality();
    density = 0;
    diameter = Double.MIN_VALUE;
    averagePathLength = 0;
    networkEfficiency = 0;
    globalClosenessCentralization = 0;
    shortestPathVariance = 0;
    double closenessCentralityMax = Double.MIN_VALUE;
    int globalClusteringNumerator = 0;
    int globalClusteringDenominator = 0;
    double betweennessCentralitySum = 0;
    double betweennessCentralitySquaredSum = 0;
    double[] closenessCentrality = new double[Main.N];
    double[] shortestPathSum = new double[Main.N];
    double[] shortestPathSquaredSum = new double[Main.N];
    for (int i = 0; i < Main.N; i++) {
      int localClusteringNumerator = 0;
      int degreeI = adjList[i].size();
      density += degreeI;
      betweennessCentralitySum += betweennessCentrality[i];
      betweennessCentralitySquaredSum += betweennessCentrality[i] * betweennessCentrality[i];
      for (int j = i; j < Main.N; j++) {
        if (i != j) {
          double dist = shortestDistance[i][j];
          if (dist > 0) { // Connected
            averagePathLength += dist;
            networkEfficiency += 1D / (double) dist;
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
        int localClusteringDenominator = degreeI * (degreeI - 1) / 2;
        globalClusteringDenominator += localClusteringDenominator;
        for (int j : adjList[i]) {
          for (int k : adjList[i]) {
            if (j < k && network2Analyze[j][k]) {
              localClusteringNumerator++;
            }
          }
        }
        globalClusteringWattsStrogatz += localClusteringNumerator / (double) localClusteringDenominator;
        globalClusteringNumerator += localClusteringNumerator;
      }
    }
    for (int i = 0; i < Main.N; i++) {
      double shortestPathMean = shortestPathSum[i] / (double) Main.N;
      shortestPathVariance += shortestPathSquaredSum[i] / (double) Main.N - shortestPathMean * shortestPathMean;
    }
    density /= (double) Main.N_DYAD;
    averagePathLength /= (double) Main.N_DYAD;
    networkEfficiency /= (double) Main.N_DYAD;
    globalClosenessCentralization += closenessCentralityMax * (double) Main.N;
    globalClosenessCentralization /= CLOSENESS_CENTRALIZATION_DENOMINATOR;
    globalClustering = (globalClusteringDenominator == 0) ? 0 : (double) globalClusteringNumerator / (double) globalClusteringDenominator;
    globalClusteringWattsStrogatz /= (double) Main.N;
    shortestPathVariance /= (double) Main.N;
    double betweennessCentralityMean = betweennessCentralitySum / (double) Main.N;
    betweennessCentralityVariance = betweennessCentralitySquaredSum / (double) Main.N - betweennessCentralityMean * betweennessCentralityMean;

    adjList = null;
  }

  private void setAdjList() {
    adjList = new ArrayList[Main.N];
    // Convert adjacency matrix to adjacency list for faster neighbor lookup
    for (int i = 0; i < Main.N; i++) {
      adjList[i] = new ArrayList<>();
      for (int j = 0; j < Main.N; j++) {
        if (network2Analyze[i][j]) {
          adjList[i].add(j);
        }
      }
    }
  }

  private void setShortestDistanceAndBetweennessCentrality() {
    //Brandes algorithm
    shortestDistance = new int[Main.N][Main.N];
    betweennessCentrality = new double[Main.N];
    Arrays.fill(betweennessCentrality, 0.0);

    // Initialize the distance matrix to -1 (unreachable)
    for (int i = 0; i < Main.N; i++) {
      Arrays.fill(shortestDistance[i], -1);
    }

    // Only calculate distances for each node once
    for (int s = 0; s < Main.N; s++) {
      // Step 1: Initialization
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

      sigma[s] = 1.0; // Number of shortest paths starting from `s`
      distance[s] = 0;

      // Step 2: BFS to compute shortest paths
      Queue<Integer> queue = new ArrayDeque<>();
      queue.add(s);

      while (!queue.isEmpty()) {
        int v = queue.poll();
        stack.push(v);

        for (int w : adjList[v]) {
          // Path discovery
          if (distance[w] < 0) { // i.e., not visited yet
            distance[w] = distance[v] + 1;
            queue.add(w);
          }
          // Path counting
          if (distance[w] == distance[v] + 1) {
            sigma[w] += sigma[v];
            predecessors.get(w).add(v);
          }
        }
      }

      // Step 3: Back-propagation of dependencies
      while (!stack.isEmpty()) {
        int w = stack.pop();
        for (int v : predecessors.get(w)) {
          delta[v] += (sigma[v] / sigma[w]) * (1.0 + delta[w]);
        }
        if (w != s) {
          betweennessCentrality[w] += delta[w];
        }
      }

      // Update the shortestDistance matrix
      for (int i = 0; i < Main.N; i++) {
        shortestDistance[s][i] = distance[i];
        shortestDistance[i][s] = distance[i]; // Symmetry for undirected graph
      }
    }

    // Normalize betweenness centralities for undirected graphs
    double scale = 2.0 / ((Main.N - 1) * (Main.N - 2));
    for (int i = 0; i < Main.N; i++) {
      betweennessCentrality[i] *= scale;
    }
  }

  private void setShortestDistance() {
    shortestDistance = new int[Main.N][Main.N];
    // Initialize the distance matrix to -1 (unreachable)
    for (int i = 0; i < Main.N; i++) {
      Arrays.fill(shortestDistance[i], -1);
    }
    // Only calculate distances for each node once
    for (int i = 0; i < Main.N; i++) {
      bfs2(i);
    }
  }

  // Optimized BFS method to calculate shortest path from a given node
  private void bfs2(int start) {
    int N = adjList.length;
    boolean[] visited = new boolean[N];

    // Initialize BFS distances
    Queue<Integer> queue = new ArrayDeque<>();
    queue.add(start);
    visited[start] = true;
    shortestDistance[start][start] = 0;

    // Perform BFS
    while (!queue.isEmpty()) {
      int current = queue.poll();
      int currentDistance = shortestDistance[start][current];

      // Explore neighbors from adjacency list
      for (int neighbor : adjList[current]) {
        if (!visited[neighbor]) {
          visited[neighbor] = true;
          shortestDistance[start][neighbor] = currentDistance + 1;
          shortestDistance[neighbor][start] = currentDistance + 1;  // Fill both i, j and j, i
          queue.add(neighbor);
        }
      }
    }
  }

}