package me.khrystal.market.util;

/**
 * Created by kHRYSTAL on 18/6/7.
 */
public class PathUtil {
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/projectdev/image";
        } else {
            basePath = "/Users/kHRYSTAL/IdeaProjects/market/src/main/resources";
        }
        return basePath;
    }
}
