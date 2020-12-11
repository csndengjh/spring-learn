package com.springboot.demo.util.watermark;


import org.apache.poi.ooxml.POIXMLDocumentPart;
import org.apache.poi.openxml4j.opc.ZipPackagePart;
import org.apache.poi.xssf.usermodel.XSSFPictureData;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @author dengjianhan
 * @className ExcelWatermarkUtils
 * @description 背景图的方式加水印
 * @date 2020/5/8 11:18
 */


public class ExcelWatermarkUtils {


    public static boolean mark(XSSFSheet sheet, String text) {

        if (sheet.getRelationParts() == null) {
            System.out.println("Sheet没有设置背景，无法添加水印");
            return false;
        }

        for (POIXMLDocumentPart.RelationPart relationPart : sheet.getRelationParts()) {
            XSSFPictureData xssfPictureData = relationPart.getDocumentPart();
            if (xssfPictureData.getPackagePart() instanceof ZipPackagePart) {
                ZipPackagePart zipPackagePart = (ZipPackagePart) xssfPictureData.getPackagePart();

                // 获取背景图片
                BufferedImage sourceImage;
                try {
                    sourceImage = ImageIO.read(zipPackagePart.getInputStream());
                } catch (IOException e) {
                    System.out.println("加载背景失败");
                    return false;
                }

                Graphics2D g = (Graphics2D) sourceImage.getGraphics();
                AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f);

                // 透明度
                g.setComposite(alphaChannel);
                // 颜色
                g.setColor(Color.BLACK);
                // 字体
                int fontSize = 64;
                if (text.length() > 9) {
                    fontSize = 52;
                }
                g.setFont(new Font("宋体", Font.BOLD, fontSize));

                FontMetrics fontMetrics = g.getFontMetrics();
                Rectangle2D rect = fontMetrics.getStringBounds(text, g);

                // calculates the center coordinates
                int centerX = (sourceImage.getWidth() - (int) rect.getWidth()) / 2 - 50;
                int centerY = sourceImage.getHeight() / 2 + 50;

                // 倾斜角度
                g.rotate(Math.toRadians(-30), centerX, centerY);

                // draw text
                g.drawString(text, centerX, centerY);
                // 覆盖原来背景
                try {
                    ImageIO.write(sourceImage, "png", zipPackagePart.getOutputStream());
                } catch (IOException e) {
                    System.out.println("背景写入失败");
                    return false;
                }
                // 手动释放资源
                g.dispose();
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {
            //模板文件，需要设置透明背景
            String path = "E:\\template.xlsx";
            //OPCPackage pkg=OPCPackage.open(path);
            XSSFWorkbook excel = new XSSFWorkbook(path);
            XSSFSheet sheet = excel.getSheetAt(0);
            boolean result = ExcelWatermarkUtils.mark(sheet, "test1234562 , 超级管理员你可以删除");
            System.out.println("添加水印结果：" + result);
            //输出添加水印后的文件
            excel.write(new FileOutputStream("E:\\test.xlsx"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
