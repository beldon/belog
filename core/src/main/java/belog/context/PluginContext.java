package belog.context;

/**
 * 插件内容管理
 * Created by Beldon
 */
public class PluginContext {

    /**
     * 插件文件
     */
    public static String PLUGIN_FILE = "PLUGIN.MF";

    /**
     * 插件id，必填，整站插件唯一，命名和约定与安卓app包名一致，如 me.beldon.plugin
     */
    public static String PLUGIN_UNIQUE_ID = "Plugin-Id";
    /**
     * 插件名称,必填
     */
    public static String PLUGIN_NAME = "Plugin-Name";


    /**
     * 插件配置文件位置
     */
    public static String PLUGIN_CONFIG_PATH = "Plugin-Config-Path";


    /**
     * 插件版本
     */
    public static String PLUGIN_VERSION = "Plugin-Version";
    /**
     * 插件作者
     */
    public static String PLUGIN_AUTHOR = "Plugin-Author";
    /**
     * 插件作者email
     */
    public static String PLUGIN_EMAIL = "Author-Email";
    /**
     * 插件网址
     */
    public static String PLUGIN_URL = "Plugin-URL";

    /**
     * 插件类，方便读取插件配置信息，填写完整路径
     */
    public static String PLUGIN_CLASS = "Plugin_Class";


    //----------------------xml Version----------------------//
    /**
     * 插件id
     */
    public static String ID = "id";
    /**
     * 插件名称
     */
    public static String NAME = "name";
    /**
     * 插件版本
     */
    public static String VERSION = "version";
    /**
     * 插件作者
     */
    public static String AUTHOR = "author";

    /**
     * 插件作者邮件
     */
    public static String EMAIL = "email";

    /**
     * 插件地址
     */
    public static String URL = "url";

    public static String CONFIG = "config";

    public static String CONFIG_PATH = "config-path";

    public static String ELEMENT = "element";

    public static String KEY = "key";

    public static String TYPE = "type";
    public static String LABEL = "label";

    public static String META_CONTENT = "metaContent";
    public static String VALUE = "value";
    public static String TEXT = "text";
}
