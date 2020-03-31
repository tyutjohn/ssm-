package com.tyutjohn.test.mapper;

import com.tyutjohn.test.bean.Account;

import java.util.List;

public interface AccountMapper {
    List<Account> selectAccountList();
}
