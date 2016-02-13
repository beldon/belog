package belog.service;

import belog.filter.DirectoryFilter;
import belog.pojo.vo.ThemeVo;
import belog.utils.ThemeUtils;
import org.junit.Test;

import java.io.File;

/**
 * Created by Beldon
 */
public class FileTest {

    @Test
    public void testDirList() throws Exception{
        File file = new File("D:\\test");
        File[] files = file.listFiles(new DirectoryFilter());
        System.out.println(files[0].getParent());
        System.out.println(files);
    }

    @Test
    public void testThemeConfig() throws Exception {
        ThemeVo themeVo = ThemeUtils.getThemeContent("F:\\Java\\git\\beldon\\belog\\web\\src\\main\\webapp\\template\\theme\\beldon\\theme.xml");
        ThemeVo themeVo2 = ThemeUtils.getThemeContent("F:\\Java\\git\\beldon\\belog\\web\\src\\main\\webapp\\template\\theme\\default\\theme.xml");
        System.out.println(themeVo);
    }
}
