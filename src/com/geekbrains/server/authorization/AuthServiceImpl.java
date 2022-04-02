package com.geekbrains.server.authorization;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.Map;

public class AuthServiceImpl implements AuthService {
    private final static Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    static {
        PropertyConfigurator.configure("log4j.properties");
    }
    private static DbConnector dbConnector;

    public static Map<String, Client> getUsers() throws SQLException {
        Map<String, Client> users = dbConnector.findAll();
        logger.info("List users: {}", users.toString());
        return users;
    }

    @Override
    public void start() {
        try {
            dbConnector = DbConnector.getDbConnector();
            logger.info("Auth service is enable");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getNickNameByLoginAndPassword(String login, String password) {
        String resultExecute = dbConnector.select(login, password);
        logger.info("Get nickname: {}", resultExecute);
        return resultExecute;
    }

    @Override
    public void end() {
        try {
            dbConnector.disconnect();
            logger.info("Auth service is shutdown");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean loginIsBusy(String login) throws SQLException {
        boolean resultExecute = dbConnector.findLogin(login);
        logger.info("Login is busy: {}", resultExecute);
        return resultExecute;
    }

    @Override
    public void addClient(String login, String password, String nickName) {
        try {
            logger.info("Insert login, password, nickname: {}, {}, {}", login, password, nickName);
            dbConnector.insert(login, password, nickName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
