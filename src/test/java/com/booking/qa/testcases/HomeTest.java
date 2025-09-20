package com.booking.qa.testcases;
import com.booking.qa.pages.HomePage;
import com.booking.qa.pages.SigninPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void assertSigninPageTitle() {
        HomePage homePage = new HomePage(driver, wait);
        SigninPage signinPage = homePage.clickSigninButton();
        String actualTitle = signinPage.validateSignInTitle();
        Assert.assertEquals(actualTitle, "Sign in or create an account", "Signin page title mismatch!");
    }
}
