package me.beldon.boot.loader;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;

/**
 * Created by Beldon
 */
public interface IClassLoader {
    Class<?> findClassBySelf(String name) throws ClassNotFoundException;

    Class<?> loadClassBySelf(String name) throws ClassNotFoundException;

    Class<?> loadClassBySelf(String name, boolean resolve) throws ClassNotFoundException;

    URL getResourceBySelf(String name);

    Enumeration<URL> getResourcesBySelf(String name) throws IOException;

    InputStream getResourceAsStreamBySelf(String name);
}
