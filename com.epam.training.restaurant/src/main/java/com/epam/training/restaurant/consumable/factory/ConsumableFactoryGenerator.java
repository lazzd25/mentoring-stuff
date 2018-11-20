package com.epam.training.restaurant.consumable.factory;

import java.util.Objects;

import com.epam.training.restaurant.consumable.Consumable;
import com.epam.training.restaurant.consumable.ConsumableWithKetchup;
import com.epam.training.restaurant.consumable.ConsumableWithMustard;
import com.epam.training.restaurant.order.Dish;

public class ConsumableFactoryGenerator {

    private static final int HOTDOG_HAPPINESS_INCREASEMENT = 2;
    private static final double KETCHUP_HAPPINESS_RATE = 1.2;
    private static final Consumable CHIPS = (happiness) -> happiness * KETCHUP_HAPPINESS_RATE;
    private static final Consumable HOT_DOG = (happiness) -> happiness + HOTDOG_HAPPINESS_INCREASEMENT;

    public static ConsumableFactory generateFactoryForDish(final Dish orderedDish) {
        if (Objects.isNull(orderedDish)) {
            throw new IllegalArgumentException("ordered dish is null");
        }

        if (Dish.CHIPS.equals(orderedDish)) {
            return () -> CHIPS;
        }
        if (Dish.CHIPS_KETCHUP.equals(orderedDish)) {
            return () -> new ConsumableWithKetchup(CHIPS);
        }
        if (Dish.CHIPS_MUSTARD.equals(orderedDish) || Dish.CHIPS_KETCHUP_MUSTARD.equals(orderedDish)) {
            return () -> new ConsumableWithMustard(CHIPS);
        }
        if (Dish.HOTDOG.equals(orderedDish)) {
            return () -> HOT_DOG;
        }
        if (Dish.HOTDOG_KETCHUP.equals(orderedDish)) {
            return () -> new ConsumableWithKetchup(HOT_DOG);
        }
        if (Dish.HOTDOG_MUSTARD.equals(orderedDish) || Dish.HOTDOG_KETCHUP_MUSTARD.equals(orderedDish)) {
            return () -> new ConsumableWithMustard(HOT_DOG);
        }

        throw new IllegalStateException();
    }

}
