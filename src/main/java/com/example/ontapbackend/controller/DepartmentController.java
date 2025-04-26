package com.example.ontapbackend.controller;

import com.example.ontapbackend.dto.request.AccountFilterReq;
import com.example.ontapbackend.dto.request.DepartmentCreateReq;
import com.example.ontapbackend.dto.request.DepartmentFilterReq;
import com.example.ontapbackend.dto.request.DepartmentUpdateReq;

import com.example.ontapbackend.dto.response.AccountRes;
import com.example.ontapbackend.entity.Accounts;
import com.example.ontapbackend.entity.Departments;
import com.example.ontapbackend.service.IDepartmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/departments")
@RequiredArgsConstructor
@CrossOrigin("*")
@RestController
@Validated
public class DepartmentController {

    private final IDepartmentService departmentService;

    @GetMapping()
    public ResponseEntity<Page<Departments>> getAllDepartments(@RequestParam int page, @RequestParam int size) {
        Page<Departments> departments = departmentService.getAllDepartments(page, size);
        return ResponseEntity.ok(departments);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Departments> updateDepartment(@PathVariable Long id, @RequestBody @Valid DepartmentUpdateReq departmentReq) {
        try {
            Departments updatedDepartment = departmentService.updateDepartment(id, departmentReq);
            return ResponseEntity.ok(updatedDepartment);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(null);
        } catch (RuntimeException ex) {
            return ResponseEntity.status(404).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(null);
    }

    @PostMapping()
    public ResponseEntity<Departments> createDepartment(@RequestBody @Valid DepartmentCreateReq departmentReq) {
        Departments response = departmentService.addDepartment(departmentReq);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/filter")
    public ResponseEntity<List<Departments>> filterDepartments(DepartmentFilterReq filterReq) {
        List<Departments> filteredDepartments = departmentService.filterDepartments(filterReq);
        return ResponseEntity.ok(filteredDepartments);
    }

    @GetMapping("{id}")
    public ResponseEntity<Departments> getDepartmentById(@PathVariable Long id) {
        Departments departments = departmentService.getDepartmentById(id);
        if (departments != null) {
            return ResponseEntity.ok(departments);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}