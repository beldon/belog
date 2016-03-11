package me.beldon.oauth;

import me.beldon.util.TokenUtils;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by Beldon
 */
public class OauthQQ extends Oauth {
    private static final String AUTH_URL = "https://graph.qq.com/oauth2.0/authorize";
    private static final String TOKEN_URL = "https://graph.qq.com/oauth2.0/token";
    private static final String TOKEN_INFO_URL = "https://graph.qq.com/oauth2.0/me";
    private static final String USER_INFO_URL = "https://graph.qq.com/user/get_user_info";

    public OauthQQ(Map<String, String> map) {
        super(map);
    }

    public String getAuthorizeUrl(String state) {
        if (StringUtils.hasText(state)) {
            super.map.put(STATE, state);
        }
        return super.getAuthorizeUrl(AUTH_URL, map);
    }

    public String getAuthorizeUrl() {
        return getAuthorizeUrl(TokenUtils.randomState());
    }

    public String getAccessToken(String code) {
        map.put(CODE, code);
        map.put(GRANT_TYPE, "authorization_code");
        return super.getAuthorizeUrl(TOKEN_URL, map);
    }

    public String getAccessToken() {
        map.put(GRANT_TYPE, "authorization_code");
        return super.getAuthorizeUrl(TOKEN_URL, map);
    }

    public String refreshToken(String refreshToken) {
        map.put(GRANT_TYPE, "refresh_token");
        map.put(REFRESH_TOKEN, refreshToken);
        return super.getAuthorizeUrl(TOKEN_URL, map);
    }

    public String refreshToken() {
        map.put(GRANT_TYPE, "refresh_token");
        return super.getAuthorizeUrl(TOKEN_URL, map);
    }
}
