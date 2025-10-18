package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;

public class SigninPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By emailAddress = By.id("username");
    private By contWithEmailBtn = By.xpath("//span[text() = \"Continue with email\"]");

    @FindBy(xpath = "//span[text() =\"Sign in or create an account\"]")
    WebElement textAfterSigninBtn;

    // Initializing page factory / page object
    public SigninPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    public String validateSignInTitle() {
        return textAfterSigninBtn.getText();
    }

    // Enter email address in signin page
    public VerificationCodePage signin(String eField){
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddress));
        WebElement contWithEmailButton = wait.until(ExpectedConditions.elementToBeClickable(contWithEmailBtn));
        emailField.sendKeys(eField);
        contWithEmailButton.click();
        return new VerificationCodePage(driver, wait);
    }

    public VerificationCodePage newTab(){
        // Switch to Tab 2 (for proton email login)
        // Opening and going to second tab using javascript (workaround used due to CDP version issue)
        ((JavascriptExecutor) driver).executeScript("window.open('https://account.proton.me/mail','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        return new VerificationCodePage(driver, wait);
    }
}
