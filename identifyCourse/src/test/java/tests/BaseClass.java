package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.BaseUi;

public class BaseClass extends BaseUi{
	
	
	public static WebDriver driver;
	
	@BeforeSuite
	public void openWebsite()
	{
		driver = getDriver("firefox");
		openUrl();	
	}
	
	
	
	@AfterSuite
	public void exitBrowser()
	{
		quitBrowser();
	}

}
