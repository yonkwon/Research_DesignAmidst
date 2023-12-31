package DABase;

import static org.apache.commons.math3.util.FastMath.pow;

import com.google.common.util.concurrent.AtomicDouble;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class Computation {

  ExecutorService workStealingPool;

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

  public void doExperiment() {
    pb = new ProgressBar(Main.RUN_ID + ": Computation", Main.ITERATION);
    setResultSpace();
    runFullExperiment();
    averageFullExperiment();
  }

  public void printNetwork() {
    try {
      Files.createDirectories(Paths.get(Main.PATH_CSV));
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int h = 0; h < Main.LENGTH_H; h++) {
        for (int b = 0; b < Main.LENGTH_SPAN; b++) {
          for (int e = 0; e < Main.LENGTH_E; e++) {
            double strength = Main.H[h];
            int span = Main.SPAN[b];
            double enforcement = Main.E[e];
            String fileName = Main.RUN_ID + "_" + "h" + strength + "_s" + span + "_e" + enforcement;
            Scenario sc = new Scenario(mc, strength, span, enforcement);
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

  private void setResultSpace() {
    performanceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    disagreementAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    dissimilarityAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilaritySTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    clusteringAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    satisfactionAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    satisfactionSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    rewiringAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    rewiringSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    sampleBetaAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    sampleBetaSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int h = 0; h < Main.LENGTH_H; h++) {
        for (int b = 0; b < Main.LENGTH_SPAN; b++) {
          for (int e = 0; e < Main.LENGTH_E; e++) {
            for (int t = 0; t < Main.TIME; t++) {
              performanceAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              performanceSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              performance12AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              performance12STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              performance23AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              performance23STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              performance13AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              performance13STDAtomic[mc][h][b][e][t] = new AtomicDouble();

              disagreementAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreementSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement12AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement12STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement23AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement23STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement13AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement13STDAtomic[mc][h][b][e][t] = new AtomicDouble();

              dissimilarityAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              dissimilaritySTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              dissimilarity12AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              dissimilarity12STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              dissimilarity23AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              dissimilarity23STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              dissimilarity13AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              dissimilarity13STDAtomic[mc][h][b][e][t] = new AtomicDouble();

              clusteringAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clusteringSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering12AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering12STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering23AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering23STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering13AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering13STDAtomic[mc][h][b][e][t] = new AtomicDouble();

              satisfactionAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              satisfactionSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              rewiringAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              rewiringSTDAtomic[mc][h][b][e][t] = new AtomicDouble();

              sampleBetaAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              sampleBetaSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
            }
          }
        }
      }
    }
    performanceAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    disagreementAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    dissimilarityAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilaritySTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    dissimilarity13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    clusteringAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    satisfactionAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    satisfactionSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    rewiringAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    rewiringSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    sampleBetaAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    sampleBetaSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
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
        for (int h = 0; h < Main.LENGTH_H; h++) {
          for (int b = 0; b < Main.LENGTH_SPAN; b++) {
            for (int e = 0; e < Main.LENGTH_E; e++) {
              new SingleRun(mc, h, b, e);
            }
          }
        }
      }
      pb.stepNext();
    }
  }

  private void averageFullExperiment() {
    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int h = 0; h < Main.LENGTH_H; h++) {
        for (int s = 0; s < Main.LENGTH_SPAN; s++) {
          for (int e = 0; e < Main.LENGTH_E; e++) {
            for (int t = 0; t < Main.TIME; t++) {
              performanceAVG[mc][h][s][e][t] = performanceAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performanceSTD[mc][h][s][e][t] = performanceSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performanceSTD[mc][h][s][e][t] = pow(performanceSTD[mc][h][s][e][t] - pow(performanceAVG[mc][h][s][e][t], 2), .5);

              performance12AVG[mc][h][s][e][t] = performance12AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performance12STD[mc][h][s][e][t] = performance12STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performance12STD[mc][h][s][e][t] = pow(performance12STD[mc][h][s][e][t] - pow(performance12AVG[mc][h][s][e][t], 2), .5);

              performance23AVG[mc][h][s][e][t] = performance23AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performance23STD[mc][h][s][e][t] = performance23STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performance23STD[mc][h][s][e][t] = pow(performance23STD[mc][h][s][e][t] - pow(performance23AVG[mc][h][s][e][t], 2), .5);

              performance13AVG[mc][h][s][e][t] = performance13AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performance13STD[mc][h][s][e][t] = performance13STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performance13STD[mc][h][s][e][t] = pow(performance13STD[mc][h][s][e][t] - pow(performance13AVG[mc][h][s][e][t], 2), .5);

              disagreementAVG[mc][h][s][e][t] = disagreementAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreementSTD[mc][h][s][e][t] = disagreementSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreementSTD[mc][h][s][e][t] = pow(disagreementSTD[mc][h][s][e][t] - pow(disagreementAVG[mc][h][s][e][t], 2), .5);

              disagreement12AVG[mc][h][s][e][t] = disagreement12AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreement12STD[mc][h][s][e][t] = disagreement12STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreement12STD[mc][h][s][e][t] = pow(disagreement12STD[mc][h][s][e][t] - pow(disagreement12AVG[mc][h][s][e][t], 2), .5);

              disagreement23AVG[mc][h][s][e][t] = disagreement23AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreement23STD[mc][h][s][e][t] = disagreement23STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreement23STD[mc][h][s][e][t] = pow(disagreement23STD[mc][h][s][e][t] - pow(disagreement23AVG[mc][h][s][e][t], 2), .5);

              disagreement13AVG[mc][h][s][e][t] = disagreement13AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreement13STD[mc][h][s][e][t] = disagreement13STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreement13STD[mc][h][s][e][t] = pow(disagreement13STD[mc][h][s][e][t] - pow(disagreement13AVG[mc][h][s][e][t], 2), .5);

              dissimilarityAVG[mc][h][s][e][t] = dissimilarityAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              dissimilaritySTD[mc][h][s][e][t] = dissimilaritySTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              dissimilaritySTD[mc][h][s][e][t] = pow(dissimilaritySTD[mc][h][s][e][t] - pow(dissimilarityAVG[mc][h][s][e][t], 2), .5);

              dissimilarity12AVG[mc][h][s][e][t] = dissimilarity12AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              dissimilarity12STD[mc][h][s][e][t] = dissimilarity12STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              dissimilarity12STD[mc][h][s][e][t] = pow(dissimilarity12STD[mc][h][s][e][t] - pow(dissimilarity12AVG[mc][h][s][e][t], 2), .5);

              dissimilarity23AVG[mc][h][s][e][t] = dissimilarity23AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              dissimilarity23STD[mc][h][s][e][t] = dissimilarity23STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              dissimilarity23STD[mc][h][s][e][t] = pow(dissimilarity23STD[mc][h][s][e][t] - pow(dissimilarity23AVG[mc][h][s][e][t], 2), .5);

              dissimilarity13AVG[mc][h][s][e][t] = dissimilarity13AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              dissimilarity13STD[mc][h][s][e][t] = dissimilarity13STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              dissimilarity13STD[mc][h][s][e][t] = pow(dissimilarity13STD[mc][h][s][e][t] - pow(dissimilarity13AVG[mc][h][s][e][t], 2), .5);

              clusteringAVG[mc][h][s][e][t] = clusteringAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clusteringSTD[mc][h][s][e][t] = clusteringSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clusteringSTD[mc][h][s][e][t] = pow(clusteringSTD[mc][h][s][e][t] - pow(clusteringAVG[mc][h][s][e][t], 2), .5);

              clustering12AVG[mc][h][s][e][t] = clustering12AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clustering12STD[mc][h][s][e][t] = clustering12STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clustering12STD[mc][h][s][e][t] = pow(clustering12STD[mc][h][s][e][t] - pow(clustering12AVG[mc][h][s][e][t], 2), .5);

              clustering23AVG[mc][h][s][e][t] = clustering23AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clustering23STD[mc][h][s][e][t] = clustering23STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clustering23STD[mc][h][s][e][t] = pow(clustering23STD[mc][h][s][e][t] - pow(clustering23AVG[mc][h][s][e][t], 2), .5);

              clustering13AVG[mc][h][s][e][t] = clustering13AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clustering13STD[mc][h][s][e][t] = clustering13STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clustering13STD[mc][h][s][e][t] = pow(clustering13STD[mc][h][s][e][t] - pow(clustering13AVG[mc][h][s][e][t], 2), .5);

              satisfactionAVG[mc][h][s][e][t] = satisfactionAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              satisfactionSTD[mc][h][s][e][t] = satisfactionSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              satisfactionSTD[mc][h][s][e][t] = pow(satisfactionSTD[mc][h][s][e][t] - pow(satisfactionAVG[mc][h][s][e][t], 2), .5);

              rewiringAVG[mc][h][s][e][t] = rewiringAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              rewiringSTD[mc][h][s][e][t] = rewiringSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              rewiringSTD[mc][h][s][e][t] = pow(rewiringSTD[mc][h][s][e][t] - pow(rewiringAVG[mc][h][s][e][t], 2), .5);

              sampleBetaAVG[mc][h][s][e][t] = sampleBetaAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              sampleBetaSTD[mc][h][s][e][t] = sampleBetaSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              sampleBetaSTD[mc][h][s][e][t] = pow(sampleBetaSTD[mc][h][s][e][t] - pow(sampleBetaAVG[mc][h][s][e][t], 2), .5);
            }
          }
        }
      }
    }
  }

  class SingleRun {

    int mcIndex;
    int hIndex;
    int spanIndex;
    int eIndex;

    double h;
    int span;
    double enforcement;

    AtomicDouble[] performanceAVGAtomicPart;
    AtomicDouble[] performanceSTDAtomicPart;
    AtomicDouble[] performance12AVGAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] performance12STDAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] performance23AVGAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] performance23STDAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] performance13AVGAtomicPart; // No rewiring v. homophily
    AtomicDouble[] performance13STDAtomicPart; // No rewiring v. homophily

    AtomicDouble[] disagreementAVGAtomicPart;
    AtomicDouble[] disagreementSTDAtomicPart;
    AtomicDouble[] disagreement12AVGAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] disagreement12STDAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] disagreement23AVGAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] disagreement23STDAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] disagreement13AVGAtomicPart; // No rewiring v. homophily
    AtomicDouble[] disagreement13STDAtomicPart; // No rewiring v. homophily

    AtomicDouble[] dissimilarityAVGAtomicPart;
    AtomicDouble[] dissimilaritySTDAtomicPart;
    AtomicDouble[] dissimilarity12AVGAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] dissimilarity12STDAtomicPart; // No rewiring v. random rewiring
    AtomicDouble[] dissimilarity23AVGAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] dissimilarity23STDAtomicPart; // Random rewiring v. homophily
    AtomicDouble[] dissimilarity13AVGAtomicPart; // No rewiring v. homophily
    AtomicDouble[] dissimilarity13STDAtomicPart; // No rewiring v. homophily

    AtomicDouble[] clusteringAVGAtomicPart;
    AtomicDouble[] clusteringSTDAtomicPart;
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

    AtomicDouble[] sampleEtaAVGAtomicPart;
    AtomicDouble[] sampleEtaSTDAtomicPart;

    SingleRun(int mcIndex, int hIndex, int spanIndex, int eIndex) {
      this.mcIndex = mcIndex;
      this.hIndex = hIndex;
      this.spanIndex = spanIndex;
      this.eIndex = eIndex;

      h = Main.H[hIndex];
      span = Main.SPAN[spanIndex];
      enforcement = Main.E[eIndex];

      initializeResultSpace();
      run();
    }

    private void initializeResultSpace() {
      performanceAVGAtomicPart = performanceAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performanceSTDAtomicPart = performanceSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performance12AVGAtomicPart = performance12AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performance12STDAtomicPart = performance12STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performance23AVGAtomicPart = performance23AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performance23STDAtomicPart = performance23STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performance13AVGAtomicPart = performance13AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performance13STDAtomicPart = performance13STDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      disagreementAVGAtomicPart = disagreementAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreementSTDAtomicPart = disagreementSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement12AVGAtomicPart = disagreement12AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement12STDAtomicPart = disagreement12STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement23AVGAtomicPart = disagreement23AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement23STDAtomicPart = disagreement23STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement13AVGAtomicPart = disagreement13AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement13STDAtomicPart = disagreement13STDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      dissimilarityAVGAtomicPart = dissimilarityAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      dissimilaritySTDAtomicPart = dissimilaritySTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      dissimilarity12AVGAtomicPart = dissimilarity12AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      dissimilarity12STDAtomicPart = dissimilarity12STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      dissimilarity23AVGAtomicPart = dissimilarity23AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      dissimilarity23STDAtomicPart = dissimilarity23STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      dissimilarity13AVGAtomicPart = dissimilarity13AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      dissimilarity13STDAtomicPart = dissimilarity13STDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      clusteringAVGAtomicPart = clusteringAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clusteringSTDAtomicPart = clusteringSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering12AVGAtomicPart = clustering12AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering12STDAtomicPart = clustering12STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering23AVGAtomicPart = clustering23AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering23STDAtomicPart = clustering23STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering13AVGAtomicPart = clustering13AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering13STDAtomicPart = clustering13STDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      satisfactionAVGAtomicPart = satisfactionAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      satisfactionSTDAtomicPart = satisfactionSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      rewiringAVGAtomicPart = rewiringAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      rewiringSTDAtomicPart = rewiringSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      sampleEtaAVGAtomicPart = sampleBetaAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      sampleEtaSTDAtomicPart = sampleBetaSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
    }

    private void run() {
      Scenario src = new Scenario(mcIndex, h, span, enforcement);
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
