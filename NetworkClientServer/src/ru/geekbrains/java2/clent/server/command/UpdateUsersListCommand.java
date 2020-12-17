package ru.geekbrains.java2.clent.server.command;

import java.io.Serializable;
import java.util.List;

// в данном классе можем передавать список пользователей в нашем приложении
public class UpdateUsersListCommand implements Serializable {

    private final List<String> users;

    public UpdateUsersListCommand(List<String> users) {
        this.users = users;
    }

    public List<String> getUsers() {
        return users;
    }
}
