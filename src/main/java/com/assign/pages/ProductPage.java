package com.assign.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Map;

public class ProductPage extends BasePage {

    AndroidDriver driver;
    WebDriverWait wait;

    public ProductPage(AppiumDriver driver) {
        this.driver = (AndroidDriver) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(how = How.CSS, using = ".a-pagination.a-dots")
    public WebElement lstPaginationDots;

    @FindBy(how = How.CSS, using = "input#gc-order-form-custom-amount")
    public WebElement txtCustomAmount;

    @FindBy(how = How.CSS, using = "input[type='email']")
    public WebElement txtRecipientEmail;

    @FindBy(how = How.CSS, using = "#gc-order-form-senderName")
    public WebElement txtSenderName;

    @FindBy(how = How.CSS, using = "#gc-order-form-message")
    public WebElement txtGiftMessage;

    @FindBy(how = How.CSS, using = "#gc-order-form-quantity")
    public WebElement txtGiftCardQuantity;

    @FindBy (how = How.XPATH, using = "//span[contains(text(),'Add to Cart')]/preceding-sibling::input")
    public WebElement btnAddToCart;

    @FindBy (how = How.XPATH, using = "//input[@name='proceedToRetailCheckout']")
    public WebElement btnProceedToCheckOut;

    @FindBy (how = How.CSS, using = "input#ap_email_login")
    public WebElement txtEmailOrPhone;

    @FindBy (how = How.XPATH, using = "//strong[contains(text(),'Added to cart')]")
    public WebElement lblAddedToCart;

    public boolean isPaginationDotsDisplayed() {
        return isElementDisplayed(lstPaginationDots);
    }

    public void fillGiftCardInfoAddToCart(Map<String,String> dataMap) {
        scrollToElement(txtCustomAmount);
        enterText(txtCustomAmount, dataMap.get("giftAmount").trim());
        scrollToElement(txtRecipientEmail);
        enterText(txtRecipientEmail, dataMap.get("recipientEmail").trim());
        scrollToElement(txtSenderName);
        enterText(txtSenderName, dataMap.get("senderName").trim());
        scrollToElement(txtGiftMessage);
        enterText(txtGiftMessage, dataMap.get("message").trim());
        scrollToElement(txtGiftCardQuantity);
        enterText(txtGiftCardQuantity, dataMap.get("quantity").trim());
        click(btnAddToCart);
        waitTillElementDisappeared(lblAddedToCart);
        waitFor(2);
    }

    public void clickonProceedToCheckout() {
        click(btnProceedToCheckOut);
    }

    public boolean isEmailOrPhonePresentOnScreen() {
        return isElementDisplayed(txtEmailOrPhone);
    }








}
