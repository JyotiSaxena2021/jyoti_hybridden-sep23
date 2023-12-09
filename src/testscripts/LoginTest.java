package testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.ControlActions;
import pages.LoginPage;

public class LoginTest {

	@BeforeMethod
	public void setup() {
		ControlActions.launchBrowser();
	}
	
	@Test
	public void verifyLogin() {
		LoginPage loginPage = new LoginPage();
		loginPage.login("jyotisaxena2006@gmail.com", "Welcome@1234");
		boolean loginFlag = loginPage.isLoginSuccessFullyDisplayed();
		Assert.assertTrue(loginFlag);
	}
	
	@AfterMethod
	public void tearDown() {
		ControlActions.closeBrowser();
	}
}
