package biz.neustar.settings;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.xpath.XPathExpressionException;

import biz.neustar.interfaces.IConstants;
import biz.neustar.interfaces.IconfigDBReader;
import biz.neustar.utils.GenerateDecryptText;
import biz.neustar.utils.XMLReader;

/**
 * @author adnan hasan
 *
 */
public class XMLDatabase implements IconfigDBReader {
	static XMLReader xmlReader;
	private static String env;
	private static String platformname;
	
	public XMLDatabase()
	{}
	
	/**
	 * get data from database.xml file
	 * (this method need to be run before the database connection 
	 * if any arg values are getting changed)
	 * 
	 * @param env
	 * @param platformname
	 */
	public XMLDatabase(String env, String platformname)
	{
		xmlReader = new XMLReader(
				IConstants.DBconfig.DB_CONFIG_PATH,
				IConstants.DBconfig.XML_DB_TAG_NAME);
		XMLDatabase.env = env;
		XMLDatabase.platformname = platformname;
	}

	/*
	 * @see com.gui.interfaces.IconfigDBReader#getDbDriver()
	 */
	@Override
	public String getDbDriver() throws XPathExpressionException {
		return xmlReader.getDBElementByTagName(XMLDatabase.platformname, XMLDatabase.env, "DatabaseDriver");
	}

	/*
	 * @see com.gui.interfaces.IconfigDBReader#getDbUrl()
	 */
	@Override
	public String getDbUrl() throws XPathExpressionException {
		return xmlReader.getDBElementByTagName(XMLDatabase.platformname, XMLDatabase.env, "DatabaseUrl");
	}

	/*
	 * @see com.gui.interfaces.IconfigDBReader#getDbUserName()
	 */
	@Override
	public String getDbUserName() throws XPathExpressionException {
		return xmlReader.getDBElementByTagName(XMLDatabase.platformname, XMLDatabase.env, "DatabaseUserName");
	}

	/*
	 * @see com.gui.interfaces.IconfigDBReader#getDbPasswordKey()
	 */
	@Override
	public String getDbPasswordKey() throws XPathExpressionException {
		return xmlReader.getDBElementByTagName(XMLDatabase.platformname, XMLDatabase.env, "DatabaseKey");
	}

	/*
	 * @see com.gui.interfaces.IconfigDBReader#getDbPassword()
	 */
	@Override
	public String getDbPassword() throws XPathExpressionException {
		try {
			return GenerateDecryptText.decryptPassword(getDbPasswordKey(),
					xmlReader.getDBElementByTagName(XMLDatabase.platformname, XMLDatabase.env,
							"DatabasePassword"));
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| NoSuchPaddingException | IllegalBlockSizeException
				| BadPaddingException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getDbMinPoolSize() {
		return ReadConfig.getPropertyFileReader().getProperty("db.min_pool_size");
	}

	@Override
	public String getDbMaxPoolSize() {
		return ReadConfig.getPropertyFileReader().getProperty("db.max_pool_size");
	}

	@Override
	public String getDbConnectionTimeout() {
		return ReadConfig.getPropertyFileReader().getProperty("db.connection_timeout");
	}

}