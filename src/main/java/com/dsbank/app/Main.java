package com.dsbank.app;

import components.Account;
import components.Client;
import components.CurrentAccount;
import components.SavingsAccount;

import java.util.ArrayList;
import java.util.List;

// 1.1.2 Creation of main class for tests
public class Main {
    public static void main(String[] args) {
        List<Client> myClients = generateClients(3);
        // displayClients(myClients);
        List<Account> myAccounts = generateAccounts(myClients);
        displayAccounts(myAccounts);
    }

    public static List<Client> generateClients(int n) {

        List<Client> clients = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            clients.add(new Client("Name" + i, "Firstname" + i));
        }
        return clients;
    }


    public static void displayClients(List<Client> clients) {
        for (Client c : clients) {
            System.out.println(c);
        }
    }

    public static List<Account> generateAccounts(List<Client> clients) {

        List<Account> accounts = new ArrayList<>();
        for (Client c : clients) {
            accounts.add(new CurrentAccount("Current Account", 0 , c));
            accounts.add(new SavingsAccount("Savings Account", 0 , c));
        }
        return accounts;
    }

    public static void displayAccounts(List<Account> accounts) {
        for (Account a : accounts) {
            System.out.println(a);
        }
    }
}
