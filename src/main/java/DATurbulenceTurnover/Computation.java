package DATurbulenceTurnover;

import static org.apache.commons.math3.util.FastMath.log;
import static org.apache.commons.math3.util.FastMath.pow;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.DoubleAdder;

public class Computation {

  ExecutorService workStealingPool;

  // Replace AdderDouble with DoubleAdder, add two dimensions: [LENGTH_TURBULENCE][LENGTH_TURNOVER]

  DoubleAdder[][][][][][] performanceAVGAdder;
  DoubleAdder[][][][][][] performanceSTDAdder;
  DoubleAdder[][][][][][] performance12AVGAdder;
  DoubleAdder[][][][][][] performance12STDAdder;
  DoubleAdder[][][][][][] performance23AVGAdder;
  DoubleAdder[][][][][][] performance23STDAdder;
  DoubleAdder[][][][][][] performance13AVGAdder;
  DoubleAdder[][][][][][] performance13STDAdder;
  DoubleAdder[][][][][][] performanceNRAVGAdder;
  DoubleAdder[][][][][][] performanceNRSTDAdder;
  DoubleAdder[][][][][][] performanceRRAVGAdder;
  DoubleAdder[][][][][][] performanceRRSTDAdder;

  DoubleAdder[][][][][][] clusteringWattsStrogatzAVGAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatzSTDAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatz12AVGAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatz12STDAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatz23AVGAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatz23STDAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatz13AVGAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatz13STDAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatzNRAVGAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatzNRSTDAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatzRRAVGAdder;
  DoubleAdder[][][][][][] clusteringWattsStrogatzRRSTDAdder;

  DoubleAdder[][][][][][] centralizationAVGAdder;
  DoubleAdder[][][][][][] centralizationSTDAdder;
  DoubleAdder[][][][][][] centralization12AVGAdder;
  DoubleAdder[][][][][][] centralization12STDAdder;
  DoubleAdder[][][][][][] centralization23AVGAdder;
  DoubleAdder[][][][][][] centralization23STDAdder;
  DoubleAdder[][][][][][] centralization13AVGAdder;
  DoubleAdder[][][][][][] centralization13STDAdder;
  DoubleAdder[][][][][][] centralizationNRAVGAdder;
  DoubleAdder[][][][][][] centralizationNRSTDAdder;
  DoubleAdder[][][][][][] centralizationRRAVGAdder;
  DoubleAdder[][][][][][] centralizationRRSTDAdder;

  DoubleAdder[][][][][][] efficiencyAVGAdder;
  DoubleAdder[][][][][][] efficiencySTDAdder;
  DoubleAdder[][][][][][] efficiency12AVGAdder;
  DoubleAdder[][][][][][] efficiency12STDAdder;
  DoubleAdder[][][][][][] efficiency23AVGAdder;
  DoubleAdder[][][][][][] efficiency23STDAdder;
  DoubleAdder[][][][][][] efficiency13AVGAdder;
  DoubleAdder[][][][][][] efficiency13STDAdder;
  DoubleAdder[][][][][][] efficiencyNRAVGAdder;
  DoubleAdder[][][][][][] efficiencyNRSTDAdder;
  DoubleAdder[][][][][][] efficiencyRRAVGAdder;
  DoubleAdder[][][][][][] efficiencyRRSTDAdder;

  DoubleAdder[][][][][][] sigmaAVGAdder;
  DoubleAdder[][][][][][] sigmaSTDAdder;
  DoubleAdder[][][][][][] sigma12AVGAdder;
  DoubleAdder[][][][][][] sigma12STDAdder;
  DoubleAdder[][][][][][] sigma23AVGAdder;
  DoubleAdder[][][][][][] sigma23STDAdder;
  DoubleAdder[][][][][][] sigma13AVGAdder;
  DoubleAdder[][][][][][] sigma13STDAdder;
  DoubleAdder[][][][][][] sigmaNRAVGAdder;
  DoubleAdder[][][][][][] sigmaNRSTDAdder;
  DoubleAdder[][][][][][] sigmaRRAVGAdder;
  DoubleAdder[][][][][][] sigmaRRSTDAdder;

  DoubleAdder[][][][][][] omegaAVGAdder;
  DoubleAdder[][][][][][] omegaSTDAdder;
  DoubleAdder[][][][][][] omega12AVGAdder;
  DoubleAdder[][][][][][] omega12STDAdder;
  DoubleAdder[][][][][][] omega23AVGAdder;
  DoubleAdder[][][][][][] omega23STDAdder;
  DoubleAdder[][][][][][] omega13AVGAdder;
  DoubleAdder[][][][][][] omega13STDAdder;
  DoubleAdder[][][][][][] omegaNRAVGAdder;
  DoubleAdder[][][][][][] omegaNRSTDAdder;
  DoubleAdder[][][][][][] omegaRRAVGAdder;
  DoubleAdder[][][][][][] omegaRRSTDAdder;

  DoubleAdder[][][][][][] shortestPathVarianceAVGAdder;
  DoubleAdder[][][][][][] shortestPathVarianceSTDAdder;
  DoubleAdder[][][][][][] shortestPathVariance12AVGAdder;
  DoubleAdder[][][][][][] shortestPathVariance12STDAdder;
  DoubleAdder[][][][][][] shortestPathVariance23AVGAdder;
  DoubleAdder[][][][][][] shortestPathVariance23STDAdder;
  DoubleAdder[][][][][][] shortestPathVariance13AVGAdder;
  DoubleAdder[][][][][][] shortestPathVariance13STDAdder;
  DoubleAdder[][][][][][] shortestPathVarianceNRAVGAdder;
  DoubleAdder[][][][][][] shortestPathVarianceNRSTDAdder;
  DoubleAdder[][][][][][] shortestPathVarianceRRAVGAdder;
  DoubleAdder[][][][][][] shortestPathVarianceRRSTDAdder;

