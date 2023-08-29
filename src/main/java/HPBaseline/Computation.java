package HPBaseline;

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

class ADComputation {

  ExecutorService workStealingPool;
  RandomGenerator r;

  AtomicDouble[] performanceByBetaAVG;
  AtomicDouble[] performanceByBetaSTD;

  AtomicDouble[][][][][][] performanceAVGAtomic;
  AtomicDouble[][][][][][] performanceSTDAtomic;
  AtomicDouble[][][][][][] performance12AVGAtomic;
  AtomicDouble[][][][][][] performance12STDAtomic;
  AtomicDouble[][][][][][] performance23AVGAtomic;
  AtomicDouble[][][][][][] performance23STDAtomic;
  AtomicDouble[][][][][][] performance13AVGAtomic;
  AtomicDouble[][][][][][] performance13STDAtomic;

  AtomicDouble[][][][][][] disagreementAVGAtomic;
  AtomicDouble[][][][][][] disagreementSTDAtomic;
  AtomicDouble[][][][][][] disagreement12AVGAtomic;
  AtomicDouble[][][][][][] disagreement12STDAtomic;
  AtomicDouble[][][][][][] disagreement23AVGAtomic;
  AtomicDouble[][][][][][] disagreement23STDAtomic;
  AtomicDouble[][][][][][] disagreement13AVGAtomic;
  AtomicDouble[][][][][][] disagreement13STDAtomic;

  AtomicDouble[][][][][][] dissimilarityAVGAtomic;
  AtomicDouble[][][][][][] dissimilaritySTDAtomic;
  AtomicDouble[][][][][][] dissimilarity12AVGAtomic;
  AtomicDouble[][][][][][] dissimilarity12STDAtomic;
  AtomicDouble[][][][][][] dissimilarity23AVGAtomic;
  AtomicDouble[][][][][][] dissimilarity23STDAtomic;
  AtomicDouble[][][][][][] dissimilarity13AVGAtomic;
  AtomicDouble[][][][][][] dissimilarity13STDAtomic;

  AtomicDouble[][][][][][] clusteringAVGAtomic;
  AtomicDouble[][][][][][] clusteringSTDAtomic;
  AtomicDouble[][][][][][] clustering12AVGAtomic;
  AtomicDouble[][][][][][] clustering12STDAtomic;
  AtomicDouble[][][][][][] clustering23AVGAtomic;
  AtomicDouble[][][][][][] clustering23STDAtomic;
  AtomicDouble[][][][][][] clustering13AVGAtomic;
  AtomicDouble[][][][][][] clustering13STDAtomic;

  AtomicDouble[][][][][][] satisfactionAVGAtomic;
  AtomicDouble[][][][][][] satisfactionSTDAtomic;
  AtomicDouble[][][][][][] rewiringAVGAtomic;
  AtomicDouble[][][][][][] rewiringSTDAtomic;

  AtomicDouble[][][][][][] sampleBetaAVGAtomic;
  AtomicDouble[][][][][][] sampleBetaSTDAtomic;

  // Results in double arrays
  double[][][][][][] performanceAVG;
  double[][][][][][] performanceSTD;
  double[][][][][][] performance12AVG;
  double[][][][][][] performance12STD;
  double[][][][][][] performance23AVG;
  double[][][][][][] performance23STD;
  double[][][][][][] performance13AVG;
  double[][][][][][] performance13STD;

  double[][][][][][] disagreementAVG;
  double[][][][][][] disagreementSTD;
  double[][][][][][] disagreement12AVG;
  double[][][][][][] disagreement12STD;
  double[][][][][][] disagreement23AVG;
  double[][][][][][] disagreement23STD;
  double[][][][][][] disagreement13AVG;
  double[][][][][][] disagreement13STD;

  double[][][][][][] dissimilarityAVG;
  double[][][][][][] dissimilaritySTD;
  double[][][][][][] dissimilarity12AVG;
  double[][][][][][] dissimilarity12STD;
  double[][][][][][] dissimilarity23AVG;
  double[][][][][][] dissimilarity23STD;
  double[][][][][][] dissimilarity13AVG;
  double[][][][][][] dissimilarity13STD;

  double[][][][][][] clusteringAVG;
  double[][][][][][] clusteringSTD;
  double[][][][][][] clustering12AVG;
  double[][][][][][] clustering12STD;
  double[][][][][][] clustering23AVG;
  double[][][][][][] clustering23STD;
  double[][][][][][] clustering13AVG;
  double[][][][][][] clustering13STD;

