package HPDegree;

import static org.apache.commons.math3.util.FastMath.pow;

import com.google.common.util.concurrent.AtomicDouble;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.math3.distribution.BetaDistribution;
import org.apache.commons.math3.random.MersenneTwister;
import org.apache.commons.math3.random.RandomGenerator;

class Computation {

  ExecutorService workStealingPool;
  RandomGenerator r;

  AtomicDouble[] performanceByBetaAVG;
  AtomicDouble[] performanceByBetaSTD;

  AtomicDouble[][][][][] performanceAVGAtomic;
  AtomicDouble[][][][][] performanceSTDAtomic;
  AtomicDouble[][][][][] performance12AVGAtomic;
  AtomicDouble[][][][][] performance12STDAtomic;
  AtomicDouble[][][][][] performance23AVGAtomic;
  AtomicDouble[][][][][] performance23STDAtomic;
  AtomicDouble[][][][][] performance13AVGAtomic;
  AtomicDouble[][][][][] performance13STDAtomic;

  AtomicDouble[][][][][] disagreementAVGAtomic;
  AtomicDouble[][][][][] disagreementSTDAtomic;
  AtomicDouble[][][][][] disagreement12AVGAtomic;
  AtomicDouble[][][][][] disagreement12STDAtomic;
  AtomicDouble[][][][][] disagreement23AVGAtomic;
  AtomicDouble[][][][][] disagreement23STDAtomic;
  AtomicDouble[][][][][] disagreement13AVGAtomic;
  AtomicDouble[][][][][] disagreement13STDAtomic;

  AtomicDouble[][][][][] dissimilarityAVGAtomic;
  AtomicDouble[][][][][] dissimilaritySTDAtomic;
  AtomicDouble[][][][][] dissimilarity12AVGAtomic;
  AtomicDouble[][][][][] dissimilarity12STDAtomic;
  AtomicDouble[][][][][] dissimilarity23AVGAtomic;
  AtomicDouble[][][][][] dissimilarity23STDAtomic;
  AtomicDouble[][][][][] dissimilarity13AVGAtomic;
  AtomicDouble[][][][][] dissimilarity13STDAtomic;

  AtomicDouble[][][][][] clusteringAVGAtomic;
  AtomicDouble[][][][][] clusteringSTDAtomic;
  AtomicDouble[][][][][] clustering12AVGAtomic;
  AtomicDouble[][][][][] clustering12STDAtomic;
  AtomicDouble[][][][][] clustering23AVGAtomic;
  AtomicDouble[][][][][] clustering23STDAtomic;
  AtomicDouble[][][][][] clustering13AVGAtomic;
  AtomicDouble[][][][][] clustering13STDAtomic;

  AtomicDouble[][][][][] satisfactionAVGAtomic;
  AtomicDouble[][][][][] satisfactionSTDAtomic;
  AtomicDouble[][][][][] rewiringAVGAtomic;
  AtomicDouble[][][][][] rewiringSTDAtomic;

  AtomicDouble[][][][][] sampleBetaAVGAtomic;
  AtomicDouble[][][][][] sampleBetaSTDAtomic;

  // Results in double arrays
  double[][][][][] performanceAVG;
  double[][][][][] performanceSTD;
  double[][][][][] performance12AVG;
  double[][][][][] performance12STD;
  double[][][][][] performance23AVG;
  double[][][][][] performance23STD;
  double[][][][][] performance13AVG;
  double[][][][][] performance13STD;

  double[][][][][] disagreementAVG;
  double[][][][][] disagreementSTD;
  double[][][][][] disagreement12AVG;
  double[][][][][] disagreement12STD;
  double[][][][][] disagreement23AVG;
  double[][][][][] disagreement23STD;
  double[][][][][] disagreement13AVG;
  double[][][][][] disagreement13STD;

  double[][][][][] dissimilarityAVG;
  double[][][][][] dissimilaritySTD;
  double[][][][][] dissimilarity12AVG;
  double[][][][][] dissimilarity12STD;
  double[][][][][] dissimilarity23AVG;
  double[][][][][] dissimilarity23STD;
  double[][][][][] dissimilarity13AVG;
  double[][][][][] dissimilarity13STD;

