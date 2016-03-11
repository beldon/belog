package me.beldon.util;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Beldon
 */
public class HttpUtilsTest {

    @Test
    public void testAddParams() throws Exception {
        String url = "http://www.baidu.com/d?a=d";
        Map<String, String> map = new HashMap<String, String>();
        map.put("hh", "h1");
        map.put("hh2", "h2");
        map.put("hh3", "h3");
        String res = HttpUtils.addParams(url, map);
        System.out.println(res);
    }

    @Test
    public void testMapToParam() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hh", "h1");
        map.put("hh2", "h2");
        map.put("hh3", "h3");
        String res = HttpUtils.mapToParam(map);
        System.out.println(res);
    }

    @Test
    public void testT() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("hh", "h1");
        map.put("hh2", "h2");
        map.put("hh3", "h3");

        Map<String, String> map2 = new HashMap<String, String>();
        map2.put("hh", "h1");
        map2.put("hh2", "h2");
        map2.put("hh3", "h3");
        boolean res = map.equals(map);
        System.out.println(res);
    }
}