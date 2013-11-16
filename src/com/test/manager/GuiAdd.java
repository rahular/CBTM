package com.test.manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

class GuiAdd extends JFrame {

	private static final long serialVersionUID = 1L;
	private int operation;

	JTextField question = new JTextField();
	JTextField o1 = new JTextField();
	JTextField o2 = new JTextField();
	JTextField o3 = new JTextField();
	JTextField o4 = new JTextField();
	JTextField correctAns = new JTextField();
	JTextField marks = new JTextField();

	private ObjectStore objectStore;
	GuiMain guimain;

	// Constructor
	GuiAdd(int width, int height, ObjectStore objectStore, int operation,
			GuiMain gm) {
		this.objectStore = objectStore;
		this.operation = operation;
		guimain = gm;

		// Get the size of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		// Detero4e the new location of the window
		int x = (dim.width - width) / 2;
		int y = (dim.height - height) / 2;

		// Move the window
		setLocation(x, y);
		setSize(width, height);
		setTitle("Test Manager");

		// when the window is close app should be tero4ated
		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI();
	}

	void createUI() {
		this.setLayout(null);

		JLabel questionLabel = new JLabel("Question");
		JLabel o1Label = new JLabel("Option 1");
		JLabel o2Label = new JLabel("Option 2");
		JLabel o3Label = new JLabel("Option 3");
		JLabel o4Label = new JLabel("Option 4");
		JLabel correctAnsLabel = new JLabel("Correct Answer");
		JLabel marksLabel = new JLabel("Marks");

		JButton addQuestion = new JButton();
		JButton exit = new JButton("Exit");
		if (operation == -1) {
			addQuestion.setText("Add Question");
		} else {
			addQuestion.setText("Modify Question");
		}

		int initX = 10, initY = 10;

		questionLabel.setLocation(initX, initY); // x and y
		question.setLocation(initX + 100, initY);
		o1Label.setLocation(initX, initY + 30);
		o1.setLocation(initX + 100, initY + 30);
		o2Label.setLocation(initX, initY + 60);
		o2.setLocation(initX + 100, initY + 60);
		o3Label.setLocation(initX, initY + 90);
		o3.setLocation(initX + 100, initY + 90);
		o4Label.setLocation(initX, initY + 120);
		o4.setLocation(initX + 100, initY + 120);
		correctAnsLabel.setLocation(initX, initY + 150);
		correctAns.setLocation(initX + 100, initY + 150);
		marksLabel.setLocation(initX, initY + 180);
		marks.setLocation(initX + 100, initY + 180);

		addQuestion.setLocation(initX + 10, initY + 220);
		exit.setLocation(initX + 150, initY + 220);

		questionLabel.setSize(100, 30); // width and height
		question.setSize(150, 30);
		o1Label.setSize(100, 30);
		o1.setSize(150, 30);
		o2Label.setSize(100, 30);
		o2.setSize(150, 30);
		o3Label.setSize(100, 30);
		o3.setSize(150, 30);
		o4Label.setSize(100, 30);
		o4.setSize(150, 30);
		correctAnsLabel.setSize(100, 30);
		correctAns.setSize(150, 30);
		marksLabel.setSize(100, 30);
		marks.setSize(150, 30);

		addQuestion.setSize(130, 30);
		exit.setSize(100, 30);

		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});

		addQuestion.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String questionValue = question.getText(), o1Value = o1
						.getText(), o2Value = o2.getText(), o3Value = o3
						.getText(), o4Value = o4.getText();
				int marksValue = 0, correctValue = 0;

				if (questionValue.isEmpty() || o1Value.isEmpty()
						|| o2Value.isEmpty() || o3Value.isEmpty()
						|| o4Value.isEmpty() || marks.getText().isEmpty()
						|| correctAns.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"No fields can be empty.");
					return;
				}
				
				try {
					marksValue = Integer.parseInt(marks.getText());
					correctValue = Integer.parseInt(correctAns.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null,
							"Marks and Correct Answer take only numbers.");
					return;
				}

				if (correctValue < 1 || correctValue > 4) {
					JOptionPane.showMessageDialog(null,
							"Correct Answer should be between 1 and 4.");
					return;
				}

				Question q = new Question();
				q.setCorrectAns(correctValue);
				q.setMarks(marksValue);
				q.setO1(o1Value);
				q.setO2(o2Value);
				q.setO3(o3Value);
				q.setO4(o4Value);
				q.setQuestion(questionValue);

				if (operation == -1) {
					objectStore.addQuestion(q);
					JOptionPane.showMessageDialog(null, "Question Added.");
					guimain.textArea.setText("Question : " + question.getText()
							+ "\n" + "Option 1: " + o1.getText() + "\n"
							+ "Option 2 : " + o2.getText() + "\n"
							+ "Option3 : " + o3.getText() + "\n" + "Option4 : "
							+ o4.getText() + "\n" + "Correct Answer : "
							+ correctAns.getText() + "\n" + "Marks: "
							+ marks.getText() + "\n");
					question.setText("");
					o1.setText("");
					o2.setText("");
					o3.setText("");
					o4.setText("");
					correctAns.setText("");
					marks.setText("");
				} else {
					objectStore.modifyQuestion(operation, q);
					JOptionPane
							.showMessageDialog(null,
									"Question Modified. Modification reflects on screen only after refresh.");
				}
			}
		});

		// Operation = -1 -> add question
		// Operation >= 0 -> modify question

		if (operation != -1) {
			question.setText(objectStore.getList().get(operation).getQuestion());
			o1.setText(objectStore.getList().get(operation).getO1());
			o2.setText(objectStore.getList().get(operation).getO2());
			o3.setText(objectStore.getList().get(operation).getO3());
			o4.setText(objectStore.getList().get(operation).getO4());
			correctAns.setText(objectStore.getList().get(operation)
					.getCorrectAns()
					+ "");
			marks.setText(objectStore.getList().get(operation).getMarks() + "");
		}

		this.add(questionLabel);
		this.add(question);
		this.add(o1Label);
		this.add(o1);
		this.add(o2Label);
		this.add(o2);
		this.add(o3Label);
		this.add(o3);
		this.add(o4Label);
		this.add(o4);
		this.add(correctAnsLabel);
		this.add(correctAns);
		this.add(marksLabel);
		this.add(marks);

		this.add(addQuestion);
		this.add(exit);
	}
}
