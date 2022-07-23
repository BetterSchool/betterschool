package com.phakel.betterschool.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * @author EvanLuo42
 * @date 7/13/22 11:00 AM
 */
@Entity
@Table(name = "homework")
@Getter
@Setter
public class Homework {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "homework_id", nullable = false)
    private Long homeworkId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = User.class)
    @JoinColumn(name = "author_id", referencedColumnName = "user_id")
    private User author;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Class.class)
    @JoinColumn(name = "class_id", referencedColumnName = "class_id")
    private Class clazz;

    @Temporal(TemporalType.DATE)
    private Date beginDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;
}
