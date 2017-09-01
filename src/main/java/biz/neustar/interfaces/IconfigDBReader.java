package biz.neustar.interfaces;

import javax.xml.xpath.XPathExpressionException;

/**
 * @author adnan hasan
 */
public interface IconfigDBReader {

	public String getDbDriver() throws XPathExpressionException;

	public String getDbUrl() throws XPathExpressionException;

	public String getDbUserName() throws XPathExpressionException;
	
	public String getDbPasswordKey() throws XPathExpressionException;

	public String getDbPassword() throws XPathExpressionException;
	
	public String getDbMinPoolSize();
	
	public String getDbMaxPoolSize();
	
	public String getDbConnectionTimeout();
}
