package com.qa.service;

import com.qa.domain.Account;
import com.qa.util.JSONUtil;

import javax.enterprise.inject.Default;
import javax.inject.Inject;

public class ImplAccountService {

    @Inject
    private JSONUtil util;

    @Default
    public String create(Account account) {

        if (account.getAccountNumber().equals("9999")) {

            return "This account is blocked";
        } else {
            return "Account Added";
        }
    }
}
