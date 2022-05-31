package goalkeeperTraining;


import javax.swing.*;
import java.util.concurrent.Phaser;

class MultithreadingDemo extends Thread {
	GoalkeeperGame game = new GoalkeeperGame();
	Goalkeeper goalkeeper = new Goalkeeper();
	FootballBall ballInstance = new FootballBall();
	PlayersMovement playersMovement = new PlayersMovement();
	MultithreadingDemo(String name){
		super(name);
	}
    public void run()
    {
        try {

			game.playGame(goalkeeper,ballInstance,playersMovement);
            // Displaying the thread that is running
            System.out.println(
					Thread.currentThread().getName()
                + " is Shooting");
			GoalkeeperGame.phasers[1].arrive();

        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
public class GoalkeeperGame {
	Double goalSize[] = {100.0,50.0};
	static Phaser[] phasers = new Phaser[4];
	public static void main(String[] args) {
		MultithreadingDemo p1 = new MultithreadingDemo("Player 1");
		MultithreadingDemo p2 = new MultithreadingDemo("Player 2");
		MultithreadingDemo p3 = new MultithreadingDemo("Player 3");
		MultithreadingDemo p4 = new MultithreadingDemo("Player 4");
		for (int i = 0; i < 4; i++) {
			phasers[i] = new Phaser(1);
		}
		p1.start();
	}
	public Boolean playGame(Goalkeeper goalkeeper,FootballBall ballInstance,PlayersMovement playersMovement) {
		System.out.println("goalKeeper Vx: "+goalkeeper.velocity[0]+"\ngoalkeeper Vy: "+goalkeeper.velocity[1]);
		GUI mainGui = new GUI(goalkeeper, ballInstance);

		return true;
	}
}
