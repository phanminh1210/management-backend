package com.example.ontapbackend.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "department")
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Departments implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "total_member")
    private Long member;

    @Column(name = "type")
    private String type;

    @Column(name = "created_date")
    private Date createdDate;


}
