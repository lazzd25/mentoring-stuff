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
import com.epam.training.restaurant.order.OrderDistributorImpl;
import com.epam.training.restaurant.order.OrderProcessor;
import com.epam.training.restaurant.order.OrderProcessorImpl;
import com.epam.training.restaurant.order.OrderDistributor;
import com.epam.training.restaurant.order.ReturnOrder;

public class App {
    public static void main(final String[] args) {
        final BlockingQueue<Order> orders = new SynchronousQueue<>();
        final BlockingQueue<ReturnOrder> returnOrders = new SynchronousQueue<>();
        final OrderDistributor distributor = new OrderDistributorImpl();
        final OrderManager orderManager = new OrderManagerImpl(orders, distributor);

        final Executor executor = Executors.newFixedThreadPool(1);
        executor.execute(new FoodBot(orders, returnOrders));

        final OrderProcessor orderProcessor = new OrderProcessorImpl(returnOrders, distributor);
        orderProcessor.start();

        orderManager.createOrder(new Client(1, 20), Dish.CHIPS);
        orderManager.createOrder(new Client(2, 20), Dish.HOTDOG);
        orderManager.createOrder(new Client(3, 20), Dish.CHIPS_KETCHUP);
        orderManager.createOrder(new Client(4, 20), Dish.HOTDOG_KETCHUP_MUSTARD);
        orderManager.createOrder(new Client(5, 18), Dish.CHIPS_MUSTARD);
        orderManager.createOrder(new Client(6, 20), Dish.CHIPS_KETCHUP_MUSTARD);

        System.exit(0);
    }
}
