package biz.neustar.settings;

import javax.xml.xpath.XPathExpressionException;

import biz.neustar.base.DriverFactory;
import biz.neustar.interfaces.IConstants;
import biz.neustar.interfaces.IconfigReader;
import biz.neustar.utils.PropertyFileReader;
import biz.neustar.utils.XMLReader;

/**
 * @author ahasan
 *
 */
public class ReadConfig implements IconfigReader {
	private static PropertyFileReader propertyFileReader;

	public ReadConfig() {
		setPropertyFileReader(propertyFileReader
				.loadProperty(IConstants.Config.PROJECT_PROPERTIES_FILE_PATH));
	}

	public static PropertyFileReader getPropertyFileReader() {
		return propertyFileReader;
	}

	public static void setPropertyFileReader(
			PropertyFileReader propertyFileReader) {
		ReadConfig.propertyFileReader = propertyFileReader;
	}

	static XMLReader xmlReader;
	private static String guiname;
	public static String platformname;
	private static String envirString;

	/**
	 * get data from config.xml file
	 * 
	 * @param guiname
	 * @param platformname
	 */
	public static void getDataByGUIName(String guiname, String platformname) {
		xmlReader = new XMLReader(IConstants.Config.CONFIG_PATH,
				IConstants.Config.XML_CONFIG_TAG_NAME);
		ReadConfig.guiname = guiname;
		ReadConfig.platformname = platformname;
		ReadConfig.envirString = DriverFactory.envType;
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getUserName()
	 */
	@Override
	public String getUserName() throws XPathExpressionException {
		return xmlReader.getElementByTagName(ReadConfig.platformname, 
				ReadConfig.guiname, "username",ReadConfig.envirString);
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getPassword()
	 */
	@Override
	public String getPassword() throws XPathExpressionException {
		return xmlReader.getElementByTagName(ReadConfig.platformname, 
				ReadConfig.guiname, "password",ReadConfig.envirString);
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getDomain()
	 */
	@Override
	public String getDomain() throws XPathExpressionException {
		return xmlReader.getElementByTagName(ReadConfig.platformname, 
				ReadConfig.guiname, "domain",ReadConfig.envirString);
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getWebsite()
	 */
	@Override
	public String getWebsite() throws XPathExpressionException {
		return xmlReader.getElementByTagName(ReadConfig.platformname, 
				ReadConfig.guiname, "url",ReadConfig.envirString);
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getPageLoadTimeOut()
	 */
	@Override
	public int getPageLoadTimeOut() {
		return Integer.parseInt(getPropertyFileReader().getProperty(
				"PageLoadTimeOut"));
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getImplicitWait()
	 */
	@Override
	public int getImplicitWait() {
		return Integer.parseInt(getPropertyFileReader().getProperty(
				"ImplcitWait"));
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getExplicitWait()
	 */
	@Override
	public int getExplicitWait() {
		return Integer.parseInt(getPropertyFileReader().getProperty(
				"ExplicitWait"));
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getRunType()
	 */
	@Override
	public String getRunType() {
		return getPropertyFileReader().getProperty("Run.Type");
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getEnvironment()
	 */
	@Override
	public String getGuiName() {
		return ReadConfig.guiname;
	}

	/*
	 * @see com.gui.interfaces.IconfigReader#getPlatformName()
	 */
	@Override
	public String getPlatformName() {
		return ReadConfig.platformname;
	}

	@Override
	public String getBrowser() {
		return getPropertyFileReader().getProperty("Browser");
	}

	@Override
	public String getLoggerLevel() {
		return getPropertyFileReader().getProperty("Logger.Level");
	}

	@Override
	public String getEnvironment() {
		return getPropertyFileReader().getProperty("Environment");
	}

}
