package com.luxoft.bankapp.domain;

import java.util.HashMap;
import java.util.Map;

public class AccountCache {
    private static Map<String, AbstractAccount> accountsMap = new HashMap<>();

    public static AbstractAccount cloneAccount(String accountType) {
        AbstractAccount cachedAccount = accountsMap.get(accountType);
        return cachedAccount.clone();
    }

    public static void loadCache() {

        CheckingAccount checkingAccount = (CheckingAccount)AccountFactory.newAccount("CHECKING", 1);
        accountsMap.put("checking_account", checkingAccount);

        SavingAccount savingAccount = (SavingAccount)AccountFactory.newAccount("SAVING", 2);
        accountsMap.put("saving_account", savingAccount);

    }

    public static void main(String[] args) {
        AccountCache.loadCache();
        AbstractAccount clonedCheckingAccount = AccountCache.cloneAccount("checking_account");
        clonedCheckingAccount.printAccountDetails();

        AbstractAccount clonedSavingAccount = AccountCache.cloneAccount("saving_account");
        clonedSavingAccount.printAccountDetails();
    }
}
