package com.anqi.demo.demoasync.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@Service
@EnableAsync
public class OrderService {

    private static final Logger LOG = LoggerFactory.getLogger(OrderService.class);

    @Autowired
    OrderBehaviorService orderBehaviorService;

    /**
     * 创建订单
     * @return nhj
     */
    public String createOrder(String orderInfo) {
        //订单落库
        LOG.info("订单信息落库");
        //处理关联业务
        try {
            Thread.sleep(3000);
            //记录用户创建订单的行为
            orderBehaviorService.saveCreateOrderBehave(orderInfo);
            //记录用户访问订单服务的行为
            orderBehaviorService.saveOrderBehave(orderInfo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "创建成功";
    }
}
