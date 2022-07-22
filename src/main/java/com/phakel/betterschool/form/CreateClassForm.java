package com.phakel.betterschool.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author EvanLuo42
 * @date 7/22/22 2:35 PM
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class CreateClassForm extends Form {
    @NotEmpty(message = "form.class.name.required")
    @Size(min = 3, max = 20, message = "form.class.name.length")
    private String className;
}
