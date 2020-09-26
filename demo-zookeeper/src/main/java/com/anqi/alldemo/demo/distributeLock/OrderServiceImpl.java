package com.anqi.alldemo.demo.distributeLock;

import com.anqi.alldemo.demo.distributeLock.zksequen.ZkSequenTemplateLock;

public class OrderServiceImpl {

    private static OrderNumberGenerator generator = new OrderNumberGenerator();
//    Lock lock = new ZkTemplateLock();
    Lock lock = new ZkSequenTemplateLock();
    public void createOrder() {
        String orderNum = null;
        try {
            lock.getLock();
            //获取订单号
            orderNum = generator.getNumber();
        } finally {
            lock.unlock();
        }
        System.out.println(Thread.currentThread().getName() + "---->" + orderNum);


    }

}
