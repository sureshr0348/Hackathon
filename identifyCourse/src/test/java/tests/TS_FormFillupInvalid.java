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
	public void TC_InvalidEmail() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(2);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 2)
	public void TC_wrongData1() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(3);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 3)
	public void TC_wrongData2() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(4);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 4)
	public void TC_wrongData3() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(5);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 5)
	public void TC_wrongData4() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(6);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 6)
	public void TC_wrongData5() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(7);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 7)
	public void TC_wrongData6() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(8);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 8)
	public void TC_wrongData7() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(9);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 9)
	public void TC_wrongData8() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(10);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 10)
	public void TC_wrongData9() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(11);
		System.out.println("Form filled with invalid details");
	}

	@Test(priority = 11)
	public void TC_wrongData10() {
		System.out.println("Form Fill-up initiated");
		fill.setFormValues(12);
		System.out.println("Form filled with invalid details");
	}
}