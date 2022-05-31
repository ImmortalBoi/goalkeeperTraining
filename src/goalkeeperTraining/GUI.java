package goalkeeperTraining;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.*;

public class GUI {
	static JFrame frame = new JFrame();
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
		boolean movementThree = false;
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
			Random rnd = new Random();
			int gk[] = {rnd.nextInt(600,1000),350};
			int fb[] = {rnd.nextInt(600,1000),rnd.nextInt(350,450)};
			public void actionPerformed(ActionEvent e) {
				ballInstance.generateVelocity();
				goalkeeper.generateVelocity();
				if (movementOne){
					goalkeeper.changePosition(gk);
					if(goalkeeper.isInRange(gk, new int[]{2, 2}, 0) && goalkeeper.isInRange(gk, new int[]{2, 2}, 1)){
						movementOne = false;
						movementTwo = true;
						movementThree = false;
					}
				}
				if (movementTwo){
					ballInstance.changePosition(fb);
					if(ballInstance.isInRange(fb, new int[]{2, 2}, 0) && ballInstance.isInRange(fb, new int[]{2, 2}, 1)){
						movementOne = false;
						movementTwo = false;
						movementThree = true;
					}
				}
				if (movementThree){
					System.out.println("Three");
					goalkeeper.changePosition(new int[]{720,350});
					ballInstance.changePosition(new int[]{700, 600});
				}
				frame.repaint();
			}
		}

	}
}

