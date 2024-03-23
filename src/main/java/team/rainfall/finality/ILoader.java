package team.rainfall.finality;

import team.rainfall.finality.Plugin;

public interface ILoader {
    public Plugin[] loadPlugins();
    public void load();

}
