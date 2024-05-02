package page;

import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailsPage extends MenuPage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"REGRESO A PRODUCTOS\"]") //+"")
    @iOSXCUITFindBy(xpath = "//XCUIElementTypeOther[@name=\"test-Descripción\"]/child::XCUIElementTypeStaticText[1]")
    private WebElement SLBTitle;

    @AndroidFindBy (xpath = "//android.view.ViewGroup[@content-desc=\"test-Descripción\"]/android.widget.TextView[2]"
            + "")
    @iOSXCUITFindBy (xpath = "//XCUIElementTypeOther[@name=\"test-Descripción\"]/child::XCUIElementTypeStaticText[2]")
    private WebElement SLBTxt;

//	@AndroidFindBy (accessibility = "test-Price") private MobileElement SLBPrice;

    @AndroidFindBy (accessibility = "test-REGRESO A PRODUCTOS")
    @iOSXCUITFindBy (id = "test-REGRESO A PRODUCTOS")
    private WebElement backToProductsBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"AÑADIR A CARRITO\"]")
    @iOSXCUITFindBy (id = "test-AÑADIR A CARRITO")
    private WebElement addToCartBtn;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text=\"carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.\"]")
    private WebElement descriptionTxt;

    public ProductDetailsPage(){
        PageFactory.initElements(new AppiumFieldDecorator(getDriver()), this);
    }

    public String getSLBTitle() {
        String title = getText(SLBTitle);//, "title is - ");
        return title;
    }

    public String getSLBTxt() {
        String txt = getText(SLBTxt);//, "txt is - ");
        return txt;
    }

    /*
     * public String getSLBPrice() { String price = getText(SLBPrice);
     * utils.log("price is - " + price); return price; }
     */

    public String getSLBPrice() {
        return getText(scrollToElement("text","$29.99"));//, "");
    }
    public String scrollToSeeAddToCartButton() {
        return getText(scrollToElement("text","AÑADIR A CARRITO"));//, "");
    }
    public void scrollPage() {
       // iOSScrollToElement();
    }

    public Boolean getDescriptionTxt(){
        return descriptionTxt.isDisplayed();
    }

    public Boolean isAddToCartBtnDisplayed() {
        return addToCartBtn.isDisplayed();
    }

    public ProductsPage pressBackToProductsBtn() {
        click(backToProductsBtn, "navigate back to products page");
        return new ProductsPage();
    }
}
