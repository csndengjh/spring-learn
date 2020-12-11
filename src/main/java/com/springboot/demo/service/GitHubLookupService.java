package com.springboot.demo.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

/**
 * @author dengjianhan
 * @date 2019/8/20 17:07
 */
@Service
@Slf4j
public class GitHubLookupService {

    private final RestTemplate restTemplate;

    public GitHubLookupService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Async
    public Future<Integer> find(String id) throws InterruptedException {
        log.info("Looking up " + id);
        String url = String.format("https://api.github.com/users/%s", id);
        Thread.sleep(100L);
        return new AsyncResult<>(Integer.valueOf(id));
    }
 }
