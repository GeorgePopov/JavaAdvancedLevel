package ru.geekbrains.java2.server.networkserver.auth;

import java.util.*;

public class BaseAuthService implements AuthService {

    // клас обёртка над двумя полями login и password
    private static class AuthEntry {

        private final String login; // private можно не указывать т.к. все переменные внутреннего класса доступны внешнему
        private final String password;

        public AuthEntry(String login, String password) {
            this.login = login;
            this.password = password;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AuthEntry authEntry = (AuthEntry) o;
            return Objects.equals(login, authEntry.login) &&
                    Objects.equals(password, authEntry.password);
        }

        @Override
        public int hashCode() {
            return Objects.hash(login, password);
        }
    }

    private static Map<AuthEntry, String> methodForNICKNAME_BY_LOGIN_AND_PASS() {
        Map<AuthEntry, String> NICKNAME_BY_LOGIN_AND_PASS = new HashMap<>();
        NICKNAME_BY_LOGIN_AND_PASS.put(new AuthEntry("login1", "pass1"), "nickname1");
        NICKNAME_BY_LOGIN_AND_PASS.put(new AuthEntry("login2", "pass2"), "nickname2");
        NICKNAME_BY_LOGIN_AND_PASS.put(new AuthEntry("login3", "pass3"), "nickname3");
        return NICKNAME_BY_LOGIN_AND_PASS;
    }

    @Override
    public String getNickByLoginAndPassword(String login, String password) {
        return methodForNICKNAME_BY_LOGIN_AND_PASS().get(new AuthEntry(login, password)); // получаем nickname по ключу
    }

    @Override
    public void start() {
        System.out.println("Auth service has been started");
    }

    @Override
    public void stop() {
        System.out.println("Auth service has been stopped");
    }
}