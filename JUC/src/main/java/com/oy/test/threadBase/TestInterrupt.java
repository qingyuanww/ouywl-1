package com.oy.test.threadBase;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * @description: step-2
 *
 *  两阶段终止模式，实现 监控线程，比如监控系统运行情况
 *  两阶段终止模式
 *  线程sleep时被打断，打断标识为false，所以要进行异常的捕获
 * @author: oywl
 * @time: 2024-03-17 17:39
 */
@Slf4j
public class TestInterrupt {
    public static void main(String[] args) throws Exception{
        TwoPhaseTermination termination = new TwoPhaseTermination();
        termination.start();

        Thread.sleep(3000);
        termination.stop();

    }

}
//如何优雅的停止线程
@Slf4j
class  TwoPhaseTermination{

    private Thread monitor;
    public void start(){
        monitor=new Thread(new Runnable() {

            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    if(interrupted){
                        log.debug("线程被打断,料理后事");
                        break;
                    }
                    try {
                        //如果在睡眠的时候被interrupt 打断标识还是为true,但是会抛出异常，所以要捕获后重新设置标识
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                        log.debug("捕获异常");
                        //重新设置打断标识
                        Thread.currentThread().interrupt();
                    }
                    log.debug("执行监控记录");
                }

            }
        });
        monitor.start();
    }

    public void stop(){
        monitor.interrupt();
    }

}
