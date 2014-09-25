package by.epam.web.parsers.entities;

public enum EmployeeEnum {
	STAFF("staff"), EMPLOYEE("employee"), MANAGER("manager"), OPERATOR(
			"operator"), CITY("city"), PASSWORD("pass"), FIRSTNAME(
			"firstname"), ID("id"), SECONDNAME("secondname"), BONUS("bonus"), NUMBEROFTOOLS(
			"numberoftools");

	private String value;

	private EmployeeEnum(String value) {// Конструктор

		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
