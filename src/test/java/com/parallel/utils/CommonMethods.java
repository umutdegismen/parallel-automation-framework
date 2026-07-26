package com.parallel.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonMethods extends ExcelUtils {

	/**
	 * This method clears a text box and send the text parameter to it.
	 * 
	 * @param element
	 * @param text
	 */
	public static void sendText(WebElement element, String text) {
		element.sendKeys(text);
	}

	/**
	 * This method creates a WebDriverWait object based on the provided
	 * EXPLICIT_WAIT_TIME.
	 * 
	 * @return WebDriverWait
	 */
	public static WebDriverWait getWaitObject() {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(Constants.EXPLICIT_WAIT_TIME));
	}

	/**
	 * This method creates a WebDriverWait object based on the specified time
	 * provided as a parameter.
	 * 
	 * @param seconds
	 * @return WebDriverWait
	 */
	public static WebDriverWait getWaitObject(int seconds) {
		return new WebDriverWait(getDriver(), Duration.ofSeconds(seconds));
	}

	/**
	 * This method waits for clickability of the element by WebElement.
	 * 
	 * @param element
	 * @return WebElement with explicit wait
	 */
	public static WebElement waitForClickable(WebElement element) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * This method waits for clickability of the element by Locator.
	 * 
	 * @param locator
	 * @return WebElement with explicit wait
	 */
	public static WebElement waitForClickable(By locator) {
		return getWaitObject().until(ExpectedConditions.elementToBeClickable(locator));
	}

	/**
	 * This method waits for visibility of the element by WebElement.
	 * 
	 * @param element
	 * @return WebElement with explicit wait
	 */
	public static WebElement waitForVisibility(WebElement element) {
		return getWaitObject().until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method waits for visibility of the element by Locator.
	 * 
	 * @param locator
	 * @return WebElement with explicit wait
	 */
	public static WebElement waitForVisibility(By locator) {
		return getWaitObject().until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	/**
	 * This method waits for the number seconds provided as parameter.
	 * 
	 * @param seconds
	 */
	public static void wait(int seconds) {
		try {
			Thread.sleep(seconds * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method waits for the element to be clickable then clicks by WebElement.
	 * 
	 * @param element
	 */
	public static void click(WebElement element) {
		waitForClickable(element);
		element.click();
	}

	/**
	 * This method waits for clickability then it clicks By Locator.
	 * 
	 * @param locator
	 */
	public static void click(By locator) {
		waitForClickable(locator);
		getDriver().findElement(locator).click();
	}

	/**
	 * This method checks if the radio/checkbox is enabled, and then clicks on the
	 * element that have the attribute value as selectValue.
	 * 
	 * @param elementList
	 * @param value
	 */
	public static void clickRadioOrCheckbox(List<WebElement> elementList, String value) {
		for (WebElement element : elementList) {
			String elementValue = element.getAttribute("value").trim();
			if (elementValue.equals(value)) {
				click(element);
				break;
			}
		}
	}

	/**
	 * This method selects a drop down element based on visible text.
	 * 
	 * @param element
	 * @param text
	 */
	public static void selectDropDownByVisibleText(WebElement element, String text) {
		try {
			Select s = new Select(element);
			s.selectByVisibleText(text);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method selects a drop down element based on index.
	 * 
	 * @param element
	 * @param index
	 */
	public static void selectDropDownByIndex(WebElement element, int index) {
		try {
			Select s = new Select(element);
			s.selectByIndex(index);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method selects a drop down element based on value.
	 * 
	 * @param element
	 * @param value
	 */
	public static void selectDropDownByValue(WebElement element, String value) {
		try {
			Select s = new Select(element);
			s.selectByValue(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the WebDriver to the Alert.
	 * <p>
	 * If there is an Alert, it accepts it.
	 * <p>
	 * <p>
	 * If there is no Alert, throws a NoAlertPresent exception.
	 * <p>
	 * 
	 * @param driver
	 */
	public static void acceptAlert() {
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.accept();
		} catch (NoAlertPresentException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the WebDriver to the Alert.
	 * <p>
	 * If there is an Alert, it dismisses it.
	 * <p>
	 * <p>
	 * If there is no Alert, throws a NoAlertPresent exception.
	 * <p>
	 * 
	 * @param driver
	 */
	public static void dismissAlert() {
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.dismiss();
		} catch (NoAlertPresentException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the WebDriver to the Alert.
	 * <p>
	 * If there is an Alert, gets the text and returns AlertText.
	 * <p>
	 * <p>
	 * If there is no Alert, throws a NoAlertPresent exception.
	 * <p>
	 * 
	 * @param driver
	 * @return AlertText
	 */
	public static String getTextAlert() {
		String alertText = "";
		try {
			Alert alert = getDriver().switchTo().alert();
			alertText = alert.getText();
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
		return alertText;
	}

	/**
	 * This method switches the focus of the WebDriver to the Alert.
	 * <p>
	 * If there is an Alert, sends the provided text as parameter.
	 * <p>
	 * <p>
	 * If there is no Alert, throws a NoAlertPresent exception.
	 * <p>
	 * 
	 * @param driver
	 * @param text
	 */
	public static void sendTextAlert(String text) {
		try {
			Alert alert = getDriver().switchTo().alert();
			alert.sendKeys(text);
		} catch (NoAlertPresentException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the driver to the frame by WebElement.
	 * <p>
	 * Gets the provided WebElement as parameter.
	 * <p>
	 * <p>
	 * If there is no frame, it throws NoSuchFrameException.
	 * <p>
	 * 
	 * @param driver
	 * @param element
	 */
	public static void switchToFrame(WebElement element) {
		try {
			getDriver().switchTo().frame(element);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the driver to the frame by Index.
	 * <p>
	 * Gets the provided int value as parameter.
	 * <p>
	 * <p>
	 * If there is no frame, it throws NoSuchFrameException.
	 * <p>
	 * 
	 * @param index
	 * @param driver
	 */
	public static void switchToFrame(int index) {
		try {
			getDriver().switchTo().frame(index);
		} catch (NoSuchFrameException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the driver to the frame by NameOrId.
	 * <p>
	 * Gets the provided String value as parameter.
	 * <p>
	 * <p>
	 * If there is no frame, it throws NoSuchFrameException.
	 * <p>
	 * 
	 * @param driver
	 * @param nameOrId
	 */
	public static void switchToFrame(String nameOrId) {
		try {
			getDriver().switchTo().frame(nameOrId);
		} catch (NoSuchFrameException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * This method switches the focus of the driver to the child window.
	 * 
	 */
	public static void switchToChildWindow() {
		Set<String> handles = getDriver().getWindowHandles();
		String currentHandle = getDriver().getWindowHandle();
		for (String handle : handles) {
			if (!handle.equals(currentHandle)) {
				getDriver().switchTo().window(handle);
				break;
			}
		}
	}

	/**
	 * This method switches the focus of the driver to the parent window.
	 * <p>
	 * Closes the child window.
	 * 
	 */
	public static void returnToDefaultContent() {
		getDriver().switchTo().defaultContent();
	}

	/**
	 * This method will cast the driver to a JavascriptExecutor object and return
	 * it.
	 * 
	 * @return
	 */
	public static JavascriptExecutor getJsObject() {
		return (JavascriptExecutor) getDriver();
	}

	/**
	 * This method will click on an element using JavascriptExecutor
	 * 
	 * @param element
	 */
	public static void jsClick(WebElement element) {
		getJsObject().executeScript("arguments[0].click()", element);
	}

	/**
	 * This method scrolls the page until a specific element is in view on top of
	 * the page.
	 * 
	 * @param element
	 */
	public static void scrollToElementOnTop(WebElement element) {
		getJsObject().executeScript("arguments[0].scrollIntoView(true)", element);
	}

	/**
	 * This method scrolls the page until a specific element is in view on bottom of
	 * the page
	 * 
	 * @param element
	 */
	public static void scrolltoElementOnBottom(WebElement element) {
		getJsObject().executeScript("arguments[0].scrollIntoView(false)", element);
	}

	/**
	 * This method scrolls the page UP using the pixels parameter.
	 * 
	 * @param pixel
	 */
	public static void scrollUp(int pixel) {
		getJsObject().executeScript("window.scrollBy(0,+" + pixel + ")");
	}

	/**
	 * This method scrolls the page UP using the pixels parameter.
	 * 
	 * @param pixel
	 */
	public static void scrollDown(int pixel) {
		getJsObject().executeScript("window.scrollBy(0,-" + pixel + ")");
	}

	/**
	 * This method will select a date on a calendar whose elements are provided as
	 * the first parameter and select the date that is the second parameter
	 * 
	 * @param elements
	 * @param dayToSelect
	 */
	public static void selectCalendarDate(List<WebElement> dayList, String dayToSelect) {

		for (WebElement day : dayList) {
			if (day.getText().equals(dayToSelect)) {
				if (day.isEnabled()) {
					click(day);
					break;
				} else {
					System.out.println("This day is not enabled!");
					break;
				}
			}
		}
	}

	/**
	 * This method returns a TimeStamp as a String.
	 * 
	 * @return
	 */
	public static String getTimeStamp() {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM_dd_yyyy_HH_mm_ss");
		return dateFormat.format(date);
	}

	/**
	 * This method returns byte[] - image file itself
	 * <p>
	 * This method checks the screenshot folder, if there is not any existing
	 * folder, it creates one.
	 * <p>
	 * It takes the screenshot and saves it in that folder.
	 * 
	 */
	public static byte[] takeScreenShot(String fileName) {

		TakesScreenshot ts = (TakesScreenshot) getDriver();

		byte[] screenshotFile = ts.getScreenshotAs(OutputType.BYTES);

		File screenShot = ts.getScreenshotAs(OutputType.FILE);

		String destination = Constants.SCREENSHOT_FILEPATH + fileName + "_" + getTimeStamp() + ".png";

		try {
			FileUtils.copyFile(screenShot, new File(destination));
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to save the screenshot!!!");
		}

		return screenshotFile;

	}

}
