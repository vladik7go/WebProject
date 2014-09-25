package by.epam.web.parsers.sax;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import by.epam.web.parsers.entities.Employee;
import by.epam.web.parsers.entities.EmployeeEnum;
import by.epam.web.parsers.entities.Manager;
import by.epam.web.parsers.entities.Operator;



public class StaffHandler extends DefaultHandler {
	private Set<Employee> employee;// ������� ���������� ���� ��������� ���
	private Employee current = null;// ������� ���������� ���� Employee
	private EmployeeEnum currentEnum = null;
	private EnumSet<EmployeeEnum> withText;// ������� ���������� ���� EnumSet
											// (���������, ��� �����
											// ���������-����� ����� � ���������
											// ����������

	public StaffHandler() {// �����������
		employee = new HashSet<Employee>();// ������� ��������� ���� ���
		withText = EnumSet.range(EmployeeEnum.FIRSTNAME,// ������� ���������
														// ���� EnumSet � ������
														// ���� ��������� ��
														// ����������
				EmployeeEnum.NUMBEROFTOOLS);

	}

	public Set<Employee> getEmployee() {
		return employee;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attrs) {
		if ("manager".equals(localName)) {// --��������� ���������� �� ���������
											// �������� manager
			current = new Manager();
			current.setCity(attrs.getValue(1));
			if (attrs.getLength() == 2) {
				((Manager) current).setPassword(attrs.getValue(0));
			}

		} else if ("operator".equals(localName)) {// --��������� ���������� ��
													// ��������� ��������
													// operator
			current = new Operator();
			current.setCity(attrs.getValue(0));
		} else {
			EmployeeEnum temp = EmployeeEnum.valueOf(localName.toUpperCase());
			if (withText.contains(temp)) {
				currentEnum = temp;
			}
		}
	}// ����� ������------------------------------------------------------

	@Override
	public void characters(char[] ch, int start, int length) {

		String s = new String(ch, start, length).trim();
		if (currentEnum != null) {
			switch (currentEnum) {
			case FIRSTNAME:
				current.setFirstName(s);
				break;
			case ID:
				current.setId(s);
				break;
			case SECONDNAME:
				current.setSecondName(s);
				break;
			case BONUS:
				((Manager) current).setBonus(Integer.parseInt(s));

				break;
			case NUMBEROFTOOLS:
				((Operator) current).setNumberOfTools(Integer.parseInt(s));
				break;
			default:
				throw new EnumConstantNotPresentException(
						currentEnum.getDeclaringClass(), currentEnum.name());
			}
		}
		currentEnum = null;
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		if ("manager".equals(localName)) {
			employee.add(current);

		} else if ("operator".equals(localName)) {
			employee.add(current);
		}
	}
}
