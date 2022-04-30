package com.gssg.gssgbe.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

@Component
public class TestUtil {

    private static ObjectMapper objectMapper;

    private TestUtil(final ObjectMapper objectMapper) {
        TestUtil.objectMapper = objectMapper;
    }

    public static <T> List<T> mvcResultToList(final MvcResult result, final Class<T> valueType)
        throws Exception {
        return objectMapper.readValue(result.getResponse().getContentAsString(),
            objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }

    public static <T> T mvcResultToObject(final MvcResult result, final Class<T> valueType)
        throws Exception {
        return objectMapper.readValue(result.getResponse().getContentAsString(), valueType);
    }
}
