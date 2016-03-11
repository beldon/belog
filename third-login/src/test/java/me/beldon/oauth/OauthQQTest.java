package me.beldon.oauth;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Beldon
 */
public class OauthQQTest {
    private String APP_ID = "101161218";
    private String APP_KEY = "3c94c6f2cd2026c4b0674d32b914e51f";
    private String respon_uri = "http://beldon.sinaapp.com/pilotgo/afterQQLoginRedirect.action";
    private String url = "http://beldon.sinaapp.com/";

    @Test
    public void testGetAuthorizeUrl() throws Exception {

    }

    @Test
    public void testGetAuthorizeUrl1() throws Exception {
        Map<String, String> map = new HashMap<String, String>();
        map.put(Oauth.CLIENT_ID, APP_ID);
        map.put(Oauth.REDIRECT_URI, respon_uri);
        map.put(Oauth.RESPONSE_TYPE, "code");

        OauthQQ qauthQQ = new OauthQQ(map);
        String res = qauthQQ.getAuthorizeUrl();
        System.out.println(res);
    }

    @Test
    public void testGetAccessToken() throws Exception{
        Map<String, String> map = new HashMap<String, String>();
        map.put(Oauth.CLIENT_ID, APP_ID);
        map.put(Oauth.CLIENT_SECRET, APP_KEY);
        map.put(Oauth.REDIRECT_URI, respon_uri);
//        map.put(Oauth.GRANT_TYPE, "authorization_code");
//        map.put(Oauth.CODE, "527E1E32F4A34BFA5FF00FD14408B0BF");
        OauthQQ qauthQQ = new OauthQQ(map);
        String res = qauthQQ.getAccessToken("527E1E32F4A34BFA5FF00FD14408B0BF");
        System.out.println(res);
    }
}