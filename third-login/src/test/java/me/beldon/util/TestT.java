package me.beldon.util;

import java.util.Map;

/**
 * Created by Beldon
 */
public class TestT {
    private final Map<String, String> map;
    private static TestT instance;

    private TestT(Map<String, String> map) {
        this.map = map;
    }

    public static TestT getInstance(Map<String, String> map) {
        if (instance == null) {
            instance = new TestT(map);
        }
        return instance;
    }

    public void sys() {
        System.out.println(map.get("2"));
    }
}
