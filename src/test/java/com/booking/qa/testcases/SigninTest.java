package com.booking.qa.testcases;

import com.booking.qa.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SigninTest extends BaseTest {

    @Test(dependsOnMethods = "com.booking.qa.testcases.HomeTest.assertSigninPageTitle")
    public void signinTest() {
        // First navigate to signin page
        SigninPage signinPage = new SigninPage(driver, wait);
        signinPage.signin(userEmail);
        Assert.assertEquals(userEmail, userEmail);
    }

    @Test(dependsOnMethods = "signinTest")
    public void newTabTest() {
        SigninPage signinPage = new SigninPage(driver, wait);
        // Open new tab
        VerificationCodePage verificationCodePage = signinPage.newTab();
        String currentUrl = verificationCodePage.getCurrentURL();
        Assert.assertEquals(currentUrl, "https://account.proton.me/mail", "Failed to open ProtonMail in new tab.");
    }
}
