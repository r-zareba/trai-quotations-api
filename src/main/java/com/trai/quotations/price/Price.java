package com.trai.quotations.price;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.Date;



@Data
@NoArgsConstructor
@JsonIgnoreProperties({"id"})
public class Price {
    /* OHLC Candlestick structure */

    @Id
    private String id;

    private double open;
    private double high;
    private double low;
    private double close;
    private Date timestamp;

    public Price(double open, double high, double low, double close, Date timestamp) {
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.timestamp = timestamp;
    }

}
