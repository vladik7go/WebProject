package by.epam.web.application.resource;

import java.util.ResourceBundle;

/*
 * This class extracts data from messages.properties file.
 */
public class MessageManager {

	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("resources.messages_bundle");

	private MessageManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
