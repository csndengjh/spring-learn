package com.springboot.demo.designmode;

import org.junit.Test;

/**
 * @author dengjianhan
 * @className AdapterMode
 * @description 适配器模式
 * @date 2020/9/24 10:04
 */
public class AdapterMode {

    /**
     * 适配器模式分为类的适配器,对象的适配器
     *
     * 角色: Target（目标抽象类)   Adapter（适配器类）   Adaptee（适配者类）
     *
     */

    public class Adaptee {

        public void adapteeRequest() {
            System.out.println("被适配者的方法");
        }
    }

    public interface Target {
        void request();
    }

    /**
     * 类的适配器
     */
    public class Adapter extends Adaptee implements Target {
        @Override
        public void request() {
            super.adapteeRequest();
        }
    }
    @Test
    public void test(){
        Adapter adapter = new Adapter();
        adapter.request();
    }

    /**
     * 对象适配器
     */
    public class AdapterTwo implements Target{

        private Adaptee adaptee = new Adaptee();

        @Override
        public void request() {
            adaptee.adapteeRequest();
        }

    }

    @Test
    public void test2(){
        AdapterTwo adapter = new AdapterTwo();
        adapter.request();
    }


    /**
     * 适配器是接口进行转换
     */

    /**
     * 接口输出电压数
     */
    public interface AC {
        int outputAC();
    }

    public class AC110 implements AC {

        @Override
        public int outputAC() {
            return 110;
        }
    }

    public class AC220 implements AC {

        @Override
        public int outputAC() {
            return 220;
        }
    }

    /**
     * 定义接口适配器转换
     */
    public interface DC5Adapter {
        boolean support(AC ac);
        int ouputDC5V(AC ac);
    }

    public class ChinaPowerAdapter implements DC5Adapter {

        public static final int voltage = 220;

        @Override
        public boolean support(AC ac) {
            return voltage == ac.outputAC();
        }

        @Override
        public int ouputDC5V(AC ac) {
            int adapterInput  = ac.outputAC();
            int adapterOutput = adapterInput / 44;
            System.out.println("使用ChinaPowerAdapter变压适配器，输入AC:" + adapterInput + "V" + "，输出DC:" + adapterOutput + "V");
            return adapterOutput;
        }
    }

    public class JapanPowerAdapter implements DC5Adapter {

        public static final int voltage = 110;

        @Override
        public boolean support(AC ac) {
            return voltage == ac.outputAC();
        }

        @Override
        public int ouputDC5V(AC ac) {
            int adapterInput  = ac.outputAC();
            int adapterOutput = adapterInput / 22;
            System.out.println("使用JapanPowerAdapter变压适配器，输入AC:" + adapterInput + "V" + "，输出DC:" + adapterOutput + "V");
            return adapterOutput;
        }
    }

    @Test
    public void test3(){
        ChinaPowerAdapter chinaPowerAdapter = new ChinaPowerAdapter();
        AC220 ac220 = new AC220();
        if (chinaPowerAdapter.support(ac220)) {
            chinaPowerAdapter.ouputDC5V(ac220);
        }

        JapanPowerAdapter japanPowerAdapter = new JapanPowerAdapter();
        AC110 ac110 = new AC110();
        if (japanPowerAdapter.support(ac110)) {
            japanPowerAdapter.ouputDC5V(ac220);
        }

    }


}
