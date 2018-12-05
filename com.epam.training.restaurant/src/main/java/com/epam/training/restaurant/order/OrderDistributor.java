package com.epam.training.restaurant.order;

public interface OrderDistributor {
    public void addObserver(OrderListener listener);

    public void removeObserver(OrderListener listener);

    public void setReturnOrder(ReturnOrder returnOrder);
}
