package com.example.springbootinaction.rabbitmq.topic;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConsumerTopic {
    public static void main(String[] args) throws IOException, TimeoutException {
        String queueName = "xc_queue_name";
        String queueName_1 = "xc_queue_name_1";
        String queueName_2 = "xc_queue_name_2";
        String queueName_3 = "xc_queue_name_3";
        String queueName_4 = "xc_queue_name_4";
        ConnectionFactory connectionFactory = new ConnectionFactory();
        //服務地址
        connectionFactory.setHost("127.0.0.1");
        //帳號
        connectionFactory.setUsername("guest");
        //密碼
        connectionFactory.setPassword("guest");
        //端口號
        connectionFactory.setPort(5672);
        //創建連接
        Connection connection = connectionFactory.newConnection();
        //創建channel
        Channel channel = connection.createChannel();
        //接收消息的回調函數
        DeliverCallback deliverCallback = (consumerTage, message) -> {
            System.out.println("接收到消息" + new String(message.getBody()));
        };
        //取消消息的回調函數
        CancelCallback cancelCallback = consumerTage ->{
            System.out.println("消息被中斷");
        };

        /**
         * 消費消息
         * 1. 消費哪個隊列
         * 2. 消費成功之後是否需要自動應答，true:自動應答
         * 3. 接受消息的回調函數
         */
        channel.basicConsume(queueName_4,true,deliverCallback,cancelCallback);


    }
}
