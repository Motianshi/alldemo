package com.anqi.alldemo.demo;

import com.anqi.alldemo.demo.zookeeper.sequence.Sequences;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

@SpringBootTest(classes = DemoZookeeperApplication.class)
@RunWith(SpringRunner.class)
public class DemoZookeeperApplicationTests {

    Logger log = LoggerFactory.getLogger(DemoZookeeperApplicationTests.class);

    @Autowired
    Sequences sequence;

    @Test
    public void testBaseSequence() throws Exception {
        for (int i = 0; i < 10; i++) {
            Long aLong = sequence.sequenceItem();
            System.out.println(aLong);
        }

    }
    @Test
    public void testSequence() {

        List list = new ArrayList();

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(101);
        CountDownLatch latch = new CountDownLatch(1);
        for (int i = 0; i < 20; i++) {
          new Thread(() -> {
              try {
                  latch.await();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              Long aLong = null;
                    try {
                        synchronized (this) {
                            for (int j = 0; j < 10; j++) {
                                aLong = sequence.sequenceItem();
                                log.info("time:{},____id:{}", System.currentTimeMillis(), aLong);
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
          }).start();
        }

        latch.countDown();

        try {
            cyclicBarrier.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }


}
