package com.anqi.alldemo.demo.zookeeper.sequence;

import com.anqi.alldemo.demo.zookeeper.ZookeeperClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Sequences {

    @Autowired
    ZookeeperClient client;

    public Long sequenceOrder() throws Exception {
        return client.sequence(ZkSequenceEnum.ORDER);
    }

    public Long sequenceItem() throws Exception {
        return client.sequence(ZkSequenceEnum.ITEM);
    }

    public Long sequenceShoppingCart() throws Exception {
        return client.sequence(ZkSequenceEnum.SHOOPING_CART);
    }
}
