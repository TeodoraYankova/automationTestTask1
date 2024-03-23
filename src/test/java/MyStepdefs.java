import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.time.Duration;

public class MyStepdefs {
    private WebDriver driver;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        this.driver = new ChromeDriver();
        this.driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");
    }

    @When("I enter username {string} and password {string}")
    public void iEnterUsernameAndPassword(String username, String password) {
        WebElement name = driver.findElement(By.xpath("//input[@id='user-name']"));
        name.click();
        name.sendKeys(username);
        WebElement pass = driver.findElement(By.xpath("//input[@id='password']"));
        pass.click();
        pass.sendKeys(password);
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//input[@id='login-button']"));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginButton.click();
    }

    @Then("I should be on the products page")
    public void iShouldBeOnTheProductsPage() {
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.saucedemo.com/inventory.html");
    }



//    @Given("I am logged in as {string}")
//    public void iAmLoggedInAs(String arg0) {
//        assert equals("1", "1");
//
//
//      }
//

}

