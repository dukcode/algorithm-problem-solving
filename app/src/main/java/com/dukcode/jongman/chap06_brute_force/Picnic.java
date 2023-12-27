package com.dukcode.jongman.chap06_brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * p.157
 */
public class Picnic {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;
  private static int c;
  private static int n;
  private static int m;
  private static boolean[][] areFriends;
  private static boolean[] taken;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());

      areFriends = new boolean[n][n];
      taken = new boolean[n];

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < m; ++i) {
        int first = Integer.parseInt(st.nextToken());
        int second = Integer.parseInt(st.nextToken());

        areFriends[first][second] = true;
        areFriends[second][first] = true;
      }

      bw.write(String.valueOf(countPairings()));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int countPairings() {
    int first = -1;
    for (int i = 0; i < n; ++i) {
      if (!taken[i]) {
        first = i;
        break;
      }
    }

    // base case
    if (first == -1) {
      return 1;
    }

    int ret = 0;
    for (int second = first + 1; second < n; ++second) {
      if (taken[second] || !areFriends[first][second]) {
        continue;
      }

      taken[first] = true;
      taken[second] = true;
      ret += countPairings();
      taken[first] = false;
      taken[second] = false;
    }

    return ret;
  }


}
