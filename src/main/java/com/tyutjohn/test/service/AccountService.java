package com.tyutjohn.test.service;

import com.tyutjohn.test.bean.Account;

import java.util.List;

public interface AccountService {
    //controller需要用到的方法
    List<Account> getAccountList();
}
