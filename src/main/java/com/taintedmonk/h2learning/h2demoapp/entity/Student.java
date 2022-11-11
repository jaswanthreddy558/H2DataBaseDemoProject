package com.taintedmonk.h2learning.h2demoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "STUDENT") //not a must, but if not used classname will be taken as table name
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Student {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username")
    private String sname;

    @Column(name = "password")
    private String password;

    @Column(name = "roles")
    private String srole;


}
