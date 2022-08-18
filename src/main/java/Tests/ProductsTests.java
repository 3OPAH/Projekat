package Tests;

import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashMap;

public class ProductsTests extends BaseTest{

    @Test
    public static void testAddingProducts() {

        String[] items = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Onesie", "Sauce Labs Bike Light"};

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login();

        ProductsPage productsPage = new ProductsPage(driver);

        for (int i = 0; i < items.length; i++){
            productsPage.addItemToCart(items[i]);
        }

        productsPage.clickOnCart();

        CartPage cartPage = new CartPage(driver);

        boolean itemInCart = false;

        for (int i = 0; i < items.length; i++){
            if (cartPage.verifyItem(items[i])){
                itemInCart = true;
            }else{
                itemInCart = false;
                break;
            }
        }

        Assert.assertEquals(itemInCart, true, "Items are not added to cart");

    }

    @Test
    public static void testAddingCheapestProductWithSort() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();

        //delete cookies from domen www.saucedemo.com
        driver.navigate().to("edge://settings/siteData");
        driver.findElement(By.xpath("//button[contains(@aria-labelledby, 'domain_saucedemo.com')]")).click();
        driver.findElement(By.xpath("//button[@title='Remove www.saucedemo.com']")).click();

        loginPage.openPage();
        loginPage.login();

        CartPage cartPage = new CartPage(driver);
        cartPage.openPage();
        HashMap<String, Integer> cartList = cartPage.verifyNumberOfItemsInCart();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openPage();

        String cheapest = productsPage.addCheapestItem();

        productsPage.clickOnCart();
        HashMap<String, Integer> cartListAfter = cartPage.verifyNumberOfItemsInCart();

        if (!cartList.containsKey(cheapest) && !cheapest.equals("")){
            Assert.assertEquals(cartListAfter.get(cheapest), 1, "Item is not added");
        }
        else if (cartList.containsKey(cheapest) && !cheapest.equals("")){
            Assert.assertEquals(cartListAfter.get(cheapest), cartList.get(cheapest) + 1, "There is more then 1 item in cart");
        }
        else{
            Assert.assertEquals(productsPage.listIsSortedFromLowToHigh(), true, "List is not sorted");
        }

    }

    @Test
    public static void testAddingCheapestProductWithoutSort() {

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();

        //delete cookies from domen www.saucedemo.com
        driver.navigate().to("edge://settings/siteData");
        driver.findElement(By.xpath("//button[contains(@aria-labelledby, 'domain_saucedemo.com')]")).click();
        driver.findElement(By.xpath("//button[@title='Remove www.saucedemo.com']")).click();

        loginPage.openPage();
        loginPage.login();

        CartPage cartPage = new CartPage(driver);
        cartPage.openPage();
        HashMap<String, Integer> cartList = cartPage.verifyNumberOfItemsInCart();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openPage();

        WebElement cheapest = productsPage.getCheapestItem();
        String cheapestItem = productsPage.addItemToCart(cheapest);

        cartPage.openPage();
        HashMap<String, Integer> cartListAfter = cartPage.verifyNumberOfItemsInCart();

        if (!cartList.containsKey(cheapestItem)){
            Assert.assertEquals(cartListAfter.get(cheapestItem), 1, "Item was not added");
        }
        else{
            Assert.assertEquals(cartListAfter.get(cheapestItem), cartList.get(cheapestItem) + 1, "Item was already added");
        }

    }
}
