package rocketmqtest;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {
    public static void main(String[] args) throws Exception {
        //Instantiate with a producer group name.
        DefaultMQProducer producer = new
                DefaultMQProducer("producer-group-1");
        // Specify name server addresses.
//        producer.setVipChannelEnabled(false);
        producer.setNamesrvAddr("127.0.0.1:9876");
        //Launch the instance.
        producer.start();
        String msgContent = "set delay!";
            //Create a message instance, specifying topic, tag and message body.
            Message msg = new Message("topic-1" /* Topic */,
                    "TagA" /* Tag */,
                    msgContent.getBytes(RemotingHelper.DEFAULT_CHARSET) /* Message body */
            );
            msg.setDelayTimeLevel(3);
            //Call send message to deliver message to one of brokers.
            SendResult sendResult = producer.send(msg);
        System.out.println("发送: "+msgContent);
            System.out.printf("%s%n", sendResult);
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}

