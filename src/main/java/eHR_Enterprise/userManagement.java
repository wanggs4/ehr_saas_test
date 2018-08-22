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

import utolity.log;
import utolity.utills;

public class userManagement {


	//被注释的方法将在每一个测试方法调用前运行
	@BeforeMethod
	public void openBrowser() {
		utills.openBrowser();
	}

	//被注释的方法将在测试运行前运行
	@BeforeTest
	public void testa() throws Exception {
		utills.module("员工管理");
		WebElement wl = utills.driver.findElement(By.xpath(".//*[@id='_3_']"));
		Select te = new Select(wl);
		// String we = te.getFirstSelectedOption().getText();
		// assertEquals("",we);
		te.selectByValue("16");
	}
	
	//被注释的方法将在每一个测试方法调用后运行
	@AfterMethod
	public void closeBrowser() {
		utills.closeBrowser();
	}
}
