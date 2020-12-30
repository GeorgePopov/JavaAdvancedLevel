package ru.geekbrains.java2.clent.server.command;

import java.io.Serializable;

// данный класс важно разделять по типам, что бы потом можно было разделять логику
public class ErrorCommand implements Serializable {

    private final String errorMessage;

    public ErrorCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
