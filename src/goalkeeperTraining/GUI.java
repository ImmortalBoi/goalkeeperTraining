package goalkeeperTraining;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {
	static JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	Goalkeeper goalkeeper;
	FootballBall footballBall;

	public GUI(Goalkeeper goalkeeper, FootballBall ballInstance) {
		this.goalkeeper = goalkeeper;
		this.footballBall = ballInstance;
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.addWindowListener(new java.awt.event.WindowAdapter() {
//			@Override
//			public void windowClosing(java.awt.event.WindowEvent windowEvent){
//				throw new Exception("Exception message");
//			}
//		});
		frame.setTitle("GUI TEST");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.repaint();
	}
	public void generateAnimation() {
		frame.add(new Animation(goalkeeper,footballBall), BorderLayout.CENTER);
		frame.repaint();
	}
	class Animation extends JPanel{
		Goalkeeper goalkeeper;
		FootballBall ballInstance;
		Timer timer;
		PlayersMovement playersMovement;

		Animation(Goalkeeper goalkeeper, FootballBall ballInstance){
			this.goalkeeper = goalkeeper;
			this.ballInstance = ballInstance;
			playersMovement = new PlayersMovement(goalkeeper,ballInstance);
			timer = new Timer(10, new moveListener());
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			super.setLayout(new GridLayout(0, 1));
			timer.start();
			frame.repaint();
		}

		public void paintComponent (Graphics g) {
			Toolkit tk = Toolkit.getDefaultToolkit();
			Image spriteGoalKeeperStanding = tk.getImage("src/goalkeeperTraining/SpriteFiles/GoalKeeperSprites/goalkeeperstanding.png");
			Image backgroundImage = tk.getImage("src/goalkeeperTraining/SpriteFiles/BackgroundImages/footballGoalFull.png");
			Image ballImage = tk.getImage("src/goalkeeperTraining/SpriteFiles/BallSprites/BallImage2.png");
			g.drawImage(backgroundImage,500,100,this);
			g.drawImage(spriteGoalKeeperStanding,goalkeeper.position[0],goalkeeper.position[1],this);
			g.drawImage(ballImage,ballInstance.position[0],ballInstance.position[1],this);
			return;
		}
		private class moveListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				playersMovement.movement(playersMovement.goalkeeper, playersMovement.ballInstance,timer);
				frame.repaint();
			}
		}
	}
}

