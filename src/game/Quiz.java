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
	private ArrayList<String> questions = new ArrayList<String>();
	private ArrayList<String> answers = new ArrayList<String>();
	private JTextField answer;
	private String playerAnswer;
	private int questionNumber;
	private Border redline = BorderFactory.createLineBorder(Color.red, 3);
	private Border greenline = BorderFactory.createLineBorder(Color.green, 3);

	public Quiz() {
		super();
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
		mainPanel.setLayout(new GridLayout(3,1));
		
		mainPanel.add(makeQuestion());
		answer = new JTextField(10);
		answer.setBorder(new TitledBorder(new EtchedBorder(), "Answer"));
		mainPanel.add(answer);
		
		JButton button = new JButton("Submit");
		mainPanel.add(button);
		class SubmitListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				playerAnswer = answer.getText();
				if(checkAnswer()) {
					//add ammo
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
		
		Random random = new Random();
		questionNumber = random.nextInt(10);
		JTextArea jlabel = new JTextArea(questions.get(questionNumber));
		jlabel.setFont(new Font("Verdana",1,10));
		jlabel.setWrapStyleWord(true);
		jlabel.setLineWrap(true);
		jlabel.setEditable(false);
		jlabel.setBackground(null);
		panel.add(jlabel);
		return panel;
	}
	
	public boolean checkAnswer() {
		if (playerAnswer.equals(answers.get(questionNumber))) {
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

