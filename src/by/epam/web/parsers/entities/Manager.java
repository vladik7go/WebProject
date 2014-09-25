package by.epam.web.parsers.entities;

public class Manager extends Employee {
	private String password;
	private int bonus;

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "\nManager ID: " + this.getId() + "\nFirstName: "
				+ this.getFirstName() + "\nSecondName:" + this.getSecondName()
				+ "\nSity: " + this.getCity() + "\nBonus: " + this.getBonus()
				+ "\nPassword: " + this.getPassword() + "\n-------------------";
	}
}
