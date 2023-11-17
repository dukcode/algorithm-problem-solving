package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * p.226 삼각형 위의 최대 경로
 */
public class TrianglePath {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int[][] triangle;
  private static int[][] cache;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      triangle = new int[n][n];
      for (int y = 0; y < n; y++) {
        st = new StringTokenizer(br.readLine());
        for (int x = 0; x <= y; x++) {
          triangle[y][x] = Integer.parseInt(st.nextToken());
        }
      }

      cache = new int[n][n];

      bw.write(String.valueOf(solve(0, 0)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int solve(int y, int x) {
    if (y == n) {
      return 0;
    }

    if (cache[y][x] != 0) {
      return cache[y][x];
    }

    return cache[y][x] = triangle[y][x] + Math.max(solve(y + 1, x), solve(y + 1, x + 1));
  }


}
