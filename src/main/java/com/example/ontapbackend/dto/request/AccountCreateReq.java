package com.example.ontapbackend.dto.request;

import com.example.ontapbackend.common.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.SecondaryTable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountCreateReq implements Serializable {

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String userName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 100, message = "Password must be between 6 and 100 characters")
    private String passWork;

    @NotNull(message = "First name cannot be null")
    @Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
    private String lastName;

    @NotNull(message = "Role cannot be null")
    private RoleEnum role;

    @NotNull(message = "Department ID cannot be null")
    @Min(value = 1, message = "Department ID must be greater than or equal to 1")
    private Long departmentId;
}
