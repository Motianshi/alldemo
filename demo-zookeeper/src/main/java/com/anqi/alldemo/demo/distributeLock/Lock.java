package com.anqi.alldemo.demo.distributeLock;

public interface Lock {

    /**
     * 获取锁
     */
    void getLock();

    /**
     * 释放锁
     */
    void unlock();
}
