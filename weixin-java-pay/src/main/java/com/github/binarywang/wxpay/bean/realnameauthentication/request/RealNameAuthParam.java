package com.github.binarywang.wxpay.bean.realnameauthentication.request;

import lombok.Data;

/**
 * @author lipeng 2025/10/4
 */
@Data
public class RealNameAuthParam {
  /**
   * openid
   */
  private String openid;
  /**
   * 真实姓名
   */
  private String realName;
  /**
   * 证件类型（文档要求必填，int类型，枚举值如下）：
   * 1-身份证，2-护照，5-回乡证，9-台胞证，12-外国人居留证，17-港澳居民居住证，18-台湾居民居住证
   * 示例值：1
   */
  private Integer credType;
  /**
   * 证件id
   */
  private String credId;
  /**
   * token
   */
  private String accessToken;
}
