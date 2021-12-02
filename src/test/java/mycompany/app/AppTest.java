package com.mycompany.app;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.Before;
import org.junit.Test;
import org.junit.After;
import static org.junit.Assert.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Integration UI test for PHP App.
 */
public class AppTest
{
	WebDriver driver; 
	WebDriverWait wait; 
	String url = "http://10.1.4.147";
	String validXSS = "<script>alert('Attacked by Prans');</script>";
	String notXSS = "hi";

    @Before
    public void setUp() { 
		driver = new HtmlUnitDriver(); 
		wait = new WebDriverWait(driver, 10); 
	} 

	@After
    public void tearDown() { 
		driver.quit(); 
	}	 
	
    @Test
    public void testXSSWithValidXSS() 
		throws InterruptedException { 

		//get web page
		driver.get(url);
		//wait until page is loaded or timeout error
		wait.until(ExpectedConditions.titleContains("Search Page")); 

		//enter input
		driver.findElement(By.Id("search-box")).sendKeys(validXSS);
		//click submit
		driver.findElement(By.Id("search-submit")).submit();
	
		//check result 
		String expectedResult = "<script>alert('Attacked by prans')</script>"; 
		boolean isResultCorrect = wait.until(ExpectedConditions.textToBe(validXSS, expectedResult)); 
		assertTrue(isResultCorrect == true); 
	}
		
	@Test
    public void testXSSWithInvalidXSS() 
		throws InterruptedException { 

		//get web page
		driver.get(url);
		//wait until page is loaded or timeout error
		wait.until(ExpectedConditions.titleContains("Search Page")); 

		//enter input
		driver.findElement(By.Id("search-box")).sendKeys(notXSS);
		//click submit
		driver.findElement(By.Id("search-submit")).submit();
	
		//check result
		String expectedResult = "hi"; 
		boolean isResultCorrect = wait.until(ExpectedConditions.textToBe(notXSS, expectedResult)); 
		assertTrue(isResultCorrect == true); 
	}

}
