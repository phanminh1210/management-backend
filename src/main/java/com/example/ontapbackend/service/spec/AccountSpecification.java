package com.example.ontapbackend.service.spec;

import com.example.ontapbackend.common.RoleEnum;
import com.example.ontapbackend.dto.request.AccountFilterReq;
import com.example.ontapbackend.entity.Accounts;
import com.example.ontapbackend.entity.Departments;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;


public class AccountSpecification {
    public static Specification<Accounts> equalsId(Long id) {
        return (Root<Accounts> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (id == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("id"), id);
        };
    }

    public static Specification<Accounts> equalsUserName(String userName) {
        return (Root<Accounts> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (StringUtils.isEmpty(userName)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("userName")), "%" + userName.toLowerCase() + "%");
        };
    }

    public static Specification<Accounts> equalsFirstName(String firstName) {
        return (Root<Accounts> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (StringUtils.isEmpty(firstName)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("firstName")), "%" + firstName.toLowerCase() + "%");
        };
    }

    public static Specification<Accounts> equalsLastName(String lastName) {
        return (Root<Accounts> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (StringUtils.isEmpty(lastName)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("lastName")), "%" + lastName.toLowerCase() + "%");
        };
    }

    public static Specification<Accounts> equalsRole(RoleEnum role) {
        return (Root<Accounts> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (role == null) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.equal(root.get("role"), role);
        };
    }

    public static Specification<Accounts> equalsDepartmentName(String departmentName) {
        return (Root<Accounts> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            if (StringUtils.isEmpty(departmentName)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(criteriaBuilder.lower(root.get("departments").get("name")), "%" + departmentName.toLowerCase() + "%");
        };
    }

}
