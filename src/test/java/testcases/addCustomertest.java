package testcases;

import base.testBase;
import utilities.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.*;

public class addCustomertest extends testBase {

	@Test(dataProvider = "dp", dataProviderClass = exceldataProvider.class)
	public static void addCustomerTest(String firstName, String lastName, String postCode, String alert)
			throws InterruptedException {

		click("bmLoginBtn_XPATH");
		Thread.sleep(3000);
		log.info("addCustomerTest is started");

		click("addcustomerbtn_CSS");
		Thread.sleep(3000);

		type("customerfirstName_XPATH", firstName);

		type("customerlastName_XPATH", lastName);

		type("customerPostCode_XPATH", postCode);

		click("addcustomerbtnsmall_XPATH");
		Thread.sleep(2000);

		Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert1.getText().contains(alert));

		alert1.accept();

		Thread.sleep(3000);

		log.info("addCustomerTest is passed like a charm");

	}

	@Test(dataProvider = "dp", dataProviderClass = exceldataProvider.class)
	public static void openAccountTest(String name, String currency, String alert) throws InterruptedException {

		click("openaccount_XPATH");
		Thread.sleep(3000);
		log.info("openAccountTest is started");

		select("userselect_XPATH", name);
		select("currencyselect_XPATH", currency);

		click("processbtn_XPATH");
		Thread.sleep(2000);

		Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
		Assert.assertTrue(alert1.getText().contains(alert));

		alert1.accept();

		Thread.sleep(3000);
		log.info("openAccountTest is passed like a charm");

	}

}
