package page;

import base.BaseTest;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage extends BaseTest {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"PRODUCTOS\"]")
    @iOSXCUITFindBy(xpath ="//XCUIElementTypeOther[@name=\"test-Zona de caída del carrito de compras\"]/parent::*[1]/preceding-sibling::*[1]")
    private WebElement productTitleTxt;

    @AndroidFindBy (xpath = "//android.widget.TextView[@content-desc=\"test-Titulo de articulo\" and @text=\"Mochila Sauce Labs\"]")
    @iOSXCUITFindBy (xpath = "(//XCUIElementTypeStaticText[@name=\"test-Item title\"])[1]")
    private WebElement SLBTitle;

    @AndroidFindBy (xpath = "(//android.widget.TextView[@content-desc=\"test-Precio\"])[2]")
    @iOSXCUITFindBy (xpath = "(//XCUIElementTypeStaticText[@name=\"test-Precio\"])[1]")
    private WebElement SLBPrice;

    public ProductsPage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public String getTitle() {
        String title = getText(productTitleTxt);//, "product page title is - ");
        return title;
    }

    public String getSLBTitle() {
        String title = getText(SLBTitle);//, "title is - ");
        return title;
    }

    public String getSLBPrice() {
        String price = getText(SLBPrice);//, "price is - ");
        return price;
    }

    public ProductDetailsPage pressSLBTitle() {
        click(SLBTitle);//, "press SLB tile link");
        return new ProductDetailsPage();
    }

    public String scrollToSLBTitleAndGetSLBTitle() {
        return getText(scrollToElement("text","Mochila Sauce Labs"));//, "");
    }
}
