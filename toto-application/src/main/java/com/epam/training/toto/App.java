package com.epam.training.toto;

import java.util.Objects;
import java.util.Scanner;

import com.epam.training.toto.eventhandling.IEventHandler;

public class App implements IApp {

    private final IEventHandler eventHandler;

    public App(final IEventHandler eventHandler) {
        if (Objects.isNull(eventHandler)) {
            throw new IllegalArgumentException("EvenHandler is null");
        }

        this.eventHandler = eventHandler;
    }

    public void startApplication() {

        final boolean isApplicationStarted = true;
        final Scanner scanner = new Scanner(System.in);

        showMenu(eventHandler.initApp());

        while (isApplicationStarted) {
            final String userInput = readUserInput(scanner);
            showMenu(eventHandler.handleEvent((userInput)));
        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void showMenu(final String message) {
        System.out.println(message);
    }

    private String readUserInput(final Scanner scanner) {
        return scanner.nextLine();
    }
}
