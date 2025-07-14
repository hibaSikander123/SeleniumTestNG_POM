package com.booking.qa.testcases;

import com.booking.qa.base.TestBase;
import com.booking.qa.pages.HomePage;
import com.booking.qa.pages.SigninPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class HomeTest extends TestBase {

    protected HomePage homePage;
    protected SigninPage signinPage;


    @BeforeClass(alwaysRun = true)
    public void setUpBase() {
        initialization();
        homePage = new HomePage();
        signinPage = homePage.clickSigninButton();

        // Perform assertion once
        assertSigninPageTitle();
    }

    public void assertSigninPageTitle() {
        String actualTitle = signinPage.validateSignInTitle();
        Assert.assertEquals(actualTitle, "Sign in or create an account", "Signin page title mismatch!");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        quitDriver();
    }
}
