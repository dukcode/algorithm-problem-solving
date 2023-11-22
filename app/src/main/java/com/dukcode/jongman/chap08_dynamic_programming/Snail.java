package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Snail {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n; // n 미터 오를 확률
  private static int m; // m 일 동안

  private static double[][] cache;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      m = Integer.parseInt(st.nextToken());

      cache = new double[m][m * 2 + 1];
      for (int y = 0; y < m; ++y) {
        Arrays.fill(cache[y], -1.0);
      }

      bw.write(String.format("%.10f", climb(0, 0)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static double climb(int day, int h) {
    if (day == m) {
      return h >= n ? 1.0 : 0.0;
    }

    if (cache[day][h] != -1.0) {
      return cache[day][h];
    }

    return cache[day][h] = 0.25 * climb(day + 1, h + 1) +
        0.75 * climb(day + 1, h + 2);
  }


}
