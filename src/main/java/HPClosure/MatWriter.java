package HPClosure;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Matrix;
import us.hebi.matlab.mat.types.Sinks;

class MatWriter {

  MatWriter(Computation c) {
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

    for (int h = 0; h < Main.LENGTH_C; h++) {
      for (int th = 0; th < Main.GRANULARITY_THETA; th++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
          for (int t = 0; t < Main.TIME; t++) {
            int[] indices = {h, th, e, t};
            matrixPerformanceAVG.setDouble(indices, c.performanceAVG[h][th][e][t]);
            matrixPerformanceSTD.setDouble(indices, c.performanceSTD[h][th][e][t]);
            matrixPerformance12AVG.setDouble(indices, c.performance12AVG[h][th][e][t]);
            matrixPerformance12STD.setDouble(indices, c.performance12STD[h][th][e][t]);
            matrixPerformance23AVG.setDouble(indices, c.performance23AVG[h][th][e][t]);
            matrixPerformance23STD.setDouble(indices, c.performance23STD[h][th][e][t]);
            matrixPerformance13AVG.setDouble(indices, c.performance13AVG[h][th][e][t]);
            matrixPerformance13STD.setDouble(indices, c.performance13STD[h][th][e][t]);

            matrixDisagreementAVG.setDouble(indices, c.disagreementAVG[h][th][e][t]);
            matrixDisagreementSTD.setDouble(indices, c.disagreementSTD[h][th][e][t]);
            matrixDisagreement12AVG.setDouble(indices, c.disagreement12AVG[h][th][e][t]);
            matrixDisagreement12STD.setDouble(indices, c.disagreement12STD[h][th][e][t]);
            matrixDisagreement23AVG.setDouble(indices, c.disagreement23AVG[h][th][e][t]);
            matrixDisagreement23STD.setDouble(indices, c.disagreement23STD[h][th][e][t]);
            matrixDisagreement13AVG.setDouble(indices, c.disagreement13AVG[h][th][e][t]);
            matrixDisagreement13STD.setDouble(indices, c.disagreement13STD[h][th][e][t]);

            matrixClusteringAVG.setDouble(indices, c.clusteringAVG[h][th][e][t]);
            matrixClusteringSTD.setDouble(indices, c.clusteringSTD[h][th][e][t]);
            matrixClustering12AVG.setDouble(indices, c.clustering12AVG[h][th][e][t]);
            matrixClustering12STD.setDouble(indices, c.clustering12STD[h][th][e][t]);
            matrixClustering23AVG.setDouble(indices, c.clustering23AVG[h][th][e][t]);
            matrixClustering23STD.setDouble(indices, c.clustering23STD[h][th][e][t]);
            matrixClustering13AVG.setDouble(indices, c.clustering13AVG[h][th][e][t]);
            matrixClustering13STD.setDouble(indices, c.clustering13STD[h][th][e][t]);

            matrixSatisfactionAVG.setDouble(indices, c.satisfactionAVG[h][th][e][t]);
            matrixSatisfactionSTD.setDouble(indices, c.satisfactionSTD[h][th][e][t]);

            matrixRewiringAVG.setDouble(indices, c.rewiringAVG[h][th][e][t]);
            matrixRewiringSTD.setDouble(indices, c.rewiringSTD[h][th][e][t]);

            matrixBetaSampledAVG.setDouble(indices, c.sampleBetaAVG[h][th][e][t]);
            matrixBetaSampledSTD.setDouble(indices, c.sampleBetaSTD[h][th][e][t]);
          }
        }
      }
    }

    Matrix matrixArrayC = Mat5.newMatrix(new int[]{1, Main.LENGTH_C});
    Matrix matrixArrayTheta = Mat5.newMatrix(new int[]{1, Main.GRANULARITY_THETA});
    Matrix matrixArrayBeta = Mat5.newMatrix(new int[]{1, Main.LENGTH_BETA});
    Matrix matrixArrayBetaCandidate = Mat5.newMatrix(new int[]{1, Main.GRANULARITY_BETA_CANDIDATE});
    Matrix matrixArrayE = Mat5.newMatrix(new int[]{1, Main.LENGTH_E});

    IntStream.range(0, Main.LENGTH_C).forEach(i -> matrixArrayC.setDouble(new int[]{0, i}, Main.C[i]));
    IntStream.range(0, Main.GRANULARITY_THETA).forEach(i -> matrixArrayTheta.setDouble(new int[]{0, i}, Main.THETA[i]));
    IntStream.range(0, Main.LENGTH_BETA).forEach(i -> matrixArrayBeta.setDouble(new int[]{0, i}, Main.BETA[i]));
    IntStream.range(0, Main.GRANULARITY_BETA_CANDIDATE).forEach(i -> matrixArrayBetaCandidate.setDouble(new int[]{0, i}, Main.BETA_CANDIDATE[i]));
    IntStream.range(0, Main.LENGTH_E).forEach(i -> matrixArrayE.setDouble(new int[]{0, i}, Main.E[i]));

