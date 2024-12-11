package com.oy.JVM2;

import java.util.ArrayList;
import java.util.concurrent.Executors;

/**
 * @description: -Xms500m -Xmx500m -XX:NewRatio=3 比例
 * @author: oywl
 * @time: 2022-8-18 14:10
 */

public class OuoOfMemoryTest {

    public static void main(String[] args) {
        ArrayList list = new ArrayList();
        while ((true)) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            list.add(new byte[1024 * 1024]);
        }
    }
}

