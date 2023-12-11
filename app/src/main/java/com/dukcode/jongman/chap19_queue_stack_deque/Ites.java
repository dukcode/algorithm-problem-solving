package com.dukcode.jongman.chap19_queue_stack_deque;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Ites {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      int k = Integer.parseInt(st.nextToken());
      int n = Integer.parseInt(st.nextToken());

      bw.write(String.valueOf(count(n, k)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int count(int n, int k) {
    int ret = 0;
    SignGenerator generator = new SignGenerator();
    Queue<Long> q = new LinkedList<>();
    long sum = 0;
    for (int i = 0; i < n; ++i) {
      long next = generator.next();
      sum += next;
      q.offer(next);

      while (sum > k) {
        sum -= q.poll();
      }

      if (sum == k) {
        ret++;
      }
    }

    return ret;
  }

  private static class SignGenerator {

    private long seed = 1983;

    private long next() {
      long ret = seed;
      seed = (seed * 214_013L + 2_531_011L) % (1L << 32);
      return (ret % 10_000L + 1L);
    }
  }


}
