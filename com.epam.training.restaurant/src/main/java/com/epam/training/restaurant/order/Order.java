package com.epam.training.restaurant.order;

import java.util.Objects;

public class Order {
    private final int clientId;
    private final Dish orderedDish;

    public Order(final int clientId, final Dish orderedDish) {
        super();
        if (Objects.isNull(orderedDish)) {
            throw new IllegalArgumentException("ordered dish is null");
        }
        this.clientId = clientId;
        this.orderedDish = orderedDish;
    }

    public int getClientId() {
        return clientId;
    }

    public Dish getOrderedDish() {
        return orderedDish;
    }
}
