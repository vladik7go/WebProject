package by.epam.web.application.resource;

import java.util.ResourceBundle;

public class MessageManager {

	private final static ResourceBundle resourceBundle = ResourceBundle
			.getBundle("resources.messages_bundle");

	// ����� ��������� ���������� �� ����� messages.properties
	private MessageManager() {
	}

	public static String getProperty(String key) {
		return resourceBundle.getString(key);
	}

}
