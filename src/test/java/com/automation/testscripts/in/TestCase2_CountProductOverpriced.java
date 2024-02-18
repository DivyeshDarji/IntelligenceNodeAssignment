package com.automation.testscripts.in;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase2_CountProductOverpriced {

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
			driver.get("https://qaportal.intelligencenode.com/?app_key=9fb211ff9d4e64a7fc09fda6661d5ef7#/compare/new/item");
			
			Thread.sleep(3000);
		} catch (Exception e) {

			// Handle any exception that may occur during setu
			e.printStackTrace();
			Assert.fail("Exception during setup: " + e.getMessage());
		}

	}

	@Test
	public void OverPriced() throws InterruptedException {
		Thread.sleep(5000);

		// Assuming your table has a specific id, change this locator accordingly
		WebElement table = driver.findElement(By.xpath("//table[@id=\"compareItemsTable\"]"));

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", table);

		Thread.sleep(5000);

		// Status Column dropdown
		// Value OverPriced : By.xpath("(//div[@id=\"discount_options\"]/ul/li[4])[1]")
		WebElement statusDropDown = driver
				.findElement(By.xpath("//span[@class=\"col-xs-12 result-filter discount-filter\"]"));
		statusDropDown.click();

		Thread.sleep(5000);

		WebElement optionOverPriced = driver.findElement(By.xpath("//div[@id=\"discount_options\"]/ul/li[4]"));
		optionOverPriced.click();

		Thread.sleep(5000);

		WebElement scrolldownToElement = driver.findElement(By.xpath("//div[@class=\"pull-left paginationList\"]"));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("arguments[0].scrollIntoView(true);", scrolldownToElement);

		Thread.sleep(5000);

		// count the number of rows
		int totalRows1 = getTotalRows(driver);
		System.out.println("Total number of rows: " + totalRows1);

		WebElement rowCount = driver.findElement(By.xpath("//span[@class=\"bold-text text-padding\"]"));
		String count = rowCount.getText();
		System.out.println("Overall Rows: " + count);
		int totalItems = Integer.parseInt(count);
		if (totalRows1 == totalItems) {
			System.out.println("The count of products available on Compare Tab for status Overpriced is " + totalItems);
		} else {
			System.out.println("No Matched");
		}

	}

	public static int getTotalRows(WebDriver driver) {
		int totalRow = 0;

		try {
			while (true) {
				// Assuming your table has a specific id, change this locator accordingly
				WebElement table = driver.findElement(By.xpath("//table[@id=\"compareItemsTable\"]"));

				// Find all rows in the table
				List<WebElement> rows = table.findElements(By.tagName("tr"));
				totalRow = rows.size() - 2;

				List<WebElement> nextPageButton = driver.findElements(
						By.xpath("//span[@class=\"compareItemShowMore\" and contains(text(),'Show More')]"));
				if (nextPageButton.size() > 0) {
					// "Next" button is present, click on it
					nextPageButton.get(0).click();
				} else {
					// "Next" button is not present, exit the loop
					break;
				}

				// System.out.println("Rows Count: " + totalRow);

				Thread.sleep(5000);

			}
		} catch (NoSuchElementException | InterruptedException e) {
			// No more "Next" button found, end of pagination
			System.out.println("No element Found");
		}

		return totalRow;
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
