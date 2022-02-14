package com.geekbrains;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoServer {
    private static volatile boolean isExit = true;
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("Сервер запущен");
            //Ожидаем клиента
            Socket socket = serverSocket.accept();
            System.out.println("Клиент подключился");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while(isExit) {
                            String messageFromClient = in.readUTF();
                            System.out.println(messageFromClient);
                            if (messageFromClient.equals("/end")) {
                                isExit = false;
                                out.writeUTF("/end");
                                break;
                            }
                        }
                            //я не очень понимаю, как должен реагировать сервер на отключение клиента. И на какой стороне нужно отключать клиента?

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
                            String text = scanner.next();
                            if(!isExit)
                                break;
                            out.writeUTF(text);
                        }
                        out.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