    try {
      Mat5.newMatFile()
          .addArray("para_is_decomposed", Mat5.newScalar(Main.EXPERIMENT_IS_DECOMPOSITION ? 1 : 0))
          .addArray("para_iteration", Mat5.newScalar(Main.ITERATION))
          .addArray("para_iteration_beta", Mat5.newScalar(Main.ITERATION_BETA))
          .addArray("para_time", Mat5.newScalar(Main.TIME))
          .addArray("para_n", Mat5.newScalar(Main.N))
          .addArray("para_n_of_unit", Mat5.newScalar(Main.N_OF_UNIT))
          .addArray("para_n_in_unit", Mat5.newScalar(Main.N_IN_UNIT))
          .addArray("para_m", Mat5.newScalar(Main.M))
          .addArray("para_m_of_bundle", Mat5.newScalar(Main.M_OF_BUNDLE))
          .addArray("para_m_in_bundle", Mat5.newScalar(Main.M_IN_BUNDLE))
          .addArray("para_g_h", Mat5.newScalar(Main.LENGTH_C))
          .addArray("para_g_theta", Mat5.newScalar(Main.GRANULARITY_THETA))
          .addArray("para_g_beta", Mat5.newScalar(Main.LENGTH_BETA))
          .addArray("para_g_beta_cand", Mat5.newScalar(Main.GRANULARITY_BETA_CANDIDATE))
          .addArray("para_o_beta", Mat5.newScalar(Main.OPTIMAL_BETA))
          .addArray("para_o_beta_set_by_user", Mat5.newScalar(Main.GET_OPTIMAL_BETA ? 1 : 0))
          .addArray("para_g_e", Mat5.newScalar(Main.LENGTH_E))
          .addArray("para_p_learning", Mat5.newScalar(Main.P_LEARNING))
          .addArray("para_a_h", matrixArrayC)
          .addArray("para_a_theta", matrixArrayTheta)
          .addArray("para_a_beta_cand", matrixArrayBetaCandidate)
          .addArray("para_a_beta", matrixArrayBeta)
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
          .addArray("r_clus_avg", matrixClusteringAVG)
          .addArray("r_clus_std", matrixClusteringSTD)
          .addArray("r_clus_12_avg", matrixClustering12AVG)
          .addArray("r_clus_12_std", matrixClustering12STD)
          .addArray("r_clus_23_avg", matrixClustering23AVG)
          .addArray("r_clus_23_std", matrixClustering23STD)
          .addArray("r_clus_13_avg", matrixClustering13AVG)
          .addArray("r_clus_13_std", matrixClustering13STD)
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

  MatWriter(Decomposition d) {
    Matrix matrixPerformanceAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformanceSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixDisagreementAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreementSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixDissimilarityAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilaritySTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixClusteringAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClusteringSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixSatisfactionAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixSatisfactionSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixRewiringAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixRewiringSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixBetaSampledAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixBetaSampledSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE_DECOMPOSITION);

    for (int h = 0; h < Main.LENGTH_C; h++) {
      for (int b = 0; b < Main.LENGTH_BETA; b++) {
        for (int e = 0; e < Main.LENGTH_E; e++) {
          for (int t = 0; t < Main.TIME; t++) {
            int[] indices = {h, b, e, t};
            matrixPerformanceAVG.setDouble(indices, d.performanceAVG[h][b][e][t]);
            matrixPerformanceSTD.setDouble(indices, d.performanceSTD[h][b][e][t]);
            matrixPerformance12AVG.setDouble(indices, d.performance12AVG[h][b][e][t]);
            matrixPerformance12STD.setDouble(indices, d.performance12STD[h][b][e][t]);
            matrixPerformance23AVG.setDouble(indices, d.performance23AVG[h][b][e][t]);
            matrixPerformance23STD.setDouble(indices, d.performance23STD[h][b][e][t]);
            matrixPerformance13AVG.setDouble(indices, d.performance13AVG[h][b][e][t]);
            matrixPerformance13STD.setDouble(indices, d.performance13STD[h][b][e][t]);

            matrixDisagreementAVG.setDouble(indices, d.disagreementAVG[h][b][e][t]);
            matrixDisagreementSTD.setDouble(indices, d.disagreementSTD[h][b][e][t]);
            matrixDisagreement12AVG.setDouble(indices, d.disagreement12AVG[h][b][e][t]);
            matrixDisagreement12STD.setDouble(indices, d.disagreement12STD[h][b][e][t]);
            matrixDisagreement23AVG.setDouble(indices, d.disagreement23AVG[h][b][e][t]);
            matrixDisagreement23STD.setDouble(indices, d.disagreement23STD[h][b][e][t]);
            matrixDisagreement13AVG.setDouble(indices, d.disagreement13AVG[h][b][e][t]);
            matrixDisagreement13STD.setDouble(indices, d.disagreement13STD[h][b][e][t]);

            matrixClusteringAVG.setDouble(indices, d.clusteringAVG[h][b][e][t]);
            matrixClusteringSTD.setDouble(indices, d.clusteringSTD[h][b][e][t]);
            matrixClustering12AVG.setDouble(indices, d.clustering12AVG[h][b][e][t]);
            matrixClustering12STD.setDouble(indices, d.clustering12STD[h][b][e][t]);
            matrixClustering23AVG.setDouble(indices, d.clustering23AVG[h][b][e][t]);
            matrixClustering23STD.setDouble(indices, d.clustering23STD[h][b][e][t]);
            matrixClustering13AVG.setDouble(indices, d.clustering13AVG[h][b][e][t]);
            matrixClustering13STD.setDouble(indices, d.clustering13STD[h][b][e][t]);

            matrixSatisfactionAVG.setDouble(indices, d.satisfactionAVG[h][b][e][t]);
            matrixSatisfactionSTD.setDouble(indices, d.satisfactionSTD[h][b][e][t]);

            matrixRewiringAVG.setDouble(indices, d.rewiringAVG[h][b][e][t]);
            matrixRewiringSTD.setDouble(indices, d.rewiringSTD[h][b][e][t]);

            matrixBetaSampledAVG.setDouble(indices, d.sampleBetaAVG[h][b][e][t]);
            matrixBetaSampledSTD.setDouble(indices, d.sampleBetaSTD[h][b][e][t]);
          }
        }
      }
    }

    Matrix matrixArrayC = Mat5.newMatrix(new int[]{1, Main.LENGTH_C});
    Matrix matrixArrayBeta = Mat5.newMatrix(new int[]{1, Main.LENGTH_BETA});
    Matrix matrixArrayE = Mat5.newMatrix(new int[]{1, Main.LENGTH_E});

    IntStream.range(0, Main.LENGTH_C).forEach(i -> matrixArrayC.setDouble(new int[]{0, i}, Main.C[i]));
    IntStream.range(0, Main.LENGTH_BETA).forEach(i -> matrixArrayBeta.setDouble(new int[]{0, i}, Main.BETA[i]));
    IntStream.range(0, Main.LENGTH_E).forEach(i -> matrixArrayE.setDouble(new int[]{0, i}, Main.E[i]));

    try {
      Mat5.newMatFile()
          .addArray("para_is_decomposed", Mat5.newScalar(Main.EXPERIMENT_IS_DECOMPOSITION ? 1 : 0))
          .addArray("para_iteration", Mat5.newScalar(Main.ITERATION))
          .addArray("para_iteration_beta", Mat5.newScalar(Main.ITERATION_BETA))
          .addArray("para_time", Mat5.newScalar(Main.TIME))
          .addArray("para_n", Mat5.newScalar(Main.N))
          .addArray("para_n_of_unit", Mat5.newScalar(Main.N_OF_UNIT))
          .addArray("para_n_in_unit", Mat5.newScalar(Main.N_IN_UNIT))
          .addArray("para_m", Mat5.newScalar(Main.M))
          .addArray("para_m_of_bundle", Mat5.newScalar(Main.M_OF_BUNDLE))
          .addArray("para_m_in_bundle", Mat5.newScalar(Main.M_IN_BUNDLE))
          .addArray("para_g_h", Mat5.newScalar(Main.LENGTH_C))
          .addArray("para_g_beta", Mat5.newScalar(Main.LENGTH_BETA))
          .addArray("para_g_e", Mat5.newScalar(Main.LENGTH_E))
          .addArray("para_g_beta_cand", Mat5.newScalar(Main.GRANULARITY_BETA_CANDIDATE))
          .addArray("para_o_beta", Mat5.newScalar(Main.OPTIMAL_BETA))
          .addArray("para_o_beta_set_by_user", Mat5.newScalar(Main.GET_OPTIMAL_BETA ? 1 : 0))
          .addArray("para_p_learning", Mat5.newScalar(Main.P_LEARNING))
          .addArray("para_a_h", matrixArrayC)
          .addArray("para_a_beta", matrixArrayBeta)
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