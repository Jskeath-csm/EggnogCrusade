package game;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class QuizDialog extends JDialog{
	
	private JRadioButton b1, b2, b3, b4;
	private JLabel question;
	private int answer;
	private JButton submitButton;
	private QuizDialog qd = this;
	private boolean submitted;
	
	public QuizDialog(){
		this.setLayout(new GridLayout(6,1));
		this.setSize(400,400);
		
		submitButton = new JButton("Submit");
		submitButton.addActionListener(new submitActionListener());
		
		//creates question
		question = new JLabel("This is a question?");
		
		//creates button group and radio buttons
		ButtonGroup bg = new ButtonGroup();
		b1 = new JRadioButton("choice 1");
		b2 = new JRadioButton("choice 2");
		b3 = new JRadioButton("choice 3");
		b4 = new JRadioButton("choice 4");
		
		//adds radiobuttons to button group
		bg.add(b1);
		bg.add(b2);
		bg.add(b3);
		bg.add(b4);
		
		
		add(question);
		add(b1);
		add(b2);
		add(b3);
		add(b4);
		add(submitButton);
		
		
		
		
		
	}
	public boolean gradeQuiz(String answer) {
		return false;
	}
	public void setQuestion(String question){
		this.question.setText(question);
	}
	public void setChoices(ArrayList<String> choices){
		if(choices.size() == 4){
			b1.setText(choices.get(0));
			b2.setText(choices.get(1));
			b3.setText(choices.get(2));
			b4.setText(choices.get(3));
		}
	}
	public void setAnswer(int a){
		answer = a;
	}
	
	public class submitActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if (e.getSource() == submitButton){
				submitted = true;
			}
			else{
				submitted = false;
			}
			qd.setVisible(false);
		}
	}
	
	public boolean gradeAnswer(){
		if(b1.isSelected() && answer == 0)
			return true;
		if(b2.isSelected() && answer == 1)
			return true;
		if(b3.isSelected() && answer == 2)
			return true;
		if(b4.isSelected() && answer == 3)
			return true;
		
		return false;
	}

	public static void main(String[] args) {
		//USAGE
		QuizDialog qdiag = new QuizDialog();
		qdiag.setModal(true); //IMPORTANT
		
		qdiag.setQuestion("Apple?");
		
		ArrayList<String> choices = new ArrayList<String>();
		choices.add("1");
		choices.add("2");
		choices.add("3");
		choices.add("4");
		
		qdiag.setAnswer(1); //the 2nd (0 based index) choice is correct
		
		qdiag.setChoices(choices);
		qdiag.setVisible(true);

		//modal, so syso is not run until dialog is not visible anymore
		//prints answer
		System.out.println(qdiag.gradeAnswer());
	}
	
	
}
