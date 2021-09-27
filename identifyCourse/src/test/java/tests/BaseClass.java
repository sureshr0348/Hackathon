package tests;

import java.util.Scanner;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import utilities.BaseUi;

public class BaseClass extends BaseUi{
	
	
	public static WebDriver driver;
	
	@BeforeSuite
	public void openWebsite()
	{
		Scanner sc = new Scanner(System.in);
		int option = 0;
		while (true) {
			System.out.println("Enter the corresponding number for Browser choice: "
					+ "\n1. Chrome\n2. Firefox\n3. Microsoft Edge");
			option = sc.nextInt();
			if (option > 0 && option < 4)
				break;
			System.out.println("Invalid Option. Enter a value from 1 to 3.");
		}
		
		driver = getDriver(option); 	//Get the Corresponding Driver Object
		pageLoad(30); 					//Set Wait time to 30 seconds
		openUrl();						//Open the Webpage
		
		sc.close();
	}
	
	
	
	@AfterSuite
	public void exitBrowser()
	{
		quitBrowser();
	}

}
