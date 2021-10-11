package com.epam.multithreading.utils;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Generator for random integers value.
 *
 * @author Ilya Vorobyeu
 * @version 1.0.0
 */
public class IntegerGenerator {

    public static int[] generateIntegers(final int size) {

        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            int randomNum = ThreadLocalRandom.current().nextInt(1, size + 1);
            array[i] = randomNum;
        }
        return array;
    }
}
