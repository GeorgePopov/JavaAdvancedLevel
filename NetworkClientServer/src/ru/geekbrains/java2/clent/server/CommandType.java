package ru.geekbrains.java2.clent.server;

public enum CommandType { // необходимые типы команд
    AUTH,
    AUTH_ERROR,
    PRIVATE_MESSAGE,
    BROADCAST_MESSAGE,
    MESSAGE,
    UPDATE_USERS_LIST,
    ERROR,
    END,
}
