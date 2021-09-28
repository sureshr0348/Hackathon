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
	public List<WebElement> courses;
	
	
	
	/*
	 * Search the String passed using the Search bar
	 */
	public void search(String testData) {
		String searchLocator = config.getProperty("search");
		waitElementClickable(searchLocator);
		driver.findElement(By.xpath(searchLocator)).clear();
		driver.findElement(By.xpath(searchLocator)).sendKeys(testData);
		snap("Search");
	}
	/*
	 * Click on Search button and take screenshot of the search results
	 */
	public void searchClick() {
		driver.findElement(By.xpath(config.getProperty("searchButton"))).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		snap("SearchResults");
	}
	
	
	
	/*
	 * Working with Language Dropdown filter
	 */
	public void filterLanguage(String language) {
		//Working with Language Drop Down box
		String languageDropDown = config.getProperty("languageDrop");
		waitElementClickable(languageDropDown);
		driver.findElement(By.xpath(languageDropDown)).click();
		
		driver.findElement(By.xpath(config.getProperty("showAll"))).click();
		
		//Select the language
		List<WebElement> languages = driver.findElements(By.xpath(config.getProperty("languageList")));
		snap("Language List");
		for (WebElement i : languages.subList(9, languages.size())) {
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
		new WebDriverWait(driver, 60).until(ExpectedConditions.presenceOfElementLocated(By.xpath(config.getProperty("courseNames"))));
		JavascriptExecutor js1 = (JavascriptExecutor) driver;
		js1.executeScript("window.scrollBy(0,300)");
		snap("CourseList");
		
		courseName=driver.findElements(By.xpath(config.getProperty("courseNames")));
		courseRating=driver.findElements(By.xpath(config.getProperty("courseRating")));
		courses = driver.findElements(By.xpath(config.getProperty("courses")));
		
	}
	
	public void getCourseDuration(int courseNo) {
		int tabNumber=0;
		String[] duration=new String [courseNo];
		
		//Collecting course duration
		for (int i=0;i<courseNo;i++) {
			new WebDriverWait(driver, 60).until(ExpectedConditions.elementToBeClickable(courses.get(i))).click();
			
			System.out.println("\n"+(i+1)+". "+courseName.get(i).getText());
			
			System.out.println("   Rating: "+ courseRating.get(i).getText());
			
			tabsGUId = new ArrayList<String>(driver.getWindowHandles());
			
			driver.switchTo().window(tabsGUId.get(++tabNumber));	
			
			duration[i] = driver.findElement(By.cssSelector(config.getProperty("courseDuration"))).getText();
			
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("window.scrollBy(0,700)");
			snap("Course page("+ (i+1) +")");
			
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