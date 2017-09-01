package biz.neustar.interfaces;

public interface IConstants {

	interface Chrome {
		public static final String CHROME_PATH = "config\\driver\\win\\";
	}

	interface Firefox {
		public static final String FIREFOX_PATH = "config\\driver\\win\\";
	}

	interface Iexplore {
		public static final String IEXPLORE_PATH = "config\\driver\\win\\";
	}

	interface Log4j {
		public static final String LOG4J_PROPERTIES_FILE_PATH = "config\\log4j\\log4j.properties";
	}

	interface Config {
		public static final String CONFIG_PATH = "config\\config.xml";
		public static final String PROJECT_PROPERTIES_FILE_PATH = "config\\config.properties";
		public static final String XML_CONFIG_TAG_NAME = "settings";
	}

	interface Screenshot {
		public static final String SCREENSHOT_PATH = "screenshot";
	}

	interface DBconfig {
		public static final String DB_CONFIG_PATH = "config\\database\\database.xml";
		public static final String XML_DB_TAG_NAME = "dbsettings";
	}

}
