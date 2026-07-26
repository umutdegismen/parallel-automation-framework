package com.parallel.testbase;

import com.parallel.pages.amazon.AmazonCartElements;
import com.parallel.pages.amazon.AmazonMainPageElements;
import com.parallel.pages.amazon.AmazonProductPageElements;
import com.parallel.pages.amazon.AmazonSearchResultsPageElements;
import com.parallel.pages.automationexercise.AutomationExerciseMainPageElements;
import com.parallel.pages.ebay.EbayDashBoardPageElements;
import com.parallel.pages.ebay.EbayRegisterPageElements;
import com.parallel.pages.hrm.HrmDashBoardPageElements;
import com.parallel.pages.hrm.HrmLoginPageELements;

public class PageInitializer extends DriverFactory {

	// Initializing the page elements with ThreadLocal to create multiple
	// pageElement objects in parallel
	
	// HRM page elements
	private static ThreadLocal<HrmLoginPageELements> hrmLoginPageELements = new ThreadLocal<>();
	private static ThreadLocal<HrmDashBoardPageElements> hrmDashBoardPageElements = new ThreadLocal<>();
	
	// EBAY page elements
	private static ThreadLocal<EbayDashBoardPageElements> ebayDashBoardPageElements = new ThreadLocal<>();
	private static ThreadLocal<EbayRegisterPageElements> ebayRegisterPageElements = new ThreadLocal<>();

	// AMAZON page elements
	private static ThreadLocal<AmazonMainPageElements> amazonMainPageElements = new ThreadLocal<>();
	private static ThreadLocal<AmazonSearchResultsPageElements> amazonSearchResultsPageElements = new ThreadLocal<>();
	private static ThreadLocal<AmazonProductPageElements> amazonProductPageElements = new ThreadLocal<>();
	private static ThreadLocal<AmazonCartElements> amazonCartElements = new ThreadLocal<>();
	
	// AutomationExercise.com page elements
	private static ThreadLocal<AutomationExerciseMainPageElements> automationExerciseMainPageElements = new ThreadLocal<>();
	
	
	
	// GETTER methods
	public static HrmLoginPageELements getHrmLoginPage() {
		if (hrmLoginPageELements.get() == null) {
			hrmLoginPageELements.set(new HrmLoginPageELements());
		}
		return hrmLoginPageELements.get();
	}

	public static HrmDashBoardPageElements getHrmDashboardPage() {
		if (hrmDashBoardPageElements.get() == null) {
			hrmDashBoardPageElements.set(new HrmDashBoardPageElements());
		}
		return hrmDashBoardPageElements.get();
	}
	
	
	

	public static EbayDashBoardPageElements getEbayDashBoardPageElements() {
		if (ebayDashBoardPageElements.get() == null) {
			ebayDashBoardPageElements.set(new EbayDashBoardPageElements());
		}
		return ebayDashBoardPageElements.get();
	}

	public static EbayRegisterPageElements getEbayRegisterPageElements() {
		if (ebayRegisterPageElements.get() == null) {
			ebayRegisterPageElements.set(new EbayRegisterPageElements());
		}
		return ebayRegisterPageElements.get();
	}
	
	
	

	public static AmazonMainPageElements getAmazonMainPageElements() {
		if (amazonMainPageElements.get() == null) {
			amazonMainPageElements.set(new AmazonMainPageElements());
		}
		return amazonMainPageElements.get();
	}

	public static AmazonSearchResultsPageElements getAmazonSearchResultsPageElements() {
		if (amazonSearchResultsPageElements.get() == null) {
			amazonSearchResultsPageElements.set(new AmazonSearchResultsPageElements());
		}
		return amazonSearchResultsPageElements.get();
	}

	public static AmazonProductPageElements getAmazonProductPageElements() {
		if (amazonProductPageElements.get() == null) {
			amazonProductPageElements.set(new AmazonProductPageElements());
		}
		return amazonProductPageElements.get();
	}
	
	public static AmazonCartElements getAmazonCartElements() {
		if(amazonCartElements.get()==null) {
			amazonCartElements.set(new AmazonCartElements());
		}
		return amazonCartElements.get();
	}
	
	
	
	
	public static AutomationExerciseMainPageElements getAEMainPageElements() {
		if(automationExerciseMainPageElements.get() == null) {
			automationExerciseMainPageElements.set(new AutomationExerciseMainPageElements());
		}
		return automationExerciseMainPageElements.get();
	}
	

	
	
	/**
	 * This method initializes the page objects.
	 */
	public static void initialize() {
		getHrmLoginPage();
		getHrmDashboardPage();
		getEbayDashBoardPageElements();
		getEbayRegisterPageElements();
		getAmazonMainPageElements();
		getAmazonSearchResultsPageElements();
		getAmazonProductPageElements();
		getAmazonCartElements();
		getAEMainPageElements();
	}

	/**
	 * This method cleans the threads.
	 */
	public static void cleanUp() {
		hrmLoginPageELements.remove();
		hrmDashBoardPageElements.remove();
		ebayDashBoardPageElements.remove();
		ebayRegisterPageElements.remove();
		amazonMainPageElements.remove();
		amazonSearchResultsPageElements.remove();
		amazonProductPageElements.remove();
		amazonCartElements.remove();
		automationExerciseMainPageElements.remove();
	}
}
