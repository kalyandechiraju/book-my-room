package cool.creators.util;


import com.google.appengine.repackaged.org.apache.commons.codec.digest.DigestUtils;

/**
 * Created by kalyandechiraju on 21/05/16.
 */
public class PasswordUtil {
    public static String getHashedPassword(String password) {
        return DigestUtils.md5Hex(password);
    }
}
