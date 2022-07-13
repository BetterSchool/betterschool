package com.phakel.betterschool.service.impl;

import com.phakel.betterschool.form.FieldError;
import com.phakel.betterschool.form.Form;
import com.phakel.betterschool.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Validator;
import java.util.List;

/**
 * @author EvanLuo42
 * @date 7/13/22 7:44 PM
 */
@Service
public class CommonServiceImpl implements CommonService {
    private final Validator validator;

    @Autowired
    public CommonServiceImpl(Validator validator) {
        this.validator = validator;
    }

    @Override
    public List<FieldError> validateForm(Form form) {
        return FieldError.getErrors(validator.validate(form));
    }
}
