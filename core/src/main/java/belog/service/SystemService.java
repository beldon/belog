package belog.service;


import java.util.Map;

/**
 * Created by Beldon
 */
public interface SystemService {
    Map<String, String> getSystemMsg();

    /**
     * 获取类加载信息
     *
     * @return
     */
    Map<String, String> getClassLoadingMsg();


    /**
     * 获取虚拟机内存信息
     *
     * @return
     */
    Map<String, String> getMemoryMsg();


}
