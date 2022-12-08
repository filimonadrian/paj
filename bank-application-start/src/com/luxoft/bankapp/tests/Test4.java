package com.luxoft.bankapp.tests;

import com.luxoft.bankapp.domain.*;
import com.luxoft.bankapp.exceptions.ClientExistsException;
import com.luxoft.bankapp.service.BankService;
import org.junit.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import static com.luxoft.bankapp.main.BankApplication.addMoreClients;
import static org.junit.Assert.assertEquals;

public class Test4 {

    @Test
    public void testGetNumberOfClients() {
        Bank bank = new Bank();
        BankReport bankReport = new BankReport();
        BankReportStreams bankReportStreams = new BankReportStreams();
        addMoreClients(bank);

        long numberOfClients = bankReport.getNumberOfClients(bank);
        long numberOfClientsStreams = bankReportStreams.getNumberOfClients(bank);

        assertEquals(numberOfClients, numberOfClientsStreams);
    }

    @Test
    public void testGetNumberOfAccounts() {
        Bank bank = new Bank();
        BankReport bankReport = new BankReport();
        BankReportStreams bankReportStreams = new BankReportStreams();
        addMoreClients(bank);

        long numberOfAccounts = bankReport.getNumberOfAccounts(bank);
        long numberOfAccountsStreams = bankReportStreams.getNumberOfAccounts(bank);

        assertEquals(numberOfAccounts, numberOfAccountsStreams);
    }

    @Test
    public void testGetTotalSumInAccounts() {
        Bank bank = new Bank();
        BankReport bankReport = new BankReport();
        BankReportStreams bankReportStreams = new BankReportStreams();
        addMoreClients(bank);

        double totalSumInAccounts = bankReport.getTotalSumInAccounts(bank);
        double totalSumInAccountsStreams = bankReportStreams.getTotalSumInAccounts(bank);

        assertEquals(totalSumInAccounts, totalSumInAccountsStreams, 0.001);
    }

    @Test
    public void testGetClientsSorted() {
        Bank bank = new Bank();
        BankReport bankReport = new BankReport();
        BankReportStreams bankReportStreams = new BankReportStreams();
        addMoreClients(bank);

        SortedSet<Client> clientsSorted = bankReport.getClientsSorted(bank);
        SortedSet<Client> clientsSortedStreams = bankReportStreams.getClientsSorted(bank);

        assertEquals(clientsSorted, clientsSortedStreams);
    }

    @Test
    public void testGetAccountsSortedBySum() {
        Bank bank = new Bank();
        BankReport bankReport = new BankReport();
        BankReportStreams bankReportStreams = new BankReportStreams();
        addMoreClients(bank);

        SortedSet<Account> clientsSortedBySum = bankReport.getAccountsSortedBySum(bank);
        SortedSet<Account> clientsSortedBySumStreams = bankReportStreams.getAccountsSortedBySum(bank);

        assertEquals(clientsSortedBySum, clientsSortedBySumStreams);
    }

    @Test
    public void testGetBankCreditSum() {
        Bank bank = new Bank();
        BankReport bankReport = new BankReport();
        BankReportStreams bankReportStreams = new BankReportStreams();
        addMoreClients(bank);

        double bankCreditSum = bankReport.getBankCreditSum(bank);
        double bankCreditSumStreams = bankReportStreams.getBankCreditSum(bank);

        assertEquals(bankCreditSum, bankCreditSumStreams, 0.001);
    }

    @Test
    public void testGetCustomerAccounts() {
        Bank bank = new Bank();
        BankReport bankReport = new BankReport();
        BankReportStreams bankReportStreams = new BankReportStreams();
        addMoreClients(bank);

        Map<Client, Collection<Account>> customerAccounts = bankReport.getCustomerAccounts(bank);
        Map<Client, Collection<Account>> customerAccountsStreams = bankReportStreams.getCustomerAccounts(bank);

        assertEquals(customerAccounts, customerAccountsStreams);
    }

    @Test
    public void testGetClientsByCity() {
        Bank bank = new Bank();
        BankReport bankReport = new BankReport();
        BankReportStreams bankReportStreams = new BankReportStreams();
        addMoreClients(bank);

        Map<String, List<Client>> clientsByCity = bankReport.getClientsByCity(bank);
        Map<String, List<Client>> clientsByCityStreams = bankReportStreams.getClientsByCity(bank);

        assertEquals(clientsByCity, clientsByCityStreams);
    }
}
