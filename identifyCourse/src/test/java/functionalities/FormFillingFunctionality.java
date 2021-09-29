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
		highLighterMethod(config.getProperty("enterpriseLink"));
		driver.findElement(By.xpath(config.getProperty("enterpriseLink"))).click();
		snap("Enterprise Page");
		
		WebElement course = driver.findElement(By.linkText("Products"));
		highLighterMethod(course);
		Actions action1 = new Actions(driver);
		action1.moveToElement(course).build().perform();
		
		WebElement campus =  driver.findElement(By.linkText("For Campus"));
		highLighterMethod(campus);
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
		WebElement fName = driver.findElement(By.xpath(config.getProperty("firstName")));
		highLighterMethod(fName);
		fName.sendKeys(in.ReadExcelData(row, column++)); //1
		unHighLighterMethod(fName);
		
		//Last Name
		WebElement lName = driver.findElement(By.xpath(config.getProperty("lastName")));
		highLighterMethod(lName);
		lName.sendKeys(in.ReadExcelData(row, column++)); //2
		unHighLighterMethod(lName);
		
		//Job Function
		WebElement jfe = driver.findElement(By.xpath(config.getProperty("jobFunction")));
		highLighterMethod(jfe);
		Select jf = new Select(jfe);
		String jfText = in.ReadExcelData(row, column++); //3
		if (jfText != "") {
			jf.selectByVisibleText(jfText);
		}
		unHighLighterMethod(jfe);
		
		//Job Title
		WebElement title = driver.findElement(By.xpath(config.getProperty("jobTitle")));
		highLighterMethod(title);
		title.sendKeys(in.ReadExcelData(row, column++)); //4
		unHighLighterMethod(title);
		
		//Work Email
		WebElement email = driver.findElement(By.xpath(config.getProperty("email")));
		highLighterMethod(email);
		email.sendKeys(in.ReadExcelData(row, column++)); //5
		unHighLighterMethod(email);

		//Phone
		WebElement phone = driver.findElement(By.xpath(config.getProperty("phone")));
		highLighterMethod(phone);
		phone.sendKeys(in.ReadExcelData(row, column++)); //6
		unHighLighterMethod(phone);
		
		//Institution Name
		WebElement inst = driver.findElement(By.xpath(config.getProperty("institution")));
		highLighterMethod(inst);
		inst.sendKeys(in.ReadExcelData(row, column++)); //7
		unHighLighterMethod(inst);
		
		//Institution Type
		WebElement instType = driver.findElement((By.xpath(config.getProperty("iType"))));
		highLighterMethod(instType);
		Select iType = new Select(instType);
		String iTypetext  = in.ReadExcelData(row, column++); //8
		if (iTypetext != "") {
			iType.selectByVisibleText(iTypetext);
		}
		unHighLighterMethod(instType);

		//Discipline
		WebElement disc = driver.findElement(By.xpath(config.getProperty("discipline")));
		highLighterMethod(disc);
		Select discipline = new Select(disc);
		String disString = in.ReadExcelData(row, column++); //9
		if (disString != "") {
			discipline.selectByVisibleText(disString);
		}
		unHighLighterMethod(disc);

		//Country
		WebElement country = driver.findElement(By.xpath(config.getProperty("country")));
		highLighterMethod(country);
		Select con = new Select(country);
		String conText = in.ReadExcelData(row, column++); //10
		if (conText != "") {
			con.selectByVisibleText(conText);
		}
		unHighLighterMethod(country);
	
		//State
		try {
			pageLoad(5);
			WebElement state = driver.findElement(By.xpath(config.getProperty("state")));
			highLighterMethod(state);
			Select st = new Select(state);
			String stText = in.ReadExcelData(row, column); //11
			if (stText != "") {
				st.selectByVisibleText(stText);
			}
			unHighLighterMethod(state);
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
		highLighterMethod(config.getProperty("submit"));
		driver.findElement(By.xpath(config.getProperty("submit"))).click();
		unHighLighterMethod(config.getProperty("submit"));

		
		js.executeScript("window.scrollBy(0,-200)");
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snap("Data "+row+" Status");
	}
}
