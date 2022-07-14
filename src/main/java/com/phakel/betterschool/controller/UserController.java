package com.phakel.betterschool.controller;

import com.phakel.betterschool.Result;
import com.phakel.betterschool.dto.TokenDetail;
import com.phakel.betterschool.dto.UserInfo;
import com.phakel.betterschool.entity.User;
import com.phakel.betterschool.form.FieldError;
import com.phakel.betterschool.form.LoginForm;
import com.phakel.betterschool.form.RegisterForm;
import com.phakel.betterschool.service.CommonService;
import com.phakel.betterschool.service.UserService;
import com.phakel.betterschool.util.I18nUtil;
import com.phakel.betterschool.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * @author EvanLuo42
 * @date 7/13/22 7:50 PM
 */
@RestController
public class UserController {
    private final UserService userService;
    private final CommonService commonService;
    private final I18nUtil i18nUtil;
    private TokenUtil tokenUtil;

    @Autowired
    public UserController(UserService userService, CommonService commonService, I18nUtil i18nUtil, TokenUtil tokenUtil, TokenUtil tokenUtil1) {
        this.userService = userService;
        this.commonService = commonService;
        this.i18nUtil = i18nUtil;
        this.tokenUtil = tokenUtil1;
    }

    @PostMapping(path = "/user/login")
    public ResponseEntity<Result> login(@RequestBody LoginForm form) {
        Result result = new Result();
        List<FieldError> formValidate = commonService.validateForm(form);

        if (!formValidate.isEmpty()) {
            return result.formError(i18nUtil.getMessage("user.login.formError"), formValidate);
        }

        if (!userService.validateLogin(form.getUserName(), form.getPassword())) {
            return result.unAuthorized(i18nUtil.getMessage("user.login.authorizedFailed"));
        }

        return userService.findUserByUserName(form.getUserName())
                .map(it -> {
                    TokenDetail tokenDetail = new TokenDetail();
                    tokenDetail.setUserName(it.getUserName());
                    tokenDetail.setUserType(it.getUserType());
                    return result.success(i18nUtil.getMessage("user.login.success"),
                            tokenUtil.generateToken(tokenDetail));
                })
                .orElseGet(() -> result.notFound(i18nUtil.getMessage("user.login.userNotFound")));
    }

    @PostMapping(path = "/user/register")
    public ResponseEntity<Result> register(@RequestBody RegisterForm form) {
        Result result = new Result();
        List<FieldError> formValidate = commonService.validateForm(form);

        if (!formValidate.isEmpty()) {
            return result.formError(i18nUtil.getMessage("user.register.formError"), formValidate);
        }

        User user = new User();
        user.setUserName(form.getUserName());
        user.setUserType(form.getUserType());
        user.setPassword(BCrypt.hashpw(form.getPassword(), BCrypt.gensalt()));

        if (!userService.createUser(user)) {
            return result.conflict(i18nUtil.getMessage("user.register.userExist"));
        }

        return result.success(i18nUtil.getMessage("player.registerSuccess"),
                Optional.of(user).map(UserInfo::new).get());
    }

    @GetMapping(path = "/users/school/id/{schoolId}")
    public ResponseEntity<Result> getUsersBySchoolId(@PathVariable Long schoolId) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersBySchoolId.success"),
                userService.findUsersBySchoolId(schoolId));
    }

    @GetMapping(path = "/users/school/name/{schoolName}")
    public ResponseEntity<Result> getUsersBySchoolName(@PathVariable String schoolName) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersBySchoolName.success"),
                userService.findUsersBySchoolName(schoolName));
    }

    @GetMapping(path = "/users/class/id/{classId}")
    public ResponseEntity<Result> getUsersByClassId(@PathVariable Long classId) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersByClassId.success"),
                userService.findUsersByClassId(classId));
    }

    @GetMapping(path = "/user/id/{userId}")
    public ResponseEntity<Result> getUserByUserId(@PathVariable Long userId) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersByUserId.success"),
                userService.findUserByUserId(userId));
    }

    @GetMapping(path = "/user/name/{userName}")
    public ResponseEntity<Result> getUserByUserName(@PathVariable String userName) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersByUserName.success"),
                userService.findUserByUserName(userName));
    }
}
