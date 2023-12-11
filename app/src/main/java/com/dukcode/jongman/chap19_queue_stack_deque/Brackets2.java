package com.dukcode.jongman.chap19_queue_stack_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Brackets2 {

  private static BufferedReader br;
  private static BufferedWriter bw;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      String line = br.readLine();
      bw.write(solve(line) ? "YES" : "NO");
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static boolean solve(String line) {
    int n = line.length();

    Stack<Character> stk = new Stack<>();
    for (int idx = 0; idx < n; ++idx) {
      char c = line.charAt(idx);
      if (c == '(' || c == '[' || c == '{') {
        stk.push(c);
        continue;
      }

      if (stk.isEmpty() || !isCounter(stk.peek(), c)) {
        return false;
      }

      stk.pop();
    }

    return stk.isEmpty();
  }

  private static boolean isCounter(char open, char close) {
    if (open == '(') {
      return close == ')';
    }

    if (open == '[') {
      return close == ']';
    }

    if (open == '{') {
      return close == '}';
    }

    return false;
  }

}
