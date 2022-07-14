package com.phakel.betterschool.dto;

import com.phakel.betterschool.entity.User;
import lombok.Data;

/**
 * @author EvanLuo42
 * @date 7/13/22 6:40 PM
 */
@Data
public class UserInfo {
    private Long userId;
    private String username;
    private String description;
    private User.UserType userType;

    public UserInfo(User user) {
        this.userId = user.getUserId();
        this.username = user.getUsername();
        this.userType = user.getUserType();
        this.description = user.getDescription();
    }
}
