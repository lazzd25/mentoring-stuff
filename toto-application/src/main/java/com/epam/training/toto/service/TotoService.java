package com.epam.training.toto.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.Round;
import com.epam.training.toto.domain.WinDistribution;
import com.epam.training.toto.utils.RoundParser;

public class TotoService implements ITotoService {

    private static final double PRECENTAGE_MULTIPLIER = 100;
    private static final String ZERO_PRICE = "0 Ft";
    private final List<Round> rounds;

    public TotoService(final String roundFileName) {
        this.rounds = new RoundParser().parseRounds(roundFileName);
    }

    @Override
    public int getLargestPrize() {
        return rounds.stream()
                .mapToInt(
                        (round) -> round.getHits().stream()
                                .mapToInt(Hit::getPriceInIntFromHit)
                                .max().orElse(0))
                .max().orElse(0);

    }

    @Override
    public WinDistribution getWinDistribution() {

        return calculateWinDistribution();
    }

    @Override
    public Hit getHitForDate(final LocalDate date, final List<Outcome> outcomes) {
        if (Objects.isNull(date)) {
            throw new IllegalArgumentException("date is null");
        }
        if (Objects.isNull(outcomes)) {
            throw new IllegalArgumentException("date is null");
        }

        final Optional<Round> round = findRoundForDate(date);
        if (round.isPresent()) {
            return calculateHit(round.get(), outcomes);
        }
        throw new IllegalArgumentException("no data for this date");
    }

    @Override
    public boolean isDateInFile(final LocalDate date) {
        if (Objects.isNull(date)) {
            throw new IllegalArgumentException("date is null");
        }
        return findRoundForDate(date).isPresent();
    }

    private Hit calculateHit(final Round round, final List<Outcome> outcomes) {
        final int hits = round.calculateOutcomeMatches(outcomes);
        String price = ZERO_PRICE;
        final Optional<Hit> matchingHit = round.getHits().stream().filter((hit) -> hit.getHits() == hits).findFirst();
        if (matchingHit.isPresent()) {
            price = matchingHit.get().getPrice();
        }

        return new Hit(hits, 0, price);
    }

    private long getWinsForOutcome(final Outcome outcome) {
        return rounds.stream()
                .mapToLong((round) -> round.getOutcomes().stream().filter((oc) -> oc.equals(outcome)).count())
                .sum();
    }

    private double getPercentage(final long sum, final long wins) {
        return ((double) wins / (double) sum) * PRECENTAGE_MULTIPLIER;
    }

    private WinDistribution calculateWinDistribution() {
        final long xWins = getWinsForOutcome(Outcome.X);
        final long oneWins = getWinsForOutcome(Outcome.ONE);
        final long twoWins = getWinsForOutcome(Outcome.TWO);
        final long sum = xWins + oneWins + twoWins;
        return new WinDistribution(getPercentage(sum, oneWins), getPercentage(sum, twoWins), getPercentage(sum, xWins));
    }

    private Optional<Round> findRoundForDate(final LocalDate date) {
        return rounds.stream()
                .filter((round) -> round.getDate().equals(date))
                .findAny();
    }

    @Override
    public boolean isDataAvailable() {
        return !rounds.isEmpty();
    }

}
