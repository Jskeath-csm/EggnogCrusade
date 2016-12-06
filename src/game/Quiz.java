package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;



public class Quiz extends JDialog {
	public static final int NUM_OF_QUESTIONS = 20;
	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();
	private JTextField answer1;
	private JTextField answer2;
	private JTextField answer3;
	private String playerAnswer1;
	private String playerAnswer2;
	private String playerAnswer3;
	private ArrayList<Integer> questionNumber;
	private Border redline = BorderFactory.createLineBorder(Color.red, 3);
	private Border greenline = BorderFactory.createLineBorder(Color.green, 3);

	public Quiz() {
		super();
		questionNumber = new ArrayList<Integer>();
		//Loads in the questions and answers from text files
		try {
			loadQuizQuestions();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			loadQuizAnswers();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		JPanel mainPanel = new JPanel();
		JPanel firstQuestion = new JPanel();
		JPanel secondQuestion = new JPanel();
		JPanel thirdQuestion = new JPanel();
		firstQuestion.setLayout(new GridLayout(1,2));
		secondQuestion.setLayout(new GridLayout(1,2));
		thirdQuestion.setLayout(new GridLayout(1,2));
		mainPanel.setLayout(new GridLayout(4,1));
		
		//Creates three panels, each with a random question from the pool of questions
		firstQuestion.add(makeQuestion());
		answer1 = new JTextField(10);
		answer1.setBorder(new TitledBorder(new EtchedBorder(), "Answer"));
		firstQuestion.add(answer1);
		
		secondQuestion.add(makeQuestion());
		answer2 = new JTextField(10);
		answer2.setBorder(new TitledBorder(new EtchedBorder(), "Answer"));
		secondQuestion.add(answer2);
		
		thirdQuestion.add(makeQuestion());
		answer3 = new JTextField(10);
		answer3.setBorder(new TitledBorder(new EtchedBorder(), "Answer"));
		thirdQuestion.add(answer3);
		
		JButton button = new JButton("Submit");
		mainPanel.add(firstQuestion);
		mainPanel.add(secondQuestion);
		mainPanel.add(thirdQuestion);
		mainPanel.add(button);
		// Action listener to check all the answers when the submit button is pressed
		class SubmitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				// Grabs the three player answers and checks to see if the are correct
				playerAnswer1 = answer1.getText();
				playerAnswer2 = answer2.getText();
				playerAnswer3 = answer3.getText();
				if(checkAnswer()) {
					//Increase the ammo count by 3
					GameFrame.getInstance().getControlGUI().increaseAmmoCount();
					GameFrame.getInstance().getControlGUI().increaseAmmoCount();
					GameFrame.getInstance().getControlGUI().increaseAmmoCount();
					setVisible(false);
				}
				else {
					giveRetryMessage();
				}
			}
		}
		button.addActionListener(new SubmitListener());
		mainPanel.setBorder(greenline);
		add(mainPanel);
		setSize(600, 475);
		
	}

	public void loadQuizQuestions() throws FileNotFoundException{
		FileReader reader = new FileReader("src/Quizzes/QuizQuestions.txt");
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String question = in.nextLine();
			questions.add(question);
		}
	}

	public void loadQuizAnswers() throws FileNotFoundException{
		FileReader reader = new FileReader("src/Quizzes/QuizAnswers.txt");
		Scanner in = new Scanner(reader);
		while (in.hasNextLine()) {
			String answer = in.nextLine();
			answers.add(answer);
		}
	}

	public JPanel makeQuestion() {
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(), "Question"));
		//Creates random int and puts it into an arraylist to keep track of the index of the question that is asked 
		Random random = new Random();
		int i = random.nextInt(NUM_OF_QUESTIONS);
		questionNumber.add(i);
		//Gets string from arrayList of questions using random int
		JTextArea jlabel = new JTextArea(questions.get(i));
		jlabel.setFont(new Font("Verdana",1,10));
		jlabel.setWrapStyleWord(true);
		jlabel.setLineWrap(true);
		jlabel.setEditable(false);
		jlabel.setBackground(null);
		panel.add(jlabel);
		return panel;
	}
	
	public boolean checkAnswer() {
		//uses the arraylist of indices to check the player answer against the actual answer from the arraylist of answers
		if (playerAnswer1.equalsIgnoreCase(answers.get(questionNumber.get(0))) && playerAnswer2.equalsIgnoreCase(answers.get(questionNumber.get(1))) && playerAnswer3.equalsIgnoreCase(answers.get(questionNumber.get(2)))) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public void giveRetryMessage() {
		JDialog message = new JDialog();
		JTextArea jlabel = new JTextArea("Wrong answer, try again");
		jlabel.setFont(new Font("Verdana",1,10));
		jlabel.setBorder(redline);
		jlabel.setEditable(false);
		message.add(jlabel);
		message.setSize(200, 200);
		message.setVisible(true);
	}
	
	public static void main(String[] args) {
		Quiz quiz = new Quiz();
		quiz.setVisible(true);
	}
	
}

