package ru.geekbrains.java2.client.controller;

@FunctionalInterface
public interface AuthEvent {

    void authIsSuccessful(String nickname); // метод об успешно пройденной аутентификации
}
