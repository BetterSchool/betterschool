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
    public static MockHttpServletRequestBuilder buildPostRequestWithBody(Object form, String path) throws JsonProcessingException {
        return buildPostRequest(path)
                .content(new ObjectMapper().writeValueAsString(form));
    }

    public static MockHttpServletRequestBuilder buildPostRequestWithUrlParam(String path, String paramName, String paramValue) {
        return buildPostRequest(path)
                .param(paramName, paramValue);
    }

    public static MockHttpServletRequestBuilder buildPostRequest(String path) {
        return MockMvcRequestBuilders
                .post(path)
                .contentType(MediaType.APPLICATION_JSON)
                .locale(Locale.CHINA);
    }

    public static MockHttpServletRequestBuilder buildGetRequestWithUrlParam(String path, String paramName, String paramValue) {
        return buildGetRequest(path)
                .param(paramName, paramValue);
    }

    public static MockHttpServletRequestBuilder buildGetRequestWithPathParam(String path, String param) {
        return MockMvcRequestBuilders
                .get(path, param)
                .contentType(MediaType.APPLICATION_JSON)
                .locale(Locale.CHINA);
    }

    public static MockHttpServletRequestBuilder buildGetRequest(String path) {
        return MockMvcRequestBuilders
                .get(path)
                .contentType(MediaType.APPLICATION_JSON)
                .locale(Locale.CHINA);
    }
}
