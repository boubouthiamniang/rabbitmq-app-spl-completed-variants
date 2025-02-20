package com.messaging.rabbitmq.productVariants.topicExchange;

import com.messaging.rabbitmq.traits.ConnectionTraits;
import com.messaging.rabbitmq.traits.ExchangeTraits;
import com.messaging.rabbitmq.traits.ProducerTraits;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class PublisherTopicCompleted {

    private static final String EXCHANGE_NAME = "exchage.*";
    private static final String ROUTING_KEY = "routingKeyXp";
    private static final String HOST = "localhost";
    private static final String EXCHANGE_TYPE = "topic";


    public static void main(String[] argv) throws Exception {

        ConnectionTraits connectionTraits = new ConnectionTraits() {};
        ExchangeTraits exchangeTraits = new ExchangeTraits() {}; 
        ProducerTraits producerTraits = new ProducerTraits() {};

        Connection connection = connectionTraits.createConnection(HOST);
        Channel channel = connection.createChannel();

        exchangeTraits.declareExchange(channel, EXCHANGE_NAME, EXCHANGE_TYPE);

        String message = "Thank you Mario, but our princess is in another castle";

        producerTraits.publish(channel, EXCHANGE_NAME, ROUTING_KEY, message);
    }
    
}
