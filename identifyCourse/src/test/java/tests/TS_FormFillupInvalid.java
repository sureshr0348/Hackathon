package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import functionalities.FormFillingFunctionality;

public class TS_FormFillupInvalid extends BaseClass {

	BaseClass bc = new BaseClass();

	FormFillingFunctionality fill = new FormFillingFunctionality();
	
	@BeforeClass
	public void navigateToForm() {
		fill.navigateForm();
	}
	
	@BeforeMethod
	public void refreshpage() {
		refresh();
	}
	
	
	@Test(priority = 1)
	public void TC_InvalidFirstName() {
		fill.setFormValues(2);
	}

	@Test(priority = 2)
	public void TC_InvalidLastName() {
		fill.setFormValues(3);
	}

	@Test(priority = 3)
	public void TC_InvalidJobFunction() {
		fill.setFormValues(4);
	}

	@Test(priority = 4)
	public void TC_InvalidJobTitle() {
		fill.setFormValues(5);
	}

	@Test(priority = 5)
	public void TC_InvalidEmail() {
		fill.setFormValues(6);
	}

	@Test(priority = 6)
	public void TC_InvalidPhoneNo() {
		fill.setFormValues(7);
	}

	@Test(priority = 7)
	public void TC_InvalidInstitutionName() {
		fill.setFormValues(8);
	}

	@Test(priority = 8)
	public void TC_InvalidInstitutionType() {
		fill.setFormValues(9);
	}

	@Test(priority = 9)
	public void TC_InvalidPrimaryDiscipline() {
		fill.setFormValues(10);
	}

	@Test(priority = 10)
	public void TC_InvalidCountry() {
		fill.setFormValues(11);
	}

	@Test(priority = 11)
	public void TC_InvalidState() {
		fill.setFormValues(12);
	}
}