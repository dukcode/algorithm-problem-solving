package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Poly {

  private static final int MOD = 10_000_000;

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;
  private static int[][] cache;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      cache = new int[n + 1][n + 1];

      int ans = 0;
      for (int i = 1; i <= n; ++i) {
        ans = (ans + poly(n, i)) % MOD;
      }

      bw.write(String.valueOf(ans));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int poly(int numBlocks, int numTop) {

    // base case
    if (numBlocks == numTop) {
      return 1;
    }

    if (cache[numBlocks][numTop] != 0) {
      return cache[numBlocks][numTop];
    }

    int ret = 0;
    for (int nextTop = 1; nextTop <= numBlocks - numTop; ++nextTop) {
      int add = ((numTop + nextTop - 1) * poly(numBlocks - numTop, nextTop)) % MOD;
      ret = (ret + add) % MOD;
    }

    return cache[numBlocks][numTop] = ret;
  }


}
