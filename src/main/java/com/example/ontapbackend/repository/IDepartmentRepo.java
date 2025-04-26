package com.example.ontapbackend.repository;

import com.example.ontapbackend.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface IDepartmentRepo extends JpaRepository<Departments, Long>, JpaSpecificationExecutor<Departments> {

}
