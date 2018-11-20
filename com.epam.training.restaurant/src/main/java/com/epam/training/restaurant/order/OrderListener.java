package com.epam.training.restaurant.order;

import com.epam.training.restaurant.consumable.Consumable;

public interface OrderListener {

    public void onOrderReady(int clientId, Consumable consumable);
}
