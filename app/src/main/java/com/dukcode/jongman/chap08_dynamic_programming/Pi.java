package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * p.239 원주율 외우기
 */
public class Pi {

  private static final int INF = 123_456_789;
  private static BufferedReader br;
  private static BufferedWriter bw;
  private static String pi;
  private static int[] cache;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      pi = br.readLine();
      cache = new int[pi.length()];
      bw.write(String.valueOf(calPoint(0)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int calPoint(int idx) {
    // base case
    if (idx == pi.length()) {
      return 0;
    }

    if (cache[idx] != 0) {
      return cache[idx];
    }

    int ret = INF;
    for (int length = 3; length <= 5; ++length) {
      if (idx + length > pi.length()) {
        continue;
      }

      ret = Math.min(ret, calPoint(idx + length) + classify(idx, idx + length));
    }

    return cache[idx] = ret;
  }

  private static int classify(int a, int b) {
    String str = pi.substring(a, b);

    int diff = str.charAt(1) - str.charAt(0);
    boolean isProgressive = true;
    for (int i = 1; i < str.length(); ++i) {
      if (str.charAt(i) - str.charAt(i - 1) != diff) {
        isProgressive = false;
        break;
      }
    }

    if (isProgressive) {
      if (diff == 0) {
        return 1;
      } else if (Math.abs(diff) == 1) {
        return 2;
      } else {
        return 5;
      }
    }

    boolean isAlternating = true;
    for (int i = 2; i < str.length(); ++i) {
      if (str.charAt(i) != str.charAt(i - 2)) {
        isAlternating = false;
        break;
      }
    }

    if (isAlternating) {
      return 4;
    }

    return 10;
  }


}
