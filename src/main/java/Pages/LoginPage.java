package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends  BasePage{

    private String userName = "standard_user";
    private String password = "secret_sauce";
    private String url;

    public LoginPage(WebDriver driver) {
        super(driver);
        this.url = "https://www.saucedemo.com/";
    }


    public LoginPage() {}

    public void openPage() {
        driver.get(this.url);
    }

    public void login () {
        this.setUserName(userName);
        this.setPassword(password);
        this.clickOnLogin();
    }

    public void setUserName(String userName) {
        driver.findElement(By.id("user-name")).sendKeys(userName);
    }

    public void setPassword(String password) {
        driver.findElement(By.id("password")).sendKeys(password);
    }

    public void clickOnLogin() {
        driver.findElement(By.id("login-button")).click();
    }

    public boolean errorMessage () {
        return driver.findElement(By.xpath("//h3[@data-test='error']")).getText().contains("Epic sadface");
    }

    public void closePage() {
        driver.quit();
    }

}
