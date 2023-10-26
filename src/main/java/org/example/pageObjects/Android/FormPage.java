package org.example.pageObjects.Android;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.example.utils.AndroidGestures;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class FormPage extends AndroidGestures {
    AndroidDriver driver;
    public FormPage(AndroidDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    @AndroidFindBy(id="com.androidsample.generalstore:id/toolbar_title")
    private WebElement headerTitle;

    @AndroidFindBy(id="com.androidsample.generalstore:id/spinnerCountry")
    private WebElement countryDropDown;

    @AndroidFindBy(id="com.androidsample.generalstore:id/nameField")
    private WebElement nameField;

    @AndroidFindBy(id="com.androidsample.generalstore:id/radioMale")
    private WebElement maleGender;

    @AndroidFindBy(id="com.androidsample.generalstore:id/radioFemale")
    private WebElement femaleGender;

    @AndroidFindBy(id="com.androidsample.generalstore:id/btnLetsShop")
    private WebElement shopCTA;

    public void setNameField(String name){
        nameField.sendKeys(name);
    }

    public void setGender(String gender){
        if (gender.toLowerCase().contains("female")){
            femaleGender.click();
        }
        else {
            maleGender.click();
        }
    }

    public void setCountrySelection(String country){
        countryDropDown.click();
        scrollToText(country);
        driver.findElement(By.xpath("//android.widget.TextView[@text=\""+country+"\"]")).click();
    }

    public ProductCatalogue submitForm(){
        shopCTA.click();
        return new ProductCatalogue(driver);
    }
}
