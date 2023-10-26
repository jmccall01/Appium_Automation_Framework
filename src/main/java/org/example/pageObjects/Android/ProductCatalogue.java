package org.example.pageObjects.Android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidGestures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class ProductCatalogue extends AndroidGestures {
    AndroidDriver driver;
    public ProductCatalogue(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
    private List<WebElement> addToCartCTA;

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
    private WebElement cart;

    public void addItemToCartByIndex(int i){
        addToCartCTA.get(i).click();
    }

    public CartPage goToCartPage(){
        cart.click();
        return new CartPage(driver);
    }
}
