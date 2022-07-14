package com.phakel.betterschool.form;

import lombok.Data;

/**
 * @author EvanLuo42
 * @date 7/14/22 3:44 PM
 */
@Data
public class LoginForm extends Form {
    private String userName;
    private String password;
}
