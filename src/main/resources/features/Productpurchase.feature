Feature: Log to https://www.saucedemo.com/
  As a user
  I want to login https://www.saucedemo.com/
  So that I can access the project page

Scenario: Verify product purchase flow

Given I am on the login page
Given I enter username "standard_user" and password "secret_sauce"
And I click the login button
Then I should be on the products page
  When I add two products by my choice
  Then I verify the products are added to the cart without going to the cart page
  When I remove the products from the card
  Then I verify products were successfully removed from the card
  When I add two products by my choice to the card
  Then I verify the products are successfully added to the cart without going to the cart page
  When I go to the card
  Then I verify the products I have selected in step ?? are displayed properly on the screen
  When I continue to the next screen
  Then I try to submit the empty form and validate the errors
  Then I clear the form, fill it in and click continue
  Then Verify that information on the last preview screen is shown properly-Price,Items,Buttons
  Then I click continue and verify success message for successful purchase
  Then I logout

#  When I have filled the form I click continue
#  Then I verify that information on the last preview screen is shown properly
#




#And I am on the products page
#When I add two products of my choice
#Then the products should be successfully added to the cart
#When I remove the products from the cart
#Then the products should be successfully removed from the cart
#When I add two products of my choice to the cart
#Then the products should be successfully added to the cart
#When I go to the cart
#Then I should see the selected products displayed properly
#When I continue to the next screen without submitting the form
#Then I should see the validation errors for the empty form
#When I clear the form, fill it in, and click continue
#Then I should see the information on the last preview screen displayed properly
#And the price, items, and buttons should be correct
#When I click continue
#Then I should see the success message for the successful purchase
#When I logout