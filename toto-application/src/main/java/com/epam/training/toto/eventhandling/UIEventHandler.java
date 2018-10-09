package com.epam.training.toto.eventhandling;

import java.util.Objects;

import com.epam.training.toto.eventhandling.menuhandler.BetMenuHandler;
import com.epam.training.toto.eventhandling.menuhandler.CommandResult;
import com.epam.training.toto.eventhandling.menuhandler.ICommandExecutor;
import com.epam.training.toto.eventhandling.menuhandler.MainMenuHandler;
import com.epam.training.toto.service.ITotoService;
import com.epam.training.toto.utils.MenuStringUtils;

public class UIEventHandler implements IEventHandler {

    private MenuType currentMenuOnDisplay;
    private final ITotoService totoService;
    private final ICommandExecutor mainMenuHandler;
    private final ICommandExecutor betMenuHandler;

    public UIEventHandler(final ITotoService totoService) {
        if (Objects.isNull(totoService)) {
            throw new IllegalArgumentException("totoservice is null");
        }
        this.totoService = totoService;
        mainMenuHandler = new MainMenuHandler(this.totoService);
        betMenuHandler = new BetMenuHandler(this.totoService);
    }

    @Override
    public String initApp() {
        if (!totoService.isDataAvailable()) {
            currentMenuOnDisplay = MenuType.NO_DATA;
            return MenuStringUtils.getNoDataMessage();
        }
        currentMenuOnDisplay = MenuType.MAIN;
        return MenuStringUtils.getMainMenu();
    }

    @Override
    public String handleEvent(final String userInput) {

        if (Objects.isNull(userInput)) {
            throw new IllegalArgumentException("userinput is null");
        }

        if (MenuType.MAIN.equals(currentMenuOnDisplay)) {
            return changeMenu(mainMenuHandler.executeCommand(userInput));
        }
        if (MenuType.HIGHEST_PRIZE.equals(currentMenuOnDisplay)) {
            return processEvent(
                    userInput,
                    (command) -> new CommandResult(MenuType.MAIN, MenuStringUtils.getMainMenu()));
        }
        if (MenuType.DISTRIBUTION.equals(currentMenuOnDisplay)) {
            return processEvent(
                    userInput,
                    (command) -> new CommandResult(MenuType.MAIN, MenuStringUtils.getMainMenu()));
        }
        if (MenuType.BET.equals(currentMenuOnDisplay)) {
            return changeMenu(betMenuHandler.executeCommand(userInput));
        }
        if (MenuType.NO_DATA.equals(currentMenuOnDisplay)) {
            System.exit(1);
        }

        throw new IllegalStateException();
    }

    private String processEvent(final String command, final ICommandExecutor commandExecutor) {
        return changeMenu(commandExecutor.executeCommand(command));
    }

    private String changeMenu(final CommandResult commandResult) {

        currentMenuOnDisplay = commandResult.getNextMenuToDisplay();
        return commandResult.getResultMessage();
    }
}
