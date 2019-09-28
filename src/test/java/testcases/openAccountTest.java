package testcases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import base.testBase;
import utilities.exceldataProvider;

public class openAccountTest extends testBase {

	@Test(dataProvider = "openAccountData", dataProviderClass = exceldataProvider.class)
	public static void openAccountTesting(String name, String currency, String alert) throws InterruptedException {

		driver.findElement(By.xpath(OR.getProperty("bmLoginBtn"))).click();
		Thread.sleep(3000);
		driver.findElement(By.xpath(OR.getProperty("openaccount"))).click();
		Thread.sleep(3000);
		select("userselect", name);
		select("currencyselect", currency);
		driver.findElement(By.xpath(OR.getProperty("processbtn"))).click();
		Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert1.getText().contains(alert));

		alert1.accept();

		Thread.sleep(3000);

	}
}
