package com.springboot.demo.config;

import com.springboot.demo.service.GitHubLookupService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.Future;

/**
 *  平常开发中有可能需要实现在项目启动后执行的功能，
 *  SpringBoot提供的一种简单的实现方案就是添加一个model并实现CommandLineRunner接口/或者实现ApplicationRunner接口
 *  实现功能的代码放在实现的run方法中
 * @author dengjianhan
 * @date 2019/8/20 17:05
 */
//@Component
@Slf4j
//@RequestMapping("/runner")
// @Order(value=1) 设置项目启动过后执行的先后循序
@Order(value=1)
public class AppRunner implements CommandLineRunner {

    private final GitHubLookupService gitHubLookupService;

    public AppRunner(GitHubLookupService gitHubLookupService) {
        this.gitHubLookupService = gitHubLookupService;
    }

    @Override
    @Async //指定线程池启用新的线程 热加载数据
//    @Async("mvcAsyncExecutor")
    public void run(String... args) throws Exception {
        // Start the clock
        log.info("--> " + Thread.currentThread().getName()+"执行中");
        long start = System.currentTimeMillis();
        Future<Integer> page1 =  gitHubLookupService.find("33");
        Future<Integer> page2 =  gitHubLookupService.find("44");
        Future<Integer> page3 =  gitHubLookupService.find("55");
        // Wait until they are all done
        // Print results, including elapsed time
        log.info("Elapsed time: " + (System.currentTimeMillis() - start));
        log.info("--> " + page1.get());
        log.info("--> " + page2.get());
        log.info("--> " + page3.get());
    }
}
