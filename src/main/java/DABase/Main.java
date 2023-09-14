package DABase;

import java.io.File;

public class Main {

  static final long TIC = System.currentTimeMillis();

  static final boolean GET_GRAPH = false;
  static final boolean GET_MAT = true;

  static final boolean LINK_LEVEL = true;

  static final int ITERATION = 500;
  static final int TIME = 500 + 1;
  static final int NUM_MECHANISM = 3; // Homophily, closure, & p-attachment
  static final int OBSERVATION_SCOPE = 2; // >= 2

  static final int N = 200;
  static final int[] SPAN = {1, 2, 3, 4, 5, 6, 7, 8};
  static final int LENGTH_SPAN = SPAN.length;

  static final int M_OF_BUNDLE = 20;
  static final int M_IN_BUNDLE = 5;
  static final int M = M_OF_BUNDLE * M_IN_BUNDLE;

  static final double[] H = {0, 1};
  static final int LENGTH_H = H.length;

  static final double[] E = {0, .25};
  static final int LENGTH_E = E.length;

  static final double P_LEARNING = .25;

  static final double M_N = M * N;
  static final double N_DYAD = N * (N - 1) / 2D;
  static final double M_N_DYAD = M * N_DYAD;
  static final double NUM_TRIPLET = (double) (N * (N - 1) * (N - 2)) / 6D;

  //Parameters of Hompohily
  static final int L = 1; // Fixed param
  static final double WEIGHT_ON_CHARACTERISTIC = 1D;
  static final double WEIGHT_ON_BELIEF = 1D - WEIGHT_ON_CHARACTERISTIC;

  static final int[] RESULT_KEY_VALUE = {NUM_MECHANISM, LENGTH_H, LENGTH_SPAN, LENGTH_E, TIME};

  static String RUN_ID = "DABase";
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
        "LL" + (LINK_LEVEL?0:1) +
        "O" + OBSERVATION_SCOPE +
        "T" + TIME +
        "N" + N +
        "L" + L +
        "W" + WEIGHT_ON_CHARACTERISTIC +
        "M" + M_OF_BUNDLE + "X" + M_IN_BUNDLE +
        "H" + LENGTH_H +
        "S" + LENGTH_SPAN +
        "E" + LENGTH_E +
        "P" + P_LEARNING;
    PATH_CSV = new File(".").getAbsolutePath() + "\\" + FILENAME + "\\";
  }
}
