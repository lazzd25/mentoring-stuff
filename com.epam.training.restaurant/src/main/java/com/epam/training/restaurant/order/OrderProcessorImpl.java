package com.epam.training.restaurant.order;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class OrderProcessorImpl implements OrderProcessor {

    private final BlockingQueue<ReturnOrder> returnOrders;
    private final Executor executor = Executors.newFixedThreadPool(1);
    private final OrderDistributor distributor;
    private boolean isRunning;

    public OrderProcessorImpl(final BlockingQueue<ReturnOrder> returnOrders, final OrderDistributor distributor) {
        if (Objects.isNull(returnOrders)) {
            throw new IllegalArgumentException("returnOrders is null");
        }
        if (Objects.isNull(distributor)) {
            throw new IllegalArgumentException("distributor is null");
        }
        this.returnOrders = returnOrders;
        this.distributor = distributor;

    }

    @Override
    public void start() {
        isRunning = true;
        executor.execute(
                new Runnable() {

                    @Override
                    public void run() {
                        while (isRunning) {
                            try {
                                final Optional<ReturnOrder> returnOrder = Optional.ofNullable(returnOrders.take());
                                returnOrder.ifPresent(order -> distributor.setReturnOrder(order));
                            }
                            catch (final InterruptedException e) {
                                isRunning = false;
                            }
                        }
                    }

                });
    }

}
