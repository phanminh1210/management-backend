package com.example.management.service.Impl;


import com.example.management.common.RoleEnum;
import com.example.management.dto.request.AccountCreateReq;
import com.example.management.dto.request.AccountFilterReq;
import com.example.management.dto.request.AccountUpdateReq;
import com.example.management.dto.response.AccountRes;
import com.example.management.entity.Accounts;
import com.example.management.entity.Departments;
import com.example.management.repository.IAccountRepo;
import com.example.management.repository.IDepartmentRepo;
import com.example.management.service.IAccountService;
import com.example.management.service.spec.AccountSpecification;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final IAccountRepo accountRepo;

    private final IDepartmentRepo departmentRepo;

    @Override
    public List<AccountRes> getAllAccountRes() {
        return accountRepo.findAllAccountRes();
    }

    @Override
    public List<Accounts> getAllAccounts() {
        return accountRepo.findAll();
    }

    @Override
    public void deleteAccount(Long id) {
        var existingGroups = accountRepo.findById(id);
        if (existingGroups.isEmpty()) {
            throw new RuntimeException("Account not found");
        }
        accountRepo.delete(existingGroups.get());
    }

    @Override
    public Accounts updateAccount(Long id, AccountUpdateReq accountUpdateReq) {
        Optional<Accounts> optionalAccount = accountRepo.findById(id); // Tìm kiếm tài khoản theo id

        if (!optionalAccount.isPresent()) {
            throw new RuntimeException("Account not found"); // Ném ngoại lệ nếu không tìm thấy tài khoản
        }

        Accounts account = optionalAccount.get(); // Lấy tài khoản đã tìm thấy

        // Lấy department từ departmentId
        Optional<Departments> optionalDepartment = departmentRepo.findById(accountUpdateReq.getDepartmentId());

        if (!optionalDepartment.isPresent()) {
            throw new RuntimeException("Department not found"); // Nếu không tìm thấy department thì ném ngoại lệ
        }

        Departments department = optionalDepartment.get(); // Lấy đối tượng department

        // Cập nhật thông tin tài khoản
        account.setUserName(accountUpdateReq.getUserName());
        account.setPassWork(accountUpdateReq.getPassWork()); // Nếu có cần thiết, bạn có thể mã hóa password
        account.setFirstName(accountUpdateReq.getFirstName());
        account.setLastName(accountUpdateReq.getLastName());
        account.setRole(accountUpdateReq.getRole());
        account.setDepartments(department); // Gán department vào tài khoản

        return accountRepo.save(account); // Lưu tài khoản đã cập nhật
    }

    @Override
    public Accounts addAccount(AccountCreateReq accountCreateReq) {
        Accounts account = new Accounts();
        account.setUserName(accountCreateReq.getUserName());
        account.setPassWork(accountCreateReq.getPassWork());
        account.setFirstName(accountCreateReq.getFirstName());
        account.setLastName(accountCreateReq.getLastName());
        account.setRole(accountCreateReq.getRole());
        Departments department = departmentRepo.findById(accountCreateReq.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found"));
        account.setDepartments(department);

        return accountRepo.save(account);
    }

    @Override
    public List<AccountRes> filter(AccountFilterReq accountFilterReq) {
            Specification<Accounts> specification = Specification.where(null);

            // Áp dụng các điều kiện từ AccountFilterReq
            if (accountFilterReq.getId() != null) {
                specification = specification.and(AccountSpecification.equalsId(accountFilterReq.getId()));
            }

            if (accountFilterReq.getUserName() != null && !accountFilterReq.getUserName().trim().isEmpty()) {
                specification = specification.and(AccountSpecification.equalsUserName(accountFilterReq.getUserName()));
            }

            if (accountFilterReq.getFirstName() != null && !accountFilterReq.getFirstName().trim().isEmpty()) {
                specification = specification.and(AccountSpecification.equalsFirstName(accountFilterReq.getFirstName()));
            }

            if (accountFilterReq.getLastName() != null && !accountFilterReq.getLastName().trim().isEmpty()) {
                specification = specification.and(AccountSpecification.equalsLastName(accountFilterReq.getLastName()));
            }

            if (accountFilterReq.getRole() != null && !accountFilterReq.getRole().trim().isEmpty()) {
                specification = specification.and(AccountSpecification.equalsRole(RoleEnum.valueOf(accountFilterReq.getRole())));
            }

            if (accountFilterReq.getDepartmentName() != null && !accountFilterReq.getDepartmentName().trim().isEmpty()) {
                specification = specification.and(AccountSpecification.equalsDepartmentName(accountFilterReq.getDepartmentName()));
            }

            // Tìm tất cả các tài khoản theo specification
            List<Accounts> accounts = accountRepo.findAll(specification);

            // Chuyển đổi kết quả thành danh sách AccountRes
            List<AccountRes> result = new ArrayList<>();
            for (Accounts account : accounts) {
                result.add(new AccountRes(
                        account.getUserName(),
                        account.getId(),
                        account.getFirstName(),
                        account.getLastName(),
                        account.getRole(),
                        account.getDepartments() != null ? account.getDepartments().getName() : null
                ));
            }

            return result;
        }

    @Override
    public Page<Accounts> getAllAccount(int page, int size) {
        Pageable pageable = PageRequest.of(page-1, size);
        return accountRepo.findAll(pageable);
    }

    @Override
    public Accounts getAccountById(Long id) {
        return accountRepo.findById(id).orElse(null);
    }

}


