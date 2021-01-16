package com.anqi.demo.demoasync.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
public class OrderBehaviorService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderBehaviorService.class);
    /**
     * 记录用户创建订单的行为
     * @param orderInfo
     */
    @Async("asyncExecutor")
    public void saveCreateOrderBehave(String orderInfo) throws InterruptedException {
        Thread.sleep(1000);
        //入库用户订单行为记录表
        LOG.info("xx 用户在" + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "创建了xxx订单");
    }

    /**
     * 记录用户所有订单的行为
     * @param orderInfo
     */
    @Async("asyncExecutor")
    public void saveOrderBehave(String orderInfo) throws InterruptedException {
        Thread.sleep(1000);
        //入库用户订单行为记录表
        LOG.info("xx 用户在" + LocalDateTime.now().format(DateTimeFormatter.BASIC_ISO_DATE) + "访问了订单服务");
    }
}
