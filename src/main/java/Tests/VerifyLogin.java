package Tests;

import Pages.LoginPage;
import Pages.ProductsPage;
import Provider.UserNameProvider;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyLogin extends BaseTest{

    @Test(dataProvider = "UserNameProvider", dataProviderClass = UserNameProvider.class)
    public void VerifyLoginWithValidCredentials(String userName, String password) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickOnLogin();

        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertEquals(productsPage.isDisplayed(), true, "Login failed");

    }

    @Test
    public void VerifyLoginWithoutPassword() {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.setUserName("standard_user");
        loginPage.clickOnLogin();

        List<WebElement> errorMsg = driver.findElements(By.className("error-message-container"));

        Assert.assertEquals(errorMsg.size() == 1, true, "Login successful");

    }

    @Test(dataProvider = "InValidUserNameProvider", dataProviderClass = UserNameProvider.class)
    public void VerifyLoginWithInValidCredentials(String userName, String password) {

        LoginPage loginPage = new LoginPage(driver);

        loginPage.openPage();
        loginPage.setUserName(userName);
        loginPage.setPassword(password);
        loginPage.clickOnLogin();

        List<WebElement> errorMsg = driver.findElements(By.className("error-message-container"));

        Assert.assertEquals(errorMsg.size() == 1, true, "Login successful");

    }

}