package ru.innopolis.stc12.servlets.service.utils;

import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashBySha256 implements HashStrategy {
    @Override
    public String execute(String password) {
        try {
            MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
            byte[] passHash = sha256.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < passHash.length; i++) {
                sb.append(Integer.toString((passHash[i] & 0xff), 16).substring(1));
            }
            String generatedPassword = sb.toString();
            while (generatedPassword.length() < 32) {
                generatedPassword = "0" + generatedPassword;
            }
            return generatedPassword;
        } catch (NoSuchAlgorithmException e) {
            Logger.getLogger(HashByMD5.class).error(e);
        }
        return null;
    }
}
