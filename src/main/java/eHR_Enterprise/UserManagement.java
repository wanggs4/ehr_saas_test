package eHR_Enterprise;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utolity.Log;
import utolity.Utills;

public class UserManagement {


	//被注释的方法将在每一个测试方法调用前运行
	@BeforeMethod
	public void openBrowser() throws InterruptedException {
//		utills utills=new utills();
		Utills.openBrowser();
		Utills.driver.get(Utills.LoginTestUrl);
		Thread.sleep(3000);
		Utills.login(Utills.TestUser, Utills.TestPwd);
	}

	//被注释的方法将在测试运行前运行
	@Test
	public void testa() throws Exception {
		Utills.module("员工管理");
		
	}
	
	//被注释的方法将在每一个测试方法调用后运行
	@AfterMethod
	public void closeBrowser() {
		Utills.closeBrowser();
	}
}
