package team.rainfall.finality.desktop;

import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

public class KevinClassLoader extends URLClassLoader {
    public KevinClassLoader(URL[] urls, ClassLoader parent) {
        super(urls, parent);
    }
    public Class<?> findClass2(final String name) throws ClassNotFoundException {
        return findClass(name);
    }

    public void addURL2(URL url){
        addURL(url);
    }


}
