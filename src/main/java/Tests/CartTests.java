package Tests;

import Pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartTests extends BaseTest{

    @Test
    public static void testAddingProducts() {

        String[] items = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket"};


        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.deleteAllCookes();
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

        for (int i = 0; i < items.length; i++){
            cartPage.removeItem(items[i]);
        }

        boolean itemRemovedFromCart = false;

        for (int i = 0; i < items.length; i++){
            if (!cartPage.verifyItem(items[i])){
                itemRemovedFromCart = true;
            }else{
                itemRemovedFromCart = false;
                break;
            }
        }

        Assert.assertEquals(itemRemovedFromCart, true, "Items are not removed from cart");
    }

    @Test
    public static void verifyPrice() {
        double cartTotalPrice = 0;

        String[] items = {"Sauce Labs Backpack", "Sauce Labs Fleece Jacket"};

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.deleteAllCookes();
        loginPage.login();

        ProductsPage productsPage = new ProductsPage(driver);

        for (int i = 0; i < items.length; i++){
            productsPage.addItemToCart(items[i]);
            cartTotalPrice += productsPage.getItemPrice(items[i]);
        }

        productsPage.clickOnCart();

        CartPage cartPage = new CartPage(driver);

        Assert.assertEquals(cartTotalPrice == cartPage.getTotalPrice(), true, "Price is not correct");

    }

    @Test
    public static void verifyItemTotal() {
        double cartTotalPrice = 0;

        String[] items = {"Test.allTheThings() T-Shirt (Red)", "Sauce Labs Backpack"};

        LoginPage loginPage = new LoginPage(driver);
        loginPage.openPage();
        loginPage.deleteAllCookes();
        loginPage.login();

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.openPage();

        for (int i = 0; i < items.length; i++){
            productsPage.addItemToCart(items[i]);
            cartTotalPrice += productsPage.getItemPrice(items[i]);
        }

        productsPage.clickOnCart();

        CartPage cartPage = new CartPage(driver);
        cartPage.clickOnCheckout();

        CheckoutStepOne checkoutStepOne = new CheckoutStepOne(driver);

        Assert.assertEquals(checkoutStepOne.isDisplayed(), true, "Check Out Step One not displayed");

        checkoutStepOne.inputInfo();

        CheckoutStepTwo checkoutStepTwo = new CheckoutStepTwo(driver);

        Assert.assertEquals(checkoutStepTwo.isDisplayed(), true, "Check Out Step Two not displayed");

        Assert.assertEquals(cartTotalPrice == checkoutStepTwo.getItemTotal(), true, "Item Total is not correct");

    }
}
