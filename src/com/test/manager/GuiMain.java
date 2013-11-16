package com.test.manager;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class GuiMain extends JFrame {
	private static final long serialVersionUID = 1L;
	private ObjectStore objectStore = new ObjectStore();
	private Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

	private int currentQuestion = 0;

	JTextArea textArea = new JTextArea();
	JButton add = new JButton("Add");
	JButton edit = new JButton("Edit");
	JButton delete = new JButton("Delete");
	JButton next = new JButton("Next");
	JButton prev = new JButton("Previous");
	GuiMain guimain;
	GuiMain() {
		guimain=this;
		this.setLayout(null);
		try {
			objectStore.readFromStore();
			System.out.println(objectStore.getTotalQuestions());
		} catch (Exception e) {
			e.printStackTrace();
		}

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Test Manager");

		// when the window is close app should be tero4ated
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createUI();
	}

	void createUI() {

		JScrollPane scrollPane = new JScrollPane(textArea);
		scrollPane
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		textArea.setEditable(false);

		add.setLocation(10, 10);
		edit.setLocation(110, 10);
		delete.setLocation(210, 10);
		prev.setLocation(dim.width / 2 - 100, 10);
		next.setLocation(dim.width / 2, 10);

		// initialising the text area
		setQuestionDisplay(objectStore.getTotalQuestions() > 0 ? currentQuestion : -1); 

		textArea.setLocation(10, 50);

		add.setSize(100, 30);
		edit.setSize(100, 30);
		delete.setSize(100, 30);
		prev.setSize(100, 30);
		next.setSize(100, 30);

		addActionListeners();
		
		textArea.setSize(dim.width - 90, dim.height - 110);
		
		this.add(add);
		this.add(edit);
		this.add(delete);
		this.add(prev);
		this.add(next);

		this.add(textArea);
	}

	void setQuestionDisplay(int index) {
		if (objectStore.getTotalQuestions() == 0)
			textArea.setText("NO QUESTIONS ADDED.");
		else
			textArea.setText(objectStore.getList().get(index).toString());
	}
	
	void addActionListeners() {
		add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GuiAdd(280, 300, objectStore, -1,guimain).setVisible(true);
				
			}
		});

		next.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (currentQuestion <= objectStore.getTotalQuestions() - 1) {
					currentQuestion++;
					if(currentQuestion == objectStore.getTotalQuestions()) {
						currentQuestion--;
					}
					setQuestionDisplay(currentQuestion);
				}
			}
		});
		
		prev.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if (currentQuestion > 0) {
					setQuestionDisplay(--currentQuestion);
				}
			}
		});
		
		edit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new GuiAdd(280, 300, objectStore, currentQuestion,guimain).setVisible(true);
			}
		});
		
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
					objectStore.deleteQuestion(currentQuestion);
					objectStore.storeData();
					
					textArea.setText("QUESTION DELETED.");
					currentQuestion--;
					if(objectStore.getTotalQuestions() != 0) {
						if (currentQuestion <= objectStore.getTotalQuestions() - 1) {
							currentQuestion++;
							if(currentQuestion == objectStore.getTotalQuestions()) {
								currentQuestion--;
							}
							setQuestionDisplay(currentQuestion);
						}
				}
			}
		});
	}
}
