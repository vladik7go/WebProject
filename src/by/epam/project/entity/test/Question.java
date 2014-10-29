package by.epam.project.entity.test;

import java.util.List;

import by.epam.project.entity.AbstractEntity;

public class Question extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int testType;
	private String content;
	private List<Answer> answers;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTestType() {
		return testType;
	}

	public void setTestType(int testType) {
		this.testType = testType;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

}
