package com.anqi.alldemo.demozookeeper;

import com.anqi.alldemo.demozookeeper.zookeeper.ZookeeperClient;
import com.anqi.alldemo.demozookeeper.zookeeper.sequence.Sequences;
import com.anqi.alldemo.demozookeeper.zookeeper.sequence.ZkSequenceEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@SpringBootTest(classes = DemoZookeeperApplication.class)
@RunWith(SpringRunner.class)
public class DemoZookeeperApplicationTests {

    @Autowired
    Sequences sequence;

    @Test
    public void testSequence() throws Exception {

        CountDownLatch latch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(()->{
                for (int j = 0; j < 10; j++) {
                    Long aLong = null;
                    try {
                        aLong = sequence.sequenceItem();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    System.out.println(aLong);
                    latch.countDown();
                }
            });
            t.start();
        }





    }

}
