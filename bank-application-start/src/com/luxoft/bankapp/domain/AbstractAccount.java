package com.luxoft.bankapp.domain;

import com.luxoft.bankapp.exceptions.NotEnoughFundsException;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;

public abstract class AbstractAccount implements Account {
	
	private int id;
	protected double balance;
	
	public AbstractAccount(int id, double balance) {
		this.id = id;
		this.balance = balance;
	}

	@Override
	public void deposit(final double amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Cannot deposit a negative amount");
		}
		this.balance += amount;
	}

	@Override
	public void withdraw(final double amount) throws NotEnoughFundsException {
		if (amount < 0) {
			throw new IllegalArgumentException("Cannot withdraw a negative amount");
		}
		
		if (amount > maximumAmountToWithdraw()) {
			throw new NotEnoughFundsException(id, balance, amount, "Requested amount exceeds the maximum amount to withdraw");
		}
		
		this.balance -= amount;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public int compareTo(Account account) {
		if (this.getBalance() > account.getBalance()) {
			return 1;
		} else if (this.getBalance() < account.getBalance()) {
			return -1;
		}

		return 0;
	}

	@Override
	public String toString() {
		return "Account {" +
				"id=" + id +
				", balance=" + balance +
				'}';
	}
}
