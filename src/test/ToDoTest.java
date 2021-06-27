package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

@TestMethodOrder(OrderAnnotation.class)
class TwitterTest {
	
	private static WebDriver webDriver;
	private static String baseUrl;

	@BeforeAll
	static void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/elmedin/Documents/code/chrome_driver/chromedriver");
		webDriver = new ChromeDriver();
		baseUrl = "https://twitter.com/";
	}
	
	@Test
	@Order(1)
	public void testLogin() throws InterruptedException {
		webDriver.get(baseUrl);
		webDriver.manage().window().maximize();
		Thread.sleep(1000);
		
		WebElement email = webDriver.findElement(By.xpath("/html/body/div/div/div/div/main/div/div/div/div[1]/div[1]/div/form/div/div[1]/div/label/div/div[2]/div/input"));
		WebElement password = webDriver.findElement(By.xpath("/html/body/div/div/div/div/main/div/div/div/div[1]/div[1]/div/form/div/div[2]/div/label/div/div[2]/div/input"));
		WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"react-root\"]/div/div/div/main/div/div/div/div[1]/div[1]/div/form/div/div[3]/div/div/span/span"));

		email.sendKeys("SVVTProject");
		Thread.sleep(500);
		
		password.sendKeys("svvttesting");
		Thread.sleep(500);
		
		loginButton.click();
		Thread.sleep(500);
		
		WebElement weUsername = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/header/div/div/div/div[2]/div/div/div[2]/div/div[2]/div/span"));
		String username = weUsername.getText();
		
		assertEquals("@SVVTProject", username);
		
		Thread.sleep(3000);
	}
	
	@Test
	@Order(2)
	public void testTweet() throws InterruptedException {		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		WebElement tweetButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/header/div/div/div/div[1]/div[3]/a/div"));
		tweetButton.click();
		Thread.sleep(1000);
		
		WebElement tweetTextField = webDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/div[1]/div/div/div/div/div[2]/div[1]/div/div/div/div/div/div/div/div/div/div[1]/div/div/div/div[2]/div/div/div/div"));
		String tweetText = scanner.nextLine();
		tweetTextField.sendKeys(tweetText);
		
		WebElement sendTweetButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div/div[3]/div/div/div/div[1]/div/div/div/div/div[2]/div[4]/div/div/div[2]/div[4]/div/span/span"));
		sendTweetButton.click();		
		
		Thread.sleep(3000);
		
		WebElement confirmation = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[4]/div/div/section/div/div/div[1]/div/div/article/div/div/div/div[2]/div[2]/div[2]/div[1]/div"));
		String confirmationString = confirmation.getText();
		
		Thread.sleep(1000);
		
		assertTrue(confirmationString.contains(tweetText));
		
		Thread.sleep(3000);
	}
	
	@Test
	@Order(3)
	public void testSearch() throws InterruptedException {		
		WebElement exploreButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/header/div/div/div/div[1]/div[2]/nav/a[2]/div"));
		exploreButton.click();
		Thread.sleep(3000);
		
		WebElement searchBar = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[1]/div[1]/div/div/div/div/div[1]/div[2]/div/div/div/form/div[1]/div/div/div[2]/input"));
		searchBar.sendKeys("@KingJames");
		searchBar.sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		
		WebElement firstResult = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/section/div/div/div[3]/div/div/div/div[2]/div[1]/div[1]/a/div/div[1]/div[1]/span/span"));
		firstResult.click();
		Thread.sleep(3000);

		WebElement userName = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/div[1]/div/div[2]/div/div/div[1]/div/span[1]/span"));
		String userNameText = userName.getText();
		
		assertEquals("LeBron James", userNameText);
		
		Thread.sleep(3000);
	}
	
	@Test
	@Order(4)
	public void testMessage() throws InterruptedException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		WebElement messagesButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/header/div/div/div/div[1]/div[2]/nav/a[4]/div"));
		messagesButton.click();
		Thread.sleep(3000);
		
		WebElement userToChatWith = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/section[1]/div[2]/div/div/div[2]/section/div/div/div[1]/div/div[1]"));
		userToChatWith.click();
		Thread.sleep(500);

		WebElement messageInput = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/section[2]/div[2]/div/div/div/div/aside/div[2]/div[2]/div/div/div/div/div[1]/div/div/div/div[2]/div/div/div/div"));
		messageInput.click();
		String message = scanner.nextLine();
		messageInput.sendKeys(message);
		messageInput.sendKeys(Keys.RETURN);
		Thread.sleep(1000);
		
		String pageSource = webDriver.getPageSource();
		
		assertTrue(pageSource.contains(message));
		
		Thread.sleep(3000);
	}

	@Test
	@Order(5)
	public void testFollowUnfollow() throws InterruptedException {
		WebElement exploreButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/header/div/div/div/div[1]/div[2]/nav/a[2]/div"));
		exploreButton.click();
		Thread.sleep(3000);
		
		WebElement searchBar = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[1]/div[1]/div/div/div/div/div[1]/div[2]/div/div/div/form/div[1]/div/div/div[2]/input"));
		searchBar.sendKeys("@EdDzeko");
		searchBar.sendKeys(Keys.RETURN);
		Thread.sleep(3000);
		
		WebElement firstResult = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/section/div/div/div[3]/div/div/div/div[2]/div[1]/div[1]/a/div/div[1]/div[1]/span/span"));
		firstResult.click();
		Thread.sleep(3000);
		
		WebElement followButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/div[1]/div/div[1]/div/div[2]/div/div"));
		
		assertNotEquals("Following", followButton.getText());
		
		followButton.click();
		Thread.sleep(1000);
		
		assertEquals("Following", followButton.getText());
		
		followButton.click();
		Thread.sleep(1000);
		
		WebElement confirmUnfollowButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div[3]/div[2]/div/span/span"));
		confirmUnfollowButton.click();
		Thread.sleep(1000);
		
		assertNotEquals("Following", followButton.getText());
		
		Thread.sleep(3000);
	}
	
	@Test
	@Order(6)
	public void testProfilePage() throws InterruptedException {
		WebElement profileButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/header/div/div/div/div[1]/div[2]/nav/a[7]/div/div[2]/span"));
		profileButton.click();
		Thread.sleep(3000);
		
		WebElement name = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/div/div[1]/div/span[1]/span"));
		String nameString = name.getText();
		
		WebElement username = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/main/div/div/div/div[1]/div/div[2]/div/div/div[1]/div[2]/div[2]/div/div/div[2]/div/span"));
		String usernameString = username.getText();
		
		String url = webDriver.getCurrentUrl();
		
		assertEquals("SVVT", nameString);
		assertEquals("@SVVTProject", usernameString);
		assertEquals("https://twitter.com/SVVTProject", url);
		
		Thread.sleep(3000);
	}
	
	@Test
	@Order(7)
	public void testLogout() throws InterruptedException {
		webDriver.get(baseUrl);
		webDriver.manage().window().maximize();
		Thread.sleep(1000);
		
		WebElement dots = webDriver.findElement(By.xpath("/html/body/div/div/div/div[2]/header/div/div/div/div[2]/div/div/div[3]"));
		dots.click();
		Thread.sleep(1000);
		
		WebElement logoutButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div[2]/div/div[2]/div/div/div/div/div/a[2]"));
		logoutButton.click();
		Thread.sleep(1000);
		
		WebElement logoutConfirmButton = webDriver.findElement(By.xpath("/html/body/div/div/div/div[1]/div[2]/div/div/div/div/div/div[2]/div[2]/div[3]/div[2]/div/span/span"));
		logoutConfirmButton.click();
		Thread.sleep(1000);
		
		String logoutUrl = webDriver.getCurrentUrl();
		
		assertTrue(logoutUrl.contains("logout"));
		
		Thread.sleep(3000);
	}
	
	@AfterAll
	static void tearDown() {
		webDriver.close();
	}

}
