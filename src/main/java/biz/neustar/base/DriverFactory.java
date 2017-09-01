package biz.neustar.base;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import biz.neustar.configuration.browser.BrowserType;
import biz.neustar.driver.base.DriverBase;
import biz.neustar.element.Element;
import biz.neustar.interfaces.IConstants;
import biz.neustar.settings.ObjectRepo;
import biz.neustar.settings.ReadConfig;
import biz.neustar.utils.GenericHelper;
import biz.neustar.utils.LoggerHelper;

/**
 * @author Adnan Hasan
 *
 */
public class DriverFactory {
	protected static Element element = new Element();

	public static String runType;
	public static String envType;

	/**
	 * @param browser
	 *            passing browser detail from config and testng xml (if
	 *            parameter is not set in testng xml file for browser then it
	 *            will check in config properties.)
	 * @throws Exception
	 */
	@Parameters({ "browser", "runtype", "environment" })
	@BeforeSuite
	public void before(@Optional String browser, @Optional String runtype,
			@Optional String envType) throws Exception {
		this.runType = runtype;
		this.envType = envType;
		if (browser == null) {
			browser = ObjectRepo.reader.getBrowser();
		}
		BrowserType browserType = BrowserType.valueOf(browser);
		setUpDriver(browserType);
	}

	/**
	 * Close/Quit all the web driver instances.
	 * 
	 * @throws Exception
	 */
	@AfterSuite
	public void after() throws Exception {
		DriverBase.tearDownDriver();
	}

	/**
	 * Set all the properties related to config.
	 */
	public static void setProperties() {
		ObjectRepo.reader = new ReadConfig();
		if (envType == null) {
			envType = ObjectRepo.reader.getEnvironment();
		}
		if (runType == null) {
			runType = ObjectRepo.reader.getRunType();
		}
		LoggerHelper.setLogLevelWithParameter();
	}

	/**
	 * @param bType
	 *            Browser type
	 * @throws Exception
	 */
	public void setUpDriver(BrowserType bType) throws Exception {
		setProperties();
		ObjectRepo.driver = DriverBase.standAloneStepUp(bType,
				setBrowserExe(bType));
		element.setImplicitWait(
				Long.valueOf(ObjectRepo.reader.getImplicitWait()),
				TimeUnit.SECONDS);
		ObjectRepo.driver.manage().window().maximize();
	}

	public String setBrowserExe(BrowserType browseType) {
		String exePath = null;
		switch (browseType) {
		case chrome:
			exePath = IConstants.Chrome.CHROME_PATH;
			break;
		case iexplorer:
			exePath = IConstants.Iexplore.IEXPLORE_PATH;
			break;
		case firefox:
			exePath = IConstants.Firefox.FIREFOX_PATH;
			break;
		}
		return exePath;
	}

	/**
	 * @param result
	 *            Test result
	 * @throws IOException
	 */
	@AfterMethod
	public void setScreenshot(ITestResult result) throws IOException {
		if (result.getStatus() == ITestResult.FAILURE) {
			new GenericHelper().takeScreenShot(result.getName());
		} else if (result.getStatus() == ITestResult.SKIP) {

		} else {

		}

	}

}
