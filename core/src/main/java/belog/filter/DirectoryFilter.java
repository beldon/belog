package belog.filter;

import java.io.File;
import java.io.FileFilter;

/**
 * 文件夹过滤
 * Created by Beldon
 */
public class DirectoryFilter implements FileFilter {
    public boolean accept(File pathname) {
        return pathname.isDirectory();
    }
}
