package com.epam.training.restaurant.order;

import java.util.Objects;

import com.epam.training.restaurant.consumable.Consumable;

public class ReturnOrder {

    private final int clientId;
    private final Consumable consumable;

    public ReturnOrder(final int clientId, final Consumable consumable) {
        if (Objects.isNull(consumable)) {
            throw new IllegalArgumentException("consumable is null");
        }
        this.clientId = clientId;
        this.consumable = consumable;
    }

    public int getClientId() {
        return clientId;
    }

    public Consumable getConsumable() {
        return consumable;
    }

}
