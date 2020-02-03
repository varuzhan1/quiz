package com.egstestmyquizi.demo.model.persistence;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "user")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String name;

    @Column
    private String surName;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    private Integer points;

    @Column
    @Enumerated(EnumType.STRING)
    private Role role;
}
