package com.example.ontapbackend.repository;

import com.example.ontapbackend.dto.response.AccountRes;
import com.example.ontapbackend.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAccountRepo extends JpaRepository<Accounts, Long>, JpaSpecificationExecutor<Accounts>{

    @Query("SELECT new com.example.ontapbackend.dto.response.AccountRes(a.userName, a.id, a.firstName, a.lastName, a.role, d.name) " +
            "FROM Accounts a LEFT JOIN a.departments d")

    List<AccountRes> findAllAccountRes();



}
