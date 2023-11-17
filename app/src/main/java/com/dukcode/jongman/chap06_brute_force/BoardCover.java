package com.dukcode.jongman.chap06_brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * p.159
 */
public class BoardCover {

  private static final int[][][] blocks = {
      {{0, 0}, {0, 1}, {1, 1}}, // ㄱ
      {{0, 0}, {1, 0}, {1, -1}}, // _l
      {{0, 0}, {1, 0}, {1, 1}}, // ㄴ
      {{0, 0}, {0, 1}, {1, 0}} // l^
  };

  private static final int BLACK = 1;
  private static final int WHITE = 0;
  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;
  private static int c;
  private static int h;
  private static int w;
  private static int[][] board;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      st = new StringTokenizer(br.readLine());
      h = Integer.parseInt(st.nextToken());
      w = Integer.parseInt(st.nextToken());

      board = new int[h][w];
      for (int y = 0; y < h; y++) {
        String line = br.readLine();
        for (int x = 0; x < w; x++) {
          board[y][x] = line.charAt(x) == '#' ? BLACK : WHITE;
        }
      }

      bw.write(String.valueOf(countCovering()));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static int countCovering() {
    int fy = -1;
    int fx = -1;

    Loop:
    for (int y = 0; y < h; y++) {
      for (int x = 0; x < w; x++) {
        if (board[y][x] == WHITE) {
          fy = y;
          fx = x;
          break Loop;
        }
      }
    }

    // base case
    if (fy == -1) {
      return 1;
    }

    int ret = 0;
    for (int block = 0; block < 4; ++block) {
      if (draw(fy, fx, block, 1)) {
        ret += countCovering();
      }

      draw(fy, fx, block, -1);
    }

    return ret;
  }

  private static boolean draw(int y, int x, int block, int delta) {
    boolean ok = true;
    for (int i = 0; i < 3; ++i) {
      int by = y + blocks[block][i][0];
      int bx = x + blocks[block][i][1];

      if (by < 0 || by >= h || bx < 0 || bx >= w) {
        ok = false;
        continue;
      }

      board[by][bx] += delta;
      if (board[by][bx] != BLACK) {
        ok = false;
      }
    }

    return ok;
  }


}
