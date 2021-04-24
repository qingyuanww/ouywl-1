package com.ouywl.activemq.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * @program: ouywl
 * @description: 消费者
 * @author: ouyangwl
 * @create: 2021-04-23 22:20
 **/
public class JmsConsumer {
    public static final String DEFAULT_BROKER_URL="tcp://192.168.22.128:61616";//虚拟机的ip
    public static final String TOPIC_NAME="topic01";//队列名
    public static void main(String[] args) throws JMSException, IOException {
        System.out.println("我是2号消费者");
        //brokeURL MQ的地址
        ActiveMQConnectionFactory acf = new ActiveMQConnectionFactory(DEFAULT_BROKER_URL);
        //2、通过连接工厂获得连接，并启动访问
        Connection con = acf.createConnection();
        con.start();//开始连接，注意，虚拟机得开着，，
        //3、创建会话
        // 事务和签收
        Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //4、创建目的地destination
        Topic topic = session.createTopic(TOPIC_NAME);
        //5、创建消费者
        MessageConsumer consumer = session.createConsumer(topic);

        //同步阻塞方式 ，一直等待，过时不候
//        while (true){
//            //死等着,只等几秒，nowait  ，这里有个强制类型转换，MQ有五种消息类型
//            TextMessage receive = (TextMessage) consumer.receive();//这里一直在等待，所以程序没有结束
//            if(receive!=null){
//                System.out.println("******消费者接收到消息："+receive.getText());
//            }else {
//                break;
//            }
//        }

        //通过监听的方式来消费消息
//        consumer.setMessageListener(new MessageListener() {
//            @Override
//            public void onMessage(Message message) {
//                if(message!=null&&message instanceof TextMessage){
//                    TextMessage textMessage= (TextMessage) message;
//                    try {
//                        System.out.println("MessageListener监听的消息："+textMessage.getText());
//                    } catch (JMSException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
        //lambda表达式
        consumer.setMessageListener((message -> {
            if(message!=null&&message instanceof TextMessage){
                TextMessage textMessage= (TextMessage) message;
                try {
                    System.out.println("MessageListener监听的消息（topic）："+textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }));
        System.in.read();//这里需要让控制台不要结束，因为消息监听需要时间
        consumer.close();
        session.close();
        con.close();
        /** 注意：先启动订阅， 再启动生产，不然发布的都是废消息
         * 1、启动3个消费者，再启动生产者
         * 结果，每个消费者都消费所有msg！
         */
    }
}
