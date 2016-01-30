package me.beldon.belog.install;


import me.beldon.belog.install.bean.Database;
import me.beldon.belog.install.bean.Msg;
import me.beldon.belog.install.bean.WebMsg;
import me.beldon.belog.install.po.*;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 安装辅助类
 * Created by Beldon
 */
public class InstallUtils {


    /**
     * 导入数据库
     *
     * @param database 数据库信息
     * @param webMsg   网站信息
     */
    public static void install(Database database, WebMsg webMsg) {
        Configuration configuration = new Configuration();
        configuration.setProperties(getConfig(database));
        addClass(configuration);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        String sha1Pass = new Sha256Hash(webMsg.getPass(), "token").toString();
        Users users = new Users();
        users.setLogin(webMsg.getUser());
        users.setEmail(webMsg.getEmail());
        users.setPass(sha1Pass);
        users.setNickName(webMsg.getUser());
        users.setDisplayName(webMsg.getUser());
        users.setStatus(0);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(users);
        transaction.commit();
        session.close();
    }

    private static void addClass(Configuration configuration) {
        configuration.addAnnotatedClass(CommentMeta.class)
                .addAnnotatedClass(Comments.class)
                .addAnnotatedClass(Links.class)
                .addAnnotatedClass(Menu.class)
                .addAnnotatedClass(Options.class)
                .addAnnotatedClass(Permission.class)
                .addAnnotatedClass(PostMeta.class)
                .addAnnotatedClass(Posts.class)
                .addAnnotatedClass(Role.class)
                .addAnnotatedClass(TermRelationships.class)
                .addAnnotatedClass(Terms.class)
                .addAnnotatedClass(TermTaxonomy.class)
                .addAnnotatedClass(UserMeta.class)
                .addAnnotatedClass(Users.class);
    }

    /**
     * 获取配置信息
     *
     * @param database 数据库信息
     * @return 配置信息
     */
    private static Properties getConfig(Database database) {

        String url = "jdbc:mysql://#host#:3306/#dbName#?useUnicode=true&amp;characterEncoding=UTF-8";

        Properties prop = new Properties();
        prop.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        prop.setProperty("hibernate.hbm2ddl.auto", "update");
        prop.setProperty("hibernate.connection.url", url.replaceAll("#host#", database.getHost()).replaceAll("#dbName#", database.getDatabase()));
        prop.setProperty("hibernate.connection.username", database.getDataUser());
        prop.setProperty("hibernate.connection.password", database.getDataPass());
        prop.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        return prop;
    }


    /**
     * 检查数据库是否通过
     *
     * @param database
     * @return
     */
    public static Msg checkDB(Database database) {
        String url = "jdbc:mysql://#host#:3306/#dbName#?useUnicode=true&amp;characterEncoding=UTF-8";
        Msg msg = new Msg();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url.replaceAll("#host#", database.getHost()).replaceAll("#dbName#",
                    database.getDatabase()), database.getDataUser(), database.getDataPass());
            msg.setErrCode(0);
            msg.setErrMsg("success");
            msg.setStatus(true);
        } catch (SQLException e) {
            msg.setErrCode(e.getErrorCode());
            msg.setErrMsg(e.getMessage());
            msg.setStatus(false);
        }
        return msg;
    }

    /**
     * 更新数据库配置文件
     *
     * @param database
     */
    public static void updateDB(Database database) {
        String url = "jdbc:mysql://#host#:3306/#dbName#?useUnicode=true&amp;characterEncoding=UTF-8";
        String path = System.getProperty("belog.root") + File.separator + "WEB-INF" + File.separator + "classes" + File.separator + "db.properties";
        File file = new File(path);
        Properties props = new Properties();
        try {
            if (!file.exists()) {
                file.createNewFile();
            }else {
                file.delete();
                file.createNewFile();
            }
            props.load(new FileInputStream(path));
            OutputStream fos = new FileOutputStream(path);
            props.setProperty("jdbc.url", url.replaceAll("#host#", database.getHost()).replaceAll("#dbName#", database.getDatabase()));
            props.setProperty("jdbc.username", database.getDataUser());
            props.setProperty("jdbc.password", database.getDataPass());
            props.setProperty("jdbc.driverClassName", "com.mysql.jdbc.Driver");
            props.store(fos, "Update 'jdbc.url','jdbc.username','jdbc.password' value");
        } catch (IOException e) {
            e.printStackTrace();
        }

        createLock();
    }

    public static void createLock(){
        String path = System.getProperty("belog.root") + File.separator + "WEB-INF" + File.separator + "install.lock";
        File lockFile = new File(path);
        if (!lockFile.exists()) {
            try {
                lockFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
