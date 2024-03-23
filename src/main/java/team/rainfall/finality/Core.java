package team.rainfall.finality;

import team.rainfall.finality.desktop.DesktopLoader;
import team.rainfall.finality.utils.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Core of the framework.
 * @author anria
 * @since 1.0.0
 */
public class Core {
    public static final Platform platform = Platform.DESKTOP;
    public static Logger logger = Logger.getLogger("FinalityCore");
    private static Core INSTANCE = new Core();
    private static ILoader loaderInstance;
    private ArrayList<Plugin> pluginList = new ArrayList<>();
    public static String gameCorePath = "core.jar";



    private Core() {
		//init();
	}

    /**
     * Initialize the framework.
     * TODO:Add support for android.
     */
    private void init(){
        switch (platform){
            case DESKTOP:
                logger.info("Load Files.");
                loadConfig();
                DesktopLoader loader = new DesktopLoader();
                this.loaderInstance = loader;
                loader.loadPlugins();
                logger.info("Game Core Path is "+this.gameCorePath);
                loader.load();
                break;
            case ANDROID:
                throw new RuntimeException("Finality doesn't support Android yet.");
        }

    }

	public static void main(String[] args){
        try {
            if (new File(args[0]).exists()) gameCorePath = args[0];
        }catch(Exception e){
            e.printStackTrace();
        }
        getINSTANCE();
        switch (platform){
            case DESKTOP:
                logger.info("Load Files.");
                loadConfig();
                DesktopLoader loader = new DesktopLoader();
                loaderInstance = loader;
                loader.loadPlugins();
                logger.info("Game Core Path is "+gameCorePath);
                loader.load();
                break;
            case ANDROID:
                throw new RuntimeException("Finality doesn't support Android yet.");
        }
	}
    public static Core getINSTANCE() {
        return INSTANCE;
    }
    public Plugin getPluginByName(String Name){
        for(Plugin plugin : pluginList){
            if(plugin.getName().equals(Name)) return plugin;
        }
        return null;
    }
    private static void loadConfig(){
        //TODO:Fill it with our codes.
    }

    public ILoader getLoaderInstance() {
        return loaderInstance;
    }
}
enum Platform{
    ANDROID,
    DESKTOP
}
