package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutStepTwo extends BasePage {

    private String url;


    public CheckoutStepTwo (WebDriver driver) {
        super(driver);
        this.url = "https://www.saucedemo.com/checkout-step-two.html";
    }

    public CheckoutStepTwo () {}

    public void openPage () {
        driver.get(this.url);
    }

    public boolean isDisplayed () {

        return driver.findElement(By.className("title")).getText().equalsIgnoreCase("Checkout: Overview");
    }

    public void clickOnFinish () {
        driver.findElement(By.xpath("//button[@name='finish']")).click();
    }

    public double getItemTotal () {

        double totalPrice = 0;

        totalPrice = Double.parseDouble(driver.findElement(
                     By.className("summary_subtotal_label")).getText().substring(13));

        return totalPrice;
    }

    public void closePage () {
        this.driver.close();
    }
}
