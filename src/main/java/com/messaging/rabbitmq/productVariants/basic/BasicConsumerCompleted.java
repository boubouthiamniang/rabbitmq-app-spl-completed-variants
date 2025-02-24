package com.messaging.rabbitmq.productVariants.basic;

import com.messaging.rabbitmq.traits.ConnectionTraits;
import com.messaging.rabbitmq.traits.ConsumerTraits;
import com.messaging.rabbitmq.traits.QueueTraits;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class BasicConsumerCompleted {

    private static final String QUEUE_NAME = "queue.xp";
    private static final String HOST = "localhost";

    public static void main(String[] argv) throws Exception {

        ConnectionTraits connectionTraits = new ConnectionTraits() {};
        QueueTraits queueTraits = new QueueTraits() {};
        ConsumerTraits consumerTraits = new ConsumerTraits() {};

        Connection connection = connectionTraits.createConnection(HOST);
        Channel channel = connection.createChannel();

        queueTraits.declareQueue(channel, QUEUE_NAME, false, false, false, null);

        consumerTraits.consume(channel, QUEUE_NAME, true);
    }
}
