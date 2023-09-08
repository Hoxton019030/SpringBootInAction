package com.example.springbootinaction.rabbitmq.direct;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ProducerDirect {
    public static void main(String[] args) throws IOException, TimeoutException {
        String exchangeName = "xc_exchange_name";
        String queueName_1 = "xc_queue_name_1";
        String queueName_2 = "xc_queue_name_2";
        String queueName_3 = "xc_queue_name_3";
        String queueName_4 = "xc_queue_name_4";

        String key_1 = "key_1";
        String key_3 = "key_3";
        String key_4 = "key_4";

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

        /**
         * 創建交換機
         * 1. 交換機名稱
         * 2. 交換機類型 direct, topic, fanout, headers
         * 3. 指定交換機是否需要持久化，如果設置為True，那麼交換機的元數據要持久化
         * 4. 指定交換機在沒有隊列綁定時，是否刪除？
         * 5. Map<String,Object>類型，用來指定我們交換機其他的一些機構話參數，我們在這裡直接設置成Null
         */

        channel.exchangeDeclare(exchangeName, BuiltinExchangeType.DIRECT, true, false, null);

        /**
         * 生產一個隊列
         * 1. 隊列名稱
         * 2. 隊列是否需要持久化，但是要注意，這裡的持久化只是隊列名稱等這些元數據的持久化，不是隊列中消息的持久化
         * 3. 表示隊列是不是私有，如果是私有的，只有唱見他的應用程序才能消費消息
         * 4. 隊列在沒有消費者訂閱的情況下，是否自動刪除
         * 5. 隊列的一些結構化訊息，比如說聲明死信隊列，磁盤隊列會用到
         */
        channel.queueDeclare(queueName_1, true, false, false, null);
        channel.queueDeclare(queueName_2, true, false, false, null);
        channel.queueDeclare(queueName_3, true, false, false, null);
        channel.queueDeclare(queueName_4, true, false, false, null);

        /**
         * 將交換機與隊列綁定
         * 1. 隊列名稱
         * 2. 交換機名稱
         * 3. 路由鍵，在我們直連模式下，可以為我們的隊列名稱
         */
        channel.queueBind(queueName_1, exchangeName, key_1);
        channel.queueBind(queueName_2, exchangeName, key_1);
        channel.queueBind(queueName_3, exchangeName, key_3);
        channel.queueBind(queueName_4, exchangeName, key_4);

        // 要發送的消息
        String message = "Hello rabbitMQ";
        /**
         * 發送消息
         * 1. 發送到哪個交換機
         * 2. 隊列名稱
         * 3. 其他參數訊息
         * 4. 發送消息的消息體
         */
        channel.basicPublish(exchangeName, key_1, null, "key_1 message".getBytes());
        channel.basicPublish(exchangeName, key_3, null, "key_3 message".getBytes());
        channel.basicPublish(exchangeName, key_4, null, "key_4 message".getBytes());
        channel.close();
        connection.close();
    }
}
