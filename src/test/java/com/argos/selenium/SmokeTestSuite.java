package com.argos.selenium;

import org.hamcrest.text.IsEqualIgnoringCase;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.fail;


public class SmokeTestSuite extends Hooks
{
    @Test
    public void searchTest()
    {

        doSearch("puma");
        //Assertions


        //Asssert2
        //Collect all elements and loop through and verify pdt contains puma
        List<WebElement> prdtWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title'"));
        for (WebElement indproduct: prdtWebElements)
        {
            String actualText = indproduct.getText();
            assertThat(actualText,containsString("Puma"));
        }

        //Assert3
        String actualTitle = driver.findElement(By.cssSelector("h1[class='search-title__term']")).getText();
        assertThat(actualTitle,is(equalToIgnoringCase("puma")));

        //Assert1
        String url = driver.getCurrentUrl();
        assertThat(url,endsWith("puma"));

    }

    @Test
    public void addToBasket()
    {
        doSearch("Shaver");
        List<WebElement> PrdtWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
        if (PrdtWebElements.size()==0)
        {
            fail("No products with name"+ "Shaver");
        }
        Random random = new Random();
        int randomNumber = random.nextInt(PrdtWebElements.size()-1);

        WebElement selectedElement = PrdtWebElements.get(randomNumber);
        String selectedproductName = selectedElement.getText();

        selectedElement.click();
        addToTrolleyIPP();
        goToTrolley();

        String actual = productInBasket();
        assertThat(actual,containsString(selectedproductName));

    }
    @Test
    public void addToBasket2()
    {
        doSearch("puma");
        List<WebElement> prdtWebElements = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
        if (prdtWebElements.size()==0)
        {
            fail("No products found");
        }


    }

    @Test
    public void filterSearch()
    {
        driver.findElement(By.cssSelector("#searchTerm")).sendKeys("shaver");
        driver.findElement(By.cssSelector("#searchTerm")).sendKeys(Keys.ENTER);
        List<WebElement> prdtsNames = driver.findElements(By.cssSelector("a[data-test='component-product-card-title']"));
        for (WebElement indPrdt : prdtsNames)
        {
          String prdName = indPrdt.getText();
          assertThat(prdName,containsString("Shaver"));
        }
        driver.findElement(By.cssSelector("#\\34 \\ or\\ more-label > div > div")).click();

        //List<WebElement> rating = driver.findElements(By.cssSelector("div[data-star-rating^='4.']"));

    }

    public void doSearch(String searchTerm)
    {

        driver.findElement(By.cssSelector("#searchTerm")).sendKeys(searchTerm);
        //driver.findElement(By.cssSelector("#haas-v2 > div._3-aaF > div > div.AOlDC > div > form > button > span._1gqeQ")).click();
        driver.findElement(By.cssSelector("#searchTerm")).sendKeys(Keys.ENTER);//this is when you want to use keyboard

    }

    public void addToTrolleyIPP()
    {
        driver.findElement(By.cssSelector("button[data-test='component-att-button']")).click();
    }
    public void goToTrolley()
    {
        driver.findElement(By.xpath("//*[@id=\"content\"]/div/div/div[1]/div[2]/section[2]/section/div[8]/div/div/div[1]/footer/div/div[2]")).click();
    }
    public String productInBasket()
    {
        return driver.findElement(By.cssSelector(".ProductCard__content__9U9b1.xsHidden.lgFlex")).getText();
    }



}
