package com.epam.training.toto.main;

import com.epam.training.toto.App;
import com.epam.training.toto.IApp;
import com.epam.training.toto.eventhandling.IEventHandler;
import com.epam.training.toto.eventhandling.UIEventHandler;
import com.epam.training.toto.service.ITotoService;
import com.epam.training.toto.service.TotoService;

/**
 * Hello world!
 *
 */
public class Main {
    public static void main(final String[] args) {
        final ITotoService totoService = new TotoService("toto.csv");
        final IEventHandler eventHandler = new UIEventHandler(totoService);
        final IApp application = new App(eventHandler);
        application.startApplication();
    }
}
