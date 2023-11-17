package com.dukcode.jongman.chap06_brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * [p.147] 1부터 n까지의 합을 계산하는 재귀 함수<br>
 * 시간복잡도 : O(n)
 */
public class RecursiveSum {

  private static BufferedReader br;
  private static BufferedWriter bw;

  private static int n;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    n = Integer.parseInt(br.readLine());

    bw.write(String.valueOf(recursiveSum(n)));

    br.close();
    bw.close();
  }

  private static int recursiveSum(int n) {
    // base case
    if (n == 1) {
      return 1;
    }

    return n + recursiveSum(n - 1);
  }

}
