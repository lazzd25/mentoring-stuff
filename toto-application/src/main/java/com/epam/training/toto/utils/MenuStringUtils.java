package com.epam.training.toto.utils;

public class MenuStringUtils {
    private final static String BREAK = "\n";
    private final static String NO_DATA = "There is no available data, press enter to quit!";
    private final static String INVALID_ARGUMENT = "Invalid argument, please try again!";
    private final static String MAIN_MENU = "Please type the number of the function:\n" +
            "    1. Show highest prize ever\n" +
            "    2. Show the distribution of results\n" +
            "    3. Calculate the results for a given bet\n" +
            "    4. Quit";
    private final static String HIGHEST_PRIZE_MENU = "Largest prize: %s Ft\n" +
            "Press Enter to continue!";
    private final static String DISTRIBUTION_MENU = "team #1 won: %1$s %%, team #2 won: %2$s %%, draw: %3$s %%\n" +
            "Press Enter to continue!";
    private final static String BET_DATE_MENU = "Enter date:";

    private final static String BET_OUTCOMES_MENU = "Enter outcomes (1/2/X):";

    private final static String BET_FULL_MENU = "Result: hits: %1$d, amount: %2$s\n" +
            "Press Enter to continue!";

    public static String getMainMenu() {
        return MAIN_MENU;
    }

    public static String getHighestPrizeMenu() {
        return HIGHEST_PRIZE_MENU;
    }

    public static String getDistributionMenu() {
        return DISTRIBUTION_MENU;
    }

    public static String getBetDateMenu() {
        return BET_DATE_MENU;
    }

    public static String getBetOutComesMenu() {
        return BET_OUTCOMES_MENU;
    }

    public static String getBetFullMenu() {
        return BET_FULL_MENU;
    }

    public static String getInvalidArgumentMessage() {
        return INVALID_ARGUMENT;
    }

    public static String getNoDataMessage() {
        return NO_DATA;
    }

    public static String concatErrorAndMenuMessage(final String errorMessage, final String menuMessage) {
        final StringBuilder builder = new StringBuilder();
        builder.append(BREAK);
        builder.append(errorMessage);
        builder.append(BREAK);
        builder.append(menuMessage);

        return builder.toString();
    }

}
