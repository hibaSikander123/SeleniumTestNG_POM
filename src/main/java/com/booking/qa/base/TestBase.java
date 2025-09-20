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
    public String email;
    public String propertiesFilePath;
    public String userEmail;
    public String password;

    public TestBase(){
        // Load environment variables
        Dotenv dotenv = Dotenv.load();
        email = dotenv.get("EMAIL");
        propertiesFilePath = dotenv.get("PROPERTIES_FILE_PATH");
        userEmail = dotenv.get("USER_EMAIL");
        password = dotenv.get("PASSWORD");

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
