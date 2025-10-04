package com.github.binarywang.wxpay.util;

import com.github.binarywang.wxpay.bean.realnameauthentication.request.RealNameAuthRequest;
import lombok.SneakyThrows;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XmlUtils {

  /**
   * Java对象转XML字符串（去掉XML声明，编码UTF-8）
   * @param obj 待转换的Java对象（需带JAXB注解）
   * @return XML字符串
   */
  @SneakyThrows
  public static <T> String objToXml(T obj) {
    JAXBContext context = JAXBContext.newInstance(obj.getClass());
    Marshaller marshaller = context.createMarshaller();
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false); // 不格式化XML（避免空格问题）
    marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // 去掉<?xml version="1.0" encoding="UTF-8"?>声明
    marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8"); // 编码UTF-8

    StringWriter writer = new StringWriter();
    marshaller.marshal(obj, writer);
    return writer.toString();
  }

  /**
   * XML字符串转Java对象
   * @param xml XML字符串
   * @param clazz 目标Java类
   * @return 转换后的Java对象
   * @throws JAXBException XML转换异常
   */
  @SneakyThrows
  public static <T> T xmlToObj(String xml, Class<T> clazz) {
    JAXBContext context = JAXBContext.newInstance(clazz);
    Unmarshaller unmarshaller = context.createUnmarshaller();
    StringReader reader = new StringReader(xml);
    return clazz.cast(unmarshaller.unmarshal(reader));
  }

  public static void main(String[] args) {
    // 示例：Java对象转XML字符串
    RealNameAuthRequest request = new RealNameAuthRequest();
    request.setVersion("1.0");
    request.setMchId("1230000109");
    request.setNonceStr("123");
    request.setSign("123");
    request.setAppid("wxd678efh567hg6787");
    request.setOpenid("oUpF8uMuAJO_M2pxb1Q9zNjWeS6o");
    request.setRealName("刘某某");
    request.setCredType(1);
    // ... 设置其他字段值

    String xmlStr = objToXml(request);
    System.out.println(xmlStr);

    RealNameAuthRequest realNameAuthRequest = xmlToObj(xmlStr, RealNameAuthRequest.class);
    System.out.println(realNameAuthRequest);
  }
}
