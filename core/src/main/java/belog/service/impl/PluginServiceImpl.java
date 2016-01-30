package belog.service.impl;


import belog.pojo.PluginContent;
import belog.pojo.vo.ConfigVo;
import belog.pojo.vo.PluginVo;
import belog.service.ConfigService;
import belog.service.PluginService;
import belog.utils.PluginUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.*;

/**
 * Created by Beldon
 */
@Service
public class PluginServiceImpl extends BaseService implements PluginService {

    @Autowired
    private ConfigService configService;

    /**
     * 保存已扫描的插件信息。 key: 插件id，value：插件文件
     */
    private Map<String, File> plugins = new HashMap<String, File>();


    public List<PluginVo> list() {
        List<PluginVo> list = new ArrayList<PluginVo>();
        File pluginDir = new File(appContext.getPluginRoot());
        if (pluginDir.isDirectory()) {
            List<File> fileList = (List<File>) FileUtils.listFiles(pluginDir, new String[]{"jar"}, false);
            for (File file : fileList) {
                PluginContent pluginContent = PluginUtils.getPluginContent(file);

//                PluginMsg pluginMsg = PluginUtils.getPluginMsg(file);
                if (!StringUtils.isEmpty(pluginContent.getId())) {
                    PluginVo pluginVo = new PluginVo();
                    BeanUtils.copyProperties(pluginContent, pluginVo);
                    if (appContext.isPluginLoad(pluginContent.getId())) {
                        pluginVo.setLoad(true);
                    }
                    list.add(pluginVo);
                    plugins.put(pluginContent.getId(), file);
                }
            }
        }
        return list;
    }

    public void enable(String pluginId) {
        if (plugins.containsKey(pluginId)) {
            if (appContext.isPluginLoad(pluginId)) {
                appContext.removePlugin(pluginId);
            }else{
                appContext.addPlugin(plugins.get(pluginId));
            }
        }
    }

    public List<PluginContent> getPluginsWithConfig() {
        List<PluginContent> pluginContents = new ArrayList<PluginContent>();
        Map<String, PluginContent> installPlugins = appContext.getInstallPlugins();
        Iterator<Map.Entry<String, PluginContent>> it =  installPlugins.entrySet().iterator();
        while (it.hasNext()) {
            PluginContent pluginContent = it.next().getValue();
            if (pluginContent.getConfig() != null) {
                pluginContents.add(pluginContent);
            }
        }
        return pluginContents;
    }

    public PluginContent getPluginById(String pluginId) {
        return appContext.getPluginById(pluginId);
    }

    public void saveOrUpdate(List<ConfigVo> configVos) {
        configService.saveOrUpdate(configVos);
    }

    public String getValue(String key) {
        return configService.findByName(key).getValue();
    }

}
