package com.example.management.entity;

import com.example.management.common.RoleEnum;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table (name = "account")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Accounts implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String passWork;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @ManyToOne
    @JoinColumn (name = "department_id")
    private Departments departments;
}

