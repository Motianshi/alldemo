package com.anqi.demo.demopool2.pool;

import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * 字符串池
 */
public class StringPool extends GenericObjectPool<String> {
    public StringPool(PooledObjectFactory<String> factory) {
        super(factory);
    }

    public StringPool(PooledObjectFactory<String> factory, GenericObjectPoolConfig<String> config) {
        super(factory, config);
    }
}
