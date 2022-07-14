package com.phakel.betterschool.dto;

import com.phakel.betterschool.entity.User;
import lombok.Data;

/**
 * @author EvanLuo42
 * @date 7/13/22 10:34 AM
 */
@Data
public class TokenDetail {
    private String username;
    private User.UserType userType;
}