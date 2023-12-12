package com.dukcode.jongman.chap21_tree_implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Traversal {

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

      List<Integer> preorder = new ArrayList<>();
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; ++i) {
        preorder.add(Integer.parseInt(st.nextToken()));
      }

      List<Integer> inorder = new ArrayList<>();
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; ++i) {
        inorder.add(Integer.parseInt(st.nextToken()));
      }

      printPostOrder(preorder, inorder);
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static void printPostOrder(List<Integer> preorder, List<Integer> inorder)
      throws IOException {
    final int n = preorder.size();

    if (n == 0) {
      return;
    }

    final int rootValue = preorder.get(0);

    final int rootIndex = inorder.indexOf(rootValue);

    printPostOrder(preorder.subList(1, rootIndex + 1), inorder.subList(0, rootIndex)); // left
    printPostOrder(preorder.subList(rootIndex + 1, n), inorder.subList(rootIndex + 1, n)); // right

    bw.write(String.valueOf(rootValue));
    bw.write(' ');
  }


}
