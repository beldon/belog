package me.beldon.boot.loader;

import me.beldon.boot.util.ClassLoaderUtils;
import org.apache.commons.io.FileUtils;
import sun.misc.CompoundEnumeration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Beldon
 */
public class CoreClassLoader extends ClassLoader {

    private static CoreClassLoader instance;
    private static final Object lock = new Object();
    private static ClassLoader webClassLoader = null;

    /**
     * 子classLoader列表
     */
    private List<BeClassLoader> classLoaders;

    private CoreClassLoader(URL[] urls,ClassLoader parent) {

        super(parent);
//        super(urls,parent);

        classLoaders = new ArrayList<BeClassLoader>();
    }

    public static CoreClassLoader getInstance() {
        if (instance == null) {
            synchronized (lock) {
                newInstance();
            }
        }
        return instance;
    }

    private static void newInstance() {
        webClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(webClassLoader);


        String path = webClassLoader.getResource("/").getPath();
        File classPath = new File(path);
        String installPath = classPath.getParent() + File.separator + "install";
        List<File> list = (List<File>) FileUtils.listFiles(new File(installPath), new String[]{"jar"}, true);
        URLClassLoader classLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
        for (File jarFile : list) {
            try {
                ClassLoaderUtils.addUrl(classLoader, jarFile.toURI().toURL());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }

        File jarFile = list.get(0);
        try {
            instance = new CoreClassLoader(new URL[]{jarFile.toURI().toURL()},webClassLoader.getParent());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try {
            Field field = ClassLoader.class.getDeclaredField("parent");
            field.setAccessible(true);
            field.set(webClassLoader, instance);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("---");
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //先查找子Classloader
        for (BeClassLoader classLoader : classLoaders) {
            Class clazz = classLoader.findClassBySelf(name);
            if (clazz != null) {
                return clazz;
            }
        }
        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        //先查找子Classloader
        for (BeClassLoader classLoader : classLoaders) {
            clazz = classLoader.loadClassBySelf(name);
            if (clazz != null) {
                return clazz;
            }
        }
        return super.loadClass(name);
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = null;
        //先查找子Classloader
        for (BeClassLoader classLoader : classLoaders) {
            clazz = classLoader.loadClassBySelf(name, resolve);
            if (clazz != null) {
                return clazz;
            }
        }
        return super.loadClass(name, resolve);
    }

    @Override
    public URL getResource(String name) {
        URL url = null;
        //先查找子Classloader
        for (BeClassLoader classLoader : classLoaders) {
            url = classLoader.getResourceBySelf(name);
            if (url != null) {
                return url;
            }
        }
        return super.getResource(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        InputStream is = null;
        //先查找子Classloader
        for (BeClassLoader classLoader : classLoaders) {
            is = classLoader.getResourceAsStreamBySelf(name);
            if (is != null) {
                return is;
            }
        }
        return super.getResourceAsStream(name);
    }

    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        int size = classLoaders.size() + 1;

        @SuppressWarnings("unchecked")
        Enumeration<URL>[] tmp = (Enumeration<URL>[]) new Enumeration<?>[size];
        Enumeration<URL> urls = super.getResources(name);
        tmp[0] = urls;
        for (int i = 0; i < classLoaders.size(); i++) {
            BeClassLoader classLoader = classLoaders.get(i);
            Enumeration<URL> tempUrls = classLoader.getResourcesBySelf(name);
            tmp[i + 1] = tempUrls;
        }
        return new CompoundEnumeration<URL>(tmp);
    }

    /**
     * 添加一个Classloader
     *
     * @param classLoader 要添加的ClassLoader
     */
    public void addRegistClassLoader(BeClassLoader classLoader) {
        classLoaders.add(classLoader);
    }

    /**
     * 删除一个classLoader
     *
     * @param classLoader 要删除的ClassLoader
     * @return boolean 删除成与否
     */
    public boolean removeClassLoader(BeClassLoader classLoader) {
        return classLoaders.remove(classLoader);
    }

    /**
     * 是否已经注册了该classLoader
     *
     * @param classLoader
     * @return boolean
     */
    public boolean contains(BeClassLoader classLoader) {
        return classLoaders.contains(classLoader);
    }

    public static ClassLoader getWebClassLoader() {
        if (webClassLoader == null) {
            synchronized (lock) {
                newInstance();
            }
        }
        return webClassLoader;
    }
}
