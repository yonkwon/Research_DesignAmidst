package DATurbulenceTurnover;

import java.io.File;

public class Main {

  static String RUN_ID = "DATT_MaxInformalFix";

  static final long TIC = System.currentTimeMillis();

  static final boolean GET_GRAPH = true;
  static final boolean GET_MAT = true;
  static final boolean LINK_LEVEL = false;
  static final double LINK_ADD = 0;
  static final boolean DO_POST_REWIRING = true;
  static final boolean LIMIT_LEVEL = false;

  static final int ITERATION = 1000;

  static final int TIME = 500 + 1;

  static final int NUM_SOCIAL_DYNAMICS = 2; // Homophily on Char, on Status, closure, & p-attachment

  static final int N = 100;
  static final int[] SPAN = {2, 3, 4, 5, 6, 7, 8};
  static final int LENGTH_SPAN = SPAN.length;

  static final int INFORMAL_MAX_NUM = 5;
  static final double INFORMAL_INITIAL_PROP = .5; // >= INFORMAL_TURNOVER
  static double INFORMAL_REWIRING_PROP = .2;

  static final int INFORMAL_INITIAL_NUM = (int) ((double) (INFORMAL_MAX_NUM * N) / 2 * INFORMAL_INITIAL_PROP); // >= INFORMAL_TURNOVER
  static int INFORMAL_REWIRING_NUM = (int) (INFORMAL_INITIAL_NUM * INFORMAL_REWIRING_PROP);

  static final int M_OF_BUNDLE = 20;
  static final int M_IN_BUNDLE = 5;

  static final int M = M_OF_BUNDLE * M_IN_BUNDLE;
  static final double M_N = M * N;
  static final double N_DYAD = N * (N - 1D) / 2D;
  static final int N_DYAD_INT = N * (N - 1) / 2;
  static final double M_N_DYAD = M * N_DYAD;

  static final double[] ENFORCEMENT = {1};
//  static final double[] ENFORCEMENT = {0, .5, .8, 1};
  static final int LENGTH_ENFORCEMENT = ENFORCEMENT.length;

  static final double[] TURNOVER_RATE = {0};
//  static final double[] TURNOVER_RATE = {0, .1};
//  static final double[] TURNOVER_RATE = {0, .01, .1};
  static final int LENGTH_TURNOVER = TURNOVER_RATE.length;

  static final double[] TURBULENCE_RATE = {0};
  static final int[] TURBULENCE_INTERVAL = {TIME};
//  static final double[] TURBULENCE_RATE = {0, .1};
//  static final int[] TURBULENCE_INTERVAL = {TIME, 25};
//  static final double[] TURBULENCE_RATE = {0, .1, .1};
//  static final int[] TURBULENCE_INTERVAL = {100, 100, 25};
  static final int LENGTH_TURBULENCE = TURBULENCE_RATE.length;

  static final double P_LEARNING = .2;

  static final int[] RESULT_KEY_VALUE = {NUM_SOCIAL_DYNAMICS, LENGTH_SPAN, LENGTH_ENFORCEMENT, LENGTH_TURBULENCE, LENGTH_TURNOVER, TIME};

  static String FILENAME;
  static String PATH_CSV;

  public static void main(String[] args) {
    if( !DO_POST_REWIRING ){
      INFORMAL_REWIRING_PROP = 0;
      INFORMAL_REWIRING_NUM = 0;
    }
    setFileName();
    System.out.println("Target File: " + FILENAME);
    Computation c = new Computation();
    if (GET_GRAPH) {
      c.printNetwork();
    }
    if (GET_MAT) {
      c.doExperiment();
      new MatWriter(c);
    }
    System.out.println("Finished: " + FILENAME);
  }

  private static void setFileName() {
    FILENAME = RUN_ID +
        "I" + ITERATION +
        "_LL" + (LINK_LEVEL ? "t" : "f") +
        (LIMIT_LEVEL ? "_LevelLimit" : "") +
        "_LA" + LINK_ADD +
        "_IM" + INFORMAL_MAX_NUM +
        (DO_POST_REWIRING ?
            "_PL" + "(i" + INFORMAL_INITIAL_PROP + "&t" + INFORMAL_REWIRING_PROP + ")" :
            "_PL" + "(i" + INFORMAL_INITIAL_PROP + "&t0)"
        ) +
        "_T" + TIME +
        "_N" + N +
        "_M" + M_OF_BUNDLE + "X" + M_IN_BUNDLE +
        "_S" + LENGTH_SPAN +
        "_E" + LENGTH_ENFORCEMENT +
        "_TT" + LENGTH_TURBULENCE + "X" + LENGTH_TURNOVER +
        "_P" + P_LEARNING;
    PATH_CSV = new File(".").getAbsolutePath() + "\\" + FILENAME + "\\";
  }
}