package main.java.mobile.PageWebelements.app;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;

public class UserDataForm {

    @AndroidFindBy(id = "android:id/text1")
    public AndroidElement countrydropDown;
    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Enter name here']")
    public AndroidElement customerName;
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Male']")
    public AndroidElement maleRadiobutton;
    @AndroidFindBy(xpath = "//android.widget.RadioButton[@text='Female']")
    public AndroidElement femaleRadiobutton;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/btnLetsShop")
    public AndroidElement clickShopbutton;
    @AndroidFindBy(id = "com.androidsample.generalstore:id/toolbar_title")
    public AndroidElement pageHeader;
    @AndroidFindBy(xpath = "//android.widget.Toast[1]")
    public AndroidElement toastMessage;
    AndroidDriver<AndroidElement> driver;

    public UserDataForm(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }
}