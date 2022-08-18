package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutComplete extends BasePage {

    private String url;


    public CheckoutComplete (WebDriver driver) {
        super(driver);
        this.url = "https://www.saucedemo.com/checkout-complete.html";
    }

    public CheckoutComplete () {}

    public void openPage () {
        driver.get(this.url);
    }

    public boolean isDisplayed () {
        return driver.findElement(By.className("title")).getText().equalsIgnoreCase("Checkout: Complete!");
    }
}
