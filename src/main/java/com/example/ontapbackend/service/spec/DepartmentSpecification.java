package com.example.ontapbackend.service.spec;

import com.example.ontapbackend.entity.Departments;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;

import java.util.Date;

public class DepartmentSpecification {

    public static Specification<Departments> equalsId(Long id) {
        return (Root<Departments> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    public static Specification<Departments> equalsName(String name) {
        return (Root<Departments> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (name == null || name.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%");
        };
    }

    public static Specification<Departments> equalsType(String type) {
        return (Root<Departments> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (type == null || type.trim().isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), "%" + type.toLowerCase() + "%");
        };
    }

    public static Specification<Departments> equalsCreatedDate(Date createdDate) {
        return (Root<Departments> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (createdDate == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("createdDate"), createdDate);
        };
    }
}
