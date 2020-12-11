package com.springboot.demo.example;

import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;
/**
 *  压缩图片
 * @author dengjianhan
 * @date 2019/6/21 16:04
 */
public class ImageCompressionTest {

    public static void main(String[] args) {
        File orgin = new File("d:/data");
        createdThumbnail(orgin);
    }


    private static void createdThumbnail(File file){
        File[] fs = file.listFiles();
        for(File f:fs){
            //若是目录，则递归打印该目录下的文件
            if(f.isDirectory())	{
                createdThumbnail(f);
            }
            if(f.isFile()){
                String fileName = f.getName();
                Integer index = fileName.lastIndexOf(".");
                String contentType = fileName.substring(index,fileName.length());
                String name = fileName.substring(0,index);
                try {
                    File newFile = new File( f.getParent(),name +"_min"+contentType);
                    Thumbnails.of(f).scale(0.25f).outputQuality(0.5f).toFile(newFile);
                    // 删除原图
                    boolean bool =  f.delete();
                    System.out.println("删除的图片:" + f.getName() + "状态为"+bool);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
