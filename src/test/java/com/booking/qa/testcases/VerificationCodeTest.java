package com.booking.qa.testcases;

import com.booking.qa.pages.VerificationCodePage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class VerificationCodeTest extends BaseTest {
    private VerificationCodePage verificationCodePage;
    String vCode;
    String verifyVCodeAccepted;

    @BeforeMethod
    public void settingUp() {
        verificationCodePage = new VerificationCodePage(driver, wait);
    }

    @Test(dependsOnMethods = "com.booking.qa.testcases.SigninTest.newTabTest")
    public void emailLoginTest()
    {
        verificationCodePage.loginEmail(userEmail, password);
        Assert.assertEquals(userEmail, userEmail);
        Assert.assertEquals(password, password);
        String validateLogin = verificationCodePage.validateLoginEmail();
        Assert.assertEquals(validateLogin, "Inbox", "Email or password is incorrect!");
    }

    @Test(dependsOnMethods = "emailLoginTest")
    public void emailInboxStabilityTest()
    {
        verificationCodePage.refreshEmailInbox();
        String verifyInboxStability = verificationCodePage.fetchSenderName();
        Assert.assertEquals(verifyInboxStability, "Booking.com", "Inbox stability is incorrect!");
    }


    @Test(dependsOnMethods = "emailInboxStabilityTest")
    public void goToInboxTest() {
        verificationCodePage.goToLatestInbox();
        String verifyLatestEmail = verificationCodePage.fetchLatestInboxTitle();
        Assert.assertEquals(verifyLatestEmail, "Booking.com", "Unable to reached to the latest inbox");
    }

    @Test(dependsOnMethods = "goToInboxTest")
    public void fetchVerificationCodeTest() {
        vCode = verificationCodePage.getVerificationCode();
        Assert.assertNotNull(vCode, "Unable to fetch the verification code");
        System.out.println("Stored Verification Code: " + vCode);
    }

    @Test(dependsOnMethods = "fetchVerificationCodeTest")
    public void feedVCodeTest() {
        verificationCodePage.setVerificationCode(vCode);
        verifyVCodeAccepted = verificationCodePage.verifyVCodeAcceptance();
        Assert.assertEquals(verifyVCodeAccepted, "Flights", "Verification has not been successful");
    }
}
