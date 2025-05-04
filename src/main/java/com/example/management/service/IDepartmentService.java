package com.example.management.service;

import com.example.management.dto.request.DepartmentCreateReq;
import com.example.management.dto.request.DepartmentFilterReq;
import com.example.management.dto.request.DepartmentUpdateReq;
import com.example.management.entity.Departments;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IDepartmentService {

    Page<Departments> getAllDepartments(int page, int size);

    void deleteDepartment(Long id);

    Departments updateDepartment(Long id, DepartmentUpdateReq departmentReq);

    Departments addDepartment(DepartmentCreateReq departmentReq);

    List<Departments> filterDepartments(DepartmentFilterReq filterReq);

    Departments getDepartmentById(Long id);
}
