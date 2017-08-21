package example.com.abhi.volleycallback;

import java.math.BigInteger;
import java.security.SecureRandom;

public class AppUtils {

    private static SecureRandom random = new SecureRandom();


    public static String nextSessionId() {
        return new BigInteger(100, random).toString(32);
    }

}
