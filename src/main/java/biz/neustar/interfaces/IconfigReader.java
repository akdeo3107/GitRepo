package biz.neustar.interfaces;

import javax.xml.xpath.XPathExpressionException;

/**
 * @author adnan hasan
 */
public interface IconfigReader {
	public String getUserName() throws XPathExpressionException;

	public String getPassword() throws XPathExpressionException;

	public String getDomain() throws XPathExpressionException;

	public String getWebsite() throws XPathExpressionException;

	public int getPageLoadTimeOut();

	public int getImplicitWait();

	public int getExplicitWait();
	
	public String getGuiName();
	
	public String getPlatformName();
	
	public String getRunType();
	
	public String getBrowser();
	
	public String getLoggerLevel();
	
	public String getEnvironment();
}
