package com.epam.training.toto.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Objects;

public class NumberFormatter {
    private final static String NUMBER_FORMATTER_PATTERN = "###,###.##";
    private final static DecimalFormatSymbols symbols = new DecimalFormatSymbols();

    public static String formatNumber(final Number number) {
        if (Objects.isNull(number)) {
            throw new IllegalArgumentException("number is null");
        }
        symbols.setGroupingSeparator(' ');
        return new DecimalFormat(NUMBER_FORMATTER_PATTERN, symbols).format(number);
    }
}
