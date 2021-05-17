package com.anqi.demo.demopool2.pool;

import com.anqi.demo.demopool2.pool.fac.StringPoolFac;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;

public class StringPoolTest {

    private static final Logger LOG = LoggerFactory.getLogger(StringPoolTest.class);

    public static void main(String[] args) {
        StringPoolFac fac = new StringPoolFac();
        GenericObjectPoolConfig<String> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(2);
        config.setMinIdle(1);
        config.setMaxWaitMillis(3000);
//        config.set
        StringPool pool = new StringPool(fac, config);
        for (int i = 0; i < 3; i++) {
            String s = "";
            try {
                s = pool.borrowObject();
                LOG.info("str:{}", s);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!s.equals("")) {
                    pool.returnObject(s);
                }
            }
        }
    }
}

