package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage{

    private WebDriver driver;
    private WebDriverWait wait;

    private By signInButtonLocator = By.xpath("//a[@data-testid = 'header-sign-in-button']");

    // Initializing page factory / page object
    public HomePage(WebDriver driver, WebDriverWait wait)
    {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // Click signin button to go to sign in page
    public SigninPage clickSigninButton() {
        WebElement signInButton = wait.until(ExpectedConditions.elementToBeClickable(signInButtonLocator));
        signInButton.click();
        return new SigninPage(driver, wait);
    }
}
