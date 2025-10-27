package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class SelectFlightPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private  boolean flightFound;
    private  boolean contStandardBool;
    private By locatorViewDetailsBtn = By.xpath("//div[@id= 'flight-card-1' ]//span[contains(@class, 'Button-module__text') and contains(text(), 'View details')]");
    private By locatorSelectDetailsBtn = By.xpath("//div[@data-testid='flight_details_inner_modal_select_button']/button");
    private By locatorContinueBtn = By.xpath("//button[@data-testid = \"ticket_type_cta_flexible\" or @data-testid = \"branded_fare_cta_0\"]");
    private By locatorDialogueBox = By.xpath("//div[contains (@class, 'DismissibleContainer-module__root')]//button");

    @FindBy(xpath = "//span[contains(@class, 'Text-module__root--variant-body') and contains(.,'Cheapest')]")
    WebElement locatorCheapeastBtn;
    @FindBy(xpath = "//span[contains(@class, 'Badge-module__text') and contains(.,'Cheapest')]")
    WebElement locatorCheapestTag;
    @FindBy(xpath = "//h1[contains(@class, 'Text-module__root--variant-headline_1')]")
    WebElement locatorTicketType;
    @FindBy(xpath = "//div[@data-testid='upb_title-extra-flexibleticket']")
    WebElement flexTicketConfirm;

    // Initializing page factory / page object
    public SelectFlightPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // Filter flights by cheapest to the top
    public void filterByCheapest(){
        WebElement cheapeastBtn = wait.until(ExpectedConditions.elementToBeClickable(locatorCheapeastBtn));
        cheapeastBtn.click();
    }

    public String fetchCheapestFlightTag(){
        return locatorCheapestTag.getText();
    }

    // Select the cheapest flight displayed at the top
    public void selectCheapestFlight(){
        flightFound = false;
        while (!flightFound) {
            WebElement viewDetailsBtn = wait.until(ExpectedConditions.elementToBeClickable(locatorViewDetailsBtn));
            viewDetailsBtn.click();
            WebElement selectDetailsBtn = wait.until(ExpectedConditions.elementToBeClickable(locatorSelectDetailsBtn));
            selectDetailsBtn.click();

            List<WebElement> dialogueBoxSearchBtn = driver.findElements(By.xpath("//button[@class = \"Actionable-module__root___Gwqe- Button-module__root___Sc1p5 Button-module__root--variant-primary___gdoNq Button-module__root--size-medium___H4FLd Button-module__root--wide___GsKkT Button-module__root--variant-primary-action___BbpQ7 ErrorAlert-module__button___bC7te Actionable-module__root--wide-true___ANwYf\"]"));
            if (!dialogueBoxSearchBtn.isEmpty()) {
                dialogueBoxSearchBtn.get(0).click();
                System.out.println("Searched Again");
            }
            else {
                flightFound = true;
            }
        }
    }

    public String fetchCheapestTicketType(){
        return locatorTicketType.getText();
    }

    // Select ticket type to standard ticket (while handling dialgue box if appear in the process)
    public void selectTicketType() {
        contStandardBool = false;
        List<WebElement> continueBtn = driver.findElements(locatorContinueBtn);

        // Click the continue button if it appears
        while (!contStandardBool && !continueBtn.isEmpty()) {
            continueBtn.get(0).click();

            // cross out dialogue box if appears
            List<WebElement> dialogueBoxCrossBtn = driver.findElements(locatorDialogueBox);
            if (!dialogueBoxCrossBtn.isEmpty()) {
                dialogueBoxCrossBtn.get(0).click();
            } else {
                contStandardBool = true;
            }
        }
    }

    public String fetchFlexTcktConfirm(){
            return flexTicketConfirm.getText();
    }
}