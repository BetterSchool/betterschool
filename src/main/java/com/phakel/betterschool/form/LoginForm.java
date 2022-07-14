package com.phakel.betterschool.form;

import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author EvanLuo42
 * @date 7/14/22 3:44 PM
 */
@Data
public class LoginForm extends Form {
    @NotNull(message = "form.user.name.required")
    @Size(min = 3, max = 20, message = "form.user.name.length")
    private String userName;

    @NotNull(message = "form.user.password.required")
    private String password;
}
