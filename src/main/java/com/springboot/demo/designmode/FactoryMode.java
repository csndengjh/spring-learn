package com.springboot.demo.designmode;

import org.junit.Test;

/**
 * @author dengjianhan
 * @className FactoryMode
 * @description
 * @date 2020/9/24 14:37
 */
public class FactoryMode {
    /**
     * 使用场景：
     * 1、日志记录器：记录可能记录到本地硬盘、系统事件、远程服务器等，用户可以选择记录日志到什么地方。
     * 2、数据库访问，当用户不知道最后系统采用哪一类数据库，以及数据库可能有变化时。
     * 3、设计一个连接服务器的框架，需要三个协议，"POP3"、"IMAP"、"HTTP"，可以把这三个作为产品类，共同实现一个接口。
     */


    public interface Phone{
        void call();
    }

    public class HuaWeiPhone implements Phone{

        @Override
        public void call() {
            System.out.println("华为手机拨打电话");
        }
    }

    public class IPhone implements Phone{

        @Override
        public void call() {
            System.out.println("iphone手机拨打电话");
        }
    }

    /**
     * 简单工厂
     */
    public class PhoneCreateFactory{

        public Phone createPhone(String type){
            if ("华为".equals(type)) {
                return new HuaWeiPhone();
            } else if ("苹果".equals(type)){
                return new IPhone();
            }
            return null;
        }
    }

    @Test
    public void test(){
        PhoneCreateFactory factory = new PhoneCreateFactory();
        Phone phone = factory.createPhone("华为");
        phone.call();
    }

    /**
     * 工厂模式
     */
    public interface PhoneFactory{
        Phone createPhone();
    }

    public class IphoneFactory implements PhoneFactory{

        @Override
        public Phone createPhone() {
            return new IPhone();
        }
    }

    public class HuaWeiFactory implements PhoneFactory{

        @Override
        public Phone createPhone() {
            return new HuaWeiPhone();
        }
    }

    @Test
    public void test1(){
        PhoneFactory factory = new IphoneFactory();
        Phone phone = factory.createPhone();
        phone.call();

        PhoneFactory huaWeiPhoneFactory = new HuaWeiFactory();
        Phone phone2 = huaWeiPhoneFactory.createPhone();
        phone2.call();
    }



}
