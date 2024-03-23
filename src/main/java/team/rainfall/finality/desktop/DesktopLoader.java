package team.rainfall.finality.desktop;

import team.rainfall.finality.Core;
import team.rainfall.finality.ILoader;
import team.rainfall.finality.Plugin;
import team.rainfall.finality.utils.FileUtil;
import team.rainfall.finality.utils.Logger;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;

public class DesktopLoader implements ILoader {
    private KevinClassLoader classLoader;
    private static Logger loaderLogger = Logger.getLogger("Finality DesktopLoader");
    public DesktopLoader(){
        try {
            classLoader = new KevinClassLoader(new URL[]{},ClassLoader.getSystemClassLoader());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Plugin[] loadPlugins() {
        loaderLogger.info("Load Plugins");
        File pluginDir = new File(FileUtil.getLocalPath()+"Finality"+File.separator+"Plugins");
        if(pluginDir.isDirectory() && pluginDir.exists()){
            try {
                for (File file : pluginDir.listFiles()) {
                    loaderLogger.info("Find file "+file.getName());
                    if (!file.isDirectory() && file.getName().endsWith(".jar"))
                        loaderLogger.info("Find Plugins and Load "+file.getName());
                        classLoader.addURL2(file.toURI().toURL());
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return new Plugin[0];
    }

    public KevinClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public void load() {
        try {
            loaderLogger.info("Load Game Core");
            loaderLogger.info("KevinClassLoader URLs "+classLoader.getURLs());
            classLoader.addURL2(new File(Core.getINSTANCE().gameCorePath).toURI().toURL());
            //idk why I should load these classes, but it does work.
            classLoader.loadClass("age.of.civilizations2.jakowski.lukasz.SliderMenu");
            classLoader.loadClass("age.of.civilizations2.jakowski.lukasz.Slider");
            //Invoke the main method of AoH2 to launch the game.
            classLoader.loadClass("age.of.civilizations2.jakowski.lukasz.desktop.DesktopLauncher").getDeclaredMethod("main",String[].class).invoke(null,(Object) new String[]{});
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                 InvocationTargetException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
