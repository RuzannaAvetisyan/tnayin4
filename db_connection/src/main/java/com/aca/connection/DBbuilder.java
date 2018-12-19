package com.aca.connection;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;


public interface DBbuilder extends Remote {
    String createDB(String user, String password) throws RemoteException;

    String createTable() throws RemoteException;
}
