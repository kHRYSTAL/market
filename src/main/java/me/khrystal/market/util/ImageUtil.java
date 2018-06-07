package me.khrystal.market.util;

import net.coobird.thumbnailator.Thumbnailator;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

/**
 * Created by kHRYSTAL on 18/6/7.
 */
public class ImageUtil {
    public static void main(String args[]) {
        String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
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
