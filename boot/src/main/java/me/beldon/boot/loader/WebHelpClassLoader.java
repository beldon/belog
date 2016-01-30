package me.beldon.boot.loader;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by Beldon
 */
public class WebHelpClassLoader extends BeClassLoader implements IClassLoader {
    private ClassLoader webClassLoader;

    public WebHelpClassLoader(ClassLoader webClassLoader, CoreClassLoader coreClassLoader) {
        super(coreClassLoader);
        this.webClassLoader = webClassLoader;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if ("belog.install.po.CommentMeta".equals(name)) {
            System.out.println("belog.install.po.CommentMeta");
        }
        return super.findClass(name);
    }

    public Class<?> findClassBySelf(String name) throws ClassNotFoundException {
        return super.findClass(name);
    }


    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("belog.install.po.CommentMeta".equals(name)) {
            System.out.println("belog.install.po.CommentMeta");
        }
        return super.loadClass(name);
    }

    public Class<?> loadClassBySelf(String name) throws ClassNotFoundException {
        return webClassLoader.loadClass(name);
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if ("belog.install.po.CommentMeta".equals(name)) {
            System.out.println("belog.install.po.CommentMeta");
        }
        return super.loadClass(name, resolve);
    }

    public Class<?> loadClassBySelf(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            Method method = ClassLoader.class.getDeclaredMethod("loadClass", String.class, boolean.class);
            method.setAccessible(true);
            clazz = (Class<?>) method.invoke(webClassLoader, name, resolve);
        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
        } catch (InvocationTargetException e) {
//            e.printStackTrace();
        } catch (IllegalAccessException e) {
//            e.printStackTrace();
        }
        return clazz;
    }

    @Override
    public URL getResource(String name) {
        return super.getResource(name);
    }

    public URL getResourceBySelf(String name) {
        return webClassLoader.getResource(name);
    }


    @Override
    public Enumeration<URL> getResources(String name) throws IOException {
        return super.getResources(name);
    }

    public Enumeration<URL> getResourcesBySelf(String name) throws IOException {
        return webClassLoader.getResources(name);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        return super.getResourceAsStream(name);
    }

    public InputStream getResourceAsStreamBySelf(String name) {
        return webClassLoader.getResourceAsStream(name);
    }
}
