package com.dukcode.jongman.chap21_tree_implement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

public class Fortress {

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int longest;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      int n = Integer.parseInt(br.readLine());
      Rampart[] ramparts = new Rampart[n];
      for (int i = 0; i < n; ++i) {
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        ramparts[i] = new Rampart(x, y, r);
      }

      bw.write(String.valueOf(solve(ramparts)));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int solve(Rampart[] ramparts) {
    TreeNode root = getTree(ramparts, 0);
    longest = 0;
    int h = height(root);
    return Math.max(h, longest);
  }

  private static int height(TreeNode root) {
    List<Integer> heights = new ArrayList<>();

    List<TreeNode> children = root.getChildren();
    for (TreeNode child : children) {
      heights.add(height(child));
    }

    if (heights.isEmpty()) {
      return 0;
    }

    heights.sort(Comparator.naturalOrder());

    if (heights.size() >= 2) {
      longest = Math.max(longest,
          2 + heights.get(heights.size() - 2) + heights.get(heights.size() - 1));
    }

    return heights.get(heights.size() - 1) + 1;
  }

  private static TreeNode getTree(Rampart[] ramparts, int root) {
    TreeNode ret = new TreeNode();
    int n = ramparts.length;

    for (int candidate = 0; candidate < n; ++candidate) {
      if (isChild(ramparts, root, candidate)) {
        ret.add(getTree(ramparts, candidate));
      }
    }
    return ret;
  }

  private static boolean isChild(Rampart[] ramparts, int parent, int child) {
    if (!encloses(ramparts, parent, child)) {
      return false;
    }

    int n = ramparts.length;
    for (int i = 0; i < n; ++i) {
      if (i != parent && i != child && encloses(ramparts, parent, i) && encloses(ramparts, i,
          child)) {
        return false;
      }
    }

    return true;
  }

  private static boolean encloses(Rampart[] ramparts, int outer, int inner) {
    return ramparts[outer].r > ramparts[inner].r &&
        squareDist(ramparts, outer, inner) < square(ramparts[outer].r - ramparts[inner].r);
  }

  private static double squareDist(Rampart[] ramparts, int outer, int inner) {
    return square(ramparts[outer].x - ramparts[inner].x) +
        square(ramparts[outer].y - ramparts[inner].y);
  }

  private static int square(int x) {
    return x * x;
  }

  private static class Rampart {

    int x;
    int y;
    int r;

    public Rampart(int x, int y, int r) {
      this.x = x;
      this.y = y;
      this.r = r;
    }
  }

  private static class TreeNode {

    private List<TreeNode> children = new ArrayList<>();

    public void add(TreeNode child) {
      children.add(child);
    }

    public List<TreeNode> getChildren() {
      return children;
    }
  }


}
