package DAMix;

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

  AtomicDouble[][][][][][] performanceAVGAtomic;
  AtomicDouble[][][][][][] performanceSTDAtomic;
  AtomicDouble[][][][][][] performance12AVGAtomic;
  AtomicDouble[][][][][][] performance12STDAtomic;
  AtomicDouble[][][][][][] performance23AVGAtomic;
  AtomicDouble[][][][][][] performance23STDAtomic;
  AtomicDouble[][][][][][] performance13AVGAtomic;
  AtomicDouble[][][][][][] performance13STDAtomic;
  AtomicDouble[][][][][][] performanceNRAVGAtomic;
  AtomicDouble[][][][][][] performanceNRSTDAtomic;
  AtomicDouble[][][][][][] performanceRRAVGAtomic;
  AtomicDouble[][][][][][] performanceRRSTDAtomic;

  AtomicDouble[][][][][][] disagreementAVGAtomic;
  AtomicDouble[][][][][][] disagreementSTDAtomic;
  AtomicDouble[][][][][][] disagreement12AVGAtomic;
  AtomicDouble[][][][][][] disagreement12STDAtomic;
  AtomicDouble[][][][][][] disagreement23AVGAtomic;
  AtomicDouble[][][][][][] disagreement23STDAtomic;
  AtomicDouble[][][][][][] disagreement13AVGAtomic;
  AtomicDouble[][][][][][] disagreement13STDAtomic;
  AtomicDouble[][][][][][] disagreementNRAVGAtomic;
  AtomicDouble[][][][][][] disagreementNRSTDAtomic;
  AtomicDouble[][][][][][] disagreementRRAVGAtomic;
  AtomicDouble[][][][][][] disagreementRRSTDAtomic;

  AtomicDouble[][][][][][] clusteringAVGAtomic;
  AtomicDouble[][][][][][] clusteringSTDAtomic;
  AtomicDouble[][][][][][] clustering12AVGAtomic;
  AtomicDouble[][][][][][] clustering12STDAtomic;
  AtomicDouble[][][][][][] clustering23AVGAtomic;
  AtomicDouble[][][][][][] clustering23STDAtomic;
  AtomicDouble[][][][][][] clustering13AVGAtomic;
  AtomicDouble[][][][][][] clustering13STDAtomic;
  AtomicDouble[][][][][][] clusteringNRAVGAtomic;
  AtomicDouble[][][][][][] clusteringNRSTDAtomic;
  AtomicDouble[][][][][][] clusteringRRAVGAtomic;
  AtomicDouble[][][][][][] clusteringRRSTDAtomic;

  AtomicDouble[][][][][][] clusteringWattsStrogatzAVGAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatzSTDAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatz12AVGAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatz12STDAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatz23AVGAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatz23STDAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatz13AVGAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatz13STDAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatzNRAVGAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatzNRSTDAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatzRRAVGAtomic;
  AtomicDouble[][][][][][] clusteringWattsStrogatzRRSTDAtomic;

  AtomicDouble[][][][][][] centralizationAVGAtomic;
  AtomicDouble[][][][][][] centralizationSTDAtomic;
  AtomicDouble[][][][][][] centralization12AVGAtomic;
  AtomicDouble[][][][][][] centralization12STDAtomic;
  AtomicDouble[][][][][][] centralization23AVGAtomic;
  AtomicDouble[][][][][][] centralization23STDAtomic;
  AtomicDouble[][][][][][] centralization13AVGAtomic;
  AtomicDouble[][][][][][] centralization13STDAtomic;
  AtomicDouble[][][][][][] centralizationNRAVGAtomic;
  AtomicDouble[][][][][][] centralizationNRSTDAtomic;
  AtomicDouble[][][][][][] centralizationRRAVGAtomic;
  AtomicDouble[][][][][][] centralizationRRSTDAtomic;

  AtomicDouble[][][][][][] efficiencyAVGAtomic;
  AtomicDouble[][][][][][] efficiencySTDAtomic;
  AtomicDouble[][][][][][] efficiency12AVGAtomic;
  AtomicDouble[][][][][][] efficiency12STDAtomic;
  AtomicDouble[][][][][][] efficiency23AVGAtomic;
  AtomicDouble[][][][][][] efficiency23STDAtomic;
  AtomicDouble[][][][][][] efficiency13AVGAtomic;
  AtomicDouble[][][][][][] efficiency13STDAtomic;
  AtomicDouble[][][][][][] efficiencyNRAVGAtomic;
  AtomicDouble[][][][][][] efficiencyNRSTDAtomic;
  AtomicDouble[][][][][][] efficiencyRRAVGAtomic;
  AtomicDouble[][][][][][] efficiencyRRSTDAtomic;

  AtomicDouble[][][][][][] distanceAVGAtomic;
  AtomicDouble[][][][][][] distanceSTDAtomic;
  AtomicDouble[][][][][][] distance12AVGAtomic;
  AtomicDouble[][][][][][] distance12STDAtomic;
  AtomicDouble[][][][][][] distance23AVGAtomic;
  AtomicDouble[][][][][][] distance23STDAtomic;
  AtomicDouble[][][][][][] distance13AVGAtomic;
  AtomicDouble[][][][][][] distance13STDAtomic;
  AtomicDouble[][][][][][] distanceNRAVGAtomic;
  AtomicDouble[][][][][][] distanceNRSTDAtomic;
  AtomicDouble[][][][][][] distanceRRAVGAtomic;
  AtomicDouble[][][][][][] distanceRRSTDAtomic;

  AtomicDouble[][][][][][] densityAVGAtomic;
  AtomicDouble[][][][][][] densitySTDAtomic;
  AtomicDouble[][][][][][] density12AVGAtomic;
  AtomicDouble[][][][][][] density12STDAtomic;
  AtomicDouble[][][][][][] density23AVGAtomic;
  AtomicDouble[][][][][][] density23STDAtomic;
  AtomicDouble[][][][][][] density13AVGAtomic;
  AtomicDouble[][][][][][] density13STDAtomic;
  AtomicDouble[][][][][][] densityNRAVGAtomic;
  AtomicDouble[][][][][][] densityNRSTDAtomic;
  AtomicDouble[][][][][][] densityRRAVGAtomic;
  AtomicDouble[][][][][][] densityRRSTDAtomic;

  AtomicDouble[][][][][][] betweennessCentralityVarianceAVGAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVarianceSTDAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVariance12AVGAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVariance12STDAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVariance23AVGAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVariance23STDAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVariance13AVGAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVariance13STDAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVarianceNRAVGAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVarianceNRSTDAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVarianceRRAVGAtomic;
  AtomicDouble[][][][][][] betweennessCentralityVarianceRRSTDAtomic;

  AtomicDouble[][][][][][] satisfactionAVGAtomic;
  AtomicDouble[][][][][][] satisfactionSTDAtomic;
  AtomicDouble[][][][][][] rewiringAVGAtomic;
  AtomicDouble[][][][][][] rewiringSTDAtomic;
  AtomicDouble[][][][][][] rewiringCumulativeAVGAtomic;
  AtomicDouble[][][][][][] rewiringCumulativeSTDAtomic;

  // Results in double arrays
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

  double[][][][][][] disagreementAVG;
  double[][][][][][] disagreementSTD;
  double[][][][][][] disagreement12AVG;
  double[][][][][][] disagreement12STD;
  double[][][][][][] disagreement23AVG;
  double[][][][][][] disagreement23STD;
  double[][][][][][] disagreement13AVG;
  double[][][][][][] disagreement13STD;
  double[][][][][][] disagreementNRAVG;
  double[][][][][][] disagreementNRSTD;
  double[][][][][][] disagreementRRAVG;
  double[][][][][][] disagreementRRSTD;

  double[][][][][][] clusteringAVG;
  double[][][][][][] clusteringSTD;
  double[][][][][][] clustering12AVG;
  double[][][][][][] clustering12STD;
  double[][][][][][] clustering23AVG;
  double[][][][][][] clustering23STD;
  double[][][][][][] clustering13AVG;
  double[][][][][][] clustering13STD;
  double[][][][][][] clusteringNRAVG;
  double[][][][][][] clusteringNRSTD;
  double[][][][][][] clusteringRRAVG;
  double[][][][][][] clusteringRRSTD;

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

  double[][][][][][] betweennessCentralityVarianceAVG;
  double[][][][][][] betweennessCentralityVarianceSTD;
  double[][][][][][] betweennessCentralityVariance12AVG;
  double[][][][][][] betweennessCentralityVariance12STD;
  double[][][][][][] betweennessCentralityVariance23AVG;
  double[][][][][][] betweennessCentralityVariance23STD;
  double[][][][][][] betweennessCentralityVariance13AVG;
  double[][][][][][] betweennessCentralityVariance13STD;
  double[][][][][][] betweennessCentralityVarianceNRAVG;
  double[][][][][][] betweennessCentralityVarianceNRSTD;
  double[][][][][][] betweennessCentralityVarianceRRAVG;
  double[][][][][][] betweennessCentralityVarianceRRSTD;

  double[][][][][][] satisfactionAVG;
  double[][][][][][] satisfactionSTD;
  double[][][][][][] rewiringAVG;
  double[][][][][][] rewiringSTD;
  double[][][][][][] rewiringCumulativeAVG;
  double[][][][][][] rewiringCumulativeSTD;

  ProgressBar pb;

  Computation() {
    workStealingPool = Executors.newWorkStealingPool();
  }

  public void printNetwork() {
    try {
      Files.createDirectories(Paths.get(Main.PATH_CSV));
    } catch (IOException e) {
      e.printStackTrace();
    }

    for (int h = 0; h < Main.LENGTH_H; h++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int c = 0; c < Main.LENGTH_CONNECTIVITY; c++) {
          for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
            double strength = Main.H[h];
            int span = Main.SPAN[s];
            double connectivity = Main.CONNECTIVITY[c];
            double enforcement = Main.ENFORCEMENT[e];
            String mcString = "mixed";
            String fileName = Main.RUN_ID + "_" + "h" + strength + "_s" + span + "_c" + connectivity + "_" + enforcement + "_" + mcString;
            Scenario src = new Scenario(strength, span, connectivity, enforcement);
            Scenario rr = src.getClone(true, true);
            Scenario nr = src.getClone(false, false);
            src.printCSV(Main.PATH_CSV + "sc_" + fileName + "_t0");
            rr.printCSV(Main.PATH_CSV + "rr_" + fileName + "_t0");
            nr.printCSV(Main.PATH_CSV + "nr_" + fileName + "_t0");
            for (int t = 0; t < Main.TIME; t++) {
              src.stepForward();
              rr.stepForward(src.numFormation, src.numBreak, src.isNotConverged);
            }
            src.printCSV(Main.PATH_CSV + "sc_" + fileName + "_t" + Main.TIME);
            rr.printCSV(Main.PATH_CSV + "rr_" + fileName + "_t" + Main.TIME);
            nr.printCSV(Main.PATH_CSV + "nr_" + fileName + "_t" + Main.TIME);
            System.out.println("Network Printed: " + fileName);
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
    performanceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    disagreementAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    clusteringAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    clusteringWattsStrogatzAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    centralizationAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    efficiencyAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencySTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    distanceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    densityAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densitySTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    betweennessCentralityVarianceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    satisfactionAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    satisfactionSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    rewiringAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    rewiringSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    rewiringCumulativeAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    rewiringCumulativeSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int h = 0; h < Main.LENGTH_H; h++) {
        for (int s = 0; s < Main.LENGTH_SPAN; s++) {
          for (int c = 0; c < Main.LENGTH_CONNECTIVITY; c++) {
            for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
              for (int t = 0; t < Main.TIME; t++) {
                performanceAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performanceSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performance12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performance12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performance23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performance23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performance13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performance13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performanceNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performanceNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performanceRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                performanceRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();

                disagreementAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreementSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreement12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreement12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreement23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreement23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreement13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreement13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreementNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreementNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreementRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                disagreementRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();

                clusteringAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clustering12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clustering12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clustering23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clustering23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clustering13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clustering13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                
                clusteringWattsStrogatzAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatzSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatz12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatz12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatz23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatz23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatz13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatz13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatzNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatzNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatzRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                clusteringWattsStrogatzRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();

                centralizationAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralizationSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralization12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralization12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralization23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralization23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralization13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralization13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralizationNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralizationNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralizationRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                centralizationRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();

                efficiencyAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiencySTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiency12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiency12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiency23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiency23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiency13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiency13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiencyNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiencyNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiencyRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                efficiencyRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();

                distanceAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distanceSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distance12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distance12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distance23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distance23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distance13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distance13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distanceNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distanceNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distanceRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                distanceRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();

                densityAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                densitySTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                density12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                density12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                density23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                density23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                density13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                density13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                densityNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                densityNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                densityRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                densityRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();

                betweennessCentralityVarianceAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVarianceSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVariance12AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVariance12STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVariance23AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVariance23STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVariance13AVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVariance13STDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVarianceNRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVarianceNRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVarianceRRAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                betweennessCentralityVarianceRRSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();

                satisfactionAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                satisfactionSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                rewiringAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                rewiringSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                rewiringCumulativeAVGAtomic[mc][h][s][c][e][t] = new AtomicDouble();
                rewiringCumulativeSTDAtomic[mc][h][s][c][e][t] = new AtomicDouble();
              }
            }
          }
        }
      }
    }
    performanceAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performance13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    performanceRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    disagreementAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreement13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    disagreementRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    clusteringAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clustering13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    clusteringWattsStrogatzAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatz13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    clusteringWattsStrogatzRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    centralizationAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralization13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    centralizationRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    efficiencyAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencySTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiency13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    efficiencyRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    distanceAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distance13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    distanceRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    densityAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densitySTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    density13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    densityRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    betweennessCentralityVarianceAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVariance13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    betweennessCentralityVarianceRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];

    satisfactionAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    satisfactionSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    rewiringAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    rewiringSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    rewiringCumulativeAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
    rewiringCumulativeSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_CONNECTIVITY][Main.LENGTH_ENFORCEMENT][Main.TIME];
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
          for (int s = 0; s < Main.LENGTH_SPAN; s++) {
            for (int c = 0; c < Main.LENGTH_CONNECTIVITY; c++) {
              for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
                new SingleRun(mc, h, s, c, e);
              }
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
          for (int c = 0; c < Main.LENGTH_CONNECTIVITY; c++) {
            for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
              for (int t = 0; t < Main.TIME; t++) {
                performanceAVG[mc][h][s][c][e][t] = performanceAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performanceSTD[mc][h][s][c][e][t] = performanceSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performanceSTD[mc][h][s][c][e][t] = pow(performanceSTD[mc][h][s][c][e][t] - pow(performanceAVG[mc][h][s][c][e][t], 2), .5);

                performance12AVG[mc][h][s][c][e][t] = performance12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performance12STD[mc][h][s][c][e][t] = performance12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performance12STD[mc][h][s][c][e][t] = pow(performance12STD[mc][h][s][c][e][t] - pow(performance12AVG[mc][h][s][c][e][t], 2), .5);

                performance23AVG[mc][h][s][c][e][t] = performance23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performance23STD[mc][h][s][c][e][t] = performance23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performance23STD[mc][h][s][c][e][t] = pow(performance23STD[mc][h][s][c][e][t] - pow(performance23AVG[mc][h][s][c][e][t], 2), .5);

                performance13AVG[mc][h][s][c][e][t] = performance13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performance13STD[mc][h][s][c][e][t] = performance13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performance13STD[mc][h][s][c][e][t] = pow(performance13STD[mc][h][s][c][e][t] - pow(performance13AVG[mc][h][s][c][e][t], 2), .5);

                performanceNRAVG[mc][h][s][c][e][t] = performanceNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performanceNRSTD[mc][h][s][c][e][t] = performanceNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performanceNRSTD[mc][h][s][c][e][t] = pow(performanceNRSTD[mc][h][s][c][e][t] - pow(performanceNRAVG[mc][h][s][c][e][t], 2), .5);

                performanceRRAVG[mc][h][s][c][e][t] = performanceRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performanceRRSTD[mc][h][s][c][e][t] = performanceRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                performanceRRSTD[mc][h][s][c][e][t] = pow(performanceRRSTD[mc][h][s][c][e][t] - pow(performanceRRAVG[mc][h][s][c][e][t], 2), .5);

                disagreementAVG[mc][h][s][c][e][t] = disagreementAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreementSTD[mc][h][s][c][e][t] = disagreementSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreementSTD[mc][h][s][c][e][t] = pow(disagreementSTD[mc][h][s][c][e][t] - pow(disagreementAVG[mc][h][s][c][e][t], 2), .5);

                disagreement12AVG[mc][h][s][c][e][t] = disagreement12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreement12STD[mc][h][s][c][e][t] = disagreement12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreement12STD[mc][h][s][c][e][t] = pow(disagreement12STD[mc][h][s][c][e][t] - pow(disagreement12AVG[mc][h][s][c][e][t], 2), .5);

                disagreement23AVG[mc][h][s][c][e][t] = disagreement23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreement23STD[mc][h][s][c][e][t] = disagreement23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreement23STD[mc][h][s][c][e][t] = pow(disagreement23STD[mc][h][s][c][e][t] - pow(disagreement23AVG[mc][h][s][c][e][t], 2), .5);

                disagreement13AVG[mc][h][s][c][e][t] = disagreement13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreement13STD[mc][h][s][c][e][t] = disagreement13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreement13STD[mc][h][s][c][e][t] = pow(disagreement13STD[mc][h][s][c][e][t] - pow(disagreement13AVG[mc][h][s][c][e][t], 2), .5);

                disagreementNRAVG[mc][h][s][c][e][t] = disagreementNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreementNRSTD[mc][h][s][c][e][t] = disagreementNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreementNRSTD[mc][h][s][c][e][t] = pow(disagreementNRSTD[mc][h][s][c][e][t] - pow(disagreementNRAVG[mc][h][s][c][e][t], 2), .5);

                disagreementRRAVG[mc][h][s][c][e][t] = disagreementRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreementRRSTD[mc][h][s][c][e][t] = disagreementRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                disagreementRRSTD[mc][h][s][c][e][t] = pow(disagreementRRSTD[mc][h][s][c][e][t] - pow(disagreementRRAVG[mc][h][s][c][e][t], 2), .5);

                clusteringAVG[mc][h][s][c][e][t] = clusteringAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringSTD[mc][h][s][c][e][t] = clusteringSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringSTD[mc][h][s][c][e][t] = pow(clusteringSTD[mc][h][s][c][e][t] - pow(clusteringAVG[mc][h][s][c][e][t], 2), .5);

                clustering12AVG[mc][h][s][c][e][t] = clustering12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clustering12STD[mc][h][s][c][e][t] = clustering12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clustering12STD[mc][h][s][c][e][t] = pow(clustering12STD[mc][h][s][c][e][t] - pow(clustering12AVG[mc][h][s][c][e][t], 2), .5);

                clustering23AVG[mc][h][s][c][e][t] = clustering23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clustering23STD[mc][h][s][c][e][t] = clustering23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clustering23STD[mc][h][s][c][e][t] = pow(clustering23STD[mc][h][s][c][e][t] - pow(clustering23AVG[mc][h][s][c][e][t], 2), .5);

                clustering13AVG[mc][h][s][c][e][t] = clustering13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clustering13STD[mc][h][s][c][e][t] = clustering13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clustering13STD[mc][h][s][c][e][t] = pow(clustering13STD[mc][h][s][c][e][t] - pow(clustering13AVG[mc][h][s][c][e][t], 2), .5);

                clusteringNRAVG[mc][h][s][c][e][t] = clusteringNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringNRSTD[mc][h][s][c][e][t] = clusteringNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringNRSTD[mc][h][s][c][e][t] = pow(clusteringNRSTD[mc][h][s][c][e][t] - pow(clusteringNRAVG[mc][h][s][c][e][t], 2), .5);

                clusteringRRAVG[mc][h][s][c][e][t] = clusteringRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringRRSTD[mc][h][s][c][e][t] = clusteringRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringRRSTD[mc][h][s][c][e][t] = pow(clusteringRRSTD[mc][h][s][c][e][t] - pow(clusteringRRAVG[mc][h][s][c][e][t], 2), .5);

                clusteringWattsStrogatzAVG[mc][h][s][c][e][t] = clusteringWattsStrogatzAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatzSTD[mc][h][s][c][e][t] = clusteringWattsStrogatzSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatzSTD[mc][h][s][c][e][t] = pow(clusteringWattsStrogatzSTD[mc][h][s][c][e][t] - pow(clusteringWattsStrogatzAVG[mc][h][s][c][e][t], 2), .5);

                clusteringWattsStrogatz12AVG[mc][h][s][c][e][t] = clusteringWattsStrogatz12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatz12STD[mc][h][s][c][e][t] = clusteringWattsStrogatz12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatz12STD[mc][h][s][c][e][t] = pow(clusteringWattsStrogatz12STD[mc][h][s][c][e][t] - pow(clusteringWattsStrogatz12AVG[mc][h][s][c][e][t], 2), .5);

                clusteringWattsStrogatz23AVG[mc][h][s][c][e][t] = clusteringWattsStrogatz23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatz23STD[mc][h][s][c][e][t] = clusteringWattsStrogatz23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatz23STD[mc][h][s][c][e][t] = pow(clusteringWattsStrogatz23STD[mc][h][s][c][e][t] - pow(clusteringWattsStrogatz23AVG[mc][h][s][c][e][t], 2), .5);

                clusteringWattsStrogatz13AVG[mc][h][s][c][e][t] = clusteringWattsStrogatz13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatz13STD[mc][h][s][c][e][t] = clusteringWattsStrogatz13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatz13STD[mc][h][s][c][e][t] = pow(clusteringWattsStrogatz13STD[mc][h][s][c][e][t] - pow(clusteringWattsStrogatz13AVG[mc][h][s][c][e][t], 2), .5);

                clusteringWattsStrogatzNRAVG[mc][h][s][c][e][t] = clusteringWattsStrogatzNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatzNRSTD[mc][h][s][c][e][t] = clusteringWattsStrogatzNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatzNRSTD[mc][h][s][c][e][t] = pow(clusteringWattsStrogatzNRSTD[mc][h][s][c][e][t] - pow(clusteringWattsStrogatzNRAVG[mc][h][s][c][e][t], 2), .5);

                clusteringWattsStrogatzRRAVG[mc][h][s][c][e][t] = clusteringWattsStrogatzRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatzRRSTD[mc][h][s][c][e][t] = clusteringWattsStrogatzRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                clusteringWattsStrogatzRRSTD[mc][h][s][c][e][t] = pow(clusteringWattsStrogatzRRSTD[mc][h][s][c][e][t] - pow(clusteringWattsStrogatzRRAVG[mc][h][s][c][e][t], 2), .5);

                centralizationAVG[mc][h][s][c][e][t] = centralizationAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralizationSTD[mc][h][s][c][e][t] = centralizationSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralizationSTD[mc][h][s][c][e][t] = pow(centralizationSTD[mc][h][s][c][e][t] - pow(centralizationAVG[mc][h][s][c][e][t], 2), .5);

                centralization12AVG[mc][h][s][c][e][t] = centralization12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralization12STD[mc][h][s][c][e][t] = centralization12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralization12STD[mc][h][s][c][e][t] = pow(centralization12STD[mc][h][s][c][e][t] - pow(centralization12AVG[mc][h][s][c][e][t], 2), .5);

                centralization23AVG[mc][h][s][c][e][t] = centralization23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralization23STD[mc][h][s][c][e][t] = centralization23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralization23STD[mc][h][s][c][e][t] = pow(centralization23STD[mc][h][s][c][e][t] - pow(centralization23AVG[mc][h][s][c][e][t], 2), .5);

                centralization13AVG[mc][h][s][c][e][t] = centralization13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralization13STD[mc][h][s][c][e][t] = centralization13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralization13STD[mc][h][s][c][e][t] = pow(centralization13STD[mc][h][s][c][e][t] - pow(centralization13AVG[mc][h][s][c][e][t], 2), .5);

                centralizationNRAVG[mc][h][s][c][e][t] = centralizationNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralizationNRSTD[mc][h][s][c][e][t] = centralizationNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralizationNRSTD[mc][h][s][c][e][t] = pow(centralizationNRSTD[mc][h][s][c][e][t] - pow(centralizationNRAVG[mc][h][s][c][e][t], 2), .5);

                centralizationRRAVG[mc][h][s][c][e][t] = centralizationRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralizationRRSTD[mc][h][s][c][e][t] = centralizationRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                centralizationRRSTD[mc][h][s][c][e][t] = pow(centralizationRRSTD[mc][h][s][c][e][t] - pow(centralizationRRAVG[mc][h][s][c][e][t], 2), .5);

                efficiencyAVG[mc][h][s][c][e][t] = efficiencyAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiencySTD[mc][h][s][c][e][t] = efficiencySTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiencySTD[mc][h][s][c][e][t] = pow(efficiencySTD[mc][h][s][c][e][t] - pow(efficiencyAVG[mc][h][s][c][e][t], 2), .5);

                efficiency12AVG[mc][h][s][c][e][t] = efficiency12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiency12STD[mc][h][s][c][e][t] = efficiency12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiency12STD[mc][h][s][c][e][t] = pow(efficiency12STD[mc][h][s][c][e][t] - pow(efficiency12AVG[mc][h][s][c][e][t], 2), .5);

                efficiency23AVG[mc][h][s][c][e][t] = efficiency23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiency23STD[mc][h][s][c][e][t] = efficiency23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiency23STD[mc][h][s][c][e][t] = pow(efficiency23STD[mc][h][s][c][e][t] - pow(efficiency23AVG[mc][h][s][c][e][t], 2), .5);

                efficiency13AVG[mc][h][s][c][e][t] = efficiency13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiency13STD[mc][h][s][c][e][t] = efficiency13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiency13STD[mc][h][s][c][e][t] = pow(efficiency13STD[mc][h][s][c][e][t] - pow(efficiency13AVG[mc][h][s][c][e][t], 2), .5);

                efficiencyNRAVG[mc][h][s][c][e][t] = efficiencyNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiencyNRSTD[mc][h][s][c][e][t] = efficiencyNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiencyNRSTD[mc][h][s][c][e][t] = pow(efficiencyNRSTD[mc][h][s][c][e][t] - pow(efficiencyNRAVG[mc][h][s][c][e][t], 2), .5);

                efficiencyRRAVG[mc][h][s][c][e][t] = efficiencyRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiencyRRSTD[mc][h][s][c][e][t] = efficiencyRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                efficiencyRRSTD[mc][h][s][c][e][t] = pow(efficiencyRRSTD[mc][h][s][c][e][t] - pow(efficiencyRRAVG[mc][h][s][c][e][t], 2), .5);

                distanceAVG[mc][h][s][c][e][t] = distanceAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distanceSTD[mc][h][s][c][e][t] = distanceSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distanceSTD[mc][h][s][c][e][t] = pow(distanceSTD[mc][h][s][c][e][t] - pow(distanceAVG[mc][h][s][c][e][t], 2), .5);

                distance12AVG[mc][h][s][c][e][t] = distance12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distance12STD[mc][h][s][c][e][t] = distance12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distance12STD[mc][h][s][c][e][t] = pow(distance12STD[mc][h][s][c][e][t] - pow(distance12AVG[mc][h][s][c][e][t], 2), .5);

                distance23AVG[mc][h][s][c][e][t] = distance23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distance23STD[mc][h][s][c][e][t] = distance23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distance23STD[mc][h][s][c][e][t] = pow(distance23STD[mc][h][s][c][e][t] - pow(distance23AVG[mc][h][s][c][e][t], 2), .5);

                distance13AVG[mc][h][s][c][e][t] = distance13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distance13STD[mc][h][s][c][e][t] = distance13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distance13STD[mc][h][s][c][e][t] = pow(distance13STD[mc][h][s][c][e][t] - pow(distance13AVG[mc][h][s][c][e][t], 2), .5);

                distanceNRAVG[mc][h][s][c][e][t] = distanceNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distanceNRSTD[mc][h][s][c][e][t] = distanceNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distanceNRSTD[mc][h][s][c][e][t] = pow(distanceNRSTD[mc][h][s][c][e][t] - pow(distanceNRAVG[mc][h][s][c][e][t], 2), .5);

                distanceRRAVG[mc][h][s][c][e][t] = distanceRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distanceRRSTD[mc][h][s][c][e][t] = distanceRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                distanceRRSTD[mc][h][s][c][e][t] = pow(distanceRRSTD[mc][h][s][c][e][t] - pow(distanceRRAVG[mc][h][s][c][e][t], 2), .5);

                densityAVG[mc][h][s][c][e][t] = densityAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                densitySTD[mc][h][s][c][e][t] = densitySTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                densitySTD[mc][h][s][c][e][t] = pow(densitySTD[mc][h][s][c][e][t] - pow(densityAVG[mc][h][s][c][e][t], 2), .5);

                density12AVG[mc][h][s][c][e][t] = density12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                density12STD[mc][h][s][c][e][t] = density12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                density12STD[mc][h][s][c][e][t] = pow(density12STD[mc][h][s][c][e][t] - pow(density12AVG[mc][h][s][c][e][t], 2), .5);

                density23AVG[mc][h][s][c][e][t] = density23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                density23STD[mc][h][s][c][e][t] = density23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                density23STD[mc][h][s][c][e][t] = pow(density23STD[mc][h][s][c][e][t] - pow(density23AVG[mc][h][s][c][e][t], 2), .5);

                density13AVG[mc][h][s][c][e][t] = density13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                density13STD[mc][h][s][c][e][t] = density13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                density13STD[mc][h][s][c][e][t] = pow(density13STD[mc][h][s][c][e][t] - pow(density13AVG[mc][h][s][c][e][t], 2), .5);

                densityNRAVG[mc][h][s][c][e][t] = densityNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                densityNRSTD[mc][h][s][c][e][t] = densityNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                densityNRSTD[mc][h][s][c][e][t] = pow(densityNRSTD[mc][h][s][c][e][t] - pow(densityNRAVG[mc][h][s][c][e][t], 2), .5);

                densityRRAVG[mc][h][s][c][e][t] = densityRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                densityRRSTD[mc][h][s][c][e][t] = densityRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                densityRRSTD[mc][h][s][c][e][t] = pow(densityRRSTD[mc][h][s][c][e][t] - pow(densityRRAVG[mc][h][s][c][e][t], 2), .5);

                betweennessCentralityVarianceAVG[mc][h][s][c][e][t] = betweennessCentralityVarianceAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVarianceSTD[mc][h][s][c][e][t] = betweennessCentralityVarianceSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVarianceSTD[mc][h][s][c][e][t] = pow(betweennessCentralityVarianceSTD[mc][h][s][c][e][t] - pow(betweennessCentralityVarianceAVG[mc][h][s][c][e][t], 2), .5);

                betweennessCentralityVariance12AVG[mc][h][s][c][e][t] = betweennessCentralityVariance12AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVariance12STD[mc][h][s][c][e][t] = betweennessCentralityVariance12STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVariance12STD[mc][h][s][c][e][t] = pow(betweennessCentralityVariance12STD[mc][h][s][c][e][t] - pow(betweennessCentralityVariance12AVG[mc][h][s][c][e][t], 2), .5);

                betweennessCentralityVariance23AVG[mc][h][s][c][e][t] = betweennessCentralityVariance23AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVariance23STD[mc][h][s][c][e][t] = betweennessCentralityVariance23STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVariance23STD[mc][h][s][c][e][t] = pow(betweennessCentralityVariance23STD[mc][h][s][c][e][t] - pow(betweennessCentralityVariance23AVG[mc][h][s][c][e][t], 2), .5);

                betweennessCentralityVariance13AVG[mc][h][s][c][e][t] = betweennessCentralityVariance13AVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVariance13STD[mc][h][s][c][e][t] = betweennessCentralityVariance13STDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVariance13STD[mc][h][s][c][e][t] = pow(betweennessCentralityVariance13STD[mc][h][s][c][e][t] - pow(betweennessCentralityVariance13AVG[mc][h][s][c][e][t], 2), .5);

                betweennessCentralityVarianceNRAVG[mc][h][s][c][e][t] = betweennessCentralityVarianceNRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVarianceNRSTD[mc][h][s][c][e][t] = betweennessCentralityVarianceNRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVarianceNRSTD[mc][h][s][c][e][t] = pow(betweennessCentralityVarianceNRSTD[mc][h][s][c][e][t] - pow(betweennessCentralityVarianceNRAVG[mc][h][s][c][e][t], 2), .5);

                betweennessCentralityVarianceRRAVG[mc][h][s][c][e][t] = betweennessCentralityVarianceRRAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVarianceRRSTD[mc][h][s][c][e][t] = betweennessCentralityVarianceRRSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                betweennessCentralityVarianceRRSTD[mc][h][s][c][e][t] = pow(betweennessCentralityVarianceRRSTD[mc][h][s][c][e][t] - pow(betweennessCentralityVarianceRRAVG[mc][h][s][c][e][t], 2), .5);

                satisfactionAVG[mc][h][s][c][e][t] = satisfactionAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                satisfactionSTD[mc][h][s][c][e][t] = satisfactionSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                satisfactionSTD[mc][h][s][c][e][t] = pow(satisfactionSTD[mc][h][s][c][e][t] - pow(satisfactionAVG[mc][h][s][c][e][t], 2), .5);

                rewiringAVG[mc][h][s][c][e][t] = rewiringAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                rewiringSTD[mc][h][s][c][e][t] = rewiringSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                rewiringSTD[mc][h][s][c][e][t] = pow(rewiringSTD[mc][h][s][c][e][t] - pow(rewiringAVG[mc][h][s][c][e][t], 2), .5);
                
                rewiringCumulativeAVG[mc][h][s][c][e][t] = rewiringCumulativeAVGAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                rewiringCumulativeSTD[mc][h][s][c][e][t] = rewiringCumulativeSTDAtomic[mc][h][s][c][e][t].get() / Main.ITERATION;
                rewiringCumulativeSTD[mc][h][s][c][e][t] = pow(rewiringCumulativeSTD[mc][h][s][c][e][t] - pow(rewiringCumulativeAVG[mc][h][s][c][e][t], 2), .5);
              }
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
    int connectivityIndex;
    int enforcementIndex;

    double h;
    int span;
    double connectivity;
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

    AtomicDouble[] satisfactionAVGAtomicPart;
    AtomicDouble[] satisfactionSTDAtomicPart;

    AtomicDouble[] rewiringAVGAtomicPart;
    AtomicDouble[] rewiringSTDAtomicPart;
    
    AtomicDouble[] rewiringCumulativeAVGAtomicPart;
    AtomicDouble[] rewiringCumulativeSTDAtomicPart;

    SingleRun(int mcIndex, int hIndex, int spanIndex, int connectivityIndex, int enforcementIndex) {
      this.mcIndex = mcIndex;
      this.hIndex = hIndex;
      this.spanIndex = spanIndex;
      this.connectivityIndex = connectivityIndex;
      this.enforcementIndex = enforcementIndex;

      h = Main.H[hIndex];
      span = Main.SPAN[spanIndex];
      connectivity = Main.CONNECTIVITY[connectivityIndex];
      enforcement = Main.ENFORCEMENT[enforcementIndex];

      initializeResultSpace();
      run();
    }

    private void initializeResultSpace() {
      performanceAVGAtomicPart = performanceAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performanceSTDAtomicPart = performanceSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performance12AVGAtomicPart = performance12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performance12STDAtomicPart = performance12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performance23AVGAtomicPart = performance23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performance23STDAtomicPart = performance23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performance13AVGAtomicPart = performance13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performance13STDAtomicPart = performance13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performanceNRAVGAtomicPart = performanceNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performanceNRSTDAtomicPart = performanceNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performanceRRAVGAtomicPart = performanceRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      performanceRRSTDAtomicPart = performanceRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      disagreementAVGAtomicPart = disagreementAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreementSTDAtomicPart = disagreementSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreement12AVGAtomicPart = disagreement12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreement12STDAtomicPart = disagreement12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreement23AVGAtomicPart = disagreement23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreement23STDAtomicPart = disagreement23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreement13AVGAtomicPart = disagreement13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreement13STDAtomicPart = disagreement13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreementNRAVGAtomicPart = disagreementNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreementNRSTDAtomicPart = disagreementNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreementRRAVGAtomicPart = disagreementRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      disagreementRRSTDAtomicPart = disagreementRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      clusteringAVGAtomicPart = clusteringAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringSTDAtomicPart = clusteringSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clustering12AVGAtomicPart = clustering12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clustering12STDAtomicPart = clustering12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clustering23AVGAtomicPart = clustering23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clustering23STDAtomicPart = clustering23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clustering13AVGAtomicPart = clustering13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clustering13STDAtomicPart = clustering13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringNRAVGAtomicPart = clusteringNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringNRSTDAtomicPart = clusteringNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringRRAVGAtomicPart = clusteringRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringRRSTDAtomicPart = clusteringRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      clusteringWattsStrogatzAVGAtomicPart = clusteringWattsStrogatzAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatzSTDAtomicPart = clusteringWattsStrogatzSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatz12AVGAtomicPart = clusteringWattsStrogatz12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatz12STDAtomicPart = clusteringWattsStrogatz12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatz23AVGAtomicPart = clusteringWattsStrogatz23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatz23STDAtomicPart = clusteringWattsStrogatz23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatz13AVGAtomicPart = clusteringWattsStrogatz13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatz13STDAtomicPart = clusteringWattsStrogatz13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatzNRAVGAtomicPart = clusteringWattsStrogatzNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatzNRSTDAtomicPart = clusteringWattsStrogatzNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatzRRAVGAtomicPart = clusteringWattsStrogatzRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      clusteringWattsStrogatzRRSTDAtomicPart = clusteringWattsStrogatzRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      centralizationAVGAtomicPart = centralizationAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralizationSTDAtomicPart = centralizationSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralization12AVGAtomicPart = centralization12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralization12STDAtomicPart = centralization12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralization23AVGAtomicPart = centralization23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralization23STDAtomicPart = centralization23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralization13AVGAtomicPart = centralization13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralization13STDAtomicPart = centralization13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralizationNRAVGAtomicPart = centralizationNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralizationNRSTDAtomicPart = centralizationNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralizationRRAVGAtomicPart = centralizationRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      centralizationRRSTDAtomicPart = centralizationRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      efficiencyAVGAtomicPart = efficiencyAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiencySTDAtomicPart = efficiencySTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiency12AVGAtomicPart = efficiency12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiency12STDAtomicPart = efficiency12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiency23AVGAtomicPart = efficiency23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiency23STDAtomicPart = efficiency23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiency13AVGAtomicPart = efficiency13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiency13STDAtomicPart = efficiency13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiencyNRAVGAtomicPart = efficiencyNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiencyNRSTDAtomicPart = efficiencyNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiencyRRAVGAtomicPart = efficiencyRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      efficiencyRRSTDAtomicPart = efficiencyRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      distanceAVGAtomicPart = distanceAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distanceSTDAtomicPart = distanceSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distance12AVGAtomicPart = distance12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distance12STDAtomicPart = distance12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distance23AVGAtomicPart = distance23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distance23STDAtomicPart = distance23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distance13AVGAtomicPart = distance13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distance13STDAtomicPart = distance13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distanceNRAVGAtomicPart = distanceNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distanceNRSTDAtomicPart = distanceNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distanceRRAVGAtomicPart = distanceRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      distanceRRSTDAtomicPart = distanceRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      densityAVGAtomicPart = densityAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      densitySTDAtomicPart = densitySTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      density12AVGAtomicPart = density12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      density12STDAtomicPart = density12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      density23AVGAtomicPart = density23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      density23STDAtomicPart = density23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      density13AVGAtomicPart = density13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      density13STDAtomicPart = density13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      densityNRAVGAtomicPart = densityNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      densityNRSTDAtomicPart = densityNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      densityRRAVGAtomicPart = densityRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      densityRRSTDAtomicPart = densityRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      betweennessCentralityVarianceAVGAtomicPart = betweennessCentralityVarianceAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVarianceSTDAtomicPart = betweennessCentralityVarianceSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVariance12AVGAtomicPart = betweennessCentralityVariance12AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVariance12STDAtomicPart = betweennessCentralityVariance12STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVariance23AVGAtomicPart = betweennessCentralityVariance23AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVariance23STDAtomicPart = betweennessCentralityVariance23STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVariance13AVGAtomicPart = betweennessCentralityVariance13AVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVariance13STDAtomicPart = betweennessCentralityVariance13STDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVarianceNRAVGAtomicPart = betweennessCentralityVarianceNRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVarianceNRSTDAtomicPart = betweennessCentralityVarianceNRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVarianceRRAVGAtomicPart = betweennessCentralityVarianceRRAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      betweennessCentralityVarianceRRSTDAtomicPart = betweennessCentralityVarianceRRSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      satisfactionAVGAtomicPart = satisfactionAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      satisfactionSTDAtomicPart = satisfactionSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];

      rewiringAVGAtomicPart = rewiringAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      rewiringSTDAtomicPart = rewiringSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      rewiringCumulativeAVGAtomicPart = rewiringCumulativeAVGAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
      rewiringCumulativeSTDAtomicPart = rewiringCumulativeSTDAtomic[mcIndex][hIndex][spanIndex][connectivityIndex][enforcementIndex];
    }

    private void run() {
      Scenario src = new Scenario(h, span, connectivity, enforcement);
      Scenario nr = src.getClone(false, false);
      Scenario rr = src.getClone(true, true);
      int rewiringCumulative = 0;

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
          clusteringWattsStrogatzSTDAtomicPart[t].addAndGet(pow(src.globalClusteringWattsStrogatz, 2));
          double clusteringWattsStrogatz12 = rr.globalClusteringWattsStrogatz - nr.globalClusteringWattsStrogatz;
          clusteringWattsStrogatz12AVGAtomicPart[t].addAndGet(clusteringWattsStrogatz12);
          clusteringWattsStrogatz12STDAtomicPart[t].addAndGet(pow(clusteringWattsStrogatz12, 2));
          double clusteringWattsStrogatz23 = src.globalClusteringWattsStrogatz - rr.globalClusteringWattsStrogatz;
          clusteringWattsStrogatz23AVGAtomicPart[t].addAndGet(clusteringWattsStrogatz23);
          clusteringWattsStrogatz23STDAtomicPart[t].addAndGet(pow(clusteringWattsStrogatz23, 2));
          double clusteringWattsStrogatz13 = src.globalClusteringWattsStrogatz - nr.globalClusteringWattsStrogatz;
          clusteringWattsStrogatz13AVGAtomicPart[t].addAndGet(clusteringWattsStrogatz13);
          clusteringWattsStrogatz13STDAtomicPart[t].addAndGet(pow(clusteringWattsStrogatz13, 2));
          clusteringWattsStrogatzNRAVGAtomicPart[t].addAndGet(nr.globalClusteringWattsStrogatz);
          clusteringWattsStrogatzNRSTDAtomicPart[t].addAndGet(pow(nr.globalClusteringWattsStrogatz, 2));
          clusteringWattsStrogatzRRAVGAtomicPart[t].addAndGet(rr.globalClusteringWattsStrogatz);
          clusteringWattsStrogatzRRSTDAtomicPart[t].addAndGet(pow(rr.globalClusteringWattsStrogatz, 2));

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


//          if( density23 != 0 && (src.numFormation + src.numBreak) > 0){
//            int sumsrc = 0;
//            int sumrr = 0;
//            for( int i = 0; i < Main.N; i ++ ){
//              for( int j = i; j < Main.N; j ++ ){
//                if( i != j ){
//                  if( src.network[i][j] ) sumsrc ++;
//                  if( rr.network[i][j] ) sumrr ++;
//                }
//              }
//            }
//            System.out.println(mcIndex+" "+span +" "+t + "\t"+ "ordered" + src.numFormation+" & " +src.numBreak+ " -> "+ rr.numFormationLeft+" " +rr.numBreakLeft+ " . . . " + density23 + " back " + sumsrc +" "+ sumrr);
//          }


          betweennessCentralityVarianceAVGAtomicPart[t].addAndGet(src.betweennessCentralityVariance);
          betweennessCentralityVarianceSTDAtomicPart[t].addAndGet(pow(src.betweennessCentralityVariance, 2));
          double betweennessCentralityVariance12 = rr.betweennessCentralityVariance - nr.betweennessCentralityVariance;
          betweennessCentralityVariance12AVGAtomicPart[t].addAndGet(betweennessCentralityVariance12);
          betweennessCentralityVariance12STDAtomicPart[t].addAndGet(pow(betweennessCentralityVariance12, 2));
          double betweennessCentralityVariance23 = src.betweennessCentralityVariance - rr.betweennessCentralityVariance;
          betweennessCentralityVariance23AVGAtomicPart[t].addAndGet(betweennessCentralityVariance23);
          betweennessCentralityVariance23STDAtomicPart[t].addAndGet(pow(betweennessCentralityVariance23, 2));
          double betweennessCentralityVariance13 = src.betweennessCentralityVariance - nr.betweennessCentralityVariance;
          betweennessCentralityVariance13AVGAtomicPart[t].addAndGet(betweennessCentralityVariance13);
          betweennessCentralityVariance13STDAtomicPart[t].addAndGet(pow(betweennessCentralityVariance13, 2));
          betweennessCentralityVarianceNRAVGAtomicPart[t].addAndGet(nr.betweennessCentralityVariance);
          betweennessCentralityVarianceNRSTDAtomicPart[t].addAndGet(pow(nr.betweennessCentralityVariance, 2));
          betweennessCentralityVarianceRRAVGAtomicPart[t].addAndGet(rr.betweennessCentralityVariance);
          betweennessCentralityVarianceRRSTDAtomicPart[t].addAndGet(pow(rr.betweennessCentralityVariance, 2));
        }
        src.stepForward();
        nr.stepForward();
        rr.stepForward(src.numFormation, src.numBreak, src.isNotConverged);
        synchronized (this) {
          rewiringCumulative += src.numFormation + src.numBreak;
          rewiringAVGAtomicPart[t].addAndGet( src.numFormation + src.numBreak);
          rewiringSTDAtomicPart[t].addAndGet(pow( src.numFormation + src.numBreak, 2));
          rewiringCumulativeAVGAtomicPart[t].addAndGet(rewiringCumulative);
          rewiringCumulativeSTDAtomicPart[t].addAndGet(pow(rewiringCumulative, 2));
        }
      }
    }

  }

}
