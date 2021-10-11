package com.epam.multithreading.sort;

import java.util.concurrent.RecursiveAction;

public class MergeSort extends RecursiveAction {

    private final int[] array;

    public MergeSort(final int[] array) {
        this.array = array;
    }

    @Override
    protected void compute() {
        int middle = array.length / 2;
        if (middle < 1) {
            return;
        }

        int[] left = new int[middle];
        int[] right = new int[array.length - left.length];

        mergeArrays(array, left, right, middle);

        MergeSort leftSort = new MergeSort(left);
        MergeSort rightSort = new MergeSort(right);
        invokeAll(leftSort, rightSort);

        merge(array, left, right, left.length, right.length);
    }

    private static void mergeArrays(final int[] array, final int[] left, final int[] right, int mid) {
        if (mid >= 0) {
            System.arraycopy(array, 0, left, 0, mid);
        }
        System.arraycopy(array, mid, right, 0, right.length + mid - mid);
    }

    public static void merge(int[] array, int[] leftArray, int[] rightArray, int left, int right) {
        int i = 0, j = 0, k = 0;
        while (i < left && j < right) {
            if (leftArray[i] <= rightArray[j]) {
                array[k++] = leftArray[i++];
            } else {
                array[k++] = rightArray[j++];
            }
        }
        while (i < left) {
            array[k++] = leftArray[i++];
        }
        while (j < right) {
            array[k++] = rightArray[j++];
        }
    }
}
