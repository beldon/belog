package me.beldon.boot.loader;

import sun.misc.CompoundEnumeration;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by Beldon
 */
public class CoreClassLoader extends ClassLoader {

    private static CoreClassLoader instance;
    private static final Object lock = new Object();
    private ClassLoader webClassLoader = null;

    public static CoreClassLoader getInstance() {
        if (instance == null) {
            synchronized (lock) {
                instance = new CoreClassLoader(Thread.currentThread().getContextClassLoader().getParent());
                System.out.println(Thread.currentThread().getContextClassLoader());
                ClassLoader webClassLoader = Thread.currentThread().getContextClassLoader();
//                instance.webClassLoader = webClassLoader;
                WebHelpClassLoader webHelpClassLoader = new WebHelpClassLoader(webClassLoader, instance);
                try {
                    Field field = ClassLoader.class.getDeclaredField("parent");
                    field.setAccessible(true);
//                    field.set(webClassLoader, null);
                    field.set(webClassLoader, webHelpClassLoader);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                System.out.println(Thread.currentThread().getContextClassLoader().getParent());

            }
        }
        return instance;
    }


    /**
     * 子classLoader列表
     */
    private List<BeClassLoader> classLoaders;

    public CoreClassLoader(ClassLoader parent) {
        super(parent);
        classLoaders = new ArrayList<BeClassLoader>();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        if ("belog.install.po.CommentMeta".equals(name)) {
            System.out.println("belog.install.po.CommentMeta");
        }

        if (webClassLoader != null) {
            try {
                Method method = ClassLoader.class.getDeclaredMethod("findClass", String.class);
                method.setAccessible(true);
                Class<?> clazz = (Class<?>) method.invoke(webClassLoader, name);
                if (clazz != null) {
                    return clazz;
                }
            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
            } catch (InvocationTargetException e) {
//                e.printStackTrace();
            } catch (IllegalAccessException e) {
//                e.printStackTrace();
            }
        }

//        for (BeClassLoader classLoader : classLoaders) {
//            Class clazz = classLoader.findClassBySelf(name);
//            if (clazz != null) {
//                return clazz;
//            }
//        }

        return super.findClass(name);
    }

    @Override
    public Class<?> loadClass(String name) throws ClassNotFoundException {
        if ("belog.install.po.CommentMeta".equals(name)) {
            System.out.println("belog.install.po.CommentMeta");
        }
        Class<?> clazz = null;
        try {
            clazz = super.loadClass(name);
            if (clazz != null) {
                return clazz;
            }
        } catch (ClassNotFoundException e) {

        }

        if (webClassLoader != null) {
            try {
                clazz = webClassLoader.loadClass(name);
                if (clazz != null) {
                    return clazz;
                }
            } catch (ClassNotFoundException e) {

            }
        }

        for (BeClassLoader classLoader : classLoaders) {
            clazz = classLoader.loadClassBySelf(name);
            if (clazz != null) {
                return clazz;
            }
        }

        return clazz;
    }

    public Class<?> loadClass(String name, BeClassLoader source) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            clazz = super.loadClass(name);
            if (clazz != null) {
                return clazz;
            }
        } catch (ClassNotFoundException e) {
        }
        for (BeClassLoader classLoader : classLoaders) {
            if (classLoader != source) {
                clazz = classLoader.loadClassBySelf(name);
                if (clazz != null) {
                    return clazz;
                }
            }
        }
        return clazz;
    }

    @Override
    protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        if ("belog.install.po.CommentMeta".equals(name)) {
            System.out.println("belog.install.po.CommentMeta");
        }
        Class<?> clazz = null;
        try {
            clazz = super.loadClass(name, resolve);
            if (clazz != null) {
                return clazz;
            }
        } catch (ClassNotFoundException e) {

        }

        if (webClassLoader != null) {
            try {
                Method method = ClassLoader.class.getDeclaredMethod("loadClass", String.class, boolean.class);
                method.setAccessible(true);
                clazz = (Class<?>) method.invoke(webClassLoader, name, resolve);
                if (clazz != null) {
                    return clazz;
                }
            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
            } catch (InvocationTargetException e) {
//                e.printStackTrace();
            } catch (IllegalAccessException e) {
//                e.printStackTrace();
            }
        }

        for (BeClassLoader classLoader : classLoaders) {
            clazz = classLoader.loadClassBySelf(name, resolve);
            if (clazz != null) {
                return clazz;
            }
        }
        return clazz;
    }

    protected Class<?> loadClass(String name, boolean resolve, BeClassLoader source) throws ClassNotFoundException {
        Class<?> clazz = null;
        try {
            clazz = super.loadClass(name, resolve);
            if (clazz != null) {
                return clazz;
            }

        } catch (ClassNotFoundException e) {
        }

        for (BeClassLoader classLoader : classLoaders) {
            if (classLoader != source) {
                clazz = classLoader.loadClassBySelf(name, resolve);
                if (clazz != null) {
                    return clazz;
                }
            }
        }
        return clazz;
    }

    @Override
    public URL getResource(String name) {
        URL url = super.getResource(name);
        if (url != null) {
            return url;
        }

        if (webClassLoader != null) {
            url = webClassLoader.getResource(name);
            if (url != null) {
                return url;
            }
        }

        return url;
    }

    /**
     * 获取资源url
     *
     * @param name
     * @param source
     * @return
     */
    public URL getResource(String name, BeClassLoader source) {
        URL url = super.getResource(name);
        if (url != null) {
            return url;
        }

        for (BeClassLoader classLoader : classLoaders) {
            if (classLoader != source) {
                url = classLoader.getResourceBySelf(name);
                if (url != null) {
                    return url;
                }
            }
        }
        return url;
    }


    /**
     * @param name
     * @param source
     * @return
     * @throws IOException
     */
    public Enumeration<URL> getResources(String name, BeClassLoader source) throws IOException {
        int size = classLoaders.size() + 1;
        if (webClassLoader != null) {
            size++;
        }

        @SuppressWarnings("unchecked")
        Enumeration<URL>[] tmp = (Enumeration<URL>[]) new Enumeration<?>[size];
        Enumeration<URL> urls = super.getResources(name);
        tmp[0] = urls;
        for (int i = 0; i < classLoaders.size(); i++) {
            BeClassLoader classLoader = classLoaders.get(i);
            if (classLoader != source) {
                Enumeration<URL> tempUrls = classLoader.getResourcesBySelf(name);
                tmp[i + 1] = tempUrls;
            }
        }

        if (webClassLoader != null) {
            tmp[size-1] = webClassLoader.getResources(name);
        }
        return new CompoundEnumeration<URL>(tmp);
    }

    /**
     * @param name
     * @param source
     * @return
     */
    public InputStream getResourceAsStream(String name, BeClassLoader source) {
        InputStream is = super.getResourceAsStream(name);
        if (is != null) {
            return is;
        }

        if (webClassLoader != null) {
            is = webClassLoader.getResourceAsStream(name);
            if (is != null) {
                return is;
            }
        }


        for (BeClassLoader classLoader : classLoaders) {
            if (classLoader != source) {
                is = classLoader.getResourceAsStreamBySelf(name);
                if (is != null) {
                    return is;
                }
            }
        }
        return is;
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
}
