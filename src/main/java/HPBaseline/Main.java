package HPBaseline;

import java.io.File;

public class ADMain {

  /*
    Version: 230721 Assortative Distribution
  */

//  static final int NUM_THREAD = Runtime.getRuntime().availableProcessors();
  static final long TIC = System.currentTimeMillis();

  static final boolean GET_GRAPH = false;
  static final boolean GET_OPTIMAL_BETA = false;
  static final boolean DO_EXPERIMENT = true;
  static final boolean EXPERIMENT_IS_DECOMPOSITION = true;
  static final double WEIGHT_ON_CHARACTERISTIC = 1D;
//  static final double WEIGHT_ON_CHARACTERISTIC = 0D;
  static final double WEIGHT_ON_BELIEF = 1D - WEIGHT_ON_CHARACTERISTIC;

  static final int ITERATION = 10_000;
  static final int TIME = 500 + 1;

//  static final int N_OF_UNIT = 5;
//  static final int N_IN_UNIT = 8;
  static final int N_OF_UNIT = 8;
  static final int N_IN_UNIT = 10;
//  static final int N_OF_UNIT = 10;
//  static final int N_IN_UNIT = 15;
  static final int N = N_OF_UNIT * N_IN_UNIT;
  static final int DENSITY = N_OF_UNIT * N_IN_UNIT * (N_IN_UNIT - 1) / 2;

  static final int L = 3; // Fixed param
  static final int M_OF_BUNDLE = 20;
  static final int M_IN_BUNDLE = 5;
//  static final int M_OF_BUNDLE = 10;
//  static final int M_IN_BUNDLE = 10;
//  static final int M_OF_BUNDLE = 100;
//  static final int M_IN_BUNDLE = 1;
  static final int M = M_OF_BUNDLE * M_IN_BUNDLE;

  static final class TurbulenceSchedule {

    int[] turbulenceAt;
    int nTurbulence;
    int maxTurbulenceAtIndex;
    double turbulenceStrengthValue;
    double turbulenceStrengthDependence;

    TurbulenceSchedule(
        int[] turbulenceAt,
        double turbulenceStrengthValue,
        double turbulenceStrengthDependence
    ) {
      this.turbulenceAt = turbulenceAt;
      nTurbulence = turbulenceAt.length;
      maxTurbulenceAtIndex = nTurbulence - 1;
      this.turbulenceStrengthValue = turbulenceStrengthValue;
      this.turbulenceStrengthDependence = turbulenceStrengthDependence;
    }
  }

  static TurbulenceSchedule NULL_TURBULENCE_SCHEUDLE = new TurbulenceSchedule(new int[]{}, 0, 0);
  static TurbulenceSchedule[] TURBULENCE_SCHEDULE;

  static int[][] turbulence_at = {{0}};
  static double[] turbulence_strength_value = {0};
  static double[] turbulence_strength_dependence = {0};

  static int GRANULARITY_TURBULENCE_SCHEDULE = turbulence_at.length;
  static final double M_N = M * N;
  static final double N_DYAD = N * (N - 1) / 2D;
  static final double M_N_DYAD = M * N_DYAD;

//  static double[] THETA = {.01, .125, .25, .375, .5, .625, .75, .875, .99};
  static final double[] THETA = {
      .0001, 0.0625, 0.125, 0.1875, 0.25, 0.3125, 0.375, 0.4375,
      0.5, 0.5625, 0.625, 0.6875, 0.75, 0.8125, 0.875, 0.9375, .9999
  };
//  static final double[] THETA = {
//    .0001, 0.0625, 0.125, 0.1875, 0.25, 0.3125, 0.375, 0.4375, 0.5, .75, .9999
//  };
  static final int GRANULARITY_THETA = THETA.length;

  static final int ITERATION_BETA = 100_000;
  static final double[] BETA_CANDIDATE = {
      .086, .087, .088, .089, .09, .091, .092, .093, .094, .095, .097
  };
//  static final double[] BETA_CANDIDATE = {
//      .06, .061, .062, .063, .064, .065, .066, .067
//  };
  static final int GRANULARITY_BETA_CANDIDATE = BETA_CANDIDATE.length;
  static double OPTIMAL_BETA = .090; // 01... Set by 100_000 iterations
  static double OPTIMAL_BETA_LEFT = 1D - OPTIMAL_BETA; // Set by 10000 iterations


//  static final double[] BETA = {0, 1};
//  static double[] BETA = {0, .25, .5, .75, 1.0};
//  static double[] BETA = {0, .125, .25, .375, .5, .625, .75, .875, 1.0};
  static final double[] BETA = {
      0, 0.0625, 0.125, 0.1875, 0.25, 0.3125, 0.375, 0.4375,
      0.5, 0.5625, 0.625, 0.6875, 0.75, 0.8125, 0.875, 0.9375, 1
  };
  static final int GRANULARITY_BETA = BETA.length;

//  static final double[] E = {.25};
  static final double[] E = {0, .25};
  static final int GRANULARITY_E = E.length;

  static final double[] A = {0, .25, .5, .75, 1};
//  static final double[] A = {1};
  static final int GRANULARITY_A = A.length;

  static final double[] H = {0, 1};
//  static final double[] H = {0, .25, .5, .75, 1};
  static final int GRANULARITY_H = H.length;

