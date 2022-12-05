package com.luxoft.bankapp.main;

import com.luxoft.bankapp.domain.*;
import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.exceptions.NotEnoughFundsException;
import com.luxoft.bankapp.exceptions.OverdraftLimitExceededException;
import com.luxoft.bankapp.service.BankService;
import com.luxoft.bankapp.domain.BankReport;

import java.util.Scanner;

public class BankApplication {
	
	private static Bank bank;
	
	public static void main(String[] args) {
		bank = new Bank();
		modifyBank();

		if (args[0].equals("-statistics")) {
			addMoreClients();
			//consoleMode();
			consoleModeFunctional();
		} else {
			printBalance();
			BankService.printMaximumAmountToWithdraw(bank);
		}
	}
	
	private static void modifyBank() {
		Client client1 = new Client("John", Gender.MALE, "New York");
		Account account1 = new SavingAccount(1, 100);
		Account account2 = new CheckingAccount(2, 100, 20);
		client1.addAccount(account1);
		client1.addAccount(account2);
		
		try {
		   BankService.addClient(bank, client1);
		} catch(ClientExistsException e) {
			System.out.format("Cannot add an already existing client: %s%n", client1.getName());
	    } 

		account1.deposit(100);
		try {
		  account1.withdraw(10);
		} catch (OverdraftLimitExceededException e) {
	    	System.out.format("Not enough funds for account %d, balance: %.2f, overdraft: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getOverdraft(), e.getAmount());
	    } catch (NotEnoughFundsException e) {
	    	System.out.format("Not enough funds for account %d, balance: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getAmount());
	    }
		
		try {
		  account2.withdraw(90);
		} catch (OverdraftLimitExceededException e) {
	      System.out.format("Not enough funds for account %d, balance: %.2f, overdraft: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getOverdraft(), e.getAmount());
	    } catch (NotEnoughFundsException e) {
	      System.out.format("Not enough funds for account %d, balance: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getAmount());
	    }
		
		try {
		  account2.withdraw(100);
		} catch (OverdraftLimitExceededException e) {
	      System.out.format("Not enough funds for account %d, balance: %.2f, overdraft: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getOverdraft(), e.getAmount());
	    } catch (NotEnoughFundsException e) {
	      System.out.format("Not enough funds for account %d, balance: %.2f, tried to extract amount: %.2f%n", e.getId(), e.getBalance(), e.getAmount());
	    }
		
		try {
		  BankService.addClient(bank, client1);
		} catch(ClientExistsException e) {
		  System.out.format("Cannot add an already existing client: %s%n", client1);
	    } 
	}

	private static void addMoreClients() {
		/* first client */
		Client client1 = new Client("Oliver", Gender.MALE, "Bucharest");
		Account account1 = new SavingAccount(3, 523);
		Account account2 = new CheckingAccount(4, 300, 100);
		client1.addAccount(account1);
		client1.addAccount(account2);

		try {
			BankService.addClient(bank, client1);
		} catch(ClientExistsException e) {
			System.out.format("Cannot add an already existing client: %s%n", client1.getName());
		}

		/* second client */
		Client client2 = new Client("James", Gender.MALE, "Bucharest");
		Account account3 = new SavingAccount(5, 935);
		Account account4 = new CheckingAccount(6, 860, 5);
		client2.addAccount(account3);
		client2.addAccount(account4);

		try {
			BankService.addClient(bank, client2);
		} catch(ClientExistsException e) {
			System.out.format("Cannot add an already existing client: %s%n", client2.getName());
		}

		/* third client */
		Client client3 = new Client("Emma", Gender.FEMALE, "Paris");
		Account account5 = new SavingAccount(7, 350);
		Account account6 = new CheckingAccount(8, 95, 10);
		client3.addAccount(account5);
		client3.addAccount(account6);

		try {
			BankService.addClient(bank, client3);
		} catch(ClientExistsException e) {
			System.out.format("Cannot add an already existing client: %s%n", client3.getName());
		}
	}

	private static void printBalance() {
		System.out.format("%nPrint balance for all clients%n");
		for(Client client : bank.getClients()) {
			System.out.println("Client: " + client);
			for (Account account : client.getAccounts()) {
				System.out.format("Account %d : %.2f%n", account.getId(), account.getBalance());
			}
		}
	}
	private static void consoleMode() {
		Scanner in = new Scanner(System.in);
		String command;
		BankReport bankReport = new BankReport();

		System.out.println("\nPress any key for options(case insensitive)");

		while(true) {
			command = in.nextLine();
			switch (command.toLowerCase()) {
				case "numberofclients" ->
						System.out.println("Number of clients: " + bankReport.getNumberOfClients(bank));
				case "numberofaccounts" ->
						System.out.println("Number of accounts: " + bankReport.getNumberOfAccounts(bank));
				case "clientssorted" -> System.out.println("Clients sorted " + bankReport.getClientsSorted(bank));
				case "totalsuminaccounts" ->
						System.out.println("Total sum in accounts: " + bankReport.getTotalSumInAccounts(bank));
				case "accountssortedbysum" ->
						System.out.println("Accounts sorted by sum: " + bankReport.getAccountsSortedBySum(bank));
				case "bankcreditsum" -> System.out.println("Bank credit sum: " + bankReport.getBankCreditSum(bank));
				case "customeraccounts" ->
						System.out.println("Customer Accounts: " + bankReport.getCustomerAccounts(bank));
				case "clientsbycity" -> System.out.println("Clients by city: " + bankReport.getClientsByCity(bank));
				case "exit", "q" -> {
					return;
				}
				default -> System.out.println("Options:\n" +
						"numberofclients\n" +
						"numberofaccounts\n" +
						"clientssorted\n" +
						"totalsuminaccounts\n" +
						"accountssortedbysum\n" +
						"bankcreditsum\n" +
						"customeraccounts\n" +
						"clientsbycity\n" +
						"exit q\n");
			}
		}
	}

	private static void consoleModeFunctional() {
		Scanner in = new Scanner(System.in);
		BankReportStreams bankReport = new BankReportStreams();
		String command;

		System.out.println("\nPress any key for options(case insensitive)");

		while(true) {
			command = in.nextLine();
			switch (command.toLowerCase()) {
				case "numberofclients", "1" ->
						System.out.println("Number of clients: " + bankReport.getNumberOfClients(bank));
				case "numberofaccounts", "2" ->
						System.out.println("Number of accounts: " + bankReport.getNumberOfAccounts(bank));
				case "clientssorted", "3" -> System.out.println("Clients sorted " + bankReport.getClientsSorted(bank));
				case "totalsuminaccounts", "4" ->
						System.out.println("Total sum in accounts: " + bankReport.getTotalSumInAccounts(bank));
				case "accountssortedbysum", "5" ->
						System.out.println("Accounts sorted by sum: " + bankReport.getAccountsSortedBySum(bank));
				case "bankcreditsum", "6" -> System.out.println("Bank credit sum: " + bankReport.getBankCreditSum(bank));
				case "customeraccounts", "7" ->
						System.out.println("Customer Accounts: " + bankReport.getCustomerAccounts(bank));
				case "clientsbycity", "8" -> System.out.println("Clients by city: " + bankReport.getClientsByCity(bank));
				case "exit", "q" -> {
					return;
				}
				default -> System.out.println("Options:\n" +
						"numberofclients 1\n" +
						"numberofaccounts 2\n" +
						"clientssorted 3\n" +
						"totalsuminaccounts 4\n" +
						"accountssortedbysum 5\n" +
						"bankcreditsum 6\n" +
						"customeraccounts 7\n" +
						"clientsbycity 8\n" +
						"exit q\n");
			}
		}
	}

}
