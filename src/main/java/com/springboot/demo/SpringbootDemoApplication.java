package com.springboot.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @EnableAsync 启用异步
 */
@EnableAsync
@SpringBootApplication(scanBasePackages = {"com.springboot.demo.*", "easycode.*"})
@EnableCaching
@MapperScan(value = "easycode.dao")
public class SpringbootDemoApplication extends AsyncConfigurerSupport {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootDemoApplication.class, args);
//		DocsConfig config = new DocsConfig();
//		config.setProjectPath("C:\\Users\\Administrator\\IdeaProjects\\springboot-demo");
//		config.setProjectName("demo");
//		config.setApiVersion("V1.0");
//		config.setDocsPath("D:\\");
//		config.setAutoGenerate(Boolean.TRUE);
//		Docs.buildHtmlDocs(config);

	}

	/**
	 * extends AsyncConfigurerSupport
     * @Async 没有指定bean 的时候异步线程使用就是这个线程池
	 * 重写 getAsyncExecutor() 方法，配置成异步调用线程池
	 * @return
	 */
	@Override
	public Executor getAsyncExecutor() {
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setMaxPoolSize(2);
		executor.setCorePoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup -");
		executor.initialize();
		return executor;
	}
}
