package com.geekbrains.server.authorization;

import java.sql.*;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class DbConnector {
    private static Connection connection;
    private static Statement statement;
    private static DbConnector dbConnector;

    public static DbConnector getDbConnector() throws SQLException {
        dbConnector = new DbConnector();
        return dbConnector;
    }

    private DbConnector() throws SQLException {
        connect();
    }

    private static void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/chat",
                    "postgres", "postgres");
        statement = connection.createStatement();
    }

    public void insert(String login, String password, String nickName) throws SQLException {
        statement.executeUpdate(String.format("INSERT INTO client(login, password, nickname) values('%s', '%s', '%s');",
                    login, password, nickName));
    }

    public String select(String login, String password) throws NoSuchElementException {
        try(ResultSet resultSet = statement.executeQuery(String.format("Select nickName from client" +
                        " where login = '%s' and password = '%s';",
                login, password))) {
            if(resultSet.next())
                return resultSet.getString(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        throw new NoSuchElementException("No such client");
    }

    public HashMap<String, Client> findAll() throws SQLException {
        HashMap<String, Client> map = new HashMap<>();
        try (ResultSet rs = statement.executeQuery("select * from client;")) {
            while (rs.next()) {
                Client client = new Client(rs.getString(1), rs.getString(2), rs.getString(3));
                map.put(client.getLogin(), client);
            }
        }

        return map;
    }

    public boolean findLogin(String login) throws SQLException {
        try (ResultSet rs = statement.executeQuery(String.format("SELECT login FROM client where login = '%s';",
                login))) {
            while (rs.next()) {
                String loginDubl = rs.getString("login");
                if(loginDubl.equals(login))
                    return true;
            }
            return false;
        }

    }

    public void disconnect() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

}
