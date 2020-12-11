package com.springboot.demo.util.watermark;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.tomcat.util.http.fileupload.FileUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author dengjianhan
 * @className WaterRemarkUtils
 * @description aspose-cells 方式excel 做水印处理
 * @date 2020/5/12 15:07
 */
public class WaterRemarkUtils {

    /**
     * 根据内容创建水印的透明背景图片 byte[]
     * @param content
     * @return
     * @throws IOException
     */
    public static byte[] createWaterMark(String content) throws IOException {
        Integer width = 1000;
        Integer height = 800;
        // 获取bufferedImage对象
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        String fontType = "宋体";
        Integer fontStyle = java.awt.Font.BOLD;
        Integer fontSize = 50;
        java.awt.Font font = new java.awt.Font(fontType, fontStyle, fontSize);
        // 获取Graphics2d对象
        Graphics2D g2d = image.createGraphics();
        image = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
        g2d.dispose();
        g2d = image.createGraphics();
        //设置字体颜色和透明度
        g2d.setColor(new Color(0, 0, 0, 80));
        // 设置字体
        g2d.setStroke(new BasicStroke(1));
        // 设置字体类型  加粗 大小
        g2d.setFont(font);
        //设置倾斜度
        g2d.rotate(Math.toRadians(-10), (double) image.getWidth() / 2, (double) image.getHeight() / 2);
        FontRenderContext context = g2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(content, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = -bounds.getY();
        double baseY = y + ascent;
        // 写入水印文字原定高度过小，所以累计写水印，增加高度
        g2d.drawString(content, (int) x, (int) baseY);
        // 设置透明度
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        // 释放对象
        g2d.dispose();
        // 先把读进来的图片放到一个ByteArrayOutputStream中，以便产生ByteArray
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOut);
        return byteArrayOut.toByteArray();
    }

    /**
     * 返回带水印的excel文档
     * @param imageData
     * @return
     * @throws Exception
     */
    public static SXSSFWorkbook getWorkbook(byte[] imageData) throws Exception {
        Workbook workbook = new Workbook();
        Worksheet sheet = workbook.getWorksheets().get(0);
        sheet.setBackground(imageData);
        String path = "e:\\BackImageSheet.xlsx";
        workbook.save(path);
        SXSSFWorkbook sxssfWorkbook = new SXSSFWorkbook(new XSSFWorkbook(path));
        FileUtils.forceDelete(new File(path));
        return sxssfWorkbook;
    }


    public static void main(String[] args) throws Exception {
        byte[] test008s = createWaterMark("test1234562, 超级管理员你可以删除");

        SXSSFWorkbook workbook = getWorkbook(test008s);
        File file = new File("e:\\BackImageSheet222.xlsx");
        FileOutputStream fos = new FileOutputStream(file);
        workbook.write(fos);
    }
}
