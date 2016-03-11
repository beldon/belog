package me.beldon.oauth;

import me.beldon.util.HttpUtils;

import java.util.Map;

/**
 * Created by Beldon
 */
public abstract class Oauth {

    /**
     * 保存oauth参数
     */
    protected final Map<String, String> map;

    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_SECRET = "client_secret";
    public static final String REDIRECT_URI = "redirect_uri";
    public static final String RESPONSE_TYPE = "response_type";
    public static final String ACCESS_TOKEN = "access_token";
    public static final String STATE = "state";
    public static final String GRANT_TYPE = "grant_type";
    public static final String CODE = "code";
    public static final String REFRESH_TOKEN = "refresh_token";

    protected Oauth(Map<String, String> map) {
        this.map = map;
    }

    /**
     * 获取认证参数
     *
     * @return
     */
    public Map<String, String> getParams() {
        return map;
    }

    protected String getAuthorizeUrl(String url, Map<String, String> params) {
        return HttpUtils.addParams(url, params);
    }
}
