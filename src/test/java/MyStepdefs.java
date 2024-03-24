import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;
import java.util.Objects;

public class MyStepdefs {
    private WebDriver driver;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @Given("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        WebElement name = driver.findElement(By.xpath("//input[@id='user-name']"));
        name.click();
        name.sendKeys(username);
        WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
        pass.click();
        pass.sendKeys(password);
    }

    @And("I click the login button")
    public void iClickTheLoginButton() {
        WebElement loginButton = driver.findElement(By.cssSelector("#login-button"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginButton.click();
    }

    @Then("I should be on the products page")
    public void iShouldBeOnTheProductsPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
    }

    @When("I add two products by my choice")
    public void iAddedTwoProducts() {
        WebElement product1 = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-fleece-jacket']"));
        WebElement product2 = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-bike-light']"));
        product1.click();
        product2.click();
    }

    @Then("I verify the products are added to the cart without going to the cart page")
    public void iVerifyTheProductsAreAddedToTheCard() {
        WebElement spanElement = driver.findElement(By.cssSelector("span.shopping_cart_badge"));
        if (Objects.equals(spanElement.getText(), "2")) {
            Assert.assertTrue(true);
        } else {
            Assert.assertFalse(true);
        }
    }

    @When("I remove the products from the card")
    public void iRemovedTheProducts() {
        WebElement removeProduct1 = driver.findElement(By.cssSelector("#remove-sauce-labs-fleece-jacket"));
        removeProduct1.click();
        WebElement removeProduct2 = driver.findElement(By.cssSelector("#remove-sauce-labs-bike-light"));
        removeProduct2.click();
    }

    @Then("I verify products were successfully removed from the card")
    public void iVerifyProductsWereSuccessfullyRemovedFromTheCard() {
        //  WebElement linkWithoutSpan = driver.findElement(By.cssSelector(".shopping_cart_link"));
        WebElement linkWithoutSpan = driver.findElement(By.xpath("//a[@class='shopping_cart_link' and not(.//span)]"));
        Assert.assertNotNull(linkWithoutSpan);
    }

    @When("I add two products by my choice to the card")
    public void iAddTwoProductsByMyChoiceToTheCard() {
        WebElement finalPr1 = driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-fleece-jacket"));
        finalPr1.click();
        WebElement finalPr2 = driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-onesie"));
        finalPr2.click();
    }

    @Then("I verify the products are successfully added to the cart without going to the cart page")
    public void iVerifyTheProductsAreSuccessfullyAddedToTheCartWithoutGoingToTheCartPage() {
        WebElement spanElement = driver.findElement(By.cssSelector("span.shopping_cart_badge"));
        Assert.assertEquals(spanElement.getText(), "2");
    }

    @When("I go to the card")
    public void iGoToTheCard() {
        WebElement shoppingCartLink = driver.findElement(By.cssSelector("span.shopping_cart_badge"));
        shoppingCartLink.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/cart.html");
    }

    @Then("I verify the products I have selected in step ?? are displayed properly on the screen")
    public void iGoToCardAndICheckIfMyFinalProductsAreAdded() {
        WebElement product1 = driver.findElement(By.cssSelector("a[id='item_5_title_link'] div[class='inventory_item_name']"));
        Assert.assertEquals(product1.getText(), "Sauce Labs Fleece Jacket");

        WebElement product2 = driver.findElement(By.cssSelector("a[id='item_2_title_link'] div[class='inventory_item_name']"));
        Assert.assertEquals(product2.getText(), "Sauce Labs Onesie");
    }

    @When("I continue to the next screen")
    public void iContinueToTheNextScreen() {
        WebElement checkoutButton = driver.findElement(By.cssSelector("#checkout"));
        checkoutButton.click();
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/checkout-step-one.html");
    }

    @Then("I try to submit the empty form and validate the errors")
    public void iTryToSubmitTheEmptyFormAndValidateTheErrors() {
        WebElement continueButton = driver.findElement(By.cssSelector("#continue"));
        continueButton.click();

        WebElement errorMessageFirstName = driver.findElement(By.xpath("(//div[@class='error-message-container error'])[1]"));
        String errorFirstName = errorMessageFirstName.getText();
        Assert.assertEquals(errorFirstName, "Error: First Name is required");

        WebElement firstNameField = driver.findElement(By.cssSelector("#first-name"));
        firstNameField.click();
        firstNameField.sendKeys("Kim");
        continueButton.click();

        //verify 2nd error
        WebElement errorMessageLastName = driver.findElement(By.xpath("(//h3[normalize-space()='Error: Last Name is required'])[1]"));
        String errorLastName = errorMessageLastName.getText();
        Assert.assertEquals(errorLastName, "Error: Last Name is required");

        WebElement lastNameField = driver.findElement(By.cssSelector("#last-name"));
        lastNameField.click();
        lastNameField.sendKeys("Test");
        continueButton.click();

        WebElement errorMessagePostalCode = driver.findElement(By.cssSelector("h3[data-test='error']"));
        String errorPostalCode = errorMessagePostalCode.getText();
        Assert.assertEquals(errorPostalCode, "Error: Postal Code is required");
    }

    @Then("I clear the form, fill it in and click continue")
    public void iClearTheFormFillItInAndClickContinue() {
        WebElement firstNameField = driver.findElement(By.cssSelector("#first-name"));
        WebElement lastNameField = driver.findElement(By.cssSelector("#last-name"));
        WebElement zipField = driver.findElement(By.cssSelector("#postal-code"));
        firstNameField.clear();
        lastNameField.clear();
        firstNameField.sendKeys("Harry");
        lastNameField.sendKeys("Yann");
        zipField.sendKeys("1111");
        WebElement continueButton = driver.findElement(By.cssSelector("#continue"));
        Assert.assertTrue(continueButton.isEnabled(), "Continue button should be enabled!");
        continueButton.click();
    }

    @Then("Verify that information on the last preview screen is shown properly-Price,Items,Buttons")
    public void verifyThatInformationOnTheLastPreviewScreenIsShownProperlyPriceItemsButtons() {
        //price
        WebElement itemPrice1 = driver.findElement(By.xpath("//div[@class='inventory_item_price'][normalize-space()='$49.99']"));
        String price1 = itemPrice1.getText();
        Assert.assertEquals(price1, "$49.99");

        WebElement itemPrice2 = driver.findElement(By.xpath("//div[@class='inventory_item_price'][normalize-space()='$7.99']"));
        String price2 = itemPrice2.getText();
        Assert.assertEquals(price2, "$7.99");
        //items
        WebElement itemName1 = driver.findElement(By.cssSelector("a[id='item_5_title_link'] div[class='inventory_item_name']"));
        String itemName1Text = itemName1.getText();
        Assert.assertEquals(itemName1Text, "Sauce Labs Fleece Jacket");

        WebElement itemName2 = driver.findElement(By.cssSelector("a[id='item_2_title_link'] div[class='inventory_item_name']"));
        String itemName2Text = itemName2.getText();
        Assert.assertEquals(itemName2Text, "Sauce Labs Onesie");

        //buttons
        WebElement cancelButton = driver.findElement(By.cssSelector("#cancel"));
        Assert.assertTrue(cancelButton.isDisplayed());
        WebElement finishButton = driver.findElement(By.cssSelector("#finish"));
        Assert.assertTrue(finishButton.isDisplayed());
    }

    @Then("I click continue and verify success message for successful purchase")
    public void iClickContinueAndVerifySuccessMessageForSuccessfulPurchase() {
        WebElement finishButton = driver.findElement(By.cssSelector("#finish"));
        finishButton.click();

        WebElement successfulPurchaseMessage = driver.findElement(By.cssSelector(".complete-header"));
        Assert.assertTrue(successfulPurchaseMessage.isDisplayed(), "Thank you for your order!");
    }

    @Then("I logout")
    public void iLogout() {
        WebElement burgerMenuButton = driver.findElement(By.cssSelector("#react-burger-menu-btn"));
        burgerMenuButton.click();
        WebElement logoutSidebarLinkButton =driver.findElement(By.cssSelector("#logout_sidebar_link"));
        logoutSidebarLinkButton.click();

    }


}

