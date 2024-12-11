package com.oy.JVM;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @description:
 * @author: oywl
 * @time: 2022-8-17 17:50
 */

public class impl implements service{

    @Autowired
    service service;
    @Override
    public String t1() {
        return this.service.t1();
    }
}