  double[][][][][] clusteringAVG;
  double[][][][][] clusteringSTD;
  double[][][][][] clustering12AVG;
  double[][][][][] clustering12STD;
  double[][][][][] clustering23AVG;
  double[][][][][] clustering23STD;
  double[][][][][] clustering13AVG;
  double[][][][][] clustering13STD;

  double[][][][][] satisfactionAVG;
  double[][][][][] satisfactionSTD;
  double[][][][][] rewiringAVG;
  double[][][][][] rewiringSTD;

  double[][][][][] sampleBetaAVG;
  double[][][][][] sampleBetaSTD;

  ProgressBar bb;
  ProgressBar pb;

  Computation() {
    workStealingPool = Executors.newWorkStealingPool();
    r = new MersenneTwister();
  }

  public void printNetwork() {
    try {
      Files.createDirectories(Paths.get(Main.PATH_CSV));
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int h = 0; h < Main.LENGTH_H; h++) {
      for (int b = 0; b < Main.LENGTH_BETA; b++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
          for (int a = 0; a < Main.LENGTH_A; a++) {
            double homophily = Main.H[h];
            double beta = Main.BETA[b];
            double enforcement = Main.E[e];
            double assortativity = Main.A[a];
            String fileName = Main.RUN_ID + "_" + "h" + homophily + "_b" + beta + "_e" + enforcement + "_a" + assortativity;
            Scenario sc = new Scenario(h, beta, enforcement, assortativity);
            sc.printCSV(Main.PATH_CSV + fileName + "_t0");
            for (int t = 0; t < Main.TIME; t++) {
              sc.stepForward();
            }
            sc.printCSV(Main.PATH_CSV + fileName + "_t" + Main.TIME);
            System.out.println("Network Printed: " + fileName);
          }
        }
      }
    }
  }

  public void setAndPrintOptimalBeta() {
    bb = new ProgressBar("Optimal Beta ", Main.ITERATION_BETA);
    //EtaSpace
    performanceByBetaAVG = new AtomicDouble[Main.LENGTH_BETA_CANDIDATE];
    performanceByBetaSTD = new AtomicDouble[Main.LENGTH_BETA_CANDIDATE];
    for (int b = 0; b < Main.LENGTH_BETA_CANDIDATE; b++) {
      performanceByBetaAVG[b] = new AtomicDouble();
      performanceByBetaSTD[b] = new AtomicDouble();
    }
    //Iteration
    for (int iteration = 0; iteration < Main.ITERATION_BETA; iteration++) {
      OptimalBetaWrapper OptimalEtaWrap = new OptimalBetaWrapper();
      workStealingPool.execute(OptimalEtaWrap);
    }
    workStealingPool.shutdown();
    try {
      workStealingPool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
    } catch (InterruptedException e) {
      System.out.println(e);
    }
    //Choose Optimal
    double optimalPerformance = Double.MIN_VALUE;
    for (int b = 0; b < Main.LENGTH_BETA_CANDIDATE; b++) {
      double performanceNowAvg = performanceByBetaAVG[b].get() / (double) Main.ITERATION_BETA;
      double performanceNowStd = performanceByBetaSTD[b].get() / (double) Main.ITERATION_BETA;
      performanceNowStd = performanceNowStd - pow(performanceNowAvg, 2);
      performanceByBetaAVG[b].set(performanceNowAvg);
      performanceByBetaSTD[b].set(performanceNowStd);
      if (performanceNowAvg > optimalPerformance) {
        optimalPerformance = performanceNowAvg;
        Main.OPTIMAL_BETA = Main.BETA_CANDIDATE[b];
        Main.OPTIMAL_BETA_LEFT = 1D - Main.OPTIMAL_BETA;
      }
    }
    System.out.println("Optimal Beta Set at " + Main.OPTIMAL_BETA);
    System.out.println("              Among " + Arrays.toString(Main.BETA_CANDIDATE));
    System.out.println("              For   " + Arrays.toString(performanceByBetaAVG));
    System.out.println("              STD   " + Arrays.toString(performanceByBetaSTD));
  }

  class OptimalBetaWrapper implements Runnable {

    OptimalBetaWrapper() {
    }

    @Override
    public void run() {
      for (int b = 0; b < Main.LENGTH_BETA_CANDIDATE; b++) {
        new BetaRun(b);
      }
      bb.stepNext();
    }
  }

  class BetaRun {

    AtomicDouble focalPerformanceByBeta;
    AtomicDouble focalPerformanceByBetaSq;

    BetaRun(int betaIndex) {
      focalPerformanceByBeta = performanceByBetaAVG[betaIndex];
      focalPerformanceByBetaSq = performanceByBetaSTD[betaIndex];
      run(Main.BETA_CANDIDATE[betaIndex]);
    }

    private void run(double beta) {
      Scenario sc = new Scenario(0, beta, 1, 0);
      for (int t = 0; t < Main.TIME; t++) {
        sc.stepForward();
      }
      sc.setOutcome();
      synchronized (this) {
        focalPerformanceByBeta.addAndGet(sc.performanceAvg);
        focalPerformanceByBetaSq.addAndGet(pow(sc.performanceAvg, 2));
      }
    }

  }

  public void doExperiment() {
    pb = new ProgressBar(Main.RUN_ID + ": Composition", Main.ITERATION);
    setResultSpace();
    runFullExperiment();
    averageFullExperiment();
  }

  private void setResultSpace() {
    performanceAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performanceSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance12AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance12STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance23AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance23STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance13AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance13STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    disagreementAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreementSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement12AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement12STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement23AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement23STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement13AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement13STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    dissimilarityAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilaritySTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity12AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity12STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity23AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity23STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity13AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity13STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    clusteringAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clusteringSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering12AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering12STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering23AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering23STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering13AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering13STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    satisfactionAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    satisfactionSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    rewiringAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    rewiringSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    sampleBetaAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    sampleBetaSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    for (int h = 0; h < Main.LENGTH_H; h++) {
      for (int th = 0; th < Main.LENGTH_THETA; th++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
          for (int a = 0; a < Main.LENGTH_A; a++) {
            for (int t = 0; t < Main.TIME; t++) {
              performanceAVGAtomic[h][th][e][a][t] = new AtomicDouble();
              performanceSTDAtomic[h][th][e][a][t] = new AtomicDouble();
              performance12AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              performance12STDAtomic[h][th][e][a][t] = new AtomicDouble();
              performance23AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              performance23STDAtomic[h][th][e][a][t] = new AtomicDouble();
              performance13AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              performance13STDAtomic[h][th][e][a][t] = new AtomicDouble();

              disagreementAVGAtomic[h][th][e][a][t] = new AtomicDouble();
              disagreementSTDAtomic[h][th][e][a][t] = new AtomicDouble();
              disagreement12AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              disagreement12STDAtomic[h][th][e][a][t] = new AtomicDouble();
              disagreement23AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              disagreement23STDAtomic[h][th][e][a][t] = new AtomicDouble();
              disagreement13AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              disagreement13STDAtomic[h][th][e][a][t] = new AtomicDouble();

              dissimilarityAVGAtomic[h][th][e][a][t] = new AtomicDouble();
              dissimilaritySTDAtomic[h][th][e][a][t] = new AtomicDouble();
              dissimilarity12AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              dissimilarity12STDAtomic[h][th][e][a][t] = new AtomicDouble();
              dissimilarity23AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              dissimilarity23STDAtomic[h][th][e][a][t] = new AtomicDouble();
              dissimilarity13AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              dissimilarity13STDAtomic[h][th][e][a][t] = new AtomicDouble();

              clusteringAVGAtomic[h][th][e][a][t] = new AtomicDouble();
              clusteringSTDAtomic[h][th][e][a][t] = new AtomicDouble();
              clustering12AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              clustering12STDAtomic[h][th][e][a][t] = new AtomicDouble();
              clustering23AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              clustering23STDAtomic[h][th][e][a][t] = new AtomicDouble();
              clustering13AVGAtomic[h][th][e][a][t] = new AtomicDouble();
              clustering13STDAtomic[h][th][e][a][t] = new AtomicDouble();

              satisfactionAVGAtomic[h][th][e][a][t] = new AtomicDouble();
              satisfactionSTDAtomic[h][th][e][a][t] = new AtomicDouble();
              rewiringAVGAtomic[h][th][e][a][t] = new AtomicDouble();
              rewiringSTDAtomic[h][th][e][a][t] = new AtomicDouble();

              sampleBetaAVGAtomic[h][th][e][a][t] = new AtomicDouble();
              sampleBetaSTDAtomic[h][th][e][a][t] = new AtomicDouble();
            }
          }
        }
      }
    }

    // Results in double arrays
    performanceAVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performanceSTD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance12AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance12STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance23AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance23STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance13AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance13STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    disagreementAVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreementSTD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement12AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement12STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement23AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement23STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement13AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement13STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    dissimilarityAVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilaritySTD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity12AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity12STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity23AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity23STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity13AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity13STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    clusteringAVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clusteringSTD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering12AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering12STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering23AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering23STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering13AVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering13STD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    satisfactionAVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    satisfactionSTD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    rewiringAVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    rewiringSTD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    sampleBetaAVG = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    sampleBetaSTD = new double[Main.LENGTH_H][Main.LENGTH_THETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
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
      for (int h = 0; h < Main.LENGTH_H; h++) {
        for (int th = 0; th < Main.LENGTH_THETA; th++) {
          for (int e = 0; e < Main.LENGTH_E; e++) {
            for (int a = 0; a < Main.LENGTH_A; a++) {
              new ThetaRun(h, th, e, a);
            }
          }
        }
      }
      pb.stepNext();
    }
  }

  private void averageFullExperiment() {
    for (int h = 0; h < Main.LENGTH_H; h++) {
      for (int th = 0; th < Main.LENGTH_THETA; th++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
          for (int a = 0; a < Main.LENGTH_A; a++) {
            for (int t = 0; t < Main.TIME; t++) {
              performanceAVG[h][th][e][a][t] = performanceAVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              performanceSTD[h][th][e][a][t] = performanceSTDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              performanceSTD[h][th][e][a][t] = pow(performanceSTD[h][th][e][a][t] - pow(performanceAVG[h][th][e][a][t], 2), .5);

              performance12AVG[h][th][e][a][t] = performance12AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              performance12STD[h][th][e][a][t] = performance12STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              performance12STD[h][th][e][a][t] = pow(performance12STD[h][th][e][a][t] - pow(performance12AVG[h][th][e][a][t], 2), .5);

              performance23AVG[h][th][e][a][t] = performance23AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              performance23STD[h][th][e][a][t] = performance23STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              performance23STD[h][th][e][a][t] = pow(performance23STD[h][th][e][a][t] - pow(performance23AVG[h][th][e][a][t], 2), .5);

              performance13AVG[h][th][e][a][t] = performance13AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              performance13STD[h][th][e][a][t] = performance13STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              performance13STD[h][th][e][a][t] = pow(performance13STD[h][th][e][a][t] - pow(performance13AVG[h][th][e][a][t], 2), .5);

              disagreementAVG[h][th][e][a][t] = disagreementAVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              disagreementSTD[h][th][e][a][t] = disagreementSTDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              disagreementSTD[h][th][e][a][t] = pow(disagreementSTD[h][th][e][a][t] - pow(disagreementAVG[h][th][e][a][t], 2), .5);

              disagreement12AVG[h][th][e][a][t] = disagreement12AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              disagreement12STD[h][th][e][a][t] = disagreement12STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              disagreement12STD[h][th][e][a][t] = pow(disagreement12STD[h][th][e][a][t] - pow(disagreement12AVG[h][th][e][a][t], 2), .5);

              disagreement23AVG[h][th][e][a][t] = disagreement23AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              disagreement23STD[h][th][e][a][t] = disagreement23STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              disagreement23STD[h][th][e][a][t] = pow(disagreement23STD[h][th][e][a][t] - pow(disagreement23AVG[h][th][e][a][t], 2), .5);

              disagreement13AVG[h][th][e][a][t] = disagreement13AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              disagreement13STD[h][th][e][a][t] = disagreement13STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              disagreement13STD[h][th][e][a][t] = pow(disagreement13STD[h][th][e][a][t] - pow(disagreement13AVG[h][th][e][a][t], 2), .5);

              dissimilarityAVG[h][th][e][a][t] = dissimilarityAVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              dissimilaritySTD[h][th][e][a][t] = dissimilaritySTDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              dissimilaritySTD[h][th][e][a][t] = pow(dissimilaritySTD[h][th][e][a][t] - pow(dissimilarityAVG[h][th][e][a][t], 2), .5);

              dissimilarity12AVG[h][th][e][a][t] = dissimilarity12AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              dissimilarity12STD[h][th][e][a][t] = dissimilarity12STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              dissimilarity12STD[h][th][e][a][t] = pow(dissimilarity12STD[h][th][e][a][t] - pow(dissimilarity12AVG[h][th][e][a][t], 2), .5);

              dissimilarity23AVG[h][th][e][a][t] = dissimilarity23AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              dissimilarity23STD[h][th][e][a][t] = dissimilarity23STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              dissimilarity23STD[h][th][e][a][t] = pow(dissimilarity23STD[h][th][e][a][t] - pow(dissimilarity23AVG[h][th][e][a][t], 2), .5);

              dissimilarity13AVG[h][th][e][a][t] = dissimilarity13AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              dissimilarity13STD[h][th][e][a][t] = dissimilarity13STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              dissimilarity13STD[h][th][e][a][t] = pow(dissimilarity13STD[h][th][e][a][t] - pow(dissimilarity13AVG[h][th][e][a][t], 2), .5);

              clusteringAVG[h][th][e][a][t] = clusteringAVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              clusteringSTD[h][th][e][a][t] = clusteringSTDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              clusteringSTD[h][th][e][a][t] = pow(clusteringSTD[h][th][e][a][t] - pow(clusteringAVG[h][th][e][a][t], 2), .5);

              clustering12AVG[h][th][e][a][t] = clustering12AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              clustering12STD[h][th][e][a][t] = clustering12STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              clustering12STD[h][th][e][a][t] = pow(clustering12STD[h][th][e][a][t] - pow(clustering12AVG[h][th][e][a][t], 2), .5);

              clustering23AVG[h][th][e][a][t] = clustering23AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              clustering23STD[h][th][e][a][t] = clustering23STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              clustering23STD[h][th][e][a][t] = pow(clustering23STD[h][th][e][a][t] - pow(clustering23AVG[h][th][e][a][t], 2), .5);

              clustering13AVG[h][th][e][a][t] = clustering13AVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              clustering13STD[h][th][e][a][t] = clustering13STDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              clustering13STD[h][th][e][a][t] = pow(clustering13STD[h][th][e][a][t] - pow(clustering13AVG[h][th][e][a][t], 2), .5);

              satisfactionAVG[h][th][e][a][t] = satisfactionAVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              satisfactionSTD[h][th][e][a][t] = satisfactionSTDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              satisfactionSTD[h][th][e][a][t] = pow(satisfactionSTD[h][th][e][a][t] - pow(satisfactionAVG[h][th][e][a][t], 2), .5);

              rewiringAVG[h][th][e][a][t] = rewiringAVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              rewiringSTD[h][th][e][a][t] = rewiringSTDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              rewiringSTD[h][th][e][a][t] = pow(rewiringSTD[h][th][e][a][t] - pow(rewiringAVG[h][th][e][a][t], 2), .5);

              sampleBetaAVG[h][th][e][a][t] = sampleBetaAVGAtomic[h][th][e][a][t].get() / Main.ITERATION;
              sampleBetaSTD[h][th][e][a][t] = sampleBetaSTDAtomic[h][th][e][a][t].get() / Main.ITERATION;
              sampleBetaSTD[h][th][e][a][t] = pow(sampleBetaSTD[h][th][e][a][t] - pow(sampleBetaAVG[h][th][e][a][t], 2), .5);
            }
          }
        }
      }
    }
  }

  class ThetaRun {

    int hIndex;
    int thetaIndex;
    int eIndex;
    int aIndex;
    int turbulenceScheduleIndex;

    double h;
    double theta;
    double enforcement;
    double assortativity;

    AtomicDouble[] performanceAVGAtomicPart; // Pure result
    AtomicDouble[] performanceSTDAtomicPart; // Pure result
    AtomicDouble[] performance12AVGAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] performance12STDAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] performance23AVGAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] performance23STDAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] performance13AVGAtomicPart; // No rewiring v. homophily
    AtomicDouble[] performance13STDAtomicPart; // No rewiring v. homophily

    AtomicDouble[] disagreementAVGAtomicPart; // Pure result
    AtomicDouble[] disagreementSTDAtomicPart; // Pure result
    AtomicDouble[] disagreement12AVGAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] disagreement12STDAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] disagreement23AVGAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] disagreement23STDAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] disagreement13AVGAtomicPart; // No rewiring v. homophily
    AtomicDouble[] disagreement13STDAtomicPart; // No rewiring v. homophily

    AtomicDouble[] dissimilarityAVGAtomicPart; // Pure result
    AtomicDouble[] dissimilaritySTDAtomicPart; // Pure result
    AtomicDouble[] dissimilarity12AVGAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] dissimilarity12STDAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] dissimilarity23AVGAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] dissimilarity23STDAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] dissimilarity13AVGAtomicPart; // No rewiring v. homophily
    AtomicDouble[] dissimilarity13STDAtomicPart; // No rewiring v. homophily

    AtomicDouble[] clusteringAVGAtomicPart; // Pure result
    AtomicDouble[] clusteringSTDAtomicPart; // Pure result
    AtomicDouble[] clustering12AVGAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] clustering12STDAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] clustering23AVGAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] clustering23STDAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] clustering13AVGAtomicPart; // No rewiring v. homophily
    AtomicDouble[] clustering13STDAtomicPart; // No rewiring v. homophily

    AtomicDouble[] satisfactionAVGAtomicPart;
    AtomicDouble[] satisfactionSTDAtomicPart;

    AtomicDouble[] rewiringAVGAtomicPart;
    AtomicDouble[] rewiringSTDAtomicPart;

    AtomicDouble[] sampleBetaAVGAtomicPart;
    AtomicDouble[] sampleBetaSTDAtomicPart;

    ThetaRun(int hIndex, int thetaIndex, int eIndex, int aIndex) {
      this.hIndex = hIndex;
      this.thetaIndex = thetaIndex;
      this.eIndex = eIndex;

      h = Main.H[hIndex];
      theta = Main.THETA[thetaIndex];
      enforcement = Main.E[eIndex];
      assortativity = Main.A[aIndex];

      initializeResultSpace();
      sampleBeta();
      run();
    }

    private void initializeResultSpace() {
      performanceAVGAtomicPart = performanceAVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      performanceSTDAtomicPart = performanceSTDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      performance12AVGAtomicPart = performance12AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      performance12STDAtomicPart = performance12STDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      performance23AVGAtomicPart = performance23AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      performance23STDAtomicPart = performance23STDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      performance13AVGAtomicPart = performance13AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      performance13STDAtomicPart = performance13STDAtomic[hIndex][thetaIndex][eIndex][aIndex];

      disagreementAVGAtomicPart = disagreementAVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      disagreementSTDAtomicPart = disagreementSTDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      disagreement12AVGAtomicPart = disagreement12AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      disagreement12STDAtomicPart = disagreement12STDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      disagreement23AVGAtomicPart = disagreement23AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      disagreement23STDAtomicPart = disagreement23STDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      disagreement13AVGAtomicPart = disagreement13AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      disagreement13STDAtomicPart = disagreement13STDAtomic[hIndex][thetaIndex][eIndex][aIndex];

      dissimilarityAVGAtomicPart = dissimilarityAVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      dissimilaritySTDAtomicPart = dissimilaritySTDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      dissimilarity12AVGAtomicPart = dissimilarity12AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      dissimilarity12STDAtomicPart = dissimilarity12STDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      dissimilarity23AVGAtomicPart = dissimilarity23AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      dissimilarity23STDAtomicPart = dissimilarity23STDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      dissimilarity13AVGAtomicPart = dissimilarity13AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      dissimilarity13STDAtomicPart = dissimilarity13STDAtomic[hIndex][thetaIndex][eIndex][aIndex];

      clusteringAVGAtomicPart = clusteringAVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      clusteringSTDAtomicPart = clusteringSTDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      clustering12AVGAtomicPart = clustering12AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      clustering12STDAtomicPart = clustering12STDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      clustering23AVGAtomicPart = clustering23AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      clustering23STDAtomicPart = clustering23STDAtomic[hIndex][thetaIndex][eIndex][aIndex];
      clustering13AVGAtomicPart = clustering13AVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      clustering13STDAtomicPart = clustering13STDAtomic[hIndex][thetaIndex][eIndex][aIndex];

      satisfactionAVGAtomicPart = satisfactionAVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      satisfactionSTDAtomicPart = satisfactionSTDAtomic[hIndex][thetaIndex][eIndex][aIndex];

      rewiringAVGAtomicPart = rewiringAVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      rewiringSTDAtomicPart = rewiringSTDAtomic[hIndex][thetaIndex][eIndex][aIndex];

      sampleBetaAVGAtomicPart = sampleBetaAVGAtomic[hIndex][thetaIndex][eIndex][aIndex];
      sampleBetaSTDAtomicPart = sampleBetaSTDAtomic[hIndex][thetaIndex][eIndex][aIndex];
    }

    private double sampleBeta() {
      double beta;
      if (r.nextBoolean()) {
        //Left to the beta^*
        beta = new BetaDistribution(1, theta).sample() * Main.OPTIMAL_BETA; // 220130 Fix: /Opt to *Opt
      } else {
        //Right from the beta^*
        beta = Main.OPTIMAL_BETA + new BetaDistribution(theta, 1).sample() * Main.OPTIMAL_BETA_LEFT; // 220130 Fix: /Opt to *Opt
      }
      return beta;
    }

    private void run() {
      Scenario src = new Scenario(h, sampleBeta(), enforcement, assortativity);
      Scenario nr = src.getClone(false, false);
      Scenario rr = src.getClone(true, true);

      for (int t = 0; t < Main.TIME; t++) {
        synchronized (this) {
          performanceAVGAtomicPart[t].addAndGet(src.performanceAvg);
          performanceSTDAtomicPart[t].addAndGet(pow(src.performanceAvg, 2));
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

          dissimilarityAVGAtomicPart[t].addAndGet(src.dissimilarityAvg);
          dissimilaritySTDAtomicPart[t].addAndGet(pow(src.dissimilarityAvg, 2));
          double dissimilarity12 = rr.dissimilarityAvg - nr.dissimilarityAvg;
          dissimilarity12AVGAtomicPart[t].addAndGet(dissimilarity12);
          dissimilarity12STDAtomicPart[t].addAndGet(pow(dissimilarity12, 2));
          double dissimilarity23 = src.dissimilarityAvg - rr.dissimilarityAvg;
          dissimilarity23AVGAtomicPart[t].addAndGet(dissimilarity23);
          dissimilarity23STDAtomicPart[t].addAndGet(pow(dissimilarity23, 2));
          double dissimilarity13 = src.dissimilarityAvg - nr.dissimilarityAvg;
          dissimilarity13AVGAtomicPart[t].addAndGet(dissimilarity13);
          dissimilarity13STDAtomicPart[t].addAndGet(pow(dissimilarity13, 2));

          clusteringAVGAtomicPart[t].addAndGet(src.clusteringCoefficient);
          clusteringSTDAtomicPart[t].addAndGet(pow(src.clusteringCoefficient, 2));
          double clustering12 = rr.clusteringCoefficient - nr.clusteringCoefficient;
          clustering12AVGAtomicPart[t].addAndGet(clustering12);
          clustering12STDAtomicPart[t].addAndGet(pow(clustering12, 2));
          double clustering23 = src.clusteringCoefficient - rr.clusteringCoefficient;
          clustering23AVGAtomicPart[t].addAndGet(clustering23);
          clustering23STDAtomicPart[t].addAndGet(pow(clustering23, 2));
          double clustering13 = src.clusteringCoefficient - nr.clusteringCoefficient;
          clustering13AVGAtomicPart[t].addAndGet(clustering13);
          clustering13STDAtomicPart[t].addAndGet(pow(clustering13, 2));

          satisfactionAVGAtomicPart[t].addAndGet(src.satisfactionRate);
          satisfactionSTDAtomicPart[t].addAndGet(pow(src.satisfactionRate, 2));

          sampleBetaAVGAtomicPart[t].addAndGet(src.beta);
          sampleBetaSTDAtomicPart[t].addAndGet(pow(src.beta, 2));
        }
        src.stepForward();
        nr.stepForward();
        rr.stepForward(src.numRewiring);
        src.setOutcome();
        nr.setOutcome();
        rr.setOutcome();
        synchronized (this) {
          rewiringAVGAtomicPart[t].addAndGet(src.numRewiring);
          rewiringSTDAtomicPart[t].addAndGet(pow(src.numRewiring, 2));
        }
      }
    }
  }
}
