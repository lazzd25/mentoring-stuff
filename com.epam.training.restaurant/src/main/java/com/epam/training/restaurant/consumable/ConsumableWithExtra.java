package com.epam.training.restaurant.consumable;

import java.util.Objects;

public abstract class ConsumableWithExtra implements Consumable {

    protected final Consumable consumable;

    public ConsumableWithExtra(final Consumable consumable) {
        super();
        if (Objects.isNull(consumable)) {
            throw new IllegalArgumentException("consumable is null");
        }
        this.consumable = consumable;
    }

    public abstract double consume(double currentHappiness);

}
