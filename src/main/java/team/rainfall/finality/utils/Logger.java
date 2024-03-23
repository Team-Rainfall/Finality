package team.rainfall.finality.utils;

public class Logger {
    private String name;
    public static Logger getLogger(String name){
        Logger instance = new Logger();
        instance.name = name;
        return instance;
    }
    public void info(String Context){
        System.out.println("[I] "+name+": "+Context);
    }

    public void warn(String Context){
        System.out.println("[W] "+name+": "+Context);
    }

    public void error(String Context){
        System.out.println("[E] "+name+": "+Context);
    }
}
