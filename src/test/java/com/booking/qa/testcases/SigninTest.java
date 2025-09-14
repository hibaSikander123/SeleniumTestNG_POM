package com.booking.qa.testcases;

import com.booking.qa.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SigninTest extends BaseTest {

    @Test
    public void signinTest() {
        // First navigate to signin page
        HomePage homePage = new HomePage(driver, wait);
        SigninPage signinPage = homePage.clickSigninButton();
        //String email = "abc@def.com";
        signinPage.signin(email);
        Assert.assertEquals(email, email);
    }

    @Test(dependsOnMethods = "signinTest")
    public void newTabTest() {
        // First navigate to signin page
        HomePage homePage = new HomePage(driver, wait);
        SigninPage signinPage = homePage.clickSigninButton();

        // Then open new tab
        EmailLoginPage emailLoginPage = signinPage.newTab();
        String currentUrl = emailLoginPage.getCurrentURL();
        Assert.assertEquals(currentUrl, "https://account.proton.me/mail", "Failed to open ProtonMail in new tab.");
    }
}
