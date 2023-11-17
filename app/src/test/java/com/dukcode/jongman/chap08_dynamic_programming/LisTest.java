package com.dukcode.jongman.chap08_dynamic_programming;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LisTest {

  private int[] cache;

  @Test
  public void LIS() throws Exception {
    // given

    for (int i = 0; i < 50; ++i) {

      int n = 50;
      int[] arr = new int[n];
      for (int j = 0; j < n; ++j) {
        arr[j] = (int) (Math.random() * 30);
      }
      cache = new int[n + 1];

      // when
      int ans1 = lisTopDown(arr, -1) - 1;
      int ans2 = lisBottomUp(arr);
      int ans3 = lisNlgN(arr);

      // then
      assertThat(ans1).isEqualTo(ans2);
      assertThat(ans2).isEqualTo(ans3);
    }

  }

  private int lisNlgN(int[] arr) {
    int n = arr.length;

    int[] minValues = new int[n];
    Arrays.fill(minValues, Integer.MAX_VALUE);

    int len = 0;
    for (int now = 0; now < n; ++now) {
      int pos = Arrays.binarySearch(minValues, arr[now]);
      pos = pos < 0 ? -(pos + 1) : pos;
      len += (pos == len ? 1 : 0);
      minValues[pos] = Math.min(minValues[pos], arr[now]);
    }

    return len;
  }


  private int lisBottomUp(int[] arr) {
    int n = arr.length;

    int[] cache = new int[n];

    int ret = 0;
    for (int now = 0; now < n; ++now) {
      cache[now] = 1;
      for (int before = 0; before < now; ++before) {
        if (arr[before] < arr[now]) {
          cache[now] = Math.max(cache[now], cache[before] + 1);
        }

      }
      ret = Math.max(ret, cache[now]);
    }

    return ret;
  }

  private int lisTopDown(int[] arr, int now) {
    int n = arr.length;

    if (cache[now + 1] != 0) {
      return cache[now + 1];
    }

    int ret = 1;
    for (int next = now + 1; next < n; ++next) {
      if (now == -1 || arr[now] < arr[next]) {
        ret = Math.max(ret, lisTopDown(arr, next) + 1);
      }
    }

    return cache[now + 1] = ret;
  }


}