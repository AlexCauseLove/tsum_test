package helpers;

import java.util.Random;

public class DataGenerator {

    public static String generateRandomPasswordAsStr(final int passwordLength) {
        final Random rand = new Random();
        return rand.ints(48, 122)
                .filter(i -> (i < 57 || i > 65) && (i < 90 || i > 97))
                .mapToObj(i -> (char) i)
                .limit(passwordLength)
                .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                .toString();
    }

    public static String generateRandomEmailAsStr() {
        return generateRandomPasswordAsStr(10)
                .concat("@")
                .concat(generateRandomPasswordAsStr(4))
                .concat(".com");
    }
}
