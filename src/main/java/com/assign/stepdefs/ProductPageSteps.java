package com.assign.stepdefs;

import com.assign.pages.BasePage;
import com.assign.pages.ProductPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class ProductPageSteps extends BasePage {

    ProductPage productPage = new ProductPage(driver);


    @Then("User should be on product page of amazon")
    public void userShouldBeOnProductPageOfAmazon() {
        assertTrue("User is not on amazon product page", productPage.isPaginationDotsDisplayed());
    }

    @And("User should enter following details related to gift card on product page")
    public void enterGiftCardDetails(List<Map<String, String>> dataList) {
        Map<String, String> dataMap = dataList.get(0);
        productPage.fillGiftCardInfoAddToCart(dataMap);
    }

    @And("User proceeds to checkout")
    public void userProceedsToCheckout() {
        productPage.clickonProceedToCheckout();
    }

    @Then("User should be on checkout or login page of amazon")
    public void userShouldBeOnCheckoutPageOfAmazon() {
        assertTrue("User is not on amazon checkout/login page", productPage.isEmailOrPhonePresentOnScreen());
    }
}
