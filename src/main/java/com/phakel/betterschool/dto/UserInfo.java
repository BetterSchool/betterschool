package com.phakel.betterschool.dto;

import com.phakel.betterschool.entity.User;
import lombok.Data;

import java.util.List;

/**
 * @author EvanLuo42
 * @date 7/13/22 6:40 PM
 */
@Data
public class UserInfo {
    private Long userId;
    private String userName;
    private String description;
    private User.UserType userType;

    public UserInfo(User user) {
        this.userId = user.getUserId();
        this.userName = user.getUserName();
        this.userType = user.getUserType();
        this.description = user.getDescription();
    }
}
