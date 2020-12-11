package com.springboot.demo.test;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.TemplateExportParams;
import com.alibaba.fastjson.JSONObject;
import com.springboot.demo.entity.ResponseDto;
import com.springboot.demo.inherit.Son;
import com.springboot.demo.util.XmlUtil;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName: Demo
 * @Description:
 * @Author: dengjianhan
 * @Date: 2019/11/28 10:34
 */
public class Demo {

    private static final Logger log = LoggerFactory.getLogger(Demo.class);

    private static List<Son> sonList = new ArrayList<>();

    static {
        sonList.add(new Son(1,1,"张一"));
        sonList.add(new Son(2,2,"张二"));
    }

    @Test
    public void test3() {
        NumberFormat currency = NumberFormat.getCurrencyInstance(); //建立货币格式化引用
        NumberFormat percent = NumberFormat.getPercentInstance();  //建立百分比格式化引用
        percent.setMaximumFractionDigits(3); //百分比小数点最多3位
        BigDecimal loanAmount = new BigDecimal("150.48"); //贷款金额
        BigDecimal interestRate = new BigDecimal("122.008"); //利率
        BigDecimal interest = loanAmount.multiply(interestRate); //相乘
        System.out.println("贷款金额:\t" + currency.format(loanAmount)); //贷款金额: ￥150.48
        System.out.println("利率:\t" + percent.format(interestRate));  //利率: 0.8%
        System.out.println("利息:\t" + currency.format(interest)); //利息: ￥1.20
        log.debug("你好");
        log.info("info 你好");
    }

    @Test
    public void test4() {
        Son son = new Son(1, 2, "里斯");

        Son son2 = new Son(1, 22, "张三");
        son2.setCount(22);
        BeanUtils.copyProperties(son2,son);
        System.out.println(son);



    }

    @Test
    public void  test2(){
        BigDecimal divide = (BigDecimal.ONE.subtract(BigDecimal.ONE)).divide(BigDecimal.ONE);
        System.out.println(divide);
    }

    @Test
    public void test5(){
        String str = "(Body:'{\"actionName\":\"agree\",\"candidate\":[{\"account\":\"gaoxiangfeng1\",\"id\":\"32775\",\"name\":\"高向峰\"}],\"createTime\":1577689082002,\"creator\":{\"account\":\"boptest\",\"id\":\"4771337\",\"name\":\"boptest\"},\"eventType\":\"taskCreate\",\"flowKey\":\"gysyb_bop_ypkhkhlc\",\"instId\":\"20000006707254\",\"nodeId\":\"UserTask11\",\"nodeName\":\"饮片业务主任（高向峰）\",\"originCandidate\":[{\"account\":\"gaoxiangfeng1\",\"id\":\"32775\",\"name\":\"高向峰\"}],\"procDefId\":\"20000006557394\",\"procDefName\":\"国药事业部_饮片客户开户流程\",\"removeTaskIds\":[],\"taskId\":\"20000006707369\",\"timestamp\":1577689173587}' MessageProperties [headers={}, contentType=text/plain, contentEncoding=UTF-8, contentLength=0, receivedDeliveryMode=PERSISTENT, priority=0, redelivered=false, receivedExchange=t-bop-bpm, receivedRoutingKey=cust-open, deliveryTag=2, consumerTag=amq.ctag-IV7AWF0ML3sbbZxCwfHj6A, consumerQueue=q-bop-bpm-cust-open])";

        str = str.replace("(","").replace(")", "");
        String message = "MessageProperties";
        if (str.contains(message)) {
            int messagePropertiesIndex = str.indexOf("MessageProperties");
            str = str.substring(0,messagePropertiesIndex).replace("Body:","").replace("\'","");
            System.out.println(str);
            Object parse = JSONObject.parse(str);
            System.out.println(parse);
        }

    }

    @Test
    public void test6(){
        double pi=232323.2093232;
        BigDecimal bigDecimal = new BigDecimal(pi);
        //取一位整数
        System.out.println(new DecimalFormat("0").format(bigDecimal));
        //取一位整数和两位小数
        System.out.println(new DecimalFormat("#.##").format(bigDecimal));

    }

