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

    Matrix matrixDisagreementAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreementSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDisagreement13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixDissimilarityAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDissimilaritySTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixClusteringAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClustering13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

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

              matrixDisagreementAVG.setDouble(indices, d.disagreementAVG[mc][h][b][e][t]);
              matrixDisagreementSTD.setDouble(indices, d.disagreementSTD[mc][h][b][e][t]);
              matrixDisagreement12AVG.setDouble(indices, d.disagreement12AVG[mc][h][b][e][t]);
              matrixDisagreement12STD.setDouble(indices, d.disagreement12STD[mc][h][b][e][t]);
              matrixDisagreement23AVG.setDouble(indices, d.disagreement23AVG[mc][h][b][e][t]);
              matrixDisagreement23STD.setDouble(indices, d.disagreement23STD[mc][h][b][e][t]);
              matrixDisagreement13AVG.setDouble(indices, d.disagreement13AVG[mc][h][b][e][t]);
              matrixDisagreement13STD.setDouble(indices, d.disagreement13STD[mc][h][b][e][t]);

              matrixDissimilarityAVG.setDouble(indices, d.dissimilarityAVG[mc][h][b][e][t]);
              matrixDissimilaritySTD.setDouble(indices, d.dissimilaritySTD[mc][h][b][e][t]);
              matrixDissimilarity12AVG.setDouble(indices, d.dissimilarity12AVG[mc][h][b][e][t]);
              matrixDissimilarity12STD.setDouble(indices, d.dissimilarity12STD[mc][h][b][e][t]);
              matrixDissimilarity23AVG.setDouble(indices, d.dissimilarity23AVG[mc][h][b][e][t]);
              matrixDissimilarity23STD.setDouble(indices, d.dissimilarity23STD[mc][h][b][e][t]);
              matrixDissimilarity13AVG.setDouble(indices, d.dissimilarity13AVG[mc][h][b][e][t]);
              matrixDissimilarity13STD.setDouble(indices, d.dissimilarity13STD[mc][h][b][e][t]);

              matrixClusteringAVG.setDouble(indices, d.clusteringAVG[mc][h][b][e][t]);
              matrixClusteringSTD.setDouble(indices, d.clusteringSTD[mc][h][b][e][t]);
              matrixClustering12AVG.setDouble(indices, d.clustering12AVG[mc][h][b][e][t]);
              matrixClustering12STD.setDouble(indices, d.clustering12STD[mc][h][b][e][t]);
              matrixClustering23AVG.setDouble(indices, d.clustering23AVG[mc][h][b][e][t]);
              matrixClustering23STD.setDouble(indices, d.clustering23STD[mc][h][b][e][t]);
              matrixClustering13AVG.setDouble(indices, d.clustering13AVG[mc][h][b][e][t]);
              matrixClustering13STD.setDouble(indices, d.clustering13STD[mc][h][b][e][t]);

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
          .addArray("para_degree", Mat5.newScalar(Main.OBSERVATION_SCOPE))
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
          .addArray("para_a_beta", matrixArraySpan)
          .addArray("para_a_e", matrixArrayE)
          .addArray("r_perf_avg", matrixPerformanceAVG)
          .addArray("r_perf_std", matrixPerformanceSTD)
          .addArray("r_perf_12_avg", matrixPerformance12AVG)
          .addArray("r_perf_12_std", matrixPerformance12STD)
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
          .addArray("r_diss_avg", matrixDissimilarityAVG)
          .addArray("r_diss_std", matrixDissimilaritySTD)
          .addArray("r_diss_12_avg", matrixDissimilarity12AVG)
          .addArray("r_diss_12_std", matrixDissimilarity12STD)
          .addArray("r_diss_23_avg", matrixDissimilarity23AVG)
          .addArray("r_diss_23_std", matrixDissimilarity23STD)
          .addArray("r_diss_13_avg", matrixDissimilarity13AVG)
          .addArray("r_diss_13_std", matrixDissimilarity13STD)
          .addArray("r_clus_avg", matrixClusteringAVG)
          .addArray("r_clus_std", matrixClusteringSTD)
          .addArray("r_clus_12_avg", matrixClustering12AVG)
          .addArray("r_clus_12_std", matrixClustering12STD)
          .addArray("r_clus_23_avg", matrixClustering23AVG)
          .addArray("r_clus_23_std", matrixClustering23STD)
          .addArray("r_clus_13_avg", matrixClustering13AVG)
          .addArray("r_clus_13_std", matrixClustering13STD)
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