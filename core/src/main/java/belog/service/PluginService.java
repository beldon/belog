package belog.service;



import belog.pojo.PluginContent;
import belog.pojo.vo.ConfigVo;
import belog.pojo.vo.PluginVo;

import java.util.List;

/**
 * Created by Beldon
 */
public interface PluginService {
    /**
     * 列出所有插件
     *
     * @return
     */
    List<PluginVo> list();

    /**
     * 启用插件
     *
     * @param pluginId 插件id
     */
    void enable(String pluginId);


    /**
     * 获取有配置信息的插件
     *
     * @return List
     */
    List<PluginContent> getPluginsWithConfig();

    /**
     * 根据插件id获取插件信息
     *
     * @param pluginId 插件id
     * @return
     */
    PluginContent getPluginById(String pluginId);

    /**
     * 保存或更新插件配置信息
     *
     * @param configVos
     */
    void saveOrUpdate(List<ConfigVo> configVos);


    /**
     * 获取配置信息内容值
     *
     * @param key
     * @return
     */
    String getValue(String key);
}
