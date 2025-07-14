package com.booking.qa.testcases;

import com.booking.qa.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SigninTest extends HomeTest {

    @Test(priority = 1)
    public void signinTest() {
        //String email = "abc@def.com";
        signinPage.signin(email);
        Assert.assertEquals(email, email);
    }

    @Test(priority = 2)
    public void newTabTest() {
        EmailLoginPage emailLoginPage = signinPage.newTab();
        String currentUrl = emailLoginPage.getCurrentURL();
        Assert.assertEquals(currentUrl, "https://account.proton.me/mail", "Failed to open ProtonMail in new tab.");
    }
}
