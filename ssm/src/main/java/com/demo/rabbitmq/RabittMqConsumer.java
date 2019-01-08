package com.demo.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.core.MessageProperties;

/**
 * Created by zhaojr on 2019/1/7.
 * rabbitmq消费者接受消息
 */
public class RabittMqConsumer implements MessageListener {

    static{
        System.out.println("注入成功，监听开启，等待接受服务端消息");
    }

    public void onMessage(Message message) {
        MessageProperties m =message.getMessageProperties();
        String msg = new String (message.getBody());
        System.out.println("消费者接收到消息:" + msg);
    }
}
