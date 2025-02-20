package com.messaging.rabbitmq.productVariants.RPC;



import java.util.UUID;

import com.messaging.rabbitmq.traits.ConnectionTraits;
import com.messaging.rabbitmq.traits.QueueTraits;
import com.messaging.rabbitmq.traits.RPCCLientTraits;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RPCClientCompleted {

    private static final String RPC_REQUEST_QUEUE_NAME = "queue.rpc.request";
    private static final String RPC_REPLY_QUEUE_NAME = "queue.rpc.reply";
    private static final String RPC_REQUEST_EXCHANGE_NAME = "";
    private static final String HOST = "localhost";

    public static void main(String[] argv) throws Exception {

        ConnectionTraits connectionTraits = new ConnectionTraits() {};
        QueueTraits queueTraits = new QueueTraits() {};
        RPCCLientTraits rpcCLientTraits = new RPCCLientTraits() {};

        Connection connection = connectionTraits.createConnection(HOST);
        Channel channel = connection.createChannel();
        queueTraits.declareQueue(channel, RPC_REPLY_QUEUE_NAME, false, false, false, null);

        String correlationId = UUID.randomUUID().toString();

        // Create properties with correlation ID and reply queue
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder()
                .correlationId(correlationId)
                .replyTo(RPC_REPLY_QUEUE_NAME)
                .build();

        String message = "myRequest";
        
        rpcCLientTraits.publishRPCRequest(channel, message, RPC_REQUEST_EXCHANGE_NAME, RPC_REQUEST_QUEUE_NAME, RPC_REPLY_QUEUE_NAME, true, props, correlationId);

    }
}
