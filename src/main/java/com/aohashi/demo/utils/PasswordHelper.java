package com.aohashi.demo.utils;

import com.aohashi.demo.entity.User;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;


/**
 * 加密
 */
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    public static final String ALGORITHM_NAME ="md5";
    public static final int HASH_ITERATIONS = 2;

    public void encryptPassword(User user){
        user.setSalt(randomNumberGenerator.nextBytes().toHex());
        String newPassword = new SimpleHash(ALGORITHM_NAME, user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()),HASH_ITERATIONS).toHex();
        user.setPassword(newPassword);
    }

    public static boolean comparePassword(User user,String password){
        String newPassword = new SimpleHash(ALGORITHM_NAME, password, ByteSource.Util.bytes(user.getCredentialsSalt()),HASH_ITERATIONS).toHex();
        return newPassword.equals(user.getPassword());
    }
}
