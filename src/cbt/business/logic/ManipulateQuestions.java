package cbt.business.logic;

import com.test.manager.ObjectStore;

public class ManipulateQuestions {

	private ObjectStore obsRead;
	private int iQuestionsMax;
	private int iCurrentQuestion;
	private int iQMarks;
	private int iTotalMarks;
	private int strCorrectAnswer;
	private String strQuestion;
	private String strOption1;
	private String strOption2;
	private String strOption3;
	private String strOption4;

	public ManipulateQuestions() {
		obsRead = new ObjectStore();
		strQuestion = null;
		strOption1 = null;
		strOption2 = null;
		strOption3 = null;
		strOption4 = null;
		strCorrectAnswer = 0;
		iQMarks = 0;
		iTotalMarks = 0;

		this.iQuestionsMax = obsRead.readFromStore();
		if (this.iQuestionsMax != 0) {
			this.iCurrentQuestion = 0;
			this.strQuestion = obsRead.getList().get(iCurrentQuestion)
					.getQuestion();
			this.strOption1 = obsRead.getList().get(iCurrentQuestion).getO1();
			this.strOption2 = obsRead.getList().get(iCurrentQuestion).getO2();
			this.strOption3 = obsRead.getList().get(iCurrentQuestion).getO3();
			this.strOption4 = obsRead.getList().get(iCurrentQuestion).getO4();
			strCorrectAnswer = obsRead.getList().get(iCurrentQuestion)
					.getCorrectAns();
			this.iQMarks = obsRead.getList().get(iCurrentQuestion).getMarks();
		}
	}

	/*
	 * sets the data members for next question ;returns the current question
	 * number; else returns -1 if reaches end of the list
	 */
	public int setNextQuestion() {
		iCurrentQuestion++;
		if (iCurrentQuestion < iQuestionsMax) {
			strQuestion = obsRead.getList().get(iCurrentQuestion).getQuestion();
			strOption1 = obsRead.getList().get(iCurrentQuestion).getO1();
			strOption2 = obsRead.getList().get(iCurrentQuestion).getO2();
			strOption3 = obsRead.getList().get(iCurrentQuestion).getO3();
			strOption4 = obsRead.getList().get(iCurrentQuestion).getO4();
			strCorrectAnswer = obsRead.getList().get(iCurrentQuestion)
					.getCorrectAns();
			iQMarks = obsRead.getList().get(iCurrentQuestion).getMarks();
			return iCurrentQuestion;
		} else
			return -1;
	}

	public int setPreviousQuestion() {
		iCurrentQuestion--;
		if (iCurrentQuestion != -1) {
			strQuestion = obsRead.getList().get(iCurrentQuestion).getQuestion();
			strOption1 = obsRead.getList().get(iCurrentQuestion).getO1();
			strOption2 = obsRead.getList().get(iCurrentQuestion).getO2();
			strOption3 = obsRead.getList().get(iCurrentQuestion).getO3();
			strOption4 = obsRead.getList().get(iCurrentQuestion).getO4();
			strCorrectAnswer = obsRead.getList().get(iCurrentQuestion)
					.getCorrectAns();
			iQMarks = obsRead.getList().get(iCurrentQuestion).getMarks();
			return iCurrentQuestion;
		} else
			return -1;
	}

	public void setiCurrentQuestionIndex() {
		iCurrentQuestion = -1;
		setNextQuestion();
	}
	
	public void setQuestionNo(int i){
		iCurrentQuestion=i;
		strQuestion = obsRead.getList().get(iCurrentQuestion).getQuestion();
		strOption1 = obsRead.getList().get(iCurrentQuestion).getO1();
		strOption2 = obsRead.getList().get(iCurrentQuestion).getO2();
		strOption3 = obsRead.getList().get(iCurrentQuestion).getO3();
		strOption4 = obsRead.getList().get(iCurrentQuestion).getO4();
		strCorrectAnswer = obsRead.getList().get(iCurrentQuestion)
				.getCorrectAns();
		iQMarks = obsRead.getList().get(iCurrentQuestion).getMarks();
		
	}

	// sets the previous question to max -1 so that u can go back
	public void setiPreviousQuestionIndex() {
		iCurrentQuestion = iQuestionsMax;
		setPreviousQuestion();
	}

	
	public int getiTotalMarks() {
		return iTotalMarks;
	}

	public void setiTotalMarks(int iTotalMarks) {

		this.iTotalMarks = iTotalMarks;
	}

	public int getiQuestionsMax() {
		return iQuestionsMax;
	}

	public int getiCurrentQuestion() {
		return iCurrentQuestion;
	}

	public int getiQMarks() {
		return iQMarks;
	}

	public int getStrCorrectAnswer() {
		return strCorrectAnswer;
	}

	public String getStrQuestion() {
		return strQuestion;
	}

	public String getStrOption1() {
		return strOption1;
	}

	public String getStrOption2() {
		return strOption2;
	}

	public String getStrOption3() {
		return strOption3;
	}

	public String getStrOption4() {
		return strOption4;
	}

	public void setiCurrentQuestion() {
		iCurrentQuestion++;
	}
}
