package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

import java.util.Comparator;

public interface Account extends Comparable<Account> {
	public void deposit(double amount);
	public void withdraw(double amount) throws NotEnoughFundsException;
	public int getId();
	public double getBalance();
	public double maximumAmountToWithdraw();
}
