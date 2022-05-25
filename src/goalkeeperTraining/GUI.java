package goalkeeperTraining;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;

import javax.swing.*;

public class GUI {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	public GUI() {
		frame.add(new Animation(), BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GUI TEST");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	class Animation extends JPanel{
		Goalkeeper goalkeeper = new Goalkeeper();
		FootballBall ballInstance = new FootballBall();
		int xPanel = getWidth();
		int yPanel = getHeight();


		Timer t = new Timer(30, new moveListener());
		Animation(){
			super.setBorder(BorderFactory.createEmptyBorder(500, 600, 500, 600));
			super.setLayout(new GridLayout(0, 1));
			t.start();
		}
		public void paintComponent (Graphics g) {
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image i = tk.getImage("src/goalkeeperTraining/goalkeeper poses/goalkeeperstanding.png");
			g.drawImage(i,goalkeeper.position[0],goalkeeper.position[1],this);
			return;
		}
		private class moveListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				ballInstance.generateVelocity();
				goalkeeper.generateVelocity();
				boolean endFlag = false;
				goalkeeper.changePosition(new int[]{900, 350});


				if (ballInstance.position[0]>=0.0 && ballInstance.position[0] <=600) {
					ballInstance.changePosition();
				}
				frame.repaint();
			}
		}

	}

	public static void main(String args[]) {
		new GUI();
	}
}

