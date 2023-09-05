package HPClosure;

import static org.apache.commons.math3.util.FastMath.pow;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Decomposition extends Computation {

  Decomposition() {
    super();
  }

  public void doExperiment() {
    pb = new ProgressBar(Main.RUN_ID + ": Decomposition", Main.ITERATION);
    setResultSpace();
    runFullExperiment();
    averageFullExperiment();
  }

  private void setResultSpace() {
    performanceAVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performanceSTDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance12AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance12STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance23AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance23STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance13AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance13STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    disagreementAVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreementSTDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement12AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement12STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement23AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement23STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement13AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement13STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    clusteringAVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clusteringSTDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering12AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering12STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering23AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering23STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering13AVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering13STDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    satisfactionAVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    satisfactionSTDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    rewiringAVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    rewiringSTDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    sampleBetaAVGAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    sampleBetaSTDAtomic = new AtomicDouble[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    for (int h = 0; h < Main.LENGTH_C; h++) {
      for (int b = 0; b < Main.LENGTH_BETA; b++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
            for (int t = 0; t < Main.TIME; t++) {
              performanceAVGAtomic[h][b][e][t] = new AtomicDouble();
              performanceSTDAtomic[h][b][e][t] = new AtomicDouble();
              performance12AVGAtomic[h][b][e][t] = new AtomicDouble();
              performance12STDAtomic[h][b][e][t] = new AtomicDouble();
              performance23AVGAtomic[h][b][e][t] = new AtomicDouble();
              performance23STDAtomic[h][b][e][t] = new AtomicDouble();
              performance13AVGAtomic[h][b][e][t] = new AtomicDouble();
              performance13STDAtomic[h][b][e][t] = new AtomicDouble();

              disagreementAVGAtomic[h][b][e][t] = new AtomicDouble();
              disagreementSTDAtomic[h][b][e][t] = new AtomicDouble();
              disagreement12AVGAtomic[h][b][e][t] = new AtomicDouble();
              disagreement12STDAtomic[h][b][e][t] = new AtomicDouble();
              disagreement23AVGAtomic[h][b][e][t] = new AtomicDouble();
              disagreement23STDAtomic[h][b][e][t] = new AtomicDouble();
              disagreement13AVGAtomic[h][b][e][t] = new AtomicDouble();
              disagreement13STDAtomic[h][b][e][t] = new AtomicDouble();

              clusteringAVGAtomic[h][b][e][t] = new AtomicDouble();
              clusteringSTDAtomic[h][b][e][t] = new AtomicDouble();
              clustering12AVGAtomic[h][b][e][t] = new AtomicDouble();
              clustering12STDAtomic[h][b][e][t] = new AtomicDouble();
              clustering23AVGAtomic[h][b][e][t] = new AtomicDouble();
              clustering23STDAtomic[h][b][e][t] = new AtomicDouble();
              clustering13AVGAtomic[h][b][e][t] = new AtomicDouble();
              clustering13STDAtomic[h][b][e][t] = new AtomicDouble();

              satisfactionAVGAtomic[h][b][e][t] = new AtomicDouble();
              satisfactionSTDAtomic[h][b][e][t] = new AtomicDouble();
              rewiringAVGAtomic[h][b][e][t] = new AtomicDouble();
              rewiringSTDAtomic[h][b][e][t] = new AtomicDouble();

              sampleBetaAVGAtomic[h][b][e][t] = new AtomicDouble();
              sampleBetaSTDAtomic[h][b][e][t] = new AtomicDouble();
            }
          }
      }
    }
    performanceAVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performanceSTD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance12AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance12STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance23AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance23STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance13AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    performance13STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    disagreementAVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreementSTD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement12AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement12STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement23AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement23STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement13AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    disagreement13STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    clusteringAVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clusteringSTD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering12AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering12STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering23AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering23STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering13AVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    clustering13STD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    satisfactionAVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    satisfactionSTD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    rewiringAVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    rewiringSTD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];

    sampleBetaAVG = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
    sampleBetaSTD = new double[Main.LENGTH_C][Main.LENGTH_BETA][Main.LENGTH_E][Main.TIME];
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
      for (int h = 0; h < Main.LENGTH_C; h++) {
        for (int b = 0; b < Main.LENGTH_BETA; b++) {
          for (int e = 0; e < Main.LENGTH_E; e++) {
              new SingleBetaRun(h, b, e);
            }
        }
      }
      pb.stepNext();
    }
  }

  private void averageFullExperiment() {
    for (int h = 0; h < Main.LENGTH_C; h++) {
      for (int b = 0; b < Main.LENGTH_BETA; b++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
            for (int t = 0; t < Main.TIME; t++) {
              performanceAVG[h][b][e][t] = performanceAVGAtomic[h][b][e][t].get() / Main.ITERATION;
              performanceSTD[h][b][e][t] = performanceSTDAtomic[h][b][e][t].get() / Main.ITERATION;
              performanceSTD[h][b][e][t] = pow(performanceSTD[h][b][e][t] - pow(performanceAVG[h][b][e][t], 2), .5);

              performance12AVG[h][b][e][t] = performance12AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              performance12STD[h][b][e][t] = performance12STDAtomic[h][b][e][t].get() / Main.ITERATION;
              performance12STD[h][b][e][t] = pow(performance12STD[h][b][e][t] - pow(performance12AVG[h][b][e][t], 2), .5);

              performance23AVG[h][b][e][t] = performance23AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              performance23STD[h][b][e][t] = performance23STDAtomic[h][b][e][t].get() / Main.ITERATION;
              performance23STD[h][b][e][t] = pow(performance23STD[h][b][e][t] - pow(performance23AVG[h][b][e][t], 2), .5);

              performance13AVG[h][b][e][t] = performance13AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              performance13STD[h][b][e][t] = performance13STDAtomic[h][b][e][t].get() / Main.ITERATION;
              performance13STD[h][b][e][t] = pow(performance13STD[h][b][e][t] - pow(performance13AVG[h][b][e][t], 2), .5);

              disagreementAVG[h][b][e][t] = disagreementAVGAtomic[h][b][e][t].get() / Main.ITERATION;
              disagreementSTD[h][b][e][t] = disagreementSTDAtomic[h][b][e][t].get() / Main.ITERATION;
              disagreementSTD[h][b][e][t] = pow(disagreementSTD[h][b][e][t] - pow(disagreementAVG[h][b][e][t], 2), .5);

              disagreement12AVG[h][b][e][t] = disagreement12AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              disagreement12STD[h][b][e][t] = disagreement12STDAtomic[h][b][e][t].get() / Main.ITERATION;
              disagreement12STD[h][b][e][t] = pow(disagreement12STD[h][b][e][t] - pow(disagreement12AVG[h][b][e][t], 2), .5);

              disagreement23AVG[h][b][e][t] = disagreement23AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              disagreement23STD[h][b][e][t] = disagreement23STDAtomic[h][b][e][t].get() / Main.ITERATION;
              disagreement23STD[h][b][e][t] = pow(disagreement23STD[h][b][e][t] - pow(disagreement23AVG[h][b][e][t], 2), .5);

              disagreement13AVG[h][b][e][t] = disagreement13AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              disagreement13STD[h][b][e][t] = disagreement13STDAtomic[h][b][e][t].get() / Main.ITERATION;
              disagreement13STD[h][b][e][t] = pow(disagreement13STD[h][b][e][t] - pow(disagreement13AVG[h][b][e][t], 2), .5);

              clusteringAVG[h][b][e][t] = clusteringAVGAtomic[h][b][e][t].get() / Main.ITERATION;
              clusteringSTD[h][b][e][t] = clusteringSTDAtomic[h][b][e][t].get() / Main.ITERATION;
              clusteringSTD[h][b][e][t] = pow(clusteringSTD[h][b][e][t] - pow(clusteringAVG[h][b][e][t], 2), .5);

              clustering12AVG[h][b][e][t] = clustering12AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              clustering12STD[h][b][e][t] = clustering12STDAtomic[h][b][e][t].get() / Main.ITERATION;
              clustering12STD[h][b][e][t] = pow(clustering12STD[h][b][e][t] - pow(clustering12AVG[h][b][e][t], 2), .5);

              clustering23AVG[h][b][e][t] = clustering23AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              clustering23STD[h][b][e][t] = clustering23STDAtomic[h][b][e][t].get() / Main.ITERATION;
              clustering23STD[h][b][e][t] = pow(clustering23STD[h][b][e][t] - pow(clustering23AVG[h][b][e][t], 2), .5);

              clustering13AVG[h][b][e][t] = clustering13AVGAtomic[h][b][e][t].get() / Main.ITERATION;
              clustering13STD[h][b][e][t] = clustering13STDAtomic[h][b][e][t].get() / Main.ITERATION;
              clustering13STD[h][b][e][t] = pow(clustering13STD[h][b][e][t] - pow(clustering13AVG[h][b][e][t], 2), .5);

              satisfactionAVG[h][b][e][t] = satisfactionAVGAtomic[h][b][e][t].get() / Main.ITERATION;
              satisfactionSTD[h][b][e][t] = satisfactionSTDAtomic[h][b][e][t].get() / Main.ITERATION;
              satisfactionSTD[h][b][e][t] = pow(satisfactionSTD[h][b][e][t] - pow(satisfactionAVG[h][b][e][t], 2), .5);

              rewiringAVG[h][b][e][t] = rewiringAVGAtomic[h][b][e][t].get() / Main.ITERATION;
              rewiringSTD[h][b][e][t] = rewiringSTDAtomic[h][b][e][t].get() / Main.ITERATION;
              rewiringSTD[h][b][e][t] = pow(rewiringSTD[h][b][e][t] - pow(rewiringAVG[h][b][e][t], 2), .5);

              sampleBetaAVG[h][b][e][t] = sampleBetaAVGAtomic[h][b][e][t].get() / Main.ITERATION;
              sampleBetaSTD[h][b][e][t] = sampleBetaSTDAtomic[h][b][e][t].get() / Main.ITERATION;
              sampleBetaSTD[h][b][e][t] = pow(sampleBetaSTD[h][b][e][t] - pow(sampleBetaAVG[h][b][e][t], 2), .5);
            }
          }
      }
    }
  }

  class SingleBetaRun {

    int hIndex;
    int betaIndex;
    int eIndex;
    
    double h;
    double beta;
    double enforcement;

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

    SingleBetaRun(int hIndex, int betaIndex, int eIndex) {
      this.hIndex = hIndex;
      this.betaIndex = betaIndex;
      this.eIndex = eIndex;

      h = Main.C[hIndex];
      beta = Main.BETA[betaIndex];
      enforcement = Main.E[eIndex];

      initializeResultSpace();
      run();
    }

    private void initializeResultSpace() {
      performanceAVGAtomicPart = performanceAVGAtomic[hIndex][betaIndex][eIndex];
      performanceSTDAtomicPart = performanceSTDAtomic[hIndex][betaIndex][eIndex];
      performance12AVGAtomicPart = performance12AVGAtomic[hIndex][betaIndex][eIndex];
      performance12STDAtomicPart = performance12STDAtomic[hIndex][betaIndex][eIndex];
      performance23AVGAtomicPart = performance23AVGAtomic[hIndex][betaIndex][eIndex];
      performance23STDAtomicPart = performance23STDAtomic[hIndex][betaIndex][eIndex];
      performance13AVGAtomicPart = performance13AVGAtomic[hIndex][betaIndex][eIndex];
      performance13STDAtomicPart = performance13STDAtomic[hIndex][betaIndex][eIndex];

      disagreementAVGAtomicPart = disagreementAVGAtomic[hIndex][betaIndex][eIndex];
      disagreementSTDAtomicPart = disagreementSTDAtomic[hIndex][betaIndex][eIndex];
      disagreement12AVGAtomicPart = disagreement12AVGAtomic[hIndex][betaIndex][eIndex];
      disagreement12STDAtomicPart = disagreement12STDAtomic[hIndex][betaIndex][eIndex];
      disagreement23AVGAtomicPart = disagreement23AVGAtomic[hIndex][betaIndex][eIndex];
      disagreement23STDAtomicPart = disagreement23STDAtomic[hIndex][betaIndex][eIndex];
      disagreement13AVGAtomicPart = disagreement13AVGAtomic[hIndex][betaIndex][eIndex];
      disagreement13STDAtomicPart = disagreement13STDAtomic[hIndex][betaIndex][eIndex];

      clusteringAVGAtomicPart = clusteringAVGAtomic[hIndex][betaIndex][eIndex];
      clusteringSTDAtomicPart = clusteringSTDAtomic[hIndex][betaIndex][eIndex];
      clustering12AVGAtomicPart = clustering12AVGAtomic[hIndex][betaIndex][eIndex];
      clustering12STDAtomicPart = clustering12STDAtomic[hIndex][betaIndex][eIndex];
      clustering23AVGAtomicPart = clustering23AVGAtomic[hIndex][betaIndex][eIndex];
      clustering23STDAtomicPart = clustering23STDAtomic[hIndex][betaIndex][eIndex];
      clustering13AVGAtomicPart = clustering13AVGAtomic[hIndex][betaIndex][eIndex];
      clustering13STDAtomicPart = clustering13STDAtomic[hIndex][betaIndex][eIndex];

      satisfactionAVGAtomicPart = satisfactionAVGAtomic[hIndex][betaIndex][eIndex];
      satisfactionSTDAtomicPart = satisfactionSTDAtomic[hIndex][betaIndex][eIndex];

      rewiringAVGAtomicPart = rewiringAVGAtomic[hIndex][betaIndex][eIndex];
      rewiringSTDAtomicPart = rewiringSTDAtomic[hIndex][betaIndex][eIndex];

      sampleEtaAVGAtomicPart = sampleBetaAVGAtomic[hIndex][betaIndex][eIndex];
      sampleEtaSTDAtomicPart = sampleBetaSTDAtomic[hIndex][betaIndex][eIndex];
    }

    private void run() {
      Scenario src = new Scenario(h, beta, enforcement);
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
