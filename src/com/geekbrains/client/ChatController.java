package com.geekbrains.client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class ChatController implements Initializable {
    public HBox authPanel;
    public HBox authorizationPanel;
    private final Network network;
    public TextArea textArea;
    public ListView<String> clientList;
    public HBox messagePanel;
    public TextField messageField;
    public TextField loginFieldAuth;
    public PasswordField passwordFieldAuth;
    public TextField loginFieldAuthorization;
    public PasswordField passwordFieldAuthorization;
    public TextField nickNameFieldAuthorization;

    public ChatController() {
        this.network = new Network(this);
    }

    public void setAuthenticated(boolean authenticated) {
        authorizationPanel.setVisible(false);
        authorizationPanel.setManaged(false);

        authPanel.setVisible(!authenticated);
        authPanel.setManaged(!authenticated);
        messagePanel.setVisible(authenticated);
        messagePanel.setManaged(authenticated);
        clientList.setVisible(authenticated);
        clientList.setManaged(authenticated);
    }

    public void setAuthorization(boolean authorization) {
        if(!authorization) {
            authPanel.setVisible(false);
            authPanel.setManaged(false);
            authorizationPanel.setVisible(true);
            authorizationPanel.setManaged(true);
            messagePanel.setVisible(false);
            messagePanel.setManaged(false);
            clientList.setVisible(false);
            clientList.setManaged(false);
        } else {
            authPanel.setVisible(false);
            authPanel.setManaged(false);
            authorizationPanel.setVisible(false);
            authorizationPanel.setManaged(false);
            messagePanel.setVisible(true);
            messagePanel.setManaged(true);
            clientList.setVisible(true);
            clientList.setManaged(true);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAuthenticated(false);
    }

    public void displayMessage(String text) {
        if (textArea.getText().isEmpty()) {
            textArea.setText(text);
        } else {
            textArea.setText(textArea.getText() + "\n" + text);
        }
    }

    public void displayClient(String nickName) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clientList.getItems().add(nickName);
            }
        });
    }

    public void removeClient(String nickName) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                clientList.getItems().remove(nickName);
            }
        });
    }


    public void sendAuth(ActionEvent event) {
        boolean authenticated = network.sendAuth(loginFieldAuth.getText(), passwordFieldAuth.getText());
        loginFieldAuth.clear();
        passwordFieldAuth.clear();
        if(authenticated) {
            setAuthenticated(true);
        }
        else {
            setAuthorization(false);
        }
    }

    public void sendAuthorization(ActionEvent actionEvent) {
        boolean authorized = network
                .sendAuthorization(loginFieldAuthorization.getText(), passwordFieldAuthorization.getText(), nickNameFieldAuthorization.getText());
        loginFieldAuthorization.clear();
        passwordFieldAuthorization.clear();
        if(authorized) {
            setAuthorization(true);
        }
    }

    public void sendMessage(ActionEvent event) {
        network.sendMessage(messageField.getText());
        messageField.clear();
    }

    public void close() {
        network.closeConnection();
    }

}
