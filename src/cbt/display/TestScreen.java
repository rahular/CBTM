package cbt.display;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import cbt.business.logic.ManipulateQuestions;

public class TestScreen extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JButton[] jbQnoX;
	int radioState;
	JPanel contentPane;
	JPanel contactListPanel;
	JLabel jtime, jtimer;

	/* Text Areas */
	private JTextArea taQuestion;
	private JTextArea taOption1;
	private JTextArea taOption2;
	private JTextArea taOption3;
	private JTextArea taOption4;
	// private JTextArea taQuestionNumbers;

	/* Scroll Panes */
	private JScrollPane jsQuestion;
	private JScrollPane jsOption1;
	private JScrollPane jsOption2;
	private JScrollPane jsOption3;
	private JScrollPane jsOption4;
	private JScrollPane jsInternal;

	/* Labels */
	private JLabel jlQuestion;
	private JLabel jlOption1;
	private JLabel jlOption2;
	private JLabel jlOption3;
	private JLabel jlOption4;
	private JLabel jlHeader;

	/* Buttons */
	private JButton jbNext;
	private JButton jbPrevious;
	private JButton jbSubmit;
	private JButton deSelect;
	private JButton jexit;

	/* Radio Buttons for options */
	private JRadioButton jrbOption1;
	private JRadioButton jrbOption2;
	private JRadioButton jrbOption3;
	private JRadioButton jrbOption4;

	/* Internal Frame */
	private JInternalFrame jifQuestionNumbers;

	/* Radio Button Group */
	private ButtonGroup bgOptions;

	private ManipulateQuestions mqQuestionPaper;
	private TestScreenLogic testscreenlogic;

	String question, option1, option2, option3, option4;

	public TestScreen() {
		this(1000, 700);
	}

	public TestScreen(int x, int y) {
		mqQuestionPaper = new ManipulateQuestions();
		testscreenlogic = new TestScreenLogic(this, mqQuestionPaper);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		this.setSize(x, y);
		this.setTitle("Computer Based Test Application : Beta");
	    setExtendedState(JFrame.MAXIMIZED_BOTH);
		createUI();
		internalFrame();
		testscreenlogic.setQuestion(mqQuestionPaper.getStrQuestion(),
				mqQuestionPaper.getStrOption1(),
				mqQuestionPaper.getStrOption2(),
				mqQuestionPaper.getStrOption3(),
				mqQuestionPaper.getStrOption4(),
				mqQuestionPaper.getiCurrentQuestion());
		testscreenlogic.createThreadAsync();

	}

	private void createUI() {
		this.setLayout(null);
		createObjects();
		setComponents();
		addComponents();
		addListeners();
	}

	private void createObjects() {
		jtime = new JLabel();
		jtimer = new JLabel();
		deSelect = new JButton(" Deselect");

		taQuestion = new JTextArea();
		taOption1 = new JTextArea();
		taOption2 = new JTextArea();
		taOption3 = new JTextArea();
		taOption4 = new JTextArea();

		jsQuestion = new JScrollPane(taQuestion);
		jsOption1 = new JScrollPane(taOption1);
		jsOption2 = new JScrollPane(taOption2);
		jsOption3 = new JScrollPane(taOption3);
		jsOption4 = new JScrollPane(taOption4);

		jlQuestion = new JLabel("Question: ");
		jlOption1 = new JLabel("1: ");
		jlOption2 = new JLabel("2: ");
		jlOption3 = new JLabel("3: ");
		jlOption4 = new JLabel("4: ");
		jlHeader = new JLabel("Computer Based Test");

		jbNext = new JButton("Next");
		jbPrevious = new JButton("Previous");
		jbSubmit = new JButton("Submit");
		jexit=new JButton("Exit");

		jrbOption1 = new JRadioButton("", false);
		jrbOption2 = new JRadioButton("", false);
		jrbOption3 = new JRadioButton("", false);
		jrbOption4 = new JRadioButton("", false);

		bgOptions = new ButtonGroup();

		jifQuestionNumbers = new JInternalFrame();
	}

	private void setComponents() {
		jtime.setLocation(700, 30);
		jtime.setSize(450, 40);
		jtimer.setLocation(700, 70);
		jtimer.setSize(450, 40);

		jlHeader.setLocation(180, 10);
		jlHeader.setSize(400, 30);

		jlQuestion.setLocation(10, 50);
		jlQuestion.setSize(100, 50);

		jlOption1.setLocation(30, 140);
		jlOption1.setSize(100, 30);

		jlOption2.setLocation(30, 200);
		jlOption2.setSize(100, 30);

		jlOption3.setLocation(30, 260);
		jlOption3.setSize(100, 30);

		jlOption4.setLocation(30, 320);
		jlOption4.setSize(100, 30);

		taQuestion.setLocation(100, 50);
		taQuestion.setSize(350, 50);
		taQuestion.setEditable(false);
		jsQuestion.setBounds(100, 50, 350, 50);

		taOption1.setLocation(100, 130);
		taOption1.setSize(350, 40);
		taOption1.setEditable(false);
		jsOption1.setBounds(100, 130, 350, 40);

		taOption2.setLocation(100, 190);
		taOption2.setSize(350, 40);
		taOption2.setEditable(false);
		jsOption2.setBounds(100, 190, 350, 40);

		taOption3.setLocation(100, 250);
		taOption3.setSize(350, 40);
		taOption3.setEditable(false);
		jsOption3.setBounds(100, 250, 350, 40);

		taOption4.setLocation(100, 310);
		taOption4.setSize(350, 40);
		taOption4.setEditable(false);
		jsOption4.setBounds(100, 310, 350, 40);

		jrbOption1.setLocation(50, 140);
		jrbOption1.setSize(30, 30);

		jrbOption2.setLocation(50, 200);
		jrbOption2.setSize(30, 30);

		jrbOption3.setLocation(50, 260);
		jrbOption3.setSize(30, 30);

		jrbOption4.setLocation(50, 320);
		jrbOption4.setSize(30, 30);

		jbPrevious.setLocation(100, 380);
		jbPrevious.setSize(100, 30);

		jbNext.setLocation(240, 380);
		jbNext.setSize(100, 30);

		jbSubmit.setLocation(100, 420);
		jbSubmit.setSize(100, 30);
		
		jexit.setLocation(160,460);
		jexit.setSize(120,30);

		deSelect.setLocation(240, 420);
		deSelect.setSize(100, 30);

		jifQuestionNumbers.setLocation(480, 50);
		jifQuestionNumbers.setSize(200, 400);
		jifQuestionNumbers.setTitle("Qno");
		jifQuestionNumbers.setBounds(480, 50, 200, 400);
		jifQuestionNumbers.setResizable(true);
		try {
			jifQuestionNumbers.setClosable(true);
			jifQuestionNumbers.setMaximizable(true);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jifQuestionNumbers.setVisible(true);

	}

	private void addComponents() {
		this.add(jifQuestionNumbers);
		bgOptions.add(jrbOption1);
		bgOptions.add(jrbOption2);
		bgOptions.add(jrbOption3);
		bgOptions.add(jrbOption4);
		this.add(jtime);
		this.add(jtimer);
		this.add(deSelect);
		this.add(jlQuestion);
		this.add(jlOption1);
		this.add(jlOption2);
		this.add(jlOption3);
		this.add(jlOption4);
		this.add(jsQuestion);
		this.add(jsOption1);
		this.add(jsOption2);
		this.add(jsOption3);
		this.add(jsOption4);
		this.add(jrbOption1);
		this.add(jrbOption2);
		this.add(jrbOption3);
		this.add(jrbOption4);
		this.add(jbPrevious);
		this.add(jbNext);
		this.add(jbSubmit);
		this.add(jlHeader);
		this.add(jexit);
		

	}

	public void addListeners() {
		jbNext.addActionListener(this);
		jbPrevious.addActionListener(this);
		jbSubmit.addActionListener(this);
		jexit.addActionListener(this);
		jrbOption1.addActionListener(this);
		jrbOption2.addActionListener(this);
		jrbOption3.addActionListener(this);
		jrbOption4.addActionListener(this);
		deSelect.addActionListener(this);
	}

	void internalFrame() {
		contentPane = (JPanel) jifQuestionNumbers.getContentPane();
		contentPane.setLayout(new BorderLayout());

		// Set layout for contactListPane
		contactListPanel = new JPanel();
		contactListPanel.setLayout(new GridLayout(25, 1));
		contactListPanel.setMinimumSize(new Dimension(100, 400));
		contactListPanel.setPreferredSize(new Dimension(100, 400));
		contactListPanel.setMaximumSize(new Dimension(100, 400));
		addQno();
		jsInternal = new JScrollPane(contactListPanel);
		contentPane.add(jsInternal, BorderLayout.CENTER);
	}

	private void addQno() {
		jbQnoX = new JButton[mqQuestionPaper.getiQuestionsMax()];
		int i;

		for (i = 0; i < jbQnoX.length; i++) {
			jbQnoX[i] = new JButton();
			jbQnoX[i].setText("Q " + (i + 1));
			contactListPanel.add(jbQnoX[i]);
			jbQnoX[i].addActionListener(this);
		}
	}

	public void actionPerformed(ActionEvent arg0) {
		testscreenlogic.executeActionPerformed(arg0);
	}

	public JButton[] getJbQnoX() {
		return jbQnoX;
	}

	public void setJbQnoX(JButton[] jbQnoX) {
		this.jbQnoX = jbQnoX;
	}

	public int getRadioState() {
		return radioState;
	}

	public void setRadioState(int radioState) {
		this.radioState = radioState;
	}

	public JLabel getJtime() {
		return jtime;
	}

	public void setJtime(JLabel jtime) {
		this.jtime = jtime;
	}

	public JLabel getJtimer() {
		return jtimer;
	}

	public void setJtimer(JLabel jtimer) {
		this.jtimer = jtimer;
	}

	public JTextArea getTaQuestion() {
		return taQuestion;
	}

	public void setTaQuestion(JTextArea taQuestion) {
		this.taQuestion = taQuestion;
	}

	public JButton getJexit() {
		return jexit;
	}

	public void setJexit(JButton jexit) {
		this.jexit = jexit;
	}

	public JTextArea getTaOption1() {
		return taOption1;
	}

	public void setTaOption1(JTextArea taOption1) {
		this.taOption1 = taOption1;
	}

	public JTextArea getTaOption2() {
		return taOption2;
	}

	public void setTaOption2(JTextArea taOption2) {
		this.taOption2 = taOption2;
	}

	public JTextArea getTaOption3() {
		return taOption3;
	}

	public void setTaOption3(JTextArea taOption3) {
		this.taOption3 = taOption3;
	}

	public JTextArea getTaOption4() {
		return taOption4;
	}

	public void setTaOption4(JTextArea taOption4) {
		this.taOption4 = taOption4;
	}

	public JScrollPane getJsQuestion() {
		return jsQuestion;
	}

	public void setJsQuestion(JScrollPane jsQuestion) {
		this.jsQuestion = jsQuestion;
	}

	public JScrollPane getJsOption1() {
		return jsOption1;
	}

	public void setJsOption1(JScrollPane jsOption1) {
		this.jsOption1 = jsOption1;
	}

	public JScrollPane getJsOption2() {
		return jsOption2;
	}

	public void setJsOption2(JScrollPane jsOption2) {
		this.jsOption2 = jsOption2;
	}

	public JScrollPane getJsOption3() {
		return jsOption3;
	}

	public void setJsOption3(JScrollPane jsOption3) {
		this.jsOption3 = jsOption3;
	}

	public JScrollPane getJsOption4() {
		return jsOption4;
	}

	public void setJsOption4(JScrollPane jsOption4) {
		this.jsOption4 = jsOption4;
	}

	public JScrollPane getJsInternal() {
		return jsInternal;
	}

	public void setJsInternal(JScrollPane jsInternal) {
		this.jsInternal = jsInternal;
	}

	public JLabel getJlQuestion() {
		return jlQuestion;
	}

	public void setJlQuestion(JLabel jlQuestion) {
		this.jlQuestion = jlQuestion;
	}

	public JLabel getJlOption1() {
		return jlOption1;
	}

	public void setJlOption1(JLabel jlOption1) {
		this.jlOption1 = jlOption1;
	}

	public JLabel getJlOption2() {
		return jlOption2;
	}

	public void setJlOption2(JLabel jlOption2) {
		this.jlOption2 = jlOption2;
	}

	public JLabel getJlOption3() {
		return jlOption3;
	}

	public void setJlOption3(JLabel jlOption3) {
		this.jlOption3 = jlOption3;
	}

	public JLabel getJlOption4() {
		return jlOption4;
	}

	public void setJlOption4(JLabel jlOption4) {
		this.jlOption4 = jlOption4;
	}

	public JLabel getJlHeader() {
		return jlHeader;
	}

	public void setJlHeader(JLabel jlHeader) {
		this.jlHeader = jlHeader;
	}

	public JButton getJbNext() {
		return jbNext;
	}

	public void setJbNext(JButton jbNext) {
		this.jbNext = jbNext;
	}

	public JButton getJbPrevious() {
		return jbPrevious;
	}

	public void setJbPrevious(JButton jbPrevious) {
		this.jbPrevious = jbPrevious;
	}

	public JButton getJbSubmit() {
		return jbSubmit;
	}

	public void setJbSubmit(JButton jbSubmit) {
		this.jbSubmit = jbSubmit;
	}

	public JButton getDeSelect() {
		return deSelect;
	}

	public void setDeSelect(JButton deSelect) {
		this.deSelect = deSelect;
	}

	public JRadioButton getJrbOption1() {
		return jrbOption1;
	}

	public void setJrbOption1(JRadioButton jrbOption1) {
		this.jrbOption1 = jrbOption1;
	}

	public JRadioButton getJrbOption2() {
		return jrbOption2;
	}

	public void setJrbOption2(JRadioButton jrbOption2) {
		this.jrbOption2 = jrbOption2;
	}

	public JRadioButton getJrbOption3() {
		return jrbOption3;
	}

	public void setJrbOption3(JRadioButton jrbOption3) {
		this.jrbOption3 = jrbOption3;
	}

	public JRadioButton getJrbOption4() {
		return jrbOption4;
	}

	public void setJrbOption4(JRadioButton jrbOption4) {
		this.jrbOption4 = jrbOption4;
	}

	public JInternalFrame getJifQuestionNumbers() {
		return jifQuestionNumbers;
	}

	public void setJifQuestionNumbers(JInternalFrame jifQuestionNumbers) {
		this.jifQuestionNumbers = jifQuestionNumbers;
	}

	public ButtonGroup getBgOptions() {
		return bgOptions;
	}

	public void setBgOptions(ButtonGroup bgOptions) {
		this.bgOptions = bgOptions;
	}

	public ManipulateQuestions getMqQuestionPaper() {
		return mqQuestionPaper;
	}

	public void setMqQuestionPaper(ManipulateQuestions mqQuestionPaper) {
		this.mqQuestionPaper = mqQuestionPaper;
	}

	public TestScreenLogic getTestscreenlogic() {
		return testscreenlogic;
	}

	public void setTestscreenlogic(TestScreenLogic testscreenlogic) {
		this.testscreenlogic = testscreenlogic;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

}

