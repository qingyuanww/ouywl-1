package com.oywl;

/**
 * @description:
 * @author: oywl
 * @time: 2023-5-25 15:17
 */

import cn.hutool.json.XML;

public class SoapUtils {

    public static void main(String[] args) {
        System.out.println(getFlagBySoap(soapCreate("123", "213", "保养")));
    }
    /**
     * 拼接发送的xml
     *
     * @param key
     * @param params
     * @param serviceType
     */
    public static String soapCreate(String key,String params,String serviceType){
        String soap="<soapenv:Envelope "+"xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" "+
                "xmlns:web=\"http://webservice.bjzcyy.cn/\">"+"<soapenv:Header/>"+"<soapenv:Body>"+"<web:ERPInterface>"
                +"<Key>"+key+"</Key>"+"<Params>"+params+"</Params>"+"<serviceType>"+serviceType+"</serviceType>"
                +"</web:ERPInterface>"+"</soapenv:Body>"+"</soapenv:Envelope>";
        return soap;
    }

    /**
     * 解析soap得到flag
     * @param soapResponse
     */
    public static String getFlagBySoap(String soapResponse){
        cn.hutool.json.JSONObject xmlJSONObj = XML.toJSONObject(soapResponse);

        String s=xmlJSONObj.getJSONObject("soapenv:Envelope").getJSONObject("soapenv:Body").getJSONObject("web:ERPInterface")
                .get("Key").toString();
        return s;
    }
}
