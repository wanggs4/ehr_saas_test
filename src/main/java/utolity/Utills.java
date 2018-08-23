package utolity;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class Utills {
	public static WebDriver driver;

	public static final String LoginTestUrl = "http://47.92.110.143:9999/www/other/login.html";
	public static final String LoginFormalUrl = "https://www.ctgpayroll.com/www/index.html";
	public static final String TestUser = "18612533709";
	public static final String TestPwd = "123456";
	public static final String TestUser1 = "18612533709";
	public static final String TestPwd1 = "999999";

	public static void login(String user, String pwd) {
		String text = "企业登录";
		String text1 = "请登录";
		// String text3 = "员工登录";
		if (Utills.driver.findElement(By.linkText(text)).equals(text)) {
			Utills.driver.findElement(By.name("mobile")).sendKeys(user);
			Utills.driver.findElement(By.name("password")).sendKeys(pwd);
			Utills.driver.findElement(By.className("register-btn")).click();
		} else if (Utills.driver.findElement(By.linkText(text1)).equals(text1)) {
			Utills.driver.findElement(By.name("mobile")).sendKeys(user);
			Utills.driver.findElement(By.name("password")).sendKeys(pwd);
			Utills.driver.findElement(By.className("register-btn")).click();
		} else {
			Utills.driver.findElement(By.className("register-btn")).click();
			Alert alert = driver.switchTo().alert();
			String text2 = alert.getText();
			System.out.println(text2);
		}
	}

	/**
	 * 打开浏览器
	 */
	public static void openBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\guosheng.wang\\AppData\\Local\\Google\\Chrome\\Application\\chromedriver.exe");
		Utills.driver = new ChromeDriver();
		Utills.driver.manage().window().maximize();
		// utills.driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	/**
	 * 关闭浏览器
	 */
	public static void closeBrowser() {
		Utills.driver.quit();
	}

	public static void module(String module) {
		Utills.driver.get(Utills.LoginTestUrl);
		Utills.login(Utills.TestUser, Utills.TestPwd);
		Utills.driver.findElement(By.linkText(module)).click();
	}

	/**
	 * 下拉框操作得方法
	 */
	public static void select() {
		WebElement wl = Utills.driver.findElement(By.xpath(".//*[@id='_3_']"));
		Select te = new Select(wl);
		te.selectByValue("16");
	}

	/**
	 * This method implements how to switch windows
	 * 
	 * @author xjjiang
	 * @param cwindow
	 * 
	 * 
	 */

	public static void switchWindows(String cwindow) {

		Set<String> Ahandles = driver.getWindowHandles();
		Iterator<String> Ait = Ahandles.iterator();
		while (Ait.hasNext()) {
			String crWindowString = Ait.next();
			System.out.println("next = " + crWindowString);
			if (cwindow == crWindowString) {
				continue;
			}
			driver.switchTo().window(crWindowString);
		}
	}

	public static String getTestData(String key) {
		ExcelData.setPath(Contants.path + Contants.filename, Contants.sheetname);
		int rowNum = ExcelData.getRowContains(key, Contants.keycolumn);
		String cellValue = ExcelData.getCellData(rowNum, Contants.column);
		return cellValue;
	}

	public static String getCSVTestData(String key) {
		String vlaue = null;
		for (String[] row : ExcelData.getLocatorsFromObjectsFile()) {
			if (row[0].equalsIgnoreCase(key)) {
				vlaue = row[1];

			}
		}
		return vlaue;
	}

	/**
	 * 通过key值读取页面元素
	 * 
	 * @param key
	 * @return
	 */
	public static By getlocator(String key) {
		By locat = null;
		String locatorType = null;
		String locator = null;
		for (String[] row : ExcelData.getLocatorsFromObjectsFile()) {
			if (row[0].equalsIgnoreCase(key)) {
				locatorType = row[1];
				locator = row[2];
				break;
			}
		}
		switch (locatorType) {
		case "id":
			locat = By.id(locator);
			break;
		case "name":
			locat = By.name(locator);
			break;
		case "xpath":
			locat = By.xpath(locator);
			break;
		case "LinkText":
			locat = By.linkText(locator);
			break;
		default:
			Log.warn("Can not find the locator type. locator type is:" + locatorType);
			break;
		}
		return locat;
	}

	/**
	 * 返回页面元素
	 * 
	 * @return
	 */
	public static WebElement getElement(String key) {

		WebElement element;

		By locat = getlocator(key);
		element = driver.findElement(locat);
		if (!element.isDisplayed()) {
			// System.out.println(element + "~~~~~~~~~~~~~~");
			Log.warn("Can not find the element:" + element);
		}
		return element;

	}

	/**
	 * 
	 * @param flag
	 * @param value
	 * @param locator
	 */

	public static void inputValue(boolean flag, String value, String locator) {
		String inputData = "";
		if (flag) {
			inputData = value;
			if (inputData != null) {
				getElement(locator).clear();
				getElement(locator).sendKeys(inputData);
				Log.info("test data :" + inputData + "is input.");
			} else {
			}

			inputData = Utills.getTestData(value);
			if (inputData != null) {
				getElement(locator).clear();
				getElement(locator).sendKeys(inputData);
				Log.info("test data :" + inputData + "is input.");
			}
		}
	}

	/**
	 * This meth implements that input test data for a field and datermine
	 * whetherthe test data need to be got from testdata file through the flag.
	 * 
	 * @param value
	 * @param locator
	 */

	public static void inputValue(String value, String locator) {
		String inputData = "";
		inputData = Utills.getTestData(value);
		if (inputData != null) {
			getElement(locator).clear();
			getElement(locator).sendKeys(inputData);
			Log.info("test data :" + inputData + "is input.");
		}
	}

	/**
	 * 封装打开浏览器
	 */
	public static void openBrowser1(String url, String browser) {
		// 判断
		switch (browser) {
		case "firefox":
			FirefoxProfile profile = new FirefoxProfile();
			profile.setPreference("security.mixed_content.block_activer_content", false);
			profile.setPreference("security.mixed_content.block_display_content", true);
			System.setProperty("webdriver.firefox.bin", "F:\\ Firefox\\firefox.exe");
			driver = new FirefoxDriver();
			break;
		case "ie":
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
			ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			System.setProperty("webdriver.ie.driver", Contants.path + Contants.ieDriverServer);
			driver = new InternetExplorerDriver();
			break;
		default:
			Log.warn("Can not find the browser type. and the browser type is :" + browser);
			break;
		}
		driver.get(url);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	// 对Click操作进行封装,加等待时间
	public static void waitforSLoad() throws Exception {
		// (new WebDriverWait(driver,80)).until(new )
	}

	public static void elementClick(String locator) {
		WebElement element;
		element = getElement(locator);
		element.click();
		Log.info(element + "is clicked");

	}

	// 封装frame的方法
	public static void switchFrame(String id) {
		driver.switchTo().frame(id);
	}

	// 封装select的方法
	public static void selectValue(String value, String locator, String flag) {
		WebElement element = getElement(locator);
		Select select = new Select(element);
		String dateValue = getTestData(value);
		if (flag.equalsIgnoreCase("byvalue")) {

			select.selectByValue(dateValue);
		} else {
			select.selectByVisibleText(dateValue);
		}
		Log.info("is selected" + dateValue);
	}

	// 封装断言的方法,存两个值
	public static void assertText(String value, String locator) {
		String actualValue = "";
		String expectedValue = "";
		actualValue = getElement(locator).getText();
		expectedValue = getTestData(value);
		// 第一个实际值，一个预期值,第三个断言失败的信息
		Assert.assertEquals(actualValue, expectedValue, "Assert is Fail with Actual data is [" + actualValue
				+ "]  And Expected Result  is [" + expectedValue + "].");

	}
}
