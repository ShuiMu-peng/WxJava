package com.github.binarywang.wxpay.bean.realnameauthentication.response;

import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 微信支付实名验证第三步 - 响应实体类
 * 对应文档：https://pay.weixin.qq.com/doc/v2/merchant/4011987263#3%E3%80%81%E5%BE%AE%E4%BF%A1%E6%94%AF%E4%BB%98%E5%AE%9E%E5%90%8D%E9%AA%8C%E8%AF%81
 * 返回格式：XML（根节点为xml）
 */
@Data
@XmlRootElement(name = "xml")
@XmlAccessorType(XmlAccessType.FIELD)
public class RealNameAuthResponse {

  // -------------------------- 1. 通信层参数（必返回）--------------------------
  /**
   * 通信状态码（文档要求必返回，SUCCESS=通信成功，FAIL=通信失败）
   * 注意：此参数仅表示“请求是否到达微信服务器”，不代表业务成功
   */
  @XmlElement(name = "return_code", required = true)
  private String returnCode;

  /**
   * 通信错误信息（文档要求非必返回，仅当return_code=FAIL时非空，描述错误原因）
   * 示例值：签名失败、参数格式校验错误
   */
  @XmlElement(name = "return_msg")
  private String returnMsg;

  // -------------------------- 2. 业务层参数（仅当return_code=SUCCESS时返回）--------------------------
  /**
   * 业务结果码（文档要求必返回，SUCCESS=业务成功，FAIL=业务失败）
   * 需结合此参数判断实名验证业务是否执行成功
   */
  @XmlElement(name = "result_code")
  private String resultCode;

  /**
   * 业务错误码（文档要求非必返回，仅当result_code=FAIL时非空，对应文档“错误码”列表）
   * 示例值：INVALID_SIGN_TYPE（签名类型错误）、ACCESS_TOKEN_EXPIRE（access_token已过期）
   */
  @XmlElement(name = "err_code")
  private String errCode;

  /**
   * 业务错误描述（文档要求非必返回，仅当result_code=FAIL时非空，解释err_code的含义）
   * 示例值：签名类型错误
   */
  @XmlElement(name = "err_code_desc")
  private String errCodeDesc;

  /**
   * 应用唯一标识（文档要求必返回，与请求的appid一致）
   * 示例值：wx2421b1c4370ec43b
   */
  @XmlElement(name = "appid")
  private String appid;

  /**
   * 微信支付商户号（文档要求必返回，与请求的mchId一致）
   * 示例值：10000100
   */
  @XmlElement(name = "mch_id")
  private String mchId;

  /**
   * 随机字符串（文档要求必返回，与请求的nonceStr对应，长度32位以内）
   * 示例值：IITRi8Iabbblz1Jc
   */
  @XmlElement(name = "nonce_str")
  private String nonceStr;

  /**
   * 签名值（文档要求必返回，微信服务器生成的签名，用于校验返回数据完整性）
   * 示例值：7921E432F65EB8ED0CE9755F0E86D72F
   */
  @XmlElement(name = "sign")
  private String sign;

  // -------------------------- 3. 实名结果参数（仅当return_code=SUCCESS且result_code=SUCCESS时返回）--------------------------
  /**
   * 微信用户唯一标识（文档要求必返回，与请求的openid一致）
   * 示例值：oUpF8uMuAJO_M2pxb1Q9zNjWeS6o
   */
  @XmlElement(name = "openid")
  private String openid;

  /**
   * 接口调用凭证（文档要求必返回，与请求的accessToken一致）
   */
  @XmlElement(name = "access_token")
  private String accessToken;

  /**
   * 用户与姓名匹配结果（文档要求非必返回，多个结果用分号“;”连接，枚举值如下）：
   * V_OP_NA：用户暂未实名认证
   * V_OP_NM_MA：用户与姓名匹配
   * V_OP_NM_UM：用户与姓名不匹配
   * 示例值：V_OP_NM_MA
   */
  @XmlElement(name = "verify_openid")
  private String verifyOpenid;

  /**
   * 姓名与证件号匹配结果（文档要求非必返回，仅当verifyOpenid=V_OP_NM_MA时返回，枚举值如下）：
   * V_NM_ID_MA：姓名与证件号匹配
   * V_NM_ID_UM：姓名与证件号不匹配
   * V_NM_ID_TYPE_UM：证件类型不匹配
   * 示例值：V_NM_ID_MA
   */
  @XmlElement(name = "verify_real_name")
  private String verifyRealName;

  /**
   * 实名认证是否绑定银行卡（文档要求非必返回，仅当verifyRealName=V_NM_ID_MA且version≥1.1时返回）：
   * YES：绑定银行卡的实名认证
   * NO：非绑定银行卡的实名认证
   */
  @XmlElement(name = "bind_bankcard")
  private String bindBankcard;
}
