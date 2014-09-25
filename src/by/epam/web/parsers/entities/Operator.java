package by.epam.web.parsers.entities;

public class Operator extends Employee {
	private int numberOfTools;

	public int getNumberOfTools() {
		return numberOfTools;
	}

	public void setNumberOfTools(int numberOfTools) {
		this.numberOfTools = numberOfTools;
	}

	@Override
	public String toString() {
		return "\nOperator ID: " + this.getId() + "\nFirstName: "
				+ this.getFirstName() + "\nSecondName:" + this.getSecondName()
				+ "\nSity: " + this.getCity() + "\nNumberOfTools: "
				+ this.getNumberOfTools() + "\n-------------------";
	}
}
