package com.phakel.betterschool.controller;

import com.phakel.betterschool.service.ClassService;
import com.phakel.betterschool.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author EvanLuo42
 * @date 7/19/22 10:00 AM
 */
@RestController
public class ClassController {
    private final CommonService commonService;

    private final ClassService classService;

    @Autowired
    public ClassController(CommonService commonService, ClassService classService) {
        this.commonService = commonService;
        this.classService = classService;
    }
}
