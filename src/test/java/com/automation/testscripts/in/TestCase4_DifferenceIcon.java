package com.automation.testscripts.in;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase4_DifferenceIcon {
	// WebElement Declaration
	WebDriver driver;

	@BeforeTest
	public void setUp() {
		try {

			// get the project path
			String path = System.getProperty("user.dir");
			System.out.println("Path : " + path);

			// set driver property
			System.setProperty("webdriver.chrome.driver", path + "\\driver\\chrome\\chromedriver.exe");

			driver = new ChromeDriver();
			driver.manage().window().maximize();

			// Navigate to the login page
			driver.get(
					"https://qaportal.intelligencenode.com/?app_key=9fb211ff9d4e64a7fc09fda6661d5ef7#/compare/new/item");

			Thread.sleep(3000);
		} catch (Exception e) {

			// Handle any exception that may occur during setup
			e.printStackTrace();
			Assert.fail("Exception during setup: " + e.getMessage());
		}

	}

	@Test
	public void checkDifferenceIcon() throws InterruptedException {
		System.out.println("Inside the Difference Icon");
		Thread.sleep(5000);

		// Assuming your table has a specific id, change this locator accordingly
		WebElement table = driver.findElement(By.xpath("//table[@id=\"compareItemsTable\"]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", table);

		Thread.sleep(5000);

		List<WebElement> differenceIcon = driver
				.findElements(By.xpath("//span[@value=\"tooltip icon\"]/i[@class=\"icon icon-sidemodal\"]"));
		System.out.println("There are " + differenceIcon.size() + " difference Icons visible on Page 01.");

		// WebElement diffIcon = driver.findElement(By.xpath("(//i[@class=\"icon
		// icon-sidemodal\"])[3]"));

		Thread.sleep(5000);

		// Click on the first element from the list
		WebElement elementToClick = differenceIcon.get(0);
		elementToClick.click();

		System.out.println("Clicked on Difference Icon");

		Thread.sleep(3000);

		WebElement insideDiffTable = driver.findElement(By.xpath("//div[@class=\"row mainHeadTooltip\"]/p"));
		if (insideDiffTable.isDisplayed()) {
			System.out.println("Differenct table is present and able to see the matched details.");
		} else {
			System.out.println("Awaiting for Differenct Table to be Appeared.");
		}

		Thread.sleep(5000);

	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
