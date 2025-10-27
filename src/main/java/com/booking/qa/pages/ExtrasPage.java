package com.booking.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class ExtrasPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private By locatorFlexTcktRadioBtn = By.xpath("//input[@value='flexible']/parent::div//span[contains(@class , 'InputRadio-module__field')]");
    private By locatorTravelProtectionBtn = By.xpath("//input[@value='insurance']/parent::div//span[contains(@class , 'InputRadio-module__field')]");
    private By locatorNextBtn2 = By.xpath("//div[@data-testid = \"checkout_extras_inner_next\"]/button");
    private By locatorSeatSelectionStep = By.xpath("//h2[contains(@class, 'Text-module__root--variant-headline') and contains(., 'Select your seat')]");
    private By locatorCheckAndPayStep = By.xpath("//h2[contains(@class, 'Text-module__root--variant-headline_2___kgmLC Text-module__root--color-neutral___M43S9 Title-module__title') and contains(., 'Check and pay')]\n");
    private By locatorContinueBtn = By.xpath("//button[@data-testid = \"ticket_type_cta_flexible\" or @data-testid = \"branded_fare_cta_0\"]");
    private By locatorDialogueBox = By.xpath("//div[contains (@class, 'DismissibleContainer-module__root')]//button");

    @FindBy(xpath = "//div[@data-testid='upb_title-extra-travelinsurance']")
    WebElement travelProtectionBtn;
    @FindBy(xpath = "//div[@data-testid='upb_title-extra-flexibleticket']")
    WebElement flexTicket;
    @FindBy(xpath = "//h2[contains(@class, 'Text-module__root--variant-headline') and contains(., 'Select your seat')]")
    WebElement seatSelectionStep;
    @FindBy(xpath = "//h2[contains(@class, 'Text-module__root--variant-headline_2___kgmLC Text-module__root--color-neutral___M43S9 Title-module__title') and contains(., 'Check and pay')]\n")
    WebElement checkAndPayStep;

    // Initializing page factory / page object
    public ExtrasPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
        PageFactory.initElements(driver, this);
    }

    // Select Ticket Type as Flexible Ticket
    public String selectTicketTypeExtras(){
        // First check if ticket type button is present
        List<WebElement> ticketTypeButtons = driver.findElements(locatorContinueBtn);

        if (!ticketTypeButtons.isEmpty()){
            // Use the same logic as SelectFlightPage's selectTicketType method
            return handleTicketTypeButton();
        }
        // If ticket type button is not present, check for radio button
        else if (!driver.findElements(locatorFlexTcktRadioBtn).isEmpty()){
            return handleFlexTicketRadioButton();
        }
        // If neither is present, return flex ticket not added
        else {
            return "FlexTicketNotAdded";
        }
    }

    // Handle ticket type button (same logic as SelectFlightPage)
    private String handleTicketTypeButton() {
        boolean contStandardBool = false;
        List<WebElement> continueBtn = driver.findElements(locatorContinueBtn);

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
        return "FlexTicketAdded";
    }

    // Handle flex ticket radio button
    private String handleFlexTicketRadioButton() {
        List<WebElement> flexTickteRadioBtn = driver.findElements(locatorFlexTcktRadioBtn);
        if (!flexTickteRadioBtn.isEmpty()){
            flexTickteRadioBtn.get(0).click();
            return "FlexTicketAdded";
        } else {
            return "FlexTicketNotAdded";
        }
    }

    public String fetchFlexTcktText() {
        return flexTicket.getText();
    }

    // Avail the Travel Protection Insurance
    public String selectTravelProtection(){
        List<WebElement> travelInsBtn = driver.findElements(locatorTravelProtectionBtn);
        if (!travelInsBtn.isEmpty()) {
            travelInsBtn.get(0).click();
            return "TravelProtectionAdded";
        } else  {
            return "TravelProtectionNotAdded";
        }
    }

    public String fetchTravelPRocText() {
        return travelProtectionBtn.getText();
    }

    // Click next button to proceed to the seat selection page
    public String goToNextPage(){
        WebElement nextBtn2 = wait.until(ExpectedConditions.elementToBeClickable(locatorNextBtn2));
        nextBtn2.click();

        // Check which page we landed on
        try {
            wait.until(ExpectedConditions.or(
                    ExpectedConditions.visibilityOfElementLocated(locatorSeatSelectionStep),
                    ExpectedConditions.visibilityOfElementLocated(locatorCheckAndPayStep)
            ));

            if (isSeatSelectionPagePresent()) {
                return "seatSelection";
            } else if (isCheckAndPayPagePresent()) {
                return "checkAndPay";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "unknown";
    }

    public boolean isSeatSelectionPagePresent() {
        try {
            return seatSelectionStep.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isCheckAndPayPagePresent() {
        try {
            return checkAndPayStep.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String fetchSeatSelectionText() {
        return seatSelectionStep.getText();
    }

    public String fetchCheckAndPayText() {
        return checkAndPayStep.getText();
    }
    }