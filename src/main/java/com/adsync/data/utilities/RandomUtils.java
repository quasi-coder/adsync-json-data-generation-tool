package com.adsync.data.utilities;

import java.util.Random;

public class RandomUtils {

    public static String getRandomString(Object... params){
        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'

        String prefix = params.length>0?(String)params[0]:"default";
        int targetLength = params.length>1? (int) params[1] :2;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(targetLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return prefix+generatedString;
    }

    public static String getRandomAlphanumericString(Object... params) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        String prefix = params.length>0?(String)params[0]:"default";
        int targetLength = params.length>1? (int) params[1] :2;

        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        return prefix+generatedString;
    }

    public static int getRandomNumber(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }
}
