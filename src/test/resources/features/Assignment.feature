@GiftCard
Feature: This feature file covers the scenarios of Amazon.com.au web application through appium

  @GiftCardPortrait
  Scenario: User navigate to Amazon website and check out Amazon Gift Card in PORTRAIT mode
    Given User changes screen orientation to "PORTRAIT"
    Given User navigates to "https://www.amazon.com.au/"
    Then User should be on home page of amazon
    When User search for product "Amazon Gift Card"
    And User scroll and click on "Myer eGift Card - Delivered via email (AU Only)"
    Then User should be on product page of amazon
    And User should enter following details related to gift card on product page
      | giftAmount | recipientEmail  | senderName | message                                          | quantity |
      | 30         | test123@abc.com | Mike       | Hope you enjoy this gift card! Have a nice day ! | 1        |
    And User navigates to cart
    And User proceeds to checkout
    Then User should be on checkout or login page of amazon


  @GiftCardLandScape
  Scenario: User navigate to Amazon website and check out Amazon Gift Card in LANDSCAPE mode
    Given User changes screen orientation to "LANDSCAPE"
    Given User navigates to "https://www.amazon.com.au/"
    Then User should be on home page of amazon
    When User search for product "Amazon Gift Card"
    And User scroll and click on "Myer eGift Card - Delivered via email (AU Only)"
    Then User should be on product page of amazon
    And User should enter following details related to gift card on product page
      | giftAmount | recipientEmail  | senderName | message                                          | quantity |
      | 30         | test123@abc.com | Mike       | Hope you enjoy this gift card! Have a nice day ! | 1        |
    And User navigates to cart
    And User proceeds to checkout
    Then User should be on checkout or login page of amazon



