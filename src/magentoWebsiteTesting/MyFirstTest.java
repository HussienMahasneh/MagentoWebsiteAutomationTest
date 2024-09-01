package magentoWebsiteTesting;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class MyFirstTest {
	WebDriver driver = new ChromeDriver();
	String myWebsite = "https://magento.softwaretestingboard.com/";
	Random rand = new Random();
	String Password = "IlovemyMom12345@";
	String LogOutPage = "https://magento.softwaretestingboard.com/customer/account/logout/";
	String EmailAddressToLogin = "";

	@BeforeTest
	public void mystup() {
		driver.manage().window().maximize();
		driver.get(myWebsite);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test(priority = 1, enabled = false)

	public void CreatAnAccount() {
		// Xpath
		// WebElement
		// creteAccountpage=driver.findElement(By.xpath("//a[@href=\'https://magento.softwaretestingboard.com/customer/account/create/']"));
		// partialLinkText
		// WebElement
		// creteAccountpage=driver.findElement(By.partialLinkText("Account"));
		// LinkText
		WebElement creteAccountpage = driver.findElement(By.linkText("Create an Account"));

		creteAccountpage.click();
// first names 
		String[] first_Names = { "Alice", "Bob", "Charlie", "David", "Eva", "Frank", "Grace", "Kathy", "Liam", "Mia",
				"Noah", "Olivia", "Paul", "Quinn", "Rita", "Sam", "Tina" };
//last names
		String[] last_Names = { "Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore",
				"Taylor", "Anderson", "Thomas", "Jackson", "White", "Harris", "Martin", "Thompson", "Garcia",
				"Martinez", "Robinson" };
		// implement
		int randomIndexForFirstNames = rand.nextInt(first_Names.length);
		int randomIndexForLasttNames = rand.nextInt(last_Names.length);
		System.out.println(randomIndexForFirstNames);
		System.out.println(randomIndexForLasttNames);

		WebElement firstNameInput = driver.findElement(By.id("firstname"));
		WebElement lastNameInput = driver.findElement(By.id("lastname"));
		WebElement emailAddressInput = driver.findElement(By.id("email_address"));
		WebElement PasswordInput = driver.findElement(By.id("password"));
		WebElement ConfirmPasswordInput = driver.findElement(By.id("password-confirmation"));
		WebElement CreateaccountButton = driver.findElement(By.xpath("//button[@title='Create an Account']"));
		// Action
		String firstnames = first_Names[randomIndexForFirstNames];
		String lastnames = last_Names[randomIndexForLasttNames];
		// ببعث الاسم
		System.out.println(firstnames);
		System.out.println(lastnames);

		int randomnumber = rand.nextInt(9975);
		String domainnumber = "@gmail.com";
		// ببعث القيمه ال value
		firstNameInput.sendKeys(firstnames);
		lastNameInput.sendKeys(lastnames);
		// emailAddressInput.sendKeys(firstnames + lastnames + rand.nextInt(9975) +
		// "@gmail.com");
		emailAddressInput.sendKeys(firstnames + lastnames + randomnumber + domainnumber);

		PasswordInput.sendKeys(Password);
		ConfirmPasswordInput.sendKeys(Password);
		CreateaccountButton.click();
		EmailAddressToLogin = firstnames + lastnames + randomnumber + domainnumber;

		WebElement MessageAsWebElement = driver.findElement(By.className("messages"));
		String ActualMessage = MessageAsWebElement.getText();
		String ExpectedMessage = "Thank you for registering with Main Website Store.";

		// if( ActualMessage == ExpectedMessage) {
		// System.out.println("passed");
		// } else {
		// System.out.println("failed");
		// }
		Assert.assertEquals(ActualMessage, ExpectedMessage); // Real Test

	}

	@Test(priority = 2, enabled = false)
	public void logout() throws InterruptedException {

		driver.get(LogOutPage);
		// Reviw
		WebElement LogoutMessage = driver.findElement(By.xpath("//span[@data-ui-id='page-title-wrapper']"));

		String ActualMsg = LogoutMessage.getText();
		String ExpectedMsg = "You are signed out";

		Assert.assertEquals(ActualMsg, ExpectedMsg);
	}

	@Test(priority = 3, enabled = false)
	public void Loginpage() {

		WebElement SighnInInput = driver.findElement(By.linkText("Sign In"));
		SighnInInput.click();

		WebElement Emaillogin = driver.findElement(By.id("email"));
		WebElement passwordloginInput = driver.findElement(By.id("pass"));
		WebElement SighninButton = driver.findElement(By.cssSelector(".action.login.primary"));

		Emaillogin.sendKeys(EmailAddressToLogin);
		passwordloginInput.sendKeys(Password);
		SighninButton.click();

		String WelcomMessage = driver.findElement(By.className("logged-in")).getText();

		boolean ActualValue = WelcomMessage.contains("Welcome");
		boolean ExpectedValue = true;
		Assert.assertEquals(ActualValue, ExpectedValue);

	}

	@Test(priority = 4, enabled = false)
	public void addMenItem() throws InterruptedException {

		WebElement MenSection = driver.findElement(By.id("ui-id-5"));

		MenSection.click();
//System.out.println(driver.findElements(By.className("product-item")).size());
		WebElement productItemsContainer = driver.findElement(By.className("product-items"));
//System.out.println(productItemsContainer.findElements(By.tagName("li")).size());
		List<WebElement> AllItems = productItemsContainer.findElements(By.tagName("li")); // قسمنا ال driver

		int totalNumberOfItems = AllItems.size();
		int RandomItem = rand.nextInt(totalNumberOfItems);
		AllItems.get(RandomItem).click();
// Sizes 
//WebElement theContainerOfSizes=driver.findElement(By.cssSelector(".swatch-attribute.size")); class parent
		WebElement theContainerOfSizes = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));// child
//select any one i will select class name
//System.out.println(theContainerOfSizes.findElements(By.className("swatch-option")).size());
//System.out.println(theContainerOfSizes.findElements(By.tagName("div")).size());
		List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.className("swatch-option"));
