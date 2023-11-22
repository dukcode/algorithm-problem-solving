package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Quantize {

  private static final int INF = 987_654_321;

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;
  private static int s;
  private static int[] arr;


  private static int[] sqsum;
  private static int[] sum;

  private static int[][] cache;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      s = Integer.parseInt(st.nextToken());

      arr = new int[n];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; ++i) {
        arr[i] = Integer.parseInt(st.nextToken());
      }

      Arrays.sort(arr);

      preCalc();

      cache = new int[s][n];
      for (int y = 0; y < s; ++y) {
        Arrays.fill(cache[y], -1);
      }

      bw.write(String.valueOf(quantize(0, s)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int quantize(int startIdx, int parts) {
    if (startIdx == n) {
      return 0;
    }

    if (parts == 0) {
      return INF;
    }

    if (cache[parts - 1][startIdx] != -1) {
      return cache[parts - 1][startIdx];
    }

    int ret = INF;
    for (int partSize = 1; startIdx + partSize <= n; ++partSize) {
      ret = Math.min(ret, quantize(startIdx + partSize, parts - 1) +
          minError(startIdx, startIdx + partSize - 1));
    }

    return cache[parts - 1][startIdx] = ret;
  }

  private static int minError(int from, int to) {
    int sqsum = psqsum(from, to);
    int sum = psum(from, to);
    return sqsum - sum * sum / (to - from + 1);
  }

  private static void preCalc() {
    sum = new int[n];
    sqsum = new int[n];

    for (int i = 0; i < n; ++i) {
      if (i == 0) {
        sum[i] = arr[i];
        sqsum[i] = arr[i] * arr[i];
        continue;
      }

      sum[i] = sum[i - 1] + arr[i];
      sqsum[i] = sqsum[i - 1] + arr[i] * arr[i];
    }
  }

  private static int psum(int from, int to) {
    if (from == 0) {
      return sum[to];
    }

    return sum[to] - sum[from - 1];
  }

  private static int psqsum(int from, int to) {
    if (from == 0) {
      return sqsum[to];
    }

    return sqsum[to] - sqsum[from - 1];
  }


}
