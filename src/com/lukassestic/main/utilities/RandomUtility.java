package com.lukassestic.main.utilities;

import java.util.Random;

public class RandomUtility {
    private static Random r;

    public static double doubleInRange(double l, double u) {
        r = new Random();
       return l + (u - l) * r.nextDouble();
    }
}
