package com.epam.training.restaurant.order;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

public class OrderDistributorImpl implements OrderDistributor {

    private final Collection<OrderListener> listeners = new ArrayList<>();

    private void updateListeners(final ReturnOrder order) {
        listeners.stream().forEach(listener -> listener.update(order));
    }

    @Override
    public synchronized void addObserver(final OrderListener listener) {
        if (Objects.nonNull(listener) && !listeners.contains(listener)) {
            listeners.add(listener);
        }

    }

    @Override
    public synchronized void removeObserver(final OrderListener listener) {
        if (Objects.nonNull(listener) && !listeners.contains(listener)) {
            listeners.remove(listener);
        }

    }

    @Override
    public synchronized void setReturnOrder(final ReturnOrder returnOrder) {
        if (Objects.nonNull(returnOrder)) {
            updateListeners(returnOrder);
        }

    }

}
