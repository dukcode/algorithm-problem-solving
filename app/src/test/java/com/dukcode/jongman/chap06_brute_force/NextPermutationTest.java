package com.dukcode.jongman.chap06_brute_force;


import java.util.Arrays;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NextPermutationTest {

  @Test
  public void 다음_순열을_구할_수_있다_1() throws Exception {
    // given
    int[] arr = {0, 1, 2, 3};

    // when
    // then
    while (nextPermutation(arr)) {
      System.out.println(Arrays.toString(arr));
    }

  }

  @Test
  public void 다음_순열을_구할_수_있다_2() throws Exception {
    // given
    int[] arr = {0, 0, 1, 1};

    // when
    // then
    while (nextPermutation(arr)) {
      System.out.println(Arrays.toString(arr));
    }

  }

  private boolean nextPermutation(int[] arr) {
    int n = arr.length;

    int a = n - 2;
    while (a >= 0 && arr[a] >= arr[a + 1]) {
      a--;
    }

    if (a == -1) {
      return false;
    }

    int b = n - 1;
    while (arr[a] >= arr[b]) {
      b--;
    }

    swap(arr, a, b);

    int st = a + 1;
    int en = n - 1;
    while (st < en) {
      swap(arr, st++, en--);
    }

    return true;
  }

  private void swap(int[] arr, int l, int r) {
    int temp = arr[l];
    arr[l] = arr[r];
    arr[r] = temp;
  }


}