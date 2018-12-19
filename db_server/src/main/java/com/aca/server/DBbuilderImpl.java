package com.aca.server;

import com.aca.connection.DBbuilder;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBbuilderImpl extends UnicastRemoteObject implements DBbuilder{
    protected static Connection connection;
    private PreparedStatement preparedStatement;

    protected DBbuilderImpl() throws RemoteException{

    }

    @Override
    public String createDB(String user, String password) throws RemoteException {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/?user="+user+"&password="+password);
            preparedStatement = connection.prepareStatement("create database if not exists rmi_db;");
            preparedStatement.execute();
            preparedStatement = connection.prepareStatement("use rmi_db;");
            preparedStatement.execute();
            return "DB rmi_db successfully created";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }

    @Override
    public String createTable() throws RemoteException {
        try {
            preparedStatement = connection.prepareStatement("create table if not exists users_rmi" +
                    " (id int auto_increment unique," +
                    " first_name text not null," +
                    " last_name text not null," +
                    " e_mail text not null, " +
                    " phone_number int(8),"+
                    " primary key (id));");
            preparedStatement.execute();
            return "Table users_rmi successfully created";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Something went wrong";
        }
    }
}
