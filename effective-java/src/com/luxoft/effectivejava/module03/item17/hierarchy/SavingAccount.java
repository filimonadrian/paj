package com.luxoft.effectivejava.module03.item17.hierarchy;

public class SavingAccount extends Account {
	public SavingAccount(double balance) {
        super(balance);
    }
	
	public double maximumAmountToWithdraw(){
        return balance;
    }
}