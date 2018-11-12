package com.epam.training.toto.main;

import java.util.Objects;

import com.epam.training.toto.App;
import com.epam.training.toto.IApp;
import com.epam.training.toto.eventhandling.IEventHandler;
import com.epam.training.toto.eventhandling.UIEventHandler;
import com.epam.training.toto.service.ITotoService;
import com.epam.training.toto.service.TotoService;


public class Main {
	private final static int PATH_INDEX = 0;
	
    public static void main(final String[] args) {
        if (Objects.nonNull(args) && args.length > 0) {            
            final ITotoService totoService = new TotoService(args[PATH_INDEX]);
            final IEventHandler eventHandler = new UIEventHandler(totoService);
            final IApp application = new App(eventHandler);
            application.startApplication();

        }
    }
}
