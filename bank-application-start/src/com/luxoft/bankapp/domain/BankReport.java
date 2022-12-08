package com.luxoft.bankapp.domain;

import java.sql.SQLOutput;
import java.util.*;
import java.util.stream.Collectors;
import com.luxoft.bankapp.domain.Bank;
import com.luxoft.bankapp.domain.Client;

public class BankReport {

    public long getNumberOfClients(Bank bank) {
        return bank.getClients().size();
    }

    public long getNumberOfAccounts(Bank bank) {
        Set<Client> clients = bank.getClients();
        long numberOfAccounts = 0;

        for (Client client : clients) {
            numberOfAccounts += client.getAccounts().size();
        }
        return numberOfAccounts;
    }

    public SortedSet<Client> getClientsSorted(Bank bank) {
        return new TreeSet<>(bank.getClients());
    }

    public double getTotalSumInAccounts(Bank bank) {
        double bankBalance = 0;

        for (Client client : bank.getClients()) {
            bankBalance += client.getTotalBalance();
        }

        return bankBalance;
    }

    public SortedSet<Account> getAccountsSortedBySum(Bank bank) {
        SortedSet <Account> sortedAccounts = new TreeSet<>();
        boolean success = false;

        for (Client client : bank.getClients()) {
            success = sortedAccounts.addAll(client.getAccounts());
            if (!success) {
                System.out.println("Cannot add the Accounts into TreeSet");
            }
        }

        return sortedAccounts;
    }

    public double getBankCreditSum(Bank bank) {
        double bankCredits = 0;

        for (Client client : bank.getClients()) {
            bankCredits += client.getTotalCredits();
        }

        return bankCredits;
    }

    public Map<Client, Collection<Account>> getCustomerAccounts(Bank bank) {
        Map<Client, Collection<Account>> clientsMap = new HashMap<>();
        Set<Client> clients = bank.getClients();


        for (Client client : clients) {
            clientsMap.put(client, client.getAccounts());
        }

        return clientsMap;
    }

    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        Map<String, List<Client>> citiesWithClients = new TreeMap<>();

        for (Client client : bank.getClients()) {
            /*
             * if this city is already in the map, just update the client list
             */
            if (citiesWithClients.containsKey(client.getCity())) {
                citiesWithClients.get(client.getCity()).add(client);
            /*
             * otherwise, create a new list with the current client
             * add the tuple in the map
             */
            } else {
                List<Client> tempList = new ArrayList<>();
                tempList.add(client);
                citiesWithClients.put(client.getCity(), tempList);
            }
        }

        return citiesWithClients;
    }

}
