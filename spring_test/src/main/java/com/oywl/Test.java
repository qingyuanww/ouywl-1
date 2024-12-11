package com.oywl;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.CaseFormat;
import javafx.scene.shape.Circle;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import sun.util.resources.LocaleData;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @description:
 * @author: oywl
 * @time: 2022-6-5 8:56
 */

public class Test {
    public static void main(String[] args) throws ParseException, DocumentException {
//        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2022-11-11 12:22:12"));
//        System.out.println("12:22:12".substring(0, 5));
//        String s1 = "";
//        Optional.ofNullable(s1).ifPresent(item -> System.out.println(item));
//
//        String sss = "@Test" +
//                "    public void selectPage";
//
//        String sss2 = "() throws Exception { " + "\n" + "\n" + "this.mockMvc.perform(get(\"/bookingOrder/selectone\")\n" +
//                "                .contentType(MediaType.APPLICATION_JSON))\n" +
//                "                .andExpect(status().isOk());" + "}";
//        for (int i = 0; i < 13; i++) {
//            System.out.println(sss + i + sss2);
//        }
////        String sjh=" 13962562591";
////        String sjh2="13962562591";
////        System.out.println(Long.valueOf(sjh2));
////        System.out.println(Long.valueOf(sjh));
////        System.out.println(Long.valueOf(sjh2));
//
//        System.out.println("AU20230410161627536807".length());
//        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        Date date = new Date(); // date 包括时分秒
//        String s = sdf.format(date); // 把带时分秒的 date 转为 yyyy-MM-dd 格式的字符串
//        System.out.println(s);
////        try {
////            Date date2 = sdf.parse("2022-08-01"); // 把上面的字符串解析为日期类型
////            System.out.println(sdf.format(date2));
////        } catch (ParseException e) {
////            e.printStackTrace();
////        }
//
//        System.out.println("2022-08-01".length());
//        System.out.println("2022-08-01".substring(0,10));
//
//        BigDecimal rate = BigDecimal.valueOf(Long.parseLong("10%".replace("%", ""))).setScale(2, RoundingMode.HALF_UP).divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1));
//
//        System.out.println(rate);
//
//        System.out.println(CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "partCodeName")));
//


        String xml="<COND_A03><IDOC BEGIN=\"1\"><EDI_DC40 SEGMENT=\"1\"><TABNAM>EDI_DC40</TABNAM><MANDT>100</MANDT><DOCNUM>0000000000116617</DOCNUM><DOCREL>755</DOCREL><STATUS>30</STATUS><DIRECT>1</DIRECT><OUTMOD>2</OUTMOD><IDOCTYP>COND_A03</IDOCTYP><MESTYP>COND_A</MESTYP><SNDPOR>SAPPS4</SNDPOR><SNDPRT>LS</SNDPRT><SNDPRN>PS4CLNT100</SNDPRN><RCVPOR>CPI_PRI_2E</RCVPOR><RCVPRT>LS</RCVPRT><RCVPRN>CPIPRD_4EU</RCVPRN><CREDAT>20230306</CREDAT><CRETIM>093036</CRETIM><SERIAL>20230306013036693001</SERIAL></EDI_DC40><E1KOMG SEGMENT=\"1\"><KVEWE>A</KVEWE><KOTABNR>910</KOTABNR><KAPPL>V</KAPPL><KSCHL>ZTP2</KSCHL><VAKEY>ZVEHHX1ETD3A61EU010417</VAKEY><MATNR>HX1ETD3A61EU010417</MATNR><EVRTP>00000</EVRTP><MTART>ZVEH</MTART><POSNR>000000</POSNR><ANZSN>0000000000</ANZSN><VAKEY_LONG>ZVEHHX1ETD3A61EU010417</VAKEY_LONG><MATNR_LONG>HX1ETD3A61EU010417</MATNR_LONG><E1KONH SEGMENT=\"1\"><KNUMH>0000101692</KNUMH><DATAB>20230306</DATAB><DATBI>99991231</DATBI><E1KONP SEGMENT=\"1\"><KSCHL>ZTP2</KSCHL><STFKZ>A</STFKZ><KSTBM>0.000</KSTBM><KSTBW>0.00</KSTBW><KRECH>C</KRECH><KBETR>25500.00</KBETR><KONWA>EUR</KONWA><KPEIN>1</KPEIN><KMEIN>PCE</KMEIN><KUMZA>0</KUMZA><KUMNE>0</KUMNE><MXWRT>0.00</MXWRT><GKWRT>0.00</GKWRT><ZAEHK_IND>01</ZAEHK_IND><KBRUE>0.00</KBRUE><VALTG>00</VALTG><VALDT>00000000</VALDT><ANZAUF>00</ANZAUF><MIKBAS>0.000</MIKBAS><MXKBAS>0.000</MXKBAS><KOMXWRT>0.00</KOMXWRT><KLF_STG>0000</KLF_STG><KLF_KAL>0000</KLF_KAL><ZAEHK_IND_LONG>001</ZAEHK_IND_LONG><KBSTAT>2</KBSTAT></E1KONP></E1KONH></E1KOMG></IDOC></COND_A03>";

        Map<String, Object> objectMap = XmlUtil.xmlToMap(xml);

        System.out.println(objectMap.toString());

        System.out.println(JSON.toJSONString(objectMap));

//        System.out.println( Double.parseDouble("dsa"));

        BigDecimal rate=new BigDecimal("1.1");
        BigDecimal mb = BigDecimal.valueOf(Double.parseDouble("50.5501")).setScale(4, RoundingMode.HALF_UP);
        System.out.println(mb);
        System.out.println(mb.multiply(rate));
        System.out.println(mb.multiply(rate).setScale(4,BigDecimal.ROUND_HALF_UP));
        System.out.println(LocalDateTime.now());
        System.out.println(new Date());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        System.out.println(format.parse("2023-05-12 17:48:27"));
    }
}























