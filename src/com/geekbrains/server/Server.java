package com.geekbrains.server;

import com.geekbrains.CommonConstants;
import com.geekbrains.server.authorization.AuthService;
import com.geekbrains.server.authorization.AuthServiceImpl;
import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private final AuthService authService;
    private final static Logger logger = LoggerFactory.getLogger(Server.class);
    static {
        PropertyConfigurator.configure("log4j.properties");
    }

    private List<ClientHandler> connectedUsers;

    public Server() {
        authService = new AuthServiceImpl();
        try (ServerSocket server = new ServerSocket(CommonConstants.SERVER_PORT)) {
            authService.start();
            logger.info("Authentical service started");
            connectedUsers = new ArrayList<>();
            while (true) {
                logger.info("Server is waiting for client to connect");
                Socket socket = server.accept();
                logger.info("Client is connected");
                new ClientHandler(this, socket);
            }
        } catch (IOException exception) {
            logger.error("Server error: {}", exception.getMessage());
            exception.printStackTrace();
        } finally {
            if (authService != null) {
                authService.end();
            }
        }
    }

    public AuthService getAuthService() {
        return authService;
    }

    public boolean isNickNameBusy(String nickName) {
        for (ClientHandler handler : connectedUsers) {
            if (handler.getNickName().equals(nickName)) {
                return true;
            }
        }

        return false;
    }

    public synchronized void broadcastMessage(String message) {
        for (ClientHandler handler : connectedUsers) {
            handler.sendMessage(message);
        }
    }

    public synchronized void addConnectedUser(ClientHandler handler) {
        connectedUsers.add(handler);
    }

    public synchronized void disconnectUser(ClientHandler handler) {
        connectedUsers.remove(handler);
    }

    public String getClients() {
        StringBuilder builder = new StringBuilder("/clients ");
        for (ClientHandler user : connectedUsers) {
            builder.append(user.getNickName()).append("\n");
        }

        return builder.toString();
    }

    public boolean isLoginBusy(String login) throws SQLException {
        return authService.loginIsBusy(login);
    }

    public void addClient(String login, String password, String nickName) throws SQLException {
        authService.addClient(login, password, nickName);
    }
}
