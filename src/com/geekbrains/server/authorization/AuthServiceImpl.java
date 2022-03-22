package com.geekbrains.server.authorization;

import java.sql.SQLException;
import java.util.Map;

public class AuthServiceImpl implements AuthService {
    private static DbConnector dbConnector;

    public static Map<String, Client> getUsers() throws SQLException {
         return dbConnector.findAll();
    }

    @Override
    public void start() {
        try {
            dbConnector = DbConnector.getDbConnector();
            System.out.println("Сервис аутентификации инициализирован");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getNickNameByLoginAndPassword(String login, String password) {
        return dbConnector.select(login, password);
    }

    @Override
    public void end() {
        try {
            dbConnector.disconnect();
            System.out.println("Сервис аутентификации отключен");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginIsBusy(String login) throws SQLException {
        return dbConnector.findLogin(login);
    }

    @Override
    public void addClient(String login, String password, String nickName) {
        try {
            dbConnector.insert(login, password, nickName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateNickName(String oldNickName, String newNickName) throws SQLException {
        dbConnector.update(oldNickName, newNickName);
    }

}
