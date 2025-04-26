package com.example.ontapbackend.dto.response;

import com.example.ontapbackend.common.RoleEnum;
import lombok.*;

@Setter
@Getter
@ToString
public class AccountRes {
    private String userName;
    private Long id;
    private String firstName;
    private String lastName;
    private RoleEnum role;
    private String departmentName;

    public AccountRes(String userName, Long id, String firstName, String lastName, RoleEnum role, String departmentName) {
        this.userName = userName;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.departmentName = departmentName;
    }

}

