package com.phakel.betterschool.form;

import com.phakel.betterschool.util.I18nUtil;
import lombok.Data;

import javax.validation.ConstraintViolation;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author EvanLuo42
 * @date 7/13/22 10:28 AM
 */
@Data
public class FieldError {
    private static I18nUtil i18nUtil = new I18nUtil();

    private String name;

    private String message;

    public FieldError(String name, String message) {
        this.name = name;
        this.message = message;
    }

    private static FieldError of(ConstraintViolation<? extends Form> constraintViolation) {
        return new FieldError(
                constraintViolation.getPropertyPath().toString(),
                i18nUtil.getMessage(constraintViolation.getMessage()));
    }

    public static List<FieldError> getErrors(Set<ConstraintViolation<Form>> constraintViolations) {

        return constraintViolations.stream()
                .map(FieldError::of)
                .collect(Collectors.toList());
    }
}
