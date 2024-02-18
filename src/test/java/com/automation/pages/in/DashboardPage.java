package com.automation.pages.in;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.AssertJUnit;

public class DashboardPage {

	public WebDriver driver;
	WebElement menu = driver.findElement(By.className("//a[@class=\"tourStep-3 \"]"));
	WebElement optionItem = driver.findElement(By.partialLinkText("Items"));
	WebElement bannerHeading = driver
			.findElement(By.xpath("//p[@class=\"heading-style\" and contains(text(),'Comparing Items')]"));

	public DashboardPage(WebDriver dDriver) {
		// TODO Auto-generated constructor stub
		this.driver = dDriver;
	}

	public void NavigateCompateItem() throws InterruptedException {
		menu.click();
		Thread.sleep(2000);
		optionItem.click();
		Thread.sleep(2000);

		String ValidateBanner = bannerHeading.getText();
		AssertJUnit.assertEquals("Comparing Items", ValidateBanner);
	}

}
