package com.luxoft.bankapp.domain;

import java.util.*;

public class Client implements Comparable<Client> {
	
	private String name;
	private Gender gender;
	private String city;
	private Set<Account> accounts = new HashSet<Account>();

//	public Client(String name, Gender gender) {
//		this.name = name;
//		this.gender = gender;
//	}

	public Client(String name, Gender gender, String city) {
		this.name = name;
		this.gender = gender;
		this.city = city;
	}

	public void addAccount(final Account account) {
		accounts.add(account);
	}
	
	public String getName() {
		return name;
	}
	
	public Gender getGender() {
		return gender;
	}

	public String getCity() {
		return city;
	}

	public Set<Account> getAccounts() {
		return Collections.unmodifiableSet(accounts);
	}

	public double getTotalBalance() {
		double totalBalance = 0;

		for (Account account : accounts) {
			totalBalance += account.getBalance();
		}
		return totalBalance;
	}

	public double getTotalCredits() {
		double totalCredits = 0;

		for (Account account : accounts) {
			if (account instanceof CheckingAccount) {
				totalCredits += ((CheckingAccount)account).getOverdraft();
			}
		}
		return totalCredits;
	}

	public String getClientGreeting() {
		if (gender != null) {
			return gender.getGreeting() + " " + name;
		} else {
			return name;
		}
	}
	
	@Override
	public String toString() {
		return getClientGreeting();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return Objects.equals(name, client.name) && gender == client.gender && Objects.equals(accounts, client.accounts);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, gender, accounts);
	}

	@Override
	public int compareTo(Client client) {
		return this.name.toLowerCase().compareTo(client.name.toLowerCase());
	}
}
