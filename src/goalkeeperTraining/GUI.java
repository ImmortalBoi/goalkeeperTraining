package goalkeeperTraining;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GUI {
	static JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel playerView = new JLabel();
	JLabel player1Score = new JLabel("Player 1 Score: 0");
	JLabel player2Score = new JLabel("Player 2 Score: 0");
	JLabel player3Score = new JLabel("Player 3 Score: 0");
	JLabel player4Score = new JLabel("Player 4 Score: 0");
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
		frame.add(playerView);
		frame.add(player1Score);
		frame.add(player2Score);
		frame.add(player3Score);
		frame.add(player4Score);
		frame.setTitle("GUI TEST");
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		frame.repaint();
	}
	public void generateAnimation() {
		frame.add(new Animation(goalkeeper,footballBall), BorderLayout.CENTER);
		playerView.setFont(new Font("Arial",Font.BOLD,36));
		player1Score.setFont(new Font("Arial",Font.BOLD,36));
		player2Score.setFont(new Font("Arial",Font.BOLD,36));
		player3Score.setFont(new Font("Arial",Font.BOLD,36));
		player4Score.setFont(new Font("Arial",Font.BOLD,36));
		Dimension playerSize = playerView.getPreferredSize();
		player1Score.setBounds(0,100,playerSize.width,playerSize.height);
		player2Score.setBounds(0,200,playerSize.width,playerSize.height);
		player3Score.setBounds(0,300,playerSize.width,playerSize.height);
		player4Score.setBounds(0,400,playerSize.width,playerSize.height);
		playerView.setSize(playerSize);
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

