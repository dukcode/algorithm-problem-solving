package com.dukcode.jongman.chap19_queue_stack_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class Fence {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      int n = Integer.parseInt(br.readLine());

      int[] arr = new int[n];
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; ++i) {
        arr[i] = Integer.parseInt(st.nextToken());
      }

      bw.write(String.valueOf(solve(arr)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int solve(final int[] arr) {
    int n = arr.length;

    int ret = 0;
    Stack<Integer> stk = new Stack<>();
    for (int idx = 0; idx <= n; ++idx) {
      int block = idx == n ? 0 : arr[idx];
      while (!stk.isEmpty() && arr[stk.peek()] >= block) {
        int height = arr[stk.pop()];
        int width = stk.isEmpty() ? idx : (idx - stk.peek() - 1);
        ret = Math.max(ret, height * width);
      }
      stk.push(idx);
    }

    return ret;
  }

}
