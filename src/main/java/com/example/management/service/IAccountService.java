package com.example.management.service;

import com.example.management.dto.request.AccountCreateReq;
import com.example.management.dto.request.AccountFilterReq;
import com.example.management.dto.request.AccountUpdateReq;
import com.example.management.dto.response.AccountRes;
import com.example.management.entity.Accounts;
import org.springframework.data.domain.Page;

import java.util.List;

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
