package com.epam.training.toto.eventhandling.menuhandler;

import java.util.Objects;

import com.epam.training.toto.eventhandling.MenuType;

public class CommandResult {

    private final MenuType nextMenuToDisplay;
    private final String resultMessage;

    public CommandResult(final MenuType nextMenuToDisplay, final String resultMessage) {
        if (Objects.isNull(nextMenuToDisplay)) {
            throw new IllegalArgumentException("nextMenuToDisplay is null");
        }
        if (Objects.isNull(resultMessage)) {
            throw new IllegalArgumentException("resultMessage is null");
        }
        this.nextMenuToDisplay = nextMenuToDisplay;
        this.resultMessage = resultMessage;
    }

    public MenuType getNextMenuToDisplay() {
        return nextMenuToDisplay;
    }

    public String getResultMessage() {
        return resultMessage;
    }

}
