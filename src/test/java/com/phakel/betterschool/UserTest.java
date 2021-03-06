package com.phakel.betterschool;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phakel.betterschool.controller.UserController;
import com.phakel.betterschool.dto.UserInfo;
import com.phakel.betterschool.entity.User;
import com.phakel.betterschool.form.LoginTestForm;
import com.phakel.betterschool.form.RegisterTestForm;
import com.phakel.betterschool.service.CommonService;
import com.phakel.betterschool.service.UserService;
import com.phakel.betterschool.util.I18nUtil;
import com.phakel.betterschool.util.RequestBuildUtil;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

/**
 * @author EvanLuo42
 * @date 7/18/22 2:16 PM
 */
@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private I18nUtil i18nUtil;

    @InjectMocks
    private UserController userController;


    @BeforeEach
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac)
                .build();
    }

    @Test
    @Order(1)
    public void registerTest() throws Exception {
        RegisterTestForm form = new RegisterTestForm();
        form.setUserType(User.UserType.STUDENT.name());
        form.setPassword("test-password");
        form.setUsername("test-username");

        MockHttpServletResponse response = mockMvc.perform(RequestBuildUtil.buildPostRequestWithBody(form, "/user/register"))
                .andReturn()
                .getResponse();

        Result result = new Result();
        result.setStatus(Result.Status.SUCCESS);
        result.setMessage("????????????");
        result.setData(userService.findUserByUsername("test-username").orElseThrow());

        Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assert.assertEquals(response.getContentAsString(StandardCharsets.UTF_8), new ObjectMapper().writeValueAsString(result));
    }

    @Test
    @Order(2)
    public void loginTest() throws Exception {
        LoginTestForm form = new LoginTestForm();
        form.setPassword("test-password");
        form.setUsername("test-username");

        MockHttpServletResponse response = mockMvc.perform(RequestBuildUtil.buildPostRequestWithBody(form, "/user/login"))
                .andReturn()
                .getResponse();

        Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assert.assertNotNull(new ObjectMapper().readValue(response.getContentAsString(StandardCharsets.UTF_8), Result.class).getData());
    }

    @Test
    @Order(3)
    public void findUserByUsernameTest() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(RequestBuildUtil.buildGetRequestWithPathParam("/user/name/{username}", "test-username"))
                .andReturn()
                .getResponse();

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUsername("test-username");
        userInfo.setUserType(User.UserType.STUDENT);

        Result result = new Result();
        result.setStatus(Result.Status.SUCCESS);
        result.setMessage("?????????????????????????????????");
        result.setData(userInfo);

        Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assert.assertEquals(response.getContentAsString(StandardCharsets.UTF_8), new ObjectMapper().writeValueAsString(result));
    }

    @Test
    @Order(4)
    public void findUserByUserIdTest() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(RequestBuildUtil.buildGetRequestWithPathParam("/user/id/{userId}", "1"))
                .andReturn()
                .getResponse();

        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(1L);
        userInfo.setUsername("test-username");
        userInfo.setUserType(User.UserType.STUDENT);

        Result result = new Result();
        result.setStatus(Result.Status.SUCCESS);
        result.setMessage("???????????? ID ??????????????????");
        result.setData(userInfo);

        Assert.assertEquals(response.getStatus(), HttpStatus.OK.value());
        Assert.assertEquals(response.getContentAsString(StandardCharsets.UTF_8), new ObjectMapper().writeValueAsString(result));
    }
}
