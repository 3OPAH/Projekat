package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;

public class CartPage extends BasePage{

    private String url;

    public CartPage(WebDriver driver) {
        super(driver);
        this.url = "https://www.saucedemo.com/cart.html";
    }

    public CartPage() {}

    public void openPage () {
        driver.get(this.url);
    }

    public boolean isDisplayed () {
        return driver.findElement(By.className("title")).getText().equalsIgnoreCase("Your Cart");
    }

    public void clickOnCheckout(){
        driver.findElement(By.xpath("//button[@name='checkout']")).click();
    }

    public boolean verifyItem (String itemName) {
        boolean itemInCart = false;

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        for (int i = 0; i < cartItems.size(); i++){
            String cartItem = cartItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
            if (itemName.equals(cartItem)){
                itemInCart = true;
                break;
            }
        }

        return itemInCart;
    }

    public void removeItem (String itemName) {

        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));
        String cartItem;

        for (int i = 0; i < cartItems.size(); i++) {
            cartItem = cartItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
            if (itemName.equals(cartItem)) {
                cartItems.get(i).findElement(By.xpath(".//button")).click();
            }
        }
    }

    public double getTotalPrice () {

        double totalPrice = 0;
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        for (int i = 0; i < cartItems.size(); i++){
            totalPrice += Double.parseDouble(cartItems.get(i).findElement(
                    By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));
        }

        return totalPrice;
    }

    public HashMap<String, Integer> verifyNumberOfItemsInCart () {

        HashMap<String, Integer> cartProducts = new HashMap<>();
        List<WebElement> cartItems = driver.findElements(By.className("cart_item"));

        for (int i = 0; i < cartItems.size(); i++){
            String cartItem = cartItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
            if (cartProducts.containsKey(cartItem)){
                int tempCount = cartProducts.get(cartItem);
                cartProducts.put(cartItem, tempCount + 1);
            }
            else{
                cartProducts.put(cartItem, 1);
            }

        }

        return cartProducts;
    }

    public void closePage() {
        driver.quit();
    }
}