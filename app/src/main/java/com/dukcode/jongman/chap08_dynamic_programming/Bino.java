package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * p.211 재귀 호출을 이용한 이항계수의 계산
 */
public class Bino {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;
  private static int r;

  private static int[][] cache;


  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    r = Integer.parseInt(st.nextToken());

    cache = new int[n + 1][r + 1];
    for (int i = 0; i <= n; ++i) {
      Arrays.fill(cache[i], -1);
    }

    bw.write(String.valueOf(bino(n, r)));

    br.close();
    bw.close();
  }

  private static int bino(int n, int r) {
    if (r == 0 || n == r) {
      return 1;
    }

    if (cache[n][r] != -1) {
      return cache[n][r];
    }

    return cache[n][r] = bino(n - 1, r - 1) + bino(n - 1, r);
  }


}
