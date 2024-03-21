package top.andyron.kafkastreamdemo;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.TimeWindows;
import org.apache.kafka.streams.kstream.ValueMapper;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

/**
 * 流式处理
 * @author andyron
 **/
public class KafkaStreamQuickStart {
    public static void main(String[] args) {
        // kafka的配置信息
        Properties properties = new Properties();
        properties.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        properties.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-quickstart");

        // stream 构建器
        StreamsBuilder streamsBuilder = new StreamsBuilder();

        // 流式计算
        streamProcessor(streamsBuilder);

        // 创建KafkaStream对象
        KafkaStreams kafkaStreams = new KafkaStreams(streamsBuilder.build(), properties);
        // 开启流式计算
        kafkaStreams.start();
    }

    /**
     * 流式计算
     * 消息内容格式：hello kafka
     *             hello itcast
     * @param streamsBuilder
     */
    private static void streamProcessor(StreamsBuilder streamsBuilder) {
        // 创建kstream对象，指定从哪个消息生成者（topic）中接收消息
        KStream<String, String> stream = streamsBuilder.stream("andyron-topic-input");

        stream.flatMapValues(new ValueMapper<String, Iterable<String>>() {
            @Override
            public Iterable<String > apply(String value) {
                return Arrays.asList(value.split(" "));
            }
        })
                // 按照value进行聚合处理
                .groupBy((key, value) -> value)
                // 时间窗口设置，每10s聚合一次数据
                .windowedBy(TimeWindows.of(Duration.ofSeconds(10)))
                // 统计单词的个数
                .count()
                // 转换为kStream
                .toStream()
                .map((key, value) -> {
                    System.out.println("key: " + key + ",value: " + value);
                    return new KeyValue<>(key.key().toString(), value.toString());
                })
                // 发送消息
                .to("andyron-topic-out");
    }
}
