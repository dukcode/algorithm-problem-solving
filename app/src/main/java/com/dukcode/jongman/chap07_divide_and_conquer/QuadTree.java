package com.dukcode.jongman.chap07_divide_and_conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.StringCharacterIterator;

/**
 * p.189 쿼드 트리 뒤집기
 */
public class QuadTree {

  private static BufferedReader br;
  private static BufferedWriter bw;

  private static int c;
  private static String compressed;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      compressed = br.readLine();

      StringCharacterIterator it = new StringCharacterIterator(compressed);
      bw.write(flip(it));
      bw.newLine();
    }

    br.close();
    bw.close();
  }

  private static String flip(StringCharacterIterator it) {
    char ch = it.current();
    it.next();

    if (ch != 'x') {
      return Character.toString(ch);
    }

    String leftUpper = flip(it);
    String rightUpper = flip(it);
    String leftLower = flip(it);
    String rightLower = flip(it);

    return "x" + leftLower + rightLower + leftUpper + rightUpper;
  }


}
