package com.epam.training.toto.eventhandling.menuhandler;

import java.util.Objects;

import com.epam.training.toto.service.ITotoService;

public class MenuHandlerBase implements ICommandExecutor {

    protected final ITotoService totoService;

    public MenuHandlerBase(final ITotoService totoService) {
        super();
        if (Objects.isNull(totoService)) {
            throw new IllegalArgumentException("totoservice is null");
        }
        this.totoService = totoService;
    }

    @Override
    public CommandResult executeCommand(final String commands) {
        // TODO Auto-generated method stub
        return null;
    }
}
