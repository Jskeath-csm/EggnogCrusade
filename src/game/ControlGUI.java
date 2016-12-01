package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class ControlGUI extends JPanel{

	private GameFrame gameFrame;

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
		JPanel anglePanel = new JPanel();
		JLabel nameLabel = new JLabel("Enter Angle");
		JTextField angle = new JTextField(2); 
		anglePanel.setLayout(new BoxLayout(anglePanel, BoxLayout.Y_AXIS));
		anglePanel.add(nameLabel);
		anglePanel.add(angle);
		anglePanel.setBorder(new TitledBorder (new EtchedBorder(), "Angle Entering System"));
		JLabel jlabel = new JLabel("Hello there private, welcome to the nog corps");
		jlabel.setFont(new Font("Verdana",1,20));
		anglePanel.add(jlabel);
		return anglePanel;
	}

	private JLabel fireButtonPanel(){
		//BufferedImage buttonPic = gameFrame.getButtonImage();
		BufferedImage buttonPic;
		try {
			buttonPic = ImageIO.read(new File("images/Button.png"));
			JLabel picLabel = new JLabel(new ImageIcon(buttonPic));
			return picLabel;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


	private JPanel createAmmoBox() {
		JPanel nogPanel = new JPanel();
		JLabel nameLabel = new JLabel("Ammo:");
		nogPanel.add(nameLabel);

		//BufferedImage nogPic = gameFrame.getImage();
		BufferedImage nogPic;
		try {
			nogPic = ImageIO.read(new File("images/Nog.png"));

			//Get ammo count and draw all the nogs
			//gameFrame.getAmmoCount();
			for (int i = 1; i <= 5; i++) {
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
}
