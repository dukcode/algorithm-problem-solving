package com.dukcode.jongman.chap06_brute_force;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * p.149 0부터 n - 1까지의 숫자 중 m개를 고르는 모든 조합을 출력
 */
public class Combination {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;
  private static int m;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    st = new StringTokenizer(br.readLine());
    n = Integer.parseInt(st.nextToken());
    m = Integer.parseInt(st.nextToken());

    Deque<Integer> picked = new ArrayDeque<>();
    pick(picked);

    br.close();
    bw.close();
  }

  private static void pick(Deque<Integer> picked) throws IOException {
    // base case
    if (picked.size() == m) {
      System.out.println(picked);
    }

    int smallest = picked.isEmpty() ? 0 : picked.peekLast() + 1;
    for (int next = smallest; next < n; ++next) {
      picked.offerLast(next);
      pick(picked);
      picked.pollLast();
    }
  }

}
