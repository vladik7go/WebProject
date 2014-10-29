package by.epam.project.entity.test;

import by.epam.project.entity.AbstractEntity;

public class Answer extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private int questionType;
	private String answer;
	private int value;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuestionType() {
		return questionType;
	}

	public void setQuestionType(int questionType) {
		this.questionType = questionType;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

}
