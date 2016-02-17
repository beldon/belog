package me.beldon.belog.install;

import me.beldon.belog.install.bean.Database;
import me.beldon.belog.install.bean.WebMsg;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Beldon
 */
public class InstallUtilsTest {

//    @Test
    public void testInstall() throws Exception {
        Database database = new Database();
        database.setHost("127.0.0.1");
        database.setDataUser("root");
        database.setDataPass("root");
        database.setDatabase("belog_test");

        WebMsg webMsg = new WebMsg();
        webMsg.setEmail("beldon");
        webMsg.setPass("beldon");
        webMsg.setSiteName("beldon");
        webMsg.setUser("beldon");

        InstallUtils.install(database, webMsg, true);

    }
}