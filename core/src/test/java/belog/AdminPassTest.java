package belog;

import belog.security.token.Token;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.junit.Test;

/**
 * Created by Beldon.
 * Copyright (c) 2016, All Rights Reserved.
 * http://beldon.me
 */
public class AdminPassTest {

    @Test
    public void testPass()throws Exception {
        String pass = "111111";
        String sha1Pass = new Sha256Hash(pass, Token.PASSWORD_TOKEN).toString();
        System.out.println(sha1Pass);
    }
}
