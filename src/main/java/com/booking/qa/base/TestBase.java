package com.booking.qa.base;

import com.booking.qa.util.TestUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.cdimascio.dotenv.Dotenv;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


public class TestBase {

    public static WebDriver driver;
    public static Properties prop;
    public static long EXPLICIT_WAIT;
    public static WebDriverWait wait;
   // public String email;
    public String propertiesFilePath;
    public String userEmail;
    public String password;
    public String firstName1;
    public String lastName1;
    public String birthMonth1;
    public String nationality1;
    public String birthDate1;
    public String birthYear1;
    public String firstName2;
    public String lastName2;
    public String birthMonth2;
    public String nationality2;
    public String birthDate2;
    public String birthYear2;
    public String phoneNum;

    public TestBase(){
        // Load environment variables
        Dotenv dotenv = Dotenv.load();

        propertiesFilePath = dotenv.get("PROPERTIES_FILE_PATH");
        userEmail = dotenv.get("USER_EMAIL");
        password = dotenv.get("PASSWORD");

        firstName1 = dotenv.get("FIRSTNAME1");
        lastName1 = dotenv.get("LASTNAME1");
        birthMonth1 = dotenv.get("BIRTHMONTH1");
        nationality1 = dotenv.get("NATIONALITY1");
        birthDate1 = dotenv.get("BIRTHDATE1");
        birthYear1 = dotenv.get("BIRTHYEAR1");

        firstName2 = dotenv.get("FIRSTNAME2");
        lastName2 = dotenv.get("LASTNAME2");
        birthMonth2 = dotenv.get("BIRTHMONTH2");
        nationality2 = dotenv.get("NATIONALITY2");
        birthDate2 = dotenv.get("BIRTHDATE2");
        birthYear2 = dotenv.get("BIRTHYEAR2");

        phoneNum = dotenv.get("PHONENUM");

        // Load properties file
        prop = new Properties();
        try (FileInputStream ip = new FileInputStream(propertiesFilePath)) {
            prop.load(ip);
            EXPLICIT_WAIT = Long.parseLong(prop.getProperty("explicitWait"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void initialization(){
        String browserName = prop.getProperty("browser");

        if (browserName.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();
            options.addArguments("--guest");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--disable-blink-features=AutomationControlled");
            options.setExperimentalOption("useAutomationExtension", false);
            options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

            driver = new ChromeDriver(options);
        } else if (browserName.equalsIgnoreCase("FF")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(TestUtil.PAGE_LOAD_TIMEOUT));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestUtil.IMPLICIT_WAIT));

        driver.get(prop.getProperty("url1"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(EXPLICIT_WAIT));
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
        }
    }
}
