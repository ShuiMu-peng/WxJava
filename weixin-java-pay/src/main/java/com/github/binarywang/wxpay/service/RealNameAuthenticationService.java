package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.realnameauthentication.request.AuthCodeUrlRequest;
import com.github.binarywang.wxpay.bean.realnameauthentication.request.RealNameAuthParam;
import com.github.binarywang.wxpay.bean.realnameauthentication.response.RealNameAuthAccessTokenResponse;
import com.github.binarywang.wxpay.bean.realnameauthentication.response.RealNameAuthResponse;

/**
 *
 * 实名认证接口
 * <a href="https://pay.weixin.qq.com/doc/v2/merchant/4011987263">参考文档</a>
 * @author shuimu
 * @since 2025-09-28
 */
public interface RealNameAuthenticationService {
  /**
   * 获取实名认证授权码参数
   * @param openId openId
   * @return AuthCodeUrlRequest
   */
  AuthCodeUrlRequest getAuthCodeUrlRequest(String openId);

  /**
   * 获取 access_token
   * @param openId openId
   * @param code auth_code
   * @return RealNameAuthAccessTokenResponse
   */
  RealNameAuthAccessTokenResponse getAccessToken(String openId, String code);

  /**
   * 实名认证
   * @param request 请求参数
   */
  RealNameAuthResponse auth(RealNameAuthParam request);

}
