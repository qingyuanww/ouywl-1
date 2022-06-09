package com.oywl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: oywl
 * @time: 2022-6-5 8:55
 */
@Component
public class UserService {
    @Autowired
    private OrderService orderService;
}
