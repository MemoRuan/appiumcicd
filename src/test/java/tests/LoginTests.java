package tests;

import base.BaseTest;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.*;
import page.LoginPage;
import page.ProductsPage;

import java.io.InputStream;
import java.lang.reflect.Method;

public class LoginTests extends BaseTest {
    LoginPage loginPage;
    ProductsPage productsPage;
/*    JSONObject loginUsers;
    TestUtils utils = new TestUtils();
    // Reading the data from the JSON file
    @BeforeClass
    public void beforeClass() throws Exception {
        InputStream datais = null;
        try {
            String dataFileName = "data/loginUsers.json";
            datais = getClass().getClassLoader().getResourceAsStream(dataFileName);
            JSONTokener tokener = new JSONTokener(datais);
            loginUsers = new JSONObject(tokener);
        } catch(Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if(datais != null) {
                datais.close();
            }
        }
		  /*
		  To ensure each test starts with a fresh app state, closeApp() and launchApp() have been moved to the
		  @BeforeMethod section.
		  // closeApp();
		  // launchApp();

    }
*/
    @BeforeClass
    public void beforeClass(){
    }
    @AfterClass
    public void afterClass() {
    }

    @BeforeMethod
    public void beforeMethod(Method m) {
        closeApp();
        launchApp();
//        utils.log().info("\n" + "****** starting test:" + m.getName() + "******" + "\n");
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void afterMethod() {
    }

    @Test
    public void invalidUserName() {
// with the listener we dont need the try/catch anymore		  try {
        loginPage.enterUserName("invalidUsername");//loginUsers.getJSONObject("invalidUser").getString("username"));
        loginPage.enterPassword("secret_sauce");//loginUsers.getJSONObject("invalidUser").getString("password"));
        loginPage.pressLoginBtn();

        String actualErrTxt = loginPage.getErrTxt();
        String expectedErrTxt = "El usuario y contraseña no coinciden con ningun usuario en este servicio.";//getStrings().get("err_invalid_username_or_password");

        Assert.assertEquals(actualErrTxt, expectedErrTxt);
    }/*catch(Exception e){
			  //Follow lines are to print the whole StackTrace in the TestNG results
			  //Not recomended to use it manually in all the tc, but instead use a Listener!
			  StringWriter sw = new StringWriter();
			  PrintWriter pw = new PrintWriter(sw);
			  System.out.println("pw.toString() = " + pw.toString());
			  e.printStackTrace();
			  Assert.fail(sw.toString());
		  }
	  }*/

    @Test
    public void invalidPassword() {
        loginPage.enterUserName("standard_user");//loginUsers.getJSONObject("invalidPassword").getString("username"));
        loginPage.enterPassword("invalidPassword");//loginUsers.getJSONObject("invalidPassword").getString("password"));
        loginPage.pressLoginBtn();

        String actualErrTxt = loginPage.getErrTxt();
        String expectedErrTxt = "El usuario y contraseña no coinciden con ningun usuario en este servicio.";//getStrings().get("err_invalid_username_or_password");

        Assert.assertEquals(actualErrTxt, expectedErrTxt);
    }

    @Test
    public void successfulLogin() {
        loginPage.enterUserName("standard_user");//loginUsers.getJSONObject("validUser").getString("username"));
        loginPage.enterPassword("secret_sauce");//loginUsers.getJSONObject("validUser").getString("password"));
        productsPage = loginPage.pressLoginBtn();

        String actualProductTitle = productsPage.getTitle();
        String expectedProductTitle = "PRODUCTOS";//getStrings().get("product_title");

        Assert.assertEquals(actualProductTitle, expectedProductTitle);
    }
}
