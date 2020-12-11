package com.springboot.demo.designmode;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengjianhan
 * @className 策略模式
 * @description  具体策略可以跟枚举值结合起来，可读性更高
 * @date 2020/7/28 14:26
 */
@Slf4j
public class StrategyMode {

    /**
     * 定义策略接口
     */
    public interface DealStrategy{
        void dealMythod(String option);
    }

    /**
     * 定义具体的策略
     */
    public static class DealSina implements DealStrategy{
        @Override
        public void dealMythod(String option) {
            // TODO
            log.info("DealSina TODO");
        }
    }


    /**
     * 定义具体的策略2
     */
    public static class DealWeChat implements DealStrategy{
        @Override
        public void dealMythod(String option){
            //TODO
            log.info("DealWeChat TODO");
        }
    }

    /**
     * 定义上下文，负责使用DealStrategy角色
     */
    public static class DealContext{
        private String type;
        private DealStrategy dealStrategy;

        public DealContext(String type, DealStrategy dealStrategy) {
            this.type = type;
            this.dealStrategy = dealStrategy;
        }

        public DealStrategy getDealStrategy() {
            return dealStrategy;
        }

        public boolean options(String type) {
            return this.type.equals(type);
        }

        /**
         * 静态代码块加载所有的策略
         */
        private static  List<DealContext> algs = new ArrayList<>();
        static {
            algs.add(new DealContext("Sina",new DealSina()));
            algs.add(new DealContext("WeChat",new DealWeChat()));
        }
        public static void shareOptions(String type) {
            DealStrategy dealStrategy = null;
            for (DealContext dealContext : algs) {
                if (dealContext.options(type)) {
                    dealStrategy = dealContext.getDealStrategy();
                    break;
                }
            }
            dealStrategy.dealMythod(type);

        }
    }



    public static void main(String[] args) {
        DealContext.shareOptions("Sina");
        DealContext.shareOptions("WeChat");
    }



}




