package com.springboot.demo.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
/**
 * @author dengjianhan
 * @className XmlUtil
 * @description
 * @date 2020/5/17 20:42
 */
public class XmlUtil {
    /**
     *  接收xml字符串，返回一个map
     * @param xmlStr xml文件字符串
     * @return
     */
    public static Map<String, Object> xml2Map(String xmlStr) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            // 将xml格式的字符串转换成Document对象
            Document doc = DocumentHelper.parseText(xmlStr);
            // 获取根节点
            Element root = doc.getRootElement();
            // 将xml的所有叶节点的name-value封装进Map
            leafNode2Map(map, root);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 将xml的所有叶节点的name-value封装进Map
     *
     * @param map
     * @param node
     */
    @SuppressWarnings("unchecked")
    private static void leafNode2Map(Map<String, Object> map, Element node) {
        List<Element> elements = node.elements();
        if (elements == null || elements.size() == 0) {
            map.put(node.getName(), node.getTextTrim());
        } else {
            for (Element element : elements) {
                // 递归
                leafNode2Map(map, element);
            }
        }
    }
}
