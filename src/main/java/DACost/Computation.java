package DACost;

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

  AtomicDouble[][][][][] performanceAVGAtomic;
  AtomicDouble[][][][][] performanceSTDAtomic;
  AtomicDouble[][][][][] performance12AVGAtomic;
  AtomicDouble[][][][][] performance12STDAtomic;
  AtomicDouble[][][][][] performance23AVGAtomic;
  AtomicDouble[][][][][] performance23STDAtomic;
  AtomicDouble[][][][][] performance13AVGAtomic;
  AtomicDouble[][][][][] performance13STDAtomic;
  AtomicDouble[][][][][] performanceNRAVGAtomic;
  AtomicDouble[][][][][] performanceNRSTDAtomic;
  AtomicDouble[][][][][] performanceRRAVGAtomic;
  AtomicDouble[][][][][] performanceRRSTDAtomic;

  AtomicDouble[][][][][] disagreementAVGAtomic;
  AtomicDouble[][][][][] disagreementSTDAtomic;
  AtomicDouble[][][][][] disagreement12AVGAtomic;
  AtomicDouble[][][][][] disagreement12STDAtomic;
  AtomicDouble[][][][][] disagreement23AVGAtomic;
  AtomicDouble[][][][][] disagreement23STDAtomic;
  AtomicDouble[][][][][] disagreement13AVGAtomic;
  AtomicDouble[][][][][] disagreement13STDAtomic;
  AtomicDouble[][][][][] disagreementNRAVGAtomic;
  AtomicDouble[][][][][] disagreementNRSTDAtomic;
  AtomicDouble[][][][][] disagreementRRAVGAtomic;
  AtomicDouble[][][][][] disagreementRRSTDAtomic;

  AtomicDouble[][][][][] clusteringAVGAtomic;
  AtomicDouble[][][][][] clusteringSTDAtomic;
  AtomicDouble[][][][][] clustering12AVGAtomic;
  AtomicDouble[][][][][] clustering12STDAtomic;
  AtomicDouble[][][][][] clustering23AVGAtomic;
  AtomicDouble[][][][][] clustering23STDAtomic;
  AtomicDouble[][][][][] clustering13AVGAtomic;
  AtomicDouble[][][][][] clustering13STDAtomic;
  AtomicDouble[][][][][] clusteringNRAVGAtomic;
  AtomicDouble[][][][][] clusteringNRSTDAtomic;
  AtomicDouble[][][][][] clusteringRRAVGAtomic;
  AtomicDouble[][][][][] clusteringRRSTDAtomic;

  AtomicDouble[][][][][] centralizationAVGAtomic;
  AtomicDouble[][][][][] centralizationSTDAtomic;
  AtomicDouble[][][][][] centralization12AVGAtomic;
  AtomicDouble[][][][][] centralization12STDAtomic;
  AtomicDouble[][][][][] centralization23AVGAtomic;
  AtomicDouble[][][][][] centralization23STDAtomic;
  AtomicDouble[][][][][] centralization13AVGAtomic;
  AtomicDouble[][][][][] centralization13STDAtomic;
  AtomicDouble[][][][][] centralizationNRAVGAtomic;
  AtomicDouble[][][][][] centralizationNRSTDAtomic;
  AtomicDouble[][][][][] centralizationRRAVGAtomic;
  AtomicDouble[][][][][] centralizationRRSTDAtomic;

  AtomicDouble[][][][][] efficiencyAVGAtomic;
  AtomicDouble[][][][][] efficiencySTDAtomic;
  AtomicDouble[][][][][] efficiency12AVGAtomic;
  AtomicDouble[][][][][] efficiency12STDAtomic;
  AtomicDouble[][][][][] efficiency23AVGAtomic;
  AtomicDouble[][][][][] efficiency23STDAtomic;
  AtomicDouble[][][][][] efficiency13AVGAtomic;
  AtomicDouble[][][][][] efficiency13STDAtomic;
  AtomicDouble[][][][][] efficiencyNRAVGAtomic;
  AtomicDouble[][][][][] efficiencyNRSTDAtomic;
  AtomicDouble[][][][][] efficiencyRRAVGAtomic;
  AtomicDouble[][][][][] efficiencyRRSTDAtomic;

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
  double[][][][][] performanceNRAVG;
  double[][][][][] performanceNRSTD;
  double[][][][][] performanceRRAVG;
  double[][][][][] performanceRRSTD;

  double[][][][][] disagreementAVG;
  double[][][][][] disagreementSTD;
  double[][][][][] disagreement12AVG;
  double[][][][][] disagreement12STD;
  double[][][][][] disagreement23AVG;
  double[][][][][] disagreement23STD;
  double[][][][][] disagreement13AVG;
  double[][][][][] disagreement13STD;
  double[][][][][] disagreementNRAVG;
  double[][][][][] disagreementNRSTD;
  double[][][][][] disagreementRRAVG;
  double[][][][][] disagreementRRSTD;

  double[][][][][] clusteringAVG;
  double[][][][][] clusteringSTD;
  double[][][][][] clustering12AVG;
  double[][][][][] clustering12STD;
  double[][][][][] clustering23AVG;
  double[][][][][] clustering23STD;
  double[][][][][] clustering13AVG;
  double[][][][][] clustering13STD;
  double[][][][][] clusteringNRAVG;
  double[][][][][] clusteringNRSTD;
  double[][][][][] clusteringRRAVG;
  double[][][][][] clusteringRRSTD;

  double[][][][][] centralizationAVG;
  double[][][][][] centralizationSTD;
  double[][][][][] centralization12AVG;
  double[][][][][] centralization12STD;
  double[][][][][] centralization23AVG;
  double[][][][][] centralization23STD;
  double[][][][][] centralization13AVG;
  double[][][][][] centralization13STD;
  double[][][][][] centralizationNRAVG;
  double[][][][][] centralizationNRSTD;
  double[][][][][] centralizationRRAVG;
  double[][][][][] centralizationRRSTD;

  double[][][][][] efficiencyAVG;
  double[][][][][] efficiencySTD;
  double[][][][][] efficiency12AVG;
  double[][][][][] efficiency12STD;
  double[][][][][] efficiency23AVG;
  double[][][][][] efficiency23STD;
  double[][][][][] efficiency13AVG;
  double[][][][][] efficiency13STD;
  double[][][][][] efficiencyNRAVG;
  double[][][][][] efficiencyNRSTD;
  double[][][][][] efficiencyRRAVG;
  double[][][][][] efficiencyRRSTD;

  double[][][][][] satisfactionAVG;
  double[][][][][] satisfactionSTD;
  double[][][][][] rewiringAVG;
  double[][][][][] rewiringSTD;

  double[][][][][] sampleBetaAVG;
  double[][][][][] sampleBetaSTD;

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

    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int h = 0; h < Main.LENGTH_H; h++) {
        for (int b = 0; b < Main.LENGTH_SPAN; b++) {
          for (int e = 0; e < Main.LENGTH_E; e++) {
            double strength = Main.H[h];
            int span = Main.SPAN[b];
            double enforcement = Main.E[e];
            String mcString = null;
            switch (mc) {
              case 0 -> mcString = "homophilyChar";
              case 1 -> mcString = "homophilyStat";
              case 2 -> mcString = "closure";
              case 3 -> mcString = "clustering";
            }
            String fileName = Main.RUN_ID + "_" + "h" + strength + "_s" + span + "_e" + enforcement + "_" + mcString;
            Scenario src = new Scenario(mc, strength, span, enforcement);
            src.printCSV(Main.PATH_CSV + fileName + "_t0");
            for (int t = 0; t < Main.TIME; t++) {
              src.stepForward();
            }
            src.printCSV(Main.PATH_CSV + fileName + "_t" + Main.TIME);
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
    performanceAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performance13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    disagreementAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    clusteringAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    centralizationAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    efficiencyAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencySTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency12AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency12STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency23AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency23STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency13AVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency13STDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencyNRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencyNRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencyRRAVGAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencyRRSTDAtomic = new AtomicDouble[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

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
              performanceNRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              performanceNRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              performanceRRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              performanceRRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();

              disagreementAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreementSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement12AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement12STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement23AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement23STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement13AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreement13STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreementNRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreementNRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreementRRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              disagreementRRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();

              clusteringAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clusteringSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering12AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering12STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering23AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering23STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering13AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clustering13STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              clusteringNRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clusteringNRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              clusteringRRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              clusteringRRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();

              centralizationAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralizationSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralization12AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralization12STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralization23AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralization23STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralization13AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralization13STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralizationNRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralizationNRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralizationRRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              centralizationRRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();

              efficiencyAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiencySTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiency12AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiency12STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiency23AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiency23STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiency13AVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiency13STDAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiencyNRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiencyNRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiencyRRAVGAtomic[mc][h][b][e][t] = new AtomicDouble();
              efficiencyRRSTDAtomic[mc][h][b][e][t] = new AtomicDouble();

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
    performanceNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    performanceRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    disagreementAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreement13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    disagreementRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    clusteringAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clustering13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    clusteringRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    centralizationAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralization13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    centralizationRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

    efficiencyAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencySTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency12AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency12STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency23AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency23STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency13AVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiency13STD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencyNRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencyNRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencyRRAVG = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];
    efficiencyRRSTD = new double[Main.NUM_MECHANISM][Main.LENGTH_H][Main.LENGTH_SPAN][Main.LENGTH_E][Main.TIME];

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

              performanceNRAVG[mc][h][s][e][t] = performanceNRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performanceNRSTD[mc][h][s][e][t] = performanceNRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performanceNRSTD[mc][h][s][e][t] = pow(performanceNRSTD[mc][h][s][e][t] - pow(performanceNRAVG[mc][h][s][e][t], 2), .5);

              performanceRRAVG[mc][h][s][e][t] = performanceRRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performanceRRSTD[mc][h][s][e][t] = performanceRRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              performanceRRSTD[mc][h][s][e][t] = pow(performanceRRSTD[mc][h][s][e][t] - pow(performanceRRAVG[mc][h][s][e][t], 2), .5);

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

              disagreementNRAVG[mc][h][s][e][t] = disagreementNRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreementNRSTD[mc][h][s][e][t] = disagreementNRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreementNRSTD[mc][h][s][e][t] = pow(disagreementNRSTD[mc][h][s][e][t] - pow(disagreementNRAVG[mc][h][s][e][t], 2), .5);

              disagreementRRAVG[mc][h][s][e][t] = disagreementRRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreementRRSTD[mc][h][s][e][t] = disagreementRRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              disagreementRRSTD[mc][h][s][e][t] = pow(disagreementRRSTD[mc][h][s][e][t] - pow(disagreementRRAVG[mc][h][s][e][t], 2), .5);

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

              clusteringNRAVG[mc][h][s][e][t] = clusteringNRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clusteringNRSTD[mc][h][s][e][t] = clusteringNRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clusteringNRSTD[mc][h][s][e][t] = pow(clusteringNRSTD[mc][h][s][e][t] - pow(clusteringNRAVG[mc][h][s][e][t], 2), .5);

              clusteringRRAVG[mc][h][s][e][t] = clusteringRRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clusteringRRSTD[mc][h][s][e][t] = clusteringRRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              clusteringRRSTD[mc][h][s][e][t] = pow(clusteringRRSTD[mc][h][s][e][t] - pow(clusteringRRAVG[mc][h][s][e][t], 2), .5);

              centralizationAVG[mc][h][s][e][t] = centralizationAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralizationSTD[mc][h][s][e][t] = centralizationSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralizationSTD[mc][h][s][e][t] = pow(centralizationSTD[mc][h][s][e][t] - pow(centralizationAVG[mc][h][s][e][t], 2), .5);

              centralization12AVG[mc][h][s][e][t] = centralization12AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralization12STD[mc][h][s][e][t] = centralization12STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralization12STD[mc][h][s][e][t] = pow(centralization12STD[mc][h][s][e][t] - pow(centralization12AVG[mc][h][s][e][t], 2), .5);

              centralization23AVG[mc][h][s][e][t] = centralization23AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralization23STD[mc][h][s][e][t] = centralization23STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralization23STD[mc][h][s][e][t] = pow(centralization23STD[mc][h][s][e][t] - pow(centralization23AVG[mc][h][s][e][t], 2), .5);

              centralization13AVG[mc][h][s][e][t] = centralization13AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralization13STD[mc][h][s][e][t] = centralization13STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralization13STD[mc][h][s][e][t] = pow(centralization13STD[mc][h][s][e][t] - pow(centralization13AVG[mc][h][s][e][t], 2), .5);

              centralizationNRAVG[mc][h][s][e][t] = centralizationNRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralizationNRSTD[mc][h][s][e][t] = centralizationNRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralizationNRSTD[mc][h][s][e][t] = pow(centralizationNRSTD[mc][h][s][e][t] - pow(centralizationNRAVG[mc][h][s][e][t], 2), .5);

              centralizationRRAVG[mc][h][s][e][t] = centralizationRRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralizationRRSTD[mc][h][s][e][t] = centralizationRRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              centralizationRRSTD[mc][h][s][e][t] = pow(centralizationRRSTD[mc][h][s][e][t] - pow(centralizationRRAVG[mc][h][s][e][t], 2), .5);

              efficiencyAVG[mc][h][s][e][t] = efficiencyAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiencySTD[mc][h][s][e][t] = efficiencySTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiencySTD[mc][h][s][e][t] = pow(efficiencySTD[mc][h][s][e][t] - pow(efficiencyAVG[mc][h][s][e][t], 2), .5);

              efficiency12AVG[mc][h][s][e][t] = efficiency12AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiency12STD[mc][h][s][e][t] = efficiency12STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiency12STD[mc][h][s][e][t] = pow(efficiency12STD[mc][h][s][e][t] - pow(efficiency12AVG[mc][h][s][e][t], 2), .5);

              efficiency23AVG[mc][h][s][e][t] = efficiency23AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiency23STD[mc][h][s][e][t] = efficiency23STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiency23STD[mc][h][s][e][t] = pow(efficiency23STD[mc][h][s][e][t] - pow(efficiency23AVG[mc][h][s][e][t], 2), .5);

              efficiency13AVG[mc][h][s][e][t] = efficiency13AVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiency13STD[mc][h][s][e][t] = efficiency13STDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiency13STD[mc][h][s][e][t] = pow(efficiency13STD[mc][h][s][e][t] - pow(efficiency13AVG[mc][h][s][e][t], 2), .5);

              efficiencyNRAVG[mc][h][s][e][t] = efficiencyNRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiencyNRSTD[mc][h][s][e][t] = efficiencyNRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiencyNRSTD[mc][h][s][e][t] = pow(efficiencyNRSTD[mc][h][s][e][t] - pow(efficiencyNRAVG[mc][h][s][e][t], 2), .5);

              efficiencyRRAVG[mc][h][s][e][t] = efficiencyRRAVGAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiencyRRSTD[mc][h][s][e][t] = efficiencyRRSTDAtomic[mc][h][s][e][t].get() / Main.ITERATION;
              efficiencyRRSTD[mc][h][s][e][t] = pow(efficiencyRRSTD[mc][h][s][e][t] - pow(efficiencyRRAVG[mc][h][s][e][t], 2), .5);

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
      performanceNRAVGAtomicPart = performanceNRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performanceNRSTDAtomicPart = performanceNRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performanceRRAVGAtomicPart = performanceRRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      performanceRRSTDAtomicPart = performanceRRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      disagreementAVGAtomicPart = disagreementAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreementSTDAtomicPart = disagreementSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement12AVGAtomicPart = disagreement12AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement12STDAtomicPart = disagreement12STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement23AVGAtomicPart = disagreement23AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement23STDAtomicPart = disagreement23STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement13AVGAtomicPart = disagreement13AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreement13STDAtomicPart = disagreement13STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreementNRAVGAtomicPart = disagreementNRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreementNRSTDAtomicPart = disagreementNRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreementRRAVGAtomicPart = disagreementRRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      disagreementRRSTDAtomicPart = disagreementRRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      clusteringAVGAtomicPart = clusteringAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clusteringSTDAtomicPart = clusteringSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering12AVGAtomicPart = clustering12AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering12STDAtomicPart = clustering12STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering23AVGAtomicPart = clustering23AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering23STDAtomicPart = clustering23STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering13AVGAtomicPart = clustering13AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clustering13STDAtomicPart = clustering13STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clusteringNRAVGAtomicPart = clusteringNRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clusteringNRSTDAtomicPart = clusteringNRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clusteringRRAVGAtomicPart = clusteringRRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      clusteringRRSTDAtomicPart = clusteringRRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      centralizationAVGAtomicPart = centralizationAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralizationSTDAtomicPart = centralizationSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralization12AVGAtomicPart = centralization12AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralization12STDAtomicPart = centralization12STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralization23AVGAtomicPart = centralization23AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralization23STDAtomicPart = centralization23STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralization13AVGAtomicPart = centralization13AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralization13STDAtomicPart = centralization13STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralizationNRAVGAtomicPart = centralizationNRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralizationNRSTDAtomicPart = centralizationNRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralizationRRAVGAtomicPart = centralizationRRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      centralizationRRSTDAtomicPart = centralizationRRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];

      efficiencyAVGAtomicPart = efficiencyAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiencySTDAtomicPart = efficiencySTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiency12AVGAtomicPart = efficiency12AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiency12STDAtomicPart = efficiency12STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiency23AVGAtomicPart = efficiency23AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiency23STDAtomicPart = efficiency23STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiency13AVGAtomicPart = efficiency13AVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiency13STDAtomicPart = efficiency13STDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiencyNRAVGAtomicPart = efficiencyNRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiencyNRSTDAtomicPart = efficiencyNRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiencyRRAVGAtomicPart = efficiencyRRAVGAtomic[mcIndex][hIndex][spanIndex][eIndex];
      efficiencyRRSTDAtomicPart = efficiencyRRSTDAtomic[mcIndex][hIndex][spanIndex][eIndex];

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
          performanceNRAVGAtomicPart[t].addAndGet(nr.performanceAvg);
          performanceNRSTDAtomicPart[t].addAndGet(pow(nr.performanceAvg, 2));
          performanceRRAVGAtomicPart[t].addAndGet(rr.performanceAvg);
          performanceRRSTDAtomicPart[t].addAndGet(pow(rr.performanceAvg, 2));

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

          clusteringAVGAtomicPart[t].addAndGet(src.overallClustering);
          clusteringSTDAtomicPart[t].addAndGet(pow(src.overallClustering, 2));
          double clustering12 = rr.overallClustering - nr.overallClustering;
          clustering12AVGAtomicPart[t].addAndGet(clustering12);
          clustering12STDAtomicPart[t].addAndGet(pow(clustering12, 2));
          double clustering23 = src.overallClustering - rr.overallClustering;
          clustering23AVGAtomicPart[t].addAndGet(clustering23);
          clustering23STDAtomicPart[t].addAndGet(pow(clustering23, 2));
          double clustering13 = src.overallClustering - nr.overallClustering;
          clustering13AVGAtomicPart[t].addAndGet(clustering13);
          clustering13STDAtomicPart[t].addAndGet(pow(clustering13, 2));
          clusteringNRAVGAtomicPart[t].addAndGet(nr.overallClustering);
          clusteringNRSTDAtomicPart[t].addAndGet(pow(nr.overallClustering, 2));
          clusteringRRAVGAtomicPart[t].addAndGet(rr.overallClustering);
          clusteringRRSTDAtomicPart[t].addAndGet(pow(rr.overallClustering, 2));

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
