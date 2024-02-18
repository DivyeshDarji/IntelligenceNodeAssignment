package com.automation.testscripts.in;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase5_RelationshipChart {
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
					"https://qaportal.intelligencenode.com/?app_key=9fb211ff9d4e64a7fc09fda6661d5ef7#/relationship-chart");

			Thread.sleep(3000);
		} catch (Exception e) {

			// Handle any exception that may occur during setup
			e.printStackTrace();
			Assert.fail("Exception during setup: " + e.getMessage());
		}

	}

	@Test
	public void operationRelationshipChart() throws InterruptedException {
		System.out.println("Inside the Relationship Chart");
		
		Thread.sleep(5000);

		// Checking Page Heading
		WebElement pageHeading = driver.findElement(By.xpath("//div[@class=\"col-md-7\"]/h3"));
		String heading = pageHeading.getText();
		System.out.println(heading);
		if (heading.equalsIgnoreCase("Relationship Chart")) {
			System.out.println("Heading Matched : " + heading);
		} else {
			System.out.println("Not Matched");
		}

		// Creating Drag and Drop
		WebElement sourceElementWebsite = driver.findElement(By.xpath("(//li[@class=\"check-dd\"])[2]"));
		WebElement destElementWebsite = driver.findElement(By.xpath("(//div[@class=\"col-sm-12\"]/div)[1]"));

		WebElement sourceElementCatergory = driver
				.findElement(By.xpath("//div[@class=\"newFeatureTooltip\"]/span[@title='Category']"));
		WebElement destElementCat = driver.findElement(By.xpath("(//div[@class=\"col-sm-12\"]/div)[2]"));

		WebElement buttonApplyFilter = driver
				.findElement(By.xpath("//button[@class=\"btn btn-primary\" and contains(text(),'Apply Filter')]"));

		// Building a drag and drop action 
		Actions action = new Actions(driver);
		Action dragAndDrop1 = action.clickAndHold(sourceElementCatergory).moveToElement(destElementCat)
				.release(destElementCat).build();

		// Performing the drag and drop action 
		dragAndDrop1.perform();

		Action dragAndDrop = action.clickAndHold(sourceElementWebsite).moveToElement(destElementWebsite)
				.release(destElementWebsite).build();

		// Performing the drag and drop action 
		dragAndDrop.perform();

		Thread.sleep(10000);


		
		

	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
