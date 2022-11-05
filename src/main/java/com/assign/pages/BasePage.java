package com.assign.pages;

import com.assign.common.DriverFactory;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Set;

public class BasePage extends DriverFactory {

    WebDriverWait wait;


    public void setDriverSession() {
        this.setDriver();
    }

    public WebDriverWait getWebDriverWait() {
        return wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    public void waitForElementToBeClickable(WebElement element) {
        getWebDriverWait().until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement ele) {
        try {
            isElementDisplayed(ele);
            waitForElementToBeClickable(ele);
            ele.click();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isElementDisplayed(WebElement ele) {
        getWebDriverWait().until(ExpectedConditions.visibilityOf(ele));
        return ele.isDisplayed();
    }

    public void navigateToURL(String url) {
        driver.get(url.trim());
    }

    public void enterText(WebElement ele, String text) {
        isElementDisplayed(ele);
        waitForElementToBeClickable(ele);
        ele.clear();
        ele.sendKeys(text.trim());
    }

    public void acceptAlert() {
        getWebDriverWait().until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }


    public Set<String> getContextHandles() {
        return ((AndroidDriver) getDriver()).getContextHandles();
    }

    public void setContext(String context) {

        Set<String> contextNames = getContextHandles();

        if (contextNames.contains(context)) {
            ((AndroidDriver) getDriver()).context(context);
            System.out.println("Context changed successfully");
        } else {
            System.out.println(context + "not found on this page");
        }

        System.out.println("Current context" + ((AndroidDriver) getDriver()).getContext());
    }

    public void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private JavascriptExecutor getJavascriptExecutor() {
        return (JavascriptExecutor) getDriver();
    }

    public void scrollToElement(WebElement element) {
        getJavascriptExecutor().executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public void changeOrientation(String orientation) {
       try {
           ScreenOrientation currOrientation = driver.getOrientation();
           if(orientation.equalsIgnoreCase("PORTRAIT")) {
               driver.rotate(currOrientation.PORTRAIT);
           } else {
               driver.rotate(currOrientation.LANDSCAPE);
           }
       } catch (Exception exception) {
           exception.printStackTrace();
       }
    }

    public void waitTillElementDisappeared(WebElement ele) {
        getWebDriverWait().until(ExpectedConditions.invisibilityOf(ele));
    }

}