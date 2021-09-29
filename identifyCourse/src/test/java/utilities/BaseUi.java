package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class BaseUi {

	public static Properties config = new Properties();
	public static WebDriver driver;

	
	/*
	 * Creating constructor to initialize properties file
	 */
	public BaseUi() {

		FileReader reader = null;
		String path = System.getProperty("user.dir") + "/Config.properties";

		try {
			reader = new FileReader(path);
			config.load(reader);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	/*
	 * Method to open user choice of browser
	 */
	public static WebDriver getDriver(String option) {
		// If option is 1, open Chrome browser
		if (option.equalsIgnoreCase("Chrome")) {
			System.setProperty("webdriver.chrome.driver", 
					System.getProperty("user.dir") + "/drivers/chromedriver.exe");
			driver = new ChromeDriver();
		}
		
		// If option is 2, open Firefox browser
		else if (option.equalsIgnoreCase("Firefox")) {
			System.setProperty("webdriver.gecko.driver", 
					System.getProperty("user.dir") + "/drivers/geckodriver.exe");
			FirefoxBinary firefoxBinary = new FirefoxBinary();
			FirefoxProfile profile = new FirefoxProfile();

			FirefoxOptions firefoxOptions = new FirefoxOptions();
			firefoxOptions.setBinary(firefoxBinary);
			firefoxOptions.setProfile(profile);
			driver = new FirefoxDriver(firefoxOptions);
		}
		
		// If option is 3, open MS Edge browser
		else if (option.equalsIgnoreCase("Edge")) {
			System.setProperty("webdriver.edge.driver", 
					System.getProperty("user.dir") + "/drivers/msedgedriver.exe");
			driver = new EdgeDriver();
		}

		// Maximize window
		driver.manage().window().maximize();
		return driver;
	}

	
	
	/*
	 * Method to Open URL
	 */
	public void openUrl() {
		driver.get(config.getProperty("baseUrl"));
	}

	
	
	/*
	 * Method to wait till element is click-able
	 */
	public void waitElementClickable(String elementXpath) {
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(elementXpath)));
	}

	
	
	/*
	 * Take Screenshot
	 */
	public String snap(String fileName) {

		// Creating a screenshot driver and storing in scrFile temporarily.
		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		// Now you can do whatever you need to do with it, for example copy somewhere
		try {
			FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir") + "/Screenshots/" + fileName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String destination = System.getProperty("user.dir") + "/Screenshots/" + fileName;
		return destination;
	}

	/*
	 * Highlight Elements
	 */
	public void highLighterMethod(String locator){
		
		WebElement element = driver.findElement(By.xpath(locator));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	
	public void highLighterMethod(WebElement element){
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}
	
	public void unHighLighterMethod(String locator){
		
		WebElement element = driver.findElement(By.xpath(locator));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: null;');", element);
	}
	
	public void unHighLighterMethod(WebElement element){
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: null;');", element);
	}
	
	/*
	 * Page Load Timeout
	 */
	public void pageLoad(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
	}

	
	
	/*
	 * Page Refresh
	 */
	public void refresh() {
		driver.navigate().refresh();
	}

	
	
	/*
	 * Closing browser
	 */
	public void quitBrowser() {
		driver.quit();
	}
}