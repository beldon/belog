package me.beldon.belog.install.bean;

/**
 * 数据库信息
 * Created by Beldon
 */
public class Database {
    /**
     * 数据库地址
     */
    private String host;

    /**
     * 数据库名称
     */
    private String database;

    /**
     * 数据用户名
     */
    private String dataUser;

    /**
     * 数据库密码
     */
    private String dataPass;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getDataUser() {
        return dataUser;
    }

    public void setDataUser(String dataUser) {
        this.dataUser = dataUser;
    }

    public String getDataPass() {
        return dataPass;
    }

    public void setDataPass(String dataPass) {
        this.dataPass = dataPass;
    }
}
