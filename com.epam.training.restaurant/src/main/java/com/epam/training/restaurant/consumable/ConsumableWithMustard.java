package com.epam.training.restaurant.consumable;

public class ConsumableWithMustard extends ConsumableWithExtra {

    public ConsumableWithMustard(final Consumable consumable) {
        super(consumable);
    }

    @Override
    public double consume(final double currentHappiness) {
        return currentHappiness + 1;
    }
}
