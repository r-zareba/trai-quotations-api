package com.trai.quotations.price_api;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
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
        ready = false;
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

//        System.setProperty("webdriver.gecko.driver", "geckodriver");

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(new FirefoxProfile());
        options.addPreference("dom.webnotifications.enabled", false);
        options.setHeadless(true);

//        Capabilities firefoxCapabilities = DesiredCapabilities.firefox();
//        Capabilities chromeCapabilities = DesiredCapabilities.chrome();
        this.webDriver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), options);
//        this.webDriver = new RemoteWebDriver(new URL("http://192.168.1.6:5557/wd/hub"), options);
//        this.webDriver = new RemoteWebDriver(new URL("http://selenium-hub:4444/wd/hub"), chromeCapabilities);


//        this.webDriver = new FirefoxDriver(options);/**/
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--no-sandbox");
//        options.addArguments("--disable-setuid-sandbox");

//        this.webDriver = new ChromeDriver();
//        this.webDriver = new FirefoxDriver();

    }

    private void setPriceElement() {
        webDriver.get(assetUrl);
        WebDriverWait driverWait = new WebDriverWait(webDriver, 15);
        driverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(priceXPath)));
        this.priceElement = webDriver.findElement(By.xpath(priceXPath));

    }


}