  // Now the final arrays also get two extra dimensions.
  double[][][][][][] performanceAVG;
  double[][][][][][] performanceSTD;
  double[][][][][][] performance12AVG;
  double[][][][][][] performance12STD;
  double[][][][][][] performance23AVG;
  double[][][][][][] performance23STD;
  double[][][][][][] performance13AVG;
  double[][][][][][] performance13STD;
  double[][][][][][] performanceNRAVG;
  double[][][][][][] performanceNRSTD;
  double[][][][][][] performanceRRAVG;
  double[][][][][][] performanceRRSTD;

  double[][][][][][] clusteringWattsStrogatzAVG;
  double[][][][][][] clusteringWattsStrogatzSTD;
  double[][][][][][] clusteringWattsStrogatz12AVG;
  double[][][][][][] clusteringWattsStrogatz12STD;
  double[][][][][][] clusteringWattsStrogatz23AVG;
  double[][][][][][] clusteringWattsStrogatz23STD;
  double[][][][][][] clusteringWattsStrogatz13AVG;
  double[][][][][][] clusteringWattsStrogatz13STD;
  double[][][][][][] clusteringWattsStrogatzNRAVG;
  double[][][][][][] clusteringWattsStrogatzNRSTD;
  double[][][][][][] clusteringWattsStrogatzRRAVG;
  double[][][][][][] clusteringWattsStrogatzRRSTD;

  double[][][][][][] centralizationAVG;
  double[][][][][][] centralizationSTD;
  double[][][][][][] centralization12AVG;
  double[][][][][][] centralization12STD;
  double[][][][][][] centralization23AVG;
  double[][][][][][] centralization23STD;
  double[][][][][][] centralization13AVG;
  double[][][][][][] centralization13STD;
  double[][][][][][] centralizationNRAVG;
  double[][][][][][] centralizationNRSTD;
  double[][][][][][] centralizationRRAVG;
  double[][][][][][] centralizationRRSTD;

  double[][][][][][] efficiencyAVG;
  double[][][][][][] efficiencySTD;
  double[][][][][][] efficiency12AVG;
  double[][][][][][] efficiency12STD;
  double[][][][][][] efficiency23AVG;
  double[][][][][][] efficiency23STD;
  double[][][][][][] efficiency13AVG;
  double[][][][][][] efficiency13STD;
  double[][][][][][] efficiencyNRAVG;
  double[][][][][][] efficiencyNRSTD;
  double[][][][][][] efficiencyRRAVG;
  double[][][][][][] efficiencyRRSTD;

  double[][][][][][] distanceAVG;
  double[][][][][][] distanceSTD;
  double[][][][][][] distance12AVG;
  double[][][][][][] distance12STD;
  double[][][][][][] distance23AVG;
  double[][][][][][] distance23STD;
  double[][][][][][] distance13AVG;
  double[][][][][][] distance13STD;
  double[][][][][][] distanceNRAVG;
  double[][][][][][] distanceNRSTD;
  double[][][][][][] distanceRRAVG;
  double[][][][][][] distanceRRSTD;

  double[][][][][][] densityAVG;
  double[][][][][][] densitySTD;
  double[][][][][][] density12AVG;
  double[][][][][][] density12STD;
  double[][][][][][] density23AVG;
  double[][][][][][] density23STD;
  double[][][][][][] density13AVG;
  double[][][][][][] density13STD;
  double[][][][][][] densityNRAVG;
  double[][][][][][] densityNRSTD;
  double[][][][][][] densityRRAVG;
  double[][][][][][] densityRRSTD;

  double[][][][][][] sigmaAVG;
  double[][][][][][] sigmaSTD;
  double[][][][][][] sigma12AVG;
  double[][][][][][] sigma12STD;
  double[][][][][][] sigma23AVG;
  double[][][][][][] sigma23STD;
  double[][][][][][] sigma13AVG;
  double[][][][][][] sigma13STD;
  double[][][][][][] sigmaNRAVG;
  double[][][][][][] sigmaNRSTD;
  double[][][][][][] sigmaRRAVG;
  double[][][][][][] sigmaRRSTD;

  double[][][][][][] omegaAVG;
  double[][][][][][] omegaSTD;
  double[][][][][][] omega12AVG;
  double[][][][][][] omega12STD;
  double[][][][][][] omega23AVG;
  double[][][][][][] omega23STD;
  double[][][][][][] omega13AVG;
  double[][][][][][] omega13STD;
  double[][][][][][] omegaNRAVG;
  double[][][][][][] omegaNRSTD;
  double[][][][][][] omegaRRAVG;
  double[][][][][][] omegaRRSTD;

  double[][][][][][] shortestPathVarianceAVG;
  double[][][][][][] shortestPathVarianceSTD;
  double[][][][][][] shortestPathVariance12AVG;
  double[][][][][][] shortestPathVariance12STD;
  double[][][][][][] shortestPathVariance23AVG;
  double[][][][][][] shortestPathVariance23STD;
  double[][][][][][] shortestPathVariance13AVG;
  double[][][][][][] shortestPathVariance13STD;
  double[][][][][][] shortestPathVarianceNRAVG;
  double[][][][][][] shortestPathVarianceNRSTD;
  double[][][][][][] shortestPathVarianceRRAVG;
  double[][][][][][] shortestPathVarianceRRSTD;

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
    BitSet[] lattice = new BitSet[Main.N];
    NetworkAnalyzer na;
    int nNeighborFloor = (int) (nInteraction / Main.N);
    int remainingNeighbor = (int) nInteraction - (nNeighborFloor * Main.N);
    int remainingNeighborInterval = (int) ((double) Main.N / (double) remainingNeighbor);

