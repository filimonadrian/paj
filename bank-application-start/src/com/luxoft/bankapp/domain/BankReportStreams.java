package com.luxoft.bankapp.domain;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BankReportStreams {

    public long getNumberOfClients(Bank bank) {
        return bank.getClients().stream().count();
    }

    public long getNumberOfAccounts(Bank bank) {
        return bank.getClients().stream()
                .map(client -> client.getAccounts().stream().count())
                .reduce(0L, Long::sum);
    }

    public SortedSet<Client> getClientsSorted(Bank bank) {
        return new TreeSet<>(bank.getClients());
    }

    public double getTotalSumInAccounts(Bank bank) {
        return bank.getClients().stream()
                .map(Client::getTotalBalance)
                .reduce((double) 0, Double::sum);
    }

    public SortedSet<Account> getAccountsSortedBySum(Bank bank) {
        Comparator<Account> bySum = Comparator.comparingDouble(Account::getBalance);
        Supplier<TreeSet<Account>> account = () -> new TreeSet<Account>(bySum);

        return bank.getClients().stream()
                .map(Client::getAccounts)
                .flatMap(Set::stream)
                .collect(Collectors.toCollection(account));
    }

    public double getBankCreditSum(Bank bank) {
        return bank.getClients().stream()
                .map(Client::getTotalCredits)
                .reduce((double) 0, Double::sum);
    }

    public Map<Client, Collection<Account>> getCustomerAccounts(Bank bank) {
        return bank.getClients().stream()
                .collect(Collectors.toMap(Function.identity(), Client::getAccounts));
    }

    /* resources: https://mkyong.com/java8/java-8-how-to-sort-a-map/ */
    public Map<String, List<Client>> getClientsByCity(Bank bank) {
        Map<String, List<Client>> citiesWithClients = new LinkedHashMap<>();
        citiesWithClients = bank.getClients().stream().collect(Collectors.groupingBy(Client::getCity));

        return citiesWithClients
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey,
                                            Map.Entry::getValue,
                                            (oldValue, newValue) -> oldValue,
                                            LinkedHashMap::new));
    }
}
