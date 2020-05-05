package com.anqi.alldemo.demo;

import com.anqi.alldemo.demo.iputil.IPZone;
import com.anqi.alldemo.demo.iputil.QQWry;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@SpringBootTest(classes = DemoZookeeperApplication.class)
@RunWith(SpringRunner.class)
public class IpUtilTest {
    QQWry qqWry;
    @Before
    public void initQQwry() throws IOException {
        qqWry = new QQWry();
    }

    @Test
    public void testIp() {
        IPZone ip = qqWry.findIP("218.82.214.115");
        System.out.println(ip.getIp());
        System.out.println(ip.getMainInfo());
        System.out.println(ip.getSubInfo());
    }

}
