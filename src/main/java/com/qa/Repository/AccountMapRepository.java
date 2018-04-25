package com.qa.Repository;

import com.qa.domain.Account;

import java.util.HashMap;
import java.util.Map;

public class AccountMapRepository {

    private Map<Integer, Account> accountMap;

    private int count = 0;


    public AccountMapRepository() {

        accountMap = new HashMap<Integer, Account>();
    }

    public void addAccountFromMap(Account userAccount) {
        accountMap.put(count, userAccount);
        count++;
    }

    public void removeAccountFromMap(Long accountToRemove) {
        boolean countExists = accountMap.containsKey(accountToRemove);
        if (countExists) {
            accountMap.remove(accountToRemove);
        }
    }

    public Map<Integer, Account> getAccountMap() {
        return accountMap;
    }

    public int getNumberOfAccountWithFirstName(String firstNameOfAccount) {
        return (int) accountMap.values().stream()
                .filter(eachAccount -> eachAccount.getFirstName().equals(firstNameOfAccount)).count();
    }

}


