package biz.neustar.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * @author adnan hasan
 *
 */
public class XMLReader {

	private XPath xPath;
	private Document xmlDocument;
	private String filePath;
	private int iteration;
	List<String> tagVal;

	/**
	 * @param file
	 * @param tagname
	 */
	public XMLReader(String file, String tagname) {
		this.filePath = file;
		xmlReader(tagname);
	}

	/**
	 * @param tagName
	 * @return
	 */
	public int xmlReader(String tagName) {
		try {
			FileInputStream file = new FileInputStream(new File(filePath));
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			xmlDocument = builder.parse(file);
			xPath = XPathFactory.newInstance().newXPath();
			NodeList nList = xmlDocument.getElementsByTagName(tagName);
			iteration = nList.getLength();
			System.out.println("iteration: " + nList.getLength());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return iteration;
	}

	/**
	 * used only for database configuration where platform name and env name and
	 * its related data source are getting fetched.
	 * 
	 * @param tagName
	 * @return
	 * @throws XPathExpressionException
	 */
	public String getDBElementByTagName(String platformname,
			String env, String tagName) throws XPathExpressionException {
		String tgvalue = null;
		String expression = "/dbsettings/platform[@name=" + "'" + platformname + "']/datasource[@env="
				+ "'" + env + "']/" + tagName;
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
				xmlDocument, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
			tgvalue = nodeList.item(i).getFirstChild().getNodeValue();
			break;
		}
		return tgvalue;
	}

	
	/**
	 * used only for configuration where platform name and gui name and its
	 * related credentials are getting fetched.
	 * 
	 * @param platformname
	 * @param guiname
	 * @param tagname
	 * @param env
	 * @return
	 * @throws XPathExpressionException
	 */
	public String getElementByTagName(String platformname,
			String guiname, String tagname, String env) throws XPathExpressionException {
		String tgvalue = null;
		String expression ="/settings/platform[@name='"+platformname+"'][@env='"+env+"']/"
				+ "application[@guiname='"+guiname+"']/"+tagname;
		
		NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
				xmlDocument, XPathConstants.NODESET);
		for (int i = 0; i < nodeList.getLength(); i++) {
			System.out.println(nodeList.item(i).getFirstChild().getNodeValue());
			tgvalue = nodeList.item(i).getFirstChild().getNodeValue();
			break;
		}
		return tgvalue;
	}

}
