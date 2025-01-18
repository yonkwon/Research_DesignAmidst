package DATurbulenceTurnover;

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

    Matrix matrixClusteringWattsStrogatzAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatzSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatz12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatz12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatz23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatz23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatz13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatz13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatzNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatzNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatzRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixClusteringWattsStrogatzRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixCentralizationAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralizationSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralization12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralization12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralization23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralization23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralization13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralization13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralizationNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralizationNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralizationRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixCentralizationRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixEfficiencyAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiencySTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiency12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiency12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiency23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiency23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiency13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiency13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiencyNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiencyNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiencyRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixEfficiencyRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixSigmaAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigmaSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigma12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigma12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigma23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigma23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigma13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigma13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigmaNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigmaNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigmaRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSigmaRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixOmegaAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmegaSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmega12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmega12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmega23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmega23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmega13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmega13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmegaNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmegaNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmegaRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixOmegaRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixShortestPathVarianceAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVarianceSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVariance12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVariance12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVariance23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVariance23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVariance13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVariance13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVarianceNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVarianceNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVarianceRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixShortestPathVarianceRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixRewiringAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixRewiringSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixRewiringCumulativeAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixRewiringCumulativeSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    for (int mc = 0; mc < Main.NUM_SOCIAL_DYNAMICS; mc++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
          for (int turb = 0; turb < Main.LENGTH_TURBULENCE; turb++) {
            for (int turn = 0; turn < Main.LENGTH_TURNOVER; turn++) {
              for (int t = 0; t < Main.TIME; t++) {
                int[] indices = {mc, s, e, turb, turn, t};
                matrixPerformanceAVG.setDouble(indices, d.performanceAVG[mc][s][e][turb][turn][t]);
                matrixPerformanceSTD.setDouble(indices, d.performanceSTD[mc][s][e][turb][turn][t]);
                matrixPerformanceNRAVG.setDouble(indices, d.performanceNRAVG[mc][s][e][turb][turn][t]);
                matrixPerformanceNRSTD.setDouble(indices, d.performanceNRSTD[mc][s][e][turb][turn][t]);
                matrixPerformanceRRAVG.setDouble(indices, d.performanceRRAVG[mc][s][e][turb][turn][t]);
                matrixPerformanceRRSTD.setDouble(indices, d.performanceRRSTD[mc][s][e][turb][turn][t]);
                matrixPerformance12AVG.setDouble(indices, d.performance12AVG[mc][s][e][turb][turn][t]);
                matrixPerformance12STD.setDouble(indices, d.performance12STD[mc][s][e][turb][turn][t]);
                matrixPerformance23AVG.setDouble(indices, d.performance23AVG[mc][s][e][turb][turn][t]);
                matrixPerformance23STD.setDouble(indices, d.performance23STD[mc][s][e][turb][turn][t]);
                matrixPerformance13AVG.setDouble(indices, d.performance13AVG[mc][s][e][turb][turn][t]);
                matrixPerformance13STD.setDouble(indices, d.performance13STD[mc][s][e][turb][turn][t]);

                matrixClusteringWattsStrogatzAVG.setDouble(indices, d.clusteringWattsStrogatzAVG[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatzSTD.setDouble(indices, d.clusteringWattsStrogatzSTD[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatz12AVG.setDouble(indices, d.clusteringWattsStrogatz12AVG[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatz12STD.setDouble(indices, d.clusteringWattsStrogatz12STD[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatz23AVG.setDouble(indices, d.clusteringWattsStrogatz23AVG[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatz23STD.setDouble(indices, d.clusteringWattsStrogatz23STD[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatz13AVG.setDouble(indices, d.clusteringWattsStrogatz13AVG[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatz13STD.setDouble(indices, d.clusteringWattsStrogatz13STD[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatzNRAVG.setDouble(indices, d.clusteringWattsStrogatzNRAVG[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatzNRSTD.setDouble(indices, d.clusteringWattsStrogatzNRSTD[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatzRRAVG.setDouble(indices, d.clusteringWattsStrogatzRRAVG[mc][s][e][turb][turn][t]);
                matrixClusteringWattsStrogatzRRSTD.setDouble(indices, d.clusteringWattsStrogatzRRSTD[mc][s][e][turb][turn][t]);

                matrixCentralizationAVG.setDouble(indices, d.centralizationAVG[mc][s][e][turb][turn][t]);
                matrixCentralizationSTD.setDouble(indices, d.centralizationSTD[mc][s][e][turb][turn][t]);
                matrixCentralization12AVG.setDouble(indices, d.centralization12AVG[mc][s][e][turb][turn][t]);
                matrixCentralization12STD.setDouble(indices, d.centralization12STD[mc][s][e][turb][turn][t]);
                matrixCentralization23AVG.setDouble(indices, d.centralization23AVG[mc][s][e][turb][turn][t]);
                matrixCentralization23STD.setDouble(indices, d.centralization23STD[mc][s][e][turb][turn][t]);
                matrixCentralization13AVG.setDouble(indices, d.centralization13AVG[mc][s][e][turb][turn][t]);
                matrixCentralization13STD.setDouble(indices, d.centralization13STD[mc][s][e][turb][turn][t]);
                matrixCentralizationNRAVG.setDouble(indices, d.centralizationNRAVG[mc][s][e][turb][turn][t]);
                matrixCentralizationNRSTD.setDouble(indices, d.centralizationNRSTD[mc][s][e][turb][turn][t]);
                matrixCentralizationRRAVG.setDouble(indices, d.centralizationRRAVG[mc][s][e][turb][turn][t]);
                matrixCentralizationRRSTD.setDouble(indices, d.centralizationRRSTD[mc][s][e][turb][turn][t]);

                matrixEfficiencyAVG.setDouble(indices, d.efficiencyAVG[mc][s][e][turb][turn][t]);
                matrixEfficiencySTD.setDouble(indices, d.efficiencySTD[mc][s][e][turb][turn][t]);
                matrixEfficiency12AVG.setDouble(indices, d.efficiency12AVG[mc][s][e][turb][turn][t]);
                matrixEfficiency12STD.setDouble(indices, d.efficiency12STD[mc][s][e][turb][turn][t]);
                matrixEfficiency23AVG.setDouble(indices, d.efficiency23AVG[mc][s][e][turb][turn][t]);
                matrixEfficiency23STD.setDouble(indices, d.efficiency23STD[mc][s][e][turb][turn][t]);
                matrixEfficiency13AVG.setDouble(indices, d.efficiency13AVG[mc][s][e][turb][turn][t]);
                matrixEfficiency13STD.setDouble(indices, d.efficiency13STD[mc][s][e][turb][turn][t]);
                matrixEfficiencyNRAVG.setDouble(indices, d.efficiencyNRAVG[mc][s][e][turb][turn][t]);
                matrixEfficiencyNRSTD.setDouble(indices, d.efficiencyNRSTD[mc][s][e][turb][turn][t]);
                matrixEfficiencyRRAVG.setDouble(indices, d.efficiencyRRAVG[mc][s][e][turb][turn][t]);
                matrixEfficiencyRRSTD.setDouble(indices, d.efficiencyRRSTD[mc][s][e][turb][turn][t]);

                matrixSigmaAVG.setDouble(indices, d.sigmaAVG[mc][s][e][turb][turn][t]);
                matrixSigmaSTD.setDouble(indices, d.sigmaSTD[mc][s][e][turb][turn][t]);
                matrixSigma12AVG.setDouble(indices, d.sigma12AVG[mc][s][e][turb][turn][t]);
                matrixSigma12STD.setDouble(indices, d.sigma12STD[mc][s][e][turb][turn][t]);
                matrixSigma23AVG.setDouble(indices, d.sigma23AVG[mc][s][e][turb][turn][t]);
                matrixSigma23STD.setDouble(indices, d.sigma23STD[mc][s][e][turb][turn][t]);
                matrixSigma13AVG.setDouble(indices, d.sigma13AVG[mc][s][e][turb][turn][t]);
                matrixSigma13STD.setDouble(indices, d.sigma13STD[mc][s][e][turb][turn][t]);
                matrixSigmaNRAVG.setDouble(indices, d.sigmaNRAVG[mc][s][e][turb][turn][t]);
                matrixSigmaNRSTD.setDouble(indices, d.sigmaNRSTD[mc][s][e][turb][turn][t]);
                matrixSigmaRRAVG.setDouble(indices, d.sigmaRRAVG[mc][s][e][turb][turn][t]);
                matrixSigmaRRSTD.setDouble(indices, d.sigmaRRSTD[mc][s][e][turb][turn][t]);

                matrixOmegaAVG.setDouble(indices, d.omegaAVG[mc][s][e][turb][turn][t]);
                matrixOmegaSTD.setDouble(indices, d.omegaSTD[mc][s][e][turb][turn][t]);
                matrixOmega12AVG.setDouble(indices, d.omega12AVG[mc][s][e][turb][turn][t]);
                matrixOmega12STD.setDouble(indices, d.omega12STD[mc][s][e][turb][turn][t]);
                matrixOmega23AVG.setDouble(indices, d.omega23AVG[mc][s][e][turb][turn][t]);
                matrixOmega23STD.setDouble(indices, d.omega23STD[mc][s][e][turb][turn][t]);
                matrixOmega13AVG.setDouble(indices, d.omega13AVG[mc][s][e][turb][turn][t]);
                matrixOmega13STD.setDouble(indices, d.omega13STD[mc][s][e][turb][turn][t]);
                matrixOmegaNRAVG.setDouble(indices, d.omegaNRAVG[mc][s][e][turb][turn][t]);
                matrixOmegaNRSTD.setDouble(indices, d.omegaNRSTD[mc][s][e][turb][turn][t]);
                matrixOmegaRRAVG.setDouble(indices, d.omegaRRAVG[mc][s][e][turb][turn][t]);
                matrixOmegaRRSTD.setDouble(indices, d.omegaRRSTD[mc][s][e][turb][turn][t]);

                matrixShortestPathVarianceAVG.setDouble(indices, d.shortestPathVarianceAVG[mc][s][e][turb][turn][t]);
                matrixShortestPathVarianceSTD.setDouble(indices, d.shortestPathVarianceSTD[mc][s][e][turb][turn][t]);
                matrixShortestPathVariance12AVG.setDouble(indices, d.shortestPathVariance12AVG[mc][s][e][turb][turn][t]);
                matrixShortestPathVariance12STD.setDouble(indices, d.shortestPathVariance12STD[mc][s][e][turb][turn][t]);
                matrixShortestPathVariance23AVG.setDouble(indices, d.shortestPathVariance23AVG[mc][s][e][turb][turn][t]);
                matrixShortestPathVariance23STD.setDouble(indices, d.shortestPathVariance23STD[mc][s][e][turb][turn][t]);
                matrixShortestPathVariance13AVG.setDouble(indices, d.shortestPathVariance13AVG[mc][s][e][turb][turn][t]);
                matrixShortestPathVariance13STD.setDouble(indices, d.shortestPathVariance13STD[mc][s][e][turb][turn][t]);
                matrixShortestPathVarianceNRAVG.setDouble(indices, d.shortestPathVarianceNRAVG[mc][s][e][turb][turn][t]);
                matrixShortestPathVarianceNRSTD.setDouble(indices, d.shortestPathVarianceNRSTD[mc][s][e][turb][turn][t]);
                matrixShortestPathVarianceRRAVG.setDouble(indices, d.shortestPathVarianceRRAVG[mc][s][e][turb][turn][t]);
                matrixShortestPathVarianceRRSTD.setDouble(indices, d.shortestPathVarianceRRSTD[mc][s][e][turb][turn][t]);
              }
            }
          }
        }
      }
    }
    Matrix matrixArraySpan = Mat5.newMatrix(new int[]{1, Main.LENGTH_SPAN});
    IntStream.range(0, Main.LENGTH_SPAN).forEach(i -> matrixArraySpan.setDouble(new int[]{0, i}, Main.SPAN[i]));
    Matrix matrixArrayEnforcement = Mat5.newMatrix(new int[]{1, Main.LENGTH_ENFORCEMENT});
    IntStream.range(0, Main.LENGTH_ENFORCEMENT).forEach(i -> matrixArrayEnforcement.setDouble(new int[]{0, i}, Main.ENFORCEMENT[i]));
    Matrix matrixArrayTurnoverRate = Mat5.newMatrix(new int[]{1, Main.LENGTH_TURNOVER});
    IntStream.range(0, Main.LENGTH_TURNOVER).forEach(i -> matrixArrayTurnoverRate.setDouble(new int[]{0, i}, Main.TURNOVER_RATE[i]));
    Matrix matrixArrayTurbulenceRate = Mat5.newMatrix(new int[]{1, Main.LENGTH_TURBULENCE});
    IntStream.range(0, Main.LENGTH_TURBULENCE).forEach(i -> matrixArrayTurbulenceRate.setDouble(new int[]{0, i}, Main.TURBULENCE_RATE[i]));
    Matrix matrixArrayTurbulenceInterval = Mat5.newMatrix(new int[]{1, Main.LENGTH_TURBULENCE});
    IntStream.range(0, Main.LENGTH_TURBULENCE).forEach(i -> matrixArrayTurbulenceInterval.setDouble(new int[]{0, i}, Main.TURBULENCE_INTERVAL[i]));
    try {
      Mat5.newMatFile()
          .addArray("para_iteration", Mat5.newScalar(Main.ITERATION))
          .addArray("para_time", Mat5.newScalar(Main.TIME))
          .addArray("para_p_learning", Mat5.newScalar(Main.P_LEARNING))
          .addArray("para_g_mech", Mat5.newScalar(Main.NUM_SOCIAL_DYNAMICS))
          .addArray("para_n", Mat5.newScalar(Main.N))
          .addArray("para_m", Mat5.newScalar(Main.M))
          .addArray("para_informal_init_p", Mat5.newScalar(Main.INFORMAL_INITIAL_PROP))
          .addArray("para_informal_init_n", Mat5.newScalar(Main.INFORMAL_INITIAL_NUM))
          .addArray("para_informal_rewi_p", Mat5.newScalar(Main.INFORMAL_REWIRING_PROP))
          .addArray("para_informal_rewi_n", Mat5.newScalar(Main.INFORMAL_REWIRING_NUM))
          .addArray("para_informal_max", Mat5.newScalar(Main.INFORMAL_MAX_NUM))
          .addArray("para_m_of_bundle", Mat5.newScalar(Main.M_OF_BUNDLE))
          .addArray("para_m_in_bundle", Mat5.newScalar(Main.M_IN_BUNDLE))
          .addArray("para_g_span", Mat5.newScalar(Main.LENGTH_SPAN))
          .addArray("para_a_span", matrixArraySpan)
          .addArray("para_g_enfo", Mat5.newScalar(Main.LENGTH_ENFORCEMENT))
          .addArray("para_a_enfo", matrixArrayEnforcement)
          .addArray("para_g_turn", Mat5.newScalar(Main.LENGTH_TURNOVER))
          .addArray("para_a_turn", matrixArrayTurnoverRate)
          .addArray("para_g_turb", Mat5.newScalar(Main.LENGTH_TURBULENCE))
          .addArray("para_a_turb_r", matrixArrayTurbulenceRate)
          .addArray("para_a_turb_i", matrixArrayTurbulenceInterval)

          .addArray("r_perf_avg", matrixPerformanceAVG)
          .addArray("r_perf_std", matrixPerformanceSTD)
          .addArray("r_perf_nr_avg", matrixPerformanceNRAVG)
          .addArray("r_perf_nr_std", matrixPerformanceNRSTD)
          .addArray("r_perf_rr_avg", matrixPerformanceRRAVG)
          .addArray("r_perf_rr_std", matrixPerformanceRRSTD)
          .addArray("r_perf_12_avg", matrixPerformance12AVG)
          .addArray("r_perf_12_std", matrixPerformance12STD)
          .addArray("r_perf_23_avg", matrixPerformance23AVG)
          .addArray("r_perf_23_std", matrixPerformance23STD)
          .addArray("r_perf_13_avg", matrixPerformance13AVG)
          .addArray("r_perf_13_std", matrixPerformance13STD)
          .addArray("r_perf_23_avg", matrixPerformance23AVG)
          .addArray("r_perf_23_std", matrixPerformance23STD)
          .addArray("r_perf_13_avg", matrixPerformance13AVG)
          .addArray("r_perf_13_std", matrixPerformance13STD)
          .addArray("r_clws_avg", matrixClusteringWattsStrogatzAVG)
          .addArray("r_clws_std", matrixClusteringWattsStrogatzSTD)
          .addArray("r_clws_12_avg", matrixClusteringWattsStrogatz12AVG)
          .addArray("r_clws_12_std", matrixClusteringWattsStrogatz12STD)
          .addArray("r_clws_23_avg", matrixClusteringWattsStrogatz23AVG)
          .addArray("r_clws_23_std", matrixClusteringWattsStrogatz23STD)
          .addArray("r_clws_13_avg", matrixClusteringWattsStrogatz13AVG)
          .addArray("r_clws_13_std", matrixClusteringWattsStrogatz13STD)
          .addArray("r_clws_nr_avg", matrixClusteringWattsStrogatzNRAVG)
          .addArray("r_clws_nr_std", matrixClusteringWattsStrogatzNRSTD)
          .addArray("r_clws_rr_avg", matrixClusteringWattsStrogatzRRAVG)
          .addArray("r_clws_rr_std", matrixClusteringWattsStrogatzRRSTD)
          .addArray("r_cent_avg", matrixCentralizationAVG)
          .addArray("r_cent_std", matrixCentralizationSTD)
          .addArray("r_cent_12_avg", matrixCentralization12AVG)
          .addArray("r_cent_12_std", matrixCentralization12STD)
          .addArray("r_cent_23_avg", matrixCentralization23AVG)
          .addArray("r_cent_23_std", matrixCentralization23STD)
          .addArray("r_cent_13_avg", matrixCentralization13AVG)
          .addArray("r_cent_13_std", matrixCentralization13STD)
          .addArray("r_cent_nr_avg", matrixCentralizationNRAVG)
          .addArray("r_cent_nr_std", matrixCentralizationNRSTD)
          .addArray("r_cent_rr_avg", matrixCentralizationRRAVG)
          .addArray("r_cent_rr_std", matrixCentralizationRRSTD)
          .addArray("r_effi_avg", matrixEfficiencyAVG)
          .addArray("r_effi_std", matrixEfficiencySTD)
          .addArray("r_effi_12_avg", matrixEfficiency12AVG)
          .addArray("r_effi_12_std", matrixEfficiency12STD)
          .addArray("r_effi_23_avg", matrixEfficiency23AVG)
          .addArray("r_effi_23_std", matrixEfficiency23STD)
          .addArray("r_effi_13_avg", matrixEfficiency13AVG)
          .addArray("r_effi_13_std", matrixEfficiency13STD)
          .addArray("r_effi_nr_avg", matrixEfficiencyNRAVG)
          .addArray("r_effi_nr_std", matrixEfficiencyNRSTD)
          .addArray("r_effi_rr_avg", matrixEfficiencyRRAVG)
          .addArray("r_effi_rr_std", matrixEfficiencyRRSTD)
          .addArray("r_sigm_avg", matrixSigmaAVG)
          .addArray("r_sigm_std", matrixSigmaSTD)
          .addArray("r_sigm_12_avg", matrixSigma12AVG)
          .addArray("r_sigm_12_std", matrixSigma12STD)
          .addArray("r_sigm_23_avg", matrixSigma23AVG)
          .addArray("r_sigm_23_std", matrixSigma23STD)
          .addArray("r_sigm_13_avg", matrixSigma13AVG)
          .addArray("r_sigm_13_std", matrixSigma13STD)
          .addArray("r_sigm_nr_avg", matrixSigmaNRAVG)
          .addArray("r_sigm_nr_std", matrixSigmaNRSTD)
          .addArray("r_sigm_rr_avg", matrixSigmaRRAVG)
          .addArray("r_sigm_rr_std", matrixSigmaRRSTD)

          .addArray("r_omeg_avg", matrixOmegaAVG)
          .addArray("r_omeg_std", matrixOmegaSTD)
          .addArray("r_omeg_12_avg", matrixOmega12AVG)
          .addArray("r_omeg_12_std", matrixOmega12STD)
          .addArray("r_omeg_23_avg", matrixOmega23AVG)
          .addArray("r_omeg_23_std", matrixOmega23STD)
          .addArray("r_omeg_13_avg", matrixOmega13AVG)
          .addArray("r_omeg_13_std", matrixOmega13STD)
          .addArray("r_omeg_nr_avg", matrixOmegaNRAVG)
          .addArray("r_omeg_nr_std", matrixOmegaNRSTD)
          .addArray("r_omeg_rr_avg", matrixOmegaRRAVG)
          .addArray("r_omeg_rr_std", matrixOmegaRRSTD)

          .addArray("r_spva_avg", matrixShortestPathVarianceAVG)
          .addArray("r_spva_std", matrixShortestPathVarianceSTD)
          .addArray("r_spva_12_avg", matrixShortestPathVariance12AVG)
          .addArray("r_spva_12_std", matrixShortestPathVariance12STD)
          .addArray("r_spva_23_avg", matrixShortestPathVariance23AVG)
          .addArray("r_spva_23_std", matrixShortestPathVariance23STD)
          .addArray("r_spva_13_avg", matrixShortestPathVariance13AVG)
          .addArray("r_spva_13_std", matrixShortestPathVariance13STD)
          .addArray("r_spva_nr_avg", matrixShortestPathVarianceNRAVG)
          .addArray("r_spva_nr_std", matrixShortestPathVarianceNRSTD)
          .addArray("r_spva_rr_avg", matrixShortestPathVarianceRRAVG)
          .addArray("r_spva_rr_std", matrixShortestPathVarianceRRSTD)

          .addArray("r_rewi_avg", matrixRewiringAVG)
          .addArray("r_rewi_std", matrixRewiringSTD)
          .addArray("r_rewc_avg", matrixRewiringCumulativeAVG)
          .addArray("r_rewc_std", matrixRewiringCumulativeSTD)
          .addArray("perf_seconds", Mat5.newScalar((System.currentTimeMillis() - Main.TIC) / 1000))

          .writeTo(Sinks.newStreamingFile(new File(Main.FILENAME + ".mat")));

      System.out.println("File Printed");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}