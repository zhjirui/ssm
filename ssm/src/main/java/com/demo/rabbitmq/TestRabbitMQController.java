package com.demo.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

//@Controller
@RequestMapping("/rabbitMQ")
public class TestRabbitMQController {

    private final static Logger logger = LoggerFactory.getLogger(TestRabbitMQController.class);
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping(value = "/sendMsg")
    @ResponseBody
    public Object sendMsg(HttpServletRequest request){
        String msg = request.getParameter("msg");
        //此处不这样设置编码直接传msg接收端会存在中文乱码问题
        Message message = MessageBuilder.withBody(msg.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setContentEncoding("utf-8")
                .setMessageId(UUID.randomUUID()+"")
                .build();
        logger.debug("接收到msg:" + msg);
        amqpTemplate.convertAndSend("mq.asdfExChange", "mq.asdf.send", message);
        return "success";
    }
    @RequestMapping(value = "/sendMsg1")
    @ResponseBody
    public Object sendMsg1(HttpServletRequest request){
        String msg = request.getParameter("msg");
        amqpTemplate.convertAndSend("mq.qwerExChange", "mq.asdf.send", msg);
        return "success";
    }

}
