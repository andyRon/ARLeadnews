package top.andyron.listener;

/**
 * 消息消费者
 * @author andyron
 **/

import com.alibaba.fastjson.JSONObject;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import top.andyron.pojo.User;

@Component
public class HelloListener {
    @KafkaListener(topics = {"andy-kafka-hello"})
    public void onMessage(String message){
        if(!StringUtils.isEmpty(message)){
            System.out.println(message);
        }
    }

    @KafkaListener(topics = {"andy-kafka-hello2"})
    public void onMessage2(String message){
        if(!StringUtils.isEmpty(message)){
            User user = JSONObject.parseObject((String) message, User.class);
            System.out.println(user);
        }
    }
}

