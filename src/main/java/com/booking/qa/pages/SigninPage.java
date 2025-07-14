package com.booking.qa.pages;

import com.booking.qa.base.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;

public class SigninPage extends TestBase {

    String firstTab;

    // Page Factory - Object Repository: ;
    private By emailAddress = By.id("username");

    private By contWithEmailBtn = By.xpath("//span[text() = \"Continue with email\"]");

    @FindBy(xpath = "//span[text() =\"Sign in or create an account\"]")
    WebElement textAfterSigninBtn;

    // Initializing page factory/ page objects
    public SigninPage() {
        PageFactory.initElements(driver, this);
        firstTab = driver.getWindowHandle();

    }

    // Actions
    public String validateSignInTitle() {
        return textAfterSigninBtn.getText();
    }

    public VerificationCodePage signin(String eField){
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(emailAddress));
        WebElement contWithEmailButton = wait.until(ExpectedConditions.elementToBeClickable(contWithEmailBtn));
        emailField.sendKeys(eField);
        contWithEmailButton.click();

        return new VerificationCodePage();
    }

    public EmailLoginPage newTab(){

        // Switch to Tab 2 (Email Login) --- workaround for new tab using javascript due to CDP version issue
        ((JavascriptExecutor) driver).executeScript("window.open('https://account.proton.me/mail','_blank');");
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
      //  driver.get("https://account.proton.me/mail");

        return new EmailLoginPage();
    }
}
