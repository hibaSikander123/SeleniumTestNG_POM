package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class VerificationCodePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private WebDriverWait emailWait;

    private By usernameEmail = By.id("username");
    private By passwordEmail = By.id("password");
    private By submitEmail = By.xpath("//button[@type = 'submit']");
    private By iframeLocator = By.cssSelector("iframe[data-testid='content-iframe']");
    private By codeElement = By.xpath("//p[contains(., 'Dear') or contains(., 'Hi')]/parent::span/child::table/tbody/tr/td");
    private By verifyButton = By.xpath("//button[@type = \"submit\"]");
    private By latestEmail= By.xpath("//span[@data-testid='recipients:sender']//bdi[@data-testid='recipient-label']");
    private By loggedInPageTitle = By.xpath("//div[@data-testid='hero-banner-solid-bg-desktop']//span[@data-testid='herobanner-title1']");
    private By emailInbox = By.xpath("//div[@class = 'w-full shrink-0']//div[@style='--index: 0;']");

    @FindBy(xpath = "//div[@class = \"w-full\"]//h2[@title = \"Inbox\"]")
    WebElement inboxTitle;

    public VerificationCodePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        this.emailWait = new WebDriverWait(driver, Duration.ofSeconds(40));
        PageFactory.initElements(driver, this);
    }

    public String getCurrentURL() {
        return driver.getCurrentUrl();
    }

    public VerificationCodePage loginEmail(String userEmail, String password) {
        WebElement userEmailField = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameEmail));
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordEmail));
        WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(submitEmail));
        userEmailField.sendKeys(userEmail);
        passwordField.sendKeys(password);
        submitBtn.click();
        return new VerificationCodePage(driver, wait);
    }

    public String validateLoginEmail() {
        return inboxTitle.getText();
    }

    public void goToLatestInbox() {
        driver.navigate().refresh();
        WebElement emailMsg = emailWait.until(ExpectedConditions.elementToBeClickable(emailInbox));
        emailMsg.click();
    }

    public String fetchLatestInboxTitle() {
        WebElement latestEmailTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(latestEmail));
        return latestEmailTitle.getText();
    }

    public String getVerificationCode() {
        WebElement iframe = driver.findElement(iframeLocator);
        driver.switchTo().frame(iframe);
        WebElement code = driver.findElement(codeElement);
        String verificationCode = code.getText();
        driver.switchTo().defaultContent();
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(0));
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        for (int i = 0; i < verificationCode.length(); i++) {
            String codeChar = String.valueOf(verificationCode.charAt(i));
            WebElement input = driver.findElement(By.name("code_" + (i)));
            input.sendKeys(codeChar);
        }
        driver.findElement(verifyButton).click();
    }

    public String verifyVCodeAcceptance() {
        WebElement pageTitle = wait.until(ExpectedConditions.visibilityOfElementLocated(loggedInPageTitle));
        return pageTitle.getText();
    }
    }