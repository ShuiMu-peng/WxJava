package com.github.binarywang.wxpay.bean.realnameauthentication.request;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信支付实名验证第三步 - 请求实体类
 * 对应文档：https://pay.weixin.qq.com/doc/v2/merchant/4011987263#3%E3%80%81%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98%E5%AE%9E%E5%90%8D%E9%AA%8C%E8%AF%81
 * 请求地址：https://fraud.mch.weixin.qq.com/secsvc/realnameauth（POST + HTTPS）
 */
@Data
@XmlRootElement(name = "xml") // 根节点为XML（符合微信支付参数格式要求）
@XmlAccessorType(XmlAccessType.FIELD) // 按字段解析XML
public class RealNameAuthRequest {

  /**
   * 接口版本号，固定值"1.0"（文档要求必填）
   */
  @XmlElement(name = "version", required = true)
  private String version = "1.0";

  /**
   * 微信支付分配的商户号（文档要求必填，长度32位以内）
   * 示例值：1230000109
   */
  @XmlElement(name = "mch_id", required = true)
  private String mchId;

  /**
   * 发起请求的小程序/应用的appid（文档要求必填，长度32位以内）
   * 示例值：wxd678efh567hg6787
   */
  @XmlElement(name = "appid", required = true)
  private String appid;

  /**
   * 微信用户在当前商户appid下的唯一标识（文档要求必填，长度128位以内）
   * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   */
  @XmlElement(name = "openid", required = true)
  private String openid;

  /**
   * 用户真实姓名，需使用UTF-8编码（文档要求必填，长度64位以内）
   * 示例值：刘某某
   */
  @XmlElement(name = "real_name", required = true)
  private String realName;

  /**
   * 证件类型（文档要求必填，int类型，枚举值如下）：
   * 1-身份证，2-护照，5-回乡证，9-台胞证，12-外国人居留证，17-港澳居民居住证，18-台湾居民居住证
   * 示例值：1
   */
  @XmlElement(name = "cred_type", required = true)
  private Integer credType;

  /**
   * 证件号码（文档要求必填，长度32位以内，需与证件类型匹配）
   * 示例值：440000000000000000
   */
  @XmlElement(name = "cred_id", required = true)
  private String credId;

  /**
   * 随机字符串，长度32位以内（文档要求必填，推荐随机数生成算法）
   * 示例值：ec2316275641faa3aacf3cc599e8730f
   */
  @XmlElement(name = "nonce_str", required = true)
  private String nonceStr;

  /**
   * 接口调用凭证（文档要求必填，长度128位以内，来自第二步“通过code换取accesstoken”的返回值）
   */
  @XmlElement(name = "access_token", required = true)
  private String accessToken;

  /**
   * 签名值（文档要求必填，长度128位以内，通过HMAC-SHA256或MD5算法计算，详见文档“签名生成算法”）
   * 示例值：0CB01533B8C1EF103065174F50BCA001
   */
  @XmlElement(name = "sign", required = true)
  private String sign;

  /**
   * 签名类型（文档要求必填，支持HMAC-SHA256或MD5）
   * 示例值：HMAC-SHA256
   */
  @XmlElement(name = "sign_type", required = true)
  private String signType;
}
