package com.ouywl.activemq.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @program: ouywl
 * @description: 队列
 * @author: ouyangwl
 * @create: 2021-04-23 21:43
 **/
public class JmsProduce {
    public static final String DEFAULT_BROKER_URL="tcp://192.168.22.128:61616";//虚拟机的ip
    public static final String QUEUE="queue01";//队列名

    public static void main(String[] args) throws JMSException {
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

        //5、创建消息的 生产者
        MessageProducer producer = session.createProducer(queue);
        //6、发送消息 三条消息
        for (int i = 0; i <9 ; i++) {
            //7、创建消息，好比学生们按照老师的要求 写好的面试题消息
            TextMessage message = session.createTextMessage("msg-" + i);
            MapMessage mapMessage = session.createMapMessage();
            mapMessage.setString("K", "V"+i);
            //8、通过消息生产者发布
            producer.send(message);
            producer.send(mapMessage);
        }
        //9、关闭资源
        producer.close();
        session.close();
        con.close();

        System.out.println("******消息发送到队列完成");

    }
}
