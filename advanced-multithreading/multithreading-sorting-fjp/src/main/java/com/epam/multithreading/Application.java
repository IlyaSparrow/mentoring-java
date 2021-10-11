package com.epam.multithreading;

import com.epam.multithreading.sort.MergeSort;

import java.util.Scanner;
import java.util.concurrent.ForkJoinPool;

import static com.epam.multithreading.utils.IntegerGenerator.generateIntegers;

public class Application {

    public static void main(String... args){

        System.out.println("Enter array size: ");
        Scanner sc = new Scanner(System.in);
        final int size = sc.nextInt();

        final int[] array = generateIntegers(size);
        System.out.println("Array generated with " + array.length + " elements...");

        ForkJoinPool pool = new ForkJoinPool();
        System.out.println("Pool started...");

        long startTime = System.nanoTime();
        pool.invoke(new MergeSort(array));
        long endTime = System.nanoTime();

        System.out.println("Pool invoked...");
        System.out.println("Pool size=" + pool.getPoolSize());

        for (int i = 0; i < size - 1; i++) {
            if (array[i] > array[i+1]) {
                System.out.println("Not sorted...");
                return;
            }
        }

        System.out.println("Array sorted...");

        System.out.println("Running time in sec: " + (endTime - startTime)/1000000000);
        System.out.println("Running time in milliseconds: " + (endTime - startTime)/1000000);
        System.out.println("Running time in nanoseconds: " + (endTime - startTime));


    }
}
