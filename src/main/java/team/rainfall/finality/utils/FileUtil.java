package team.rainfall.finality.utils;

import java.io.File;

public class FileUtil {
    public static String getLocalPath() {
        return  (new File("")).getAbsolutePath() + File.separator;
    }
}
