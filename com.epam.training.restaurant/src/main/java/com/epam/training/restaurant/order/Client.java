package com.epam.training.restaurant.order;

import com.epam.training.restaurant.consumable.Consumable;

public class Client implements OrderListener {

    private final int clientId;
    private double happiness;

    public Client(final int clientId, final double happiness) {
        this.clientId = clientId;
        this.happiness = happiness;
    }

    public int getClientId() {
        return clientId;
    }

    @Override
    public void onOrderReady(final int clientId, final Consumable consumable) {
        if (this.clientId == clientId) {
            System.out.println("happiness before:" + happiness);
            happiness = consumable.consume(happiness);
            System.out.println("happiness after:" + happiness);
        }

    }
}
