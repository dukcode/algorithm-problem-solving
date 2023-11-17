package com.dukcode.jongman.chap08_dynamic_programming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * p.218 와일드카드
 */
public class WildCard {

  private static final int TRUE = 1;
  private static final int FALSE = 0;

  private static BufferedReader br;
  private static BufferedWriter bw;

  private static int[][] cache;

  private static String pattern;
  private static String word;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
//    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int c = Integer.parseInt(br.readLine());
    while (c-- > 0) {
      pattern = br.readLine();
      int n = Integer.parseInt(br.readLine());

      List<String> answers = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        word = br.readLine();

        cache = new int[pattern.length()][word.length()];
        for (int y = 0; y < pattern.length(); ++y) {
          Arrays.fill(cache[y], -1);
        }

        if (solve(0, 0) == TRUE) {
          answers.add(word);
        }
      }

      Collections.sort(answers);
      for (String answer : answers) {
        bw.write(answer);
        bw.newLine();
      }
    }

    br.close();
    bw.close();
  }

  private static int solve(int patternIdx, int wordIdx) {
    if (wordIdx == word.length()) {
      for (int idx = patternIdx; idx < pattern.length(); ++idx) {
        if (pattern.charAt(idx) != '*') {
          return FALSE;
        }
      }
      return TRUE;
    }

    if (patternIdx == pattern.length()) {
      return FALSE;
    }

    if (cache[patternIdx][wordIdx] != -1) {
      return cache[patternIdx][wordIdx];
    }

    char p = pattern.charAt(patternIdx);
    if (p == '*') {
      return cache[patternIdx][wordIdx] = (solve(patternIdx, wordIdx + 1) == TRUE ||
          solve(patternIdx + 1, wordIdx) == TRUE) ? TRUE : FALSE;
    }

    if (p == '?' || p == word.charAt(wordIdx)) {
      return cache[patternIdx][wordIdx] = solve(patternIdx + 1, wordIdx + 1);
    }

    return cache[patternIdx][wordIdx] = FALSE;
  }


}
