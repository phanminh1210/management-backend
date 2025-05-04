package com.example.management.service.Impl;

import com.example.management.dto.request.DepartmentCreateReq;
import com.example.management.dto.request.DepartmentFilterReq;
import com.example.management.dto.request.DepartmentUpdateReq;
import com.example.management.entity.Departments;
import com.example.management.repository.IDepartmentRepo;
import com.example.management.service.IDepartmentService;
import com.example.management.service.spec.DepartmentSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class DepartmentImpl implements IDepartmentService {

    private final IDepartmentRepo departmentRepo;

    @Override
    public Page<Departments> getAllDepartments(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return departmentRepo.findAll(pageable);

    }

    @Override
    public void deleteDepartment(Long id) {
        var existingGroups = departmentRepo.findById(id);
        if(existingGroups.isEmpty()){
            throw new RuntimeException("Department not found");
        }
        departmentRepo.delete(existingGroups.get());
    }

    public Departments updateDepartment(Long id, DepartmentUpdateReq departmentReq) {
        Optional<Departments> optionalDepartment = departmentRepo.findById(id);

        if (!optionalDepartment.isPresent()) {
            throw new RuntimeException("Department not found");
        }

        Departments department = optionalDepartment.get();

        if (departmentReq.getName() == null || departmentReq.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Department name cannot be null or empty");
        }

        department.setName(departmentReq.getName());
        department.setType(departmentReq.getType());

        return departmentRepo.save(department);
    }

    @Override
    public Departments addDepartment(DepartmentCreateReq departmentReq) {
        Departments department = new Departments();
        department.setName(departmentReq.getName());
        department.setType(departmentReq.getType());
        return departmentRepo.save(department);
    }

    @Override
    public List<Departments> filterDepartments(DepartmentFilterReq filterReq) {
        Specification<Departments> specification = Specification.where(null);

        if (filterReq.getId() != null) {
            specification = specification.and(DepartmentSpecification.equalsId(filterReq.getId()));
        }
        if (filterReq.getName() != null && !filterReq.getName().trim().isEmpty()) {
            specification = specification.and(DepartmentSpecification.equalsName(filterReq.getName()));
        }
        if (filterReq.getType() != null && !filterReq.getType().trim().isEmpty()) {
            specification = specification.and(DepartmentSpecification.equalsType(filterReq.getType()));
        }
        if (filterReq.getCreatedDate() != null) {
            specification = specification.and(DepartmentSpecification.equalsCreatedDate(filterReq.getCreatedDate()));
        }

        return departmentRepo.findAll(specification);
    }

    @Override
    public Departments getDepartmentById(Long id) {
        return departmentRepo.findById(id).orElse(null);
    }

}
