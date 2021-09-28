package functionalities;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
//import org.testng.annotations.DataProvider;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.BaseUi;
import utilities.Input;

public class FormFillingFunctionality extends BaseUi {
	
	/*
	 * Navigate to the Form filling page
	 */
	public void navigateForm()
	{
		driver.findElement(By.xpath(config.getProperty("enterpriseLink"))).click();
		snap("Enterprise Page");
		
		WebElement course = driver.findElement(By.linkText("Products"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(course).build().perform();
		
		new WebDriverWait(driver, 30).until(ExpectedConditions.elementToBeClickable(By.linkText("For Campus"))).click();
		
		int tabNumber = 0;
		ArrayList<String> tabsGUId = new ArrayList<String>(driver.getWindowHandles());
		
		if (tabsGUId.size() > 1) {
			driver.switchTo().window(tabsGUId.get(++tabNumber));
		}
	}
	
	
	/*
	 * Setting form values
	 */
	public void setFormValues(int row) {
		Input in = new Input();
		int column = 0;
		
		// First name
		driver.findElement(By.xpath(config.getProperty("firstName"))).sendKeys(in.ReadExcelData(row, column++)); //1
		
		//Last Name
		driver.findElement(By.xpath(config.getProperty("lastName"))).sendKeys(in.ReadExcelData(row, column++)); //2
		
		//Job Function
		Select jf = new Select(driver.findElement(By.xpath(config.getProperty("jobFunction"))));
		String jfText = in.ReadExcelData(row, column++); //3
		if (jfText != "") {
			jf.selectByVisibleText(jfText);
		}
		
		//Job Title
		driver.findElement(By.xpath(config.getProperty("jobTitle"))).sendKeys(in.ReadExcelData(row, column++)); //4
		
		//Work Email
		driver.findElement(By.xpath(config.getProperty("email"))).sendKeys(in.ReadExcelData(row, column++)); //5

		//Phone
		driver.findElement(By.xpath(config.getProperty("phone"))).sendKeys(in.ReadExcelData(row, column++)); //6
		
		//Institution Name
		driver.findElement(By.xpath(config.getProperty("institution"))).sendKeys(in.ReadExcelData(row, column++)); //7
		
		//Institution Type
		Select iType = new Select(driver.findElement((By.xpath(config.getProperty("iType")))));
		String iTypetext  = in.ReadExcelData(row, column++); //8
		if (iTypetext != "") {
			iType.selectByVisibleText(iTypetext);
		}

		//Discipline
		Select discipline = new Select(driver.findElement(By.xpath(config.getProperty("discipline"))));
		String disString = in.ReadExcelData(row, column++); //9
		if (disString != "") {
			discipline.selectByVisibleText(disString);
		}

		//Country
		Select con = new Select(driver.findElement(By.xpath(config.getProperty("country"))));
		String conText = in.ReadExcelData(row, column++); //10
		if (conText != "") {
			con.selectByVisibleText(conText);
		}
	
		//State
		try {
			pageLoad(5);
			Select st = new Select(driver.findElement(By.xpath(config.getProperty("state"))));
			String stText = in.ReadExcelData(row, column); //11
			if (stText != "") {
				st.selectByVisibleText(stText);
			}
		}
		catch (NoSuchElementException e) {
		}
	
		pageLoad(30);
		
		try {
			Robot robot = new Robot();
			for (int i = 0; i < 3; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_SUBTRACT);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
		} catch (AWTException e1) {
			e1.printStackTrace();
		}
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollBy(0,-200)");
		
		snap("Details" + row);
	
		try {
			Robot robot = new Robot();
			for (int i = 0; i < 3; i++) {
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_ADD);
				robot.keyRelease(KeyEvent.VK_ADD);
				robot.keyRelease(KeyEvent.VK_CONTROL);
			}
		} catch (AWTException e1) {
			e1.printStackTrace();
		}

		// for testing purposes
		driver.findElement(By.xpath(config.getProperty("submit"))).click();
		
		js.executeScript("window.scrollBy(0,-200)");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snap("Data "+row+" Status");
	}
}
