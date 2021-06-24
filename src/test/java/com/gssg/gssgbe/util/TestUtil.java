package com.gssg.gssgbe.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gssg.gssgbe.common.token.JwtAuthTokenProvider;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

@Component
public class TestUtil {

  private static ObjectMapper objectMapper;
  public static JwtAuthTokenProvider jwtAuthTokenProvider;

  private TestUtil(ObjectMapper objectMapper, JwtAuthTokenProvider jwtAuthTokenProvider) {
    TestUtil.objectMapper = objectMapper;
    TestUtil.jwtAuthTokenProvider = jwtAuthTokenProvider;
  }

  public static <T> List<T> mvcResultToList(MvcResult result, Class<T> valueType) throws Exception {
    return objectMapper.readValue(result.getResponse().getContentAsString(),
        objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
  }

  public static <T> T mvcResultToObject(MvcResult result, Class<T> valueType) throws Exception {
    return objectMapper.readValue(result.getResponse().getContentAsString(), valueType);
  }
}
