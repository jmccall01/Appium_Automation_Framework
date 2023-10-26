package org.example;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.example.pageObjects.Android.CartPage;
import org.example.pageObjects.Android.FormPage;
import org.example.pageObjects.Android.ProductCatalogue;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.text.Normalizer;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import static java.time.Duration.*;

public class journey1 extends Basis{
    /*
     * Here are the E2E journey tests as I was learning Appium
     * The final piece using an actual automation framework is at the bottom of the page - called 'checkoutFramework'


    @Test
    public void FillForm() {
        driver.findElement(By.id("android:id/text1")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Belgium\"));"));
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text='Belgium']")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Josh McCall");
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
    }

    @Test
    public void ErrorCatch() {
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        String errorMessage = driver.findElement(By.xpath("//android.widget.Toast[1]")).getText();
        Assert.assertEquals(errorMessage, "Please enter your name");
    }

    @Test
    public void Checkout() throws InterruptedException {
        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("Josh");
        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"LeBron Soldier 12 \"));"));
        int productCount = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).size();
        for(int i=0;i<productCount;i++){
            String productName = driver.findElements(By.id("com.androidsample.generalstore:id/productName")).get(i).getText();
            if (productName.equalsIgnoreCase("LeBron Soldier 12 ")) {
                driver.findElements(By.id("com.androidsample.generalstore:id/productAddCart")).get(i).click();
            }
        }
        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
        //wait until page is displayed, example of an explicit wait
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
        //wait until an expected condition is shown -- eg. attributeContains(element, attribute, value)
        wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")), "text", "Cart"));
        String baggedItem = driver.findElement(By.id("com.androidsample.generalstore:id/productName")).getText();
        Assert.assertEquals(baggedItem, "LeBron Soldier 12 ");
        //Check that the price is equal
        List<WebElement> productPrices = driver.findElements(By.id("com.androidsample.generalstore:id/productPrice"));
        int count = productPrices.size();
        double totalSum = 0;
        for (int i=0; i<count; i++){
            String amountString = productPrices.get(i).getText();
            Double price = getFormattedAmount(amountString);
            totalSum= totalSum + price;
        }
        String orderTotal = driver.findElement(By.id("com.androidsample.generalstore:id/totalAmountLbl")).getText();
        Double totalAmount = getFormattedAmount(orderTotal);
        Assert.assertEquals(totalSum, totalAmount);
        //Go to webview checkout (as App is hybrid)
        driver.findElement(AppiumBy.className("android.widget.CheckBox")).click();
        driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
        Thread.sleep(6000);

        //Now we need for the driver to be able to handle web events
        //This is a method to get a list of the contexts, which you can then set to the driver
        /* Web automation - google cookies pop up preventing tests from passing

        Set<String> contexts = driver.getContextHandles();
        for (String cName :contexts){
            System.out.println(cName);
        }

        driver.context("WEBVIEW_com.androidsample.generalstore");
        driver.findElement(By.name("q")).sendKeys("Gennady Golovkin");
        driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP");

     */

    @Test
    public void checkoutFramework(){
        //First page - form page
        formPage.setNameField("Josh");
        formPage.setGender("Male");
        formPage.setCountrySelection("Argentina");
        //driver from submitForm return value
        ProductCatalogue productCatalogue = formPage.submitForm();
        //product catalogue page
        productCatalogue.addItemToCartByIndex(0);
        productCatalogue.addItemToCartByIndex(0);
        //cartPage driver comes from return value of goToCartPage()
        CartPage cartPage = productCatalogue.goToCartPage();
        //cart
        double totalSum = cartPage.getProductSum();
        double totalPrice = cartPage.getTotalPrice();
        Assert.assertEquals(totalSum, totalPrice);
        cartPage.checkout();
    }
}
