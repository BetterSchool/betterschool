package com.phakel.betterschool.service;

import com.phakel.betterschool.form.FieldError;
import com.phakel.betterschool.form.Form;

import java.util.List;

/**
 * @author EvanLuo42
 * @date 7/13/22 7:44 PM
 */
public interface CommonService {
    List<FieldError> validateForm(Form form);
}
