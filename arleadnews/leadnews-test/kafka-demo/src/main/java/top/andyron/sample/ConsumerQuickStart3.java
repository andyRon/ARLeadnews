package top.andyron.sample;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * 手动提交：同步和异步组合提交
 * 消费者
 * @author andyron
 *
 */
public class ConsumerQuickStart3 {

    public static void main(String[] args) {
        // 1.添加kafka的配置信息
        Properties properties = new Properties();
        // kafka的连接地址
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "group2");
        // key、value的反序列化器
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringDeserializer");

        // 关闭自动提交，手动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

        // 2.消费者对象
        KafkaConsumer<String, String> consumer = new KafkaConsumer<String, String>(properties);

        // 3.订阅主题
        consumer.subscribe(Collections.singletonList("andyron-topic"));

        // 当前线程一直处于监听状态
        try {
            while (true){
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println(record.value());
                    System.out.println(record.key());
                }
                consumer.commitAsync();
            }
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("记录错误信息："+e);
        }finally {
            try {
                consumer.commitSync();
            }finally {
                consumer.close();
            }
        }

    }

}