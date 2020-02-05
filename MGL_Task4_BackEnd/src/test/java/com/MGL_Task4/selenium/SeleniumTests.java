package com.MGL_Task4.selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class SeleniumTests {

    private WebDriver driver;
    private WebDriverWait wait;
    static {
	System.setProperty("webdriver.chrome.driver",
		"C:\\Users\\matthew.wozniak\\Documents\\My Code\\AutomatedTesting - Selenium\\ChromeDriver\\chromedriver.exe");
    }

    @Test
    public void testOpenHomeGoToGamesPageGoBackToHomePage() {
	wait = new WebDriverWait(driver, 5);
	driver.get("http://localhost:7070/MGL_Task4/");
	driver.findElement(By.id("navBar-Link-Games")).click();// assert in games
	driver.findElement(By.id("navBar-Link-Home")).click();// assert back home

    }

    @Test
    public void testGameCrudFunctonality() {
	wait = new WebDriverWait(driver, 10);
	driver.get("http://localhost:7070/MGL_Task4/");
	driver.findElement(By.id("navBar-Link-Games")).click();

	/*
	 * get starting number of games in existing games table
	 */
	int initialNumberOfExistingGames = currentNumberOfExistingGamesInTable();

	/*
	 * Test reset button
	 */
	driver.findElement(By.id("inputText-NewGameForm-GameName")).sendKeys("seleniumTest-GameName");
	driver.findElement(By.id("inputText-NewGameForm-GameGenre")).sendKeys("seleniumTest-GameGenre");
	driver.findElement(By.id("inputText-NewGameForm-ReleaseDate-yyyy-MM-dd")).sendKeys("2020-01-22");
	driver.findElement(By.id("button-NewGameForm-Reset")).click();
	// get current number of Existing Games after reset is clicked
	int numberOfExistingGamesAfterReset = currentNumberOfExistingGamesInTable();
	Assert.assertEquals(initialNumberOfExistingGames, numberOfExistingGamesAfterReset);

	/*
	 * TestCreateGame
	 */
	driver.findElement(By.id("button-NewGameForm-Submit")).click();
	driver.findElement(By.id("inputText-NewGameForm-GameName")).sendKeys("seleniumTest-GameName");
	driver.findElement(By.id("inputText-NewGameForm-GameGenre")).sendKeys("seleniumTest-GameGenre");
	driver.findElement(By.id("inputText-NewGameForm-ReleaseDate-yyyy-MM-dd")).sendKeys("2020-01-22");
	driver.findElement(By.id("button-NewGameForm-Submit")).click();

	/*
	 * get current number of Existing Games after add is clicked
	 */
	checkExistingGamesHasNewElement(initialNumberOfExistingGames);
	int numberOfExistingGamesAfterSubmit = currentNumberOfExistingGamesInTable();
	Assert.assertEquals(initialNumberOfExistingGames + 1, numberOfExistingGamesAfterSubmit);

	/*
	 * TestUpdateGame
	 */
	initialNumberOfExistingGames = currentNumberOfExistingGamesInTable();
	wait.until(ExpectedConditions.presenceOfElementLocated(
		By.id("tableBody-ExistingGames-button-ExistingGame-Update-GameID-seleniumTest-GameName")));
	driver.findElement(By.id("tableBody-ExistingGames-button-ExistingGame-Update-GameID-seleniumTest-GameName"))
		.click();
	driver.findElement(By.id("inputText-NewGameForm-GameName")).clear();
	driver.findElement(By.id("inputText-NewGameForm-GameName")).sendKeys("seleniumTest-GameName-UPDATED");
	driver.findElement(By.id("inputText-NewGameForm-GameGenre")).clear();
	driver.findElement(By.id("inputText-NewGameForm-GameGenre")).sendKeys("seleniumTest-GameGenre-UPDATED");
	driver.findElement(By.id("inputText-NewGameForm-ReleaseDate-yyyy-MM-dd")).clear();
	driver.findElement(By.id("inputText-NewGameForm-ReleaseDate-yyyy-MM-dd")).sendKeys("2020-10-18");
	driver.findElement(By.id("button-NewGameForm-Submit")).click();
	driver.findElement(By.id("button-NewGameForm-Reset")).click();

	/*
	 * get current number of Existing Games after update is clicked
	 */
	int numberOfExistingGamesAfterUpdate = currentNumberOfExistingGamesInTable();
	Assert.assertEquals(initialNumberOfExistingGames, numberOfExistingGamesAfterUpdate);

	/*
	 * TestCreateReview
	 */
	/*
	 * Wait until reviews button for updated game is present and go to
	 * reviewsForGame page
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(
		By.id("tableBody-ExistingGames-button-ExistingGame-Reviews-GameID-seleniumTest-GameName-UPDATED")));
	driver.findElement(
		By.id("tableBody-ExistingGames-button-ExistingGame-Reviews-GameID-seleniumTest-GameName-UPDATED"))
		.click();
	/*
	 * Wait until Review Submission form and fields are present then enter data
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputText-NewReviewForm-ReviewBody")));
	driver.findElement(By.id("input-NewReviewForm-Submit")).click();
	driver.findElement(By.id("inputText-NewReviewForm-ReviewBody")).sendKeys("seleniumTest-ReviewBody");
	driver.findElement(By.id("inputText-NewReviewForm-ReviewAuthor")).sendKeys("111");
	/*
	 * before submitting get current number of reviews in the table
	 */
	int initialNumberOfReviews = currentNumberOfReviewsInTable();
	driver.findElement(By.id("input-NewReviewForm-Submit")).click();

	/*
	 * wait until return to reviews button is present on submittedResult page and
	 * click to return to reviewsForGame page
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("link-submittedReview-ReturnToReviews")));
	driver.findElement(By.id("link-submittedReview-ReturnToReviews")).click();

	/*
	 * wait until reviewsForGame page loads then get number of reviews and check
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputText-NewReviewForm-ReviewBody")));
	Assert.assertTrue(initialNumberOfReviews <= currentNumberOfReviewsInTable());

	/*
	 * TestCreateAnotherReview
	 */

	/*
	 * Wait until Review Submission form and fields are present then enter data
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputText-NewReviewForm-ReviewBody")));
	driver.findElement(By.id("inputText-NewReviewForm-ReviewBody")).sendKeys("seleniumTest-ReviewBody");
	driver.findElement(By.id("inputText-NewReviewForm-ReviewAuthor")).sendKeys("");
	/*
	 * before submitting get current number of reviews in the table
	 */
	initialNumberOfReviews = currentNumberOfReviewsInTable();
	driver.findElement(By.id("input-NewReviewForm-Submit")).click();

	/*
	 * wait until return to reviews button is present on submittedResult page and
	 * click to return to reviewsForGame page
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("link-submittedReview-ReturnToReviews")));
	driver.findElement(By.id("link-submittedReview-ReturnToReviews")).click();

	/*
	 * wait until reviewsForGame page loads then get number of reviews and check
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputText-NewReviewForm-ReviewBody")));
	Assert.assertTrue(initialNumberOfReviews <= currentNumberOfReviewsInTable());

	/*
	 * TestUpdateReview
	 */

	/*
	 * Wait until Review Submission form and fields are present then enter data
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputText-NewReviewForm-ReviewBody")));
	driver.findElement(By.id("inputText-NewReviewForm-ReviewBody")).sendKeys("seleniumTest-ReviewBody");
	driver.findElement(By.id("inputText-NewReviewForm-ReviewAuthor")).sendKeys("");
	/*
	 * before submitting get current number of reviews in the table
	 */
	initialNumberOfReviews = currentNumberOfReviewsInTable();
	driver.findElement(By.id("input-NewReviewForm-Submit")).click();

	/*
	 * wait until return to reviews button is present on submittedResult page and
	 * click to return to reviewsForGame page
	 *
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("link-submittedReview-ReturnToReviews")));
	driver.findElement(By.id("link-submittedReview-ReturnToReviews")).click();

	/*
	 * wait until reviewsForGame page loads then get number of reviews and check
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputText-NewReviewForm-ReviewBody")));
	String newestReviewId = newestReviewIdInTable();
	driver.findElement(By.id("tableBody-Reviews-button-Review-Update-ReviewID-" + newestReviewId)).click();

	// Assert.assertTrue(initialNumberOfReviews <= currentNumberOfReviewsInTable());

	/*
	 * test update review form
	 */
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("inputText-ReviewUpdateForm-ReviewBody")));
	driver.findElement(By.id("inputText-ReviewUpdateForm-ReviewBody")).clear();
	driver.findElement(By.id("button-ReviewUpdateForm-Submit")).click();
	driver.findElement(By.id("inputText-ReviewUpdateForm-ReviewBody")).sendKeys("seleniumTest-ReviewBody-UPDATED");
	driver.findElement(By.id("inputText-ReviewUpdateForm-ReviewAuthor"))
		.sendKeys("seleniumTest-ReviewAuthor-UPDATED");
	driver.findElement(By.id("button-ReviewUpdateForm-Submit")).click();
	driver.findElement(By.id("tableBody-Reviews-button-Review-Delete-ReviewID-" + newestReviewId)).click();
	wait.until(ExpectedConditions.alertIsPresent());
	driver.switchTo().alert().accept();
	Assert.assertTrue(!newestReviewId.equals(newestReviewIdInTable()));
	/*
	 * TestRemoveGame
	 */
	driver.findElement(By.id("navBar-Link-Games")).click();
	initialNumberOfExistingGames = currentNumberOfExistingGamesInTable();
	wait.until(ExpectedConditions.presenceOfElementLocated(
		By.id("tableBody-ExistingGames-button-ExistingGame-Remove-GameID-seleniumTest-GameName-UPDATED")));
	driver.findElement(
		By.id("tableBody-ExistingGames-button-ExistingGame-Remove-GameID-seleniumTest-GameName-UPDATED"))
		.click();
	/*
	 * get current number of Existing Games after update is clicked
	 */
	driver.findElement(By.id("navBar-Link-Games")).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("tableBody-ExistingGames")));
	Assert.assertNotEquals(initialNumberOfExistingGames, currentNumberOfExistingGamesInTable());
    }

    private int currentNumberOfReviewsInTable() {

	/*
	 * get current number of Existing Games
	 */
	return driver.findElement(By.id("tableBody-Reviews")).findElements(By.className("tableBody-Reviews-tr")).size();

    }

    private String newestReviewIdInTable() {

	/*
	 * get current number of Existing Games
	 */
	String idOfNewestReviewUpdateButton = "";
	List<WebElement> listOfReviewUpdateButtons = driver.findElement(By.id("tableBody-Reviews"))
		.findElements(By.cssSelector("[id^=tableBody-Reviews-button-Review-Update-ReviewID-]"));
	for (WebElement we : listOfReviewUpdateButtons) {
	    idOfNewestReviewUpdateButton = we.getAttribute("id").toString();
	}

	return idOfNewestReviewUpdateButton.substring(("tableBody-Reviews-button-Review-Update-ReviewID-").length(),
		idOfNewestReviewUpdateButton.length());

    }

    private int currentNumberOfExistingGamesInTable() {
	/*
	 * get current number of Existing Games
	 */
	return driver.findElement(By.id("tableBody-ExistingGames"))
		.findElements(By.className("tableBody-ExistingGames-tr")).size();

    }

    private Boolean checkExistingGamesHasNewElement(int currentNumberOfGames) {
	boolean newRowHasAppeared = false;
	int numberOfRowsWhileWaiting = currentNumberOfGames;
	while (!newRowHasAppeared) {
	    numberOfRowsWhileWaiting = driver.findElement(By.id("tableBody-ExistingGames"))
		    .findElements(By.className("tableBody-ExistingGames-tr")).size();
	    if (numberOfRowsWhileWaiting > currentNumberOfGames) {
		newRowHasAppeared = true;
	    }
	}

	return Boolean.TRUE;
    }

    @BeforeTest
    public void beforeTest() {
	driver = new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().timeouts().pageLoadTimeout(50, TimeUnit.SECONDS);
	driver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest() {
	driver.quit();
    }

}
