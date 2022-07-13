package com.phakel.betterschool.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author EvanLuo42
 * @date 7/13/22 10:55 AM
 */
@Entity
@Table(name = "class")
@Getter
@Setter
public class Clazz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "class_id", nullable = false)
    private Long classId;

    @Column(name = "class_name", nullable = false)
    private String className;

    @ManyToOne(cascade = CascadeType.ALL)
    private School school;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<User> users;
}
