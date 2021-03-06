package com.phakel.betterschool.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author EvanLuo42
 * @date 7/13/22 10:44 AM
 */
@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    public enum UserType {
        TEACHER,
        STUDENT,
        PARENTS
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    private UserType userType = UserType.STUDENT;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Clazz> classes;
}
