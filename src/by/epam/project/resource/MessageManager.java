package by.epam.project.resource;

import java.util.ResourceBundle;

/*
 * This class extracts data from messages.properties file.
 */
public class MessageManager {

	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("messages_bundle");

	private MessageManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
