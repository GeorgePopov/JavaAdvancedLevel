package ru.geekbrains.java2.clent.server.command;

import java.io.Serializable;

// каждый класс в папке command это команда
// команда по аутинтефикации, используем её, что бы отправить от клиента к серверу логин и пароль,
// а так же можем её переиспользовать и отправить от сервера к клиенту тот username который у нас есть
public class AuthCommand implements Serializable {

    private final String login;
    private final String password;

    private String username;

    public AuthCommand(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
