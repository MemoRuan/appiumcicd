package tests;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import page.LoginPage;
import page.ProductDetailsPage;
import page.ProductsPage;
import page.SettingsPage;

import java.lang.reflect.Method;

public class ProductTests extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;
    SettingsPage settingsPage;
    ProductDetailsPage productDetailsPage;

    @BeforeMethod
    public void beforeMethod(Method m) {
        closeApp();
        launchApp();
    }

    @Test
    public void validateProductOnProductsPage() {
        loginPage = new LoginPage();
        productsPage = loginPage.login("standard_user","secret_sauce");//loginUsers.getJSONObject("validUser").getString("username"),
                //loginUsers.getJSONObject("validUser").getString("password"));

        SoftAssert sa = new SoftAssert();
        String SLBTitle = productsPage.scrollToSLBTitleAndGetSLBTitle();//getSLBTitle();
        sa.assertEquals(SLBTitle, "Mochila Sauce Labs");//getStrings().get("products_page_slb_title"));

        String SLBPrice = productsPage.getSLBPrice();
        sa.assertEquals(SLBPrice, "$29.99");//getStrings().get("products_page_slb_price"));

        sa.assertAll();
    }

    @Test
    public void validateProductOnProductDetailsPage() {
        loginPage = new LoginPage();
        productsPage = loginPage.login("standard_user","secret_sauce");/*loginUsers.getJSONObject("validUser").getString("username"),
                loginUsers.getJSONObject("validUser").getString("password"));*/

        SoftAssert sa = new SoftAssert();
        String SLBTitle = productsPage.scrollToSLBTitleAndGetSLBTitle();
      //  productDetailsPage = productsPage.pressSLBTitle();
        //String SLBTitle = productDetailsPage.getSLBTitle();
        //scrollToElement("text", SLBTitle);
        sa.assertEquals(SLBTitle, "Mochila Sauce Labs");//getStrings().get("product_details_page_slb_title"));
        String SLBPrice = productsPage.getSLBPrice();//.scrollToSLBPriceAndGetSLBPrice();
        sa.assertEquals(SLBPrice, "$29.99");//getStrings().get("product_details_page_slb_price"));

        productDetailsPage = productsPage.pressSLBTitle();
       // String cartButton = productDetailsPage.scrollToSeeAddToCartButton();
        sa.assertTrue(productDetailsPage.getDescriptionTxt());
//        if(getPlatform().equalsIgnoreCase("Android")) {


//        }
/*        if(getPlatform().equalsIgnoreCase("iOS")) {
            String SLBTxt = productDetailsPage.getSLBTxt();
            sa.assertEquals(SLBTxt, "carry.allTheThings() with the sleek, streamlined Sly Pack that melds uncompromising style with unequaled laptop and tablet protection.");//getStrings().get("product_details_page_slb_txt"));

            productDetailsPage.scrollPage();
            sa.assertTrue(productDetailsPage.isAddToCartBtnDisplayed());
        }
        */
//		  productsPage = productDetailsPage.pressBackToProductsBtn(); // -> Commented as this is causing stale element exception for the Settings icon

        sa.assertAll();
    }
}