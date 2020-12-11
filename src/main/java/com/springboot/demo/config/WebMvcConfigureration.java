package com.springboot.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  WebMvcConfigurer 可配置多个 taskExecutor
 * @author dengjianhan
 * @date 2019/9/26 16:48
 */
@Configuration
//@ConditionalOnBean(SpringThreadPoolConfiguration.class)
@EnableAsync
public class WebMvcConfigureration implements WebMvcConfigurer {

    // 配置异步@Async请求使用的线程池
    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        configurer.setTaskExecutor(mvcAsyncExecutor());
    }
    
    @Bean
    public AsyncTaskExecutor mvcAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setMaxPoolSize(10);
        executor.setThreadNamePrefix("mvcAsyncExecutor -");
        return executor;
    }

}