  double[][][][][][] satisfactionAVG;
  double[][][][][][] satisfactionSTD;
  double[][][][][][] rewiringAVG;
  double[][][][][][] rewiringSTD;

  double[][][][][][] sampleBetaAVG;
  double[][][][][][] sampleBetaSTD;

  ProgressBar bb;
  ProgressBar pb;

  ADComputation() {
    workStealingPool = Executors.newWorkStealingPool();
    r = new MersenneTwister();
  }

  public void printNetwork() {
    try {
      Files.createDirectories(Paths.get(ADMain.PATH_CSV));
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int h = 0; h < ADMain.GRANULARITY_H; h++) {
      for (int b = 0; b < ADMain.GRANULARITY_BETA; b++) {
        for (int e = 0; e < ADMain.GRANULARITY_E; e++) {
          for (int a = 0; a < ADMain.GRANULARITY_A; a++) {
            double homophily = ADMain.H[h];
            double beta = ADMain.BETA[b];
            double enforcement = ADMain.E[e];
            double assortativity = ADMain.A[a];
            String fileName =
                ADMain.RUN_ID + "_" +
                    "h" + homophily +
                    "_b" + beta +
                    "_e" + enforcement +
                    "_a" + assortativity;
            ADScenario sc = new ADScenario(h, beta, enforcement, assortativity, 0, 0);
            sc.printCSV(ADMain.PATH_CSV + fileName + "_t0");
            for (int t = 0; t < ADMain.TIME; t++) {
              sc.stepForward();
            }
            sc.printCSV(ADMain.PATH_CSV + fileName + "_t" + ADMain.TIME);
            System.out.println("Network Printed: " + fileName);
          }
        }
      }
    }
  }

