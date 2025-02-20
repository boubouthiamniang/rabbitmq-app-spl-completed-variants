package com.messaging.rabbitmq.productVariants.PublisherConfirms;

import com.messaging.rabbitmq.traits.ChannelTraits;
import com.messaging.rabbitmq.traits.ConnectionTraits;
import com.messaging.rabbitmq.traits.ProducerConfirmsTraits;
import com.messaging.rabbitmq.traits.QueueTraits;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class PublisherConfirmsCompleted {

    private final static String QUEUE_NAME = "queue.xp";
    private static final String HOST = "localhost";
    static final int MESSAGE_COUNT = 50000;

    public static void main(String[] args) throws Exception {

        ConnectionTraits connectionTraits = new ConnectionTraits() {};
        ChannelTraits channelTraits = new ChannelTraits() {};    
        QueueTraits queueTraits = new QueueTraits() {};
        ProducerConfirmsTraits producerConfirmsTraits = new ProducerConfirmsTraits() {};

        Connection connection = connectionTraits.createConnection(HOST);
        Channel channel = connection.createChannel();

        queueTraits.declareQueue(channel, QUEUE_NAME, false, false, true, null);
        channelTraits.channelConfirmSelect(channel);

        String message = "Thank you mario, but our princess is in another castle";

        producerConfirmsTraits.publishMessagesIndividually(channel, QUEUE_NAME, message, null);
    }
}
