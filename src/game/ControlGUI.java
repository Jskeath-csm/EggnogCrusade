package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JPanel{

	private GameFrame gameFrame;
	private JTextField angle;
	private double theta = 45;
	
	private boolean firePressed = false;
	private int ammoCount = 5;

	public ControlGUI() {
		gameFrame = GameFrame.getInstance();
		setLayout(new GridLayout(1,4));
		add(createAngleBox());
		add(fireButtonPanel());
		add(createAmmoBox());
		Border redline = BorderFactory.createLineBorder(Color.red, 3);
		Border raisedbevel = BorderFactory.createRaisedBevelBorder();
		Border matteBorder = BorderFactory.createMatteBorder(3, 3, 3, 3, Color.green);
		Border compound = BorderFactory.createCompoundBorder(raisedbevel, redline);
		compound = BorderFactory.createCompoundBorder(matteBorder, compound);
		setBorder(compound);
	}

	private JPanel createAngleBox() {
		JPanel angleDisplayPanel = new JPanel();
		angleDisplayPanel.setBorder(new TitledBorder (new EtchedBorder(), "Angle Entering System"));
		angleDisplayPanel.setLayout(new BoxLayout(angleDisplayPanel, BoxLayout.Y_AXIS));
		
		JPanel angleEnterPanel = new JPanel();
		JLabel nameLabel = new JLabel("Enter Angle");
		nameLabel.setSize(50, 50);
		angleEnterPanel.add(nameLabel);
		
		angle = new JTextField(2); 
		angle.setText("45");
		angleEnterPanel.add(angle);
		
		angleDisplayPanel.add(angleEnterPanel);
		
		JTextArea  jlabel = new JTextArea("To arms private, the people of the city "
				+ "want western culture. Dick Cheney says the best way to do this is with the spirit of Christmas. "
				+ "The civilians have set out buckets to catch America's famous eggnog, "
				+ "launch the nog into these buckets to save the holy land.");
		jlabel.setFont(new Font("Verdana",1,15));
		jlabel.setWrapStyleWord(true);
		jlabel.setLineWrap(true);
		jlabel.setEditable(false);
		jlabel.setBackground(null);
		angleDisplayPanel.add(jlabel);
		return angleDisplayPanel;
	}

	private JLabel fireButtonPanel(){
		URL url = getClass().getResource("/images/Button.png");
		JLabel picLabel = new JLabel();
		try {
			picLabel.setIcon(new ImageIcon(ImageIO.read(url)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		picLabel.addMouseListener(new FireListener());
		return picLabel;
	}

	private class FireListener implements MouseListener{


		@Override
		public void mouseClicked(MouseEvent arg0) {}

		@Override
		public void mouseEntered(MouseEvent arg0) {}

		@Override
		public void mouseExited(MouseEvent arg0) {}

		@Override
		public void mousePressed(MouseEvent arg0) {
			System.out.println("Printed");
			firePressed = true;		
			ammoCount--;
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {}
		
	}

	private JPanel createAmmoBox() {
		JPanel nogPanel = new JPanel();
		JLabel nameLabel = new JLabel("Ammo:");
		nogPanel.add(nameLabel);

		//BufferedImage nogPic = gameFrame.getImage();
		BufferedImage nogPic;
		try {
			URL url = getClass().getResource("/images/Nog2.png");
			nogPic = ImageIO.read(url);

			for (int i = 1; i <= ammoCount; i++) {
				JLabel picLabel = new JLabel(new ImageIcon(nogPic));
				nogPanel.add(picLabel);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

		nogPanel.setBorder(new TitledBorder (new EtchedBorder(), "Nogs Left"));
		return nogPanel;
	}


	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Temp title");
		frame.setSize(1200, 250);

		ControlGUI gui = new ControlGUI();
		frame.add(gui);
		frame.setVisible(true);

	}
	
	public boolean isFired(){
		return firePressed;
	}
	public void resetFired(){
		firePressed = false;
	}
	
	public double getAngle(){
		double a = theta;
		try{
			a = Double.parseDouble(angle.getText());
		}
		catch(NumberFormatException e){	}
		return a;
	}
}
