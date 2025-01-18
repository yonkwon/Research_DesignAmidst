package DASequential;

import static org.apache.commons.math3.util.FastMath.log;
import static org.apache.commons.math3.util.FastMath.pow;

import com.google.common.util.concurrent.AtomicDouble;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Computation {

  ExecutorService workStealingPool;

  AtomicDouble[][][][] performanceAVGAtomic;
  AtomicDouble[][][][] performanceSTDAtomic;
  AtomicDouble[][][][] performance12AVGAtomic;
  AtomicDouble[][][][] performance12STDAtomic;
  AtomicDouble[][][][] performance23AVGAtomic;
  AtomicDouble[][][][] performance23STDAtomic;
  AtomicDouble[][][][] performance13AVGAtomic;
  AtomicDouble[][][][] performance13STDAtomic;
  AtomicDouble[][][][] performanceNRAVGAtomic;
  AtomicDouble[][][][] performanceNRSTDAtomic;
  AtomicDouble[][][][] performanceRRAVGAtomic;
  AtomicDouble[][][][] performanceRRSTDAtomic;

  AtomicDouble[][][][] disagreementAVGAtomic;
  AtomicDouble[][][][] disagreementSTDAtomic;
  AtomicDouble[][][][] disagreement12AVGAtomic;
  AtomicDouble[][][][] disagreement12STDAtomic;
  AtomicDouble[][][][] disagreement23AVGAtomic;
  AtomicDouble[][][][] disagreement23STDAtomic;
  AtomicDouble[][][][] disagreement13AVGAtomic;
  AtomicDouble[][][][] disagreement13STDAtomic;
  AtomicDouble[][][][] disagreementNRAVGAtomic;
  AtomicDouble[][][][] disagreementNRSTDAtomic;
  AtomicDouble[][][][] disagreementRRAVGAtomic;
  AtomicDouble[][][][] disagreementRRSTDAtomic;

  AtomicDouble[][][][] clusteringAVGAtomic;
  AtomicDouble[][][][] clusteringSTDAtomic;
  AtomicDouble[][][][] clustering12AVGAtomic;
  AtomicDouble[][][][] clustering12STDAtomic;
  AtomicDouble[][][][] clustering23AVGAtomic;
  AtomicDouble[][][][] clustering23STDAtomic;
  AtomicDouble[][][][] clustering13AVGAtomic;
  AtomicDouble[][][][] clustering13STDAtomic;
  AtomicDouble[][][][] clusteringNRAVGAtomic;
  AtomicDouble[][][][] clusteringNRSTDAtomic;
  AtomicDouble[][][][] clusteringRRAVGAtomic;
  AtomicDouble[][][][] clusteringRRSTDAtomic;

  AtomicDouble[][][][] clusteringWattsStrogatzAVGAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatzSTDAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatz12AVGAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatz12STDAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatz23AVGAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatz23STDAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatz13AVGAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatz13STDAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatzNRAVGAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatzNRSTDAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatzRRAVGAtomic;
  AtomicDouble[][][][] clusteringWattsStrogatzRRSTDAtomic;

  AtomicDouble[][][][] centralizationAVGAtomic;
  AtomicDouble[][][][] centralizationSTDAtomic;
  AtomicDouble[][][][] centralization12AVGAtomic;
  AtomicDouble[][][][] centralization12STDAtomic;
  AtomicDouble[][][][] centralization23AVGAtomic;
  AtomicDouble[][][][] centralization23STDAtomic;
  AtomicDouble[][][][] centralization13AVGAtomic;
  AtomicDouble[][][][] centralization13STDAtomic;
  AtomicDouble[][][][] centralizationNRAVGAtomic;
  AtomicDouble[][][][] centralizationNRSTDAtomic;
  AtomicDouble[][][][] centralizationRRAVGAtomic;
  AtomicDouble[][][][] centralizationRRSTDAtomic;

  AtomicDouble[][][][] efficiencyAVGAtomic;
  AtomicDouble[][][][] efficiencySTDAtomic;
  AtomicDouble[][][][] efficiency12AVGAtomic;
  AtomicDouble[][][][] efficiency12STDAtomic;
  AtomicDouble[][][][] efficiency23AVGAtomic;
  AtomicDouble[][][][] efficiency23STDAtomic;
  AtomicDouble[][][][] efficiency13AVGAtomic;
  AtomicDouble[][][][] efficiency13STDAtomic;
  AtomicDouble[][][][] efficiencyNRAVGAtomic;
  AtomicDouble[][][][] efficiencyNRSTDAtomic;
  AtomicDouble[][][][] efficiencyRRAVGAtomic;
  AtomicDouble[][][][] efficiencyRRSTDAtomic;

  AtomicDouble[][][][] distanceAVGAtomic;
  AtomicDouble[][][][] distanceSTDAtomic;
  AtomicDouble[][][][] distance12AVGAtomic;
  AtomicDouble[][][][] distance12STDAtomic;
  AtomicDouble[][][][] distance23AVGAtomic;
  AtomicDouble[][][][] distance23STDAtomic;
  AtomicDouble[][][][] distance13AVGAtomic;
  AtomicDouble[][][][] distance13STDAtomic;
  AtomicDouble[][][][] distanceNRAVGAtomic;
  AtomicDouble[][][][] distanceNRSTDAtomic;
  AtomicDouble[][][][] distanceRRAVGAtomic;
  AtomicDouble[][][][] distanceRRSTDAtomic;

  AtomicDouble[][][][] densityAVGAtomic;
  AtomicDouble[][][][] densitySTDAtomic;
  AtomicDouble[][][][] density12AVGAtomic;
  AtomicDouble[][][][] density12STDAtomic;
  AtomicDouble[][][][] density23AVGAtomic;
  AtomicDouble[][][][] density23STDAtomic;
  AtomicDouble[][][][] density13AVGAtomic;
  AtomicDouble[][][][] density13STDAtomic;
  AtomicDouble[][][][] densityNRAVGAtomic;
  AtomicDouble[][][][] densityNRSTDAtomic;
  AtomicDouble[][][][] densityRRAVGAtomic;
  AtomicDouble[][][][] densityRRSTDAtomic;

  AtomicDouble[][][][] sigmaAVGAtomic;
  AtomicDouble[][][][] sigmaSTDAtomic;
  AtomicDouble[][][][] sigma12AVGAtomic;
  AtomicDouble[][][][] sigma12STDAtomic;
  AtomicDouble[][][][] sigma23AVGAtomic;
  AtomicDouble[][][][] sigma23STDAtomic;
  AtomicDouble[][][][] sigma13AVGAtomic;
  AtomicDouble[][][][] sigma13STDAtomic;
  AtomicDouble[][][][] sigmaNRAVGAtomic;
  AtomicDouble[][][][] sigmaNRSTDAtomic;
  AtomicDouble[][][][] sigmaRRAVGAtomic;
  AtomicDouble[][][][] sigmaRRSTDAtomic;

  AtomicDouble[][][][] omegaAVGAtomic;
  AtomicDouble[][][][] omegaSTDAtomic;
  AtomicDouble[][][][] omega12AVGAtomic;
  AtomicDouble[][][][] omega12STDAtomic;
  AtomicDouble[][][][] omega23AVGAtomic;
  AtomicDouble[][][][] omega23STDAtomic;
  AtomicDouble[][][][] omega13AVGAtomic;
  AtomicDouble[][][][] omega13STDAtomic;
  AtomicDouble[][][][] omegaNRAVGAtomic;
  AtomicDouble[][][][] omegaNRSTDAtomic;
  AtomicDouble[][][][] omegaRRAVGAtomic;
  AtomicDouble[][][][] omegaRRSTDAtomic;

  AtomicDouble[][][][] shortestPathVarianceAVGAtomic;
  AtomicDouble[][][][] shortestPathVarianceSTDAtomic;
  AtomicDouble[][][][] shortestPathVariance12AVGAtomic;
  AtomicDouble[][][][] shortestPathVariance12STDAtomic;
  AtomicDouble[][][][] shortestPathVariance23AVGAtomic;
  AtomicDouble[][][][] shortestPathVariance23STDAtomic;
  AtomicDouble[][][][] shortestPathVariance13AVGAtomic;
  AtomicDouble[][][][] shortestPathVariance13STDAtomic;
  AtomicDouble[][][][] shortestPathVarianceNRAVGAtomic;
  AtomicDouble[][][][] shortestPathVarianceNRSTDAtomic;
  AtomicDouble[][][][] shortestPathVarianceRRAVGAtomic;
  AtomicDouble[][][][] shortestPathVarianceRRSTDAtomic;
  
  AtomicDouble[][][][] betweennessCentralityVarianceAVGAtomic;
  AtomicDouble[][][][] betweennessCentralityVarianceSTDAtomic;
  AtomicDouble[][][][] betweennessCentralityVariance12AVGAtomic;
  AtomicDouble[][][][] betweennessCentralityVariance12STDAtomic;
  AtomicDouble[][][][] betweennessCentralityVariance23AVGAtomic;
  AtomicDouble[][][][] betweennessCentralityVariance23STDAtomic;
  AtomicDouble[][][][] betweennessCentralityVariance13AVGAtomic;
  AtomicDouble[][][][] betweennessCentralityVariance13STDAtomic;
  AtomicDouble[][][][] betweennessCentralityVarianceNRAVGAtomic;
  AtomicDouble[][][][] betweennessCentralityVarianceNRSTDAtomic;
  AtomicDouble[][][][] betweennessCentralityVarianceRRAVGAtomic;
  AtomicDouble[][][][] betweennessCentralityVarianceRRSTDAtomic;

  // Results in double arrays
  double[][][][] performanceAVG;
  double[][][][] performanceSTD;
  double[][][][] performance12AVG;
  double[][][][] performance12STD;
  double[][][][] performance23AVG;
  double[][][][] performance23STD;
  double[][][][] performance13AVG;
  double[][][][] performance13STD;
  double[][][][] performanceNRAVG;
  double[][][][] performanceNRSTD;
  double[][][][] performanceRRAVG;
  double[][][][] performanceRRSTD;

  double[][][][] disagreementAVG;
  double[][][][] disagreementSTD;
  double[][][][] disagreement12AVG;
  double[][][][] disagreement12STD;
  double[][][][] disagreement23AVG;
  double[][][][] disagreement23STD;
  double[][][][] disagreement13AVG;
  double[][][][] disagreement13STD;
  double[][][][] disagreementNRAVG;
  double[][][][] disagreementNRSTD;
  double[][][][] disagreementRRAVG;
  double[][][][] disagreementRRSTD;

  double[][][][] clusteringAVG;
  double[][][][] clusteringSTD;
  double[][][][] clustering12AVG;
  double[][][][] clustering12STD;
  double[][][][] clustering23AVG;
  double[][][][] clustering23STD;
  double[][][][] clustering13AVG;
  double[][][][] clustering13STD;
  double[][][][] clusteringNRAVG;
  double[][][][] clusteringNRSTD;
  double[][][][] clusteringRRAVG;
  double[][][][] clusteringRRSTD;

  double[][][][] clusteringWattsStrogatzAVG;
  double[][][][] clusteringWattsStrogatzSTD;
  double[][][][] clusteringWattsStrogatz12AVG;
  double[][][][] clusteringWattsStrogatz12STD;
  double[][][][] clusteringWattsStrogatz23AVG;
  double[][][][] clusteringWattsStrogatz23STD;
  double[][][][] clusteringWattsStrogatz13AVG;
  double[][][][] clusteringWattsStrogatz13STD;
  double[][][][] clusteringWattsStrogatzNRAVG;
  double[][][][] clusteringWattsStrogatzNRSTD;
  double[][][][] clusteringWattsStrogatzRRAVG;
  double[][][][] clusteringWattsStrogatzRRSTD;

  double[][][][] centralizationAVG;
  double[][][][] centralizationSTD;
  double[][][][] centralization12AVG;
  double[][][][] centralization12STD;
  double[][][][] centralization23AVG;
  double[][][][] centralization23STD;
  double[][][][] centralization13AVG;
  double[][][][] centralization13STD;
  double[][][][] centralizationNRAVG;
  double[][][][] centralizationNRSTD;
  double[][][][] centralizationRRAVG;
  double[][][][] centralizationRRSTD;

  double[][][][] efficiencyAVG;
  double[][][][] efficiencySTD;
  double[][][][] efficiency12AVG;
  double[][][][] efficiency12STD;
  double[][][][] efficiency23AVG;
  double[][][][] efficiency23STD;
  double[][][][] efficiency13AVG;
  double[][][][] efficiency13STD;
  double[][][][] efficiencyNRAVG;
  double[][][][] efficiencyNRSTD;
  double[][][][] efficiencyRRAVG;
  double[][][][] efficiencyRRSTD;

  double[][][][] distanceAVG;
  double[][][][] distanceSTD;
  double[][][][] distance12AVG;
  double[][][][] distance12STD;
  double[][][][] distance23AVG;
  double[][][][] distance23STD;
  double[][][][] distance13AVG;
  double[][][][] distance13STD;
  double[][][][] distanceNRAVG;
  double[][][][] distanceNRSTD;
  double[][][][] distanceRRAVG;
  double[][][][] distanceRRSTD;

  double[][][][] densityAVG;
  double[][][][] densitySTD;
  double[][][][] density12AVG;
  double[][][][] density12STD;
  double[][][][] density23AVG;
  double[][][][] density23STD;
  double[][][][] density13AVG;
  double[][][][] density13STD;
  double[][][][] densityNRAVG;
  double[][][][] densityNRSTD;
  double[][][][] densityRRAVG;
  double[][][][] densityRRSTD;

  double[][][][] sigmaAVG;
  double[][][][] sigmaSTD;
  double[][][][] sigma12AVG;
  double[][][][] sigma12STD;
  double[][][][] sigma23AVG;
  double[][][][] sigma23STD;
  double[][][][] sigma13AVG;
  double[][][][] sigma13STD;
  double[][][][] sigmaNRAVG;
  double[][][][] sigmaNRSTD;
  double[][][][] sigmaRRAVG;
  double[][][][] sigmaRRSTD;

  double[][][][] omegaAVG;
  double[][][][] omegaSTD;
  double[][][][] omega12AVG;
  double[][][][] omega12STD;
  double[][][][] omega23AVG;
  double[][][][] omega23STD;
  double[][][][] omega13AVG;
  double[][][][] omega13STD;
  double[][][][] omegaNRAVG;
  double[][][][] omegaNRSTD;
  double[][][][] omegaRRAVG;
  double[][][][] omegaRRSTD;

  double[][][][] shortestPathVarianceAVG;
  double[][][][] shortestPathVarianceSTD;
  double[][][][] shortestPathVariance12AVG;
  double[][][][] shortestPathVariance12STD;
  double[][][][] shortestPathVariance23AVG;
  double[][][][] shortestPathVariance23STD;
  double[][][][] shortestPathVariance13AVG;
  double[][][][] shortestPathVariance13STD;
  double[][][][] shortestPathVarianceNRAVG;
  double[][][][] shortestPathVarianceNRSTD;
  double[][][][] shortestPathVarianceRRAVG;
  double[][][][] shortestPathVarianceRRSTD;
  
  double[][][][] betweennessCentralityVarianceAVG;
  double[][][][] betweennessCentralityVarianceSTD;
  double[][][][] betweennessCentralityVariance12AVG;
  double[][][][] betweennessCentralityVariance12STD;
  double[][][][] betweennessCentralityVariance23AVG;
  double[][][][] betweennessCentralityVariance23STD;
  double[][][][] betweennessCentralityVariance13AVG;
  double[][][][] betweennessCentralityVariance13STD;
  double[][][][] betweennessCentralityVarianceNRAVG;
  double[][][][] betweennessCentralityVarianceNRSTD;
  double[][][][] betweennessCentralityVarianceRRAVG;
  double[][][][] betweennessCentralityVarianceRRSTD;

  ProgressBar pb;

  double averagePathLengthRandom, averagePathLengthLattice;
  double clusteringCoefficientRandom, clusteringCoefficientLattice;
  double averagePathLengthRandomNR, clusteringCoefficientRandomNR;
  double swiDenominator;

  Computation() {
    workStealingPool = Executors.newWorkStealingPool();
    setBaselineNetworkMetric();
  }

  private void setBaselineNetworkMetric() {
    double nInteraction = (double) (Main.INFORMAL_INITIAL_NUM + Main.N - 1);
    double p = nInteraction / (double) Main.N_DYAD;
    double pNR = (Main.N - 1D) / (double) Main.N_DYAD;
    double EulerConstant = 0.57721566490153286060651209008240243;
    boolean[][] lattice = new boolean[Main.N][Main.N];
    NetworkAnalyzer na;
    int nNeighborFloor = (int) (nInteraction / Main.N);
    int remainingNeighbor = (int) nInteraction - (nNeighborFloor * Main.N);
    int remainingNeighborInterval = (int) ((double) Main.N / (double) remainingNeighbor);
    //https://math.stackexchange.com/questions/501216/what-is-the-equation-for-the-average-path-length-in-a-random-graph
    //https://chih-ling-hsu.github.io/2020/05/15/Gnp#:~:text=Clustering%20coefficient%20of%20a%20random,with%20the%20graph%20size%20n.
    averagePathLengthRandom = log(Main.N) / log(p * (Main.N - 1D));
    averagePathLengthRandomNR = log(Main.N) / log(pNR * (Main.N - 1D));
    //https://math.stackexchange.com/questions/2641947/expected-global-clustering-coefficient-for-erd%C5%91s-r%C3%A9nyi-graph
    clusteringCoefficientRandom = p;
    clusteringCoefficientRandomNR = pNR;
    for (int i = 0; i < nNeighborFloor; i++) {
      for (int n = 0; n < Main.N; n++) {
        int target = (n + i + 1) % Main.N;
        lattice[n][target] = true;
        lattice[target][n] = true;
      }
    }
    if (remainingNeighborInterval > 0) {
      for (int n = 0; n < Main.N; n += remainingNeighborInterval) {
        int target = (n + nNeighborFloor + 1) % Main.N;
        lattice[n][target] = true;
        lattice[target][n] = true;
      }
    }
    na = new NetworkAnalyzer(lattice);
    na.setNetworkMetrics();
    averagePathLengthLattice = na.getAveragePathLength();
    clusteringCoefficientLattice = na.getGlobalClusteringWattsStrogatz();
    swiDenominator = (averagePathLengthRandom - averagePathLengthLattice) * (clusteringCoefficientLattice - clusteringCoefficientRandom);
//    System.out.println("Check: setBaselineNetworkMetric()");
//    System.out.println("nInteraction: " + nInteraction + "\tp: " + p+"\t"+na.density);
//    System.out.println("avgpathlength" + "\trandom" + averagePathLengthRandom + "\tlattice" + averagePathLengthLattice);
//    System.out.println("clusteringcof" + "\trandom" + clusteringCoefficientRandom + "\tlattice" + clusteringCoefficientLattice);
  }

  public void printNetwork() {
    try {
      Files.createDirectories(Paths.get(Main.PATH_CSV));
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
          int span = Main.SPAN[s];
          double enforcement = Main.ENFORCEMENT[e];
          String mcString = null;
          switch (mc) {
            case 0 -> mcString = "netClos";
            case 1 -> mcString = "prefAtt";
          }
          String fileName = Main.RUN_ID + "_s" + span + "_" + enforcement + "_" + mcString;
          Scenario src = new Scenario(mc, span, enforcement);
          Scenario rr = src.getClone(true, true);
          Scenario nr = src.getClone(false, false);
          src.doRewiring(Main.INFORMAL_INITIAL_NUM, 0);
          rr.doRewiring(Main.INFORMAL_INITIAL_NUM, 0);
          src.printCSV(Main.PATH_CSV + "sc_" + fileName + "_t0");
          rr.printCSV(Main.PATH_CSV + "rr_" + fileName + "_t0");
          nr.printCSV(Main.PATH_CSV + "nr_" + fileName + "_t0");
          if (Main.DO_POST_REWIRING) {
            for (int t = 0; t < Main.TIME; t++) {
              src.stepForward(Main.INFORMAL_TURNOVER_NUM);
              rr.stepForward(Main.INFORMAL_TURNOVER_NUM);
            }
            src.printCSV(Main.PATH_CSV + "sc_" + fileName + "_t" + Main.TIME);
            rr.printCSV(Main.PATH_CSV + "rr_" + fileName + "_t" + Main.TIME);
            nr.printCSV(Main.PATH_CSV + "nr_" + fileName + "_t" + Main.TIME);
          }
          System.out.println("Network Printed: " + fileName);
        }
      }
    }
  }

  public void doExperiment() {
    pb = new ProgressBar(Main.RUN_ID + ": Computation", Main.ITERATION);
    setResultSpace();
    runFullExperiment();
    averageFullExperiment();
  }

  private void setResultSpace() {
    performanceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    disagreementAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    clusteringAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    clusteringWattsStrogatzAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    centralizationAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    efficiencyAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencySTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    distanceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    densityAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densitySTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    sigmaAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    omegaAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    shortestPathVarianceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    betweennessCentralityVarianceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
          for (int t = 0; t < Main.TIME; t++) {
            performanceAVGAtomic[mc][s][e][t] = new AtomicDouble();
            performanceSTDAtomic[mc][s][e][t] = new AtomicDouble();
            performance12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            performance12STDAtomic[mc][s][e][t] = new AtomicDouble();
            performance23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            performance23STDAtomic[mc][s][e][t] = new AtomicDouble();
            performance13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            performance13STDAtomic[mc][s][e][t] = new AtomicDouble();
            performanceNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            performanceNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            performanceRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            performanceRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            disagreementAVGAtomic[mc][s][e][t] = new AtomicDouble();
            disagreementSTDAtomic[mc][s][e][t] = new AtomicDouble();
            disagreement12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            disagreement12STDAtomic[mc][s][e][t] = new AtomicDouble();
            disagreement23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            disagreement23STDAtomic[mc][s][e][t] = new AtomicDouble();
            disagreement13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            disagreement13STDAtomic[mc][s][e][t] = new AtomicDouble();
            disagreementNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            disagreementNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            disagreementRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            disagreementRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            clusteringAVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringSTDAtomic[mc][s][e][t] = new AtomicDouble();
            clustering12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            clustering12STDAtomic[mc][s][e][t] = new AtomicDouble();
            clustering23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            clustering23STDAtomic[mc][s][e][t] = new AtomicDouble();
            clustering13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            clustering13STDAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            clusteringWattsStrogatzAVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatzSTDAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatz12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatz12STDAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatz23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatz23STDAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatz13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatz13STDAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatzNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatzNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatzRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            clusteringWattsStrogatzRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            centralizationAVGAtomic[mc][s][e][t] = new AtomicDouble();
            centralizationSTDAtomic[mc][s][e][t] = new AtomicDouble();
            centralization12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            centralization12STDAtomic[mc][s][e][t] = new AtomicDouble();
            centralization23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            centralization23STDAtomic[mc][s][e][t] = new AtomicDouble();
            centralization13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            centralization13STDAtomic[mc][s][e][t] = new AtomicDouble();
            centralizationNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            centralizationNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            centralizationRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            centralizationRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            efficiencyAVGAtomic[mc][s][e][t] = new AtomicDouble();
            efficiencySTDAtomic[mc][s][e][t] = new AtomicDouble();
            efficiency12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            efficiency12STDAtomic[mc][s][e][t] = new AtomicDouble();
            efficiency23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            efficiency23STDAtomic[mc][s][e][t] = new AtomicDouble();
            efficiency13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            efficiency13STDAtomic[mc][s][e][t] = new AtomicDouble();
            efficiencyNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            efficiencyNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            efficiencyRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            efficiencyRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            distanceAVGAtomic[mc][s][e][t] = new AtomicDouble();
            distanceSTDAtomic[mc][s][e][t] = new AtomicDouble();
            distance12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            distance12STDAtomic[mc][s][e][t] = new AtomicDouble();
            distance23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            distance23STDAtomic[mc][s][e][t] = new AtomicDouble();
            distance13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            distance13STDAtomic[mc][s][e][t] = new AtomicDouble();
            distanceNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            distanceNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            distanceRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            distanceRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            densityAVGAtomic[mc][s][e][t] = new AtomicDouble();
            densitySTDAtomic[mc][s][e][t] = new AtomicDouble();
            density12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            density12STDAtomic[mc][s][e][t] = new AtomicDouble();
            density23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            density23STDAtomic[mc][s][e][t] = new AtomicDouble();
            density13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            density13STDAtomic[mc][s][e][t] = new AtomicDouble();
            densityNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            densityNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            densityRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            densityRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            sigmaAVGAtomic[mc][s][e][t] = new AtomicDouble();
            sigmaSTDAtomic[mc][s][e][t] = new AtomicDouble();
            sigma12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            sigma12STDAtomic[mc][s][e][t] = new AtomicDouble();
            sigma23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            sigma23STDAtomic[mc][s][e][t] = new AtomicDouble();
            sigma13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            sigma13STDAtomic[mc][s][e][t] = new AtomicDouble();
            sigmaNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            sigmaNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            sigmaRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            sigmaRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            omegaAVGAtomic[mc][s][e][t] = new AtomicDouble();
            omegaSTDAtomic[mc][s][e][t] = new AtomicDouble();
            omega12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            omega12STDAtomic[mc][s][e][t] = new AtomicDouble();
            omega23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            omega23STDAtomic[mc][s][e][t] = new AtomicDouble();
            omega13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            omega13STDAtomic[mc][s][e][t] = new AtomicDouble();
            omegaNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            omegaNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            omegaRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            omegaRRSTDAtomic[mc][s][e][t] = new AtomicDouble();

            shortestPathVarianceAVGAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVarianceSTDAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVariance12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVariance12STDAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVariance23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVariance23STDAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVariance13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVariance13STDAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVarianceNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVarianceNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVarianceRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            shortestPathVarianceRRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            
            betweennessCentralityVarianceAVGAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVarianceSTDAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVariance12AVGAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVariance12STDAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVariance23AVGAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVariance23STDAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVariance13AVGAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVariance13STDAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVarianceNRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVarianceNRSTDAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVarianceRRAVGAtomic[mc][s][e][t] = new AtomicDouble();
            betweennessCentralityVarianceRRSTDAtomic[mc][s][e][t] = new AtomicDouble();
          }
        }
      }
    }
    performanceAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    disagreementAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    clusteringAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    clusteringWattsStrogatzAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    centralizationAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    efficiencyAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencySTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    distanceAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    densityAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densitySTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    sigmaAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigma13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    sigmaRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    omegaAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omega13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    omegaRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    shortestPathVarianceAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVariance13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    shortestPathVarianceRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];

    betweennessCentralityVarianceAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.TIME];
  }

  private void runFullExperiment() {
    for (int iteration = 0; iteration < Main.ITERATION; iteration++) {
      experimentWrapper experimentWrap = new experimentWrapper();
      workStealingPool.execute(experimentWrap);
    }
    workStealingPool.shutdown();
    try {
      workStealingPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
  }

  class experimentWrapper implements Runnable {

    experimentWrapper() {
    }

    @Override
    public void run() {
      for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
        for (int s = 0; s < Main.LENGTH_SPAN; s++) {
          for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
            new SingleRun(mc, s, e);
          }
        }
      }
      pb.stepNext();
    }
  }

  private void averageFullExperiment() {
    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
          for (int t = 0; t < Main.TIME; t++) {
            performanceAVG[mc][s][e][t] = performanceAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            performanceSTD[mc][s][e][t] = performanceSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            performanceSTD[mc][s][e][t] = pow(
                performanceSTD[mc][s][e][t] - pow(performanceAVG[mc][s][e][t], 2), .5);

            performance12AVG[mc][s][e][t] =
                performance12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            performance12STD[mc][s][e][t] =
                performance12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            performance12STD[mc][s][e][t] = pow(
                performance12STD[mc][s][e][t] - pow(performance12AVG[mc][s][e][t], 2), .5);

            performance23AVG[mc][s][e][t] =
                performance23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            performance23STD[mc][s][e][t] =
                performance23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            performance23STD[mc][s][e][t] = pow(
                performance23STD[mc][s][e][t] - pow(performance23AVG[mc][s][e][t], 2), .5);

            performance13AVG[mc][s][e][t] =
                performance13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            performance13STD[mc][s][e][t] =
                performance13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            performance13STD[mc][s][e][t] = pow(
                performance13STD[mc][s][e][t] - pow(performance13AVG[mc][s][e][t], 2), .5);

            performanceNRAVG[mc][s][e][t] =
                performanceNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            performanceNRSTD[mc][s][e][t] =
                performanceNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            performanceNRSTD[mc][s][e][t] = pow(
                performanceNRSTD[mc][s][e][t] - pow(performanceNRAVG[mc][s][e][t], 2), .5);

            performanceRRAVG[mc][s][e][t] =
                performanceRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            performanceRRSTD[mc][s][e][t] =
                performanceRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            performanceRRSTD[mc][s][e][t] = pow(
                performanceRRSTD[mc][s][e][t] - pow(performanceRRAVG[mc][s][e][t], 2), .5);

            disagreementAVG[mc][s][e][t] =
                disagreementAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreementSTD[mc][s][e][t] =
                disagreementSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreementSTD[mc][s][e][t] = pow(
                disagreementSTD[mc][s][e][t] - pow(disagreementAVG[mc][s][e][t], 2), .5);

            disagreement12AVG[mc][s][e][t] =
                disagreement12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreement12STD[mc][s][e][t] =
                disagreement12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreement12STD[mc][s][e][t] = pow(
                disagreement12STD[mc][s][e][t] - pow(disagreement12AVG[mc][s][e][t], 2), .5);

            disagreement23AVG[mc][s][e][t] =
                disagreement23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreement23STD[mc][s][e][t] =
                disagreement23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreement23STD[mc][s][e][t] = pow(
                disagreement23STD[mc][s][e][t] - pow(disagreement23AVG[mc][s][e][t], 2), .5);

            disagreement13AVG[mc][s][e][t] =
                disagreement13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreement13STD[mc][s][e][t] =
                disagreement13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreement13STD[mc][s][e][t] = pow(
                disagreement13STD[mc][s][e][t] - pow(disagreement13AVG[mc][s][e][t], 2), .5);

            disagreementNRAVG[mc][s][e][t] =
                disagreementNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreementNRSTD[mc][s][e][t] =
                disagreementNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreementNRSTD[mc][s][e][t] = pow(
                disagreementNRSTD[mc][s][e][t] - pow(disagreementNRAVG[mc][s][e][t], 2), .5);

            disagreementRRAVG[mc][s][e][t] =
                disagreementRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreementRRSTD[mc][s][e][t] =
                disagreementRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            disagreementRRSTD[mc][s][e][t] = pow(
                disagreementRRSTD[mc][s][e][t] - pow(disagreementRRAVG[mc][s][e][t], 2), .5);

            clusteringAVG[mc][s][e][t] = clusteringAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringSTD[mc][s][e][t] = clusteringSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringSTD[mc][s][e][t] = pow(
                clusteringSTD[mc][s][e][t] - pow(clusteringAVG[mc][s][e][t], 2), .5);

            clustering12AVG[mc][s][e][t] =
                clustering12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clustering12STD[mc][s][e][t] =
                clustering12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clustering12STD[mc][s][e][t] = pow(
                clustering12STD[mc][s][e][t] - pow(clustering12AVG[mc][s][e][t], 2), .5);

            clustering23AVG[mc][s][e][t] =
                clustering23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clustering23STD[mc][s][e][t] =
                clustering23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clustering23STD[mc][s][e][t] = pow(
                clustering23STD[mc][s][e][t] - pow(clustering23AVG[mc][s][e][t], 2), .5);

            clustering13AVG[mc][s][e][t] =
                clustering13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clustering13STD[mc][s][e][t] =
                clustering13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clustering13STD[mc][s][e][t] = pow(
                clustering13STD[mc][s][e][t] - pow(clustering13AVG[mc][s][e][t], 2), .5);

            clusteringNRAVG[mc][s][e][t] =
                clusteringNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringNRSTD[mc][s][e][t] =
                clusteringNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringNRSTD[mc][s][e][t] = pow(
                clusteringNRSTD[mc][s][e][t] - pow(clusteringNRAVG[mc][s][e][t], 2), .5);

            clusteringRRAVG[mc][s][e][t] =
                clusteringRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringRRSTD[mc][s][e][t] =
                clusteringRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringRRSTD[mc][s][e][t] = pow(
                clusteringRRSTD[mc][s][e][t] - pow(clusteringRRAVG[mc][s][e][t], 2), .5);

            clusteringWattsStrogatzAVG[mc][s][e][t] =
                clusteringWattsStrogatzAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatzSTD[mc][s][e][t] =
                clusteringWattsStrogatzSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatzSTD[mc][s][e][t] = pow(
                clusteringWattsStrogatzSTD[mc][s][e][t] - pow(
                    clusteringWattsStrogatzAVG[mc][s][e][t], 2), .5);

            clusteringWattsStrogatz12AVG[mc][s][e][t] =
                clusteringWattsStrogatz12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatz12STD[mc][s][e][t] =
                clusteringWattsStrogatz12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatz12STD[mc][s][e][t] = pow(
                clusteringWattsStrogatz12STD[mc][s][e][t] - pow(
                    clusteringWattsStrogatz12AVG[mc][s][e][t], 2), .5);

            clusteringWattsStrogatz23AVG[mc][s][e][t] =
                clusteringWattsStrogatz23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatz23STD[mc][s][e][t] =
                clusteringWattsStrogatz23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatz23STD[mc][s][e][t] = pow(
                clusteringWattsStrogatz23STD[mc][s][e][t] - pow(
                    clusteringWattsStrogatz23AVG[mc][s][e][t], 2), .5);

            clusteringWattsStrogatz13AVG[mc][s][e][t] =
                clusteringWattsStrogatz13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatz13STD[mc][s][e][t] =
                clusteringWattsStrogatz13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatz13STD[mc][s][e][t] = pow(
                clusteringWattsStrogatz13STD[mc][s][e][t] - pow(
                    clusteringWattsStrogatz13AVG[mc][s][e][t], 2), .5);

            clusteringWattsStrogatzNRAVG[mc][s][e][t] =
                clusteringWattsStrogatzNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatzNRSTD[mc][s][e][t] =
                clusteringWattsStrogatzNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatzNRSTD[mc][s][e][t] = pow(
                clusteringWattsStrogatzNRSTD[mc][s][e][t] - pow(
                    clusteringWattsStrogatzNRAVG[mc][s][e][t], 2), .5);

            clusteringWattsStrogatzRRAVG[mc][s][e][t] =
                clusteringWattsStrogatzRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatzRRSTD[mc][s][e][t] =
                clusteringWattsStrogatzRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            clusteringWattsStrogatzRRSTD[mc][s][e][t] = pow(
                clusteringWattsStrogatzRRSTD[mc][s][e][t] - pow(
                    clusteringWattsStrogatzRRAVG[mc][s][e][t], 2), .5);

            centralizationAVG[mc][s][e][t] =
                centralizationAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralizationSTD[mc][s][e][t] =
                centralizationSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralizationSTD[mc][s][e][t] = pow(
                centralizationSTD[mc][s][e][t] - pow(centralizationAVG[mc][s][e][t], 2), .5);

            centralization12AVG[mc][s][e][t] =
                centralization12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralization12STD[mc][s][e][t] =
                centralization12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralization12STD[mc][s][e][t] = pow(
                centralization12STD[mc][s][e][t] - pow(centralization12AVG[mc][s][e][t], 2), .5);

            centralization23AVG[mc][s][e][t] =
                centralization23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralization23STD[mc][s][e][t] =
                centralization23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralization23STD[mc][s][e][t] = pow(
                centralization23STD[mc][s][e][t] - pow(centralization23AVG[mc][s][e][t], 2), .5);

            centralization13AVG[mc][s][e][t] =
                centralization13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralization13STD[mc][s][e][t] =
                centralization13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralization13STD[mc][s][e][t] = pow(
                centralization13STD[mc][s][e][t] - pow(centralization13AVG[mc][s][e][t], 2), .5);

            centralizationNRAVG[mc][s][e][t] =
                centralizationNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralizationNRSTD[mc][s][e][t] =
                centralizationNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralizationNRSTD[mc][s][e][t] = pow(
                centralizationNRSTD[mc][s][e][t] - pow(centralizationNRAVG[mc][s][e][t], 2), .5);

            centralizationRRAVG[mc][s][e][t] =
                centralizationRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralizationRRSTD[mc][s][e][t] =
                centralizationRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            centralizationRRSTD[mc][s][e][t] = pow(
                centralizationRRSTD[mc][s][e][t] - pow(centralizationRRAVG[mc][s][e][t], 2), .5);

            efficiencyAVG[mc][s][e][t] = efficiencyAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiencySTD[mc][s][e][t] = efficiencySTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiencySTD[mc][s][e][t] = pow(
                efficiencySTD[mc][s][e][t] - pow(efficiencyAVG[mc][s][e][t], 2), .5);

            efficiency12AVG[mc][s][e][t] =
                efficiency12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiency12STD[mc][s][e][t] =
                efficiency12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiency12STD[mc][s][e][t] = pow(
                efficiency12STD[mc][s][e][t] - pow(efficiency12AVG[mc][s][e][t], 2), .5);

            efficiency23AVG[mc][s][e][t] =
                efficiency23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiency23STD[mc][s][e][t] =
                efficiency23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiency23STD[mc][s][e][t] = pow(
                efficiency23STD[mc][s][e][t] - pow(efficiency23AVG[mc][s][e][t], 2), .5);

            efficiency13AVG[mc][s][e][t] =
                efficiency13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiency13STD[mc][s][e][t] =
                efficiency13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiency13STD[mc][s][e][t] = pow(
                efficiency13STD[mc][s][e][t] - pow(efficiency13AVG[mc][s][e][t], 2), .5);

            efficiencyNRAVG[mc][s][e][t] =
                efficiencyNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiencyNRSTD[mc][s][e][t] =
                efficiencyNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiencyNRSTD[mc][s][e][t] = pow(
                efficiencyNRSTD[mc][s][e][t] - pow(efficiencyNRAVG[mc][s][e][t], 2), .5);

            efficiencyRRAVG[mc][s][e][t] =
                efficiencyRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiencyRRSTD[mc][s][e][t] =
                efficiencyRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            efficiencyRRSTD[mc][s][e][t] = pow(
                efficiencyRRSTD[mc][s][e][t] - pow(efficiencyRRAVG[mc][s][e][t], 2), .5);

            distanceAVG[mc][s][e][t] = distanceAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            distanceSTD[mc][s][e][t] = distanceSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            distanceSTD[mc][s][e][t] = pow(
                distanceSTD[mc][s][e][t] - pow(distanceAVG[mc][s][e][t], 2), .5);

            distance12AVG[mc][s][e][t] = distance12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            distance12STD[mc][s][e][t] = distance12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            distance12STD[mc][s][e][t] = pow(
                distance12STD[mc][s][e][t] - pow(distance12AVG[mc][s][e][t], 2), .5);

            distance23AVG[mc][s][e][t] = distance23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            distance23STD[mc][s][e][t] = distance23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            distance23STD[mc][s][e][t] = pow(
                distance23STD[mc][s][e][t] - pow(distance23AVG[mc][s][e][t], 2), .5);

            distance13AVG[mc][s][e][t] = distance13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            distance13STD[mc][s][e][t] = distance13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            distance13STD[mc][s][e][t] = pow(
                distance13STD[mc][s][e][t] - pow(distance13AVG[mc][s][e][t], 2), .5);

            distanceNRAVG[mc][s][e][t] = distanceNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            distanceNRSTD[mc][s][e][t] = distanceNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            distanceNRSTD[mc][s][e][t] = pow(
                distanceNRSTD[mc][s][e][t] - pow(distanceNRAVG[mc][s][e][t], 2), .5);

            distanceRRAVG[mc][s][e][t] = distanceRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            distanceRRSTD[mc][s][e][t] = distanceRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            distanceRRSTD[mc][s][e][t] = pow(
                distanceRRSTD[mc][s][e][t] - pow(distanceRRAVG[mc][s][e][t], 2), .5);

            densityAVG[mc][s][e][t] = densityAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            densitySTD[mc][s][e][t] = densitySTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            densitySTD[mc][s][e][t] = pow(densitySTD[mc][s][e][t] - pow(densityAVG[mc][s][e][t], 2),
                .5);

            density12AVG[mc][s][e][t] = density12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            density12STD[mc][s][e][t] = density12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            density12STD[mc][s][e][t] = pow(
                density12STD[mc][s][e][t] - pow(density12AVG[mc][s][e][t], 2), .5);

            density23AVG[mc][s][e][t] = density23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            density23STD[mc][s][e][t] = density23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            density23STD[mc][s][e][t] = pow(
                density23STD[mc][s][e][t] - pow(density23AVG[mc][s][e][t], 2), .5);

            density13AVG[mc][s][e][t] = density13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            density13STD[mc][s][e][t] = density13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            density13STD[mc][s][e][t] = pow(
                density13STD[mc][s][e][t] - pow(density13AVG[mc][s][e][t], 2), .5);

            densityNRAVG[mc][s][e][t] = densityNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            densityNRSTD[mc][s][e][t] = densityNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            densityNRSTD[mc][s][e][t] = pow(
                densityNRSTD[mc][s][e][t] - pow(densityNRAVG[mc][s][e][t], 2), .5);

            densityRRAVG[mc][s][e][t] = densityRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            densityRRSTD[mc][s][e][t] = densityRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            densityRRSTD[mc][s][e][t] = pow(
                densityRRSTD[mc][s][e][t] - pow(densityRRAVG[mc][s][e][t], 2), .5);

            sigmaAVG[mc][s][e][t] = sigmaAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigmaSTD[mc][s][e][t] = sigmaSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigmaSTD[mc][s][e][t] = pow(sigmaSTD[mc][s][e][t] - pow(sigmaAVG[mc][s][e][t], 2),
                .5);

            sigma12AVG[mc][s][e][t] = sigma12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigma12STD[mc][s][e][t] = sigma12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigma12STD[mc][s][e][t] = pow(
                sigma12STD[mc][s][e][t] - pow(sigma12AVG[mc][s][e][t], 2), .5);

            sigma23AVG[mc][s][e][t] = sigma23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigma23STD[mc][s][e][t] = sigma23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigma23STD[mc][s][e][t] = pow(
                sigma23STD[mc][s][e][t] - pow(sigma23AVG[mc][s][e][t], 2), .5);

            sigma13AVG[mc][s][e][t] = sigma13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigma13STD[mc][s][e][t] = sigma13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigma13STD[mc][s][e][t] = pow(
                sigma13STD[mc][s][e][t] - pow(sigma13AVG[mc][s][e][t], 2), .5);

            sigmaNRAVG[mc][s][e][t] = sigmaNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigmaNRSTD[mc][s][e][t] = sigmaNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigmaNRSTD[mc][s][e][t] = pow(
                sigmaNRSTD[mc][s][e][t] - pow(sigmaNRAVG[mc][s][e][t], 2), .5);

            sigmaRRAVG[mc][s][e][t] = sigmaRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigmaRRSTD[mc][s][e][t] = sigmaRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            sigmaRRSTD[mc][s][e][t] = pow(
                sigmaRRSTD[mc][s][e][t] - pow(sigmaRRAVG[mc][s][e][t], 2), .5);

            omegaAVG[mc][s][e][t] = omegaAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            omegaSTD[mc][s][e][t] = omegaSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            omegaSTD[mc][s][e][t] = pow(omegaSTD[mc][s][e][t] - pow(omegaAVG[mc][s][e][t], 2),
                .5);

            omega12AVG[mc][s][e][t] = omega12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            omega12STD[mc][s][e][t] = omega12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            omega12STD[mc][s][e][t] = pow(
                omega12STD[mc][s][e][t] - pow(omega12AVG[mc][s][e][t], 2), .5);

            omega23AVG[mc][s][e][t] = omega23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            omega23STD[mc][s][e][t] = omega23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            omega23STD[mc][s][e][t] = pow(
                omega23STD[mc][s][e][t] - pow(omega23AVG[mc][s][e][t], 2), .5);

            omega13AVG[mc][s][e][t] = omega13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            omega13STD[mc][s][e][t] = omega13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            omega13STD[mc][s][e][t] = pow(
                omega13STD[mc][s][e][t] - pow(omega13AVG[mc][s][e][t], 2), .5);

            omegaNRAVG[mc][s][e][t] = omegaNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            omegaNRSTD[mc][s][e][t] = omegaNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            omegaNRSTD[mc][s][e][t] = pow(
                omegaNRSTD[mc][s][e][t] - pow(omegaNRAVG[mc][s][e][t], 2), .5);

            omegaRRAVG[mc][s][e][t] = omegaRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            omegaRRSTD[mc][s][e][t] = omegaRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            omegaRRSTD[mc][s][e][t] = pow(
                omegaRRSTD[mc][s][e][t] - pow(omegaRRAVG[mc][s][e][t], 2), .5);

            shortestPathVarianceAVG[mc][s][e][t] =
                shortestPathVarianceAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVarianceSTD[mc][s][e][t] =
                shortestPathVarianceSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVarianceSTD[mc][s][e][t] = pow(
                shortestPathVarianceSTD[mc][s][e][t] - pow(
                    shortestPathVarianceAVG[mc][s][e][t], 2), .5);

            shortestPathVariance12AVG[mc][s][e][t] =
                shortestPathVariance12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVariance12STD[mc][s][e][t] =
                shortestPathVariance12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVariance12STD[mc][s][e][t] = pow(
                shortestPathVariance12STD[mc][s][e][t] - pow(
                    shortestPathVariance12AVG[mc][s][e][t], 2), .5);

            shortestPathVariance23AVG[mc][s][e][t] =
                shortestPathVariance23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVariance23STD[mc][s][e][t] =
                shortestPathVariance23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVariance23STD[mc][s][e][t] = pow(
                shortestPathVariance23STD[mc][s][e][t] - pow(
                    shortestPathVariance23AVG[mc][s][e][t], 2), .5);

            shortestPathVariance13AVG[mc][s][e][t] =
                shortestPathVariance13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVariance13STD[mc][s][e][t] =
                shortestPathVariance13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVariance13STD[mc][s][e][t] = pow(
                shortestPathVariance13STD[mc][s][e][t] - pow(
                    shortestPathVariance13AVG[mc][s][e][t], 2), .5);

            shortestPathVarianceNRAVG[mc][s][e][t] =
                shortestPathVarianceNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVarianceNRSTD[mc][s][e][t] =
                shortestPathVarianceNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVarianceNRSTD[mc][s][e][t] = pow(
                shortestPathVarianceNRSTD[mc][s][e][t] - pow(
                    shortestPathVarianceNRAVG[mc][s][e][t], 2), .5);

            shortestPathVarianceRRAVG[mc][s][e][t] =
                shortestPathVarianceRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVarianceRRSTD[mc][s][e][t] =
                shortestPathVarianceRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            shortestPathVarianceRRSTD[mc][s][e][t] = pow(
                shortestPathVarianceRRSTD[mc][s][e][t] - pow(
                    shortestPathVarianceRRAVG[mc][s][e][t], 2), .5);
            
            betweennessCentralityVarianceAVG[mc][s][e][t] =
                betweennessCentralityVarianceAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVarianceSTD[mc][s][e][t] =
                betweennessCentralityVarianceSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVarianceSTD[mc][s][e][t] = pow(
                betweennessCentralityVarianceSTD[mc][s][e][t] - pow(
                    betweennessCentralityVarianceAVG[mc][s][e][t], 2), .5);

            betweennessCentralityVariance12AVG[mc][s][e][t] =
                betweennessCentralityVariance12AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVariance12STD[mc][s][e][t] =
                betweennessCentralityVariance12STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVariance12STD[mc][s][e][t] = pow(
                betweennessCentralityVariance12STD[mc][s][e][t] - pow(
                    betweennessCentralityVariance12AVG[mc][s][e][t], 2), .5);

            betweennessCentralityVariance23AVG[mc][s][e][t] =
                betweennessCentralityVariance23AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVariance23STD[mc][s][e][t] =
                betweennessCentralityVariance23STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVariance23STD[mc][s][e][t] = pow(
                betweennessCentralityVariance23STD[mc][s][e][t] - pow(
                    betweennessCentralityVariance23AVG[mc][s][e][t], 2), .5);

            betweennessCentralityVariance13AVG[mc][s][e][t] =
                betweennessCentralityVariance13AVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVariance13STD[mc][s][e][t] =
                betweennessCentralityVariance13STDAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVariance13STD[mc][s][e][t] = pow(
                betweennessCentralityVariance13STD[mc][s][e][t] - pow(
                    betweennessCentralityVariance13AVG[mc][s][e][t], 2), .5);

            betweennessCentralityVarianceNRAVG[mc][s][e][t] =
                betweennessCentralityVarianceNRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVarianceNRSTD[mc][s][e][t] =
                betweennessCentralityVarianceNRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVarianceNRSTD[mc][s][e][t] = pow(
                betweennessCentralityVarianceNRSTD[mc][s][e][t] - pow(
                    betweennessCentralityVarianceNRAVG[mc][s][e][t], 2), .5);

            betweennessCentralityVarianceRRAVG[mc][s][e][t] =
                betweennessCentralityVarianceRRAVGAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVarianceRRSTD[mc][s][e][t] =
                betweennessCentralityVarianceRRSTDAtomic[mc][s][e][t].get() / Main.ITERATION;
            betweennessCentralityVarianceRRSTD[mc][s][e][t] = pow(
                betweennessCentralityVarianceRRSTD[mc][s][e][t] - pow(
                    betweennessCentralityVarianceRRAVG[mc][s][e][t], 2), .5);
          }
        }
      }
    }
  }

  class SingleRun {

    int mcIndex;
    int spanIndex;
    int enforcementIndex;

    int span;
    double enforcement;

    AtomicDouble[] performanceAVGAtomicPart;
    AtomicDouble[] performanceSTDAtomicPart;
    AtomicDouble[] performance12AVGAtomicPart;
    AtomicDouble[] performance12STDAtomicPart;
    AtomicDouble[] performance23AVGAtomicPart;
    AtomicDouble[] performance23STDAtomicPart;
    AtomicDouble[] performance13AVGAtomicPart;
    AtomicDouble[] performance13STDAtomicPart;
    AtomicDouble[] performanceNRAVGAtomicPart;
    AtomicDouble[] performanceNRSTDAtomicPart;
    AtomicDouble[] performanceRRAVGAtomicPart;
    AtomicDouble[] performanceRRSTDAtomicPart;

    AtomicDouble[] disagreementAVGAtomicPart;
    AtomicDouble[] disagreementSTDAtomicPart;
    AtomicDouble[] disagreement12AVGAtomicPart;
    AtomicDouble[] disagreement12STDAtomicPart;
    AtomicDouble[] disagreement23AVGAtomicPart;
    AtomicDouble[] disagreement23STDAtomicPart;
    AtomicDouble[] disagreement13AVGAtomicPart;
    AtomicDouble[] disagreement13STDAtomicPart;
    AtomicDouble[] disagreementNRAVGAtomicPart;
    AtomicDouble[] disagreementNRSTDAtomicPart;
    AtomicDouble[] disagreementRRAVGAtomicPart;
    AtomicDouble[] disagreementRRSTDAtomicPart;

    AtomicDouble[] clusteringAVGAtomicPart;
    AtomicDouble[] clusteringSTDAtomicPart;
    AtomicDouble[] clustering12AVGAtomicPart;
    AtomicDouble[] clustering12STDAtomicPart;
    AtomicDouble[] clustering23AVGAtomicPart;
    AtomicDouble[] clustering23STDAtomicPart;
    AtomicDouble[] clustering13AVGAtomicPart;
    AtomicDouble[] clustering13STDAtomicPart;
    AtomicDouble[] clusteringNRAVGAtomicPart;
    AtomicDouble[] clusteringNRSTDAtomicPart;
    AtomicDouble[] clusteringRRAVGAtomicPart;
    AtomicDouble[] clusteringRRSTDAtomicPart;

    AtomicDouble[] clusteringWattsStrogatzAVGAtomicPart;
    AtomicDouble[] clusteringWattsStrogatzSTDAtomicPart;
    AtomicDouble[] clusteringWattsStrogatz12AVGAtomicPart;
    AtomicDouble[] clusteringWattsStrogatz12STDAtomicPart;
    AtomicDouble[] clusteringWattsStrogatz23AVGAtomicPart;
    AtomicDouble[] clusteringWattsStrogatz23STDAtomicPart;
    AtomicDouble[] clusteringWattsStrogatz13AVGAtomicPart;
    AtomicDouble[] clusteringWattsStrogatz13STDAtomicPart;
    AtomicDouble[] clusteringWattsStrogatzNRAVGAtomicPart;
    AtomicDouble[] clusteringWattsStrogatzNRSTDAtomicPart;
    AtomicDouble[] clusteringWattsStrogatzRRAVGAtomicPart;
    AtomicDouble[] clusteringWattsStrogatzRRSTDAtomicPart;

    AtomicDouble[] centralizationAVGAtomicPart;
    AtomicDouble[] centralizationSTDAtomicPart;
    AtomicDouble[] centralization12AVGAtomicPart;
    AtomicDouble[] centralization12STDAtomicPart;
    AtomicDouble[] centralization23AVGAtomicPart;
    AtomicDouble[] centralization23STDAtomicPart;
    AtomicDouble[] centralization13AVGAtomicPart;
    AtomicDouble[] centralization13STDAtomicPart;
    AtomicDouble[] centralizationNRAVGAtomicPart;
    AtomicDouble[] centralizationNRSTDAtomicPart;
    AtomicDouble[] centralizationRRAVGAtomicPart;
    AtomicDouble[] centralizationRRSTDAtomicPart;

    AtomicDouble[] efficiencyAVGAtomicPart;
    AtomicDouble[] efficiencySTDAtomicPart;
    AtomicDouble[] efficiency12AVGAtomicPart;
    AtomicDouble[] efficiency12STDAtomicPart;
    AtomicDouble[] efficiency23AVGAtomicPart;
    AtomicDouble[] efficiency23STDAtomicPart;
    AtomicDouble[] efficiency13AVGAtomicPart;
    AtomicDouble[] efficiency13STDAtomicPart;
    AtomicDouble[] efficiencyNRAVGAtomicPart;
    AtomicDouble[] efficiencyNRSTDAtomicPart;
    AtomicDouble[] efficiencyRRAVGAtomicPart;
    AtomicDouble[] efficiencyRRSTDAtomicPart;

    AtomicDouble[] distanceAVGAtomicPart;
    AtomicDouble[] distanceSTDAtomicPart;
    AtomicDouble[] distance12AVGAtomicPart;
    AtomicDouble[] distance12STDAtomicPart;
    AtomicDouble[] distance23AVGAtomicPart;
    AtomicDouble[] distance23STDAtomicPart;
    AtomicDouble[] distance13AVGAtomicPart;
    AtomicDouble[] distance13STDAtomicPart;
    AtomicDouble[] distanceNRAVGAtomicPart;
    AtomicDouble[] distanceNRSTDAtomicPart;
    AtomicDouble[] distanceRRAVGAtomicPart;
    AtomicDouble[] distanceRRSTDAtomicPart;

    AtomicDouble[] densityAVGAtomicPart;
    AtomicDouble[] densitySTDAtomicPart;
    AtomicDouble[] density12AVGAtomicPart;
    AtomicDouble[] density12STDAtomicPart;
    AtomicDouble[] density23AVGAtomicPart;
    AtomicDouble[] density23STDAtomicPart;
    AtomicDouble[] density13AVGAtomicPart;
    AtomicDouble[] density13STDAtomicPart;
    AtomicDouble[] densityNRAVGAtomicPart;
    AtomicDouble[] densityNRSTDAtomicPart;
    AtomicDouble[] densityRRAVGAtomicPart;
    AtomicDouble[] densityRRSTDAtomicPart;
    
    AtomicDouble[] sigmaAVGAtomicPart;
    AtomicDouble[] sigmaSTDAtomicPart;
    AtomicDouble[] sigma12AVGAtomicPart;
    AtomicDouble[] sigma12STDAtomicPart;
    AtomicDouble[] sigma23AVGAtomicPart;
    AtomicDouble[] sigma23STDAtomicPart;
    AtomicDouble[] sigma13AVGAtomicPart;
    AtomicDouble[] sigma13STDAtomicPart;
    AtomicDouble[] sigmaNRAVGAtomicPart;
    AtomicDouble[] sigmaNRSTDAtomicPart;
    AtomicDouble[] sigmaRRAVGAtomicPart;
    AtomicDouble[] sigmaRRSTDAtomicPart;

    AtomicDouble[] omegaAVGAtomicPart;
    AtomicDouble[] omegaSTDAtomicPart;
    AtomicDouble[] omega12AVGAtomicPart;
    AtomicDouble[] omega12STDAtomicPart;
    AtomicDouble[] omega23AVGAtomicPart;
    AtomicDouble[] omega23STDAtomicPart;
    AtomicDouble[] omega13AVGAtomicPart;
    AtomicDouble[] omega13STDAtomicPart;
    AtomicDouble[] omegaNRAVGAtomicPart;
    AtomicDouble[] omegaNRSTDAtomicPart;
    AtomicDouble[] omegaRRAVGAtomicPart;
    AtomicDouble[] omegaRRSTDAtomicPart;

    AtomicDouble[] shortestPathVarianceAVGAtomicPart;
    AtomicDouble[] shortestPathVarianceSTDAtomicPart;
    AtomicDouble[] shortestPathVariance12AVGAtomicPart;
    AtomicDouble[] shortestPathVariance12STDAtomicPart;
    AtomicDouble[] shortestPathVariance23AVGAtomicPart;
    AtomicDouble[] shortestPathVariance23STDAtomicPart;
    AtomicDouble[] shortestPathVariance13AVGAtomicPart;
    AtomicDouble[] shortestPathVariance13STDAtomicPart;
    AtomicDouble[] shortestPathVarianceNRAVGAtomicPart;
    AtomicDouble[] shortestPathVarianceNRSTDAtomicPart;
    AtomicDouble[] shortestPathVarianceRRAVGAtomicPart;
    AtomicDouble[] shortestPathVarianceRRSTDAtomicPart;

    AtomicDouble[] betweennessCentralityVarianceAVGAtomicPart;
    AtomicDouble[] betweennessCentralityVarianceSTDAtomicPart;
    AtomicDouble[] betweennessCentralityVariance12AVGAtomicPart;
    AtomicDouble[] betweennessCentralityVariance12STDAtomicPart;
    AtomicDouble[] betweennessCentralityVariance23AVGAtomicPart;
    AtomicDouble[] betweennessCentralityVariance23STDAtomicPart;
    AtomicDouble[] betweennessCentralityVariance13AVGAtomicPart;
    AtomicDouble[] betweennessCentralityVariance13STDAtomicPart;
    AtomicDouble[] betweennessCentralityVarianceNRAVGAtomicPart;
    AtomicDouble[] betweennessCentralityVarianceNRSTDAtomicPart;
    AtomicDouble[] betweennessCentralityVarianceRRAVGAtomicPart;
    AtomicDouble[] betweennessCentralityVarianceRRSTDAtomicPart;

    SingleRun(int mcIndex, int spanIndex, int enforcementIndex) {
      this.mcIndex = mcIndex;
      this.spanIndex = spanIndex;
      this.enforcementIndex = enforcementIndex;

      span = Main.SPAN[spanIndex];
      enforcement = Main.ENFORCEMENT[enforcementIndex];

      initializeResultSpace();
      run();
    }

    private void initializeResultSpace() {
      performanceAVGAtomicPart = performanceAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      performanceSTDAtomicPart = performanceSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      performance12AVGAtomicPart = performance12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      performance12STDAtomicPart = performance12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      performance23AVGAtomicPart = performance23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      performance23STDAtomicPart = performance23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      performance13AVGAtomicPart = performance13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      performance13STDAtomicPart = performance13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      performanceNRAVGAtomicPart = performanceNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      performanceNRSTDAtomicPart = performanceNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      performanceRRAVGAtomicPart = performanceRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      performanceRRSTDAtomicPart = performanceRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      disagreementAVGAtomicPart = disagreementAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreementSTDAtomicPart = disagreementSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreement12AVGAtomicPart = disagreement12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreement12STDAtomicPart = disagreement12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreement23AVGAtomicPart = disagreement23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreement23STDAtomicPart = disagreement23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreement13AVGAtomicPart = disagreement13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreement13STDAtomicPart = disagreement13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreementNRAVGAtomicPart = disagreementNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreementNRSTDAtomicPart = disagreementNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreementRRAVGAtomicPart = disagreementRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      disagreementRRSTDAtomicPart = disagreementRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      clusteringAVGAtomicPart = clusteringAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringSTDAtomicPart = clusteringSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      clustering12AVGAtomicPart = clustering12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clustering12STDAtomicPart = clustering12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      clustering23AVGAtomicPart = clustering23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clustering23STDAtomicPart = clustering23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      clustering13AVGAtomicPart = clustering13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clustering13STDAtomicPart = clustering13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringNRAVGAtomicPart = clusteringNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringNRSTDAtomicPart = clusteringNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringRRAVGAtomicPart = clusteringRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringRRSTDAtomicPart = clusteringRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      clusteringWattsStrogatzAVGAtomicPart = clusteringWattsStrogatzAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatzSTDAtomicPart = clusteringWattsStrogatzSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatz12AVGAtomicPart = clusteringWattsStrogatz12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatz12STDAtomicPart = clusteringWattsStrogatz12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatz23AVGAtomicPart = clusteringWattsStrogatz23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatz23STDAtomicPart = clusteringWattsStrogatz23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatz13AVGAtomicPart = clusteringWattsStrogatz13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatz13STDAtomicPart = clusteringWattsStrogatz13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatzNRAVGAtomicPart = clusteringWattsStrogatzNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatzNRSTDAtomicPart = clusteringWattsStrogatzNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatzRRAVGAtomicPart = clusteringWattsStrogatzRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      clusteringWattsStrogatzRRSTDAtomicPart = clusteringWattsStrogatzRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      centralizationAVGAtomicPart = centralizationAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      centralizationSTDAtomicPart = centralizationSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      centralization12AVGAtomicPart = centralization12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      centralization12STDAtomicPart = centralization12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      centralization23AVGAtomicPart = centralization23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      centralization23STDAtomicPart = centralization23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      centralization13AVGAtomicPart = centralization13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      centralization13STDAtomicPart = centralization13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      centralizationNRAVGAtomicPart = centralizationNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      centralizationNRSTDAtomicPart = centralizationNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      centralizationRRAVGAtomicPart = centralizationRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      centralizationRRSTDAtomicPart = centralizationRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      efficiencyAVGAtomicPart = efficiencyAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiencySTDAtomicPart = efficiencySTDAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiency12AVGAtomicPart = efficiency12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiency12STDAtomicPart = efficiency12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiency23AVGAtomicPart = efficiency23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiency23STDAtomicPart = efficiency23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiency13AVGAtomicPart = efficiency13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiency13STDAtomicPart = efficiency13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiencyNRAVGAtomicPart = efficiencyNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiencyNRSTDAtomicPart = efficiencyNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiencyRRAVGAtomicPart = efficiencyRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      efficiencyRRSTDAtomicPart = efficiencyRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      distanceAVGAtomicPart = distanceAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      distanceSTDAtomicPart = distanceSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      distance12AVGAtomicPart = distance12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      distance12STDAtomicPart = distance12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      distance23AVGAtomicPart = distance23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      distance23STDAtomicPart = distance23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      distance13AVGAtomicPart = distance13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      distance13STDAtomicPart = distance13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      distanceNRAVGAtomicPart = distanceNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      distanceNRSTDAtomicPart = distanceNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      distanceRRAVGAtomicPart = distanceRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      distanceRRSTDAtomicPart = distanceRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      densityAVGAtomicPart = densityAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      densitySTDAtomicPart = densitySTDAtomic[mcIndex][spanIndex][enforcementIndex];
      density12AVGAtomicPart = density12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      density12STDAtomicPart = density12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      density23AVGAtomicPart = density23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      density23STDAtomicPart = density23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      density13AVGAtomicPart = density13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      density13STDAtomicPart = density13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      densityNRAVGAtomicPart = densityNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      densityNRSTDAtomicPart = densityNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      densityRRAVGAtomicPart = densityRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      densityRRSTDAtomicPart = densityRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      sigmaAVGAtomicPart = sigmaAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      sigmaSTDAtomicPart = sigmaSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      sigma12AVGAtomicPart = sigma12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      sigma12STDAtomicPart = sigma12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      sigma23AVGAtomicPart = sigma23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      sigma23STDAtomicPart = sigma23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      sigma13AVGAtomicPart = sigma13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      sigma13STDAtomicPart = sigma13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      sigmaNRAVGAtomicPart = sigmaNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      sigmaNRSTDAtomicPart = sigmaNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      sigmaRRAVGAtomicPart = sigmaRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      sigmaRRSTDAtomicPart = sigmaRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      omegaAVGAtomicPart = omegaAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      omegaSTDAtomicPart = omegaSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      omega12AVGAtomicPart = omega12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      omega12STDAtomicPart = omega12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      omega23AVGAtomicPart = omega23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      omega23STDAtomicPart = omega23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      omega13AVGAtomicPart = omega13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      omega13STDAtomicPart = omega13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      omegaNRAVGAtomicPart = omegaNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      omegaNRSTDAtomicPart = omegaNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      omegaRRAVGAtomicPart = omegaRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      omegaRRSTDAtomicPart = omegaRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      shortestPathVarianceAVGAtomicPart = shortestPathVarianceAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVarianceSTDAtomicPart = shortestPathVarianceSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVariance12AVGAtomicPart = shortestPathVariance12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVariance12STDAtomicPart = shortestPathVariance12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVariance23AVGAtomicPart = shortestPathVariance23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVariance23STDAtomicPart = shortestPathVariance23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVariance13AVGAtomicPart = shortestPathVariance13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVariance13STDAtomicPart = shortestPathVariance13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVarianceNRAVGAtomicPart = shortestPathVarianceNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVarianceNRSTDAtomicPart = shortestPathVarianceNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVarianceRRAVGAtomicPart = shortestPathVarianceRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      shortestPathVarianceRRSTDAtomicPart = shortestPathVarianceRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];

      betweennessCentralityVarianceAVGAtomicPart = betweennessCentralityVarianceAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVarianceSTDAtomicPart = betweennessCentralityVarianceSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVariance12AVGAtomicPart = betweennessCentralityVariance12AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVariance12STDAtomicPart = betweennessCentralityVariance12STDAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVariance23AVGAtomicPart = betweennessCentralityVariance23AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVariance23STDAtomicPart = betweennessCentralityVariance23STDAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVariance13AVGAtomicPart = betweennessCentralityVariance13AVGAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVariance13STDAtomicPart = betweennessCentralityVariance13STDAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVarianceNRAVGAtomicPart = betweennessCentralityVarianceNRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVarianceNRSTDAtomicPart = betweennessCentralityVarianceNRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVarianceRRAVGAtomicPart = betweennessCentralityVarianceRRAVGAtomic[mcIndex][spanIndex][enforcementIndex];
      betweennessCentralityVarianceRRSTDAtomicPart = betweennessCentralityVarianceRRSTDAtomic[mcIndex][spanIndex][enforcementIndex];
    }

    private void run() {
      Scenario src = new Scenario(mcIndex, span, enforcement);
      Scenario nr = src.getClone(false, false);
      Scenario rr = src.getClone(true, true);
      src.doRewiring(Main.INFORMAL_INITIAL_NUM, 0);
      rr.doRewiring(Main.INFORMAL_INITIAL_NUM, 0);
      for (int t = 0; t < Main.TIME; t++) {
        synchronized (this) {
          performanceAVGAtomicPart[t].addAndGet(src.performanceAvg);
          performanceSTDAtomicPart[t].addAndGet(pow(src.performanceAvg, 2));
          performanceNRAVGAtomicPart[t].addAndGet(nr.performanceAvg);
          performanceNRSTDAtomicPart[t].addAndGet(pow(nr.performanceAvg, 2));
          performanceRRAVGAtomicPart[t].addAndGet(rr.performanceAvg);
          performanceRRSTDAtomicPart[t].addAndGet(pow(rr.performanceAvg, 2));
          double performance12 = rr.performanceAvg - nr.performanceAvg;
          performance12AVGAtomicPart[t].addAndGet(performance12);
          performance12STDAtomicPart[t].addAndGet(pow(performance12, 2));
          double performance23 = src.performanceAvg - rr.performanceAvg;
          performance23AVGAtomicPart[t].addAndGet(performance23);
          performance23STDAtomicPart[t].addAndGet(pow(performance23, 2));
          double performance13 = src.performanceAvg - nr.performanceAvg;
          performance13AVGAtomicPart[t].addAndGet(performance13);
          performance13STDAtomicPart[t].addAndGet(pow(performance13, 2));

          disagreementAVGAtomicPart[t].addAndGet(src.disagreementAvg);
          disagreementSTDAtomicPart[t].addAndGet(pow(src.disagreementAvg, 2));
          double disagreement12 = rr.disagreementAvg - nr.disagreementAvg;
          disagreement12AVGAtomicPart[t].addAndGet(disagreement12);
          disagreement12STDAtomicPart[t].addAndGet(pow(disagreement12, 2));
          double disagreement23 = src.disagreementAvg - rr.disagreementAvg;
          disagreement23AVGAtomicPart[t].addAndGet(disagreement23);
          disagreement23STDAtomicPart[t].addAndGet(pow(disagreement23, 2));
          double disagreement13 = src.disagreementAvg - nr.disagreementAvg;
          disagreement13AVGAtomicPart[t].addAndGet(disagreement13);
          disagreement13STDAtomicPart[t].addAndGet(pow(disagreement13, 2));
          disagreementNRAVGAtomicPart[t].addAndGet(nr.disagreementAvg);
          disagreementNRSTDAtomicPart[t].addAndGet(pow(nr.disagreementAvg, 2));
          disagreementRRAVGAtomicPart[t].addAndGet(rr.disagreementAvg);
          disagreementRRSTDAtomicPart[t].addAndGet(pow(rr.disagreementAvg, 2));

          clusteringAVGAtomicPart[t].addAndGet(src.globalClustering);
          clusteringSTDAtomicPart[t].addAndGet(pow(src.globalClustering, 2));
          double clustering12 = rr.globalClustering - nr.globalClustering;
          clustering12AVGAtomicPart[t].addAndGet(clustering12);
          clustering12STDAtomicPart[t].addAndGet(pow(clustering12, 2));
          double clustering23 = src.globalClustering - rr.globalClustering;
          clustering23AVGAtomicPart[t].addAndGet(clustering23);
          clustering23STDAtomicPart[t].addAndGet(pow(clustering23, 2));
          double clustering13 = src.globalClustering - nr.globalClustering;
          clustering13AVGAtomicPart[t].addAndGet(clustering13);
          clustering13STDAtomicPart[t].addAndGet(pow(clustering13, 2));
          clusteringNRAVGAtomicPart[t].addAndGet(nr.globalClustering);
          clusteringNRSTDAtomicPart[t].addAndGet(pow(nr.globalClustering, 2));
          clusteringRRAVGAtomicPart[t].addAndGet(rr.globalClustering);
          clusteringRRSTDAtomicPart[t].addAndGet(pow(rr.globalClustering, 2));

          clusteringWattsStrogatzAVGAtomicPart[t].addAndGet(src.globalClusteringWattsStrogatz);
          clusteringWattsStrogatzSTDAtomicPart[t].addAndGet(
              pow(src.globalClusteringWattsStrogatz, 2));
          double clusteringWattsStrogatz12 =
              rr.globalClusteringWattsStrogatz - nr.globalClusteringWattsStrogatz;
          clusteringWattsStrogatz12AVGAtomicPart[t].addAndGet(clusteringWattsStrogatz12);
          clusteringWattsStrogatz12STDAtomicPart[t].addAndGet(pow(clusteringWattsStrogatz12, 2));
          double clusteringWattsStrogatz23 =
              src.globalClusteringWattsStrogatz - rr.globalClusteringWattsStrogatz;
          clusteringWattsStrogatz23AVGAtomicPart[t].addAndGet(clusteringWattsStrogatz23);
          clusteringWattsStrogatz23STDAtomicPart[t].addAndGet(pow(clusteringWattsStrogatz23, 2));
          double clusteringWattsStrogatz13 =
              src.globalClusteringWattsStrogatz - nr.globalClusteringWattsStrogatz;
          clusteringWattsStrogatz13AVGAtomicPart[t].addAndGet(clusteringWattsStrogatz13);
          clusteringWattsStrogatz13STDAtomicPart[t].addAndGet(pow(clusteringWattsStrogatz13, 2));
          clusteringWattsStrogatzNRAVGAtomicPart[t].addAndGet(nr.globalClusteringWattsStrogatz);
          clusteringWattsStrogatzNRSTDAtomicPart[t].addAndGet(
              pow(nr.globalClusteringWattsStrogatz, 2));
          clusteringWattsStrogatzRRAVGAtomicPart[t].addAndGet(rr.globalClusteringWattsStrogatz);
          clusteringWattsStrogatzRRSTDAtomicPart[t].addAndGet(
              pow(rr.globalClusteringWattsStrogatz, 2));

          centralizationAVGAtomicPart[t].addAndGet(src.overallCentralization);
          centralizationSTDAtomicPart[t].addAndGet(pow(src.overallCentralization, 2));
          double centralization12 = rr.overallCentralization - nr.overallCentralization;
          centralization12AVGAtomicPart[t].addAndGet(centralization12);
          centralization12STDAtomicPart[t].addAndGet(pow(centralization12, 2));
          double centralization23 = src.overallCentralization - rr.overallCentralization;
          centralization23AVGAtomicPart[t].addAndGet(centralization23);
          centralization23STDAtomicPart[t].addAndGet(pow(centralization23, 2));
          double centralization13 = src.overallCentralization - nr.overallCentralization;
          centralization13AVGAtomicPart[t].addAndGet(centralization13);
          centralization13STDAtomicPart[t].addAndGet(pow(centralization13, 2));
          centralizationNRAVGAtomicPart[t].addAndGet(nr.overallCentralization);
          centralizationNRSTDAtomicPart[t].addAndGet(pow(nr.overallCentralization, 2));
          centralizationRRAVGAtomicPart[t].addAndGet(rr.overallCentralization);
          centralizationRRSTDAtomicPart[t].addAndGet(pow(rr.overallCentralization, 2));

          efficiencyAVGAtomicPart[t].addAndGet(src.networkEfficiency);
          efficiencySTDAtomicPart[t].addAndGet(pow(src.networkEfficiency, 2));
          double efficiency12 = rr.networkEfficiency - nr.networkEfficiency;
          efficiency12AVGAtomicPart[t].addAndGet(efficiency12);
          efficiency12STDAtomicPart[t].addAndGet(pow(efficiency12, 2));
          double efficiency23 = src.networkEfficiency - rr.networkEfficiency;
          efficiency23AVGAtomicPart[t].addAndGet(efficiency23);
          efficiency23STDAtomicPart[t].addAndGet(pow(efficiency23, 2));
          double efficiency13 = src.networkEfficiency - nr.networkEfficiency;
          efficiency13AVGAtomicPart[t].addAndGet(efficiency13);
          efficiency13STDAtomicPart[t].addAndGet(pow(efficiency13, 2));
          efficiencyNRAVGAtomicPart[t].addAndGet(nr.networkEfficiency);
          efficiencyNRSTDAtomicPart[t].addAndGet(pow(nr.networkEfficiency, 2));
          efficiencyRRAVGAtomicPart[t].addAndGet(rr.networkEfficiency);
          efficiencyRRSTDAtomicPart[t].addAndGet(pow(rr.networkEfficiency, 2));

          distanceAVGAtomicPart[t].addAndGet(src.averagePathLength);
          distanceSTDAtomicPart[t].addAndGet(pow(src.averagePathLength, 2));
          double distance12 = rr.averagePathLength - nr.averagePathLength;
          distance12AVGAtomicPart[t].addAndGet(distance12);
          distance12STDAtomicPart[t].addAndGet(pow(distance12, 2));
          double distance23 = src.averagePathLength - rr.averagePathLength;
          distance23AVGAtomicPart[t].addAndGet(distance23);
          distance23STDAtomicPart[t].addAndGet(pow(distance23, 2));
          double distance13 = src.averagePathLength - nr.averagePathLength;
          distance13AVGAtomicPart[t].addAndGet(distance13);
          distance13STDAtomicPart[t].addAndGet(pow(distance13, 2));
          distanceNRAVGAtomicPart[t].addAndGet(nr.averagePathLength);
          distanceNRSTDAtomicPart[t].addAndGet(pow(nr.averagePathLength, 2));
          distanceRRAVGAtomicPart[t].addAndGet(rr.averagePathLength);
          distanceRRSTDAtomicPart[t].addAndGet(pow(rr.averagePathLength, 2));

          densityAVGAtomicPart[t].addAndGet(src.density);
          densitySTDAtomicPart[t].addAndGet(pow(src.density, 2));
          double density12 = rr.density - nr.density;
          density12AVGAtomicPart[t].addAndGet(density12);
          density12STDAtomicPart[t].addAndGet(pow(density12, 2));
          double density23 = src.density - rr.density;
          density23AVGAtomicPart[t].addAndGet(density23);
          density23STDAtomicPart[t].addAndGet(pow(density23, 2));
          double density13 = src.density - nr.density;
          density13AVGAtomicPart[t].addAndGet(density13);
          density13STDAtomicPart[t].addAndGet(pow(density13, 2));
          densityNRAVGAtomicPart[t].addAndGet(nr.density);
          densityNRSTDAtomicPart[t].addAndGet(pow(nr.density, 2));
          densityRRAVGAtomicPart[t].addAndGet(rr.density);
          densityRRSTDAtomicPart[t].addAndGet(pow(rr.density, 2));

          double sigmaSC = transform2Sigma(src.averagePathLength, src.globalClusteringWattsStrogatz);
          double sigmaRR = transform2Sigma(rr.averagePathLength, rr.globalClusteringWattsStrogatz);
          double sigmaNR = transform2SigmaNR(nr.averagePathLength, nr.globalClusteringWattsStrogatz);
//          double sigmaSC = transform2Sigma(src.averagePathLength, src.globalClustering);
//          double sigmaRR = transform2Sigma(rr.averagePathLength, rr.globalClustering);
//          double sigmaNR = transform2SigmaNR(nr.averagePathLength, nr.globalClustering);
          sigmaAVGAtomicPart[t].addAndGet(sigmaSC);
          sigmaSTDAtomicPart[t].addAndGet(pow(sigmaSC, 2));
          sigmaRRAVGAtomicPart[t].addAndGet(sigmaRR);
          sigmaRRSTDAtomicPart[t].addAndGet(pow(sigmaRR, 2));
          sigmaNRAVGAtomicPart[t].addAndGet(sigmaNR);
          sigmaNRSTDAtomicPart[t].addAndGet(pow(sigmaNR, 2));
          double sigma12 = sigmaRR - sigmaNR;
          sigma12AVGAtomicPart[t].addAndGet(sigma12);
          sigma12STDAtomicPart[t].addAndGet(pow(sigma12, 2));
          double sigma23 = sigmaSC - sigmaRR;
          sigma23AVGAtomicPart[t].addAndGet(sigma23);
          sigma23STDAtomicPart[t].addAndGet(pow(sigma23, 2));
          double sigma13 = sigmaSC - sigmaNR;
          sigma13AVGAtomicPart[t].addAndGet(sigma13);
          sigma13STDAtomicPart[t].addAndGet(pow(sigma13, 2));

          double omegaSC = transform2Omega(src.averagePathLength, src.globalClusteringWattsStrogatz);
          double omegaRR = transform2Omega(rr.averagePathLength, rr.globalClusteringWattsStrogatz);
          double omegaNR = transform2OmegaNR(nr.averagePathLength, nr.globalClusteringWattsStrogatz);
//          double omegaSC = transform2Omega(src.averagePathLength, src.globalClustering);
//          double omegaRR = transform2Omega(rr.averagePathLength, rr.globalClustering);
//          double omegaNR = transform2OmegaNR(nr.averagePathLength, nr.globalClustering);
          omegaAVGAtomicPart[t].addAndGet(omegaSC);
          omegaSTDAtomicPart[t].addAndGet(pow(omegaSC, 2));
          omegaRRAVGAtomicPart[t].addAndGet(omegaRR);
          omegaRRSTDAtomicPart[t].addAndGet(pow(omegaRR, 2));
          omegaNRAVGAtomicPart[t].addAndGet(omegaNR);
          omegaNRSTDAtomicPart[t].addAndGet(pow(omegaNR, 2));
          double omega12 = omegaRR - omegaNR;
          omega12AVGAtomicPart[t].addAndGet(omega12);
          omega12STDAtomicPart[t].addAndGet(pow(omega12, 2));
          double omega23 = omegaSC - omegaRR;
          omega23AVGAtomicPart[t].addAndGet(omega23);
          omega23STDAtomicPart[t].addAndGet(pow(omega23, 2));
          double omega13 = omegaSC - omegaNR;
          omega13AVGAtomicPart[t].addAndGet(omega13);
          omega13STDAtomicPart[t].addAndGet(pow(omega13, 2));

          shortestPathVarianceAVGAtomicPart[t].addAndGet(
              src.shortestPathVariance);
          shortestPathVarianceSTDAtomicPart[t].addAndGet(
              pow(src.shortestPathVariance, 2));
          double shortestPathVariance12 =
              rr.shortestPathVariance - nr.shortestPathVariance;
          shortestPathVariance12AVGAtomicPart[t].addAndGet(
              shortestPathVariance12);
          shortestPathVariance12STDAtomicPart[t].addAndGet(
              pow(shortestPathVariance12, 2));
          double shortestPathVariance23 =
              src.shortestPathVariance - rr.shortestPathVariance;
          shortestPathVariance23AVGAtomicPart[t].addAndGet(
              shortestPathVariance23);
          shortestPathVariance23STDAtomicPart[t].addAndGet(
              pow(shortestPathVariance23, 2));
          double shortestPathVariance13 =
              src.shortestPathVariance - nr.shortestPathVariance;
          shortestPathVariance13AVGAtomicPart[t].addAndGet(
              shortestPathVariance13);
          shortestPathVariance13STDAtomicPart[t].addAndGet(
              pow(shortestPathVariance13, 2));
          shortestPathVarianceNRAVGAtomicPart[t].addAndGet(
              nr.shortestPathVariance);
          shortestPathVarianceNRSTDAtomicPart[t].addAndGet(
              pow(nr.shortestPathVariance, 2));
          shortestPathVarianceRRAVGAtomicPart[t].addAndGet(
              rr.shortestPathVariance);
          shortestPathVarianceRRSTDAtomicPart[t].addAndGet(
              pow(rr.shortestPathVariance, 2));

          betweennessCentralityVarianceAVGAtomicPart[t].addAndGet(
              src.betweennessCentralityVariance);
          betweennessCentralityVarianceSTDAtomicPart[t].addAndGet(
              pow(src.betweennessCentralityVariance, 2));
          double betweennessCentralityVariance12 =
              rr.betweennessCentralityVariance - nr.betweennessCentralityVariance;
          betweennessCentralityVariance12AVGAtomicPart[t].addAndGet(
              betweennessCentralityVariance12);
          betweennessCentralityVariance12STDAtomicPart[t].addAndGet(
              pow(betweennessCentralityVariance12, 2));
          double betweennessCentralityVariance23 =
              src.betweennessCentralityVariance - rr.betweennessCentralityVariance;
          betweennessCentralityVariance23AVGAtomicPart[t].addAndGet(
              betweennessCentralityVariance23);
          betweennessCentralityVariance23STDAtomicPart[t].addAndGet(
              pow(betweennessCentralityVariance23, 2));
          double betweennessCentralityVariance13 =
              src.betweennessCentralityVariance - nr.betweennessCentralityVariance;
          betweennessCentralityVariance13AVGAtomicPart[t].addAndGet(
              betweennessCentralityVariance13);
          betweennessCentralityVariance13STDAtomicPart[t].addAndGet(
              pow(betweennessCentralityVariance13, 2));
          betweennessCentralityVarianceNRAVGAtomicPart[t].addAndGet(
              nr.betweennessCentralityVariance);
          betweennessCentralityVarianceNRSTDAtomicPart[t].addAndGet(
              pow(nr.betweennessCentralityVariance, 2));
          betweennessCentralityVarianceRRAVGAtomicPart[t].addAndGet(
              rr.betweennessCentralityVariance);
          betweennessCentralityVarianceRRSTDAtomicPart[t].addAndGet(
              pow(rr.betweennessCentralityVariance, 2));
        }
        src.stepForward(Main.INFORMAL_TURNOVER_NUM);
        rr.stepForward(Main.INFORMAL_TURNOVER_NUM);
        nr.stepForward();
      }
    }

    private double transform2Sigma(double averagePathLength, double clusteringCoefficient) {
      // https://en.wikipedia.org/wiki/Small-world_network
      return (clusteringCoefficient / clusteringCoefficientRandom) / (averagePathLength / averagePathLengthRandom);
    }

    private double transform2Omega(double averagePathLength, double clusteringCoefficient) {
      // https://en.wikipedia.org/wiki/Small-world_network (Omega)
      return (averagePathLengthRandom / averagePathLength) - (clusteringCoefficient / clusteringCoefficientLattice);
//      https://en.wikipedia.org/wiki/Small-world_network (SWI)
//      return (averagePathLength - averagePathLengthLattice) * (clusteringCoefficient - clusteringCoefficientRandom) / swiDenominator;
    }

    private double transform2SigmaNR(double averagePathLength, double clusteringCoefficient) {
      // https://en.wikipedia.org/wiki/Small-world_network
      return (clusteringCoefficient / clusteringCoefficientRandomNR) / (averagePathLength / averagePathLengthRandomNR);
    }

    private double transform2OmegaNR(double averagePathLength, double clusteringCoefficient) {
      // https://en.wikipedia.org/wiki/Small-world_network (Omega)
      return (averagePathLengthRandomNR / averagePathLength); // We drop the minus part since CC of NR is guaranteed to be 0.
//      https://en.wikipedia.org/wiki/Small-world_network (SWI)
//      return (averagePathLength - averagePathLengthLattice) * (clusteringCoefficient - clusteringCoefficientRandom) / swiDenominator;
    }


  }

}
