package com.ouywl.spi;

/**
 * @description:
 * @author: oywl
 * @time: 2022-6-16 10:53
 */

public class WX implements IPayMent{
    @Override
    public String returnMessage(String type) {
        return "微信支付";
    }
}
