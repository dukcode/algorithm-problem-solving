package com.dukcode.jongman.chap18_linear_data_structure;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Josephus {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;
  private static int k;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      k = Integer.parseInt(st.nextToken());

      List<Integer> ans = solve();
      Collections.sort(ans);
      for (int i : ans) {
        bw.write(String.valueOf(i));
        bw.write(' ');
      }
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static List<Integer> solve() {
    LinkedList<Integer> list = IntStream.rangeClosed(1, n).boxed()
        .collect(Collectors.toCollection(LinkedList::new));

    while (list.size() > 2) {
      list.pollFirst();

      for (int i = 0; i < k - 1; ++i) {
        list.offerLast(list.pollFirst());
      }
    }

    return list;
  }

}