  public void setAndPrintOptimalBeta() {
    bb = new ProgressBar("Optimal Beta ", ADMain.ITERATION_BETA);
    //EtaSpace
    performanceByBetaAVG = new AtomicDouble[ADMain.GRANULARITY_BETA_CANDIDATE];
    performanceByBetaSTD = new AtomicDouble[ADMain.GRANULARITY_BETA_CANDIDATE];
    for (int b = 0; b < ADMain.GRANULARITY_BETA_CANDIDATE; b++) {
      performanceByBetaAVG[b] = new AtomicDouble();
      performanceByBetaSTD[b] = new AtomicDouble();
    }
    //Iteration
    for (int iteration = 0; iteration < ADMain.ITERATION_BETA; iteration++) {
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
    for (int b = 0; b < ADMain.GRANULARITY_BETA_CANDIDATE; b++) {
      double performanceNowAvg = performanceByBetaAVG[b].get() / (double) ADMain.ITERATION_BETA;
      double performanceNowStd = performanceByBetaSTD[b].get() / (double) ADMain.ITERATION_BETA;
      performanceNowStd = performanceNowStd - pow(performanceNowAvg, 2);
      performanceByBetaAVG[b].set(performanceNowAvg);
      performanceByBetaSTD[b].set(performanceNowStd);
      if (performanceNowAvg > optimalPerformance) {
        optimalPerformance = performanceNowAvg;
        ADMain.OPTIMAL_BETA = ADMain.BETA_CANDIDATE[b];
        ADMain.OPTIMAL_BETA_LEFT = 1D - ADMain.OPTIMAL_BETA;
      }
    }
    System.out.println("Optimal Beta Set at " + ADMain.OPTIMAL_BETA);
    System.out.println("              Among " + Arrays.toString(ADMain.BETA_CANDIDATE));
    System.out.println("              For   " + Arrays.toString(performanceByBetaAVG));
    System.out.println("              STD   " + Arrays.toString(performanceByBetaSTD));
  }

  class OptimalBetaWrapper implements Runnable {

    OptimalBetaWrapper() {
    }

    @Override
    public void run() {
      for (int b = 0; b < ADMain.GRANULARITY_BETA_CANDIDATE; b++) {
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
      run(ADMain.BETA_CANDIDATE[betaIndex]);
    }

    private void run(double beta) {
      ADScenario sc = new ADScenario(0, beta, 1, 0, 0, 0);
      for (int t = 0; t < ADMain.TIME; t++) {
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
    pb = new ProgressBar("Full Experiment: Computation", ADMain.ITERATION);
    setResultSpace();
    runFullExperiment();
    averageFullExperiment();
  }

  private void setResultSpace() {
    performanceAVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performanceSTDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance12AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance12STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance23AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance23STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance13AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance13STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    disagreementAVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreementSTDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement12AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement12STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement23AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement23STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement13AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement13STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    dissimilarityAVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilaritySTDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity12AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity12STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity23AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity23STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity13AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity13STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    clusteringAVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clusteringSTDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering12AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering12STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering23AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering23STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering13AVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering13STDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    satisfactionAVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    satisfactionSTDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    rewiringAVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    rewiringSTDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    sampleBetaAVGAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    sampleBetaSTDAtomic = new AtomicDouble[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    for (int h = 0; h < ADMain.GRANULARITY_H; h++) {
      for (int th = 0; th < ADMain.GRANULARITY_THETA; th++) {
        for (int e = 0; e < ADMain.GRANULARITY_E; e++) {
          for (int a = 0; a < ADMain.GRANULARITY_A; a++) {
            for (int tb = 0; tb < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; tb++) {
              for (int t = 0; t < ADMain.TIME; t++) {
                performanceAVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                performanceSTDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                performance12AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                performance12STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                performance23AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                performance23STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                performance13AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                performance13STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();

                disagreementAVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                disagreementSTDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                disagreement12AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                disagreement12STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                disagreement23AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                disagreement23STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                disagreement13AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                disagreement13STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();

                dissimilarityAVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                dissimilaritySTDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                dissimilarity12AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                dissimilarity12STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                dissimilarity23AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                dissimilarity23STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                dissimilarity13AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                dissimilarity13STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();

                clusteringAVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                clusteringSTDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                clustering12AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                clustering12STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                clustering23AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                clustering23STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                clustering13AVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                clustering13STDAtomic[h][th][e][a][tb][t] = new AtomicDouble();

                satisfactionAVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                satisfactionSTDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                rewiringAVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                rewiringSTDAtomic[h][th][e][a][tb][t] = new AtomicDouble();

                sampleBetaAVGAtomic[h][th][e][a][tb][t] = new AtomicDouble();
                sampleBetaSTDAtomic[h][th][e][a][tb][t] = new AtomicDouble();
              }
            }
          }
        }
      }
    }

    // Results in double arrays
    performanceAVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performanceSTD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance12AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance12STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance23AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance23STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance13AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    performance13STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    disagreementAVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreementSTD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement12AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement12STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement23AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement23STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement13AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    disagreement13STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    dissimilarityAVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilaritySTD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity12AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity12STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity23AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity23STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity13AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    dissimilarity13STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    clusteringAVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clusteringSTD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering12AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering12STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering23AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering23STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering13AVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    clustering13STD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    satisfactionAVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    satisfactionSTD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    rewiringAVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    rewiringSTD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];

    sampleBetaAVG = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
    sampleBetaSTD = new double[ADMain.GRANULARITY_H][ADMain.GRANULARITY_THETA][ADMain.GRANULARITY_E][ADMain.GRANULARITY_A][ADMain.GRANULARITY_TURBULENCE_SCHEDULE][ADMain.TIME];
  }

  private void runFullExperiment() {
    for (int iteration = 0; iteration < ADMain.ITERATION; iteration++) {
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
      for (int h = 0; h < ADMain.GRANULARITY_H; h++) {
        for (int th = 0; th < ADMain.GRANULARITY_THETA; th++) {
          for (int e = 0; e < ADMain.GRANULARITY_E; e++) {
            for (int a = 0; a < ADMain.GRANULARITY_A; a++) {
              for (int tb = 0; tb < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; tb++) {
                new ThetaRun(h, th, e, a, tb);
              }
            }
          }
        }
      }
      pb.stepNext();
    }
  }

  private void averageFullExperiment() {
    for (int h = 0; h < ADMain.GRANULARITY_H; h++) {
      for (int th = 0; th < ADMain.GRANULARITY_THETA; th++) {
        for (int e = 0; e < ADMain.GRANULARITY_E; e++) {
          for (int a = 0; a < ADMain.GRANULARITY_A; a++) {
            for (int tb = 0; tb < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; tb++) {
              for (int t = 0; t < ADMain.TIME; t++) {
                performanceAVG[h][th][e][a][tb][t] =
                    performanceAVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                performanceSTD[h][th][e][a][tb][t] =
                    performanceSTDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                performanceSTD[h][th][e][a][tb][t] = pow(
                    performanceSTD[h][th][e][a][tb][t] - pow(performanceAVG[h][th][e][a][tb][t], 2),
                    .5);

                performance12AVG[h][th][e][a][tb][t] =
                    performance12AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                performance12STD[h][th][e][a][tb][t] =
                    performance12STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                performance12STD[h][th][e][a][tb][t] = pow(
                    performance12STD[h][th][e][a][tb][t] - pow(performance12AVG[h][th][e][a][tb][t],
                        2),
                    .5);

                performance23AVG[h][th][e][a][tb][t] =
                    performance23AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                performance23STD[h][th][e][a][tb][t] =
                    performance23STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                performance23STD[h][th][e][a][tb][t] = pow(
                    performance23STD[h][th][e][a][tb][t] - pow(performance23AVG[h][th][e][a][tb][t],
                        2),
                    .5);

                performance13AVG[h][th][e][a][tb][t] =
                    performance13AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                performance13STD[h][th][e][a][tb][t] =
                    performance13STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                performance13STD[h][th][e][a][tb][t] = pow(
                    performance13STD[h][th][e][a][tb][t] - pow(performance13AVG[h][th][e][a][tb][t],
                        2),
                    .5);

                disagreementAVG[h][th][e][a][tb][t] =
                    disagreementAVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                disagreementSTD[h][th][e][a][tb][t] =
                    disagreementSTDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                disagreementSTD[h][th][e][a][tb][t] = pow(
                    disagreementSTD[h][th][e][a][tb][t] - pow(disagreementAVG[h][th][e][a][tb][t],
                        2), .5);

                disagreement12AVG[h][th][e][a][tb][t] =
                    disagreement12AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                disagreement12STD[h][th][e][a][tb][t] =
                    disagreement12STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                disagreement12STD[h][th][e][a][tb][t] = pow(
                    disagreement12STD[h][th][e][a][tb][t] - pow(
                        disagreement12AVG[h][th][e][a][tb][t], 2),
                    .5);

                disagreement23AVG[h][th][e][a][tb][t] =
                    disagreement23AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                disagreement23STD[h][th][e][a][tb][t] =
                    disagreement23STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                disagreement23STD[h][th][e][a][tb][t] = pow(
                    disagreement23STD[h][th][e][a][tb][t] - pow(
                        disagreement23AVG[h][th][e][a][tb][t], 2),
                    .5);

                disagreement13AVG[h][th][e][a][tb][t] =
                    disagreement13AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                disagreement13STD[h][th][e][a][tb][t] =
                    disagreement13STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                disagreement13STD[h][th][e][a][tb][t] = pow(
                    disagreement13STD[h][th][e][a][tb][t] - pow(
                        disagreement13AVG[h][th][e][a][tb][t], 2),
                    .5);

                dissimilarityAVG[h][th][e][a][tb][t] =
                    dissimilarityAVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                dissimilaritySTD[h][th][e][a][tb][t] =
                    dissimilaritySTDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                dissimilaritySTD[h][th][e][a][tb][t] = pow(
                    dissimilaritySTD[h][th][e][a][tb][t] - pow(dissimilarityAVG[h][th][e][a][tb][t],
                        2),
                    .5);

                dissimilarity12AVG[h][th][e][a][tb][t] =
                    dissimilarity12AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                dissimilarity12STD[h][th][e][a][tb][t] =
                    dissimilarity12STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                dissimilarity12STD[h][th][e][a][tb][t] = pow(
                    dissimilarity12STD[h][th][e][a][tb][t] - pow(
                        dissimilarity12AVG[h][th][e][a][tb][t], 2),
                    .5);

                dissimilarity23AVG[h][th][e][a][tb][t] =
                    dissimilarity23AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                dissimilarity23STD[h][th][e][a][tb][t] =
                    dissimilarity23STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                dissimilarity23STD[h][th][e][a][tb][t] = pow(
                    dissimilarity23STD[h][th][e][a][tb][t] - pow(
                        dissimilarity23AVG[h][th][e][a][tb][t], 2),
                    .5);

                dissimilarity13AVG[h][th][e][a][tb][t] =
                    dissimilarity13AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                dissimilarity13STD[h][th][e][a][tb][t] =
                    dissimilarity13STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                dissimilarity13STD[h][th][e][a][tb][t] = pow(
                    dissimilarity13STD[h][th][e][a][tb][t] - pow(
                        dissimilarity13AVG[h][th][e][a][tb][t], 2),
                    .5);

                clusteringAVG[h][th][e][a][tb][t] =
                    clusteringAVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                clusteringSTD[h][th][e][a][tb][t] =
                    clusteringSTDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                clusteringSTD[h][th][e][a][tb][t] = pow(
                    clusteringSTD[h][th][e][a][tb][t] - pow(clusteringAVG[h][th][e][a][tb][t], 2),
                    .5);

                clustering12AVG[h][th][e][a][tb][t] =
                    clustering12AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                clustering12STD[h][th][e][a][tb][t] =
                    clustering12STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                clustering12STD[h][th][e][a][tb][t] = pow(
                    clustering12STD[h][th][e][a][tb][t] - pow(clustering12AVG[h][th][e][a][tb][t],
                        2), .5);

                clustering23AVG[h][th][e][a][tb][t] =
                    clustering23AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                clustering23STD[h][th][e][a][tb][t] =
                    clustering23STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                clustering23STD[h][th][e][a][tb][t] = pow(
                    clustering23STD[h][th][e][a][tb][t] - pow(clustering23AVG[h][th][e][a][tb][t],
                        2), .5);

                clustering13AVG[h][th][e][a][tb][t] =
                    clustering13AVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                clustering13STD[h][th][e][a][tb][t] =
                    clustering13STDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                clustering13STD[h][th][e][a][tb][t] = pow(
                    clustering13STD[h][th][e][a][tb][t] - pow(clustering13AVG[h][th][e][a][tb][t],
                        2), .5);

                satisfactionAVG[h][th][e][a][tb][t] =
                    satisfactionAVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                satisfactionSTD[h][th][e][a][tb][t] =
                    satisfactionSTDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                satisfactionSTD[h][th][e][a][tb][t] = pow(
                    satisfactionSTD[h][th][e][a][tb][t] - pow(satisfactionAVG[h][th][e][a][tb][t],
                        2), .5);

                rewiringAVG[h][th][e][a][tb][t] =
                    rewiringAVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                rewiringSTD[h][th][e][a][tb][t] =
                    rewiringSTDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                rewiringSTD[h][th][e][a][tb][t] = pow(
                    rewiringSTD[h][th][e][a][tb][t] - pow(rewiringAVG[h][th][e][a][tb][t], 2), .5);

                sampleBetaAVG[h][th][e][a][tb][t] =
                    sampleBetaAVGAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                sampleBetaSTD[h][th][e][a][tb][t] =
                    sampleBetaSTDAtomic[h][th][e][a][tb][t].get() / ADMain.ITERATION;
                sampleBetaSTD[h][th][e][a][tb][t] = pow(
                    sampleBetaSTD[h][th][e][a][tb][t] - pow(sampleBetaAVG[h][th][e][a][tb][t], 2),
                    .5);
              }
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
    ADMain.TurbulenceSchedule turbulenceSchedule;

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

    ThetaRun(int hIndex, int thetaIndex, int eIndex, int aIndex, int turbulenceScheduleIndex) {
      this.hIndex = hIndex;
      this.thetaIndex = thetaIndex;
      this.eIndex = eIndex;
      this.turbulenceScheduleIndex = turbulenceScheduleIndex;

      h = ADMain.H[hIndex];
      theta = ADMain.THETA[thetaIndex];
      enforcement = ADMain.E[eIndex];
      assortativity = ADMain.A[aIndex];
      turbulenceSchedule = ADMain.TURBULENCE_SCHEDULE[turbulenceScheduleIndex];

      initializeResultSpace();
      sampleBeta();
      run();
    }

    private void initializeResultSpace() {
      performanceAVGAtomicPart = performanceAVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      performanceSTDAtomicPart = performanceSTDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      performance12AVGAtomicPart = performance12AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      performance12STDAtomicPart = performance12STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      performance23AVGAtomicPart = performance23AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      performance23STDAtomicPart = performance23STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      performance13AVGAtomicPart = performance13AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      performance13STDAtomicPart = performance13STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];

      disagreementAVGAtomicPart = disagreementAVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      disagreementSTDAtomicPart = disagreementSTDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      disagreement12AVGAtomicPart = disagreement12AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      disagreement12STDAtomicPart = disagreement12STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      disagreement23AVGAtomicPart = disagreement23AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      disagreement23STDAtomicPart = disagreement23STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      disagreement13AVGAtomicPart = disagreement13AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      disagreement13STDAtomicPart = disagreement13STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];

      dissimilarityAVGAtomicPart = dissimilarityAVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      dissimilaritySTDAtomicPart = dissimilaritySTDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      dissimilarity12AVGAtomicPart = dissimilarity12AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      dissimilarity12STDAtomicPart = dissimilarity12STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      dissimilarity23AVGAtomicPart = dissimilarity23AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      dissimilarity23STDAtomicPart = dissimilarity23STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      dissimilarity13AVGAtomicPart = dissimilarity13AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      dissimilarity13STDAtomicPart = dissimilarity13STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];

      clusteringAVGAtomicPart = clusteringAVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      clusteringSTDAtomicPart = clusteringSTDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      clustering12AVGAtomicPart = clustering12AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      clustering12STDAtomicPart = clustering12STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      clustering23AVGAtomicPart = clustering23AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      clustering23STDAtomicPart = clustering23STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      clustering13AVGAtomicPart = clustering13AVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      clustering13STDAtomicPart = clustering13STDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];

      satisfactionAVGAtomicPart = satisfactionAVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      satisfactionSTDAtomicPart = satisfactionSTDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];

      rewiringAVGAtomicPart = rewiringAVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      rewiringSTDAtomicPart = rewiringSTDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];

      sampleBetaAVGAtomicPart = sampleBetaAVGAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
      sampleBetaSTDAtomicPart = sampleBetaSTDAtomic[hIndex][thetaIndex][eIndex][aIndex][turbulenceScheduleIndex];
    }

    private double sampleBeta() {
      double beta;
      if (r.nextBoolean()) {
        //Left to the beta^*
        beta = new BetaDistribution(1, theta).sample()
            * ADMain.OPTIMAL_BETA; // 220130 Fix: /Opt to *Opt
      } else {
        //Right from the beta^*
        beta = ADMain.OPTIMAL_BETA + new BetaDistribution(theta, 1).sample()
            * ADMain.OPTIMAL_BETA_LEFT; // 220130 Fix: /Opt to *Opt
      }
      return beta;
//      double b = 1D/tan(theta*PI/2D)-(3* MTMain.OPTIMAL_BETA - pow(MTMain.OPTIMAL_BETA *(MTMain.OPTIMAL_BETA + 8), .5))/(2* MTMain.OPTIMAL_BETA);
//      double a = (MTMain.OPTIMAL_BETA +2* MTMain.OPTIMAL_BETA *b+pow(MTMain.OPTIMAL_BETA * MTMain.OPTIMAL_BETA +4* MTMain.OPTIMAL_BETA *b*b-2* MTMain.OPTIMAL_BETA +1, .5)-1)/(2*(1- MTMain.OPTIMAL_BETA));
//      return pow(new BetaDistribution(a, b).sample(),2); //210218
    }

    private void run() {
      ADScenario src = new ADScenario(
          h, sampleBeta(), enforcement, assortativity,
          turbulenceSchedule.turbulenceStrengthValue,
          turbulenceSchedule.turbulenceStrengthDependence
      );
      ADScenario nr = src.getClone(false, false);
      ADScenario rr = src.getClone(true, true);

      int nextTurbulenceAtIndex = 0;
      int nextTurbulenceAt = turbulenceSchedule.turbulenceAt[nextTurbulenceAtIndex];
      for (int t = 0; t < ADMain.TIME; t++) {
        if (t == nextTurbulenceAt) {
          src.shakeReality();
          nr.copyRealityOf(src);
          rr.copyRealityOf(src);
          if (nextTurbulenceAtIndex < turbulenceSchedule.nTurbulence) {
            nextTurbulenceAt = turbulenceSchedule.turbulenceAt[nextTurbulenceAtIndex++];
          } else {
            nextTurbulenceAtIndex = -1;
            nextTurbulenceAt = -1;
          }
        }

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