    @Test
    public void test7(){

        try {

            // 使用基本编码
            String base64encodedString = Base64.getEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);

            // 解码
            byte[] base64decodedBytes = Base64.getDecoder().decode(base64encodedString);

            System.out.println("原始字符串: " + new String(base64decodedBytes, "utf-8"));
            base64encodedString = Base64.getUrlEncoder().encodeToString("runoob?java8".getBytes("utf-8"));
            System.out.println("Base64 编码字符串 (URL) :" + base64encodedString);

            StringBuilder stringBuilder = new StringBuilder();

            for (int i = 0; i < 10; ++i) {
                stringBuilder.append(UUID.randomUUID().toString());
            }

            byte[] mimeBytes = stringBuilder.toString().getBytes("utf-8");
            String mimeEncodedString = Base64.getMimeEncoder().encodeToString(mimeBytes);
            System.out.println("Base64 编码字符串 (MIME) :" + mimeEncodedString);

            byte[] decode = Base64.getMimeDecoder().decode(mimeEncodedString);
            System.out.println("Base64 编码字符串解码 (MIME) :" + new String(decode));

        }catch(UnsupportedEncodingException e){
            System.out.println("Error :" + e.getMessage());
        }
    }

    @Test
    public void test8(){
        CountDownLatch countDownLatch = new CountDownLatch(2){
            @Override
            public void await() throws InterruptedException {
                super.await();
                System.out.println(Thread.currentThread().getName() +  " count down is ok");
            }
        };
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is done");
                countDownLatch.countDown();
            }
        },"thread1");
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is done");
                countDownLatch.countDown();
            }
        },"thread2");

        thread.start();
        thread2.start();
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("====end====");
    }


    @Test
    public void test9() {
        String str = "hifhsdifhs";
        System.out.println(str.hashCode());
        Son son = new Son(1,1,"乔峰");

        System.out.println(son.hashCode());
        Son son2 = new Son(1,1,"萧峰");

        System.out.println(son2.hashCode());
        HashMap<Son,String> map = new HashMap<Son, String>();
        map.put(son,"天龙八部");
        System.out.println(map.get(son2));

    }


    @Test
    public void test10() {
    String ctoeo ="<p><img src=\"/manager/ftp/getImage?content=/album/2019/8/12/73491665-f4f2-4a27-98d1-eb4962fa7b11.jpg\" title=\"微信图片_20190812162222.jpg\" alt=\"微信图片_20190812162222.jpg\"/>" +
            "<img src=\"/manager/ftp/getImage?content=/album/2019/8/12/e15680dc-3827-4d65-ba2c-4071cd9ea294.jpg\" title=\"微信图片_20190812162225.jpg\" alt=\"微信图片_20190812162225.jpg\"/>" +
            "<img src=\"/manager/ftp/getImage?content=/album/2019/8/12/5dd54568-093f-4f4f-ba72-51a89321ac9d.jpg\" title=\"微信图片_20190812162240.jpg\" alt=\"微信图片_20190812162240.jpg\"/>" +
            "<img src=\"/manager/ftp/getImage?content=/album/2019/8/12/a6e1eb72-ac3d-4306-9af9-f77b9dfd44fa.jpg\" title=\"微信图片_20190812162236.jpg\" alt=\"微信图片_20190812162236.jpg\"/>" +
            "<img src=\"/manager/ftp/getImage?content=/album/2019/8/12/cfaecae3-bb20-4d0b-a204-3bd9db0daf68.jpg\" title=\"微信图片_20190812162229.jpg\" alt=\"微信图片_20190812162229.jpg\"/>" +
            "<img src=\"/album/2019/8/12/7c678062-1f4b-4d85-820a-c25a6935696b.jpg\" title=\"微信图片_20190812162233.jpg\" alt=\"微信图片_20190812162233.jpg\"/>" +
            "<img src=\"/album/2019/8/12/a15d2aa9-dae2-4bfb-9554-f1f22896945c.jpg\" title=\"微信图片_20190812162244.jpg\" alt=\"微信图片_20190812162244.jpg\"/>" +
            "<img src=\"/album/2019/8/12/019ff751-d97e-4889-aa00-c03f548931c8.jpg\" title=\"微信图片_20190812162250.jpg\" alt=\"微信图片_20190812162250.jpg\"/>" +
            "<img src=\"/album/2019/8/12/8431edbe-afce-4dab-9bc3-e678fc2295bc.jpg\" title=\"微信图片_20190812162254.jpg\" alt=\"微信图片_20190812162254.jpg\"/></p>";
        Map<String, Object> stringObjectMap = XmlUtil.xml2Map(ctoeo);
        System.out.println(stringObjectMap);

    }

    @Test
    public void test11(){
        BigDecimal count = new BigDecimal("054454545454542655262.00000");
        System.out.println(count.toPlainString());
        BigDecimal paymentPrice = new BigDecimal("50");
        BigDecimal bigDecimal = paymentPrice.setScale(2, BigDecimal.ROUND_HALF_UP);
        System.out.println(bigDecimal.toString());
    }

    @Test
    public void test12(){
        String endNumber = "580003777886";
        String substring = endNumber.substring(endNumber.length() - 4);
        System.out.println(substring);
    }



    @Test
    public void test13(){
        final int number = 1;
        final Integer one = 1;
        switch (number) {
            case '1':
                System.out.println("1");
                break;
            default:
                break;
        }
    }

    /**
     * StringEscapeUtils 的转义和反转义
     */
    @Test
    public void test14(){
        String sql = "1' or '1'='1";

        String javaScript = "alert('21313')";

        System.out.println("xml4转义:"+ StringEscapeUtils.escapeHtml4("<font>chen磊  xing</font>"));

        System.out.println("转成Unicode编码:"+ StringEscapeUtils.escapeJava("成磊"));


        String xmk = StringEscapeUtils.escapeXml11("<name>陈磊兴</name>");

        System.out.println("转义XML："+ xmk);

        System.out.println("反转义XML："+StringEscapeUtils.unescapeXml(xmk));

        System.out.println("转义javaScrpt:"+ StringEscapeUtils.escapeEcmaScript(javaScript));
    }


    @Test
    public void test15() throws IOException {
        TemplateExportParams params = new TemplateExportParams(
                "D:\\BaiduNetdiskDownload\\airLineRate-export-template.xlsx");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("data", "2014-12-25");
        Workbook workbook = ExcelExportUtil.exportExcel(params, map);
        FileOutputStream fos = new FileOutputStream("D:/BaiduNetdiskDownload/excel.xls");
        workbook.write(fos);
        fos.close();
    }


    /**
     * java  反射的使用测试
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     */
    @Test
    public void test17() throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        ResponseDto res = new ResponseDto();
        res.setCode(2);
        res.setMes("hha");

        Class<? extends ResponseDto> resClass = res.getClass();
        // 反射获取字段, name成员变量
        Field[] fields = resClass.getDeclaredFields();

        Field code = resClass.getDeclaredField("mes");
        // 私有属性允许访问修改
        code.setAccessible(true);
        System.out.println(code.get(res));
        code.set(res,"code32342423");
        System.out.println(code.get(res));

        System.out.println("****************************");
        Arrays.stream(fields).forEach(
                field -> {
                    System.out.println(field.getName());
                }
        );

        System.out.println("****************************");

        Method[] methods = resClass.getMethods();

        Arrays.stream(methods).forEach(
                mt -> {
                    String mn = mt.getName();
                    Object dv = mt.getDefaultValue();
                    String returnType = mt.getReturnType().getName();
                    System.out.println("方法名称：" + mn + "    默认值：" + dv + "    返回类型：" + returnType);
                }
        );

        System.out.println("****************************");

        Demo demo = new Demo();
        Field listField = demo.getClass().getDeclaredField("sonList");
        System.out.println(listField.getGenericType());
        Class<?> type = listField.getType();

        System.out.println("数据的类型" + type.getTypeName());
        //获取 list 字段的泛型参数
        ParameterizedType listGenericType = (ParameterizedType) listField.getGenericType();
        String typeName = listGenericType.getTypeName();
        System.out.println("list里泛型的类名"+ typeName);
        //获取 list 字段的泛型参数
        if (List.class.equals(type)) {
            Type genericType = listField.getGenericType();
            if (genericType instanceof ParameterizedType) {
                ParameterizedType pt = (ParameterizedType) genericType;
                //得到对象list中实例的类型
                Class clz = (Class) pt.getActualTypeArguments()[0];
                //获取到属性的值的Class对象
                Class clazz = listField.get(demo).getClass();
                Method m = clazz.getDeclaredMethod("size");
                //调用list的size方法，得到list的长度
                int size = (Integer) m.invoke(listField.get(demo));
                for (int i = 0; i < size; i++) {
                    Method getM = clazz.getDeclaredMethod("get", int.class);
                    getM.setAccessible(true);
                    Object invoke = getM.invoke(listField.get(demo), i);
                    System.out.println(invoke.toString());
                }
            }

        }


    }



    @Test
    public void test18(){

        System.out.println(new Date());
        int num = 3;
        String str = "张三";
        add(num, str);
        System.out.println(num);
        System.out.println(str);
    }



    public static void add(int num, String str){
        num = num +2;
        str = "李四";
    }


    @Test
    public void stringTest19(){
        String[] attributeItems = {"abc","def","ghj"};
        String join = String.join("&&", attributeItems);
        System.out.println(join);
        char c = join.charAt(0);
        System.out.println(c);
    }

    @Test
    public void test() {

        //language=JSON5
        String json3 = "{\"name\":\"藏撒\",\"age\":23}";
        System.out.println(json3);

        //language=JSON5
        String json = "";

    }

}

