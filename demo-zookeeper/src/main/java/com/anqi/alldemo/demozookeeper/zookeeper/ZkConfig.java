package com.anqi.alldemo.demozookeeper.zookeeper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:zookeeper.properties")
public class ZkConfig {

    @Value("${zk.host}")
    private String host;
    @Value("${zk.sequence-path}")
    private String sequencePath;

     @Bean
    public ZookeeperClient zookeeperClient() {
        return new ZookeeperClient(host, sequencePath);
    }
}
