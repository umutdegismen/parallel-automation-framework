package com.parallel.scratch;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class LinkChecker {
	public static void main(String[] args) {

		// Get the broken links in Selenium

		String urlString = "http://www.deadlinkcity.com/";

		// RestAssured
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(urlString);
		List<WebElement> links = driver.findElements(By.tagName("a"));
		
		for(WebElement element: links) {
			String link = element.getAttribute("href");
			
			if(link == null || link.isBlank()) {
				continue; // skip
			}
			
			try{
				//relaxedHTTPSValidation() is a Rest Assured configuration method that tells Rest Assured to ignore SSL certificate problems when sending HTTPS requests.
				Response response = RestAssured
						.given()
						.relaxedHTTPSValidation()
						.head(link);
				// HEAD or GET -> both can be used
				// HEAD is faster because it asks the server for headers only, without the response body.
				// GET is slower because it asks for headers + full response body (HTML, JSON, images, etc.).
				// But most servers doesn't accept HEAD http method so, GET is more reliable on most servers.
				
				int status = response.getStatusCode();
				
				if(status >= 400)
				{
					System.out.println("Link is dead -> Status code: "+ status +"\n Link: "+ link);
				}	
			}catch(Exception ex) {
				System.out.println("Link is not reachable. \n Link: " + link);
			}
		}
		
		driver.quit();

		// Core Java
//		WebDriver driver = new EdgeDriver();
//		driver.manage().window().maximize();
//		driver.get(urlString);
//
//		List<WebElement> linkList = driver.findElements(By.tagName("a"));
//
//		for (WebElement element : linkList) {
//			String link = element.getAttribute("href");
//
//			if (link == null || link.isBlank()) {
//				continue;
//			}
//
//			try {
//				URI uri = URI.create(link);
//				HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();
//				conn.setRequestMethod("HEAD");
//				int statusCode = conn.getResponseCode();
//
//				if (statusCode >= 400) {
//					System.out.println("Link is dead. -> Status: " + statusCode + "\n " + link);
//				}
//			} catch (Exception e) {
//				System.out.println("Link is not reachable! \n Link: " + link);
//			}
//		}
//
//		driver.quit();
	}
}
