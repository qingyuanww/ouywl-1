package com.oywl.Thread;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.Arrays;

/**
 * @description:
 * @author: oywl
 * @time: 2024-03-14 21:02
 */

public class OnlyMain {
    public static void main(String[] args) {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadInfo[] allThreads = threadMXBean.dumpAllThreads(false, false);
        Arrays.stream(allThreads).forEach((v)->{
            System.out.println(v.getThreadId()+"-->"+v.getThreadName());
        });
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
