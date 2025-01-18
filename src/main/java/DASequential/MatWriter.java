package DASequential;

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

    Matrix matrixDistanceAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistanceSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistance12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistance12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistance23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistance23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistance13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistance13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistanceNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistanceNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistanceRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDistanceRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixDensityAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensitySTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensity12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensity12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensity23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensity23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensity13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensity13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensityNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensityNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensityRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixDensityRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

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

    Matrix matrixBetweennessCentralityVarianceAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVarianceSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVariance12AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVariance12STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVariance23AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVariance23STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVariance13AVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVariance13STD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVarianceNRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVarianceNRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVarianceRRAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixBetweennessCentralityVarianceRRSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixSatisfactionAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixSatisfactionSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    Matrix matrixRewiringAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixRewiringSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixRewiringCumulativeAVG = Mat5.newMatrix(Main.RESULT_KEY_VALUE);
    Matrix matrixRewiringCumulativeSTD = Mat5.newMatrix(Main.RESULT_KEY_VALUE);

    for (int mc = 0; mc < Main.NUM_MECHANISM; mc++) {
      for (int s = 0; s < Main.LENGTH_SPAN; s++) {
        for (int e = 0; e < Main.LENGTH_ENFORCEMENT; e++) {
          for (int t = 0; t < Main.TIME; t++) {
            int[] indices = {mc, s, e, t};
            matrixPerformanceAVG.setDouble(indices, d.performanceAVG[mc][s][e][t]);
            matrixPerformanceSTD.setDouble(indices, d.performanceSTD[mc][s][e][t]);
            matrixPerformanceNRAVG.setDouble(indices, d.performanceNRAVG[mc][s][e][t]);
            matrixPerformanceNRSTD.setDouble(indices, d.performanceNRSTD[mc][s][e][t]);
            matrixPerformanceRRAVG.setDouble(indices, d.performanceRRAVG[mc][s][e][t]);
            matrixPerformanceRRSTD.setDouble(indices, d.performanceRRSTD[mc][s][e][t]);
            matrixPerformance12AVG.setDouble(indices, d.performance12AVG[mc][s][e][t]);
            matrixPerformance12STD.setDouble(indices, d.performance12STD[mc][s][e][t]);
            matrixPerformance23AVG.setDouble(indices, d.performance23AVG[mc][s][e][t]);
            matrixPerformance23STD.setDouble(indices, d.performance23STD[mc][s][e][t]);
            matrixPerformance13AVG.setDouble(indices, d.performance13AVG[mc][s][e][t]);
            matrixPerformance13STD.setDouble(indices, d.performance13STD[mc][s][e][t]);

            matrixDisagreementAVG.setDouble(indices, d.disagreementAVG[mc][s][e][t]);
            matrixDisagreementSTD.setDouble(indices, d.disagreementSTD[mc][s][e][t]);
            matrixDisagreement12AVG.setDouble(indices, d.disagreement12AVG[mc][s][e][t]);
            matrixDisagreement12STD.setDouble(indices, d.disagreement12STD[mc][s][e][t]);
            matrixDisagreement23AVG.setDouble(indices, d.disagreement23AVG[mc][s][e][t]);
            matrixDisagreement23STD.setDouble(indices, d.disagreement23STD[mc][s][e][t]);
            matrixDisagreement13AVG.setDouble(indices, d.disagreement13AVG[mc][s][e][t]);
            matrixDisagreement13STD.setDouble(indices, d.disagreement13STD[mc][s][e][t]);
            matrixDisagreementNRAVG.setDouble(indices, d.disagreementNRAVG[mc][s][e][t]);
            matrixDisagreementNRSTD.setDouble(indices, d.disagreementNRSTD[mc][s][e][t]);
            matrixDisagreementRRAVG.setDouble(indices, d.disagreementRRAVG[mc][s][e][t]);
            matrixDisagreementRRSTD.setDouble(indices, d.disagreementRRSTD[mc][s][e][t]);

            matrixClusteringAVG.setDouble(indices, d.clusteringAVG[mc][s][e][t]);
            matrixClusteringSTD.setDouble(indices, d.clusteringSTD[mc][s][e][t]);
            matrixClustering12AVG.setDouble(indices, d.clustering12AVG[mc][s][e][t]);
            matrixClustering12STD.setDouble(indices, d.clustering12STD[mc][s][e][t]);
            matrixClustering23AVG.setDouble(indices, d.clustering23AVG[mc][s][e][t]);
            matrixClustering23STD.setDouble(indices, d.clustering23STD[mc][s][e][t]);
            matrixClustering13AVG.setDouble(indices, d.clustering13AVG[mc][s][e][t]);
            matrixClustering13STD.setDouble(indices, d.clustering13STD[mc][s][e][t]);
            matrixClusteringNRAVG.setDouble(indices, d.clusteringNRAVG[mc][s][e][t]);
            matrixClusteringNRSTD.setDouble(indices, d.clusteringNRSTD[mc][s][e][t]);
            matrixClusteringRRAVG.setDouble(indices, d.clusteringRRAVG[mc][s][e][t]);
            matrixClusteringRRSTD.setDouble(indices, d.clusteringRRSTD[mc][s][e][t]);

            matrixClusteringWattsStrogatzAVG.setDouble(indices, d.clusteringWattsStrogatzAVG[mc][s][e][t]);
            matrixClusteringWattsStrogatzSTD.setDouble(indices, d.clusteringWattsStrogatzSTD[mc][s][e][t]);
            matrixClusteringWattsStrogatz12AVG.setDouble(indices, d.clusteringWattsStrogatz12AVG[mc][s][e][t]);
            matrixClusteringWattsStrogatz12STD.setDouble(indices, d.clusteringWattsStrogatz12STD[mc][s][e][t]);
            matrixClusteringWattsStrogatz23AVG.setDouble(indices, d.clusteringWattsStrogatz23AVG[mc][s][e][t]);
            matrixClusteringWattsStrogatz23STD.setDouble(indices, d.clusteringWattsStrogatz23STD[mc][s][e][t]);
            matrixClusteringWattsStrogatz13AVG.setDouble(indices, d.clusteringWattsStrogatz13AVG[mc][s][e][t]);
            matrixClusteringWattsStrogatz13STD.setDouble(indices, d.clusteringWattsStrogatz13STD[mc][s][e][t]);
            matrixClusteringWattsStrogatzNRAVG.setDouble(indices, d.clusteringWattsStrogatzNRAVG[mc][s][e][t]);
            matrixClusteringWattsStrogatzNRSTD.setDouble(indices, d.clusteringWattsStrogatzNRSTD[mc][s][e][t]);
            matrixClusteringWattsStrogatzRRAVG.setDouble(indices, d.clusteringWattsStrogatzRRAVG[mc][s][e][t]);
            matrixClusteringWattsStrogatzRRSTD.setDouble(indices, d.clusteringWattsStrogatzRRSTD[mc][s][e][t]);

            matrixCentralizationAVG.setDouble(indices, d.centralizationAVG[mc][s][e][t]);
            matrixCentralizationSTD.setDouble(indices, d.centralizationSTD[mc][s][e][t]);
            matrixCentralization12AVG.setDouble(indices, d.centralization12AVG[mc][s][e][t]);
            matrixCentralization12STD.setDouble(indices, d.centralization12STD[mc][s][e][t]);
            matrixCentralization23AVG.setDouble(indices, d.centralization23AVG[mc][s][e][t]);
            matrixCentralization23STD.setDouble(indices, d.centralization23STD[mc][s][e][t]);
            matrixCentralization13AVG.setDouble(indices, d.centralization13AVG[mc][s][e][t]);
            matrixCentralization13STD.setDouble(indices, d.centralization13STD[mc][s][e][t]);
            matrixCentralizationNRAVG.setDouble(indices, d.centralizationNRAVG[mc][s][e][t]);
            matrixCentralizationNRSTD.setDouble(indices, d.centralizationNRSTD[mc][s][e][t]);
            matrixCentralizationRRAVG.setDouble(indices, d.centralizationRRAVG[mc][s][e][t]);
            matrixCentralizationRRSTD.setDouble(indices, d.centralizationRRSTD[mc][s][e][t]);

            matrixEfficiencyAVG.setDouble(indices, d.efficiencyAVG[mc][s][e][t]);
            matrixEfficiencySTD.setDouble(indices, d.efficiencySTD[mc][s][e][t]);
            matrixEfficiency12AVG.setDouble(indices, d.efficiency12AVG[mc][s][e][t]);
            matrixEfficiency12STD.setDouble(indices, d.efficiency12STD[mc][s][e][t]);
            matrixEfficiency23AVG.setDouble(indices, d.efficiency23AVG[mc][s][e][t]);
            matrixEfficiency23STD.setDouble(indices, d.efficiency23STD[mc][s][e][t]);
            matrixEfficiency13AVG.setDouble(indices, d.efficiency13AVG[mc][s][e][t]);
            matrixEfficiency13STD.setDouble(indices, d.efficiency13STD[mc][s][e][t]);
            matrixEfficiencyNRAVG.setDouble(indices, d.efficiencyNRAVG[mc][s][e][t]);
            matrixEfficiencyNRSTD.setDouble(indices, d.efficiencyNRSTD[mc][s][e][t]);
            matrixEfficiencyRRAVG.setDouble(indices, d.efficiencyRRAVG[mc][s][e][t]);
            matrixEfficiencyRRSTD.setDouble(indices, d.efficiencyRRSTD[mc][s][e][t]);

            matrixDistanceAVG.setDouble(indices, d.distanceAVG[mc][s][e][t]);
            matrixDistanceSTD.setDouble(indices, d.distanceSTD[mc][s][e][t]);
            matrixDistance12AVG.setDouble(indices, d.distance12AVG[mc][s][e][t]);
            matrixDistance12STD.setDouble(indices, d.distance12STD[mc][s][e][t]);
            matrixDistance23AVG.setDouble(indices, d.distance23AVG[mc][s][e][t]);
            matrixDistance23STD.setDouble(indices, d.distance23STD[mc][s][e][t]);
            matrixDistance13AVG.setDouble(indices, d.distance13AVG[mc][s][e][t]);
            matrixDistance13STD.setDouble(indices, d.distance13STD[mc][s][e][t]);
            matrixDistanceNRAVG.setDouble(indices, d.distanceNRAVG[mc][s][e][t]);
            matrixDistanceNRSTD.setDouble(indices, d.distanceNRSTD[mc][s][e][t]);
            matrixDistanceRRAVG.setDouble(indices, d.distanceRRAVG[mc][s][e][t]);
            matrixDistanceRRSTD.setDouble(indices, d.distanceRRSTD[mc][s][e][t]);

            matrixDensityAVG.setDouble(indices, d.densityAVG[mc][s][e][t]);
            matrixDensitySTD.setDouble(indices, d.densitySTD[mc][s][e][t]);
            matrixDensity12AVG.setDouble(indices, d.density12AVG[mc][s][e][t]);
            matrixDensity12STD.setDouble(indices, d.density12STD[mc][s][e][t]);
            matrixDensity23AVG.setDouble(indices, d.density23AVG[mc][s][e][t]);
            matrixDensity23STD.setDouble(indices, d.density23STD[mc][s][e][t]);
            matrixDensity13AVG.setDouble(indices, d.density13AVG[mc][s][e][t]);
            matrixDensity13STD.setDouble(indices, d.density13STD[mc][s][e][t]);
            matrixDensityNRAVG.setDouble(indices, d.densityNRAVG[mc][s][e][t]);
            matrixDensityNRSTD.setDouble(indices, d.densityNRSTD[mc][s][e][t]);
            matrixDensityRRAVG.setDouble(indices, d.densityRRAVG[mc][s][e][t]);
            matrixDensityRRSTD.setDouble(indices, d.densityRRSTD[mc][s][e][t]);

            matrixSigmaAVG.setDouble(indices, d.sigmaAVG[mc][s][e][t]);
            matrixSigmaSTD.setDouble(indices, d.sigmaSTD[mc][s][e][t]);
            matrixSigma12AVG.setDouble(indices, d.sigma12AVG[mc][s][e][t]);
            matrixSigma12STD.setDouble(indices, d.sigma12STD[mc][s][e][t]);
            matrixSigma23AVG.setDouble(indices, d.sigma23AVG[mc][s][e][t]);
            matrixSigma23STD.setDouble(indices, d.sigma23STD[mc][s][e][t]);
            matrixSigma13AVG.setDouble(indices, d.sigma13AVG[mc][s][e][t]);
            matrixSigma13STD.setDouble(indices, d.sigma13STD[mc][s][e][t]);
            matrixSigmaNRAVG.setDouble(indices, d.sigmaNRAVG[mc][s][e][t]);
            matrixSigmaNRSTD.setDouble(indices, d.sigmaNRSTD[mc][s][e][t]);
            matrixSigmaRRAVG.setDouble(indices, d.sigmaRRAVG[mc][s][e][t]);
            matrixSigmaRRSTD.setDouble(indices, d.sigmaRRSTD[mc][s][e][t]);

            matrixOmegaAVG.setDouble(indices, d.omegaAVG[mc][s][e][t]);
            matrixOmegaSTD.setDouble(indices, d.omegaSTD[mc][s][e][t]);
            matrixOmega12AVG.setDouble(indices, d.omega12AVG[mc][s][e][t]);
            matrixOmega12STD.setDouble(indices, d.omega12STD[mc][s][e][t]);
            matrixOmega23AVG.setDouble(indices, d.omega23AVG[mc][s][e][t]);
            matrixOmega23STD.setDouble(indices, d.omega23STD[mc][s][e][t]);
            matrixOmega13AVG.setDouble(indices, d.omega13AVG[mc][s][e][t]);
            matrixOmega13STD.setDouble(indices, d.omega13STD[mc][s][e][t]);
            matrixOmegaNRAVG.setDouble(indices, d.omegaNRAVG[mc][s][e][t]);
            matrixOmegaNRSTD.setDouble(indices, d.omegaNRSTD[mc][s][e][t]);
            matrixOmegaRRAVG.setDouble(indices, d.omegaRRAVG[mc][s][e][t]);
            matrixOmegaRRSTD.setDouble(indices, d.omegaRRSTD[mc][s][e][t]);

            matrixShortestPathVarianceAVG.setDouble(indices, d.shortestPathVarianceAVG[mc][s][e][t]);
            matrixShortestPathVarianceSTD.setDouble(indices, d.shortestPathVarianceSTD[mc][s][e][t]);
            matrixShortestPathVariance12AVG.setDouble(indices, d.shortestPathVariance12AVG[mc][s][e][t]);
            matrixShortestPathVariance12STD.setDouble(indices, d.shortestPathVariance12STD[mc][s][e][t]);
            matrixShortestPathVariance23AVG.setDouble(indices, d.shortestPathVariance23AVG[mc][s][e][t]);
            matrixShortestPathVariance23STD.setDouble(indices, d.shortestPathVariance23STD[mc][s][e][t]);
            matrixShortestPathVariance13AVG.setDouble(indices, d.shortestPathVariance13AVG[mc][s][e][t]);
            matrixShortestPathVariance13STD.setDouble(indices, d.shortestPathVariance13STD[mc][s][e][t]);
            matrixShortestPathVarianceNRAVG.setDouble(indices, d.shortestPathVarianceNRAVG[mc][s][e][t]);
            matrixShortestPathVarianceNRSTD.setDouble(indices, d.shortestPathVarianceNRSTD[mc][s][e][t]);
            matrixShortestPathVarianceRRAVG.setDouble(indices, d.shortestPathVarianceRRAVG[mc][s][e][t]);
            matrixShortestPathVarianceRRSTD.setDouble(indices, d.shortestPathVarianceRRSTD[mc][s][e][t]);

            matrixBetweennessCentralityVarianceAVG.setDouble(indices, d.betweennessCentralityVarianceAVG[mc][s][e][t]);
            matrixBetweennessCentralityVarianceSTD.setDouble(indices, d.betweennessCentralityVarianceSTD[mc][s][e][t]);
            matrixBetweennessCentralityVariance12AVG.setDouble(indices, d.betweennessCentralityVariance12AVG[mc][s][e][t]);
            matrixBetweennessCentralityVariance12STD.setDouble(indices, d.betweennessCentralityVariance12STD[mc][s][e][t]);
            matrixBetweennessCentralityVariance23AVG.setDouble(indices, d.betweennessCentralityVariance23AVG[mc][s][e][t]);
            matrixBetweennessCentralityVariance23STD.setDouble(indices, d.betweennessCentralityVariance23STD[mc][s][e][t]);
            matrixBetweennessCentralityVariance13AVG.setDouble(indices, d.betweennessCentralityVariance13AVG[mc][s][e][t]);
            matrixBetweennessCentralityVariance13STD.setDouble(indices, d.betweennessCentralityVariance13STD[mc][s][e][t]);
            matrixBetweennessCentralityVarianceNRAVG.setDouble(indices, d.betweennessCentralityVarianceNRAVG[mc][s][e][t]);
            matrixBetweennessCentralityVarianceNRSTD.setDouble(indices, d.betweennessCentralityVarianceNRSTD[mc][s][e][t]);
            matrixBetweennessCentralityVarianceRRAVG.setDouble(indices, d.betweennessCentralityVarianceRRAVG[mc][s][e][t]);
            matrixBetweennessCentralityVarianceRRSTD.setDouble(indices, d.betweennessCentralityVarianceRRSTD[mc][s][e][t]);
          }
        }
      }
    }
    Matrix matrixArraySpan = Mat5.newMatrix(new int[]{1, Main.LENGTH_SPAN});
    IntStream.range(0, Main.LENGTH_SPAN).forEach(i -> matrixArraySpan.setDouble(new int[]{0, i}, Main.SPAN[i]));
    Matrix matrixArrayEnforcement = Mat5.newMatrix(new int[]{1, Main.LENGTH_ENFORCEMENT});
    IntStream.range(0, Main.LENGTH_ENFORCEMENT).forEach(i -> matrixArrayEnforcement.setDouble(new int[]{0, i}, Main.ENFORCEMENT[i]));
    try {
      Mat5.newMatFile()
          .addArray("para_iteration", Mat5.newScalar(Main.ITERATION))
          .addArray("para_time", Mat5.newScalar(Main.TIME))
          .addArray("para_g_mech", Mat5.newScalar(Main.NUM_MECHANISM))
          .addArray("para_n", Mat5.newScalar(Main.N))
          .addArray("para_m", Mat5.newScalar(Main.M))
          .addArray("para_informal_init_p", Mat5.newScalar(Main.INFORMAL_INITIAL_PROP))
          .addArray("para_informal_init_n", Mat5.newScalar(Main.INFORMAL_INITIAL_NUM))
          .addArray("para_informal_turn_p", Mat5.newScalar(Main.INFORMAL_TURNOVER_PROP))
          .addArray("para_informal_turn_n", Mat5.newScalar(Main.INFORMAL_TURNOVER_NUM))
          .addArray("para_informal_max", Mat5.newScalar(Main.INFORMAL_MAX_NUM))
          .addArray("para_m_of_bundle", Mat5.newScalar(Main.M_OF_BUNDLE))
          .addArray("para_m_in_bundle", Mat5.newScalar(Main.M_IN_BUNDLE))
          .addArray("para_g_span", Mat5.newScalar(Main.LENGTH_SPAN))
          .addArray("para_a_span", matrixArraySpan)
          .addArray("para_g_enfo", Mat5.newScalar(Main.LENGTH_ENFORCEMENT))
          .addArray("para_a_enfo", matrixArrayEnforcement)
          .addArray("para_p_learning", Mat5.newScalar(Main.P_LEARNING))

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
          .addArray("r_dist_avg", matrixDistanceAVG)
          .addArray("r_dist_std", matrixDistanceSTD)
          .addArray("r_dist_12_avg", matrixDistance12AVG)
          .addArray("r_dist_12_std", matrixDistance12STD)
          .addArray("r_dist_23_avg", matrixDistance23AVG)
          .addArray("r_dist_23_std", matrixDistance23STD)
          .addArray("r_dist_13_avg", matrixDistance13AVG)
          .addArray("r_dist_13_std", matrixDistance13STD)
          .addArray("r_dist_nr_avg", matrixDistanceNRAVG)
          .addArray("r_dist_nr_std", matrixDistanceNRSTD)
          .addArray("r_dist_rr_avg", matrixDistanceRRAVG)
          .addArray("r_dist_rr_std", matrixDistanceRRSTD)
          .addArray("r_dens_avg", matrixDensityAVG)
          .addArray("r_dens_std", matrixDensitySTD)
          .addArray("r_dens_12_avg", matrixDensity12AVG)
          .addArray("r_dens_12_std", matrixDensity12STD)
          .addArray("r_dens_23_avg", matrixDensity23AVG)
          .addArray("r_dens_23_std", matrixDensity23STD)
          .addArray("r_dens_13_avg", matrixDensity13AVG)
          .addArray("r_dens_13_std", matrixDensity13STD)
          .addArray("r_dens_nr_avg", matrixDensityNRAVG)
          .addArray("r_dens_nr_std", matrixDensityNRSTD)
          .addArray("r_dens_rr_avg", matrixDensityRRAVG)
          .addArray("r_dens_rr_std", matrixDensityRRSTD)
          
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
          
          .addArray("r_bcva_avg", matrixBetweennessCentralityVarianceAVG)
          .addArray("r_bcva_std", matrixBetweennessCentralityVarianceSTD)
          .addArray("r_bcva_12_avg", matrixBetweennessCentralityVariance12AVG)
          .addArray("r_bcva_12_std", matrixBetweennessCentralityVariance12STD)
          .addArray("r_bcva_23_avg", matrixBetweennessCentralityVariance23AVG)
          .addArray("r_bcva_23_std", matrixBetweennessCentralityVariance23STD)
          .addArray("r_bcva_13_avg", matrixBetweennessCentralityVariance13AVG)
          .addArray("r_bcva_13_std", matrixBetweennessCentralityVariance13STD)
          .addArray("r_bcva_nr_avg", matrixBetweennessCentralityVarianceNRAVG)
          .addArray("r_bcva_nr_std", matrixBetweennessCentralityVarianceNRSTD)
          .addArray("r_bcva_rr_avg", matrixBetweennessCentralityVarianceRRAVG)
          .addArray("r_bcva_rr_std", matrixBetweennessCentralityVarianceRRSTD)

          .addArray("r_sati_avg", matrixSatisfactionAVG)
          .addArray("r_sati_std", matrixSatisfactionSTD)
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