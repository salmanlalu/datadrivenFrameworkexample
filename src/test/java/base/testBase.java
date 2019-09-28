package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExtentManager;
import utilities.excelutil;

public class testBase {

	/*
	 * WebDriver excel Database properties logs extent report Mail ReportNG Extent
	 * Report Jenkins
	 */

	public static WebDriver driver;
	public static Properties config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static FileInputStream fis1;
	public static excelutil excel = new excelutil(
			System.getProperty("user.dir") + "\\src\\test\\resources\\excel\\testdata.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report = ExtentManager.getInstance();
	public static ExtentTest test;
	public static String browser;
	public static Logger log = Logger.getLogger(testBase.class.getName());
	
	
	//this is for testing the jenkins with github
	@BeforeTest
	public void setUp() throws IOException {
		
		BasicConfigurator.configure();
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			config.load(fis);
			log.info("config file loaded");

		} catch (IOException e) {
			e.printStackTrace();
			log.debug("config file unable to load");
		}
		try {
			fis1 = new FileInputStream(
					System.getProperty("user.dir") + "\\src\\test\\resources\\properties\\OR.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			OR.load(fis1);
			log.info("Object repository file loaded");
		} catch (IOException e) {
			e.printStackTrace();
			log.debug("unable to load OR file");
		}
		
		
		if(System.getenv("browser")!=null && !System.getenv("browser").isEmpty()) {
			
			browser = System.getenv("browser");
		}
		else {
			browser = config.getProperty("browser");
		}
		
		config.setProperty("browser", browser);

		if (config.getProperty("browser").equals("chrome")) {

			System.setProperty("webdriver.chrome.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\chromedriver.exe");
			driver = new ChromeDriver();

		}

		else if (config.getProperty("browser").equals("firefox")) {
			System.setProperty("webdriver.gecko.driver",
					System.getProperty("user.dir") + "\\src\\test\\resources\\executables\\geckodriver.exe");
			driver = new FirefoxDriver();

		}

		driver.get(config.getProperty("testsiteurl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(config.getProperty("implicit_wait")),
				TimeUnit.SECONDS);
		wait = new WebDriverWait(driver, 5);

	}

	public boolean isPresentElement(By by) {

		try {

			driver.findElement(by);
			return true;

		} catch (NoSuchElementException e) {

			return false;
		}

	}
	
	public static void click(String locator) {
		
		if (locator.endsWith("_XPATH")) {
		
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			
		} 
		else if (locator.endsWith("_CSS")) {
		
			driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}
		else if (locator.endsWith("_ID")) {
			
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		
		test.log(LogStatus.INFO, "clicking on: "+locator);
		log.info("clilcking on: "+locator);
	}
	
	public static void type(String locator, String value) {
		
		if (locator.endsWith("_XPATH")) {
		
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
		} 
		else if (locator.endsWith("_CSS")) {
		
			driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}
		else if (locator.endsWith("_ID")) {
			
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);
		}
		
		test.log(LogStatus.INFO, "Typing in: "+locator+" entering value of: "+value);
		log.info("Typing in: "+locator+" entering the value of: "+value);
	}

	static WebElement dropdown;

	public static void select(String locator, String value) {

		if(locator.endsWith("_XPATH")) {
			dropdown = driver.findElement(By.xpath(OR.getProperty(locator)));
		}
		else if (locator.endsWith("_CSS")) {
			dropdown = driver.findElement(By.cssSelector(OR.getProperty(locator)));
		}
		else if (locator.endsWith("_ID")) {
			dropdown = driver.findElement(By.id(OR.getProperty(locator)));
		}
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		
		test.log(LogStatus.INFO, "Selecting in: "+locator+" entering value of: "+value);
		log.info("Selecting in: "+locator+" entering value of: "+value);
	}

	@AfterTest
	public void tearDown() {

		if (driver != null) {

			driver.quit();

		}

	}

}