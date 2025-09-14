package com.booking.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class VerificationCodePage  {

    private WebDriver driver;
    private WebDriverWait wait;

    public VerificationCodePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // Add methods for verification code page interactions here
}