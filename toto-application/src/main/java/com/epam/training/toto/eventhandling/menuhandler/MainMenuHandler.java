package com.epam.training.toto.eventhandling.menuhandler;

import java.util.Arrays;
import java.util.Formatter;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.epam.training.toto.domain.WinDistribution;
import com.epam.training.toto.eventhandling.MenuType;
import com.epam.training.toto.service.ITotoService;
import com.epam.training.toto.utils.MenuStringUtils;
import com.epam.training.toto.utils.NumberFormatter;

public class MainMenuHandler extends MenuHandlerBase {

    private final static String HIGHEST_PRIZE_COMMAND = "1";
    private final static String DISTRIBUTION_OF_RESULTS_COMMAND = "2";
    private final static String BET_COMMAND = "3";
    private final static String QUIT_COMMAND = "4";
    private final static Set<String> AVAILABLE_COMMANDS = new HashSet<String>(Arrays.asList(HIGHEST_PRIZE_COMMAND, DISTRIBUTION_OF_RESULTS_COMMAND, BET_COMMAND, QUIT_COMMAND));

    public MainMenuHandler(final ITotoService totoService) {
        super(totoService);
    }

    @Override
    public CommandResult executeCommand(final String command) {
        if (Objects.isNull(command)) {
            throw new IllegalArgumentException("command is null");
        }
        if (isMainMenuUserInputValid(command)) {
            return handleValidCommand(command);
        }
        return new CommandResult(
                MenuType.MAIN,
                MenuStringUtils.concatErrorAndMenuMessage(MenuStringUtils.getInvalidArgumentMessage(), MenuStringUtils.getMainMenu()));
    }

    private void quit() {
        System.exit(0);
    }

    private boolean isMainMenuUserInputValid(final String userInput) {
        return AVAILABLE_COMMANDS.contains(userInput);
    }

    private CommandResult handleValidCommand(final String command) {
        if (HIGHEST_PRIZE_COMMAND.equals(command)) {
            return new CommandResult(MenuType.HIGHEST_PRIZE, convertHighPrizeResult(totoService.getLargestPrize()));

        }
        if (DISTRIBUTION_OF_RESULTS_COMMAND.equals(command)) {
            return new CommandResult(MenuType.DISTRIBUTION, convertDistributionResult(totoService.getWinDistribution()));

        }
        if (BET_COMMAND.equals(command)) {
            return new CommandResult(MenuType.BET, MenuStringUtils.getBetDateMenu());

        }
        else if (QUIT_COMMAND.equals(command)) {
            quit();

        }
        throw new IllegalStateException();

    }

    @SuppressWarnings("resource")
    private String convertHighPrizeResult(final int highestPrize) {
        final StringBuilder resultBuilder = new StringBuilder();

        new Formatter(resultBuilder).format(MenuStringUtils.getHighestPrizeMenu(), NumberFormatter.formatNumber(highestPrize));

        return resultBuilder.toString();
    }

    @SuppressWarnings("resource")
    private String convertDistributionResult(final WinDistribution winDistribution) {
        final StringBuilder resultBuilder = new StringBuilder();
        new Formatter(resultBuilder).format(
                MenuStringUtils.getDistributionMenu(),
                NumberFormatter.formatNumber(winDistribution.getFirst()),
                NumberFormatter.formatNumber(winDistribution.getSecond()),
                NumberFormatter.formatNumber(winDistribution.getDraw()));

        return resultBuilder.toString();
    }

}
