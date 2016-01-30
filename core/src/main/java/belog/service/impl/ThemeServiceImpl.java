package belog.service.impl;


import belog.pojo.vo.ConfigVo;
import belog.service.ConfigService;
import belog.service.ThemeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Beldon
 */
@Service
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
        ConfigVo configVo = new ConfigVo();
        configVo.setName(THEME_KEY);
        configVo.setValue(themeName);
        configVo.setAutoLoad("no");
        configService.saveOrUpdate(configVo);
    }
}
