package com.springboot.demo.even;

import com.springboot.demo.SpringbootDemoApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeansException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.Serializable;

/**
 * @author dengjianhan
 * @className MyEvent
 * @description  ApplicationEvent 的事件使用学习
 * @date 2020/10/26 9:07
 */
public class MyEvent extends ApplicationEvent implements Serializable {

    private static final long serialVersionUID = -7643917854546534610L;

    private String data;

    public MyEvent(Object source, String data) {
        super(source);
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Component
    @Slf4j
    public static class MyListener{

        @Async
        @EventListener
        public void handleDemo(MyEvent event) {
            log.info("发布的data为:{}", event.getData());
        }
    }

    @RunWith(value = SpringJUnit4ClassRunner.class)
    @SpringBootTest(classes = SpringbootDemoApplication.class)
    public  static class ListenerTest implements ApplicationContextAware {

        private ApplicationContext context;

        @Override
        public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.context = applicationContext;
        }

        @Test
        public void listener() {
            context.publishEvent(new MyEvent(this, "测试"));
        }
    }
}
