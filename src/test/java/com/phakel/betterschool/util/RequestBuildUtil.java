package com.phakel.betterschool.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Locale;

/**
 * @author EvanLuo42
 * @date 7/18/22 9:57 PM
 */
public class RequestBuildUtil {
    public static MockHttpServletRequestBuilder buildRequestWithBody(Object form, String path) throws JsonProcessingException {
        return buildRequest(path)
                .content(new ObjectMapper().writeValueAsString(form));
    }

    public static MockHttpServletRequestBuilder buildRequestWithUrlParams(String path, String paramName, String paramValue) {
        return buildRequest(path)
                .param(paramName, paramValue);
    }

    public static MockHttpServletRequestBuilder buildRequest(String path) {
        return MockMvcRequestBuilders
                .post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .locale(Locale.CHINA);
    }
}
