package com.phakel.betterschool.form;

import com.phakel.betterschool.entity.User;
import lombok.Data;

/**
 * @author EvanLuo42
 * @date 7/14/22 4:05 PM
 */
@Data
public class RegisterForm extends Form {
    private String userName;
    private String password;
    private User.UserType userType;
}
