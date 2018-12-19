package com.aca.server;

import com.aca.connection.DBbuilder;
import com.aca.connection.DBconnection;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;


public class Server {
    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            DBconnection dbConnection = new DBconnectionImpl();
            DBbuilder dbBuilder = new DBbuilderImpl();
            registry.bind("0", dbConnection);
            registry.bind("1", dbBuilder);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }
    }
}
