package com.phakel.betterschool.form;

import com.phakel.betterschool.entity.User;
import lombok.Data;

import javax.validation.constraints.*;

/**
 * @author EvanLuo42
 * @date 7/14/22 4:05 PM
 */
@Data
public class RegisterForm extends Form {
    @NotEmpty(message = "form.user.name.required")
    @Size(min = 3, max = 20, message = "form.user.name.length")
    private String username;

    @NotEmpty(message = "form.user.password.required")
    private String password;

    @Pattern(regexp = "STUDENT|TEACHER|PARENTS", message = "form.user.usertype.notExisted")
    private String userType;

    public User.UserType getUserType() {
        return User.UserType.valueOf(this.userType);
    }

    public void setUserType(User.UserType userType) {
        this.userType = String.valueOf(userType);
    }
}
