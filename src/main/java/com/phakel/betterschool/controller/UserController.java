package com.phakel.betterschool.controller;

import com.phakel.betterschool.Result;
import com.phakel.betterschool.service.UserService;
import com.phakel.betterschool.util.I18nUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EvanLuo42
 * @date 7/13/22 7:50 PM
 */
@RestController
public class UserController {
    private UserService userService;
    private final I18nUtil i18nUtil;

    @Autowired
    public UserController(UserService userService, I18nUtil i18nUtil) {
        this.userService = userService;
        this.i18nUtil = i18nUtil;
    }

    @GetMapping(path = "/users/school/{schoolId}")
    public ResponseEntity<Result> getUsersBySchoolId(@PathVariable Long schoolId) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersBySchoolId.success"),
                userService.findUsersBySchoolId(schoolId));
    }

    @GetMapping(path = "/users/school/{schoolName}")
    public ResponseEntity<Result> getUsersBySchoolName(@PathVariable String schoolName) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersBySchoolName.success"),
                userService.findUsersBySchoolName(schoolName));
    }

    @GetMapping(path = "/users/class/{classId}")
    public ResponseEntity<Result> getUsersByClassId(@PathVariable Long classId) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersByClassId.success"),
                userService.findUsersByClassId(classId));
    }

    @GetMapping(path = "/user/{userId}")
    public ResponseEntity<Result> getUserByUserId(@PathVariable Long userId) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersByUserId.success"),
                userService.findUserByUserId(userId));
    }

    @GetMapping(path = "/user/{userName}")
    public ResponseEntity<Result> getUserByUserName(@PathVariable String userName) {
        Result result = new Result();
        return result.success(i18nUtil.getMessage("user.getUsersByUserName.success"),
                userService.findUserByUserName(userName));
    }
}
