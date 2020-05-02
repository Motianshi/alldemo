package com.anqi.alldemo.demozookeeper.test;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.ACL;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class testzk {

    public static final String URL = "localhost:2181";
    public static final int SESSION_TIMEOUT = 5000;

    private static CountDownLatch latch = new CountDownLatch(1);

    public static void main(String[] args) throws IOException{

        ZooKeeper zk = new ZooKeeper(URL, SESSION_TIMEOUT, (watchedEvent)->{
                Watcher.Event.KeeperState state = watchedEvent.getState();
                Watcher.Event.EventType type = watchedEvent.getType();
                if (state == Watcher.Event.KeeperState.SyncConnected) {
                    System.out.println("连接成功");
                    latch.countDown();

                }
            });


        try {
            latch.await();
//            zk.create("/testnode", "testval".getBytes(),
//                    ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

            Stat stat = new Stat();
            byte[] data = zk.getData("/testnode", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(111);
                }
            }, stat);
            Stat stat2 = new Stat();
            zk.getChildren("/testnode", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(222);
                }
            }, stat2);
            System.out.println(new String(data)+"~"+stat.getCtime());
            zk.exists("/testnode", new Watcher() {
                @Override
                public void process(WatchedEvent watchedEvent) {
                    System.out.println(3333);
                }
            });
            zk.delete("/testnode", stat2.getVersion());



        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
