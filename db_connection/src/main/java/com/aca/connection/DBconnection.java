package com.aca.connection;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DBconnection extends Remote {
    String createUser(String firstName, String lastName, String eMail, int phoneNumber,
                      String user, String password) throws RemoteException;
    String listUser(String user, String password)throws RemoteException;
}
