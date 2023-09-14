package DABase;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Matrix;
import us.hebi.matlab.mat.types.Sinks;

class MatWriter {

  MatWriter(Computation d) {
    Matrix matrixPerformanceAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformanceSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformance12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformance12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformance23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformance23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformance13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformance13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformanceNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformanceNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformanceRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixPerformanceRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixDisagreementAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreementSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreementNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreementNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreementRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreementRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixClusteringAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    
    Matrix matrixSatisfactionAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSatisfactionSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixRewiringAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixRewiringSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixBetaSampledAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetaSampledSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int h = 0; h < Main.LENGTH_H; h++) {
        for (int b = 0; b < Main.LENGTH_SPAN; b++) {
          for (int e = 0; e < Main.LENGTH_E; e++) {
            for (int t = 0; t < Main.TIME; t++) {
              int[] indices = {mc, h, b, e, t};
              matrixPerformanceAVG.setDouble(indices, d.performanceAVG[mc][h][b][e][t]);
              matrixPerformanceSTD.setDouble(indices, d.performanceSTD[mc][h][b][e][t]);
              matrixPerformance12AVG.setDouble(indices, d.performance12AVG[mc][h][b][e][t]);
              matrixPerformance12STD.setDouble(indices, d.performance12STD[mc][h][b][e][t]);
              matrixPerformance23AVG.setDouble(indices, d.performance23AVG[mc][h][b][e][t]);
              matrixPerformance23STD.setDouble(indices, d.performance23STD[mc][h][b][e][t]);
              matrixPerformance13AVG.setDouble(indices, d.performance13AVG[mc][h][b][e][t]);
              matrixPerformance13STD.setDouble(indices, d.performance13STD[mc][h][b][e][t]);
              matrixPerformanceNRAVG.setDouble(indices, d.performanceNRAVG[mc][h][b][e][t]);
              matrixPerformanceNRSTD.setDouble(indices, d.performanceNRSTD[mc][h][b][e][t]);
              matrixPerformanceRRAVG.setDouble(indices, d.performanceRRAVG[mc][h][b][e][t]);
              matrixPerformanceRRSTD.setDouble(indices, d.performanceRRSTD[mc][h][b][e][t]);

              matrixDisagreementAVG.setDouble(indices, d.disagreementAVG[mc][h][b][e][t]);
              matrixDisagreementSTD.setDouble(indices, d.disagreementSTD[mc][h][b][e][t]);
              matrixDisagreement12AVG.setDouble(indices, d.disagreement12AVG[mc][h][b][e][t]);
              matrixDisagreement12STD.setDouble(indices, d.disagreement12STD[mc][h][b][e][t]);
              matrixDisagreement23AVG.setDouble(indices, d.disagreement23AVG[mc][h][b][e][t]);
              matrixDisagreement23STD.setDouble(indices, d.disagreement23STD[mc][h][b][e][t]);
              matrixDisagreement13AVG.setDouble(indices, d.disagreement13AVG[mc][h][b][e][t]);
              matrixDisagreement13STD.setDouble(indices, d.disagreement13STD[mc][h][b][e][t]);
              matrixDisagreementNRAVG.setDouble(indices, d.disagreementNRAVG[mc][h][b][e][t]);
              matrixDisagreementNRSTD.setDouble(indices, d.disagreementNRSTD[mc][h][b][e][t]);
              matrixDisagreementRRAVG.setDouble(indices, d.disagreementRRAVG[mc][h][b][e][t]);
              matrixDisagreementRRSTD.setDouble(indices, d.disagreementRRSTD[mc][h][b][e][t]);

              matrixClusteringAVG.setDouble(indices, d.clusteringAVG[mc][h][b][e][t]);
              matrixClusteringSTD.setDouble(indices, d.clusteringSTD[mc][h][b][e][t]);
              matrixClustering12AVG.setDouble(indices, d.clustering12AVG[mc][h][b][e][t]);
              matrixClustering12STD.setDouble(indices, d.clustering12STD[mc][h][b][e][t]);
              matrixClustering23AVG.setDouble(indices, d.clustering23AVG[mc][h][b][e][t]);
              matrixClustering23STD.setDouble(indices, d.clustering23STD[mc][h][b][e][t]);
              matrixClustering13AVG.setDouble(indices, d.clustering13AVG[mc][h][b][e][t]);
              matrixClustering13STD.setDouble(indices, d.clustering13STD[mc][h][b][e][t]);
              matrixClusteringNRAVG.setDouble(indices, d.clusteringNRAVG[mc][h][b][e][t]);
              matrixClusteringNRSTD.setDouble(indices, d.clusteringNRSTD[mc][h][b][e][t]);
              matrixClusteringRRAVG.setDouble(indices, d.clusteringRRAVG[mc][h][b][e][t]);
              matrixClusteringRRSTD.setDouble(indices, d.clusteringRRSTD[mc][h][b][e][t]);

              matrixSatisfactionAVG.setDouble(indices, d.satisfactionAVG[mc][h][b][e][t]);
              matrixSatisfactionSTD.setDouble(indices, d.satisfactionSTD[mc][h][b][e][t]);

              matrixRewiringAVG.setDouble(indices, d.rewiringAVG[mc][h][b][e][t]);
              matrixRewiringSTD.setDouble(indices, d.rewiringSTD[mc][h][b][e][t]);

              matrixBetaSampledAVG.setDouble(indices, d.sampleBetaAVG[mc][h][b][e][t]);
              matrixBetaSampledSTD.setDouble(indices, d.sampleBetaSTD[mc][h][b][e][t]);
            }
          }
        }
      }
    }

    Matrix matrixArrayH = Mat5.newMatrix(new int[]{1, Main.LENGTH_H});
    Matrix matrixArraySpan = Mat5.newMatrix(new int[]{1, Main.LENGTH_SPAN});
    Matrix matrixArrayE = Mat5.newMatrix(new int[]{1, Main.LENGTH_E});

    IntStream.range(0, Main.LENGTH_H).forEach(i -> matrixArrayH.setDouble(new int[]{0, i}, Main.H[i]));
    IntStream.range(0, Main.LENGTH_SPAN).forEach(i -> matrixArraySpan.setDouble(new int[]{0, i}, Main.SPAN[i]));
    IntStream.range(0, Main.LENGTH_E).forEach(i -> matrixArrayE.setDouble(new int[]{0, i}, Main.E[i]));

    try {
      Mat5.newMatFile()
          .addArray("para_w", Mat5.newScalar(Main.WEIGHT_ON_CHARACTERISTIC))
          .addArray("para_iteration", Mat5.newScalar(Main.ITERATION))
          .addArray("para_time", Mat5.newScalar(Main.TIME))
          .addArray("para_o", Mat5.newScalar(Main.OBSERVATION_SCOPE))
          .addArray("para_g_mech", Mat5.newScalar(Main.NUM_MECHANISM))
          .addArray("para_n", Mat5.newScalar(Main.N))
          .addArray("para_l", Mat5.newScalar(Main.L))
          .addArray("para_m", Mat5.newScalar(Main.M))
          .addArray("para_m_of_bundle", Mat5.newScalar(Main.M_OF_BUNDLE))
          .addArray("para_m_in_bundle", Mat5.newScalar(Main.M_IN_BUNDLE))
          .addArray("para_g_h", Mat5.newScalar(Main.LENGTH_H))
          .addArray("para_g_span", Mat5.newScalar(Main.LENGTH_SPAN))
          .addArray("para_g_e", Mat5.newScalar(Main.LENGTH_E))
          .addArray("para_p_learning", Mat5.newScalar(Main.P_LEARNING))
          .addArray("para_a_h", matrixArrayH)
          .addArray("para_a_span", matrixArraySpan)
          .addArray("para_a_e", matrixArrayE)
          .addArray("r_perf_avg", matrixPerformanceAVG)
          .addArray("r_perf_std", matrixPerformanceSTD)
          .addArray("r_perf_12_avg", matrixPerformance12AVG)
          .addArray("r_perf_12_std", matrixPerformance12STD)
          .addArray("r_perf_23_avg", matrixPerformance23AVG)
          .addArray("r_perf_23_std", matrixPerformance23STD)
          .addArray("r_perf_13_avg", matrixPerformance13AVG)
          .addArray("r_perf_13_std", matrixPerformance13STD)
          .addArray("r_perf_nr_avg", matrixPerformanceNRAVG)
          .addArray("r_perf_nr_std", matrixPerformanceNRSTD)
          .addArray("r_perf_rr_avg", matrixPerformanceRRAVG)
          .addArray("r_perf_rr_std", matrixPerformanceRRSTD)
          .addArray("r_perf_23_avg", matrixPerformance23AVG)
          .addArray("r_perf_23_std", matrixPerformance23STD)
          .addArray("r_perf_13_avg", matrixPerformance13AVG)
          .addArray("r_perf_13_std", matrixPerformance13STD)
          .addArray("r_disa_avg", matrixDisagreementAVG)
          .addArray("r_disa_std", matrixDisagreementSTD)
          .addArray("r_disa_12_avg", matrixDisagreement12AVG)
          .addArray("r_disa_12_std", matrixDisagreement12STD)
          .addArray("r_disa_23_avg", matrixDisagreement23AVG)
          .addArray("r_disa_23_std", matrixDisagreement23STD)
          .addArray("r_disa_13_avg", matrixDisagreement13AVG)
          .addArray("r_disa_13_std", matrixDisagreement13STD)
          .addArray("r_disa_nr_avg", matrixDisagreementNRAVG)
          .addArray("r_disa_nr_std", matrixDisagreementNRSTD)
          .addArray("r_disa_rr_avg", matrixDisagreementRRAVG)
          .addArray("r_disa_rr_std", matrixDisagreementRRSTD)
          .addArray("r_clus_avg", matrixClusteringAVG)
          .addArray("r_clus_std", matrixClusteringSTD)
          .addArray("r_clus_12_avg", matrixClustering12AVG)
          .addArray("r_clus_12_std", matrixClustering12STD)
          .addArray("r_clus_23_avg", matrixClustering23AVG)
          .addArray("r_clus_23_std", matrixClustering23STD)
          .addArray("r_clus_13_avg", matrixClustering13AVG)
          .addArray("r_clus_13_std", matrixClustering13STD)
          .addArray("r_clus_nr_avg", matrixClusteringNRAVG)
          .addArray("r_clus_nr_std", matrixClusteringNRSTD)
          .addArray("r_clus_rr_avg", matrixClusteringRRAVG)
          .addArray("r_clus_rr_std", matrixClusteringRRSTD)
          .addArray("r_sati_avg", matrixSatisfactionAVG)
          .addArray("r_sati_std", matrixSatisfactionSTD)
          .addArray("r_rewi_avg", matrixRewiringAVG)
          .addArray("r_rewi_std", matrixRewiringSTD)
          .addArray("r_seta_avg", matrixBetaSampledAVG)
          .addArray("r_seta_std", matrixBetaSampledSTD)
          .addArray("perf_seconds", Mat5.newScalar((System.currentTimeMillis() - Main.TIC) / 1000))

          .writeTo(Sinks.newStreamingFile(new File(Main.FILENAME + ".mat")));

      System.out.println("File Printed");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}