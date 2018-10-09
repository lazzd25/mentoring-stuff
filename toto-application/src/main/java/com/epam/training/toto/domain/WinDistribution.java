package com.epam.training.toto.domain;

public class WinDistribution {
    private final double first;
    private final double second;
    private final double draw;

    public WinDistribution(final double first, final double second, final double draw) {
        super();
        this.first = first;
        this.second = second;
        this.draw = draw;
    }

    public double getFirst() {
        return first;
    }

    public double getSecond() {
        return second;
    }

    public double getDraw() {
        return draw;
    }

}
