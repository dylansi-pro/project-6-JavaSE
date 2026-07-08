package com.dsbank.app;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

import java.util.*;

// 1.1.2 Creation of main class for tests
public class Main {
    public static void main(String[] args) {
        List<Client> myClients = generateClients(3);
        // 1.2.3 Creation of the tablea accoun
        List<Account> myAccounts = generateAccounts(myClients);
        // 1.3.1 Adaptation of the table of accounts
        Map<Integer, Account> myClientsMap = convertToHashtable(myAccounts);
        displaySortedAccounts(myClientsMap);
    }

    public static List<Client> generateClients(int n) {

        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            clients.add(new Client("Name" + i, "Firstname" + i));
        }
        return clients;
    }


//    public static void displayClients(List<Client> clients) {
//        for (Client c : clients) {
//            System.out.println(c);
//        }
//    }

    public static List<Account> generateAccounts(List<Client> clients) {

        List<Account> accounts = new ArrayList<>();
        for (Client c : clients) {
            accounts.add(new CurrentAccount("Current Account", 0 , c));
            accounts.add(new SavingsAccount("Savings Account", 0 , c));
        }
        return accounts;
    }

//    public static void displayAccounts(List<Account> accounts) {
//        for (Account a : accounts) {
//            System.out.println(a);
//        }
//    }

    public static Map<Integer, Account> convertToHashtable(List<Account> accounts) {

        Map<Integer, Account> map = new HashMap<>();
        for (Account a : accounts) {
            map.put(a.getAccountNumber(), a);
        }
        return map;
    }

    public static void displaySortedAccounts(Map<Integer, Account> accountsMap) {
        accountsMap.entrySet().stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getBalance()))
                .forEach(entry -> System.out.println(entry.getValue()));
    }
}
