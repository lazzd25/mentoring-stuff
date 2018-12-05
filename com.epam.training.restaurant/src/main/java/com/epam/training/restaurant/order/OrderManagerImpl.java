package com.epam.training.restaurant.order;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class OrderManagerImpl implements OrderManager {

    private final BlockingQueue<Order> orders;
    private final OrderDistributor distributor;

    public OrderManagerImpl(final BlockingQueue<Order> orders, final OrderDistributor distributor) {
        if (Objects.isNull(orders)) {
            throw new IllegalArgumentException("orders is null");
        }
        if (Objects.isNull(distributor)) {
            throw new IllegalArgumentException("distributor is null");
        }
        this.orders = orders;
        this.distributor = distributor;
    }

    @Override
    public void createOrder(final Client client, final Dish dish) {
        if (Objects.isNull(client)) {
            throw new IllegalArgumentException("client is null");
        }
        if (Objects.isNull(dish)) {
            throw new IllegalArgumentException("dish is null");
        }

        distributor.addObserver(client);
        try {
            orders.put(new Order(client.getClientId(), dish));
        }
        catch (final InterruptedException e) {
            e.printStackTrace();
            return;
        }

    }

}
