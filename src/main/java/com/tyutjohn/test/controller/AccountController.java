package com.tyutjohn.test.controller;

import com.tyutjohn.test.bean.Account;
import com.tyutjohn.test.mapper.AccountMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class AccountController {
    @Resource
    AccountMapper accountMapper;
    @RequestMapping(value = "/getAccount",method = RequestMethod.GET)
    @ResponseBody
    public List<Account> getAccountList(){
        List<Account> accountList=accountMapper.selectAccountList();
        return accountList;
    }
}
