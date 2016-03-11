package me.beldon.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Beldon
 */
public class TestTTest {

    @Test
    public void testSys() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("2", "3");
        System.out.println(map.get("3"));
    }
}