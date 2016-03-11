package me.beldon.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

/**
 * Created by Beldon
 */
public class HttpUtils {

    /**
     * url添加参数
     *
     * @param url    目标网址
     * @param params 参数
     * @return url
     */
    public static String addParams(String url, Map<String, String> params) {
        StringBuilder stringBuilder = new StringBuilder(url);
        if (null != params && !params.isEmpty()) {
            if (url.indexOf("?") == -1) {
                stringBuilder.append("?");
            } else {
                stringBuilder.append("&");
            }
            stringBuilder.append(mapToParam(params));
        }
        return stringBuilder.toString();
    }

    /**
     * map转url参数
     * @param params map参数
     * @return url
     */
    public static String mapToParam(Map<String, String> params) {
        StringBuilder url = new StringBuilder();
        if (null != params || !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                url.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
            url.deleteCharAt(url.length() - 1);
        }
        return url.toString();
    }
}
