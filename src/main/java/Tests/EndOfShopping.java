package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EndOfShopping extends BaseTest {

    @Test
    public static void testAddingProducts() {

        double cartTotalPrice = 0;
        String[] items = {"Sauce Labs Bolt T-Shirt", "Sauce Labs Bike Light"};

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.login();

        ProductsPage productsPage = new ProductsPage(driver);

        Assert.assertEquals(productsPage.isDisplayed(), true, "Login failed");

        for (int i = 0; i < items.length; i++){
            productsPage.addItemToCart(items[i]);
            cartTotalPrice += productsPage.getItemPrice(items[i]);
        }

        productsPage.clickOnCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartPage.isDisplayed(), true, "Cart Page is not displayed");

        Assert.assertEquals(cartTotalPrice == cartPage.getTotalPrice(), true, "Price is not correct");

        boolean itemsInCart = false;

        for (int i = 0; i < items.length; i++){
            if (cartPage.verifyItem(items[i])){
                itemsInCart = true;
            }else{
                itemsInCart = false;
                break;
            }
        }

        Assert.assertEquals(itemsInCart, true, "Items are not added to cart");

        cartPage.clickOnCheckout();

        CheckoutStepOne checkoutStepOne = new CheckoutStepOne(driver);

        Assert.assertEquals(checkoutStepOne.isDisplayed(), true, "Check Out Step One not displayed");

        checkoutStepOne.inputInfo();

        CheckoutStepTwo checkoutStepTwo = new CheckoutStepTwo(driver);

        Assert.assertEquals(checkoutStepTwo.isDisplayed(), true, "Check Out Step Two not displayed");

        Assert.assertEquals(cartTotalPrice == checkoutStepTwo.getItemTotal(), true, "Item Total is not correct");

        checkoutStepTwo.clickOnFinish();

        CheckoutComplete checkoutComplete = new CheckoutComplete(driver);

        Assert.assertEquals(checkoutComplete.isDisplayed(), true, "Check Out Complete not displayed");

    }

}
