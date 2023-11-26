package DAFormation;

import java.io.File;

public class Main {

  static final long TIC = System.currentTimeMillis();

  static final boolean GET_GRAPH = true;
  static final boolean GET_MAT = true;
  static final boolean LINK_LEVEL = false;

  static final int ITERATION = 1_000;
  static final int TIME = 100 + 1;

//  static final int ITERATION = 1;
//  static final int TIME = 5 + 1;

  static final int NUM_MECHANISM = 4; // Homophily on Char, on Status, closure, & p-attachment
  static final int OBSERVATION_SCOPE = 3; // >= 2
  static final int MAX_DEGREE = 10; // >= 2

  static final int N = 100;
  static final int[] SPAN = {2, 5, 8, 11, 14, 17, 20, N-1};
//  static final int[] SPAN = {2, 20};
//  static final int N = 20;
//  static final int[] SPAN = {2};
  static final int LENGTH_SPAN = SPAN.length;

  static final int M_OF_BUNDLE = 10;
  static final int M_IN_BUNDLE = 5;
  static final int M = M_OF_BUNDLE * M_IN_BUNDLE;

  static final double[] H = {1};
  static final int LENGTH_H = H.length;

  static final double[] CONNECTIVITY = {0, .02, .04, .06, .08, .1};
  static final int LENGTH_CONNECTIVITY = CONNECTIVITY.length;

  static final double P_LEARNING = .25;

  static final double N_DYAD = N * (N - 1D) / 2D;
  static final double M_N = M * N;
  static final double M_N_DYAD = M * N_DYAD;
  static final double N_TRIPLET = (double) (N * (N - 1D) * (N - 2D)) / 6D;

  //Parameters of Hompohily
  static final int L = 1; // Fixed param
  static final double WEIGHT_ON_CHARACTERISTIC = 1D;
  static final double WEIGHT_ON_BELIEF = 1D - WEIGHT_ON_CHARACTERISTIC;

  static final int[] RESULT_KEY_VALUE = {NUM_MECHANISM, LENGTH_H, LENGTH_SPAN, LENGTH_CONNECTIVITY, TIME};

  static String RUN_ID = "DAFormation";
  static String FILENAME;
  static String PATH_CSV;

  public static void main(String[] args) {
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
        "LL" + (LINK_LEVEL?"t":"f") +
        "O" + OBSERVATION_SCOPE +
        "MD" + MAX_DEGREE +
        "T" + TIME +
        "N" + N +
        "L" + L +
        "W" + WEIGHT_ON_CHARACTERISTIC +
        "M" + M_OF_BUNDLE + "X" + M_IN_BUNDLE +
        "H" + LENGTH_H +
        "S" + LENGTH_SPAN +
        "C" + LENGTH_CONNECTIVITY +
        "P" + P_LEARNING;
    PATH_CSV = new File(".").getAbsolutePath() + "\\" + FILENAME + "\\";
  }
}
