package HPBaseline;

import static org.apache.commons.math3.util.FastMath.pow;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.concurrent.TimeUnit;

public class Decomposition extends Computation {

  Decomposition() {
    super();
  }

  public void doExperiment() {
    pb = new ProgressBar("Full Experiment: Decomposition", Main.ITERATION);
    setResultSpace();
    runFullExperiment();
    averageFullExperiment();
  }

  private void setResultSpace() {
    performanceAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performanceSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance12AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance12STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance23AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance23STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance13AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance13STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    disagreementAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreementSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement12AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement12STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement23AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement23STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement13AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement13STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    dissimilarityAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilaritySTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity12AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity12STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity23AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity23STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity13AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity13STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    clusteringAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clusteringSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering12AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering12STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering23AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering23STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering13AVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering13STDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    satisfactionAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    satisfactionSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    rewiringAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    rewiringSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    sampleBetaAVGAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    sampleBetaSTDAtomic = new AtomicDouble[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    for (int h = 0; h < Main.LENGTH_H; h++) {
      for (int b = 0; b < Main.LENGTH_BETA; b++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
          for (int a = 0; a < Main.LENGTH_A; a++) {
              for (int t = 0; t < Main.TIME; t++) {
                performanceAVGAtomic[h][b][e][a][t] = new AtomicDouble();
                performanceSTDAtomic[h][b][e][a][t] = new AtomicDouble();
                performance12AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                performance12STDAtomic[h][b][e][a][t] = new AtomicDouble();
                performance23AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                performance23STDAtomic[h][b][e][a][t] = new AtomicDouble();
                performance13AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                performance13STDAtomic[h][b][e][a][t] = new AtomicDouble();

                disagreementAVGAtomic[h][b][e][a][t] = new AtomicDouble();
                disagreementSTDAtomic[h][b][e][a][t] = new AtomicDouble();
                disagreement12AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                disagreement12STDAtomic[h][b][e][a][t] = new AtomicDouble();
                disagreement23AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                disagreement23STDAtomic[h][b][e][a][t] = new AtomicDouble();
                disagreement13AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                disagreement13STDAtomic[h][b][e][a][t] = new AtomicDouble();

                dissimilarityAVGAtomic[h][b][e][a][t] = new AtomicDouble();
                dissimilaritySTDAtomic[h][b][e][a][t] = new AtomicDouble();
                dissimilarity12AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                dissimilarity12STDAtomic[h][b][e][a][t] = new AtomicDouble();
                dissimilarity23AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                dissimilarity23STDAtomic[h][b][e][a][t] = new AtomicDouble();
                dissimilarity13AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                dissimilarity13STDAtomic[h][b][e][a][t] = new AtomicDouble();

                clusteringAVGAtomic[h][b][e][a][t] = new AtomicDouble();
                clusteringSTDAtomic[h][b][e][a][t] = new AtomicDouble();
                clustering12AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                clustering12STDAtomic[h][b][e][a][t] = new AtomicDouble();
                clustering23AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                clustering23STDAtomic[h][b][e][a][t] = new AtomicDouble();
                clustering13AVGAtomic[h][b][e][a][t] = new AtomicDouble();
                clustering13STDAtomic[h][b][e][a][t] = new AtomicDouble();

                satisfactionAVGAtomic[h][b][e][a][t] = new AtomicDouble();
                satisfactionSTDAtomic[h][b][e][a][t] = new AtomicDouble();
                rewiringAVGAtomic[h][b][e][a][t] = new AtomicDouble();
                rewiringSTDAtomic[h][b][e][a][t] = new AtomicDouble();

                sampleBetaAVGAtomic[h][b][e][a][t] = new AtomicDouble();
                sampleBetaSTDAtomic[h][b][e][a][t] = new AtomicDouble();
              }
          }
        }
      }
    }
    performanceAVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performanceSTD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance12AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance12STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance23AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance23STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance13AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    performance13STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    disagreementAVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreementSTD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement12AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement12STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement23AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement23STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement13AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    disagreement13STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    dissimilarityAVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilaritySTD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity12AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity12STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity23AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity23STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity13AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    dissimilarity13STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    clusteringAVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clusteringSTD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering12AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering12STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering23AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering23STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering13AVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    clustering13STD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    satisfactionAVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    satisfactionSTD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    rewiringAVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    rewiringSTD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];

