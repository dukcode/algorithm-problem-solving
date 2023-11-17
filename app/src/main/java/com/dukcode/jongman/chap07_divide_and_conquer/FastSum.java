package com.dukcode.jongman.chap07_divide_and_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * p.177 1부터 n 까지의 빠른 합 구하기
 * 시간 복잡도 : O(lgn)
 */
public class FastSum {

  private static BufferedReader br;
  private static BufferedWriter bw;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int n = Integer.parseInt(br.readLine());

    bw.write(String.valueOf(fastSum(n)));

    br.close();
    bw.close();
  }

  private static int fastSum(int n) {
    // base case
    if (n == 1) {
      return 1;
    }

    if (n % 2 == 1) {
      return n + fastSum(n - 1);
    }

    return n * n / 4 + 2 * fastSum(n / 2);
  }

}
