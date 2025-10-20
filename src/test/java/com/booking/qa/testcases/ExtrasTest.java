package com.booking.qa.testcases;

import com.booking.qa.pages.ExtrasPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtrasTest extends BaseTest{

    private ExtrasPage extrasPage;

    @BeforeMethod
    public void settingUp() {
        extrasPage = new ExtrasPage(driver, wait);
    }

    @Test(dependsOnMethods = "com.booking.qa.testcases.TravelerDetailsTest.goToExtrasPageTest")
    public void selectTicketTypeTest () {
        String ticketTypeResult = extrasPage.selectTicketType();

        if("FlexTicketAdded".equals(ticketTypeResult)){
        String verifyFlexTcktSelection = extrasPage.fetchFlexTcktText();
        Assert.assertEquals(verifyFlexTcktSelection, "Flexible ticket", "Unable to get the flexible ticket");
    } else {
            System.out.println("Ticket Type Selection option is not provided");
        }
    }

    @Test(dependsOnMethods = "selectTicketTypeTest")
    public void selectTravelProtectionTest () {
        String travelProcResult = extrasPage.selectTravelProtection();

        if("TravelProtectionAdded".equals(travelProcResult)) {
            String verifyTravelProtection = extrasPage.fetchTravelPRocText();
            Assert.assertEquals(verifyTravelProtection, "Travel protection", "Unable to get travel protection");
        }  else {
            System.out.println("Travel Protection Selection option is not provided");
        }
    }

    @Test(dependsOnMethods = "selectTravelProtectionTest")
    public void goToSeatSelectionPageTest () {
        extrasPage.goToSeatSelectionPage();
        String verifySeatSelectionReach = extrasPage.fetchSeatSelectionText();
        Assert.assertEquals(verifySeatSelectionReach, "Select your seat", "Unable to click next button succesfully to reach the seat selection step");
    }
}
