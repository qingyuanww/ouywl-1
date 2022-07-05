package com.ouywl.spi;

/**
 * @description:
 * @author: oywl
 * @time: 2022-6-16 10:53
 */

public class ZFB implements IPayMent{
    @Override
    public String returnMessage(String type) {
        return "支付宝支付";
    }
}
