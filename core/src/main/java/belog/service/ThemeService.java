package belog.service;

/**
 * 主题模板管理Service
 * Created by Beldon
 */
public interface ThemeService {

    /**
     * 获取模板路径
     *
     * @param template
     * @return
     */
    String getTemplatePath(String template);

    /**
     * 获取模板名称
     *
     * @return
     */
    String getThemeName();

    /**
     * 设置模板
     *
     * @param themeName 模板名称
     */
    void setTheme(String themeName);
}
