package com.dukcode.jongman.chap07_divide_and_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * p.179 행렬의 거듭 제곱을 구하는 알고리즘
 */
public class SquareMatrixPower {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;


  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    st = new StringTokenizer(br.readLine());
    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[][] matrix = inputMatrix(n);

    int[][] ret = pow(matrix, m);
    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        bw.write(String.valueOf(ret[y][x]));
        bw.write(' ');
      }
      bw.newLine();
    }
    bw.newLine();

    br.close();
    bw.close();
  }

  private static int[][] pow(int[][] matrix, int m) {
    int n = matrix.length;

    // base case
    if (m == 0) {
      return identity(n);
    }

    if (m % 2 == 1) {
      return multiply(pow(matrix, m - 1), matrix);
    }

    int[][] half = pow(matrix, m / 2);
    return multiply(half, half);
  }

  private static int[][] multiply(int[][] a, int[][] b) {
    int n = a.length;

    int[][] ret = new int[n][n];

    for (int y = 0; y < n; y++) {
      for (int x = 0; x < n; x++) {
        for (int i = 0; i < n; ++i) {
          ret[y][x] += a[i][x] * b[y][i];
        }
      }
    }

    return ret;
  }

  private static int[][] identity(int n) {
    int[][] ret = new int[n][n];
    for (int i = 0; i < n; ++i) {
      ret[i][i] = 1;
    }

    return ret;
  }

  private static int[][] inputMatrix(int n) throws IOException {
    int[][] ret = new int[n][n];
    for (int y = 0; y < n; y++) {
      st = new StringTokenizer(br.readLine());
      for (int x = 0; x < n; x++) {
        ret[y][x] = Integer.parseInt(st.nextToken());
      }
    }

    return ret;
  }

}
