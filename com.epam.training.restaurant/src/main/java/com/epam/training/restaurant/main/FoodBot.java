package com.epam.training.restaurant.main;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

import com.epam.training.restaurant.consumable.Consumable;
import com.epam.training.restaurant.consumable.factory.ConsumableFactoryGenerator;
import com.epam.training.restaurant.order.Dish;
import com.epam.training.restaurant.order.Order;
import com.epam.training.restaurant.order.ReturnOrder;

public class FoodBot implements Runnable {

    private final static long WAIT_PERIOD = 2000L;
    private final BlockingQueue<Order> orders;
    private final BlockingQueue<ReturnOrder> returnOrders;

    public FoodBot(final BlockingQueue<Order> orders, final BlockingQueue<ReturnOrder> returnOrders) {
        if (Objects.isNull(orders)) {
            throw new IllegalArgumentException("orders is null");
        }
        if (Objects.isNull(returnOrders)) {
            throw new IllegalArgumentException("returnOrders is null");
        }
        this.orders = orders;
        this.returnOrders = returnOrders;
    }

    public void createOrder(final Order order) {
        if (Objects.isNull(order)) {
            throw new IllegalArgumentException("order is null");
        }
        if (Objects.isNull(order.getOrderedDish())) {
            throw new IllegalArgumentException("ordered dish is null");
        }

    }

    private Consumable createDish(final Dish orderedDish) {
        return ConsumableFactoryGenerator.generateFactoryForDish(orderedDish).createConsumable();
    }

    @Override
    public void run() {

        boolean condition = true;

        while (condition) {
            try {
                final Order order = orders.take();                
                Thread.sleep(WAIT_PERIOD);
                returnOrders.put(new ReturnOrder(order.getClientId(), createDish(order.getOrderedDish())));
            }
            catch (final InterruptedException e) {
                condition = false;
            }
        }

    }

}
