package com.epam.training.restaurant.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.epam.training.restaurant.consumable.Consumable;

public class OrderObserverImpl implements OrderObserver {

    private final BlockingQueue<ReturnOrder> returnOrders;
    private final Collection<OrderListener> listeners = new ArrayList<>();
    private final Executor executor = Executors.newFixedThreadPool(1);
    private boolean isObserving;

    public OrderObserverImpl(final BlockingQueue<ReturnOrder> returnOrders) {
        if (Objects.isNull(returnOrders)) {
            throw new IllegalArgumentException("returnOrders is null");
        }
        this.returnOrders = returnOrders;
    }

    @Override
    public void startObservation() {
        isObserving = true;
        executor.execute(
                new Runnable() {

                    @Override
                    public void run() {
                        while (isObserving) {
                            try {
                                final Optional<ReturnOrder> returnOrder = Optional.ofNullable(returnOrders.take());
                                System.out.println("got return");
                                if (returnOrder.isPresent()) {
                                    updateListeners(returnOrder.get().getClientId(), returnOrder.get().getConsumable());
                                }
                            }
                            catch (final InterruptedException e) {
                                isObserving = false;
                            }
                        }
                    }

                });

    }

    private synchronized void updateListeners(final int clientId, final Consumable consumable) {
        listeners.stream().forEach(listener -> listener.onOrderReady(clientId, consumable));
    }

    @Override
    public synchronized void registerOrderListener(final OrderListener listener) {
        if (Objects.nonNull(listener) && !listeners.contains(listener)) {
            listeners.add(listener);
        }

    }

    @Override
    public synchronized void removeOrderListener(final OrderListener listener) {
        if (Objects.nonNull(listener) && !listeners.contains(listener)) {
            listeners.remove(listener);
        }

    }

    @Override
    public boolean isObserving() {
        return isObserving;
    }
}
