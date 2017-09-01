package biz.neustar.utils;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

/**
 * @author adnan hasan
 *
 */
public class UIPopulationHelper extends UIPopulation {
	String filePath;

	public UIPopulationHelper(String filePath) {
		this.filePath = filePath;
	}

	public String getNodeLocator(String xpath) {
		String nodeLocator = null;
		try {
			nodeLocator = setFilePath(filePath).getNodeLocatorByXpath(xpath);
		} catch (XPathExpressionException | IOException
				| ParserConfigurationException | SAXException e) {
			e.printStackTrace();
		}
		return nodeLocator;
	}

}
