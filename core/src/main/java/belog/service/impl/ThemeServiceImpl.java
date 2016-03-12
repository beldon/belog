package belog.service.impl;


import belog.filter.DirectoryFilter;
import belog.pojo.vo.ConfigVo;
import belog.pojo.vo.ThemeVo;
import belog.service.ConfigService;
import belog.service.ThemeService;
import belog.utils.ThemeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beldon
 */
@Service("ThemeService")
public class ThemeServiceImpl extends BaseService implements ThemeService {
    public static String THEME_KEY = "theme_name";

    @Autowired
    private ConfigService configService;

    public String getTemplatePath(String template) {
        return "theme/" + getThemeName() + "/" + template;
    }

    public String getThemeName() {
        ConfigVo themeConfig = configService.findByName(THEME_KEY);
        if (themeConfig == null || StringUtils.isEmpty(themeConfig.getValue())) {
            return "default";
        }
        return themeConfig.getValue();
    }

    public void setTheme(String themeName) {
        ConfigVo configVo = configService.findByName(THEME_KEY);
        if (configVo == null) {
            configVo = new ConfigVo();
        }
        configVo.setName(THEME_KEY);
        configVo.setValue(themeName);
        configVo.setAutoLoad("no");
        configService.saveOrUpdate(configVo);
    }

    public List<ThemeVo> getThemes() {
        List<ThemeVo> list = new ArrayList<ThemeVo>();
        File themeDir = new File(appContext.getThemeRoot());
        if (themeDir.isDirectory()) {
            File[] themes = themeDir.listFiles(new DirectoryFilter());
            for (File theme : themes) {
                ThemeVo themeVo = ThemeUtils.getThemeContent(theme.getPath() + File.separator + ThemeService.CONFIG_FILE);
                themeVo.setDirectory(theme.getName());
                list.add(themeVo);
            }
        }
        return list;
    }

    public ThemeVo getThemeByDir(String directory) {
        String themeDir = appContext.getThemeRoot() + File.separator + directory;
        File theme = new File(themeDir);
        if (theme.exists()) {
            ThemeVo themeVo = ThemeUtils.getThemeContent(theme.getPath() + File.separator + ThemeService.CONFIG_FILE);
            themeVo.setDirectory(directory);
            return themeVo;
        }
        return new ThemeVo();
    }
}
