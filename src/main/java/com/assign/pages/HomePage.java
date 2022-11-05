package com.assign.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    AndroidDriver driver;
    WebDriverWait wait;

    public HomePage(AppiumDriver driver) {
        this.driver = (AndroidDriver) driver;
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @FindBy(how = How.XPATH, using = "//a[@data-automation='myer-logo-link']")
    public WebElement iconMyer;

    @FindBy(how = How.ID, using = "nav-search-keywords")
    public WebElement txtSearch;

    @FindBy(how = How.XPATH, using = "//input[@type='submit']")
    public WebElement btnSearch;

    @FindBy(how = How.XPATH, using = "//com.android.chrome[text()='Allow']")
    public WebElement btnAllowNotification;

    @FindBy(how = How.CSS, using = "#nav-gwbar")
    public WebElement divSubMenu;

    @FindBy(how = How.ID, using = "nav-cart-count")
    public WebElement btnCart;


    public void searchProduct(String productName) {
        click(txtSearch);
        enterText(txtSearch, productName);
        click(btnSearch);
    }

    public void acceptNotification() {
        click(btnAllowNotification);
    }

    public void scrollAndClickOnGiftCard(String itemName) {
        By lblGiftCard = By.xpath("//span[text()='" + itemName + "']");
        WebElement ele = getDriver().findElement(lblGiftCard);
        scrollToElement(ele);
        click(ele);
    }

    public boolean isSubMenuDivPresentOnScreen() {
        return isElementDisplayed(divSubMenu);
    }

    public void clickOnCart() {
        scrollToElement(btnCart);
        click(btnCart);
    }


}