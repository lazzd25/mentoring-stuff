package com.epam.training.toto.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class Round {
    private final int roundNumber;
    private final LocalDate date;
    private final List<Hit> hits;
    private final List<Outcome> outcomes;

    public Round(final int roundNumber, final LocalDate date, final List<Hit> hits, final List<Outcome> outcomes) {
        super();
        this.roundNumber = roundNumber;
        this.date = date;
        this.hits = hits;
        this.outcomes = outcomes;
    }

    public int getRoundNumber() {
        return roundNumber;
    }

    public LocalDate getDate() {
        return date;
    }

    public List<Hit> getHits() {
        return hits;
    }

    public List<Outcome> getOutcomes() {
        return outcomes;
    }

    public int calculateOutcomeMatches(final List<Outcome> outcomesToCompare) {
        if (Objects.isNull(outcomesToCompare)) {
            throw new IllegalArgumentException("outcomes is null");
        }

        int matchCounter = 0;

        for (int i = 0; i < outcomesToCompare.size(); i++) {
            if (i < outcomes.size() && outcomes.get(i).equals(outcomesToCompare.get(i))) {
                matchCounter++;
            }
        }

        return matchCounter;
    }

}
