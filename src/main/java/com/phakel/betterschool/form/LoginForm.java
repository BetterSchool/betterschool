package com.phakel.betterschool.form;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.*;

/**
 * @author EvanLuo42
 * @date 7/14/22 3:44 PM
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class LoginForm extends Form {
    @NotEmpty(message = "form.user.name.required")
    @Size(min = 3, max = 20, message = "form.user.name.length")
    private String username;

    @NotEmpty(message = "form.user.password.required")
    private String password;
}
