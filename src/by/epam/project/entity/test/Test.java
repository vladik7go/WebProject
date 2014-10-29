package by.epam.project.entity.test;

import java.util.Set;

import by.epam.project.entity.AbstractEntity;

public class Test extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;
	private int id;
	private String title;
	private String description;
	private Set<Question> questions;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(Set<Question> questions) {
		this.questions = questions;
	}

}
