package biz.neustar.utils;

import java.lang.reflect.Method;

import org.testng.annotations.DataProvider;

import biz.neustar.base.DriverFactory;
import biz.neustar.test.datautils.CallerXml;
import biz.neustar.test.datautils.XmlFileParameters;

/**
 * @author adnan hasan
 *
 */
public class CallerXMLHelper extends CallerXml {

	public Object[][] parseCallerXml(String path, String type) {
		return parseTestFlowXml(path, type);
	}

	public Object[][] parseCallerXml(String path) {
		return parseTestFlowXml(path, DriverFactory.runType);
	}

	@DataProvider(name = "basicDataProvider", parallel = true)
	public static Object[][] basicDataProvider(final Method testMethod)
			throws Exception {

		XmlFileParameters parameters = testMethod
				.getAnnotation(XmlFileParameters.class);
		String path;
		if (parameters != null) {
			path = parameters.path();

		} else {
			throw new RuntimeException("Couldn't find the annotation");
		}
		CallerXMLHelper call = new CallerXMLHelper();
		Object[][] gotCallerContents = call.parseCallerXml(path);
		gotCallerContents.toString();

		System.out.println(gotCallerContents.toString());

		return gotCallerContents;
	}
}
