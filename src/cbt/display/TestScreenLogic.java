package cbt.display;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import cbt.business.logic.ManipulateQuestions;

public class TestScreenLogic implements Runnable {
	TestScreen testscreen;
	ManipulateQuestions mqQuestionPaper;
	ArrayList<StoreStateOfClass> stores = new ArrayList<StoreStateOfClass>();
	long start = System.currentTimeMillis() + (10 * 60 * 1000 + 1 * 1000);

	public TestScreenLogic(TestScreen temp, ManipulateQuestions mq) {
		testscreen = temp;
		mqQuestionPaper = mq;
	}

	

	public void createThreadAsync() {
		Thread t = new Thread(this);
		t.start();
	}

	public void run() {
		int second, minute, hour;
		while (true) {
			long seconds = ((start - System.currentTimeMillis()) / 1000) % 60;
			long minutes = (start - System.currentTimeMillis()) / (60 * 1000);
			long hours = (start - System.currentTimeMillis())
					/ (60 * 60 * 1000);
			java.text.SimpleDateFormat simpledayformat = new java.text.SimpleDateFormat(
					"hh:mm:ss");
			String time = simpledayformat.format(new java.util.Date());
			testscreen.getJtime().setText("Current System Time: " + time);
			testscreen.getJtimer().setText(
					"Time Left :  " + String.format("%02d", hours) + ":"
							+ String.format("%02d", minutes) + ":"
							+ String.format("%02d", seconds));
			if (hours == 0 && minutes == 0 && seconds == 0) {
				testscreen.getJbSubmit().doClick();
				JOptionPane.showMessageDialog(null, "time's up");
				System.exit(0);
			}
		}
	}

	public void selected() {
		if (testscreen.getJrbOption1().isSelected() == true) {
			testscreen.setRadioState(1);
		} else if (testscreen.getJrbOption2().isSelected() == true) {
			testscreen.setRadioState(2);
		} else if (testscreen.getJrbOption3().isSelected() == true) {
			testscreen.setRadioState(3);
		} else if (testscreen.getJrbOption4().isSelected() == true) {
			testscreen.setRadioState(4);
		} else
			testscreen.setRadioState(0);
		testscreen.getBgOptions().clearSelection();
	}

	public void create_object_of_store_state_class() {
		int i;
		StoreStateOfClass storeObject = new StoreStateOfClass();
		selected();
		storeObject.qno = mqQuestionPaper.getiCurrentQuestion();
		storeObject.correctAnswer = mqQuestionPaper.getStrCorrectAnswer();
		storeObject.marks = mqQuestionPaper.getiQMarks();
		storeObject.radioState = testscreen.getRadioState();
		// JOptionPane.showMessageDialog(null,storeObject.qno+" "+storeObject.correctAnswer+" "+storeObject.marks+" "+s.radioState);
		for (i = 0; i < stores.size(); i++)
			if (stores.get(i).qno == storeObject.qno) {
				stores.remove(i);
				break;
			}
		stores.add(storeObject);
	}
	
	public void remember_state() {
		int i;
		for (i = 0; i < stores.size(); i++) {
			if (stores.get(i).qno == mqQuestionPaper.getiCurrentQuestion()) {
				if (stores.get(i).radioState == 1) {
					testscreen.getJrbOption1().setSelected(true);
				} else if (stores.get(i).radioState == 2) {
					testscreen.getJrbOption2().setSelected(true);
				} else if (stores.get(i).radioState == 3) {
					testscreen.getJrbOption3().setSelected(true);
				} else if (stores.get(i).radioState == 4) {
					testscreen.getJrbOption4().setSelected(true);
				}
			}
		}
	}
	
	public void setQuestion(String strQuestion, String strOption1,
			String strOption2, String strOption3, String strOption4, int iQno) {

		testscreen.getJlQuestion().setText("Question " + (iQno + 1) + ":");
		testscreen.getTaQuestion().setText(strQuestion);
		testscreen.getTaOption1().setText(strOption1);
		testscreen.getTaOption2().setText(strOption2);
		testscreen.getTaOption3().setText(strOption3);
		testscreen.getTaOption4().setText(strOption4);
	}

