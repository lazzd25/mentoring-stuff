package com.epam.training.toto.domain;

import static org.apache.commons.lang3.StringUtils.remove;

public enum Outcome {
    ONE,
    TWO,
    X;

    private final static String STRING_X = "X";
    private final static String STRING_1 = "1";
    private final static String STRING_2 = "2";

    public static Outcome convertStringToOutcome(String outcomeString) {
        outcomeString = clearBonusOutcome(outcomeString.toUpperCase());

        if (outcomeString.matches("[X1-2]{1}")) {
            if (STRING_X.equals(outcomeString)) {
                return X;
            }
            if (STRING_1.equals(outcomeString)) {
                return Outcome.ONE;
            }
            if (STRING_2.equals(outcomeString)) {
                return Outcome.TWO;
            }
        }
        throw new IllegalArgumentException("invalid outcome");
    }

    private static String clearBonusOutcome(String outcomeString) {
        if (outcomeString.contains("+")) {
            outcomeString = remove(outcomeString, "+");
        }
        return outcomeString;
    }

}
