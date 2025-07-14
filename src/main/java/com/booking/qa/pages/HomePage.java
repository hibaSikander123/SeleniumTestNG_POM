package com.booking.qa.pages;

import com.booking.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends TestBase {

    private By signInButtonLocator = By.xpath("//a[@data-testid = 'header-sign-in-button']");

    public HomePage() {
        PageFactory.initElements(driver, this);
    }

    public SigninPage clickSigninButton() {
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        signInButton.click();
        return new SigninPage();
    }
}
