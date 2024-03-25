Feature: Product Purchase Flow on SauceDemo

  As a user
  I want to log in to SauceDemo
  So that I can access the project page and perform product purchase tasks

  Scenario: Verify product purchase flow
    Given I am on the login page
    When I enter username "standard_user" and password "secret_sauce"
    And I click the login button
    Then I should be on the products page

    When I add two products of my choice
    Then I verify the products are added to the cart without going to the cart page

    When I remove the products from the card
    Then I verify products were successfully removed from the card

    When I add two products of my choice to the card
    Then I verify the products are successfully added to the cart without going to the cart page

    When I go to the card
    Then I verify the products I have selected are displayed properly on the screen

    When I continue to the next screen
    Then I try to submit the empty form and validate the errors

    And I clear the form, fill it in and click continue
    Then Verify that information on the last preview screen is shown properly-Price,Items,Buttons

    And I click continue and verify success message for successful purchase
    Then I logout