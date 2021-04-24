package com.ouywl.activemq.queue;

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
    public static final String QUEUE="queue01";//队列名
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
        //4、创建目的地destination     先 队列
        Queue queue = session.createQueue(QUEUE);
        //5、创建消费者
        MessageConsumer consumer = session.createConsumer(queue);

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
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                if(message!=null&&message instanceof TextMessage){
                    TextMessage textMessage= (TextMessage) message;
                    try {
                        System.out.println("MessageListener监听的消息："+textMessage.getText());
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
                if(message!=null&&message instanceof MapMessage){
                    MapMessage mapMessage= (MapMessage) message;
                    try {
                        System.out.println("MessageListener监听的消息："+mapMessage.getString("K"));
                    } catch (JMSException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        System.in.read();//这里需要让控制台不要结束，因为消息监听需要时间
        consumer.close();
        session.close();
        con.close();
        /**
         * 1、先生产，只启动1号消费者
         * 2、先生产，启动1号，再启动2号， 2号不可以消费
         * 3、先启动1号和2号，再生产，
         * 结果：均衡消费
         */
    }
}
