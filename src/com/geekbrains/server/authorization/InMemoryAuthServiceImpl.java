//package com.geekbrains.server.authorization;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class InMemoryAuthServiceImpl implements AuthService {
//    private final Map<String, Client> users;
//
//    public InMemoryAuthServiceImpl() {
//        users = new HashMap<>();
//        users.put("login1", new Client("login1", "password1", "first_user"));
//        users.put("login2", new Client("login2", "password2", "second_user"));
//        users.put("login3", new Client("login3", "password3", "third_user"));
//    }
//
//    @Override
//    public void start() {
//        System.out.println("Сервис аутентификации инициализирован");
//    }
//
//    @Override
//    public synchronized String getNickNameByLoginAndPassword(String login, String password) {
//        Client client = users.get(login);
//        // Ищем пользователя по логину и паролю, если нашли то возвращаем никнэйм
//        if (client != null && client.getPassword().equals(password)) {
//            return client.getNickName();
//        }
//
//        return null;
//    }
//
//    @Override
//    public void end() {
//        System.out.println("Сервис аутентификации отключен");
//    }
//}
