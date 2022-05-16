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
		frame.setVisible(true);
	}

	class Animation extends JPanel{
		FootballBall ballInstance = new FootballBall();
		Timer t = new Timer(30, new moveListener());
		Animation(){
			super.setBorder(BorderFactory.createEmptyBorder(500, 600, 500, 600));
			super.setLayout(new GridLayout(0, 1));
			t.start();
		}
		public void paintComponent (Graphics g) {
			frame.paintComponents(g);
			Graphics g2 = (Graphics2D) g;
			Ellipse2D.Double c = new Ellipse2D.Double(ballInstance.position[0],ballInstance.position[1],ballInstance.size[0],ballInstance.size[1]);
			g2.setColor(Color.pink);
			g2.fillOval(ballInstance.position[0],ballInstance.position[1],ballInstance.size[0],ballInstance.size[1]);
			return;
		}
		private class moveListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				ballInstance.generateVelocity();
				if (ballInstance.position[0]>=0.0 && ballInstance.position[0] <=270.0) {
					ballInstance.changePosition();
				}
				repaint();
			}

		}

	}

	public static void main(String args[]) {
		new GUI();
	}
}

