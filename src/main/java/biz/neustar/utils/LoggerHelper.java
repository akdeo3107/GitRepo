package biz.neustar.utils;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import biz.neustar.interfaces.IConstants;
import biz.neustar.settings.ObjectRepo;

/**
 * @author adnan hasan
 *
 */
@SuppressWarnings("rawtypes")
public class LoggerHelper {

	private static boolean root = false;
	private static String logLevel = null;
	private static Logger loggerRoot;

	public static Logger getLogger(Class clas) {
		String log4jConfigFile = IConstants.Log4j.LOG4J_PROPERTIES_FILE_PATH;
		if (root)
			return Logger.getLogger(clas);

		PropertyConfigurator.configure(log4jConfigFile);
		root = true;
		return Logger.getLogger(clas);
	}

	public static void setLogLevelWithParameter() {
		loggerRoot = Logger.getRootLogger();
		Level lLevel = getLoggerLevel();
		if (lLevel == null) {
			logLevel = loggerRoot.getLevel().toString();
		} else {
			logLevel = lLevel.toString();
		}
		boolean logLevelRecognized = true;
		if ("DEBUG".equalsIgnoreCase(logLevel)) {
			loggerRoot.setLevel(Level.DEBUG);
		} else if ("INFO".equalsIgnoreCase(logLevel)) {
			loggerRoot.setLevel(Level.INFO);
		} else if ("WARN".equalsIgnoreCase(logLevel)) {
			loggerRoot.setLevel(Level.WARN);
		} else if ("ERROR".equalsIgnoreCase(logLevel)) {
			loggerRoot.setLevel(Level.ERROR);
		} else if ("FATAL".equalsIgnoreCase(logLevel)) {
			loggerRoot.setLevel(Level.FATAL);
		} else {
			logLevelRecognized = false;
		}

		if (logLevelRecognized) {
			System.out.println("Log level has been set to: " + logLevel);
		} else {
			System.out.println("logLevel parameter '" + logLevel
					+ "' level not recognized<br/>");
		}
	}

	public static Level getLoggerLevel() {

		switch (ObjectRepo.reader.getLoggerLevel().toUpperCase()) {

		case "DEBUG":
			return Level.DEBUG;
		case "INFO":
			return Level.INFO;
		case "WARN":
			return Level.WARN;
		case "ERROR":
			return Level.ERROR;
		case "FATAL":
			return Level.FATAL;
		}
		return null;
	}

}
