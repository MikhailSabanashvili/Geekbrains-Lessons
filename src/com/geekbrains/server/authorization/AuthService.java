package com.geekbrains.server.authorization;

import java.sql.SQLException;

public interface AuthService {
    void start();
    String getNickNameByLoginAndPassword(String login, String password) throws SQLException;
    boolean loginIsBusy(String login) throws SQLException;
    void end();
    void addClient(String login, String password, String nickName) throws SQLException;
}
