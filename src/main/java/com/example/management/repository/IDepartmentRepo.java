package com.example.management.repository;

import com.example.management.entity.Departments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IDepartmentRepo extends JpaRepository<Departments, Long>, JpaSpecificationExecutor<Departments> {

}
