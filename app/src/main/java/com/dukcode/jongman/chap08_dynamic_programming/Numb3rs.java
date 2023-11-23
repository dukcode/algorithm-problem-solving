package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Numb3rs {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;
  private static int d;
  private static int p;
  private static boolean[][] connected;
  private static int[] degree;

  private static int t;

  private static int[] q;

  private static double[][] cache;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      d = Integer.parseInt(st.nextToken());
      p = Integer.parseInt(st.nextToken());

      connected = new boolean[n][n];
      degree = new int[n];
      for (int y = 0; y < n; y++) {
        st = new StringTokenizer(br.readLine());
        for (int x = 0; x < n; x++) {
          connected[y][x] = Integer.parseInt(st.nextToken()) == 1;
          degree[x] += connected[y][x] ? 1 : 0;
        }
      }

      t = Integer.parseInt(br.readLine());

      q = new int[t];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < t; ++i) {
        q[i] = Integer.parseInt(st.nextToken());
      }

      cache = new double[n][d + 1];
      for (int y = 0; y < n; y++) {
        Arrays.fill(cache[y], -1.0);
      }

      for (int i = 0; i < t; ++i) {
        bw.write(String.format("%.8f", search(q[i], d)));
        bw.write(' ');
      }
      bw.newLine();

    }

    br.close();
    bw.close();
  }

  private static double search(int now, int days) {
    // base case
    if (days == 0) {
      return cache[now][days] = (now == p ? 1.0 : 0.0);
    }

    if (cache[now][days] > -0.5) {
      return cache[now][days];
    }

    double ret = 0.0;
    for (int next = 0; next < n; ++next) {
      if (!connected[now][next]) {
        continue;
      }

      ret += search(next, days - 1) / degree[next];
    }

    return cache[now][days] = ret;
  }


}
