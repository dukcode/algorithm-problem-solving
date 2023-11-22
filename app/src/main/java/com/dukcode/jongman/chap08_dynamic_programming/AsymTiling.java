package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AsymTiling {

  private static final int MOD = 1_000_000_007;

  private static BufferedReader br;
  private static BufferedWriter bw;

  private static int n;

  private static int[] tiling;
  private static int[] asymTiling;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());
      bw.write(String.valueOf(count()));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int count() {
    tiling = new int[n + 1];
    asymTiling = new int[n + 1];

    return countAsymTiling(n);
  }

  private static int countAsymTiling(int width) {
    if (width <= 2) {
      return 0;
    }

    if (asymTiling[width] != 0) {
      return asymTiling[width];
    }

    asymTiling[width] = countAsymTiling(width - 2) % MOD;
    asymTiling[width] = (asymTiling[width] + countAsymTiling(width - 4)) % MOD;
    asymTiling[width] = (asymTiling[width] + countTiling(width - 3)) % MOD;
    asymTiling[width] = (asymTiling[width] + countTiling(width - 3)) % MOD;
    return asymTiling[width];
  }

  private static int countTiling(int width) {
    if (width <= 1) {
      return 1;
    }

    if (tiling[width] != 0) {
      return tiling[width];
    }

    return tiling[width] = (countTiling(width - 1) + countTiling(width - 2)) % MOD;
  }


}
