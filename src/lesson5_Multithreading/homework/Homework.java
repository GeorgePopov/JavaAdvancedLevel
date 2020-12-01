package lesson5_Multithreading.homework;

import java.util.Arrays;

public class Homework {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    static float[] arr;

    static void method1(float... arr) {

        arr = new float[SIZE];

        Arrays.fill(arr, 1);

        long a = System.currentTimeMillis();

        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }

        System.currentTimeMillis();
        System.out.println(System.currentTimeMillis() - a);
    }

    static void method2(float... arr) {

        arr = new float[SIZE];

        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];

        Arrays.fill(arr, 1);

        System.arraycopy(arr, 0, arr1, 0, HALF);
        System.arraycopy(arr, HALF, arr2, 0, HALF);

        System.arraycopy(arr1, 0, arr, 0, HALF);
        System.arraycopy(arr2, 0, arr, HALF, HALF);


    }
}
