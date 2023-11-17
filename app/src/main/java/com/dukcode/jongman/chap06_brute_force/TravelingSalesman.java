package com.dukcode.jongman.chap06_brute_force;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * p.167 <a href=https://www.acmicpc.net/problem/2098>백준 문제 링크</a>
 */
public class TravelingSalesman {

  private static final int INF = 987_654_321;

  private static BufferedReader br;
  private static BufferedWriter bw;
  private static StringTokenizer st;

  private static int n;
  private static int[][] dist;

  private static boolean[] visited;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    br = new BufferedReader(new FileReader("input.txt"));
    bw = new BufferedWriter(new OutputStreamWriter(System.out));

    n = Integer.parseInt(br.readLine());

    visited = new boolean[n];

    dist = new int[n][n];
    for (int y = 0; y < n; ++y) {
      st = new StringTokenizer(br.readLine());
      for (int x = 0; x < n; ++x) {
        dist[y][x] = Integer.parseInt(st.nextToken());
      }

    }

    bw.write(String.valueOf(shortestPath()));

    br.close();
    bw.close();
  }

  private static int shortestPath() {
    int ret = INF;
    for (int start = 0; start < n; ++start) {
      visited[start] = true;
      ret = Math.min(ret, shortestPath(start, start));
      visited[start] = false;
    }

    return ret;
  }

  private static int shortestPath(int start, int now) {
    boolean finished = true;
    for (int i = 0; i < n; ++i) {
      if (!visited[i]) {
        finished = false;
        break;
      }
    }

    if (finished) {
      return dist[now][start];
    }

    int ret = INF;
    for (int next = 0; next < n; ++next) {
      if (visited[next] || next == now) {
        continue;
      }

      visited[next] = true;
      ret = Math.min(ret, shortestPath(start, next) + dist[now][next]);
      visited[next] = false;
    }

    return ret;
  }


}
