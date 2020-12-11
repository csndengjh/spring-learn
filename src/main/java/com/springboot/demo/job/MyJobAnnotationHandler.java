package com.springboot.demo.job;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dengjianhan
 * @className MyJobAnnotationHandler
 * @description xxjob 的用法
 * @date 2020/9/25 16:28
 */
@Slf4j
@JobHandler(value = "MyJobHandler")
public class MyJobAnnotationHandler extends IJobHandler {

    @Override
    public ReturnT<String> execute(String param) throws Exception {

        XxlJobLogger.log("XXL-JOB-API, Hello World.");

        log.info("my job api run, param: {}",param);

        return ReturnT.SUCCESS;
    }
}
