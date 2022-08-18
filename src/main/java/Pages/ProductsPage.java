package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage{

    private String url;


    public ProductsPage (WebDriver driver) {
        super(driver);
        this.url = "https://www.saucedemo.com/inventory.html";
    }

    public ProductsPage(){}


    public void openPage() {
        driver.get(this.url);
    }

    public Boolean isDisplayed() {

        return driver.findElement(By.className("title")).getText().equalsIgnoreCase("Products");
    }

    public void clickOnCart () {
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    public void  sortItemsBy(String value) {
        driver.findElement(By.xpath(".//select[@class='product_sort_container']")).click();
        driver.findElement(By.xpath(".//option[text()='" + value + "']")).click();
    }


    public WebElement getCheapestItem (){
        List<WebElement> listInventoryItems = driver.findElements(By.className("inventory_item"));

        double lowestPrice = Double.parseDouble(listInventoryItems.get(0).findElement(
                By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));

        WebElement lowestPriceItem = null;

        for (int i = 1; i < listInventoryItems.size(); i++) {
            double price = Double.parseDouble(listInventoryItems.get(i).findElement(
                    By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));
            if (price < lowestPrice){
                lowestPrice = price;
                lowestPriceItem = listInventoryItems.get(i);
            }
        }
        return lowestPriceItem;
    }

    public String addItemToCart (WebElement item){

        if (item.findElement(By.xpath(".//button")).getText().equals("ADD TO CART")) {
            item.findElement(By.xpath(".//button")).click();
        }else{
            System.out.println("item is already in cart");
        }
        return item.findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
    }

    public void addItemToCart (String itemName) {

        List<WebElement> listInventoryItems = driver.findElements(By.className("inventory_item"));

        for (int i = 0; i < listInventoryItems.size(); i++) {
            if (listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"))
                                         .getText().equals(itemName)){
                if (listInventoryItems.get(i).findElement(By.xpath(".//button")).getText().equals("ADD TO CART")) {
                    listInventoryItems.get(i).findElement(By.xpath(".//button")).click();
                    break;
                }else{
                    System.out.println("item is already in cart");
                }
            }
        }
    }

    public double getItemPrice (String itemName){

        double price = 0;
        List<WebElement> listInventoryItems = driver.findElements(By.className("inventory_item"));

        for (int i = 0; i < listInventoryItems.size(); i++) {
            if (listInventoryItems.get(i).findElement(By.xpath(".//div[@class='inventory_item_name']"))
                    .getText().equals(itemName)) {
                price = Double.parseDouble(listInventoryItems.get(i).findElement(
                        By.xpath(".//div[@class='inventory_item_price']")).getText().substring(1));
                break;
            }
        }

        return price;
    }

    public boolean listIsSortedFromLowToHigh() {

        boolean sorted = true;

        List<WebElement> listInventoryItems = driver.findElements(By.className("inventory_item"));

        double lowestPrice = Double.parseDouble(listInventoryItems.get(0).
                            findElement(By.xpath(".//div[@class='inventory_item_price']")).
                            getText().substring(1));

        for (int i = 1; i < listInventoryItems.size(); i++){
            if (lowestPrice <= Double.parseDouble(listInventoryItems.get(i)
                            .findElement(By.xpath(".//div[@class='inventory_item_price']"))
                            .getText().substring(1))){
                lowestPrice = Double.parseDouble(listInventoryItems.get(i)
                            .findElement(By.xpath(".//div[@class='inventory_item_price']"))
                            .getText().substring(1));
            }else{
                sorted = false;
                break;
            }
        }

        return sorted;
    }

    public String addCheapestItem (){

        this.sortItemsBy("Price (low to high)");
        List<WebElement> listInventoryItems = driver.findElements(By.className("inventory_item"));

        if (this.listIsSortedFromLowToHigh()) {
            if (listInventoryItems.get(0).findElement(By.xpath(".//button")).getText().equals("ADD TO CART")) {
                listInventoryItems.get(0).findElement(By.xpath(".//button")).click();
            }else{
                System.out.println("item is already in cart");
            }
        }else{
            return "";
        }
        return listInventoryItems.get(0).findElement(By.xpath(".//div[@class='inventory_item_name']")).getText();
    }

    public void closePage() {
        driver.quit();
    }

}