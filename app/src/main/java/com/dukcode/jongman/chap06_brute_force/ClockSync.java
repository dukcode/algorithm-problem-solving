package com.dukcode.jongman.chap06_brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * p.168
 */
public class ClockSync {

  private static final int[][] switches = {
      {0, 1, 2},
      {3, 7, 9, 11},
      {4, 10, 14, 15},
      {0, 4, 5, 6, 7},
      {6, 7, 8, 10, 12},
      {0, 2, 14, 15},
      {3, 14, 15},
      {4, 5, 7, 14, 15},
      {1, 2, 3, 4, 5},
      {3, 4, 5, 9, 13},
  };

  private static final int NUM_CLOCKS = 16;
  private static final int NUM_SWITCHES = 10;

  private static final int INF = 987_654_321;

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int[] clocks;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      clocks = new int[NUM_CLOCKS];
      for (int i = 0; i < NUM_CLOCKS; ++i) {
        clocks[i] = Integer.parseInt(st.nextToken());
      }

      int minClickCnt = cntClick(0);
      bw.write(String.valueOf(minClickCnt >= INF ? -1 : minClickCnt));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int cntClick(int switchIdx) {
    // base case
    if (switchIdx == NUM_SWITCHES) {
      for (int clock : clocks) {
        if (clock != 12) {
          return INF;
        }
      }
      return 0;
    }

    int ret = INF;
    for (int click = 0; click < 4; ++click) {
      ret = Math.min(ret, cntClick(switchIdx + 1) + click);
      for (int target : switches[switchIdx]) {
        clocks[target] = (clocks[target] + 3 == 15) ? 3 : clocks[target] + 3;
      }
    }

    return ret;
  }


}
