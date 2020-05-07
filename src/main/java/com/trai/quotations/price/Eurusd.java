package com.trai.quotations.price;

import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "EURUSD")
public class Eurusd extends Price {

    public Eurusd(double open, double high, double low, double close, Date timestamp) {
        super(open, high, low, close, timestamp);
    }
}