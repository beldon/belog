package me.beldon.boot.loader;


import sun.misc.CompoundEnumeration;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * Created by Beldon
 */
public class BeClassLoader extends URLClassLoader {

    private CoreClassLoader parent;

    public BeClassLoader(URL[] urls, CoreClassLoader parent) {
        super(urls);
        this.parent = parent;
        parent.addRegistClassLoader(this);
    }

    public BeClassLoader(URL[] urls) {
        super(urls);
    }

    public BeClassLoader(CoreClassLoader coreClassLoader) {
        super(new URL[]{});
        this.parent = coreClassLoader;
        parent.addRegistClassLoader(this);
    }

    @Override
    public void addURL(URL url) {
        super.addURL(url);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }

    public Class<?> findClassBySelf(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            clazz = super.findClass(name);
        } catch (ClassNotFoundException e) {

        }
        return clazz;
    }

    /**
     * 重写loadClass
     *
     * @param name 类名
     * @return Class
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        try {
            Class<?> clazz = parent.loadClass(name, this);
            if (clazz != null) {
                return clazz;
            }
        } catch (ClassNotFoundException e) {

        }
        return super.loadClass(name);
    }

    /**
     * 重写 loadclass方法，如CoreClassLoader找不到，则从自身classLoader中找
     *
     * @param name    类名
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        try {
            Class<?> clazz = parent.loadClass(name, resolve, this);
            if (clazz != null) {
                return clazz;
            }
        } catch (ClassNotFoundException e) {

        }
        return super.loadClass(name, resolve);
    }

    /**
     * 在自身classloader中查找class，不在父类找
     *
     * @param name Class名称
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> loadClassBySelf(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            clazz = super.loadClass(name);
        } catch (ClassNotFoundException e) {
        } finally {
            return clazz;
        }
    }

    /**
     * @param name
     * @param resolve
     * @return
     * @throws ClassNotFoundException
     */
    public Class<?> loadClassBySelf(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            clazz = super.loadClass(name, resolve);
        } catch (ClassNotFoundException e) {
        } finally {
            return clazz;
        }
    }

    @Override
    public URL getResource(String name) {
        URL url = parent.getResource(name, this);
        if (url != null) {
            return url;
        }
        return super.getResource(name);
    }

    public URL getResourceBySelf(String name) {
        return super.getResource(name);
    }


    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        @SuppressWarnings("unchecked")
        Enumeration<URL>[] tmp = (Enumeration<URL>[]) new Enumeration<?>[2];
        Enumeration<URL> url = parent.getResources(name, this);
        tmp[0] = url;
        tmp[1] = super.getResources(name);
        return new CompoundEnumeration<URL>(tmp);
    }

    public Enumeration<URL> getResourcesBySelf(String name) throws IOException {
        return super.getResources(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        InputStream is = parent.getResourceAsStream(name, this);
        if (is != null) {
            return is;
        }
        return super.getResourceAsStream(name);
    }

    public InputStream getResourceAsStreamBySelf(String name) {
        return super.getResourceAsStream(name);
    }
}
