package utils;

import java.util.Random;

public class Rand {
    private static final String ALPHABET_AND_NUMS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final Random random = new Random();

    public static int[] genIntArray(long length, int min, int max) {
        return genIntArray((int) length, min, max);  // TODO: call many times required for long length
    }

    public static int[] genIntArray(int length, int min, int max) {
        int[] result = new int[length];
        for (int i = 0; i < length; i++) {
            result[i] = random.nextInt(max - min + 1) + min;
        }
        return result;
    }

    public static int[] genIntSequencedArray(int startValue, int endValue) {
        int[] result = new int[endValue - startValue];
        for (int index = 0, value = startValue; index < result.length; index++, value++) {
            result[index] = value;
        }
        return result;
    }

    public static String generateRandomString(long length) {
        return generateRandomString((int) length); // TODO: call many times required for long length
    }

    public static String generateRandomString(int length) {
        var stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(ALPHABET_AND_NUMS.length());
            stringBuilder.append(ALPHABET_AND_NUMS.charAt(index));
        }

        return stringBuilder.toString();
    }
}
