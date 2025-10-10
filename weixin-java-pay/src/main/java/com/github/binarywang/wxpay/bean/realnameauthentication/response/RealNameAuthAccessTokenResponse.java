package com.github.binarywang.wxpay.bean.realnameauthentication.response;

import lombok.Data;

import java.io.Serializable;

/**
 * {
 * "retcode":0,
 * "retmsg":"ok",
 * "access_token":"Ca5sECXTwzkWYXs_do9ZaEeueqVHOtF-nXr51yNll2O97zqk9niwJnmSWxhDJELqoDDVFws6LCBbSulnEAaxCg==",
 * "access_token_expire_in":7200,
 * "refresh_token":"Q2eOMW_fnMX4U18SAGr2CuONaNgE3qYRP8eJUHKjFjz65ddh7DGb2koRG5ij-rlHohDmyEpz1wKSH9jPiAAJzg==",
 * "refresh_token_expire_in":2592000
 * }
 * @author lipeng 2025/10/3
 */
@Data
public class RealNameAuthAccessTokenResponse implements Serializable {
  private static final long serialVersionUID = 1L;
  /**
   * 0-成功
   */
  private Integer retcode;
  /**
   * 处理成功，返回ok,其他情况返回 具体错误码
   */
  private String retmsg;
  /**
   * 接口调用凭证
   */
  private String access_token;
  /**
   * 请求返回的access_token过期时间，以秒为单位，有效期较短
   */
  private String access_token_expire_in;
  /**
   * refresh令牌
   */
  private String refresh_token;
  /**
   * refresh_token过期时间，以秒为单位，有效期较长
   */
  private String refresh_token_expire_in;
}
