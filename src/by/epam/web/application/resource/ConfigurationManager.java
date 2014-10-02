package by.epam.web.application.resource;

import java.util.ResourceBundle;

/*
 * This class extracts data from config.properties file.
 */
public class ConfigurationManager {

	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("resources.config");

	
	private ConfigurationManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
