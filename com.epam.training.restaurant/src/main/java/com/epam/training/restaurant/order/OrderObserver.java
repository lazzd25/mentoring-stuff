package com.epam.training.restaurant.order;

public interface OrderObserver {
    public void registerOrderListener(OrderListener listener);

    public void removeOrderListener(OrderListener listener);

    public void startObservation();

    public boolean isObserving();
}
