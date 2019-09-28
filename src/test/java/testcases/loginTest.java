package testcases;

import org.openqa.selenium.By;

import org.testng.Assert;
import org.testng.annotations.Test;
import base.testBase;

public class loginTest extends testBase {

	// public static Logger log = LogManager.getLogger(loginTest.class);

	@Test
	public void loginAsBankManager() throws InterruptedException {

		click("bmLoginBtn_XPATH");
		log.info("loginTest Started");
		Thread.sleep(2000);
		
		Assert.assertTrue(isPresentElement(By.cssSelector(OR.getProperty("addcustomerbtn_CSS"))));
		log.info("loginTest is passed like a charm :)");
	}

}
