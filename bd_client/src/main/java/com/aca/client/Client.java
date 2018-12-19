package com.aca.client;

import com.aca.connection.DBbuilder;
import com.aca.connection.DBconnection;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Enter your username to connect to the database:");
        final String user = scanner.nextLine();
        System.out.print("Enter your password to connect to the database:");
        final String password = scanner.nextLine();

        while (true){
            System.out.println("Enter command");
            System.out.println("(B) - Create a database and table.");
            System.out.println("(U) - Register user.");
            System.out.println("(L) - List users.");
            System.out.println("(E) - Exit.");

            String command = scanner.nextLine();

            switch (command){
                case "B":
                    createDB(user,password);
                    break;
                case "U":
                    createUser(user,password);
                    break;
                case "L":
                    listUser(user,password);
                    break;
                case "E":
                    return;
            }
        }
    }

    private static void listUser(String user, String password) {
        try {
            DBconnection dbConnection = (DBconnection)Naming.lookup("rmi://localhost/0");
            System.out.println(dbConnection.listUser(user, password));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void createUser(String user, String password) {
        System.out.print("First name: ");
        String firstName = scanner.nextLine();
        System.out.print("Last name: ");
        String lastName = scanner.nextLine() ;
        System.out.print("E-mail: ");
        String eMail = scanner.nextLine();
        System.out.print("Phone number: ");
        int phoneNumber = Integer.parseInt(scanner.nextLine());
        try {
            DBconnection dbConnection = (DBconnection)Naming.lookup("rmi://localhost/0");
            System.out.println(dbConnection.createUser(firstName, lastName, eMail, phoneNumber, user, password));
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    private static void createDB(String user, String password) {
        try {
            DBbuilder dbBuilder = (DBbuilder) Naming.lookup("rmi://localhost/1");
            System.out.println(dbBuilder.createDB(user,password));
            System.out.println(dbBuilder.createTable());
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
