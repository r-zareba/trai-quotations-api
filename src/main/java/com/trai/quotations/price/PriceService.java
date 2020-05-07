package com.trai.quotations.price;

import com.mongodb.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;


    public void save(Eurusd ohlc) {
        priceRepository.save(ohlc);
    }




}