//int numberOfSizes = theContainerOfSizes.findElements(By.className("swatch-option")).size();
		int numberOfSizes = ListOfSizes.size();

		int randomSize = rand.nextInt(numberOfSizes);
		ListOfSizes.get(randomSize).click();
		// colors
		WebElement ColorContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));

		List<WebElement> ListOfColors = ColorContainer.findElements(By.tagName("div"));
		int numberOfColors = ListOfColors.size();
		int randomColors = rand.nextInt(numberOfColors);
		ListOfColors.get(randomColors).click();

// Button AddtoCart
		WebElement AddtoCart = driver.findElement(By.id("product-addtocart-button"));
		AddtoCart.click();

//WRONG CODE ////////////////////////////////////////////////////////
		// WebElement MenSection = driver.findElement(By.cssSelector("#ui-id-5"));
		// MenSection.click();
		// WebElement ItemsContnainer =
		// driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
// بحث ب جزئيه بسيطه داخل ال driver الرئيسي 
		// List<WebElement> Items = ItemsContnainer.findElements(By.tagName("li"));
//for (int i=0 ; i<size() ; i++)
		// System.out.println(Items.size());
		// Items.get(0).click();
		// driver.navigate().back();
		// Thread.sleep(3000);
		// Items.get(1).click();
///////////////////////////////////////////////////////////////////
//Thread.sleep(10000);
		WebElement MessageAdded = driver.findElement(By.className("message-success"));
		System.out.println(MessageAdded.getText().contains("You added"));
		Assert.assertEquals(MessageAdded.getText().contains("You added"), true); // Real Test (Acctual,excpted)

	}

	@Test(priority = 5)
	public void addWomenItem() throws InterruptedException {
		WebElement WomenSection = driver.findElement(By.id("ui-id-4"));

		WomenSection.click();
//System.out.println(driver.findElements(By.className("product-item")).size());
		WebElement productItemsContainer = driver.findElement(By.className("product-items"));
//System.out.println(productItemsContainer.findElements(By.tagName("li")).size());
		List<WebElement> AllItems = productItemsContainer.findElements(By.tagName("li")); // قسمنا ال driver

		int totalNumberOfItems = AllItems.size();
		int RandomItem = rand.nextInt(totalNumberOfItems);
		AllItems.get(RandomItem).click();
// Sizes 
//WebElement theContainerOfSizes=driver.findElement(By.cssSelector(".swatch-attribute.size")); class parent
		WebElement theContainerOfSizes = driver.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));// child
//select any one i will select class name
//System.out.println(theContainerOfSizes.findElements(By.className("swatch-option")).size());
//System.out.println(theContainerOfSizes.findElements(By.tagName("div")).size());
		List<WebElement> ListOfSizes = theContainerOfSizes.findElements(By.className("swatch-option"));
//int numberOfSizes = theContainerOfSizes.findElements(By.className("swatch-option")).size();
		int numberOfSizes = ListOfSizes.size();

		int randomSize = rand.nextInt(numberOfSizes);
		ListOfSizes.get(randomSize).click();
		// colors
		WebElement ColorContainer = driver
				.findElement(By.cssSelector("div[class='swatch-attribute color'] div[role='listbox']"));

		List<WebElement> ListOfColors = ColorContainer.findElements(By.tagName("div"));
		int numberOfColors = ListOfColors.size();
		int randomColors = rand.nextInt(numberOfColors);
		ListOfColors.get(randomColors).click();

// Button AddtoCart
		WebElement AddtoCart = driver.findElement(By.id("product-addtocart-button"));
		AddtoCart.click();

		WebElement MessageAdded = driver.findElement(By.cssSelector(".message-success.success.message"));
		System.out.println(MessageAdded.getText().contains("You added"));
		Assert.assertEquals(MessageAdded.getText().contains("You added"), true); // Real Test (Acctual,excpted)

		// Review Section

		driver.navigate().refresh();

		WebElement ReviewSEction = driver.findElement(By.id("tab-label-reviews-title"));

		ReviewSEction.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollTo(0,1200)");

		Thread.sleep(2000);

		WebElement RatingStars = driver.findElement(By.cssSelector(".control.review-control-vote"));

		;

		Thread.sleep(2000);

		System.out.println(RatingStars.findElements(By.tagName("label")).size() + "*****************");
//		RatingStars.findElements(By.tagName("label")).get(2).click();

		String[] ids = { "Rating_1", "Rating_2", "Rating_3", "Rating_4", "Rating_5" };
		int randomIndex = rand.nextInt(ids.length);
		WebElement element = driver.findElement(By.id(ids[randomIndex]));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		// استدعاء بلغة ال JavaScript

		WebElement Nickname = driver.findElement(By.id("nickname_field"));
		Nickname.sendKeys("Hussein");
		WebElement Summary = driver.findElement(By.id("summary_field"));
		Summary.sendKeys("any thing");
		WebElement review = driver.findElement(By.id("review_field"));
		review.sendKeys("hello I'm Hussein i write the code in 2024");
		WebElement ButtonSumbitReview = driver.findElement(By.cssSelector(".action.submit.primary"));
		ButtonSumbitReview.click();

		String actualTextforReview = driver.findElement(By.cssSelector(".message-success.success.message")).getText();
		String expectedTextforReview = "You submitted your review for moderation.";

		Assert.assertEquals(actualTextforReview, expectedTextforReview);

	}

}
