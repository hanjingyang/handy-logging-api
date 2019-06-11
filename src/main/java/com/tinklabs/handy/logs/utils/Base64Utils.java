package com.tinklabs.handy.logs.utils;

import java.util.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

public class Base64Utils {

  private static final Logger logger = LoggerFactory.getLogger(Base64Utils.class);

  public static final String DEFAULT_CHARSET_NAME = "UTF-8";

  public static String encodeWithCharsetName(String text, String charsetName) {
    String encodedText = null;
    if (StringUtils.isEmpty(text)) {
      return null;
    }
    try {
      Base64.Encoder encoder = Base64.getEncoder();
      byte[] textByte = text.getBytes(charsetName);
      encodedText = encoder.encodeToString(textByte);
    } catch (Exception e) {
      logger.error("base64 encodeToString error", e);
    }
    return encodedText;
  }

  public static String encode(String text) {
    return encodeWithCharsetName(text, DEFAULT_CHARSET_NAME);
  }
}
