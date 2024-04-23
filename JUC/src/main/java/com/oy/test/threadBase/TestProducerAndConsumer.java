package com.oy.test.threadBase;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;

/**
 * @description: wait和notifyAll 实现 生产者和消费者
 * @author: oywl
 * @time: 2024-03-17 18:51
 */
@Slf4j
public class TestProducerAndConsumer {
    public static void main(String[] args) {
        MessageQueue queue = new MessageQueue(3);


        for (int i = 0; i < 5; i++) {
            int id = i;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    queue.put(new Message(id, "值为:" + id));
                }
            }, "生产者" + i).start();
        }

        //消费者消费
        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                while (true) {
                    Thread.sleep(1000);
                    Message message = queue.take();
                }
            }
        }).start();


    }
}

//消息队列类，线程之间的通信
@Slf4j
class MessageQueue {
    final LinkedList<Message> list = new LinkedList<Message>();
    int capcity;//最大容量

    public MessageQueue(int capcity) {
        this.capcity = capcity;
    }

    //消费者获取消息
    public Message take() {
        synchronized (list) {
            //当没有消息时，消费者等待
            while ((list.isEmpty())) {
                try {
                    log.debug("队列为空,消费者等待");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //从队列头部获取消息
            Message message = list.removeFirst();
            //唤醒 所有生产者
            list.notifyAll();
            log.debug("已消费消息:{}", message);
            return message;
        }

    }

    //生产者存入消息
    public void put(Message message) {
        synchronized (list) {
            //检查对象是否已满，已满则等待消费者消费
            while (list.size() == capcity) {
                try {
                    log.debug("队列已满，等待消费者消费");
                    list.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            //将消息加入队列尾部
            list.addLast(message);
            log.debug("已生产消息:{}", message);
            //唤醒 所有正在等待的消费者
            list.notifyAll();
        }
    }

}

@Getter //final 不能有子类，无法覆盖
final class Message {
    private int id;
    private Object object;

    public Message(int id, Object object) {
        this.id = id;
        this.object = object;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", object=" + object +
                '}';
    }
}
