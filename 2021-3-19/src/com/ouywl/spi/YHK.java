package com.ouywl.spi;

/**
 * @description:
 * @author: oywl
 * @time: 2022-6-16 10:54
 */

public class YHK implements IPayMent{
    @Override
    public String returnMessage(String type) {
        return "银行卡支付";
    }
}
