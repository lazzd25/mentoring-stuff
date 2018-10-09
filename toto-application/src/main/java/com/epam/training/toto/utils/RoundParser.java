package com.epam.training.toto.utils;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.Round;
import com.opencsv.CSVReader;

public class RoundParser {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd.");
    private static final int HIT_START_INDEX = 4;
    private static final int OUTCOME_START_INDEX = 14;

    public List<Round> parseRounds(final String fileName) {
        if (Objects.isNull(fileName)) {
            throw new IllegalArgumentException("filename is null");
        }

        final List<Round> rounds = new ArrayList<>();
        String[] nextLine;
        try {
            final CSVReader reader = new CSVReader(new FileReader(fileName));
            boolean readingFinished = false;
            while (!readingFinished) {
                try {
                    if ((nextLine = reader.readNext()) == null) {
                        readingFinished = true;
                    }
                    else {
                        final String[] line = nextLine[0].trim().split(";");
                        rounds.add(createRound(line));
                    }
                }
                catch (final Exception e) {
                }
            }
            reader.close();
        }
        catch (final Exception e) {
        }
        return rounds;

    }

    private Round createRound(final String[] line) {
        final int roundNumber = Integer.parseInt(line[2]);
        final LocalDate date = LocalDate.parse(line[3], FORMATTER);

        return new Round(roundNumber, date, createHitList(line), createOutcomeList(line));
    }

    private List<Hit> createHitList(final String[] line) {
        int hitCounter = 14;
        int index = HIT_START_INDEX;
        final List<Hit> hitList = new ArrayList<>();

        while (hitCounter > 9) {
            hitList.add(new Hit(hitCounter, Integer.parseInt(line[index++]), line[index++]));
            hitCounter--;
        }

        return hitList;
    }

    private List<Outcome> createOutcomeList(final String[] line) {
        int index = OUTCOME_START_INDEX;
        final List<Outcome> outcomeList = new ArrayList<>();

        while (index < 28) {
            outcomeList.add(Outcome.convertStringToOutcome(line[index++]));
        }

        return outcomeList;
    }

}
