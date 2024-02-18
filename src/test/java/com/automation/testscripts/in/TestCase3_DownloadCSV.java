package com.automation.testscripts.in;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCase3_DownloadCSV {
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

			// Handle any exception that may occur during setup
			e.printStackTrace();
			Assert.fail("Exception during setup: " + e.getMessage());
		}

	}

	@Test
	public void TestMethod() throws InterruptedException {
		System.out.println("Inside the table method");
		
		Thread.sleep(5000);

		// Click on dropdown option for CSV
		WebElement dropdownOption = driver.findElement(By.xpath("//address[@class=\"compareCategoryBrandPadding widthForItemForMozilla\"]//parent::li"));
		dropdownOption.click();
		
		Thread.sleep(2000);
		
		// Click on CSV Option
		WebElement selectCSV = driver.findElement(By.xpath("(//div[@class=\"comparing-dd click-container\"]/ul/li[4])[1]"));
		selectCSV.click();

		Thread.sleep(5000);
		
		// Path to the downloaded folder
        String folderPath = "Your Download Folder Path";

        try {
            // Open the downloaded folder using the default file explorer
            Desktop.getDesktop().open(new File(folderPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        Thread.sleep(2000);
	}

	@AfterTest
	public void tearDown() {
		driver.close();
	}

}
