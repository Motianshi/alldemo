package com.anqi.demo.producer;


import com.anqi.demo.common.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.Date;

@Component
public class DemoProducer {

    @Autowired
    private KafkaTemplate<String, String> template;

    private static final Logger LOG = LoggerFactory.getLogger(DemoProducer.class);


    public void send(String val) {
        ListenableFuture<SendResult<String, String>> res = template.send(Constants.DEMO_TOPIC, val);
        LOG.info("kafka send msg{}", new Date());
        res.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable throwable) {
                LOG.error("kafka send error");
                //todo
            }

            @Override
            public void onSuccess(SendResult<String, String> stringStringSendResult) {
                LOG.info("kafka send success");
            }
        });
    }
}
