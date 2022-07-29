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

public class ContactTest {

	private WebDriver driver;
	private Duration wait;

	@Before
	public void setUp() throws Exception {

		System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
		ChromeOptions o = new ChromeOptions();
		o.addArguments("headless");
		o.addArguments("disable-gpu");
		o.addArguments("--incognito");
		driver = new ChromeDriver(o);
		driver.manage().window().maximize();
		driver.get("http://localhost:3000");

		wait = Duration.ofSeconds(10);

	}

	@After
	public void tearDown() throws Exception {
		driver.manage().timeouts().implicitlyWait(wait);
		driver.quit();
	}

	@Test
	public void AddContactTest() {
		WebElement addContactButton = driver.findElement(By.id("add-contact-button"));
		WebElement tableBody = driver.findElement(By.id("tableBody"));

		addContactButton.click();

		WebElement name = driver.findElement(By.id("name"));
		WebElement lastName = driver.findElement(By.id("lastName"));
		WebElement secondLastName = driver.findElement(By.id("secondLastName"));
		WebElement email = driver.findElement(By.id("email"));
		WebElement phone = driver.findElement(By.id("phone"));
		WebElement sendButton = driver.findElement(By.id("send"));

		name.sendKeys("Diego");
		lastName.sendKeys("Aguilera");
		secondLastName.sendKeys("Alvarado");
		email.sendKeys("diego.aguileraa@sansano.usm.cl");
		phone.sendKeys("912345678");

		sendButton.click();

		driver.manage().timeouts().implicitlyWait(wait);

		try {
			assertEquals("Diego", tableBody.findElement(By.xpath("//tr/td[1]")).getText());
			assertEquals("Aguilera", tableBody.findElement(By.xpath("//tr/td[2]")).getText());
			assertEquals("Alvarado", tableBody.findElement(By.xpath("//tr/td[3]")).getText());
			assertEquals("diego.aguileraa@sansano.usm.cl", tableBody.findElement(By.xpath("//tr/td[4]")).getText());
			assertEquals("912345678", tableBody.findElement(By.xpath("//tr/td[5]")).getText());
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			assertEquals("Diego", tableBody.findElement(By.xpath("//tr/td[1]")).getText());
			assertEquals("Aguilera", tableBody.findElement(By.xpath("//tr/td[2]")).getText());
			assertEquals("Alvarado", tableBody.findElement(By.xpath("//tr/td[3]")).getText());
			assertEquals("diego.aguileraa@sansano.usm.cl", tableBody.findElement(By.xpath("//tr/td[4]")).getText());
			assertEquals("912345678", tableBody.findElement(By.xpath("//tr/td[5]")).getText());

		}

		WebElement deleteButton = driver
				.findElement(By.xpath("//html/body/div/div/div[1]/div/div/table/tbody/tr/td[6]/button[2]"));
		deleteButton.click();
		WebElement confirmDeleteButton = driver
				.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[3]/button[1]"));
		confirmDeleteButton.click();

	}

	@Test
	public void EditContactTest() {

		WebElement addContactButton = driver.findElement(By.id("add-contact-button"));
		WebElement tableBody = driver.findElement(By.id("tableBody"));

		addContactButton.click();

		WebElement name = driver.findElement(By.id("name"));
		WebElement lastName = driver.findElement(By.id("lastName"));
		WebElement secondLastName = driver.findElement(By.id("secondLastName"));
		WebElement email = driver.findElement(By.id("email"));
		WebElement phone = driver.findElement(By.id("phone"));
		WebElement sendButton = driver.findElement(By.id("send"));

		name.sendKeys("Diego");
		lastName.sendKeys("Aguilera");
		secondLastName.sendKeys("Alvarado");
		email.sendKeys("diego.aguileraa@sansano.usm.cl");
		phone.sendKeys("912345678");

		sendButton.click();

		driver.manage().timeouts().implicitlyWait(wait);

		tableBody = driver.findElement(By.id("tableBody"));
		try {
			driver.findElement(By.xpath("//html/body/div/div/div[1]/div/div/table/tbody/tr/td[6]/button[1]")).click();
		} catch (org.openqa.selenium.StaleElementReferenceException e) {
			driver.findElement(By.xpath("//html/body/div/div/div[1]/div/div/table/tbody/tr/td[6]/button[1]")).click();
		}

		WebElement editName = driver.findElement(By.id("name"));
		sendButton = driver.findElement(By.id("send"));

		editName.clear();
		editName.sendKeys("DiegoE");
		sendButton.click();

		driver.manage().timeouts().implicitlyWait(wait);

		tableBody = driver.findElement(By.id("tableBody"));
		assertEquals("DiegoE", tableBody.findElement(By.xpath("//tr/td[1]")).getText());

		WebElement deleteButton = driver
				.findElement(By.xpath("//html/body/div/div/div[1]/div/div/table/tbody/tr/td[6]/button[2]"));
		deleteButton.click();
		WebElement confirmDeleteButton = driver
				.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[3]/button[1]"));
		confirmDeleteButton.click();

	}

	@Test
	public void DeleteContactTest() {
		WebElement addContactButton = driver.findElement(By.id("add-contact-button"));

		addContactButton.click();

		WebElement name = driver.findElement(By.id("name"));
		WebElement lastName = driver.findElement(By.id("lastName"));
		WebElement secondLastName = driver.findElement(By.id("secondLastName"));
		WebElement email = driver.findElement(By.id("email"));
		WebElement phone = driver.findElement(By.id("phone"));
		WebElement sendButton = driver.findElement(By.id("send"));

		name.sendKeys("Diego");
		lastName.sendKeys("Aguilera");
		secondLastName.sendKeys("Alvarado");
		email.sendKeys("diego.aguileraa@sansano.usm.cl");
		phone.sendKeys("912345678");

		sendButton.click();

		driver.manage().timeouts().implicitlyWait(wait);

		WebElement deleteButton = driver
				.findElement(By.xpath("//html/body/div/div/div[1]/div/div/table/tbody/tr/td[6]/button[2]"));
		deleteButton.click();
		WebElement confirmDeleteButton = driver
				.findElement(By.xpath("//html/body/div[2]/div/div[1]/div/div/div[3]/button[1]"));
		confirmDeleteButton.click();

		driver.manage().timeouts().implicitlyWait(wait);
		WebElement noContacts = driver.findElement(By.id("noContacts"));
		assertEquals("No hay contactos", noContacts.getText());

	}

}
