package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.realnameauthentication.request.AuthCodeUrlRequest;
import com.github.binarywang.wxpay.bean.realnameauthentication.response.RealNameAuthAccessTokenResponse;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.RealNameAuthenticationService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * @author lipeng 2025/10/3
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class RealNameAuthServiceImplTest {
  @Inject
  private WxPayService wxPayService;

  @Test
  public void testGetAuthCodeUrl() throws WxPayException {
    RealNameAuthenticationService realNameAuthenticationService = wxPayService.getRealNameAuthenticationService();
    AuthCodeUrlRequest request = realNameAuthenticationService.getAuthCodeUrlRequest("openId");
    log.info("request: {}", request);
  }

  @Test
  public void testGetRealName() {
    RealNameAuthenticationService realNameAuthenticationService = wxPayService.getRealNameAuthenticationService();
    RealNameAuthAccessTokenResponse response = realNameAuthenticationService.getAccessToken("123", "code");
    log.info("response: {}", response);
  }
}
