package 易HR_企业端;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import utolity.log;
import utolity.utills;

public class 用户管理 {
	WebDriver dr;

	@BeforeTest
	public void setUp() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\guosheng.wang\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		utills.driver = new ChromeDriver();
		utills.driver.manage().window().maximize();
		utills.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void testa() throws Exception {
		
		dr.findElement(By.xpath(".//*[@id='r1']/ul/li[5]/table/tbody/tr/td[2]")).click();
		log.info("点击行政办公");
		Thread.sleep(2000);
		dr.findElement(By.xpath(".//*[@id='m09']/ul/li[1]/div[1]")).click();
		log.info("点击办公用品");
		dr.findElement(By.xpath(".//*[@id='m09']/ul/li[1]/div[2]/ul/li[4]/span")).click();
		log.info("点击办公用品管理");
		Thread.sleep(2000);
		dr.findElement(By.xpath("html/body/div[1]/div[1]/div/div[2]/span[1]")).click();
		log.info("点击新增");
		dr.findElement(By.xpath(".//*[@id='_2_']/div/input")).sendKeys("0");
		log.info("办公用品名称");
		dr.findElement(By.xpath(".//*[@id='_6_']/div/div[2]/div[1]/div[2]/div/div/input")).sendKeys("1");
		log.info("计量单位");
		WebElement wl = dr.findElement(By.xpath(".//*[@id='_3_']"));
		Select te = new Select(wl);
		// String we = te.getFirstSelectedOption().getText();
		// assertEquals("",we);
		te.selectByValue("16");

		dr.quit();
	}

}
