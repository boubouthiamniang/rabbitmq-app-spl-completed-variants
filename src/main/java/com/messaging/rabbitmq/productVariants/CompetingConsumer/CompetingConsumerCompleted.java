package com.messaging.rabbitmq.productVariants.CompetingConsumer;
import com.messaging.rabbitmq.traits.ChannelTraits;
import com.messaging.rabbitmq.traits.ConnectionTraits;
import com.messaging.rabbitmq.traits.ConsumerTraits;
import com.messaging.rabbitmq.traits.QueueTraits;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class CompetingConsumerCompleted {

  private static final String TASK_QUEUE_NAME = "queue.task";

  private static final String HOST = "localhost";

  public static void main(String[] argv) throws Exception {

      ConnectionTraits connectionTraits = new ConnectionTraits() {};
      ChannelTraits channelTraits = new ChannelTraits() {};    
      QueueTraits queueTraits = new QueueTraits() {};
      ConsumerTraits consumerTraits = new ConsumerTraits() {};

      Connection connection = connectionTraits.createConnection(HOST);
      Channel channel = connection.createChannel();

      queueTraits.declareQueue(channel, TASK_QUEUE_NAME, true, false, false, null);

      channelTraits.channelBasicQos(channel, 1);

      consumerTraits.consumeCompeting(channel, TASK_QUEUE_NAME, false);
  }

}