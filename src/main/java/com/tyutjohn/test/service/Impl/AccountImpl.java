package com.tyutjohn.test.service.Impl;

import com.tyutjohn.test.bean.Account;
import com.tyutjohn.test.mapper.AccountMapper;
import com.tyutjohn.test.service.AccountService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AccountImpl implements AccountService {
    @Resource
    AccountMapper accountMapper;
    public List<Account> getAccountList() {
        return accountMapper.selectAccountList();
    }
}