  static final double P_LEARNING = .25;

  static final int[] RESULT_KEY_VALUE = {
      GRANULARITY_H, GRANULARITY_THETA, GRANULARITY_E, GRANULARITY_A,
      GRANULARITY_TURBULENCE_SCHEDULE, TIME
  };

  static final int[] RESULT_KEY_VALUE_DECOMPOSITION = {
      GRANULARITY_H, GRANULARITY_BETA, GRANULARITY_E, GRANULARITY_A,
      GRANULARITY_TURBULENCE_SCHEDULE, TIME
  };

  static String RUN_ID = "HP230721AssortativeDistribution";
  static String FILENAME = RUN_ID +
      (EXPERIMENT_IS_DECOMPOSITION ?
          //Decomposed
          "Dec_I" + ITERATION
              + "T" + TIME
              + "N" + N_OF_UNIT + "X" + N_IN_UNIT
              + "L" + L
              + "W" + WEIGHT_ON_CHARACTERISTIC
              + "M" + M_OF_BUNDLE + "X" + M_IN_BUNDLE
              + "H" + GRANULARITY_H
              + "Be" + GRANULARITY_THETA + "(Opt" + "" + (GET_OPTIMAL_BETA ? "Calc" : "Set") + ")"
              + "E" + GRANULARITY_E
              + "A" + GRANULARITY_A
              + "Turb" + GRANULARITY_TURBULENCE_SCHEDULE
              + "P" + P_LEARNING :
          //Not Decomposed
          "Com_I" + ITERATION
              + "T" + TIME
              + "N" + N_OF_UNIT + "X" + N_IN_UNIT
              + "L" + L
              + "W" + WEIGHT_ON_CHARACTERISTIC
              + "M" + M_OF_BUNDLE + "X" + M_IN_BUNDLE
              + "H" + GRANULARITY_H
              + "Et" + GRANULARITY_BETA
              + "E" + GRANULARITY_E
              + "A" + GRANULARITY_A
              + "Turb" + GRANULARITY_TURBULENCE_SCHEDULE
              + "P" + P_LEARNING
      );
  static String PATH_CSV = new File(".").getAbsolutePath() + "\\" + FILENAME + "\\";

  public static void main(String[] args) {
    System.out.println("Homophily w Assortative Distribution (230721)");
    System.out.println("Target File: " + RUN_ID);
    fillTurbulenceSchedule();

    if (GET_GRAPH) {
      setFileName();
      ADComputation c = new ADComputation();
      c.printNetwork();
      System.exit(0);
    }

    if (GET_OPTIMAL_BETA) {
      ADComputation c = new ADComputation();
      c.setAndPrintOptimalBeta();
      System.gc();
    } else {
      System.out.println("Optimal Beta Set at " + OPTIMAL_BETA + " by User");
    }

    if (DO_EXPERIMENT) {
      if (EXPERIMENT_IS_DECOMPOSITION) {
        ADDecomposition d = new ADDecomposition();
        d.doExperiment();
        setFileName();
        new ADMatWriter(d);
      } else {
        ADComputation c = new ADComputation();
        c.doExperiment();
        setFileName();
        new ADMatWriter(c);
      }
    }
    System.out.println("Finished: " + FILENAME);
  }

  private static void fillTurbulenceSchedule() {
    TURBULENCE_SCHEDULE = new TurbulenceSchedule[GRANULARITY_TURBULENCE_SCHEDULE];
    for (int i = 0; i < GRANULARITY_TURBULENCE_SCHEDULE; i++) {
      TURBULENCE_SCHEDULE[i] = new TurbulenceSchedule(turbulence_at[i],
          turbulence_strength_value[i], turbulence_strength_dependence[i]);
    }
  }

  private static void setFileName() {
    FILENAME = RUN_ID +
        (EXPERIMENT_IS_DECOMPOSITION ?
            //Decomposed
            "Dec_I" + ITERATION
                + "T" + TIME
                + "N" + N_OF_UNIT + "X" + N_IN_UNIT
                + "L" + L
                + "W" + WEIGHT_ON_CHARACTERISTIC
                + "M" + M_OF_BUNDLE + "X" + M_IN_BUNDLE
                + "H" + GRANULARITY_H
                + "Th" + GRANULARITY_THETA + "(Opt" + OPTIMAL_BETA + (GET_OPTIMAL_BETA ? "Calc"
                : "Set") + ")"
                + "E" + GRANULARITY_E
                + "A" + GRANULARITY_A
                + "Turb" + GRANULARITY_TURBULENCE_SCHEDULE
                + "P" + P_LEARNING :
            //Not Decomposed
            "Com_I" + ITERATION
                + "T" + TIME
                + "N" + N_OF_UNIT + "X" + N_IN_UNIT
                + "L" + L
                + "W" + WEIGHT_ON_CHARACTERISTIC
                + "M" + M_OF_BUNDLE + "X" + M_IN_BUNDLE
                + "H" + GRANULARITY_H
                + "B" + GRANULARITY_BETA
                + "E" + GRANULARITY_E
                + "A" + GRANULARITY_A
                + "Turb" + GRANULARITY_TURBULENCE_SCHEDULE
                + "P" + P_LEARNING
        );
    PATH_CSV = new File(".").getAbsolutePath() + "\\" + FILENAME + "\\";
  }
}
