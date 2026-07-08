package com.dsbank.app;

import components.Client;

import java.util.ArrayList;
import java.util.List;

// 1.1.2 Creation of main class for tests
public class Main {
    public static void main(String[] args) {
        List<Client> myClients = generateClients(3);
        displayClients(myClients);
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
}
