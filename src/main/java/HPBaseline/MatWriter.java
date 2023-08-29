package HPBaseline;

import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import us.hebi.matlab.mat.format.Mat5;
import us.hebi.matlab.mat.types.Matrix;
import us.hebi.matlab.mat.types.Sinks;

class ADMatWriter {

  ADMatWriter(ADComputation c) {
    Matrix matrixPerformanceAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixPerformanceSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixPerformance12AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixPerformance12STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixPerformance23AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixPerformance23STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixPerformance13AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixPerformance13STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);

    Matrix matrixDisagreementAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDisagreementSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDisagreement12AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDisagreement12STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDisagreement23AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDisagreement23STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDisagreement13AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDisagreement13STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);

    Matrix matrixDissimilarityAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDissimilaritySTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity12AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity12STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity23AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity23STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity13AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixDissimilarity13STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);

    Matrix matrixClusteringAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixClusteringSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixClustering12AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixClustering12STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixClustering23AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixClustering23STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixClustering13AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixClustering13STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);

    Matrix matrixSatisfactionAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixSatisfactionSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);

    Matrix matrixRewiringAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixRewiringSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);

    Matrix matrixBetaSampledAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);
    Matrix matrixBetaSampledSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE);

    for (int h = 0; h < ADMain.GRANULARITY_H; h++) {
      for (int th = 0; th < ADMain.GRANULARITY_THETA; th++) {
        for (int e = 0; e < ADMain.GRANULARITY_E; e++) {
          for (int a = 0; a < ADMain.GRANULARITY_A; a++) {
            for (int tb = 0; tb < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; tb++) {
              for (int t = 0; t < ADMain.TIME; t++) {
                int[] indices = {h, th, e, a, tb, t};
                matrixPerformanceAVG.setDouble(indices, c.performanceAVG[h][th][e][a][tb][t]);
                matrixPerformanceSTD.setDouble(indices, c.performanceSTD[h][th][e][a][tb][t]);
                matrixPerformance12AVG.setDouble(indices, c.performance12AVG[h][th][e][a][tb][t]);
                matrixPerformance12STD.setDouble(indices, c.performance12STD[h][th][e][a][tb][t]);
                matrixPerformance23AVG.setDouble(indices, c.performance23AVG[h][th][e][a][tb][t]);
                matrixPerformance23STD.setDouble(indices, c.performance23STD[h][th][e][a][tb][t]);
                matrixPerformance13AVG.setDouble(indices, c.performance13AVG[h][th][e][a][tb][t]);
                matrixPerformance13STD.setDouble(indices, c.performance13STD[h][th][e][a][tb][t]);

                matrixDisagreementAVG.setDouble(indices, c.disagreementAVG[h][th][e][a][tb][t]);
                matrixDisagreementSTD.setDouble(indices, c.disagreementSTD[h][th][e][a][tb][t]);
                matrixDisagreement12AVG.setDouble(indices, c.disagreement12AVG[h][th][e][a][tb][t]);
                matrixDisagreement12STD.setDouble(indices, c.disagreement12STD[h][th][e][a][tb][t]);
                matrixDisagreement23AVG.setDouble(indices, c.disagreement23AVG[h][th][e][a][tb][t]);
                matrixDisagreement23STD.setDouble(indices, c.disagreement23STD[h][th][e][a][tb][t]);
                matrixDisagreement13AVG.setDouble(indices, c.disagreement13AVG[h][th][e][a][tb][t]);
                matrixDisagreement13STD.setDouble(indices, c.disagreement13STD[h][th][e][a][tb][t]);

                matrixDissimilarityAVG.setDouble(indices, c.dissimilarityAVG[h][th][e][a][tb][t]);
                matrixDissimilaritySTD.setDouble(indices, c.dissimilaritySTD[h][th][e][a][tb][t]);
                matrixDissimilarity12AVG.setDouble(indices, c.dissimilarity12AVG[h][th][e][a][tb][t]);
                matrixDissimilarity12STD.setDouble(indices, c.dissimilarity12STD[h][th][e][a][tb][t]);
                matrixDissimilarity23AVG.setDouble(indices, c.dissimilarity23AVG[h][th][e][a][tb][t]);
                matrixDissimilarity23STD.setDouble(indices, c.dissimilarity23STD[h][th][e][a][tb][t]);
                matrixDissimilarity13AVG.setDouble(indices, c.dissimilarity13AVG[h][th][e][a][tb][t]);
                matrixDissimilarity13STD.setDouble(indices, c.dissimilarity13STD[h][th][e][a][tb][t]);

                matrixClusteringAVG.setDouble(indices, c.clusteringAVG[h][th][e][a][tb][t]);
                matrixClusteringSTD.setDouble(indices, c.clusteringSTD[h][th][e][a][tb][t]);
                matrixClustering12AVG.setDouble(indices, c.clustering12AVG[h][th][e][a][tb][t]);
                matrixClustering12STD.setDouble(indices, c.clustering12STD[h][th][e][a][tb][t]);
                matrixClustering23AVG.setDouble(indices, c.clustering23AVG[h][th][e][a][tb][t]);
                matrixClustering23STD.setDouble(indices, c.clustering23STD[h][th][e][a][tb][t]);
                matrixClustering13AVG.setDouble(indices, c.clustering13AVG[h][th][e][a][tb][t]);
                matrixClustering13STD.setDouble(indices, c.clustering13STD[h][th][e][a][tb][t]);

                matrixSatisfactionAVG.setDouble(indices, c.satisfactionAVG[h][th][e][a][tb][t]);
                matrixSatisfactionSTD.setDouble(indices, c.satisfactionSTD[h][th][e][a][tb][t]);

                matrixRewiringAVG.setDouble(indices, c.rewiringAVG[h][th][e][a][tb][t]);
                matrixRewiringSTD.setDouble(indices, c.rewiringSTD[h][th][e][a][tb][t]);

                matrixBetaSampledAVG.setDouble(indices, c.sampleBetaAVG[h][th][e][a][tb][t]);
                matrixBetaSampledSTD.setDouble(indices, c.sampleBetaSTD[h][th][e][a][tb][t]);
              }
            }
          }
        }
      }
    }

    Matrix matrixArrayH = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_H});
    Matrix matrixArrayTheta = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_THETA});
    Matrix matrixArrayBeta = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_BETA});
    Matrix matrixArrayBetaCandidate = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_BETA_CANDIDATE});
    Matrix matrixArrayE = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_E});
    Matrix matrixArrayA = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_A});

    IntStream.range(0, ADMain.GRANULARITY_H).forEach(i -> matrixArrayH.setDouble(new int[] {0, i}, ADMain.H[i]));
    IntStream.range(0, ADMain.GRANULARITY_THETA).forEach(i -> matrixArrayTheta.setDouble(new int[] {0, i}, ADMain.THETA[i]));
    IntStream.range(0, ADMain.GRANULARITY_BETA).forEach(i -> matrixArrayBeta.setDouble(new int[] {0, i}, ADMain.BETA[i]));
    IntStream.range(0, ADMain.GRANULARITY_BETA_CANDIDATE).forEach(i -> matrixArrayBetaCandidate.setDouble(new int[] {0, i}, ADMain.BETA_CANDIDATE[i]));
    IntStream.range(0, ADMain.GRANULARITY_E).forEach(i -> matrixArrayE.setDouble(new int[] {0, i}, ADMain.E[i]));
    IntStream.range(0, ADMain.GRANULARITY_A).forEach(i -> matrixArrayA.setDouble(new int[] {0, i}, ADMain.A[i]));

    int maxLengthTurbulenceAt = Integer.MIN_VALUE;
    for (int i = 0; i < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; i++) {
      if (ADMain.TURBULENCE_SCHEDULE[i].nTurbulence > maxLengthTurbulenceAt) {
        maxLengthTurbulenceAt = ADMain.TURBULENCE_SCHEDULE[i].nTurbulence;
      }
    }
    Matrix matrixArrayTurbulenceAt = Mat5.newMatrix(new int[]{ADMain.GRANULARITY_TURBULENCE_SCHEDULE, maxLengthTurbulenceAt});
    Matrix matrixArrayTurbulenceStrengthValue = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_TURBULENCE_SCHEDULE});
    Matrix matrixArrayTurbulenceStrengthDependence = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_TURBULENCE_SCHEDULE});

    IntStream.range(0, ADMain.GRANULARITY_TURBULENCE_SCHEDULE).
        forEach(i -> matrixArrayTurbulenceStrengthValue.setDouble(new int[] {0, i}, ADMain.TURBULENCE_SCHEDULE[i].turbulenceStrengthValue));
    IntStream.range(0, ADMain.GRANULARITY_TURBULENCE_SCHEDULE).
        forEach(i -> matrixArrayTurbulenceStrengthDependence.setDouble(new int[] {0, i}, ADMain.TURBULENCE_SCHEDULE[i].turbulenceStrengthDependence));

    for (int i = 0; i < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; i++) {
      int nTurbulenceOfThisScheudle = ADMain.TURBULENCE_SCHEDULE[i].nTurbulence;
      for (int j = 0; j < nTurbulenceOfThisScheudle; j++) {
        matrixArrayTurbulenceAt.setDouble(new int[]{i, j},
            ADMain.TURBULENCE_SCHEDULE[i].turbulenceAt[j]);
      }
    }

    try {
      Mat5.newMatFile()
          .addArray("para_is_decomposed",
              Mat5.newScalar(ADMain.EXPERIMENT_IS_DECOMPOSITION ? 1 : 0))
          .addArray("para_w", Mat5.newScalar(ADMain.WEIGHT_ON_CHARACTERISTIC))
          .addArray("para_iteration", Mat5.newScalar(ADMain.ITERATION))
          .addArray("para_iteration_beta", Mat5.newScalar(ADMain.ITERATION_BETA))
          .addArray("para_time", Mat5.newScalar(ADMain.TIME))
          .addArray("para_n", Mat5.newScalar(ADMain.N))
          .addArray("para_n_of_unit", Mat5.newScalar(ADMain.N_OF_UNIT))
          .addArray("para_n_in_unit", Mat5.newScalar(ADMain.N_IN_UNIT))
          .addArray("para_l", Mat5.newScalar(ADMain.L))
          .addArray("para_m", Mat5.newScalar(ADMain.M))
          .addArray("para_m_of_bundle", Mat5.newScalar(ADMain.M_OF_BUNDLE))
          .addArray("para_m_in_bundle", Mat5.newScalar(ADMain.M_IN_BUNDLE))

          .addArray("para_g_h", Mat5.newScalar(ADMain.GRANULARITY_H))
          .addArray("para_g_theta", Mat5.newScalar(ADMain.GRANULARITY_THETA))
          .addArray("para_g_beta", Mat5.newScalar(ADMain.GRANULARITY_BETA))
          .addArray("para_g_beta_cand", Mat5.newScalar(ADMain.GRANULARITY_BETA_CANDIDATE))
          .addArray("para_o_beta", Mat5.newScalar(ADMain.OPTIMAL_BETA))
          .addArray("para_o_beta_set_by_user", Mat5.newScalar(ADMain.GET_OPTIMAL_BETA ? 1 : 0))
          .addArray("para_g_e", Mat5.newScalar(ADMain.GRANULARITY_E))
          .addArray("para_p_learning", Mat5.newScalar(ADMain.P_LEARNING))

          .addArray("para_a_h", matrixArrayH)
          .addArray("para_a_theta", matrixArrayTheta)
          .addArray("para_a_beta_cand", matrixArrayBetaCandidate)
          .addArray("para_a_beta", matrixArrayBeta)
          .addArray("para_a_e", matrixArrayE)

          .addArray("para_g_turb", Mat5.newScalar(ADMain.GRANULARITY_TURBULENCE_SCHEDULE))
          .addArray("para_a_turb_at", matrixArrayTurbulenceAt)
          .addArray("para_a_turb_value", matrixArrayTurbulenceStrengthValue)
          .addArray("para_a_turb_dependence", matrixArrayTurbulenceStrengthDependence)

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
          .addArray("perf_seconds",
              Mat5.newScalar((System.currentTimeMillis() - ADMain.TIC) / 1000))

          .writeTo(Sinks.newStreamingFile(new File(ADMain.FILENAME + ".mat")));

      System.out.println("File Printed");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  ADMatWriter(ADDecomposition d) {
    Matrix matrixPerformanceAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformanceSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance12AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance12STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance23AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance23STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance13AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixPerformance13STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixDisagreementAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreementSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement12AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement12STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement23AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement23STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement13AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDisagreement13STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixDissimilarityAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilaritySTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity12AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity12STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity23AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity23STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity13AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixDissimilarity13STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixClusteringAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClusteringSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering12AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering12STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering23AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering23STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering13AVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixClustering13STD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixSatisfactionAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixSatisfactionSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixRewiringAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixRewiringSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);

    Matrix matrixBetaSampledAVG = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);
    Matrix matrixBetaSampledSTD = Mat5.newMatrix(ADMain.RESULT_KEY_VALUE_DECOMPOSITION);

    for (int h = 0; h < ADMain.GRANULARITY_H; h++) {
      for (int b = 0; b < ADMain.GRANULARITY_BETA; b++) {
        for (int e = 0; e < ADMain.GRANULARITY_E; e++) {
          for (int a = 0; a < ADMain.GRANULARITY_A; a++) {
            for (int tb = 0; tb < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; tb++) {
              for (int t = 0; t < ADMain.TIME; t++) {
                int[] indices = {h, b, e, a, tb, t};
                matrixPerformanceAVG.setDouble(indices, d.performanceAVG[h][b][e][a][tb][t]);
                matrixPerformanceSTD.setDouble(indices, d.performanceSTD[h][b][e][a][tb][t]);
                matrixPerformance12AVG.setDouble(indices, d.performance12AVG[h][b][e][a][tb][t]);
                matrixPerformance12STD.setDouble(indices, d.performance12STD[h][b][e][a][tb][t]);
                matrixPerformance23AVG.setDouble(indices, d.performance23AVG[h][b][e][a][tb][t]);
                matrixPerformance23STD.setDouble(indices, d.performance23STD[h][b][e][a][tb][t]);
                matrixPerformance13AVG.setDouble(indices, d.performance13AVG[h][b][e][a][tb][t]);
                matrixPerformance13STD.setDouble(indices, d.performance13STD[h][b][e][a][tb][t]);

                matrixDisagreementAVG.setDouble(indices, d.disagreementAVG[h][b][e][a][tb][t]);
                matrixDisagreementSTD.setDouble(indices, d.disagreementSTD[h][b][e][a][tb][t]);
                matrixDisagreement12AVG.setDouble(indices, d.disagreement12AVG[h][b][e][a][tb][t]);
                matrixDisagreement12STD.setDouble(indices, d.disagreement12STD[h][b][e][a][tb][t]);
                matrixDisagreement23AVG.setDouble(indices, d.disagreement23AVG[h][b][e][a][tb][t]);
                matrixDisagreement23STD.setDouble(indices, d.disagreement23STD[h][b][e][a][tb][t]);
                matrixDisagreement13AVG.setDouble(indices, d.disagreement13AVG[h][b][e][a][tb][t]);
                matrixDisagreement13STD.setDouble(indices, d.disagreement13STD[h][b][e][a][tb][t]);

                matrixDissimilarityAVG.setDouble(indices, d.dissimilarityAVG[h][b][e][a][tb][t]);
                matrixDissimilaritySTD.setDouble(indices, d.dissimilaritySTD[h][b][e][a][tb][t]);
                matrixDissimilarity12AVG.setDouble(indices, d.dissimilarity12AVG[h][b][e][a][tb][t]);
                matrixDissimilarity12STD.setDouble(indices, d.dissimilarity12STD[h][b][e][a][tb][t]);
                matrixDissimilarity23AVG.setDouble(indices, d.dissimilarity23AVG[h][b][e][a][tb][t]);
                matrixDissimilarity23STD.setDouble(indices, d.dissimilarity23STD[h][b][e][a][tb][t]);
                matrixDissimilarity13AVG.setDouble(indices, d.dissimilarity13AVG[h][b][e][a][tb][t]);
                matrixDissimilarity13STD.setDouble(indices, d.dissimilarity13STD[h][b][e][a][tb][t]);

                matrixClusteringAVG.setDouble(indices, d.clusteringAVG[h][b][e][a][tb][t]);
                matrixClusteringSTD.setDouble(indices, d.clusteringSTD[h][b][e][a][tb][t]);
                matrixClustering12AVG.setDouble(indices, d.clustering12AVG[h][b][e][a][tb][t]);
                matrixClustering12STD.setDouble(indices, d.clustering12STD[h][b][e][a][tb][t]);
                matrixClustering23AVG.setDouble(indices, d.clustering23AVG[h][b][e][a][tb][t]);
                matrixClustering23STD.setDouble(indices, d.clustering23STD[h][b][e][a][tb][t]);
                matrixClustering13AVG.setDouble(indices, d.clustering13AVG[h][b][e][a][tb][t]);
                matrixClustering13STD.setDouble(indices, d.clustering13STD[h][b][e][a][tb][t]);

                matrixSatisfactionAVG.setDouble(indices, d.satisfactionAVG[h][b][e][a][tb][t]);
                matrixSatisfactionSTD.setDouble(indices, d.satisfactionSTD[h][b][e][a][tb][t]);

                matrixRewiringAVG.setDouble(indices, d.rewiringAVG[h][b][e][a][tb][t]);
                matrixRewiringSTD.setDouble(indices, d.rewiringSTD[h][b][e][a][tb][t]);

                matrixBetaSampledAVG.setDouble(indices, d.sampleBetaAVG[h][b][e][a][tb][t]);
                matrixBetaSampledSTD.setDouble(indices, d.sampleBetaSTD[h][b][e][a][tb][t]);
              }
            }
          }
        }
      }
    }


    Matrix matrixArrayH = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_H});
    Matrix matrixArrayBeta = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_BETA});
    Matrix matrixArrayE = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_E});
    Matrix matrixArrayA = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_A});

    IntStream.range(0, ADMain.GRANULARITY_H).forEach(i -> matrixArrayH.setDouble(new int[] {0, i}, ADMain.H[i]));
    IntStream.range(0, ADMain.GRANULARITY_BETA).forEach(i -> matrixArrayBeta.setDouble(new int[] {0, i}, ADMain.BETA[i]));
    IntStream.range(0, ADMain.GRANULARITY_E).forEach(i -> matrixArrayE.setDouble(new int[] {0, i}, ADMain.E[i]));
    IntStream.range(0, ADMain.GRANULARITY_A).forEach(i -> matrixArrayA.setDouble(new int[] {0, i}, ADMain.A[i]));

    int maxLengthTurbulenceAt = Integer.MIN_VALUE;
    for (int i = 0; i < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; i++) {
      if (ADMain.TURBULENCE_SCHEDULE[i].nTurbulence > maxLengthTurbulenceAt) {
        maxLengthTurbulenceAt = ADMain.TURBULENCE_SCHEDULE[i].nTurbulence;
      }
    }
    Matrix matrixArrayTurbulenceAt = Mat5.newMatrix(new int[]{ADMain.GRANULARITY_TURBULENCE_SCHEDULE, maxLengthTurbulenceAt});
    Matrix matrixArrayTurbulenceStrengthValue = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_TURBULENCE_SCHEDULE});
    Matrix matrixArrayTurbulenceStrengthDependence = Mat5.newMatrix(new int[]{1, ADMain.GRANULARITY_TURBULENCE_SCHEDULE});

    IntStream.range(0, ADMain.GRANULARITY_TURBULENCE_SCHEDULE).
        forEach(i -> matrixArrayTurbulenceStrengthValue.setDouble(new int[] {0, i}, ADMain.TURBULENCE_SCHEDULE[i].turbulenceStrengthValue));
    IntStream.range(0, ADMain.GRANULARITY_TURBULENCE_SCHEDULE).
        forEach(i -> matrixArrayTurbulenceStrengthDependence.setDouble(new int[] {0, i}, ADMain.TURBULENCE_SCHEDULE[i].turbulenceStrengthDependence));

    for (int i = 0; i < ADMain.GRANULARITY_TURBULENCE_SCHEDULE; i++) {
      int nTurbulenceOfThisScheudle = ADMain.TURBULENCE_SCHEDULE[i].nTurbulence;
      for (int j = 0; j < nTurbulenceOfThisScheudle; j++) {
        matrixArrayTurbulenceAt.setDouble(new int[]{i, j},
            ADMain.TURBULENCE_SCHEDULE[i].turbulenceAt[j]);
      }
    }

    try {
      Mat5.newMatFile()
          .addArray("para_is_decomposed",Mat5.newScalar(ADMain.EXPERIMENT_IS_DECOMPOSITION ? 1 : 0))
          .addArray("para_w", Mat5.newScalar(ADMain.WEIGHT_ON_CHARACTERISTIC))
          .addArray("para_iteration", Mat5.newScalar(ADMain.ITERATION))
          .addArray("para_iteration_beta", Mat5.newScalar(ADMain.ITERATION_BETA))
          .addArray("para_time", Mat5.newScalar(ADMain.TIME))
          .addArray("para_n", Mat5.newScalar(ADMain.N))
          .addArray("para_n_of_unit", Mat5.newScalar(ADMain.N_OF_UNIT))
          .addArray("para_n_in_unit", Mat5.newScalar(ADMain.N_IN_UNIT))
          .addArray("para_l", Mat5.newScalar(ADMain.L))
          .addArray("para_m", Mat5.newScalar(ADMain.M))
          .addArray("para_m_of_bundle", Mat5.newScalar(ADMain.M_OF_BUNDLE))
          .addArray("para_m_in_bundle", Mat5.newScalar(ADMain.M_IN_BUNDLE))

          .addArray("para_g_h", Mat5.newScalar(ADMain.GRANULARITY_H))
          .addArray("para_g_beta", Mat5.newScalar(ADMain.GRANULARITY_BETA))
          .addArray("para_g_e", Mat5.newScalar(ADMain.GRANULARITY_E))
          .addArray("para_g_a", Mat5.newScalar(ADMain.GRANULARITY_A))
          .addArray("para_g_beta_cand", Mat5.newScalar(ADMain.GRANULARITY_BETA_CANDIDATE))
          .addArray("para_o_beta", Mat5.newScalar(ADMain.OPTIMAL_BETA))
          .addArray("para_o_beta_set_by_user", Mat5.newScalar(ADMain.GET_OPTIMAL_BETA ? 1 : 0))
          .addArray("para_p_learning", Mat5.newScalar(ADMain.P_LEARNING))

          .addArray("para_a_h", matrixArrayH)
          .addArray("para_a_beta", matrixArrayBeta)
          .addArray("para_a_e", matrixArrayE)
          .addArray("para_a_a", matrixArrayA)

          .addArray("para_g_turb", Mat5.newScalar(ADMain.GRANULARITY_TURBULENCE_SCHEDULE))
          .addArray("para_a_turb_at", matrixArrayTurbulenceAt)
          .addArray("para_a_turb_value", matrixArrayTurbulenceStrengthValue)
          .addArray("para_a_turb_dependence", matrixArrayTurbulenceStrengthDependence)

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
          .addArray("perf_seconds",
              Mat5.newScalar((System.currentTimeMillis() - ADMain.TIC) / 1000))

          .writeTo(Sinks.newStreamingFile(new File(ADMain.FILENAME + ".mat")));

      System.out.println("File Printed");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}