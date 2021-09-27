package functionalities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.*;

public class CourseDetailsFunctionality extends BaseUi{
	
	public ArrayList<String> tabsGUId;
	public List<WebElement> courseName;
	public List<WebElement> courseRating;
	
	/*
	 * Working with Search Bar
	 */
	public void search(String testData) {
    	
		waitElementClickable(config.getProperty("search"));
		driver.findElement(By.xpath(config.getProperty("search"))).clear();
		driver.findElement(By.xpath(config.getProperty("search"))).sendKeys(testData);
		snap("Search");
	}
	
	public void searchClick() {
		driver.findElement(By.xpath(config.getProperty("searchButton"))).click();
		pageLoad(60);
	}
	
	/*
	 * Working with Filter Language criteria
	 */
	public void filterLanguage(String language) {
		pageLoad(60);
		
		// Working with Language Checkbox
		new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(By.xpath(config.getProperty("languageDrop")))).click();
		driver.findElement(By.xpath(config.getProperty("showAll"))).click();
		//Select the language
		List<WebElement> languages = driver.findElements(By.xpath(config.getProperty("languageList")));
		snap("Language List");
		for (WebElement i : languages) {
			String choice=i.getAttribute("value");
			if(choice.equalsIgnoreCase(language)) {
					i.click();
					break;
					}
		}
		driver.findElement(By.xpath(config.getProperty("languageClose"))).click(); //Close language lists checkbox
	}
	
	/*
	 * Working with Filter Level criteria
	 */
	public void filterLevel(String level) {
		//Working with Level Checkbox
		driver.findElement(By.xpath(config.getProperty("levelBar"))).click();
		snap("Level List");
		
		//Select the level
		List<WebElement> levels=driver.findElements(By.xpath(config.getProperty("levelList")));
		for (WebElement i : levels) {
			String choice=i.getAttribute("value");
			if(choice.equalsIgnoreCase(level)) {
					i.click();
					break;
					}
		}
		driver.findElement(By.xpath(config.getProperty("levelBar"))).click(); //Close level lists checkbox
	}
	
	
	/*
	 * Collecting details of 'n' number of courses
	 */
	public void getCourseDetails(int courseNo) {
		pageLoad(60);
		new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getProperty("courseNames"))));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,300)");
		snap("CourseList");
		courseName=driver.findElements(By.xpath(config.getProperty("courseNames")));
		courseRating=driver.findElements(By.xpath(config.getProperty("courseRating")));
		
	}
	
	public void getCourseDuration(int courseNo) {
		int tabNumber=0;
		String[] duration=new String [courseNo]; //take it above
		
		//Collecting course duration
		for (int i=0;i<courseNo;i++) {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(courseName.get(i))).click();
			System.out.println("\n"+(i+1)+". "+courseName.get(i).getText());
			System.out.println("   Rating: "+ courseRating.get(i).getText());
			tabsGUId = new ArrayList<String>(driver.getWindowHandles());
			driver.switchTo().window(tabsGUId.get(++tabNumber));	
			pageLoad(60);
			duration[i] = driver.findElement(By.xpath(config.getProperty("courseDuration"))).getText();
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,700)");
			snap("Course page("+i+")");
			System.out.println("   "+duration[i]);
			driver.close();
			tabNumber--;
			driver.switchTo().window(tabsGUId.get(tabNumber));
			
		}
		
		try {
			SendToExcel.sendData(courseName, courseRating, duration, "Course Details", "COURSE NAME", "COURSE RATING", "COURSE DURATION");
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}