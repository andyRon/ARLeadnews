package top.andyron.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import top.andyron.pojo.User;

/**
 * 消息生产者
 * @author andyron
 **/
@RestController
public class HelloController {
    @Autowired
    private KafkaTemplate<String,String> kafkaTemplate;

    @GetMapping("/hello")
    public String hello() {
        // 第一个参数：topics
        // 第二个参数：消息内容
        kafkaTemplate.send("andy-kafka-hello","成为优秀的程序员");
        return "ok";
    }

    @GetMapping("/hello2")
    public String hello2() {
        User user = new User();
        user.setName("zhangsan");
        user.setAge(18);
        kafkaTemplate.send("andy-kafka-hello2", JSON.toJSONString(user));
        return "ok";
    }
}
