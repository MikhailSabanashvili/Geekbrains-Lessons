package com.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private final static String SERVER_ADDRESS = "localhost";
    private final static int SERVER_PORT = 8080;
    private static volatile boolean isExit = true;

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    public EchoClient() {
        try {
            openConnection();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isExit) {
                        String messageFromServer = in.readUTF();
                        if(messageFromServer.equals("/end")) {
                            closeConnection();
                            isExit = false;
                            break;
                        }
                        System.out.println(messageFromServer);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isExit) {
                        Scanner scanner = new Scanner(System.in);
                        String text = scanner.nextLine();
                        if(!isExit)
                            break;
                        sendMessage(text + "\r");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void closeConnection() {
        try {
            in.close();
            out.close();
            socket.close();
            System.out.println("Клиент завершил работу");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void sendMessage(String text) {
        try {
            out.writeUTF(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        EchoClient echoClient = new EchoClient();
    }
}
