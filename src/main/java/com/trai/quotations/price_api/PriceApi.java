package com.trai.quotations.price_api;

import org.springframework.stereotype.Component;

//@Component
public interface PriceApi {
    void init();
    double getPrice();
    void close();
    boolean isReady();

}
