package DASequential;
import java.io.File;

public class Main {

  static String RUN_ID = "DASequelSequel";

  static final long TIC = System.currentTimeMillis();

  static final boolean GET_GRAPH = true;
  static final boolean GET_MAT = true;
  static final boolean LINK_LEVEL = false;
  static final double LINK_ADD = .00;
  static final boolean DO_POST_REWIRING = true;
  static final boolean LIMIT_LEVEL = false;

//  static final int ITERATION = 10_000;
  static final int ITERATION = 1_000;
  static final int TIME = 600 + 1;

  static final int NUM_MECHANISM = 2; // Homophily on Char, on Status, closure, & p-attachment

  static final int N = 100;
  static final int[] SPAN = {2, 3, 4, 5, 6, 7, 8};
  //  static final int[] SPAN = {2, 5, 8};
  static final int LENGTH_SPAN = SPAN.length;

  static final int N_DYAD_INT = N * (N - 1) / 2;
  static final double N_DYAD = N * (N - 1D) / 2D;
  static final int INFORMAL_MAX_NUM = 5;
  static final double INFORMAL_INITIAL_PROP = .25; // >= INFORMAL_TURNOVER
  static double INFORMAL_TURNOVER_PROP = .2;
  static final int INFORMAL_INITIAL_NUM = (int) (INFORMAL_MAX_NUM * N / 2 * INFORMAL_INITIAL_PROP); // >= INFORMAL_TURNOVER
  static int INFORMAL_TURNOVER_NUM = (int) (INFORMAL_INITIAL_NUM * INFORMAL_TURNOVER_PROP);

  static final int M_OF_BUNDLE = 20;
  static final int M_IN_BUNDLE = 5;
  static final int M = M_OF_BUNDLE * M_IN_BUNDLE;
  static final double M_N = M * N;
  static final double M_N_DYAD = M * N_DYAD;

  static final double[] ENFORCEMENT = {1};
  //  static final double[] ENFORCEMENT = {0, 1};
  static final int LENGTH_ENFORCEMENT = ENFORCEMENT.length;
  static final double P_LEARNING = .2;

  //Parameters of Hompohily
  static final int L = 2; // Fixed param

  static final int[] RESULT_KEY_VALUE = {NUM_MECHANISM, LENGTH_SPAN, LENGTH_ENFORCEMENT, TIME};

  static String FILENAME;
  static String PATH_CSV;

  public static void main(String[] args) {
    if( !DO_POST_REWIRING ){
      INFORMAL_TURNOVER_PROP = 0;
      INFORMAL_TURNOVER_NUM = 0;
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
            "_PL" + "(i" + INFORMAL_INITIAL_PROP + "&t" + INFORMAL_TURNOVER_PROP + ")" :
            "_PL" + "(i" + INFORMAL_INITIAL_PROP + "&t0)"
        ) +
        "_T" + TIME +
        "_N" + N +
        "_M" + M_OF_BUNDLE + "X" + M_IN_BUNDLE +
        "_S" + LENGTH_SPAN +
        "_E" + LENGTH_ENFORCEMENT +
        "_P" + P_LEARNING;
    PATH_CSV = new File(".").getAbsolutePath() + "\\" + FILENAME + "\\";
  }
}