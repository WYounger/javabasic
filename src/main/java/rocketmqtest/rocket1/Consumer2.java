package rocketmqtest.rocket1;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class Consumer2 {
    public static void main(String[] args) {
        /**
         * 1.创建消费者
         * 2.设置name server
         * 3.订阅主题
         * 4.注册消息监听
         * 5.开始
         */
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-1");
        consumer.setNamesrvAddr("localhost:9876");
        try {
            consumer.subscribe("topic-1","*");
            consumer.registerMessageListener(new MessageListenerConcurrently() {
                @Override
                public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                    for (MessageExt messageExt : list){
                        try {
                            System.out.println("receive: "+new String(messageExt.getBody(), RemotingHelper.DEFAULT_CHARSET));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    }
                    return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
                }
            });
            consumer.start();
            System.out.println("consumer2 is started");
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
