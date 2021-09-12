package com.gssg.gssgbe.util;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TestUtil {

	private static ObjectMapper objectMapper;

	private TestUtil(ObjectMapper objectMapper) {
		TestUtil.objectMapper = objectMapper;
	}

	public static <T> List<T> mvcResultToList(MvcResult result, Class<T> valueType) throws Exception {
		return objectMapper.readValue(result.getResponse().getContentAsString(),
			objectMapper.getTypeFactory().constructCollectionType(List.class, valueType));
	}

	public static <T> T mvcResultToObject(MvcResult result, Class<T> valueType) throws Exception {
		return objectMapper.readValue(result.getResponse().getContentAsString(), valueType);
	}
}
