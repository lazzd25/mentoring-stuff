package com.epam.training.restaurant.order;

import java.util.Objects;

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
    public void update(final ReturnOrder order) {
        if (isOrderValid(order)) {
            System.out.println("happiness before:" + happiness);
            happiness = order.getConsumable().consume(happiness);
            System.out.println("happiness after:" + happiness);
        }

    }

    private boolean isOrderValid(final ReturnOrder order) {
        return Objects.nonNull(order) && Objects.nonNull(order.getConsumable()) && clientId == order.getClientId();
    }
}
