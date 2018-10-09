package com.epam.training.toto.service;

import java.time.LocalDate;
import java.util.List;

import com.epam.training.toto.domain.Hit;
import com.epam.training.toto.domain.Outcome;
import com.epam.training.toto.domain.WinDistribution;

public interface ITotoService {

    int getLargestPrize();

    WinDistribution getWinDistribution();

    Hit getHitForDate(LocalDate date, List<Outcome> outcomes);

    boolean isDateInFile(LocalDate date);

    boolean isDataAvailable();
}
