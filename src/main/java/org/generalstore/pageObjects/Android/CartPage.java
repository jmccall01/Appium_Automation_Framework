package org.generalstore.pageObjects.Android;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.generalstore.utils.AndroidActions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class CartPage extends AndroidActions {
    AndroidDriver driver;
    public CartPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/appbar_btn_back")
    private WebElement backCTA;

    @AndroidFindBy(xpath="//android.widget.ImageView[@class='android.widget.ImageView'")
    private List<WebElement> productImages;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id='com.androidsample.generalstore:id/productName'")
    private List<WebElement> productNames;

    @AndroidFindBy(id = "com.androidsample.generalstore:id/productPrice")
    private List<WebElement> productPrices;

    @AndroidFindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
    private WebElement totalPrice;

    @AndroidFindBy(className = "android.widget.CheckBox")
    private WebElement emailCheckBox;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnProceed")
    private WebElement completePurchase;

    public void goBack(){
        backCTA.click();
    }

    public double getProductSum() {
        double totalSum = 0;
        for (int i = 0; i < productPrices.size(); i++) {
            Double price = getFormattedAmount(productPrices.get(i).getText());
            totalSum = totalSum + price;
        }
        return totalSum;
    }

    public double getTotalPrice(){
        double totalPriceShown = getFormattedAmount(totalPrice.getText());
        return totalPriceShown;
    }

    public void checkout(){
        completePurchase.click();
    }

}
