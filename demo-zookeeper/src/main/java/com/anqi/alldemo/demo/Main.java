package com.anqi.alldemo.demo;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

    double [] arr = {36.8,36.3,36.4,36.5,37,39,39.5,38,37,36.5,37};
    int[] m = so(arr);
        for (int i : m) {
            System.out.println(i);
        }
    }

    public static int[] so(double[] arr) {
        int[]dp = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int maxCount = 0;
            for (int j = i+1; j < arr.length; j++) {
                maxCount++;
                if(arr[i] < arr[j]){
                    break;
                }

                if (j == arr.length-1) {
                    maxCount = 0;
                }
            }
            dp[i] = maxCount;
        }
        return dp;
    }
}