	public void executeActionPerformed(ActionEvent arg0) {
		if (arg0.getSource() instanceof JButton) {
			create_object_of_store_state_class();
			// JOptionPane.showMessageDialog(null,"Size is "+stores.size());
			JButton jbutton = (JButton) arg0.getSource();
			if (jbutton.getText().equalsIgnoreCase(testscreen.getJbNext().getText())) {
				int i = mqQuestionPaper.setNextQuestion();
				if (i != -1) {
					setQuestion(mqQuestionPaper.getStrQuestion(),
							mqQuestionPaper.getStrOption1(),
							mqQuestionPaper.getStrOption2(),
							mqQuestionPaper.getStrOption3(),
							mqQuestionPaper.getStrOption4(),
							mqQuestionPaper.getiCurrentQuestion());
					// JOptionPane.showMessageDialog(null,"value of i is "+(i+1));
				} else if (i == -1) {
					// JOptionPane.showMessageDialog(null,"Hi how are u "+i);
					mqQuestionPaper.setiCurrentQuestionIndex();
					setQuestion(mqQuestionPaper.getStrQuestion(),
							mqQuestionPaper.getStrOption1(),
							mqQuestionPaper.getStrOption2(),
							mqQuestionPaper.getStrOption3(),
							mqQuestionPaper.getStrOption4(),
							mqQuestionPaper.getiCurrentQuestion());
				}
				remember_state();
			} else if (jbutton.getText().equalsIgnoreCase(testscreen.getJbPrevious().getText())) {
				int i = mqQuestionPaper.setPreviousQuestion();
				if (i != -1) {
					setQuestion(mqQuestionPaper.getStrQuestion(),
							mqQuestionPaper.getStrOption1(),
							mqQuestionPaper.getStrOption2(),
							mqQuestionPaper.getStrOption3(),
							mqQuestionPaper.getStrOption4(),
							mqQuestionPaper.getiCurrentQuestion());
				} else if (i == -1) {
					mqQuestionPaper.setiPreviousQuestionIndex();
					setQuestion(mqQuestionPaper.getStrQuestion(),
							mqQuestionPaper.getStrOption1(),
							mqQuestionPaper.getStrOption2(),
							mqQuestionPaper.getStrOption3(),
							mqQuestionPaper.getStrOption4(),
							mqQuestionPaper.getiCurrentQuestion());
				}
				remember_state();
			} else if (jbutton.getText().equalsIgnoreCase(testscreen.getJbSubmit().getText())) {
				int i, k = 0;
				for (i = 0; i < stores.size(); i++) {
					if (stores.get(i).radioState == stores.get(i).correctAnswer)
						k += stores.get(i).marks;
				}
				remember_state();				
				Object[] options = { "Click to submit", "Click to go Back" };
				int dialogbutton = JOptionPane.showOptionDialog(null,
						"Would u like to submit", "Submitting Answer",
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, options[0]);
				if (dialogbutton == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, "Your Final Score Is "
							+ k);
					System.exit(0);
				}
			}
			else if(jbutton.getText().equalsIgnoreCase(testscreen.getJexit().getText())){
				System.exit(0);
			}

			else if (jbutton.getText().equalsIgnoreCase(testscreen.getDeSelect().getText())) {
				testscreen.getBgOptions().clearSelection();
			} 
			else {
				for(int i=0;i<testscreen.getJbQnoX().length;i++)
					if(jbutton.getText().equalsIgnoreCase(testscreen.getJbQnoX()[i].getText()))
					{
						mqQuestionPaper.setQuestionNo(i);
						setQuestion(mqQuestionPaper.getStrQuestion(),
								mqQuestionPaper.getStrOption1(),
								mqQuestionPaper.getStrOption2(),
								mqQuestionPaper.getStrOption3(),
								mqQuestionPaper.getStrOption4(),
								mqQuestionPaper.getiCurrentQuestion());
						remember_state();
					}
			}
			
		}
	}

}

