package com.example.management.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountFilterReq {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotEmpty(message = "Username cannot be empty")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String passWork;

    @NotEmpty(message = "First Name cannot be empty")
    @Size(min = 1, max = 50, message = "First Name must be between 1 and 50 characters")
    private String firstName;

    @NotEmpty(message = "Last Name cannot be empty")
    @Size(min = 1, max = 50, message = "Last Name must be between 1 and 50 characters")
    private String lastName;

    @NotEmpty(message = "Role cannot be empty")
    private String role;

    @NotEmpty(message = "Department Name cannot be empty")
    private String departmentName;
}

