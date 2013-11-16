package com.test.manager;

import java.io.Serializable;

public class Question implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String question,o1,o2,o3,o4;
	private int correctAns,marks;
	
	Question() {}
	
	public String getQuestion() {
		return question;
	}
	public void setQuestion(String question) {
		this.question = question;
	}
	public String getO1() {
		return o1;
	}
	public void setO1(String o1) {
		this.o1 = o1;
	}
	public String getO2() {
		return o2;
	}
	public void setO2(String o2) {
		this.o2 = o2;
	}
	public String getO3() {
		return o3;
	}
	public void setO3(String o3) {
		this.o3 = o3;
	}
	public String getO4() {
		return o4;
	}
	public void setO4(String o4) {
		this.o4 = o4;
	}
	public int getCorrectAns() {
		return correctAns;
	}
	public void setCorrectAns(int correctAns) {
		this.correctAns = correctAns;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	public String toString() {
		return "Question: " + question + "\n"
		+ "Option 1: " + o1 + "\n"
		+ "Option 2: " + o2 + "\n"
		+ "Option 3: " + o3 + "\n"
		+ "Option 4: " + o4 + "\n"
		+ "Correct Answer: " + correctAns + "\n"
		+ "Marks: " + marks + "\n";
	}
}
