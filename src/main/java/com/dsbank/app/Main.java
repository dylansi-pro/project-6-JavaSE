package com.dsbank.app;

import components.*;

import java.util.*;
import java.util.Optional;
import java.util.function.Predicate;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.BufferedReader;
import java.nio.file.Files;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.List;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

// 1.1.2 Creation of main class for tests
public class Main {
    public static void main(String[] args) {

        /*  // I - Account Management
        List<Client> myClients = generateClients(3);
        // 1.2.3 Creation of the tablea accoun
        List<Account> myAccounts = generateAccounts(myClients);
        // 1.3.1 Adaptation of the table of accounts
        Map<Integer, Account> myClientsMap = convertToHashtable(myAccounts);
        // Chargement des flux (étape 1.3.4)
        List<Flow> myFlows = generateFlows(myAccounts);
        // Traitement des flux et vérification (étape 1.3.5)
        processAndCheckFlows(myFlows, myClientsMap);
        // Affichage final trié (étape 1.3.1)
        System.out.println("--- Comptes triés par solde ---");
        displaySortedAccounts(myClientsMap);
        */

        // II - Account management adcanced

        List<Account> myAccounts = loadAccountsFromXml("src/main/resources/accounts.xml");
        List<Flow> myFlows = loadFlowsFromJson("src/main/resources/flows.json");

        // Associer un nouveau Client à chaque compte
        for (Account acc : myAccounts) {
            Client client = new Client("ClientNom" + acc.getAccountNumber(), "Prenom" + acc.getAccountNumber());
            acc.setClient(client);
        }

        System.out.println("Chargement terminé !");
        System.out.println("Nombre de comptes chargés : " + myAccounts.size());
        System.out.println("Nombre de flux chargés : " + myFlows.size());

        if (myAccounts.isEmpty() || myFlows.isEmpty()) {
            System.out.println("ATTENTION : Les listes sont vides. Vérifie ton XML/JSON.");
            return; // On arrête tout si rien n'est chargé
        }

        Map<Integer, Account> myAccountsMap = convertToHashtable(myAccounts);
        processAndCheckFlows(myFlows, myAccountsMap);

        System.out.println("--- Résultat Final ---");
        displaySortedAccounts(myAccountsMap);
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
        Map<Integer, Account> map = new Hashtable<>();
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

    public static List<Flow> generateFlows(List<Account> accounts) {
        List<Flow> flows = new ArrayList<>();

        // 1. Débit de 50€ du compte n°1
        flows.add(new Debit("Débit de secours", 1, 50.0, 1, true));

        // 2. Crédit de 100.50€ sur tous les "CurrentAccount"
        for (Account a : accounts) {
            if (a instanceof CurrentAccount) {
                flows.add(new Credit("Prime Current", 2, 100.50, a.getAccountNumber(), true));
            }
        }

        // 3. Crédit de 1500€ sur tous les "SavingsAccount"
        for (Account a : accounts) {
            if (a instanceof SavingsAccount) {
                flows.add(new Credit("Prime Epargne", 3, 1500.0, a.getAccountNumber(), true));
            }
        }

        // 4. Transfert de 50€ du compte n°1 vers n°2
        flows.add(new Transfert("Virement interne", 4, 50.0, 2, true, 1));

        return flows;
    }

    public static void processAndCheckFlows(List<Flow> flows, Map<Integer, Account> accounts) {
        // Mise à jour des comptes
        for (Flow f : flows) {
            accounts.get(f.getTargetAccountNumber()).processFlow(f);

            if (f instanceof Transfert t) {
                accounts.get(t.getOriginAccountNumber()).processFlow(f);
            }
        }

        Predicate<Account> isNegative = a -> a.getBalance() < 0;

        accounts.values().stream()
                .filter(isNegative)
                .findFirst()
                .ifPresent(a -> System.out.println("Compte n°" + a.getAccountNumber() + " à découvert : " + a.getBalance()));
    }

    // 2.1 Charger les flux depuis un JSON
    public static List<Flow> loadFlowsFromJson(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(new File(filePath), new TypeReference<List<Flow>>(){});
        } catch (Exception e) {
            System.err.println("Erreur lors de la lecture du JSON : " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // 2.2 Charger les comptes depuis un XML
    public static List<Account> loadAccountsFromXml(String filePath) {
        XmlMapper xmlMapper = new XmlMapper();
        try {
            return xmlMapper.readValue(new File(filePath), new TypeReference<List<Account>>(){});
        } catch (Exception e) {
            System.err.println("Erreur XML : " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
