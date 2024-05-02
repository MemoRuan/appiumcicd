package page;

import base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class SettingsPage extends BaseTest {
    @AndroidFindBy(accessibility="test-CERRAR SESION")
    @iOSXCUITFindBy(id = "test-LOGOUT")
    private WebElement logoutBtn;

    public SettingsPage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public LoginPage pressLogoutBtn() {
        click(logoutBtn, "press Logout button");
        return new LoginPage();
    }

}
