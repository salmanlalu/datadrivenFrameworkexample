package roughTry;
import java.util.concurrent.TimeUnit;

import base.testBase;

public class methodTesting extends testBase {

	public static void main(String[] args) {
		
		driver.get(config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit_wait")),
				TimeUnit.SECONDS);
		
		click("bmLoginBtn_XPATH");

	}

}
