package ru.innopolis.stc12.servlets.service.utils;

import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashByMD5 implements HashStrategy {
    @Override
    public String execute(String password) {
        MessageDigest messageDigest;
        byte[] digest = new byte[0];

        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(password.getBytes());
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(HashByMD5.class).error(e);
        }
        BigInteger bigInteger = new BigInteger(1, digest);
        String res = bigInteger.toString(16);
        while (res.length() < 32) {
            res = "0" + res;
        }
        return res;
    }
}
