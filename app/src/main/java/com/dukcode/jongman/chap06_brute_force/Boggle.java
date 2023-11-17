package com.dukcode.jongman.chap06_brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * p.152
 */
public class Boggle {

  private static final int BOARD_SIZE = 5;

  private static final int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
  private static final int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
  private static BufferedReader br;
  private static BufferedWriter bw;
  private static int c;
  private static char[][] board;

  private static int n;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      board = new char[BOARD_SIZE][];
      for (int y = 0; y < BOARD_SIZE; ++y) {
        board[y] = br.readLine().toCharArray();
      }

      n = Integer.parseInt(br.readLine());
      for (int i = 0; i < n; ++i) {
        String word = br.readLine();

        bw.write(word);
        bw.write(' ');
        bw.write(canFind(word) ? "YES" : "NO");
        bw.newLine();
      }
    }

    br.close();
    bw.close();
  }

  private static boolean canFind(String word) {
    for (int y = 0; y < BOARD_SIZE; y++) {
      for (int x = 0; x < BOARD_SIZE; x++) {
        if (canFind(word, 0, y, x)) {
          return true;
        }
      }
    }

    return false;
  }

  private static boolean canFind(String word, int index, int y, int x) {
    // base case 1
    if (word.length() == index) {
      return true;
    }

    // base case 2
    if (y < 0 || y >= BOARD_SIZE || x < 0 || x >= BOARD_SIZE) {
      return false;
    }

    // base case 3
    if (board[y][x] != word.charAt(index)) {
      return false;
    }

    for (int i = 0; i < 8; ++i) {
      int ny = y + dy[i];
      int nx = x + dx[i];

      if (canFind(word, index + 1, ny, nx)) {
        return true;
      }
    }

    return false;
  }

}
