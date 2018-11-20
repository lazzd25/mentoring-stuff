package com.epam.training.restaurant.main;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

import com.epam.training.restaurant.order.Client;
import com.epam.training.restaurant.order.Dish;
import com.epam.training.restaurant.order.Order;
import com.epam.training.restaurant.order.OrderManager;
import com.epam.training.restaurant.order.OrderManagerImpl;
import com.epam.training.restaurant.order.OrderObserver;
import com.epam.training.restaurant.order.OrderObserverImpl;
import com.epam.training.restaurant.order.ReturnOrder;

public class App {
    public static void main(final String[] args) {
        final BlockingQueue<Order> orders = new SynchronousQueue<>();
        final BlockingQueue<ReturnOrder> returnOrders = new SynchronousQueue<>();
        final OrderObserver observer = new OrderObserverImpl(returnOrders);
        final OrderManager orderManager = new OrderManagerImpl(orders, observer);

        final Executor executor = Executors.newFixedThreadPool(1);
        executor.execute(new FoodBot(orders, returnOrders));

        orderManager.createOrder(new Client(1, 20), Dish.CHIPS);
        orderManager.createOrder(new Client(2, 20), Dish.HOTDOG);
        orderManager.createOrder(new Client(3, 20), Dish.CHIPS_KETCHUP);
        orderManager.createOrder(new Client(4, 20), Dish.HOTDOG_KETCHUP_MUSTARD);
        orderManager.createOrder(new Client(5, 20), Dish.CHIPS_MUSTARD);
        orderManager.createOrder(new Client(5, 20), Dish.CHIPS_KETCHUP_MUSTARD);

        System.exit(0);
    }
}
