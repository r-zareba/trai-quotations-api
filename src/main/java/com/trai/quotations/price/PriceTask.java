package com.trai.quotations.price;

import com.trai.quotations.price_api.PriceApi;
import com.trai.quotations.price_api.TradingViewApi;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Component
public class PriceTask {
    private static final Logger log = LoggerFactory.getLogger(PriceTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Autowired
    private PriceRepository priceRepository;

    private PriceApi priceApi;
    private List<Double> prices = new ArrayList<>();

    private boolean useHeadless = false;

    public PriceTask() {
        priceApi = new TradingViewApi("EURUSD");
    }

    @Scheduled(fixedRate = 200)
    public void reportCurrentTime() {
        prices.add(priceApi.getPrice());
    }

    @Scheduled(cron = "0 * * * * *")
    public void updateOHLC() {
        double open = prices.get(0);
        double high = Collections.max(prices);
        double low = Collections.min(prices);
        double close = prices.get(prices.size() - 1);
        Date timestamp = new Date(System.currentTimeMillis() - 60 * 1000);

        log.info(String.format("OHLC for %s : open: %f, high: %f, low: %f, close: %f",
                dateFormat.format(new Date(System.currentTimeMillis() - 60 * 1000)), open, high, low, close));

        Eurusd ohlc = new Eurusd(open, high, low, close, timestamp);
        priceRepository.save(ohlc).subscribe();
        prices.clear();
    }
}
