package me.beldon.boot.util;

import me.beldon.boot.loader.BeClassLoader;
import me.beldon.boot.loader.CoreClassLoader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.List;

/**
 * class加载相关工具类
 * Created by Beldon
 */
public class ClassLoaderUtils {

    /**
     * 加载jar包
     *
     * @param url jar包路径
     * @return
     */
    public static URLClassLoader loadJar(URL url) {
        return loadJar(new URL[]{url});
    }

    /**
     * 加载jar包
     *
     * @param urls jars包路径
     * @return
     */
    public static URLClassLoader loadJar(URL[] urls) {
        URLClassLoader urlClassLoader = new URLClassLoader(urls, Thread.currentThread().getContextClassLoader());
        return urlClassLoader;
    }

    /**
     * 加载jar包
     *
     * @param jarFile jar文件
     * @return
     */
    public static URLClassLoader loadJar(File jarFile) {
        URL url = null;
        try {
            url = jarFile.toURI().toURL();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return loadJar(url);
    }

    /**
     * 加载jar包
     *
     * @param jarFiles jars包文件
     * @return
     */
    public static URLClassLoader loadJar(File[] jarFiles) {
        URL urls[] = fileToUrl(jarFiles);
        return loadJar(urls);
    }

    public static URLClassLoader loadJar(List<File> jarFiles) {
        File[] files = jarFiles.toArray(new File[jarFiles.size()]);
        return loadJar(files);
    }

    public static URLClassLoader loadJar(String jarPath) {
        File jarFile = new File(jarPath);
        URL url = null;
        if (jarFile.exists()) {
            try {
                jarFile.toURI().toURL();
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
        }
        return loadJar(url);
    }

    /**
     * @param jarsPath
     * @return
     * @TODO
     */
    public static URLClassLoader loadJar(String jarsPath[]) {
        return null;
    }

    /**
     * 加载jar
     *
     * @param urlClassLoader 目标clasloader
     * @param urls           文件url
     * @return
     */
    public static URLClassLoader addUrls(URLClassLoader urlClassLoader, URL[] urls) {
        try {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            boolean accessible = method.isAccessible();     // 获取方法的访问权限
            if (accessible == false) {
                method.setAccessible(true);     // 设置方法的访问权限
            }
            for (URL url : urls) {
                method.invoke(urlClassLoader, url);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return urlClassLoader;
    }

    /**
     * 加载jar
     *
     * @param urlClassLoader classLoader
     * @param url            文件url
     * @return
     */
    public static URLClassLoader addUrl(URLClassLoader urlClassLoader, URL url) {
        return addUrls(urlClassLoader, new URL[]{url});
    }


    /**
     * 生成一个BeClassLoader
     *
     * @param jarFiles
     * @param coreClassLoader
     * @return
     */
    public static BeClassLoader loadJar(File[] jarFiles, CoreClassLoader coreClassLoader) {
        URL[] urls = fileToUrl(jarFiles);
        BeClassLoader beClassLoader = new BeClassLoader(urls, coreClassLoader);
        return beClassLoader;
    }

    public static BeClassLoader loadJar(List<File> jarFiles, CoreClassLoader coreClassLoader) {
        URL[] urls = fileToUrl(jarFiles);
        BeClassLoader beClassLoader = new BeClassLoader(urls, coreClassLoader);
        return beClassLoader;
    }


    /**
     * 把File类转 url
     *
     * @param jarFiles 目标文件
     * @return URL
     */
    private static URL[] fileToUrl(File[] jarFiles) {
        URL urls[] = new URL[jarFiles.length];
        try {
            for (int i = 0; i < jarFiles.length; i++) {
                urls[i] = jarFiles[i].toURI().toURL();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return urls;
    }

    /**
     * 把File类转 url
     *
     * @param jarFiles 目标文件
     * @return URL
     */
    private static URL[] fileToUrl(List<File> jarFiles) {
        URL urls[] = new URL[jarFiles.size()];
        try {
            for (int i = 0; i < jarFiles.size(); i++) {
                urls[i] = jarFiles.get(i).toURI().toURL();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
        return urls;
    }
}
