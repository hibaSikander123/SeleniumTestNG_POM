package com.booking.qa.testcases;

import com.booking.qa.pages.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SigninTest extends BaseTest {
    private SigninPage signinPage;

    @BeforeMethod
    public void settingUp() {
        signinPage = new SigninPage(driver, wait);
    }

    @Test(dependsOnMethods = "com.booking.qa.testcases.HomeTest.assertSigninPageTitle")
    public void signinTest() {
        signinPage.signin(userEmail);
        Assert.assertEquals(userEmail, userEmail);
    }

    @Test(dependsOnMethods = "signinTest")
    public void newTabTest() {
        VerificationCodePage verificationCodePage = signinPage.newTab();
        String currentUrl = verificationCodePage.getCurrentURL();
        Assert.assertEquals(currentUrl, "https://account.proton.me/mail", "Failed to open ProtonMail in new tab.");
    }
}
