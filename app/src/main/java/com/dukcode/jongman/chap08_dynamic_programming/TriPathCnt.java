package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class TriPathCnt {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;
  private static int[][] triangle;

  private static int[][] cache;
  private static int[][] count;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      triangle = new int[n][n];
      cache = new int[n][n];
      count = new int[n][n];

      for (int y = 0; y < n; y++) {
        st = new StringTokenizer(br.readLine());
        for (int x = 0; x <= y; x++) {
          triangle[y][x] = Integer.parseInt(st.nextToken());
        }
      }

      bw.write(String.valueOf(countPath()));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int countPath() {
    calculateMaxPathSum(0, 0);
    return countPath(0, 0);
  }

  private static int countPath(int y, int x) {
    if (y == n - 1) {
      return 1;
    }

    if (count[y][x] != 0) {
      return count[y][x];
    }

    int ret = 0;
    ret += calculateMaxPathSum(y + 1, x) >= calculateMaxPathSum(y + 1, x + 1)
        ? countPath(y + 1, x) : 0;
    ret +=
        calculateMaxPathSum(y + 1, x) <= calculateMaxPathSum(y + 1, x + 1)
            ? countPath(y + 1, x + 1) : 0;

    return count[y][x] = ret;
  }

  private static int calculateMaxPathSum(int y, int x) {
    if (y == n - 1) {
      return triangle[y][x];
    }

    if (cache[y][x] != 0) {
      return cache[y][x];
    }

    return cache[y][x] = triangle[y][x]
        + Math.max(calculateMaxPathSum(y + 1, x), calculateMaxPathSum(y + 1, x + 1));
  }


}
