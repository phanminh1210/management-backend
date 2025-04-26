package com.example.ontapbackend.dto.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentFilterReq {

    @NotNull(message = "ID cannot be null")
    private Long id;

    @NotNull(message = "Username cannot be null")
    @Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    private String name;

    @NotEmpty(message = "Type cannot be empty")
    @Size(min = 1, max = 50, message = "Type must be between 1 and 50 characters")
    private String type;

    @NotNull(message = "Created Date cannot be null")
    @Past(message = "Created Date must be a date in the past")
    private Date createdDate;
}
