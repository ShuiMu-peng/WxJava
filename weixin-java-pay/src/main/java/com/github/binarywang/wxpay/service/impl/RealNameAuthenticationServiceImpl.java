package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.realnameauthentication.request.AuthCodeUrlRequest;
import com.github.binarywang.wxpay.bean.realnameauthentication.request.RealNameAuthParam;
import com.github.binarywang.wxpay.bean.realnameauthentication.request.RealNameAuthRequest;
import com.github.binarywang.wxpay.bean.realnameauthentication.response.RealNameAuthAccessTokenResponse;
import com.github.binarywang.wxpay.bean.realnameauthentication.response.RealNameAuthResponse;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.service.RealNameAuthenticationService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.util.SignUtils;
import com.github.binarywang.wxpay.util.XmlUtils;
import com.google.common.base.Joiner;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.util.RandomUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 实名认证服务类
 * @author lipeng 2025/9/28
 */
@Slf4j
@RequiredArgsConstructor
public class RealNameAuthenticationServiceImpl implements RealNameAuthenticationService {
  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public AuthCodeUrlRequest getAuthCodeUrlRequest(String openId) {
    WxPayConfig config = payService.getConfig();
    AuthCodeUrlRequest request = new AuthCodeUrlRequest();
    request.setAppid(config.getAppId());
    request.setMchId(config.getMchId());
    request.setScope("pay_identity");
    request.setResponseType("code");
    request.setOpenid(openId);
    request.setNonceStr(RandomUtils.getRandomStr());
    request.setSignType("HMAC-SHA256");

    @SuppressWarnings("unchecked")
    String sign = SignUtils.createSign(GSON.fromJson(GSON.toJson(request), Map.class),
      WxPayConstants.SignType.HMAC_SHA256,
      config.getMchKey(),
      null);
    request.setSign(sign);
    return request;
  }

  @SneakyThrows
  @Override
  public RealNameAuthAccessTokenResponse getAccessToken(String openId, String code) {
    WxPayConfig config = payService.getConfig();
    Map<String, String> params = new HashMap<>();
    params.put("mch_id", config.getMchId());
    params.put("appid", config.getAppId());
    params.put("openid", openId);
    params.put("code", code);
    params.put("scope", "pay_identity");
    params.put("grant_type", "authorization_code");
    params.put("sign_type", WxPayConstants.SignType.HMAC_SHA256);
    String sign = SignUtils.createSign(params, WxPayConstants.SignType.HMAC_SHA256, config.getMchKey(), null);
    params.put("sign", sign);
    String paramStr = Joiner.on("&").withKeyValueSeparator("=").join(params);
    String url = payService.getPayBaseUrl() + "/appauth/getaccesstoken?" + paramStr;
    String result = payService.getV3(url);
    return GSON.fromJson(result, RealNameAuthAccessTokenResponse.class);
  }

  @SneakyThrows
  @Override
  public RealNameAuthResponse auth(RealNameAuthParam param) {
    WxPayConfig config = payService.getConfig();
    RealNameAuthRequest request = new RealNameAuthRequest();
    request.setOpenid(param.getOpenid());
    request.setRealName(param.getRealName());
    request.setCredType(param.getCredType());
    request.setCredId(param.getCredId());
    request.setAccessToken(param.getAccessToken());
    request.setSignType(WxPayConstants.SignType.HMAC_SHA256);
    request.setMchId(config.getMchId());
    request.setAppid(config.getAppId());
    request.setNonceStr(RandomUtils.getRandomStr());
    Map<String, String> paramMap = requestToMap(request);
    String sign = SignUtils.createSign(paramMap, WxPayConstants.SignType.HMAC_SHA256, payService.getConfig().getMchKey(), null);
    request.setSign(sign);

    String paramStr = XmlUtils.objToXml(request);
    String url = payService.getPayBaseUrl() + "/secsvc/realnameauth";
    String result = payService.post(url, paramStr, false);
    return XmlUtils.xmlToObj(result, RealNameAuthResponse.class);
  }

  public static Map<String, String> requestToMap(RealNameAuthRequest request) {
    Map<String, String> map = new HashMap<>();
    map.put("version", request.getVersion());
    map.put("mch_id", request.getMchId());
    map.put("appid", request.getAppid());
    map.put("openid", request.getOpenid());
    map.put("real_name", request.getRealName());
    map.put("cred_type", request.getCredType() != null ? request.getCredType().toString() : null);
    map.put("cred_id", request.getCredId());
    map.put("nonce_str", request.getNonceStr());
    map.put("access_token", request.getAccessToken());
    map.put("sign_type", request.getSignType());
    return map;
  }
}
