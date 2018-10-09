package com.epam.training.toto.eventhandling.menuhandler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.eventhandling.MenuType;
import com.epam.training.toto.service.ITotoService;
import com.epam.training.toto.utils.MenuStringUtils;

public class BetMenuHandler extends MenuHandlerBase {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
    private final static String VALIDATOR_REGEX = "[X1-2]{14}";

    private SubMenuType currentSubMenu;
    private LocalDate dateOfBet;

    public BetMenuHandler(final ITotoService totoService) {
        super(totoService);
        currentSubMenu = SubMenuType.BET_RESULT_DATE;
    }

    @Override
    public CommandResult executeCommand(final String command) {

        if (Objects.isNull(command)) {
            throw new IllegalArgumentException("command is null");
        }
        if (SubMenuType.BET_RESULT_DATE.equals(currentSubMenu)) {
            return handleDateMenu(command);
        }
        else if (SubMenuType.BET_RESULT_OUTCOMES.equals(currentSubMenu)) {
            return handleOutcomesMenu(command);
        }
        else if (SubMenuType.BET_RESULT_FULL.equals(currentSubMenu)) {
            currentSubMenu = SubMenuType.BET_RESULT_DATE;
            return new CommandResult(MenuType.MAIN, MenuStringUtils.getMainMenu());
        }

        throw new IllegalStateException();
    }

    private CommandResult handleDateMenu(final String date) {
        if (isDateValid(date)) {
            currentSubMenu = SubMenuType.BET_RESULT_OUTCOMES;
            return new CommandResult(MenuType.BET, MenuStringUtils.getBetOutComesMenu());
        }
        return new CommandResult(MenuType.BET, MenuStringUtils.concatErrorAndMenuMessage(MenuStringUtils.getInvalidArgumentMessage(), MenuStringUtils.getBetDateMenu()));
    }

    private CommandResult handleOutcomesMenu(final String command) {
        if (isOutcomeValid(command)) {
            currentSubMenu = SubMenuType.BET_RESULT_FULL;
            return new CommandResult(
                    MenuType.BET,
                    convertHitResult(totoService.getHitForDate(dateOfBet, convertStringtoOucomesList(command))));
        }
        return new CommandResult(MenuType.BET, MenuStringUtils.concatErrorAndMenuMessage(MenuStringUtils.getInvalidArgumentMessage(), MenuStringUtils.getBetOutComesMenu()));
    }

    private boolean isDateValid(final String dateString) {

        try {
            dateOfBet = LocalDate.parse(dateString, FORMATTER);
        }
        catch (final DateTimeParseException e) {
            return false;
        }
        return totoService.isDateInFile(dateOfBet);
    }

    private boolean isOutcomeValid(final String command) {
        if (Objects.isNull(command)) {
            throw new IllegalArgumentException("outcomes is null");
        }
        return command.matches(VALIDATOR_REGEX);
    }

    @SuppressWarnings("resource")
    private String convertHitResult(final Hit hit) {
        final StringBuilder resultBuilder = new StringBuilder();
        new Formatter(resultBuilder).format(
                MenuStringUtils.getBetFullMenu(),
                hit.getHits(),
                hit.getPrice());

        return resultBuilder.toString();
    }

    private List<Outcome> convertStringtoOucomesList(final String outcomes) {

        return Arrays.asList(outcomes.split("")).stream().map(Outcome::convertStringToOutcome).collect(Collectors.toList());
    }

}
