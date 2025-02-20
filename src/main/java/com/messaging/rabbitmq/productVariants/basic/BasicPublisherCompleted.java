package com.messaging.rabbitmq.productVariants.basic;
import com.rabbitmq.client.Connection;
import com.messaging.rabbitmq.traits.ConnectionTraits;
import com.messaging.rabbitmq.traits.ProducerTraits;
import com.messaging.rabbitmq.traits.QueueTraits;
import com.rabbitmq.client.Channel;

public class BasicPublisherCompleted {
    
    private final static String QUEUE_NAME = "queue.xp";
    private final static String EXCHANGE_NAME = "";
    private static final String HOST = "localhost";


    public static void main(String[] argv) throws Exception {

        ConnectionTraits connectionTraits = new ConnectionTraits() {};
        QueueTraits queueTraits = new QueueTraits() {};
        ProducerTraits producerTraits = new ProducerTraits() {};

        Connection connection = connectionTraits.createConnection(HOST);
        Channel channel = connection.createChannel();

        String message = "Thank you Mario, but our princess is in another castle";

        queueTraits.declareQueue(channel, QUEUE_NAME, false, false, false, null);
        producerTraits.publish(channel, EXCHANGE_NAME, QUEUE_NAME, message);
    }
}