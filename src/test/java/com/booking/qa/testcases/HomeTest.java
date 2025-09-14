package com.booking.qa.testcases;

import com.booking.qa.base.TestBase;
import com.booking.qa.pages.HomePage;
import com.booking.qa.pages.SigninPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

  //  protected HomePage homePage;
    //protected SigninPage signinPage;


    /*@BeforeClass(alwaysRun = true)
    public void setUpBase() {
        initialization();
        homePage = new HomePage();
        signinPage = homePage.clickSigninButton();

        // Perform assertion once
        assertSigninPageTitle();
    }
*/
    @Test
    public void assertSigninPageTitle() {
        HomePage homePage = new HomePage(driver, wait);
        SigninPage signinPage = homePage.clickSigninButton();

        // Perform assertion
        String actualTitle = signinPage.validateSignInTitle();
        Assert.assertEquals(actualTitle, "Sign in or create an account", "Signin page title mismatch!");
    }

}
