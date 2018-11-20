package com.epam.training.restaurant.consumable;

public class ConsumableWithKetchup extends ConsumableWithExtra {

    public ConsumableWithKetchup(final Consumable consumable) {
        super(consumable);
    }

    @Override
    public double consume(final double currentHappiness) {
        return consumable.consume(consumable.consume(currentHappiness));
    }

}
