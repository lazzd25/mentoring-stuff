package com.epam.training.restaurant.order;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class OrderManagerImpl implements OrderManager {

    private final BlockingQueue<Order> orders;
    private final OrderObserver orderObserver;

    public OrderManagerImpl(final BlockingQueue<Order> orders, final OrderObserver orderObserver) {
        if (Objects.isNull(orders)) {
            throw new IllegalArgumentException("orders is null");
        }
        if (Objects.isNull(orderObserver)) {
            throw new IllegalArgumentException("orderObserver is null");
        }
        this.orders = orders;
        this.orderObserver = orderObserver;
    }

    @Override
    public void createOrder(final Client client, final Dish dish) {
        System.out.println("createorder");
        if (Objects.isNull(client)) {
            throw new IllegalArgumentException("client is null");
        }
        if (Objects.isNull(dish)) {
            throw new IllegalArgumentException("dish is null");
        }

        orderObserver.registerOrderListener(client);
        try {
            System.out.println("waiting to add");
            orders.put(new Order(client.getClientId(), dish));
        }
        catch (final InterruptedException e) {
            e.printStackTrace();
            return;
        }
        if (!orderObserver.isObserving()) {
            orderObserver.startObservation();
        }

    }

}
