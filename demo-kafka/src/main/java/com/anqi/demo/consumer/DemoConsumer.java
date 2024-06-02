package com.anqi.demo.consumer;

import com.anqi.demo.producer.DemoProducer;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.*;

@Component
public class DemoConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DemoProducer.class);

    private static final String CONSUMER_GROUP_NAME = "CONSUMER_GROUP_NAME";
    private static final String TOPIC_NAME = "TOPIC_NAME";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:8080");
        // 消费分组名
        props.put(ConsumerConfig.GROUP_ID_CONFIG, CONSUMER_GROUP_NAME);
        // 是否自动提交offset，默认就是true（拉取完消息后提交偏移量offset 下次就从提交的offset后面开始拉取数据 但是一般不会设置为true 因为会存在丢数据问题 比如在设置的时间（比如一秒）内拉取了10条数据 但是对这十条数据的业务逻辑处理要五秒 然而就在这个处理时间内宕机了 数据没有处理完 但是offset以及提交了 就消费不到丢失的数据了 在比如这十条数据处理时间只要0.1秒 我没有有提交offset就宕机了 也会丢数据  而设置false就不会自动提交offset 如果不手动提交 下次启动还会再次消费）
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "true");
        // 自动提交offset的间隔时间
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        //props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        /*
           当消费主题的是一个新的消费组，或者指定offset的消费方式，offset不存在，那么应该如何消费
           latest(默认) ：只消费自己启动之后发送到主题的消息
           earliest：第一次从头开始消费，以后按照消费offset记录继续消费，这个需要区别于consumer.seekToBeginning(每次都从头开始消费)
        */
        //props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        /*
            consumer给broker发送心跳的间隔时间，broker接收到心跳如果此时有rebalance发生会通过心跳响应将,rebalance方案下发给consumer，这个时间可以稍微短一点
         */
        props.put(ConsumerConfig.HEARTBEAT_INTERVAL_MS_CONFIG, 1000);
        // 服务端broker多久感知不到一个consumer心跳就认为他故障了，会将其踢出消费组，对应的Partition也会被重新分配给其他consumer，默认是10秒
         props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 10 * 1000);
        //一次poll最大拉取消息的条数，如果消费者处理速度很快，可以设置大点，如果处理速度一般，可以设置小点
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, 500);
        /*
            如果两次poll操作间隔超过了这个时间，broker就会认为这个consumer处理能力太弱，会将其踢出消费组，将分区分配给别的consumer消费
        */
        props.put(ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG, 30 * 1000);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(TOPIC_NAME));
        // 消费指定分区
        //consumer.assign(Arrays.asList(new TopicPartition(TOPIC_NAME, 0)));
        //消息回溯消费
        //consumer.seekToBeginning(Arrays.asList(new TopicPartition(TOPIC_NAME, 0)));
        //指定offset消费
        //consumer.seek(new TopicPartition(TOPIC_NAME, 0), 10);
        //从指定时间点开始消费
        //List<PartitionInfo> topicPartitions = consumer.partitionsFor(TOPIC_NAME);
        int count = 0;
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> r : records) {
                    String key = r.key();
                    String value = r.value();
                    Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                    TopicPartition topicPartition = new TopicPartition(r.topic(), r.partition());
                    //消费者提交的消费位移=当前消费消息的位移+1
                    OffsetAndMetadata metadata = new OffsetAndMetadata(r.offset() + 1);
                    offsets.put(topicPartition, metadata);
                    if(count % 1000 == 0){
                        consumer.commitSync(offsets);
                    }
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                // 同步提交位移
                consumer.commitSync();
            }finally{
                consumer.close();
            }
        }

    }
}
