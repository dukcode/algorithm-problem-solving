package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * p.290 최대 증가 부분 수열
 */
public class Lis {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int[] arr;
  private static int[] cache;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      arr = new int[n];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; ++i) {
        arr[i] = Integer.parseInt(st.nextToken());
      }

      cache = new int[n + 1];

      bw.write(String.valueOf(lis(-1) - 1));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int lis(int now) {
    if (now == n) {
      return 0;
    }

    if (cache[now + 1] != 0) {
      return cache[now + 1];
    }

    int ret = 1;
    for (int next = now + 1; next < n; ++next) {
      if (now == -1 || arr[now] < arr[next]) {
        ret = Math.max(ret, lis(next) + 1);
      }
    }
    return cache[now + 1] = ret;
  }


}
