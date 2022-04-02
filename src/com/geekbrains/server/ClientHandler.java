package com.geekbrains.server;

import org.apache.log4j.PropertyConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.NoSuchElementException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream inputStream;
    private final DataOutputStream outputStream;
    private ExecutorService service;
    private final static Logger logger = LoggerFactory.getLogger(ClientHandler.class);
    static {
        PropertyConfigurator.configure("log4j.properties");
    }

    private String nickName;

    public String getNickName() {
        return nickName;
    }

    public ClientHandler(Server server, Socket socket) {
        service = Executors.newFixedThreadPool(4);
        try {
            this.server = server;
            this.socket = socket;
            this.inputStream = new DataInputStream(socket.getInputStream());
            this.outputStream = new DataOutputStream(socket.getOutputStream());
            service.execute(this::clientWorker);

        } catch (IOException exception) {
            logger.error("ClientHandler creating error: {}", exception.getMessage());
            throw new RuntimeException("Проблемы при создании обработчика");
        }
//        } finally {
//            service.shutdownNow();
//            try {
//                if (!service.awaitTermination(100, TimeUnit.MICROSECONDS)) {
//                    System.exit(0);
//                }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
    }

    private void clientWorker() {
        try {
            authentication();
            readMessages();
        } catch (IOException | NoSuchElementException | SQLException exception) {
            exception.printStackTrace();
            if (exception instanceof NoSuchElementException) {
                try {
                    authorization();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        readMessages();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    private void authorization() throws IOException {
        outputStream.writeBoolean(false);
        while(true) {
            String message = inputStream.readUTF();
            logger.info("Process authorization is running");
            if (message.startsWith(ServerCommandConstants.AUTHORIZATION)) {
                String[] authorizationInfo = message.split(" ");
                String login = authorizationInfo[1];
                String password = authorizationInfo[2];
                String nickName = authorizationInfo[3];
                if (nickName != null && login != null && password != null) {
                    try {
                        if (!server.isNickNameBusy(nickName) || !server.isLoginBusy(login)) {
                            //если такого пользака нет, то создать - внести в базу
                            server.addClient(login, password, nickName);
                            sendAuthorizationMessage(true);
                            this.nickName = nickName;
                            server.broadcastMessage(nickName + " зашел в чат");
                            sendMessage(server.getClients());
                            server.addConnectedUser(this);
                            logger.info("Process authorization is finished");
                            return;
                        } else {
                            sendAuthorizationMessage(false);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                        logger.error("Authorization failed: {}", e.getMessage());
                        sendAuthorizationMessage(false);
                    }
                } else {
                    sendAuthorizationMessage(false);
                }
            }
        }
    }

    private void sendAuthorizationMessage(boolean authorized) throws IOException {
        outputStream.writeBoolean(authorized);
    }

    public void authentication() throws IOException, SQLException {
        while (true) {
            String message = inputStream.readUTF();
            logger.info("Process authentication is running");
            if (message.startsWith(ServerCommandConstants.AUTHENTICATION)) {
                String[] authInfo = message.split(" ");
                String nickName = server.getAuthService().getNickNameByLoginAndPassword(authInfo[1], authInfo[2]);
                if (nickName != null) {
                    if (!server.isNickNameBusy(nickName)) {
                        sendAuthenticationMessage(true);
                        this.nickName = nickName;
                        server.broadcastMessage(nickName + " зашел в чат");
                        logger.info("Joined the chat: {}", nickName);
                        sendMessage(server.getClients());
                        server.addConnectedUser(this);
                        logger.info("Process authentication is finished");
                        return;
                    } else {
                        sendAuthenticationMessage(false);
                    }
                } else {
                    sendAuthenticationMessage(false);
                }
            }
        }
    }

    private void sendAuthenticationMessage(boolean authenticated) throws IOException {
        outputStream.writeBoolean(authenticated);
    }

    private void readMessages() throws IOException {
        while (true) {
            String messageInChat = inputStream.readUTF();
            logger.info("Message from {} : {}", nickName, messageInChat);
            if (messageInChat.equals(ServerCommandConstants.EXIT)) {
                closeConnection();
                return;
            }

            server.broadcastMessage(nickName + ": " + messageInChat);
        }
    }

    public void sendMessage(String message) {
        try {
            logger.info("Send message: {}", message);
            outputStream.writeUTF(message);
        } catch (IOException exception) {
            logger.error("Send message error: {}", exception.getMessage());
            exception.printStackTrace();
        }
    }

    private void closeConnection() {
        server.disconnectUser(this);
        server.broadcastMessage(ServerCommandConstants.EXIT + " " + nickName);
        try {
            outputStream.close();
            inputStream.close();
            socket.close();
            service.shutdownNow();
            try {
                if (!service.awaitTermination(100, TimeUnit.MICROSECONDS)) {
                    System.exit(0);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("All connections are closed");
        } catch (IOException exception) {
            logger.error("Connection closed error: {}", exception.getMessage());
            exception.printStackTrace();
        }
    }
}