    averagePathLengthRandom = log(Main.N) / log(p * (Main.N - 1D));
    averagePathLengthRandomNR = log(Main.N) / log(pNR * (Main.N - 1D));
    clusteringCoefficientRandom = p;
    clusteringCoefficientRandomNR = pNR;

    for (int i = 0; i < Main.N; i++) {
      lattice[i] = new BitSet(Main.N);
    }
    for (int i = 0; i < nNeighborFloor; i++) {
      for (int n = 0; n < Main.N; n++) {
        int target = (n + i + 1) % Main.N;
        lattice[n].set(target);
        lattice[target].set(n);
      }
    }
    if (remainingNeighborInterval > 0) {
      for (int n = 0; n < Main.N; n += remainingNeighborInterval) {
        int target = (n + nNeighborFloor + 1) % Main.N;
        lattice[n].set(target);
        lattice[target].set(n);
      }
    }
    na = new NetworkAnalyzer(lattice);
    na.setNetworkMetrics();
    averagePathLengthLattice = na.getAveragePathLength();
    clusteringCoefficientLattice = na.getGlobalClusteringWattsStrogatz();
    swiDenominator = (averagePathLengthRandom - averagePathLengthLattice) * (clusteringCoefficientLattice - clusteringCoefficientRandom);
  }

  public void printNetwork() {
    try {
      Files.createDirectories(Paths.get(Main.PATH_CSV));
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int sd = 0; sd < Main.NUM_SOCIAL_DYNAMICS; sd++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
          for (int turb = 0; turb < Main.LENGTH_TURBULENCE; turb++) {
            for (int turn = 0; turn < Main.LENGTH_TURNOVER; turn++) {
              int span = Main.SPAN[s];
              double enforcement = Main.ENFORCEMENT[e];
              double turbulenceRate = Main.TURBULENCE_RATE[turb];
              int turbulenceInterval = Main.TURBULENCE_INTERVAL[turb];
              double turnoverRate = Main.TURNOVER_RATE[turn];
              String mcString = null;
              switch (sd) {
                case 0 -> mcString = "netClos";
                case 1 -> mcString = "prefAtt";
              }
              String fileName = Main.RUN_ID + "_s" + span + "_" + enforcement + "_" + turbulenceRate + "_(@" + turbulenceInterval + ")_" + turnoverRate + "_" + mcString;
              Scenario src = new Scenario(sd, span, enforcement, turbulenceRate, turnoverRate);
              Scenario rr = src.getClone(true, true);
              Scenario nr = src.getClone(false, false);
              src.doRewiring(Main.INFORMAL_INITIAL_NUM, 0);
              rr.doRewiring(Main.INFORMAL_INITIAL_NUM, 0);
              src.printCSV(Main.PATH_CSV + "sc_" + fileName + "_t0");
              rr.printCSV(Main.PATH_CSV + "rr_" + fileName + "_t0");
              nr.printCSV(Main.PATH_CSV + "nr_" + fileName + "_t0");
              if (Main.DO_POST_REWIRING) {
                for (int t = 0; t < Main.TIME; t++) {
                  if (t % turbulenceInterval == 0) {
                    src.doTurbulence();
                    nr.doTurbulence();
                    rr.doTurbulence();
                  }
                  src.stepForward(Main.INFORMAL_REWIRING_NUM);
                  rr.stepForward(Main.INFORMAL_REWIRING_NUM);
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
    }
  }

  public void doExperiment() {
    pb = new ProgressBar(Main.RUN_ID + ": Computation", Main.ITERATION);
    setResultSpace();
    runFullExperiment();
    averageFullExperiment();
  }

  private void setResultSpace() {
    // Now we create 6D DoubleAdder arrays:
    performanceAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance12AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance12STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance23AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance23STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance13AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance13STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceNRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceNRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceRRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceRRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    clusteringWattsStrogatzAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz12AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz12STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz23AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz23STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz13AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz13STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzNRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzNRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzRRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzRRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    centralizationAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization12AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization12STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization23AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization23STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization13AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization13STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationNRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationNRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationRRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationRRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    efficiencyAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencySTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency12AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency12STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency23AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency23STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency13AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency13STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencyNRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencyNRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencyRRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencyRRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    sigmaAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma12AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma12STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma23AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma23STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma13AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma13STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaNRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaNRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaRRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaRRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    omegaAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega12AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega12STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega23AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega23STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega13AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega13STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaNRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaNRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaRRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaRRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    shortestPathVarianceAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance12AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance12STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance23AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance23STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance13AVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance13STDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceNRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceNRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceRRAVGAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceRRSTDAdder = new DoubleAdder[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    // Initialize each DoubleAdder in a nested loop with 6 levels
    for (int mc = 0; mc < Main.NUM_SOCIAL_DYNAMICS; mc++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
          for (int turb = 0; turb < Main.LENGTH_TURBULENCE; turb++) {
            for (int turn = 0; turn < Main.LENGTH_TURNOVER; turn++) {
              for (int t = 0; t < Main.TIME; t++) {
                performanceAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performanceSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performance12AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performance12STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performance23AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performance23STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performance13AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performance13STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performanceNRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performanceNRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performanceRRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                performanceRRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();

                clusteringWattsStrogatzAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatzSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatz12AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatz12STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatz23AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatz23STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatz13AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatz13STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatzNRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatzNRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatzRRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                clusteringWattsStrogatzRRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();

                centralizationAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralizationSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralization12AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralization12STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralization23AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralization23STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralization13AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralization13STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralizationNRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralizationNRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralizationRRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                centralizationRRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();

                efficiencyAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiencySTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiency12AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiency12STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiency23AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiency23STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiency13AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiency13STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiencyNRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiencyNRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiencyRRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                efficiencyRRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();

                sigmaAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigmaSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigma12AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigma12STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigma23AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigma23STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigma13AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigma13STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigmaNRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigmaNRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigmaRRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                sigmaRRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();

                omegaAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omegaSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omega12AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omega12STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omega23AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omega23STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omega13AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omega13STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omegaNRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omegaNRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omegaRRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                omegaRRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();

                shortestPathVarianceAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVarianceSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVariance12AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVariance12STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVariance23AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVariance23STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVariance13AVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVariance13STDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVarianceNRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVarianceNRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVarianceRRAVGAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
                shortestPathVarianceRRSTDAdder[mc][s][e][turb][turn][t] = new DoubleAdder();
              }
            }
          }
        }
      }
    }

    // Now the final arrays: 6D double arrays.
    performanceAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performance13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    performanceRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    clusteringWattsStrogatzAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatz13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    clusteringWattsStrogatzRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    centralizationAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralization13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    centralizationRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    efficiencyAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencySTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiency13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencyNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencyNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencyRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    efficiencyRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    distanceAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distanceSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distance12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distance12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distance23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distance23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distance13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distance13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distanceNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distanceNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distanceRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    distanceRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    densityAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    densitySTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    density12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    density12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    density23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    density23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    density13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    density13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    densityNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    densityNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    densityRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    densityRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    sigmaAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigma13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    sigmaRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    omegaAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omega13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    omegaRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];

    shortestPathVarianceAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance12AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance12STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance23AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance23STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance13AVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVariance13STD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceNRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceNRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceRRAVG = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
    shortestPathVarianceRRSTD = new double[Main.NUM_SOCIAL_DYNAMICS][Main.LENGTH_SPAN][Main.LENGTH_ENFORCEMENT][Main.LENGTH_TURBULENCE][Main.LENGTH_TURNOVER][Main.TIME];
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
      // Add two extra loops for turb, turn:
      for (int mc = 0; mc < Main.NUM_SOCIAL_DYNAMICS; mc++) {
        for (int s = 0; s < Main.LENGTH_SPAN; s++) {
          for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
            for (int turb = 0; turb < Main.LENGTH_TURBULENCE; turb++) {
              for (int turn = 0; turn < Main.LENGTH_TURNOVER; turn++) {
                new SingleRun(mc, s, e, turb, turn);
              }
            }
          }
        }
      }
      pb.stepNext();
    }
  }

  private void averageFullExperiment() {
    // Again, we add two more loops for turb, turn
    for (int mc = 0; mc < Main.NUM_SOCIAL_DYNAMICS; mc++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
          for (int turb = 0; turb < Main.LENGTH_TURBULENCE; turb++) {
            for (int turn = 0; turn < Main.LENGTH_TURNOVER; turn++) {
              for (int t = 0; t < Main.TIME; t++) {
                performanceAVG[mc][s][e][turb][turn][t] = performanceAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performanceSTD[mc][s][e][turb][turn][t] = performanceSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performanceSTD[mc][s][e][turb][turn][t] = pow(performanceSTD[mc][s][e][turb][turn][t] - pow(performanceAVG[mc][s][e][turb][turn][t], 2), .5);

                performance12AVG[mc][s][e][turb][turn][t] = performance12AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performance12STD[mc][s][e][turb][turn][t] = performance12STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performance12STD[mc][s][e][turb][turn][t] = pow(performance12STD[mc][s][e][turb][turn][t] - pow(performance12AVG[mc][s][e][turb][turn][t], 2), .5);

                performance23AVG[mc][s][e][turb][turn][t] = performance23AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performance23STD[mc][s][e][turb][turn][t] = performance23STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performance23STD[mc][s][e][turb][turn][t] = pow(performance23STD[mc][s][e][turb][turn][t] - pow(performance23AVG[mc][s][e][turb][turn][t], 2), .5);

                performance13AVG[mc][s][e][turb][turn][t] = performance13AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performance13STD[mc][s][e][turb][turn][t] = performance13STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performance13STD[mc][s][e][turb][turn][t] = pow(performance13STD[mc][s][e][turb][turn][t] - pow(performance13AVG[mc][s][e][turb][turn][t], 2), .5);

                performanceNRAVG[mc][s][e][turb][turn][t] = performanceNRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performanceNRSTD[mc][s][e][turb][turn][t] = performanceNRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performanceNRSTD[mc][s][e][turb][turn][t] = pow(performanceNRSTD[mc][s][e][turb][turn][t] - pow(performanceNRAVG[mc][s][e][turb][turn][t], 2), .5);

                performanceRRAVG[mc][s][e][turb][turn][t] = performanceRRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performanceRRSTD[mc][s][e][turb][turn][t] = performanceRRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                performanceRRSTD[mc][s][e][turb][turn][t] = pow(performanceRRSTD[mc][s][e][turb][turn][t] - pow(performanceRRAVG[mc][s][e][turb][turn][t], 2), .5);

                clusteringWattsStrogatzAVG[mc][s][e][turb][turn][t] = clusteringWattsStrogatzAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatzSTD[mc][s][e][turb][turn][t] = clusteringWattsStrogatzSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatzSTD[mc][s][e][turb][turn][t] = pow(clusteringWattsStrogatzSTD[mc][s][e][turb][turn][t] - pow(clusteringWattsStrogatzAVG[mc][s][e][turb][turn][t], 2), .5);

                clusteringWattsStrogatz12AVG[mc][s][e][turb][turn][t] = clusteringWattsStrogatz12AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatz12STD[mc][s][e][turb][turn][t] = clusteringWattsStrogatz12STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatz12STD[mc][s][e][turb][turn][t] = pow(clusteringWattsStrogatz12STD[mc][s][e][turb][turn][t] - pow(clusteringWattsStrogatz12AVG[mc][s][e][turb][turn][t], 2), .5);

                clusteringWattsStrogatz23AVG[mc][s][e][turb][turn][t] = clusteringWattsStrogatz23AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatz23STD[mc][s][e][turb][turn][t] = clusteringWattsStrogatz23STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatz23STD[mc][s][e][turb][turn][t] = pow(clusteringWattsStrogatz23STD[mc][s][e][turb][turn][t] - pow(clusteringWattsStrogatz23AVG[mc][s][e][turb][turn][t], 2), .5);

                clusteringWattsStrogatz13AVG[mc][s][e][turb][turn][t] = clusteringWattsStrogatz13AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatz13STD[mc][s][e][turb][turn][t] = clusteringWattsStrogatz13STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatz13STD[mc][s][e][turb][turn][t] = pow(clusteringWattsStrogatz13STD[mc][s][e][turb][turn][t] - pow(clusteringWattsStrogatz13AVG[mc][s][e][turb][turn][t], 2), .5);

                clusteringWattsStrogatzNRAVG[mc][s][e][turb][turn][t] = clusteringWattsStrogatzNRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatzNRSTD[mc][s][e][turb][turn][t] = clusteringWattsStrogatzNRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatzNRSTD[mc][s][e][turb][turn][t] = pow(clusteringWattsStrogatzNRSTD[mc][s][e][turb][turn][t] - pow(clusteringWattsStrogatzNRAVG[mc][s][e][turb][turn][t], 2), .5);

                clusteringWattsStrogatzRRAVG[mc][s][e][turb][turn][t] = clusteringWattsStrogatzRRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatzRRSTD[mc][s][e][turb][turn][t] = clusteringWattsStrogatzRRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                clusteringWattsStrogatzRRSTD[mc][s][e][turb][turn][t] = pow(clusteringWattsStrogatzRRSTD[mc][s][e][turb][turn][t] - pow(clusteringWattsStrogatzRRAVG[mc][s][e][turb][turn][t], 2), .5);

                centralizationAVG[mc][s][e][turb][turn][t] = centralizationAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralizationSTD[mc][s][e][turb][turn][t] = centralizationSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralizationSTD[mc][s][e][turb][turn][t] = pow(centralizationSTD[mc][s][e][turb][turn][t] - pow(centralizationAVG[mc][s][e][turb][turn][t], 2), .5);

                centralization12AVG[mc][s][e][turb][turn][t] = centralization12AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralization12STD[mc][s][e][turb][turn][t] = centralization12STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralization12STD[mc][s][e][turb][turn][t] = pow(centralization12STD[mc][s][e][turb][turn][t] - pow(centralization12AVG[mc][s][e][turb][turn][t], 2), .5);

                centralization23AVG[mc][s][e][turb][turn][t] = centralization23AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralization23STD[mc][s][e][turb][turn][t] = centralization23STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralization23STD[mc][s][e][turb][turn][t] = pow(centralization23STD[mc][s][e][turb][turn][t] - pow(centralization23AVG[mc][s][e][turb][turn][t], 2), .5);

                centralization13AVG[mc][s][e][turb][turn][t] = centralization13AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralization13STD[mc][s][e][turb][turn][t] = centralization13STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralization13STD[mc][s][e][turb][turn][t] = pow(centralization13STD[mc][s][e][turb][turn][t] - pow(centralization13AVG[mc][s][e][turb][turn][t], 2), .5);

                centralizationNRAVG[mc][s][e][turb][turn][t] = centralizationNRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralizationNRSTD[mc][s][e][turb][turn][t] = centralizationNRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralizationNRSTD[mc][s][e][turb][turn][t] = pow(centralizationNRSTD[mc][s][e][turb][turn][t] - pow(centralizationNRAVG[mc][s][e][turb][turn][t], 2), .5);

                centralizationRRAVG[mc][s][e][turb][turn][t] = centralizationRRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralizationRRSTD[mc][s][e][turb][turn][t] = centralizationRRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                centralizationRRSTD[mc][s][e][turb][turn][t] = pow(centralizationRRSTD[mc][s][e][turb][turn][t] - pow(centralizationRRAVG[mc][s][e][turb][turn][t], 2), .5);

                efficiencyAVG[mc][s][e][turb][turn][t] = efficiencyAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiencySTD[mc][s][e][turb][turn][t] = efficiencySTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiencySTD[mc][s][e][turb][turn][t] = pow(efficiencySTD[mc][s][e][turb][turn][t] - pow(efficiencyAVG[mc][s][e][turb][turn][t], 2), .5);

                efficiency12AVG[mc][s][e][turb][turn][t] = efficiency12AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiency12STD[mc][s][e][turb][turn][t] = efficiency12STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiency12STD[mc][s][e][turb][turn][t] = pow(efficiency12STD[mc][s][e][turb][turn][t] - pow(efficiency12AVG[mc][s][e][turb][turn][t], 2), .5);

                efficiency23AVG[mc][s][e][turb][turn][t] = efficiency23AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiency23STD[mc][s][e][turb][turn][t] = efficiency23STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiency23STD[mc][s][e][turb][turn][t] = pow(efficiency23STD[mc][s][e][turb][turn][t] - pow(efficiency23AVG[mc][s][e][turb][turn][t], 2), .5);

                efficiency13AVG[mc][s][e][turb][turn][t] = efficiency13AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiency13STD[mc][s][e][turb][turn][t] = efficiency13STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiency13STD[mc][s][e][turb][turn][t] = pow(efficiency13STD[mc][s][e][turb][turn][t] - pow(efficiency13AVG[mc][s][e][turb][turn][t], 2), .5);

                efficiencyNRAVG[mc][s][e][turb][turn][t] = efficiencyNRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiencyNRSTD[mc][s][e][turb][turn][t] = efficiencyNRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiencyNRSTD[mc][s][e][turb][turn][t] = pow(efficiencyNRSTD[mc][s][e][turb][turn][t] - pow(efficiencyNRAVG[mc][s][e][turb][turn][t], 2), .5);

                efficiencyRRAVG[mc][s][e][turb][turn][t] = efficiencyRRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiencyRRSTD[mc][s][e][turb][turn][t] = efficiencyRRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                efficiencyRRSTD[mc][s][e][turb][turn][t] = pow(efficiencyRRSTD[mc][s][e][turb][turn][t] - pow(efficiencyRRAVG[mc][s][e][turb][turn][t], 2), .5);

                sigmaAVG[mc][s][e][turb][turn][t] = sigmaAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigmaSTD[mc][s][e][turb][turn][t] = sigmaSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigmaSTD[mc][s][e][turb][turn][t] = pow(sigmaSTD[mc][s][e][turb][turn][t] - pow(sigmaAVG[mc][s][e][turb][turn][t], 2), .5);

                sigma12AVG[mc][s][e][turb][turn][t] = sigma12AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigma12STD[mc][s][e][turb][turn][t] = sigma12STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigma12STD[mc][s][e][turb][turn][t] = pow(sigma12STD[mc][s][e][turb][turn][t] - pow(sigma12AVG[mc][s][e][turb][turn][t], 2), .5);

                sigma23AVG[mc][s][e][turb][turn][t] = sigma23AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigma23STD[mc][s][e][turb][turn][t] = sigma23STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigma23STD[mc][s][e][turb][turn][t] = pow(sigma23STD[mc][s][e][turb][turn][t] - pow(sigma23AVG[mc][s][e][turb][turn][t], 2), .5);

                sigma13AVG[mc][s][e][turb][turn][t] = sigma13AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigma13STD[mc][s][e][turb][turn][t] = sigma13STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigma13STD[mc][s][e][turb][turn][t] = pow(sigma13STD[mc][s][e][turb][turn][t] - pow(sigma13AVG[mc][s][e][turb][turn][t], 2), .5);

                sigmaNRAVG[mc][s][e][turb][turn][t] = sigmaNRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigmaNRSTD[mc][s][e][turb][turn][t] = sigmaNRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigmaNRSTD[mc][s][e][turb][turn][t] = pow(sigmaNRSTD[mc][s][e][turb][turn][t] - pow(sigmaNRAVG[mc][s][e][turb][turn][t], 2), .5);

                sigmaRRAVG[mc][s][e][turb][turn][t] = sigmaRRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigmaRRSTD[mc][s][e][turb][turn][t] = sigmaRRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                sigmaRRSTD[mc][s][e][turb][turn][t] = pow(sigmaRRSTD[mc][s][e][turb][turn][t] - pow(sigmaRRAVG[mc][s][e][turb][turn][t], 2), .5);

                omegaAVG[mc][s][e][turb][turn][t] = omegaAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omegaSTD[mc][s][e][turb][turn][t] = omegaSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omegaSTD[mc][s][e][turb][turn][t] = pow(omegaSTD[mc][s][e][turb][turn][t] - pow(omegaAVG[mc][s][e][turb][turn][t], 2), .5);

                omega12AVG[mc][s][e][turb][turn][t] = omega12AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omega12STD[mc][s][e][turb][turn][t] = omega12STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omega12STD[mc][s][e][turb][turn][t] = pow(omega12STD[mc][s][e][turb][turn][t] - pow(omega12AVG[mc][s][e][turb][turn][t], 2), .5);

                omega23AVG[mc][s][e][turb][turn][t] = omega23AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omega23STD[mc][s][e][turb][turn][t] = omega23STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omega23STD[mc][s][e][turb][turn][t] = pow(omega23STD[mc][s][e][turb][turn][t] - pow(omega23AVG[mc][s][e][turb][turn][t], 2), .5);

                omega13AVG[mc][s][e][turb][turn][t] = omega13AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omega13STD[mc][s][e][turb][turn][t] = omega13STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omega13STD[mc][s][e][turb][turn][t] = pow(omega13STD[mc][s][e][turb][turn][t] - pow(omega13AVG[mc][s][e][turb][turn][t], 2), .5);

                omegaNRAVG[mc][s][e][turb][turn][t] = omegaNRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omegaNRSTD[mc][s][e][turb][turn][t] = omegaNRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omegaNRSTD[mc][s][e][turb][turn][t] = pow(omegaNRSTD[mc][s][e][turb][turn][t] - pow(omegaNRAVG[mc][s][e][turb][turn][t], 2), .5);

                omegaRRAVG[mc][s][e][turb][turn][t] = omegaRRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omegaRRSTD[mc][s][e][turb][turn][t] = omegaRRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                omegaRRSTD[mc][s][e][turb][turn][t] = pow(omegaRRSTD[mc][s][e][turb][turn][t] - pow(omegaRRAVG[mc][s][e][turb][turn][t], 2), .5);

                shortestPathVarianceAVG[mc][s][e][turb][turn][t] = shortestPathVarianceAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVarianceSTD[mc][s][e][turb][turn][t] = shortestPathVarianceSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVarianceSTD[mc][s][e][turb][turn][t] = pow(shortestPathVarianceSTD[mc][s][e][turb][turn][t] - pow(shortestPathVarianceAVG[mc][s][e][turb][turn][t], 2), .5);

                shortestPathVariance12AVG[mc][s][e][turb][turn][t] = shortestPathVariance12AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVariance12STD[mc][s][e][turb][turn][t] = shortestPathVariance12STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVariance12STD[mc][s][e][turb][turn][t] = pow(shortestPathVariance12STD[mc][s][e][turb][turn][t] - pow(shortestPathVariance12AVG[mc][s][e][turb][turn][t], 2), .5);

                shortestPathVariance23AVG[mc][s][e][turb][turn][t] = shortestPathVariance23AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVariance23STD[mc][s][e][turb][turn][t] = shortestPathVariance23STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVariance23STD[mc][s][e][turb][turn][t] = pow(shortestPathVariance23STD[mc][s][e][turb][turn][t] - pow(shortestPathVariance23AVG[mc][s][e][turb][turn][t], 2), .5);

                shortestPathVariance13AVG[mc][s][e][turb][turn][t] = shortestPathVariance13AVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVariance13STD[mc][s][e][turb][turn][t] = shortestPathVariance13STDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVariance13STD[mc][s][e][turb][turn][t] = pow(shortestPathVariance13STD[mc][s][e][turb][turn][t] - pow(shortestPathVariance13AVG[mc][s][e][turb][turn][t], 2), .5);

                shortestPathVarianceNRAVG[mc][s][e][turb][turn][t] = shortestPathVarianceNRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVarianceNRSTD[mc][s][e][turb][turn][t] = shortestPathVarianceNRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVarianceNRSTD[mc][s][e][turb][turn][t] = pow(shortestPathVarianceNRSTD[mc][s][e][turb][turn][t] - pow(shortestPathVarianceNRAVG[mc][s][e][turb][turn][t], 2), .5);

                shortestPathVarianceRRAVG[mc][s][e][turb][turn][t] = shortestPathVarianceRRAVGAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVarianceRRSTD[mc][s][e][turb][turn][t] = shortestPathVarianceRRSTDAdder[mc][s][e][turb][turn][t].sum() / Main.ITERATION;
                shortestPathVarianceRRSTD[mc][s][e][turb][turn][t] = pow(shortestPathVarianceRRSTD[mc][s][e][turb][turn][t] - pow(shortestPathVarianceRRAVG[mc][s][e][turb][turn][t], 2), .5);

              } // end for t
            } // end for turn
          } // end for turb
        }
      }
    }
  }

  // We KEEP the old SingleRun constructor for backward-compatibility:
  // (We cannot remove or omit lines.)
  class SingleRun {

    int mcIndex;
    int spanIndex;
    int enforcementIndex;
    // new fields:
    int turbIndex;
    int turnIndex;

    int span;
    double enforcement;
    double turbulenceRate;
    int turbulenceInterval;
    double turnoverRate;

    SingleRun(int mcIndex, int spanIndex, int enforcementIndex, int turbIndex, int turnIndex) {
      this.mcIndex = mcIndex;
      this.spanIndex = spanIndex;
      this.enforcementIndex = enforcementIndex;
      this.turbIndex = turbIndex;
      this.turnIndex = turnIndex;

      span = Main.SPAN[spanIndex];
      enforcement = Main.ENFORCEMENT[enforcementIndex];
      turbulenceRate = Main.TURBULENCE_RATE[turbIndex];
      turbulenceInterval = Main.TURBULENCE_INTERVAL[turbIndex];
      turnoverRate = Main.TURNOVER_RATE[turnIndex];

      run();
    }

    private void run() {
      Scenario src = new Scenario(mcIndex, span, enforcement, turbulenceRate, turnoverRate);
      Scenario nr = src.getClone(false, false);
      Scenario rr = src.getClone(true, true);
      src.doRewiring(Main.INFORMAL_INITIAL_NUM, 0);
      rr.doRewiring(Main.INFORMAL_INITIAL_NUM, 0);
      for (int t = 0; t < Main.TIME; t++) {
        performanceAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(src.performanceAvg);
        performanceSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(src.performanceAvg, 2));
        performanceNRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(nr.performanceAvg);
        performanceNRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(nr.performanceAvg, 2));
        performanceRRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(rr.performanceAvg);
        performanceRRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(rr.performanceAvg, 2));
        double performance12 = rr.performanceAvg - nr.performanceAvg;
        performance12AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(performance12);
        performance12STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(performance12, 2));
        double performance23 = src.performanceAvg - rr.performanceAvg;
        performance23AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(performance23);
        performance23STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(performance23, 2));
        double performance13 = src.performanceAvg - nr.performanceAvg;
        performance13AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(performance13);
        performance13STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(performance13, 2));

        clusteringWattsStrogatzAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(src.globalClusteringWattsStrogatz);
        clusteringWattsStrogatzSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(src.globalClusteringWattsStrogatz, 2));

        double clusteringWattsStrogatz12 = rr.globalClusteringWattsStrogatz - nr.globalClusteringWattsStrogatz;
        clusteringWattsStrogatz12AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(clusteringWattsStrogatz12);
        clusteringWattsStrogatz12STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(clusteringWattsStrogatz12, 2));

        double clusteringWattsStrogatz23 = src.globalClusteringWattsStrogatz - rr.globalClusteringWattsStrogatz;
        clusteringWattsStrogatz23AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(clusteringWattsStrogatz23);
        clusteringWattsStrogatz23STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(clusteringWattsStrogatz23, 2));

        double clusteringWattsStrogatz13 = src.globalClusteringWattsStrogatz - nr.globalClusteringWattsStrogatz;
        clusteringWattsStrogatz13AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(clusteringWattsStrogatz13);
        clusteringWattsStrogatz13STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(clusteringWattsStrogatz13, 2));

        clusteringWattsStrogatzNRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(nr.globalClusteringWattsStrogatz);
        clusteringWattsStrogatzNRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(nr.globalClusteringWattsStrogatz, 2));
        clusteringWattsStrogatzRRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(rr.globalClusteringWattsStrogatz);
        clusteringWattsStrogatzRRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(rr.globalClusteringWattsStrogatz, 2));

        centralizationAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(src.overallCentralization);
        centralizationSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(src.overallCentralization, 2));
        double centralization12 = rr.overallCentralization - nr.overallCentralization;
        centralization12AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(centralization12);
        centralization12STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(centralization12, 2));
        double centralization23 = src.overallCentralization - rr.overallCentralization;
        centralization23AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(centralization23);
        centralization23STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(centralization23, 2));
        double centralization13 = src.overallCentralization - nr.overallCentralization;
        centralization13AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(centralization13);
        centralization13STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(centralization13, 2));
        centralizationNRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(nr.overallCentralization);
        centralizationNRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(nr.overallCentralization, 2));
        centralizationRRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(rr.overallCentralization);
        centralizationRRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(rr.overallCentralization, 2));

        efficiencyAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(src.networkEfficiency);
        efficiencySTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(src.networkEfficiency, 2));
        double efficiency12 = rr.networkEfficiency - nr.networkEfficiency;
        efficiency12AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(efficiency12);
        efficiency12STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(efficiency12, 2));
        double efficiency23 = src.networkEfficiency - rr.networkEfficiency;
        efficiency23AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(efficiency23);
        efficiency23STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(efficiency23, 2));
        double efficiency13 = src.networkEfficiency - nr.networkEfficiency;
        efficiency13AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(efficiency13);
        efficiency13STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(efficiency13, 2));
        efficiencyNRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(nr.networkEfficiency);
        efficiencyNRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(nr.networkEfficiency, 2));
        efficiencyRRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(rr.networkEfficiency);
        efficiencyRRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(rr.networkEfficiency, 2));

        double sigmaSC = transform2Sigma(src.averagePathLength, src.globalClusteringWattsStrogatz);
        double sigmaRR = transform2Sigma(rr.averagePathLength, rr.globalClusteringWattsStrogatz);
        double sigmaNR = transform2SigmaNR(nr.averagePathLength, nr.globalClusteringWattsStrogatz);
        sigmaAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(sigmaSC);
        sigmaSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(sigmaSC, 2));
        sigmaRRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(sigmaRR);
        sigmaRRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(sigmaRR, 2));
        sigmaNRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(sigmaNR);
        sigmaNRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(sigmaNR, 2));
        double sigma12 = sigmaRR - sigmaNR;
        sigma12AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(sigma12);
        sigma12STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(sigma12, 2));
        double sigma23 = sigmaSC - sigmaRR;
        sigma23AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(sigma23);
        sigma23STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(sigma23, 2));
        double sigma13 = sigmaSC - sigmaNR;
        sigma13AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(sigma13);
        sigma13STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(sigma13, 2));

        double omegaSC = transform2Omega(src.averagePathLength, src.globalClusteringWattsStrogatz);
        double omegaRR = transform2Omega(rr.averagePathLength, rr.globalClusteringWattsStrogatz);
        double omegaNR = transform2OmegaNR(nr.averagePathLength, nr.globalClusteringWattsStrogatz);
        omegaAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(omegaSC);
        omegaSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(omegaSC, 2));
        omegaRRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(omegaRR);
        omegaRRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(omegaRR, 2));
        omegaNRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(omegaNR);
        omegaNRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(omegaNR, 2));
        double omega12 = omegaRR - omegaNR;
        omega12AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(omega12);
        omega12STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(omega12, 2));
        double omega23 = omegaSC - omegaRR;
        omega23AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(omega23);
        omega23STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(omega23, 2));
        double omega13 = omegaSC - omegaNR;
        omega13AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(omega13);
        omega13STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(omega13, 2));

        shortestPathVarianceAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(src.shortestPathVariance);
        shortestPathVarianceSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(src.shortestPathVariance, 2));
        double shortestPathVariance12 = rr.shortestPathVariance - nr.shortestPathVariance;
        shortestPathVariance12AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(shortestPathVariance12);
        shortestPathVariance12STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(shortestPathVariance12, 2));
        double shortestPathVariance23 = src.shortestPathVariance - rr.shortestPathVariance;
        shortestPathVariance23AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(shortestPathVariance23);
        shortestPathVariance23STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(shortestPathVariance23, 2));
        double shortestPathVariance13 = src.shortestPathVariance - nr.shortestPathVariance;
        shortestPathVariance13AVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(shortestPathVariance13);
        shortestPathVariance13STDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(shortestPathVariance13, 2));
        shortestPathVarianceNRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(nr.shortestPathVariance);
        shortestPathVarianceNRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(nr.shortestPathVariance, 2));
        shortestPathVarianceRRAVGAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(rr.shortestPathVariance);
        shortestPathVarianceRRSTDAdder[mcIndex][spanIndex][enforcementIndex][turbIndex][turnIndex][t].add(pow(rr.shortestPathVariance, 2));
        if (t % turbulenceInterval == 0) {
          src.doTurbulence();
          nr.doTurbulence();
          rr.doTurbulence();
        }
        src.stepForward(Main.INFORMAL_REWIRING_NUM);
        rr.stepForward(Main.INFORMAL_REWIRING_NUM);
        nr.stepForward();
      }
    }

    private double transform2Sigma(double averagePathLength, double clusteringCoefficient) {
      return (clusteringCoefficient / clusteringCoefficientRandom) / (averagePathLength / averagePathLengthRandom);
    }

    private double transform2Omega(double averagePathLength, double clusteringCoefficient) {
      return (averagePathLengthRandom / averagePathLength) - (clusteringCoefficient / clusteringCoefficientLattice);
    }

    private double transform2SigmaNR(double averagePathLength, double clusteringCoefficient) {
      return (clusteringCoefficient / clusteringCoefficientRandomNR) / (averagePathLength / averagePathLengthRandomNR);
    }

    private double transform2OmegaNR(double averagePathLength, double clusteringCoefficient) {
      // The minus part omitted for NR as in original code
      return (averagePathLengthRandomNR / averagePathLength);
    }
  }
}
