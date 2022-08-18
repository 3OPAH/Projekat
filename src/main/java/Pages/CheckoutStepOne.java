package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepOne extends BasePage{
    private String url;


    public CheckoutStepOne (WebDriver driver) {
        super(driver);
        this.url = "https://www.saucedemo.com/checkout-step-one.html";
    }

    public CheckoutStepOne () {}

    public boolean isDisplayed () {

        return driver.findElement(By.className("title")).getText().equalsIgnoreCase("Checkout: Your Information");
    }
    public void openPage () {
        driver.get(this.url);
    }

    public void inputInfo () {
        this.setFirstName("Nikola");
        this.setLastName("Tesla");
        this.setZipCode(12345);
        this.clickOnContinue();
    }

    public void setFirstName(String firstName) {
        driver.findElement(By.id("first-name")).sendKeys(firstName);
    }

    public void setLastName(String lastName) {
        driver.findElement(By.id("last-name")).sendKeys(lastName);
    }

    public void setZipCode(int zipCode) {
        driver.findElement(By.id("postal-code")).sendKeys(String.valueOf(zipCode));
    }

    public void clickOnContinue () {
        driver.findElement(By.xpath("//input[@name='continue']")).click();
    }
}
