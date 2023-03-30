package com.kuaka.cn.algorithm.sort;

/**
 * @author liqiang.chen
 * @time 2023/3/28 19:52
 **/
public class MergeSort {
    // 递归版本
    public static void mergeSort(int[] source, int L, int R) {
        if (source == null || source.length < 2 || L == R) {
            return;
        }

        int M = L + ((R - L) >> 1);
        mergeSort(source, L, M);
        mergeSort(source, M + 1, R);
        merge(source, L, M, R);
    }

    // 非递归-迭代版本
    public static void mergeSortByIteration(int[] source) {
        if (source == null || source.length < 2) {
            return;
        }

        int N = source.length;
        int step = 1;
        while (step < N) {
            int L = 0;
            while (L < N) {
                int M = L + step - 1;

                if (M >= N) {
                    break;
                }

                int R = Math.min(M + step, N-1);

                merge(source, L, M, R);

                L = R + 1;
            }

            // 防止内存溢出
            if (step > N / 2) {
                break;
            }

            step <<= 1;
        }
    }

    private static void merge(int[] source, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int p1 = l;
        int p2 = m + 1;
        int index = 0;
        while (p1 <= m && p2 <= r) {
            help[index++] = source[p1] <= source[p2] ? source[p1++] : source[p2++];
        }

        while (p1 <= m) {
            help[index++] = source[p1++];
        }

        while (p2 <= r) {
            help[index++] = source[p2++];
        }

        if (index >= 0) System.arraycopy(help, 0, source, l, index);
    }

    public static void main(String[] args) {
        int[] arr = { 5, 3, 8, 6, 4, 234, 53, 23, 42, 23, 2, 52, 645, 2, 32, 15, 90, 232, 39 };
        mergeSort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }

        System.out.println("");
        System.out.println("----------------------------------------");

        mergeSortByIteration(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
