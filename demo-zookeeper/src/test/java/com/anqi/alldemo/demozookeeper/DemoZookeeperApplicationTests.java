package com.anqi.alldemo.demozookeeper;

import com.anqi.alldemo.demozookeeper.zookeeper.sequence.Sequences;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
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
    public void testSequence() throws Exception {

        List list = new ArrayList();

        final CyclicBarrier cyclicBarrier = new CyclicBarrier(11);
        for (int i = 0; i < 10; i++) {
          new Thread(() -> {
                Long aLong = null;
                    try {
                        aLong = sequence.sequenceItem();
                        log.info("!!!!!!!!!!!!!!：{}", aLong);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

          }).start();

        }
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
