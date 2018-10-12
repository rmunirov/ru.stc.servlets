package ru.innopolis.stc12.servlets.service.utils;

public class HashUtil {
    private static HashStrategy strategy = new HashByMD5();

    private HashUtil() {
    }

    public static void setStrategy(HashStrategy strategy) {
        HashUtil.strategy = strategy;
    }

    public static String HashPassword(String password) {
        return strategy.execute(password);
    }
}
