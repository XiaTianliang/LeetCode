package utils;

import java.util.Random;

/**
 * Created by tianliangxia on 16-10-21.
 */
class RandomUtils {
    private static Random random = new Random();
    public static int[] ints(int length){
        return random.ints().limit(length).toArray();
    }
    public static double[] doubles(int length){
        return random.doubles().limit(length).toArray();
    }
}