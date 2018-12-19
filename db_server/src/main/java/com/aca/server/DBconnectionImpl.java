package com.aca.server;

import com.aca.connection.DBconnection;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class DBconnectionImpl extends UnicastRemoteObject implements DBconnection {
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    protected DBconnectionImpl() throws RemoteException {
    }

    @Override
    public String createUser(String firstName, String lastName, String eMail, int phoneNumber,
                             String user, String password) {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi_db",user,password);
        } catch (SQLException e) {
            return "You need to create a database and a table first.";
        }
        String sql = "insert into users_rmi (first_name, last_name, e_mail, phone_number) values (?,?,?,?)";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);
            preparedStatement.setString(3,eMail);
            preparedStatement.setInt(4,phoneNumber);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "User added to the table.";
    }

    @Override
    public String listUser(String user, String password) throws RemoteException {
        String sql = "select * from users_rmi";
        String list = "";
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rmi_db",user,password);
            preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String eMail = resultSet.getString("e_mail");
                int phoneNumber = resultSet.getInt("phone_number");
                User u = new User(id, firstName, lastName, eMail, phoneNumber);
                list += u.toString();
            }
            return list;
        } catch (SQLException e) {
            return "You need to create a database and a table first.";
        }
    }
}
