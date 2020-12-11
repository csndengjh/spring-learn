package com.springboot.demo.test;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:lwy
 * @Date:2020/9/22
 * @Description:
 */
public class MatchTest {



    public static <K, V> String replacePlaceHolderWithMapValue(String msg, Map<K,V> placeHolderMap,Map<String, Map<String,String>> matchMap) {
        Pattern pattern = Pattern.compile("\\$\\{\\w+\\}");
        Matcher matcher = pattern.matcher(msg);
        Map<String, String> replaceList = new HashMap();
        while (matcher.find()) {
            String placeHolder = matcher.group(0);
            String key = matcher.group();
            Object value = placeHolderMap.get(key);
            Map<String, String> stringStringMap = matchMap.get(value);
            if (stringStringMap != null) {
                String oldStr = stringStringMap.get(key);
                if (StringUtils.isNotBlank(oldStr)) {
                    msg = msg.replace(oldStr, "");
                }
            }
            if (value != null) {
                replaceList.put(placeHolder, value.toString());
            }
        }
        for (Map.Entry<String, String> keyValue : replaceList.entrySet()) {
            msg = msg.replace(keyValue.getKey(), keyValue.getValue());
        }
        return msg;

    }


    public static void main(String[] args) {

        Map<String, String> kvs = new HashMap<>();
        kvs.put("${ruleId}", "p0lErRGQ7GMKwcHZ1GG");
        kvs.put("${ruleSequence}", "1");
        kvs.put("${ruleEffectiveStartTime}", "1");
        kvs.put("${ruleEffectiveEndTime}", "1");
        kvs.put("${channel}", "MATCH_ALL");
        kvs.put("${ruleResults}", "1");

        Map<String, Map<String,String>> map = new HashMap<>();

        Map<String, String> map2 = new HashMap<>();
        String oldStr = "&&\n" +
                "  toList(\"${channel}\", \";\").contains(this[\"channel\"])\n";
        map2.put("${channel}", oldStr);
        map.put("MATCH_ALL", map2);




        String content = "package com.csair.rules\n" +
                "import java.util.Map\n" +
                "import java.util.List\n" +
                "import java.util.Arrays\n" +
                "import java.util.ArrayList\n" +
                "\n" +
                "rule \"rule_${ruleId}\"\n" +
                "salience ${ruleSequence}\n" +
                "when\n" +
                "$map:\n" +
                "Map(\n" +
                "isDateEffectived(\"${ruleEffectiveStartTime}\", \"${ruleEffectiveEndTime}\")\n" +
                "&&\n" +
                "  toList(\"${channel}\", \";\").contains(this[\"channel\"])\n" +
                ")\n" +
                "then\n" +
                "System.out.println(\"匹配成功，规则ID=${ruleId}\");\n" +
                "((List)($map.get(\"ruleConfigIds\"))).add(\"${ruleId}\");\n" +
                "((List)($map.get(\"ruleResults\"))).add(\"${ruleResults}\");\n" +
                "end";

        String s = replacePlaceHolderWithMapValue(content, kvs, map);
        System.out.println(s);
    }

    @Test
    public void test(){
        String content = "/*! long   long  ago, the is a man called Jack, \n" +
                " he has one boat. !*/";
        // Pattern.DOTALL: 这种模式下 . 可以匹配任何字符，包括换行符
        Pattern p = Pattern.compile("/\\*!(.*)!\\*/", Pattern.DOTALL);
        Matcher m = p.matcher(content);
        if(m.find())
            // 匹配到/*! !*/中的内容
            content = m.group(1);
        // 把两个以上空格的地方缩减为一个空格
        content = content.replaceAll(" {2,}", " ");
        // 开启多行模式，删除每一行开头部分的空格，+表示匹配一个或多个
        content = content.replaceAll("(?m)^ +", "");
        // 匹配到字符串中的第一个元音字母，并替换为VOWEL
        content = content.replaceFirst("[aeiou]", "VOWEL");
        // 下面一段程序演示把字符串中的所有元音字母替换为大写
        Pattern p1 = Pattern.compile("[aeiou]");
        Matcher m1 = p1.matcher(content);
        StringBuffer sb = new StringBuffer();
        while(m1.find()){
            // 非终端添加和替换，
            m1.appendReplacement(sb, m1.group().toUpperCase());
        }
        System.out.println(sb);
        // 终端添加和替换  (把最后的给加上)
        m1.appendTail(sb);
        System.out.println(sb);
    }
}
