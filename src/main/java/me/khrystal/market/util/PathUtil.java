package me.khrystal.market.util;

/**
 * Created by kHRYSTAL on 18/6/7.
 */
public class PathUtil {

    private static String separator = System.getProperty("file.separator");

    /**
     * 获取图片资源根路径
     * @return
     */
    public static String getImgBasePath() {
        String os = System.getProperty("os.name");
        String basePath = "";
        if (os.toLowerCase().startsWith("win")) {
            basePath = "D:/projectdev/image";
        } else {
            // 一般都存在一个独立的图片服务器 通过url引入
            basePath = "/Users/kHRYSTAL/IdeaProjects/market/src/main/resources";
        }
        // 适配不同系统的分隔符
        basePath = basePath.replace("/", separator);
        return basePath;
    }

    /**
     * 获取上传店铺图片路径 参考
     * @link {ShopServiceImpl#addShopImg}
     * 此路径根据不同店铺生成不同文件夹 最后与basePath拼接生成
     * @param shopId
     * @return
     */
    public static String getShopImagePath(long shopId) {
        String imagePath = "/upload/item/shop/" + shopId + "/";
        return imagePath.replace("/", separator);
    }
}
