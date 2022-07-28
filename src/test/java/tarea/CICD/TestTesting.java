package tarea.CICD;

import static org.junit.Assert.*;

import java.time.Duration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class TestTesting {

	private WebDriver driver;
	private Duration wait;

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		ChromeOptions o = new ChromeOptions();
		o.addArguments("--incognito");
		driver = new ChromeDriver(o);
		driver.manage().window().maximize();
		driver.get("localhost:3000");

		wait = Duration.ofSeconds(10);

	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	public void test() {

		WebElement searchbox = driver.findElement(By.name("q"));
		searchbox.clear();
		searchbox.sendKeys("Probando Selenium");
		searchbox.submit();

		driver.manage().timeouts().implicitlyWait(wait);

		assertEquals("Probando Selenium - Buscar con Google", driver.getTitle());
	}

}
