package cn.rongcoud.rce.demo.sorts;

import java.util.Arrays;

/**
 * Project: netty-demo
 * Package: cn.rongcoud.rce.demo.sorts
 * User: @CZN 2017/11/2 14:32
 * Use: 插入排序
 */
public class InsertSort {

    public static void insertSort(int[] arr) {
        int length = arr.length;
        int j, i, key;
        for (i = 1; i < length; i++) {
            key = arr[i];
            j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = key;
        }

        System.out.println(Arrays.toString(arr));
    }


    public static void main(String[] args) {
        int[] a = {2, 53, 23, 35, 234, 56};
        insertSort(a);
    }
}
