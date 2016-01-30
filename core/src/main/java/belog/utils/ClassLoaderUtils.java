package belog.utils;

import java.net.URL;
import java.net.URLClassLoader;

/**
 * class加载相关工具类
 * Created by Beldon
 */
public class ClassLoaderUtils {

    public static URLClassLoader loadUrlJar(URL url){
        URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{url}, Thread.currentThread().getContextClassLoader());
//        try {
//            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
//            boolean accessible = method.isAccessible();     // 获取方法的访问权限
//            if (accessible == false) {
//                method.setAccessible(true);     // 设置方法的访问权限
//            }
//            method.invoke(urlClassLoader, url);
//
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }

        return urlClassLoader;
    }
}
