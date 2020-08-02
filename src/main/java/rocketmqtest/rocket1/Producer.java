package rocketmqtest.rocket1;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

public class Producer {
    public static void main(String[] args) {

        /**
         * 1.创建生产者
         * 2.设置name server
         * 3.启动生产者
         * 4.创建消息对象，用生产者发送
         * 5.关闭生产者
         */
        DefaultMQProducer producer  = new DefaultMQProducer("producer-1");
        producer.setNamesrvAddr("127.0.0.1:9876");
        try {
            producer.start();
            producer.setRetryTimesWhenSendAsyncFailed(3);
            Message message = new Message("topic-1","测试一个消息在不是广播的情况下是否回发送给该群组的所有成员".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(message
                    , new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.println(sendResult.getSendStatus());
                }

                @Override
                public void onException(Throwable throwable) {
                    throwable.printStackTrace();
                }
            });
//            producer.shutdown();

        } catch (MQClientException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (RemotingException e) {
            e.printStackTrace();
        }
    }
}
