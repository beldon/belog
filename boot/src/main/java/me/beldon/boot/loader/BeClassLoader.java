package me.beldon.boot.loader;


import sun.misc.CompoundEnumeration;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;

/**
 * Created by Beldon
 */
public class BeClassLoader extends URLClassLoader {

    private CoreClassLoader parent;

    private URLClassLoader webClassLoader;

    public BeClassLoader(URL[] urls, CoreClassLoader parent) {
        super(urls);
        this.parent = parent;
        webClassLoader = (URLClassLoader) Thread.currentThread().getContextClassLoader();
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

    public void test() {
//        super.defineClass()
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            Method method = ClassLoader.class.getDeclaredMethod("findClass", String.class);
            method.setAccessible(true);
            clazz = (Class<?>) method.invoke(webClassLoader, name);
            if (clazz != null) {
                return clazz;
            }
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
        }

        if (clazz == null) {
            clazz = super.findClass(name);
        }
        return clazz;
//        return CoreClassLoader.getWebClassLoader().loadClass(name);
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

        Class<?> clazz = null;
        try {
            clazz = webClassLoader.loadClass(name);
        } catch (ClassNotFoundException e) {

        }
        if (clazz == null) {
            clazz = super.loadClass(name);
        }
        return clazz;

//        return CoreClassLoader.getWebClassLoader().loadClass(name);
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
        Class<?> clazz = null;
        try {
            Method method = ClassLoader.class.getDeclaredMethod("loadClass", String.class, boolean.class);
            method.setAccessible(true);
//            clazz = (Class<?>) method.invoke(CoreClassLoader.getWebClassLoader(), name, resolve);
            clazz = (Class<?>) method.invoke(webClassLoader, name, resolve);
            if (clazz != null) {
                return clazz;
            }
        } catch (NoSuchMethodException e) {
        } catch (InvocationTargetException e) {
        } catch (IllegalAccessException e) {
        }finally {
            if (clazz == null) {
//                throw new ClassNotFoundException();
                clazz = super.loadClass(name, resolve);
            }
            return clazz;
        }
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
        URL url = webClassLoader.getResource(name);

        if (url == null) {
            url = super.getResource(name);
        }
        return url;

//        return CoreClassLoader.getWebClassLoader().getResource(name);
    }

    public URL getResourceBySelf(String name) {
        return super.getResource(name);
    }


    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        Enumeration<URL>[] tmp = (Enumeration<URL>[]) new Enumeration<?>[2];
        Enumeration<URL> urls = super.getResources(name);
        tmp[0] = urls;
        tmp[1] = webClassLoader.getResources(name);

        return new CompoundEnumeration<URL>(tmp);

//        return CoreClassLoader.getWebClassLoader().getResources(name);
    }

    public Enumeration<URL> getResourcesBySelf(String name) throws IOException {
        return super.getResources(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        InputStream is = webClassLoader.getResourceAsStream(name);
        if (is == null) {
            is = super.getResourceAsStream(name);
        }

        return is;
//        return CoreClassLoader.getWebClassLoader().getResourceAsStream(name);
    }

    public InputStream getResourceAsStreamBySelf(String name) {
        return super.getResourceAsStream(name);
    }
}
