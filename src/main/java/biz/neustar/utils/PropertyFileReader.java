package biz.neustar.utils;

import java.util.Properties;

import tools.ReadProperties;

public class PropertyFileReader {

	Properties properties;

	public PropertyFileReader loadProperty(String fileName) {
		properties = ReadProperties.loadProperties(fileName);
		return new PropertyFileReader();
	}

	public String getProperty(String key) {
		return properties.getProperty(key);
	}

}
