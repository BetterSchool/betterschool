package com.phakel.betterschool.controller;

import com.phakel.betterschool.Result;
import com.phakel.betterschool.entity.Class;
import com.phakel.betterschool.form.CreateClassForm;
import com.phakel.betterschool.service.ClassService;
import com.phakel.betterschool.service.CommonService;
import com.phakel.betterschool.util.I18nUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author EvanLuo42
 * @date 7/19/22 10:00 AM
 */
@RestController
public class ClassController {
    private final CommonService commonService;

    private final ClassService classService;

    private final I18nUtil i18nUtil;

    @Autowired
    public ClassController(CommonService commonService, ClassService classService, I18nUtil i18nUtil) {
        this.commonService = commonService;
        this.classService = classService;
        this.i18nUtil = i18nUtil;
    }

    @PostMapping(path = "/class/create")
    public ResponseEntity<Result> createClass(@RequestBody CreateClassForm form) {
        Result result = new Result();

        Class clazz = new Class();
        clazz.setClassName(form.getClassName());

        if (!classService.createClass(clazz)) {
            return result.conflict(i18nUtil.getMessage("class.create.conflict"));
        }

        return result.success(i18nUtil.getMessage("class.create.success"), null);
    }

    @GetMapping(path = "/class/id/{classId}/user/id/{userId}/add")
    public ResponseEntity<Result> addUserToClassByUserId(@PathVariable Long userId, @PathVariable Long classId) {
        Result result = new Result();
        if (classService.addUserToClassByUserIdAndClassId(userId, classId)) {
            return result.conflict(i18nUtil.getMessage("class.addUserToClassByUserId.conflict"));
        }

        return result.success(i18nUtil.getMessage("class.addUserToClassByUserId.success"), null);
    }
}
