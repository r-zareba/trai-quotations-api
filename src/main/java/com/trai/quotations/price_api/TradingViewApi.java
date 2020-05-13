package com.trai.quotations.price_api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.net.MalformedURLException;
import java.net.URL;


public class TradingViewApi implements PriceApi {

    private static final Logger log = LoggerFactory.getLogger(TradingViewApi.class);

    private static String baseUrl = "https://www.tradingview.com/symbols/";
    private static String priceXPath = "/html/body/div[2]/div[4]/div/header/div/div[3]/div[1]/div/div/div/div[1]/div[1]";

    private final String asset;
    private String assetUrl;
    private WebDriver webDriver;
    private WebElement priceElement;
    private boolean ready = false;

    private boolean useHeadless;

    public void init() {
        if (!ready) {

            try {
                initDriver();
            } catch (MalformedURLException e) {
                log.info("\n\nweb driver not initialized\n\n");
            }

            setPriceElement();
            ready = true;
            log.info("INITIALIZED ! {}", this);
        }
    }

    public double getPrice() {
        if (ready)
            return Double.parseDouble(priceElement.getText());
        return 0;
    }

    public void close() {
        webDriver.close();
    }

    public TradingViewApi(String asset) {
        this.asset = asset;
        this.assetUrl = baseUrl + asset;
        init();
    }

    public boolean isReady() {
        return ready;
    }

    private void initDriver() throws MalformedURLException {
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(new FirefoxProfile());
        options.addPreference("dom.webnotifications.enabled", false);
        options.setHeadless(useHeadless);

//        Capabilities firefoxCapabilities = DesiredCapabilities.firefox();
        this.webDriver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);

//        this.webDriver = new FirefoxDriver(options);/**/

    }

    private void setPriceElement() {
        webDriver.get(assetUrl);
        WebDriverWait driverWait = new WebDriverWait(webDriver, 15);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(priceXPath)));
        this.priceElement = webDriver.findElement(By.xpath(priceXPath));

    }


}
