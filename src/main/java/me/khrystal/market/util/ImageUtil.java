package me.khrystal.market.util;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Created by kHRYSTAL on 18/6/7.
 */
public class ImageUtil {

    private static String basePath = PathUtil.getImgBasePath();
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final Random r = new Random();


    /**
     * 生成缩略图 带水印
     *
     * @param thumbnail
     * @param targetAddr
     * @return 图片相对路径
     */
    public static String generateThumbnail(CommonsMultipartFile thumbnail, String targetAddr) {
        String realFileName = getRandomFileName(); // 随机名
        String extension = getFileExtension(thumbnail); // 扩展名
        makeDirPath(targetAddr);
        String relativeAddr = targetAddr + realFileName + extension;
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try {
            System.out.println(basePath + "/watermark.png");
            Thumbnails.of(thumbnail.getInputStream())
                    .size(200, 200)
                    .watermark(Positions.BOTTOM_RIGHT,
                            // 水印图片地址 透明度
                            ImageIO.read(new File(basePath + "/watermark.png")), 0.5f)
                    // 压缩质量80%
                    .outputQuality(0.8f)
                    .toFile(dest);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        return relativeAddr;
    }

    /**
     * 创建目标路径所涉及到的目录
     *
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath() + targetAddr;
        File dirPath = new File(realFileParentPath);
        if (!dirPath.exists()) {
            // 递归创建
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名
     *
     * @param cFile
     * @return
     */
    private static String getFileExtension(CommonsMultipartFile cFile) {
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名, 当前年月日小时分钟秒 + 随机五位数
     *
     * @return
     */
    public static String getRandomFileName() {
        int randomNum = r.nextInt(89999) + 10000;
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr + randomNum;
    }

    /**
     * storePath 是文件的路径还是目录的路径
     * 如果storePath是文件路径则删除该文件
     * 如果storePath是目录路径则删除该目录下的所有文件
     * 删除图片或路径
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        if (fileOrPath.exists()) {
            if (fileOrPath.isDirectory()) {
                File[] files = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    files[i].delete();
                }
            }
            fileOrPath.delete();
        }
    }

    public static void main(String args[]) {
        System.out.println(basePath); // /Users/kHRYSTAL/IdeaProjects/market/target/classes/
        try {
            Thumbnails.of(new File("/Users/kHRYSTAL/Documents/1.png"))
                    .size(200, 200).watermark(Positions.BOTTOM_RIGHT,
                    // 水印图片地址 透明度
                    ImageIO.read(new File(basePath + "/watermark.png")), 0.5f)
                    // 压缩质量80%
                    .outputQuality(0.8f)
                    .toFile("/Users/kHRYSTAL/IdeaProjects/market/src/main/resources" + "/new_image.jpg");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
