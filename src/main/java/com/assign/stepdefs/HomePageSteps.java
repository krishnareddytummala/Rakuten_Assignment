package com.assign.stepdefs;

import com.assign.pages.BasePage;
import com.assign.pages.HomePage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class HomePageSteps extends BasePage {

    HomePage homePage = new HomePage(driver);


    @Given("User launches chrome browser on mobile")
    public void launchChrome() {
        homePage.setDriverSession();
    }

    @Given("User navigates to {string}")
    public void navigateToSpecificURL(String url) {
        homePage.navigateToURL(url);
    }


    @Given("User search for product {string}")
    public void searchProduct(String productName) {
        homePage.searchProduct(productName);
    }

    @When("User scroll and click on {string}")
    public void scrollAndClickOnItem(String itemName) {
        homePage.scrollAndClickOnGiftCard(itemName);
    }

    @Then("User should be on home page of amazon")
    public void userShouldBeOnHomePageOfAmazon() {
        assertTrue("User is not on amazon home page",homePage.isSubMenuDivPresentOnScreen());
    }


    @And("User navigates to cart")
    public void userNavigatesToCart() {
        homePage.clickOnCart();
    }

    @Given("User changes screen orientation to {string}")
    public void userChangesScreenOrientationTo(String screenOri) {
        homePage.changeOrientation(screenOri);
    }
}