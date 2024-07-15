package DefinitioQA;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DemoQAUITests {
	static WebDriver driver;
	static WebDriverWait wait;
	Random random = new Random();
	static String testUser;

	@SuppressWarnings("deprecation")
	@BeforeClass
	public static void setup() {
		System.setProperty("webdriver.chrome.driver", "<path-to-chromedriver>");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		Random random = new Random();
		testUser = "testUser" + random.nextInt(0, 50000);
	}

	@Test
	public void testA_Registration() {

		driver.get("https://demoqa.com/register");
		driver.findElement(By.id("firstname")).sendKeys("firstName" + random.nextInt(0, 50000));
		driver.findElement(By.id("lastname")).sendKeys("lastName" + random.nextInt(0, 50000));
		driver.findElement(By.id("userName")).sendKeys(testUser);
		driver.findElement(By.id("password")).sendKeys("Password123!");
		// Time to click captcha manually
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.findElement(By.id("register")).click();
	}

	@Test
	public void testB_Login() {
		driver.get("https://demoqa.com/login");
		driver.findElement(By.id("userName")).sendKeys(testUser);
		driver.findElement(By.id("password")).sendKeys("Password123!");
		driver.findElement(By.id("login")).click();
		WebElement userNameEntered = driver.findElement(By.id("userName-value"));
		Assert.assertTrue(userNameEntered.isDisplayed());
	}

	@Test
	public void testC_Search() {
		driver.get("https://demoqa.com/books");
		driver.findElement(By.id("searchBox")).sendKeys("Git Pocket Guide");
		driver.findElement(By.id("basic-addon2")).click();
		WebElement bookResult = driver.findElement(By.xpath("//a[contains(text(), 'Git Pocket Guide')]"));
		Assert.assertTrue(bookResult.isDisplayed());
	}

	@AfterClass
	public static void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
