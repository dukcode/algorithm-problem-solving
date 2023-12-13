package com.dukcode.jongman.chap22_binary_search_tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Nerd2 {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static TreeMap<Integer, Integer> treeMap;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      treeMap = new TreeMap<>();

      int n = Integer.parseInt(br.readLine());

      int ans = 0;
      for (int i = 0; i < n; ++i) {
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        ans += register(p, q);
      }

      bw.write(String.valueOf(ans));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int register(int p, int q) {
    if (dominated(p, q)) {
      return treeMap.size();
    }

    removeDominated(p, q);
    treeMap.put(p, q);

    return treeMap.size();
  }

  private static void removeDominated(int p, int q) {
    while (true) {
      Entry<Integer, Integer> entry = treeMap.lowerEntry(p);
      if (entry == null) {
        return;
      }

      if (entry.getValue() < q) {
        treeMap.remove(entry.getKey());
      } else {
        return;
      }
    }

  }

  private static boolean dominated(int p, int q) {
    Entry<Integer, Integer> entry = treeMap.ceilingEntry(p);

    if (entry == null) {
      return false;
    }

    return entry.getValue() > q;
  }


}
