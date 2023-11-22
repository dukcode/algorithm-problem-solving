package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Tiling2 {

  private static final int MOD = 1_000_000_007;

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;

  private static int[] cache;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      cache = new int[n + 1];

      bw.write(String.valueOf(countTiling(n)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int countTiling(int size) {
    if (size == 1 || size == 2) {
      return size;
    }

    if (cache[size] != 0) {
      return cache[size];
    }

    return cache[size] = (countTiling(size - 1) + countTiling(size - 2)) % MOD;
  }


}
