package stepDefinition;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.concurrent.TimeUnit;

public class TestScenario {

    WebDriver driver;

    @Given("^Open chrome and start application$")
    public void Open_chrome_and_start_application() throws Throwable {
        System.setProperty("webdriver.chrome.driver", "/Users/anupam/Desktop/Automation Framework/chromedriver");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
    }

    @When("^User enters valid details$")
    public void User_enters_valid_details() throws Throwable {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.findElement(By.id("application_type_single")).click();
        driver.findElement(By.xpath("//label[contains(@for,'home')]")).click();//input[contains(@aria-labelledby,'q2q2')]
        driver.findElement(By.xpath("//input[@aria-labelledby='q2q1']")).sendKeys("80000");
        driver.findElement(By.xpath("//input[@aria-labelledby='q2q2']")).sendKeys("10000");
        driver.findElement(By.xpath("//input[contains(@id,'expenses')]")).sendKeys("500");
        driver.findElement(By.xpath("//input[contains(@id,'otherloans')]")).sendKeys("100");
        driver.findElement(By.xpath("//input[@id='credit']")).sendKeys("10000");
        driver.findElement(By.xpath("//button[contains(@class,'calculate')]")).click();
    }

    @Then("^borrowing estimate is displayed correctly$")
    public void borrowing_estimate_is_displayed_correctly() throws Throwable {
        Thread.sleep(1000);
        String actualamount = "$467,000";
        String item = driver.findElement(By.xpath("//span[@class='borrow__result__text__amount']")).getText();
        Assert.assertTrue(item.contains(actualamount));
    }

    @When("^User clicks start over button$")
    public void User_clicks_start_over_button() throws Throwable {
        driver.findElement(By.xpath("(//span[contains(@class,'icon icon_restart')])[1]")).click();
    }

    @Then("^Form is cleared$")
    public void Form_is_cleared() throws Throwable {
        String resetvalue = "0";
        String item1 = driver.findElement(By.xpath("//input[@aria-labelledby='q2q1']")).getAttribute("value");
        String item2 = driver.findElement(By.xpath("//input[@aria-labelledby='q2q2']")).getAttribute("value");
        String item3 = driver.findElement(By.xpath("//input[contains(@id,'expenses')]")).getAttribute("value");
        String item4 = driver.findElement(By.xpath("//input[@id='homeloans']")).getAttribute("value");
        String item5 = driver.findElement(By.xpath("//input[@id='otherloans']")).getAttribute("value");
        String item6 = driver.findElement(By.xpath("//input[contains(@aria-labelledby,'q3q4')]")).getAttribute("value");
        String item7 = driver.findElement(By.xpath("//input[@id='credit']")).getAttribute("value");

        Assert.assertTrue(item1.contains(resetvalue));
        Assert.assertTrue(item2.contains(resetvalue));
        Assert.assertTrue(item3.contains(resetvalue));
        Assert.assertTrue(item4.contains(resetvalue));
        Assert.assertTrue(item5.contains(resetvalue));
        Assert.assertTrue(item6.contains(resetvalue));
        Assert.assertTrue(item7.contains(resetvalue));
    }

    @When("^User does not enter all details$")
    public void User_does_not_enter_all_details() throws Throwable {
        driver.findElement(By.xpath("//input[@id='expenses']")).sendKeys("1");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//button[contains(@class,'calculate')]")).click();
    }

    @Then("^Correct error is displayed$")
    public void Correct_error_is_displayed() throws Throwable {
        String errorMessage = "Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 100 641.";
        String item = driver.findElement(By.xpath("//span[contains(@class,'borrow__error__text')]")).getText();
        Assert.assertTrue(item.contains(errorMessage));
    }

    @Then("^Application should be closed$")
    public void Application_should_be_closed() throws Throwable {
        driver.quit();
    }
}