package com.example.ludo;

import java.sql.*;

import static com.example.ludo.Account.accounts;


public class DBManager {


    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc", "root", "123456");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static Statement statement;

    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void filler() throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM jdbc.accounts");

            while (resultSet.next()) {
            Account account = new Account();
            account.id = Integer.parseInt(resultSet.getString("id"));
            account.username = resultSet.getString("username");
            account.password =  resultSet.getString("password");
            account.points =  Integer.parseInt(resultSet.getString("points"));
            accounts.add(account);
        }

    }

    public static void sort() throws SQLException {
        int counter = 1;
        for (Account account: accounts) {
            statement.execute("UPDATE jdbc.accounts set id = '" + counter + "' where id = '" + account.id + "'");            // updating table
            account.id = counter ;
            counter++;
        }
    }

    public static void insert(String username, String password) throws SQLException {
        int id = accounts.size() + 1;
        Account account = new Account();

        statement.executeUpdate("INSERT into jdbc.accounts(id, username, password) VALUES ('" + id + "', '" + username + "', '" + password + "')");
        account.id = id ;
        account.username = username ;
        account.password = password ;
        account.points = 0 ;
        accounts.add(account);
    }

    public static void delete(String username) throws SQLException {
        int index = 0;
        for(Account account : accounts) {

            if (account.username.equals(username)) {
                statement.execute("DELETE from jdbc.accounts where id = '" + account.id + "'");
                break;
            }

        }
        accounts.removeAll(accounts);
        filler();
        sort();

    }
}