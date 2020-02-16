package com.argos.selenium;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

public class Hooks
{
    public static WebDriver driver;
    public String browser = "firefox";

    @Before
    public void setUp()
    {
        openBrowser();
        navigateToUrl();
        maxScreen();
        deleteAllCookies();
        implicitWait30s();
        manageCookies();
    }


    //@After
    public void tearDown()
    {
        driver.quit();
    }


    public void openBrowser()
    {
       switch (browser)
       {
           case "ie": WebDriverManager.iedriver().setup();
           driver = new InternetExplorerDriver();
           break;

           case "firefox": WebDriverManager.firefoxdriver().setup();
           driver = new FirefoxDriver();
           break;

           default: WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
       }
    }

    public void navigateToUrl()
    {
        driver.get("https://www.argos.co.uk/");
    }

    public void maxScreen()
    {
        driver.manage().window().maximize();
    }

    public void deleteAllCookies()
    {
        driver.manage().deleteAllCookies();
    }

    public void implicitWait30s()
    {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    public void manageCookies()
    {
        driver.findElement(By.cssSelector("#__tealiumGDPRecModal > div > div > div.privacy-prompt-footer > a")).click();
    }
}
