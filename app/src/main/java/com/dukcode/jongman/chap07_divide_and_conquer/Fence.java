package com.dukcode.jongman.chap07_divide_and_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * p.195 울타리 잘라내기
 */
public class Fence {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int c;
  private static int n;
  private static int[] fences;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      n = Integer.parseInt(br.readLine());

      fences = new int[n];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; ++i) {
        fences[i] = Integer.parseInt(st.nextToken());
      }

      bw.write(String.valueOf(solve(0, n - 1)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int solve(int left, int right) {
    if (left == right) {
      return fences[left];
    }

    int mid = (left + right) / 2;

    int ret = Math.max(solve(left, mid), solve(mid + 1, right));

    int l = mid;
    int r = mid + 1;
    int minHeight = Math.min(fences[l], fences[r]);
    ret = Math.max(ret, minHeight * 2);
    while (left < l || r < right) {
      if (left < l && (r == right || fences[l - 1] >= fences[r + 1])) {
        minHeight = Math.min(minHeight, fences[--l]);
      } else {
        minHeight = Math.min(minHeight, fences[++r]);
      }

      ret = Math.max(ret, minHeight * (r - l + 1));
    }

    return ret;
  }

}
