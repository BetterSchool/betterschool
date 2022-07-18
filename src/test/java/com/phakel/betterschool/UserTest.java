package com.phakel.betterschool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phakel.betterschool.controller.UserController;
import com.phakel.betterschool.entity.User;
import com.phakel.betterschool.form.RegisterForm;
import com.phakel.betterschool.service.CommonService;
import com.phakel.betterschool.service.UserService;
import com.phakel.betterschool.util.I18nUtil;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Locale;

/**
 * @author EvanLuo42
 * @date 7/18/22 2:16 PM
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    private MockMvc mockMvc;

    @Mock
    UserService userService;

    @Mock
    CommonService commonService;

    @Mock
    I18nUtil i18nUtil;

    @InjectMocks
    UserController userController;


    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }

    @Test
    public void registerTest() throws Exception {
        RegisterForm form = new RegisterForm();
        form.setUserType(User.UserType.STUDENT);
        form.setPassword("test-password");
        form.setUsername("test-username");

        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/user/register")
                .content(new ObjectMapper().writeValueAsString(form))
                .contentType(MediaType.APPLICATION_JSON)
                .locale(Locale.CHINA);
        MockHttpServletResponse response = this.mockMvc.perform(requestBuilder)
                .andReturn()
                .getResponse();

        Result result = new Result();
        result.setStatus(Result.Status.SUCCESS);
        result.setMessage("注册成功");
        result.setData(userService.findUserByUsername("test-username").orElseThrow());

        Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assert.assertEquals(response.getContentAsString(), new ObjectMapper().writeValueAsString(result));
    }
}
