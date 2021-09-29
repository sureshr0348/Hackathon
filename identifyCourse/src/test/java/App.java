

import java.util.ArrayList;
import java.util.List;

import org.testng.TestNG;

public class App {

	public static void main(String[] args) {

		TestNG suite = new TestNG();
		
		List<String> tests = new ArrayList<String>();
		tests.add(System.getProperty("user.dir")  + "/testng.xml");
		
		suite.setTestSuites(tests);
		
		suite.run();
	}

}
