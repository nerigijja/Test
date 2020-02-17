package Login.Login_test;

import org.openqa.selenium.WebDriver;

import Utilties.HelperClass;


public class Login_Test extends HelperClass{
	
	public static WebDriver driver;
	
	public static void Login() {
		
		login("Helper","BaseURL", 1, 0, 1, 1);
		explicitWaitForClickable(3000, "Helper", "Logout_Xpath");
		clickWebelement("Helper", "Logout_Xpath");
	
	}
public static void main(String[] args) {
	
	Login();
}
}
