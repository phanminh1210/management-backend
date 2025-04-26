package com.example.ontapbackend.service;

import com.example.ontapbackend.dto.request.AccountCreateReq;
import com.example.ontapbackend.dto.request.AccountFilterReq;
import com.example.ontapbackend.dto.request.AccountUpdateReq;
import com.example.ontapbackend.dto.response.AccountRes;
import com.example.ontapbackend.entity.Accounts;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    List<AccountRes> getAllAccountRes();

    List<Accounts> getAllAccounts();

    void deleteAccount(Long id);

    Accounts updateAccount(Long id, AccountUpdateReq accountUpdateReq);

    Accounts addAccount(AccountCreateReq accountCreateReq);

    List<AccountRes> filter(AccountFilterReq accountFilterReq);

    Page<Accounts> getAllAccount(int page, int size);

    Accounts getAccountById(Long id);

}
