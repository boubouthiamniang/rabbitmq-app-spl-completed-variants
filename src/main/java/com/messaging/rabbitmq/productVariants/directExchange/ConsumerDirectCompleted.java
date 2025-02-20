package com.messaging.rabbitmq.productVariants.directExchange;


import com.messaging.rabbitmq.traits.ChannelTraits;
import com.messaging.rabbitmq.traits.ConnectionTraits;
import com.messaging.rabbitmq.traits.ConsumerTraits;
import com.messaging.rabbitmq.traits.ExchangeTraits;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ConsumerDirectCompleted {

    private final static String QUEUE_NAME = "queue.xp";
    private final static String EXCHANGE_NAME = "exchange.xp";
    private static final String EXCHANGE_TYPE = "direct";
    private static final String ROUTING_KEY = "routingKeyXp";
    private static final String HOST = "localhost";

    public static void main(String[] argv) throws Exception {

        ConnectionTraits connectionTraits = new ConnectionTraits() {};
        ChannelTraits channelTraits = new ChannelTraits() {};    
        ExchangeTraits exchangeTraits = new ExchangeTraits() {}; 
        ConsumerTraits consumerTraits = new ConsumerTraits() {};

        Connection connection = connectionTraits.createConnection(HOST);
        Channel channel = connection.createChannel();

        exchangeTraits.declareExchange(channel, EXCHANGE_NAME, EXCHANGE_TYPE);

        channelTraits.channelQueueBind(channel, QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        consumerTraits.consume(channel, QUEUE_NAME, true);
    }
}