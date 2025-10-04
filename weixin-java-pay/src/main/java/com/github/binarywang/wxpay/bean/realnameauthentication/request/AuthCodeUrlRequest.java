package com.github.binarywang.wxpay.bean.realnameauthentication.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 获取authCode需要的参数
 * <pre>
 * {
 * 	"api_version" : "1.0",
 * 	"mch_id" : "1230000109",
 * 	"appid" : "wx88736d7d39e2eda6",
 * 	"scope" : "pay_identity",
 * 	"response_type" : "code",
 * 	"openid" : "oUpF8uMuAJO_M2pxb1Q9zNjWeS6o",
 * 	"sign_type" : "HMAC-SHA256",
 * 	"sign" : "DBB47C037C812B29E0E7B4C5F62972B92E61CF05DE14FA958D9054B2",
 * 	"nonce_str" : "190703203728315382"
 *  }
 * </pre>
 * @author lipeng 2025/9/28
 */
@Data
public class AuthCodeUrlRequest implements Serializable {
  private String apiVersion = "1.0";
  private String mchId;
  private String appid;
  private String scope;
  private String responseType;
  private String openid;
  private String signType;
  private String sign;
  private String nonceStr;
}
