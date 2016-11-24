package app.common.util.password;

import java.security.SecureRandom;

public class PasswordUtil {
    private static final char[] LETTERS = {
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    private static int DEFAULT_PASSWORD_LENGTH = 12;

    public static String hash(String plainPassword) {
        try {
            return PasswordStorage.createHash(plainPassword);
        } catch (PasswordStorage.CannotPerformOperationException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isEquals(String plainPassword, String hashedPassword) {
        try {
            return PasswordStorage.verifyPassword(plainPassword, hashedPassword);
        } catch (PasswordStorage.CannotPerformOperationException | PasswordStorage.InvalidHashException e) {
            throw new RuntimeException(e);
        }
    }

    public static String newPassword() {
        SecureRandom random = new SecureRandom();

        StringBuilder sb = new StringBuilder(12);
        for (int i=0; i<DEFAULT_PASSWORD_LENGTH; i++) {
            sb.append(LETTERS[random.nextInt(LETTERS.length)]);
        }

        return sb.toString();
    }
}
