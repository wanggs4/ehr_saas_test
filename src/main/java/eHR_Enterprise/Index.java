package eHR_Enterprise;

import static org.testng.Assert.assertFalse;

import java.rmi.server.UID;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.SeleneseTestNgHelper;
import com.thoughtworks.selenium.webdriven.WebDriverBackedSelenium;

import junit.framework.Assert;
//import utolity.log;
import utolity.Utills;

public class Index {

	@BeforeMethod
	public void openBrowser() {
//		utills.openBrowser();
	}

	public static void loginIndex(String user, String pwd) {
		Utills.driver.get(Utills.LoginFormalUrl);
		Utills.login(user, pwd);
	}

	@BeforeTest
	public void 首页title() {
		loginIndex(Utills.TestUser, Utills.TestPwd);
		Assert.assertEquals(Utills.driver.getTitle(), "首页-易HR企业端");	
	}

	@BeforeTest
	public void 首页logo() {
		loginIndex(Utills.TestUser, Utills.TestPwd);
		boolean logo = Utills.driver.findElement(By.className("hd-logobox")).findElement(By.tagName("img"))
				.getAttribute("src").contentEquals("images/head/head-logo.png");
		Assert.assertFalse("企业端首页左上角人事服务系统logo存在", logo);
	}

	@BeforeTest
	public void 首页_消息代办() {
		loginIndex(Utills.TestUser, Utills.TestPwd);
		boolean 消息代办 = Utills.driver.findElement(By.xpath("//*[@class='con-center fl-left']")).getAttribute("h2")
				.contentEquals("消息待办");
		Assert.assertFalse("消息代办存在", 消息代办);
	}

	@BeforeTest
	public void 首页_信息提示() {
		loginIndex(Utills.TestUser, Utills.TestPwd);
		WebElement xpath = Utills.driver.findElement(By.xpath("//*[@class='right-bar fl-left']"));
		boolean 信息 = xpath.getAttribute("h2").equals(" 信息提示");
		Assert.assertFalse("首页右侧【信息提示】存在", 信息);
		// WebElement h2=(WebElement) xpath.findElements(By.tagName("h2"));
		List<WebElement> links = xpath.findElements(By.tagName("h3"));
		// 获取集合中元素的总数量并转为String格式（下方assertEquals方法参数格式要求）
		String size = String.valueOf(links.size());
		// String size = Integer.toString(links.size());
		// String size = links.size()+"";
		// 验证链接数量
		SeleneseTestNgHelper.assertEquals("2", size);
		// 打印href属性
		// 通过for循环获得list中的所有元素，再调用getAttribute()方法得到元素的属性
		for (int i = 0; i < links.size(); i++) {
			System.out.println(links.get(i).getAttribute("href"));
			Assert.assertEquals("/html/Company/Notice/NoticeList.html?mp=72", links.get(i));
			Assert.assertEquals("/html/company/message/messagelist.html?mp=72", links.get(i));
			// boolean 信息1= h2.getAttribute("h2").equals(" 公告提示");
			// Assert.assertFalse("首页右侧【信息提示】存在",信息1);
			// boolean 信息2= xpath.getAttribute("h2").equals(" 任务待办");
			// Assert.assertFalse("首页右侧【信息提示】存在",信息2);
		}
	}

	@BeforeTest
	public void 首页_功能模块() {
		loginIndex(Utills.TestUser, Utills.TestPwd);

		Assert.assertEquals("消息代办存在", "");
	}

	@AfterMethod
	public void closeBrowser() {
		Utills.closeBrowser();
	}

}
