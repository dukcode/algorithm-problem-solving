package com.dukcode.jongman.chap23_priority_queue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class RunningMedian {

  public static final int MOD = 20090711;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      StringTokenizer st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      bw.write(String.valueOf(solve(n, a, b)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int solve(int n, int a, int b) {
    NumberGenerator generator = new NumberGenerator(a, b);

    int ret = 0;
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(Comparator.naturalOrder());

    for (int i = 0; i < n; ++i) {
      int number = generator.next();
      if (maxHeap.size() == minHeap.size()) {
        maxHeap.offer(number);
      } else {
        minHeap.offer(number);
      }

      if (!maxHeap.isEmpty() && !minHeap.isEmpty() &&
          minHeap.peek() < maxHeap.peek()) {
        int num1 = maxHeap.poll();
        int num2 = minHeap.poll();
        maxHeap.offer(num2);
        minHeap.offer(num1);
      }

      ret = (ret + maxHeap.peek()) % MOD;
    }

    return ret;
  }

  private static class NumberGenerator {

    private final int a;
    private final int b;
    private int seed = 1983;

    public NumberGenerator(int a, int b) {
      this.a = a;
      this.b = b;
    }

    public int next() {
      int ret = seed;
      seed = (int) ((seed * (long) a + b) % 20090711);
      return ret;
    }
  }
}
