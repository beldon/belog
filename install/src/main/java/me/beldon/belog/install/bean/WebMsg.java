package me.beldon.belog.install.bean;

/**
 * Created by Beldon
 */
public class WebMsg {

    /**
     * 网站名称
     */
    private String siteName;

    /**
     * 网站管理员用户名
     */
    private String user;

    /**
     * 网站管理员密码
     */
    private String pass;

    /**
     * 管理员email
     */
    private String email;

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
