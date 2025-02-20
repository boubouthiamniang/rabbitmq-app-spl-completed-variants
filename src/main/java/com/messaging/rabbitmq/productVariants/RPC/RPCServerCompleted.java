package com.messaging.rabbitmq.productVariants.RPC;

import com.messaging.rabbitmq.traits.ChannelTraits;
import com.messaging.rabbitmq.traits.ConnectionTraits;
import com.messaging.rabbitmq.traits.QueueTraits;
import com.messaging.rabbitmq.traits.RPCServerTraits;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RPCServerCompleted {

    private static final String RPC_REQUEST_QUEUE_NAME = "queue.rpc.reply";
    private static final String HOST = "localhost";

    public static void main(String[] argv) throws Exception {

        ConnectionTraits connectionTraits = new ConnectionTraits() {};
        ChannelTraits channelTraits = new ChannelTraits() {};    
        QueueTraits queueTraits = new QueueTraits() {};
        RPCServerTraits rpcServerTraits = new RPCServerTraits() {};

        Connection connection = connectionTraits.createConnection(HOST);
        Channel channel = connection.createChannel();
            
        queueTraits.declareQueue(channel, RPC_REQUEST_QUEUE_NAME, true, false, false, null);
        queueTraits.purgeQueue(channel, RPC_REQUEST_QUEUE_NAME);
        channelTraits.channelBasicQos(channel, 1);

        rpcServerTraits.handleMessage(channel, RPC_REQUEST_QUEUE_NAME);
    } 
}
