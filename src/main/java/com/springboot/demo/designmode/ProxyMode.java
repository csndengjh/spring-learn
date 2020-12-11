package com.springboot.demo.designmode;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author dengjianhan
 * @className ProxyMode
 * @description 代理模式
 * @date 2020/9/10 16:17
 */
/** https://www.cnblogs.com/daniels/p/8242592.html  网址提供代理模式详解 **/
@Slf4j
public class ProxyMode {


    /**
     * 静态代理:  优点：可以做到在符合开闭原则的情况下对目标对象进行功能扩展
     */

    /**
     * 定义买房接口
     */
    public interface BuyHouse {
        void buyHouse();
    }

    /**
     * 买房的代理类实现顶级的接口
     */
    public static class BuyHouseProxy implements BuyHouse {

        private BuyHouse buyHouse;

        public BuyHouseProxy(){
            this.buyHouse = new BuyHouseProxy();
        }

        public BuyHouseProxy(final BuyHouse buyHouse) {
            this.buyHouse = buyHouse;
        }

        @Override
        public void buyHouse() {
            log.info("买房准备");
            buyHouse.buyHouse();
            log.info("买房后装修");
        }

    }

    /**
     * 实际的买房者
     */
    public static class BuyHouseImpl implements BuyHouse{
        @Override
        public void buyHouse() {
            log.info("我要买房");
        }
    }



    /****   JDK 动态代理   ****/
    public static class DynamicProxyHandler implements InvocationHandler {

        private Object object;

        public DynamicProxyHandler(final Object object) {
            this.object = object;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            log.info("买房准备");
            Object result = method.invoke(object, args);
            log.info("买房后装修");
            return result;
        }
    }


    /***   CgLIb 动态代理    **/

    public class CglibProxy implements MethodInterceptor {

        private Object target;

        // 创建返回目标实例
        public Object getInstance(final Object target) {
            this.target = target;
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(this.target.getClass());
            enhancer.setCallback(this);
            return enhancer.create();

        }

        @Override
        public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
            log.info("买房准备");
            Object result = methodProxy.invokeSuper(o, objects);
            log.info("买房后装修");
            return result;
        }
    }

    /**
     * 静态代理测试类
     */
    @Test
    public void  proxyTest(){
        BuyHouse buyHouse = new BuyHouseImpl();
        buyHouse.buyHouse();
        BuyHouseProxy proxy = new BuyHouseProxy(buyHouse);
        proxy.buyHouse();
    }


    /**
     * jdk 动态代理测试类
     */
    @Test
    public void  jdkProxyTest(){
        BuyHouse buyHouse = new BuyHouseImpl();
        BuyHouse proxyBuyHouse = (BuyHouse)Proxy.newProxyInstance(BuyHouse.class.getClassLoader(), new Class[]{BuyHouse.class},
                new DynamicProxyHandler(buyHouse));
        proxyBuyHouse.buyHouse();
    }

    /**
     *  CGLIB 动态代理测试类
     */
    @Test
    public void  cglibProxyTest(){
        BuyHouse buyHouse = new BuyHouseImpl();
        CglibProxy proxy = new CglibProxy();
        BuyHouseImpl proxyInstance = (BuyHouseImpl)proxy.getInstance(buyHouse);
        proxyInstance.buyHouse();
    }


}