    sampleBetaAVG = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
    sampleBetaSTD = new double[Main.LENGTH_H][Main.LENGTH_BETA][Main.LENGTH_E][Main.LENGTH_A][Main.TIME];
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
        for (int b = 0; b < Main.LENGTH_BETA; b++) {
          for (int e = 0; e < Main.LENGTH_E; e++) {
            for (int a = 0; a < Main.LENGTH_A; a++) {
                new SingleBetaRun(h, b, e, a);
            }
          }
        }
      }
      pb.stepNext();
    }
  }

  private void averageFullExperiment() {
    for (int h = 0; h < Main.LENGTH_H; h++) {
      for (int b = 0; b < Main.LENGTH_BETA; b++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
          for (int a = 0; a < Main.LENGTH_A; a++) {
              for (int t = 0; t < Main.TIME; t++) {
                performanceAVG[h][b][e][a][t] =
                    performanceAVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                performanceSTD[h][b][e][a][t] =
                    performanceSTDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                performanceSTD[h][b][e][a][t] =
                    pow(performanceSTD[h][b][e][a][t] - pow(performanceAVG[h][b][e][a][t], 2), .5);

                performance12AVG[h][b][e][a][t] =
                    performance12AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                performance12STD[h][b][e][a][t] =
                    performance12STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                performance12STD[h][b][e][a][t] = pow(
                    performance12STD[h][b][e][a][t] - pow(performance12AVG[h][b][e][a][t], 2), .5);

                performance23AVG[h][b][e][a][t] =
                    performance23AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                performance23STD[h][b][e][a][t] =
                    performance23STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                performance23STD[h][b][e][a][t] = pow(
                    performance23STD[h][b][e][a][t] - pow(performance23AVG[h][b][e][a][t], 2), .5);

                performance13AVG[h][b][e][a][t] =
                    performance13AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                performance13STD[h][b][e][a][t] =
                    performance13STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                performance13STD[h][b][e][a][t] = pow(
                    performance13STD[h][b][e][a][t] - pow(performance13AVG[h][b][e][a][t], 2), .5);

                disagreementAVG[h][b][e][a][t] =
                    disagreementAVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                disagreementSTD[h][b][e][a][t] =
                    disagreementSTDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                disagreementSTD[h][b][e][a][t] = pow(
                    disagreementSTD[h][b][e][a][t] - pow(disagreementAVG[h][b][e][a][t], 2), .5);

                disagreement12AVG[h][b][e][a][t] =
                    disagreement12AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                disagreement12STD[h][b][e][a][t] =
                    disagreement12STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                disagreement12STD[h][b][e][a][t] = pow(
                    disagreement12STD[h][b][e][a][t] - pow(disagreement12AVG[h][b][e][a][t], 2), .5);

                disagreement23AVG[h][b][e][a][t] =
                    disagreement23AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                disagreement23STD[h][b][e][a][t] =
                    disagreement23STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                disagreement23STD[h][b][e][a][t] = pow(
                    disagreement23STD[h][b][e][a][t] - pow(disagreement23AVG[h][b][e][a][t], 2), .5);

                disagreement13AVG[h][b][e][a][t] =
                    disagreement13AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                disagreement13STD[h][b][e][a][t] =
                    disagreement13STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                disagreement13STD[h][b][e][a][t] = pow(
                    disagreement13STD[h][b][e][a][t] - pow(disagreement13AVG[h][b][e][a][t], 2), .5);

                dissimilarityAVG[h][b][e][a][t] =
                    dissimilarityAVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                dissimilaritySTD[h][b][e][a][t] =
                    dissimilaritySTDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                dissimilaritySTD[h][b][e][a][t] = pow(
                    dissimilaritySTD[h][b][e][a][t] - pow(dissimilarityAVG[h][b][e][a][t], 2), .5);

                dissimilarity12AVG[h][b][e][a][t] =
                    dissimilarity12AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                dissimilarity12STD[h][b][e][a][t] =
                    dissimilarity12STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                dissimilarity12STD[h][b][e][a][t] = pow(
                    dissimilarity12STD[h][b][e][a][t] - pow(dissimilarity12AVG[h][b][e][a][t], 2), .5);

                dissimilarity23AVG[h][b][e][a][t] =
                    dissimilarity23AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                dissimilarity23STD[h][b][e][a][t] =
                    dissimilarity23STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                dissimilarity23STD[h][b][e][a][t] = pow(
                    dissimilarity23STD[h][b][e][a][t] - pow(dissimilarity23AVG[h][b][e][a][t], 2), .5);

                dissimilarity13AVG[h][b][e][a][t] =
                    dissimilarity13AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                dissimilarity13STD[h][b][e][a][t] =
                    dissimilarity13STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                dissimilarity13STD[h][b][e][a][t] = pow(
                    dissimilarity13STD[h][b][e][a][t] - pow(dissimilarity13AVG[h][b][e][a][t], 2), .5);

                clusteringAVG[h][b][e][a][t] =
                    clusteringAVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                clusteringSTD[h][b][e][a][t] =
                    clusteringSTDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                clusteringSTD[h][b][e][a][t] = pow(
                    clusteringSTD[h][b][e][a][t] - pow(clusteringAVG[h][b][e][a][t], 2),
                    .5);

                clustering12AVG[h][b][e][a][t] =
                    clustering12AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                clustering12STD[h][b][e][a][t] =
                    clustering12STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                clustering12STD[h][b][e][a][t] = pow(
                    clustering12STD[h][b][e][a][t] - pow(clustering12AVG[h][b][e][a][t], 2),
                    .5);

                clustering23AVG[h][b][e][a][t] =
                    clustering23AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                clustering23STD[h][b][e][a][t] =
                    clustering23STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                clustering23STD[h][b][e][a][t] = pow(
                    clustering23STD[h][b][e][a][t] - pow(clustering23AVG[h][b][e][a][t], 2),
                    .5);

                clustering13AVG[h][b][e][a][t] =
                    clustering13AVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                clustering13STD[h][b][e][a][t] =
                    clustering13STDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                clustering13STD[h][b][e][a][t] = pow(
                    clustering13STD[h][b][e][a][t] - pow(clustering13AVG[h][b][e][a][t], 2),
                    .5);

                satisfactionAVG[h][b][e][a][t] =
                    satisfactionAVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                satisfactionSTD[h][b][e][a][t] =
                    satisfactionSTDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                satisfactionSTD[h][b][e][a][t] = pow(
                    satisfactionSTD[h][b][e][a][t] - pow(satisfactionAVG[h][b][e][a][t], 2),
                    .5);

                rewiringAVG[h][b][e][a][t] =
                    rewiringAVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                rewiringSTD[h][b][e][a][t] =
                    rewiringSTDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                rewiringSTD[h][b][e][a][t] = pow(
                    rewiringSTD[h][b][e][a][t] - pow(rewiringAVG[h][b][e][a][t], 2), .5);

                sampleBetaAVG[h][b][e][a][t] =
                    sampleBetaAVGAtomic[h][b][e][a][t].get() / Main.ITERATION;
                sampleBetaSTD[h][b][e][a][t] =
                    sampleBetaSTDAtomic[h][b][e][a][t].get() / Main.ITERATION;
                sampleBetaSTD[h][b][e][a][t] = pow(
                    sampleBetaSTD[h][b][e][a][t] - pow(sampleBetaAVG[h][b][e][a][t], 2),
                    .5);
              }
            }
        }
      }
    }
  }

  class SingleBetaRun {

    int hIndex;
    int betaIndex;
    int eIndex;
    int aIndex;
    int turbulenceScheduleIndex;

    double h;
    double beta;
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

    AtomicDouble[] sampleEtaAVGAtomicPart;
    AtomicDouble[] sampleEtaSTDAtomicPart;

    SingleBetaRun(int hIndex, int betaIndex, int eIndex, int aIndex) {
      this.hIndex = hIndex;
      this.betaIndex = betaIndex;
      this.eIndex = eIndex;
      this.aIndex = aIndex;

      h = Main.H[hIndex];
      beta = Main.BETA[betaIndex];
      enforcement = Main.E[eIndex];
      assortativity = Main.A[aIndex];

      initializeResultSpace();
      run();
    }

    private void initializeResultSpace() {
      performanceAVGAtomicPart = performanceAVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      performanceSTDAtomicPart = performanceSTDAtomic[hIndex][betaIndex][eIndex][aIndex];
      performance12AVGAtomicPart = performance12AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      performance12STDAtomicPart = performance12STDAtomic[hIndex][betaIndex][eIndex][aIndex];
      performance23AVGAtomicPart = performance23AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      performance23STDAtomicPart = performance23STDAtomic[hIndex][betaIndex][eIndex][aIndex];
      performance13AVGAtomicPart = performance13AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      performance13STDAtomicPart = performance13STDAtomic[hIndex][betaIndex][eIndex][aIndex];

      disagreementAVGAtomicPart = disagreementAVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      disagreementSTDAtomicPart = disagreementSTDAtomic[hIndex][betaIndex][eIndex][aIndex];
      disagreement12AVGAtomicPart = disagreement12AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      disagreement12STDAtomicPart = disagreement12STDAtomic[hIndex][betaIndex][eIndex][aIndex];
      disagreement23AVGAtomicPart = disagreement23AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      disagreement23STDAtomicPart = disagreement23STDAtomic[hIndex][betaIndex][eIndex][aIndex];
      disagreement13AVGAtomicPart = disagreement13AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      disagreement13STDAtomicPart = disagreement13STDAtomic[hIndex][betaIndex][eIndex][aIndex];

      dissimilarityAVGAtomicPart = dissimilarityAVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      dissimilaritySTDAtomicPart = dissimilaritySTDAtomic[hIndex][betaIndex][eIndex][aIndex];
      dissimilarity12AVGAtomicPart = dissimilarity12AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      dissimilarity12STDAtomicPart = dissimilarity12STDAtomic[hIndex][betaIndex][eIndex][aIndex];
      dissimilarity23AVGAtomicPart = dissimilarity23AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      dissimilarity23STDAtomicPart = dissimilarity23STDAtomic[hIndex][betaIndex][eIndex][aIndex];
      dissimilarity13AVGAtomicPart = dissimilarity13AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      dissimilarity13STDAtomicPart = dissimilarity13STDAtomic[hIndex][betaIndex][eIndex][aIndex];

      clusteringAVGAtomicPart = clusteringAVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      clusteringSTDAtomicPart = clusteringSTDAtomic[hIndex][betaIndex][eIndex][aIndex];
      clustering12AVGAtomicPart = clustering12AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      clustering12STDAtomicPart = clustering12STDAtomic[hIndex][betaIndex][eIndex][aIndex];
      clustering23AVGAtomicPart = clustering23AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      clustering23STDAtomicPart = clustering23STDAtomic[hIndex][betaIndex][eIndex][aIndex];
      clustering13AVGAtomicPart = clustering13AVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      clustering13STDAtomicPart = clustering13STDAtomic[hIndex][betaIndex][eIndex][aIndex];

      satisfactionAVGAtomicPart = satisfactionAVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      satisfactionSTDAtomicPart = satisfactionSTDAtomic[hIndex][betaIndex][eIndex][aIndex];

      rewiringAVGAtomicPart = rewiringAVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      rewiringSTDAtomicPart = rewiringSTDAtomic[hIndex][betaIndex][eIndex][aIndex];

      sampleEtaAVGAtomicPart = sampleBetaAVGAtomic[hIndex][betaIndex][eIndex][aIndex];
      sampleEtaSTDAtomicPart = sampleBetaSTDAtomic[hIndex][betaIndex][eIndex][aIndex];
    }

    private void run() {
      Scenario src = new Scenario(
          h, beta, enforcement, assortativity
      );
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

          sampleEtaAVGAtomicPart[t].addAndGet(src.beta);
          sampleEtaSTDAtomicPart[t].addAndGet(pow(src.beta, 2));
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
