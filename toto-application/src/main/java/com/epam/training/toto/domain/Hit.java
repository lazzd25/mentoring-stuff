package com.epam.training.toto.domain;

public class Hit {
    private final int hits;
    private final int numberOfGames;
    private final String price;

    public Hit(final int hits, final int numberOfGames, final String price) {
        super();
        this.hits = hits;
        this.numberOfGames = numberOfGames;
        this.price = price;
    }

    public int getHits() {
        return hits;
    }

    public int getNumberOfGames() {
        return numberOfGames;
    }

    public String getPrice() {
        return price;
    }

    public static int getPriceInIntFromHit(final Hit hit) {
        final StringBuilder priceBuilder = new StringBuilder();
        final String[] price = hit.getPrice().split(" ");
        for (int i = 0; i < price.length - 1; i++) {
            priceBuilder.append(price[i]);
        }

        return Integer.parseInt(priceBuilder.toString());

    }

}
