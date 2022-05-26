package goalkeeperTraining;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();

	public GUI(Goalkeeper goalkeeper, FootballBall ballInstance) {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new Animation(goalkeeper,ballInstance), BorderLayout.CENTER);
		frame.setTitle("GUI TEST");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	class Animation extends JPanel{
		Goalkeeper goalkeeper;
		FootballBall ballInstance;
		boolean movementOne = true;
		boolean movementTwo = false;
		Timer t = new Timer(10, new moveListener());
		Animation(Goalkeeper goalkeeper, FootballBall ballInstance){
			this.goalkeeper = goalkeeper;
			this.ballInstance = ballInstance;
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			super.setLayout(new GridLayout(0, 1));
			t.start();
		}
		public void paintComponent (Graphics g) {
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image spriteGoalKeeperStanding = tk.getImage("src/goalkeeperTraining/SpriteFiles/GoalKeeperSprites/goalkeeperstanding.png");
			Image backgroundImage = tk.getImage("src/goalkeeperTraining/SpriteFiles/BackgroundImages/footballBackground.png");
			Image ballImage = tk.getImage("src/goalkeeperTraining/SpriteFiles/BallSprites/BallImage2.png");
			g.drawImage(backgroundImage,500,100,this);
			g.drawImage(spriteGoalKeeperStanding,goalkeeper.position[0],goalkeeper.position[1],this);
			g.drawImage(ballImage,ballInstance.position[0],ballInstance.position[1],this);
			return;
		}
		private class moveListener implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				ballInstance.generateVelocity();
				goalkeeper.generateVelocity();
				if (movementOne){
					goalkeeper.changePosition(new int[]{900, 350});
					if(goalkeeper.isInRange(new int[]{900, 350}, new int[]{2, 2}, 0) && goalkeeper.isInRange(new int[]{900, 350}, new int[]{2, 2}, 1)){
						movementOne = false;
						movementTwo = true;
					}
				}
				if (movementTwo){
					ballInstance.changePosition(new int[]{900, 350});
					if(ballInstance.isInRange(new int[]{900, 350}, new int[]{2, 2}, 0) && ballInstance.isInRange(new int[]{900, 350}, new int[]{2, 2}, 1)){
						movementOne = true;
						movementTwo = false;
					}
				}


//				if (ballInstance.position[0]>=0.0 && ballInstance.position[0] <=600) {
//					ballInstance.changePosition();
//				}
				frame.repaint();
			}
		}

	}
}

