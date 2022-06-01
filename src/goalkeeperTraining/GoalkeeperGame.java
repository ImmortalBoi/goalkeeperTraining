package goalkeeperTraining;


import java.util.concurrent.Phaser;

class MultithreadingDemo extends Thread {

	MultithreadingDemo(String name){
		super(name);
	}
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
					Thread.currentThread().getName()
                + " is Shooting");
			game.playGame(goalkeeper,ballInstance);
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
public class GoalkeeperGame {
	static Phaser phaser = new Phaser(1);
	public Boolean playGame(Goalkeeper goalkeeper,FootballBall ballInstance,PlayersMovement playersMovement) {
		MultithreadingDemo p1 = new MultithreadingDemo("Player 1");
		MultithreadingDemo p2 = new MultithreadingDemo("Player 2");
		MultithreadingDemo p3 = new MultithreadingDemo("Player 3");
		MultithreadingDemo p4 = new MultithreadingDemo("Player 4");

		try {
			p1.start();
			phaser.awaitAdvance(p1.playersMovement.arrived);
			p1.join();
			p2.start();
			Thread.sleep(1000);
			phaser.awaitAdvance(p2.playersMovement.arrived);
			p2.join();
			p3.start();
			Thread.sleep(1000);
			phaser.awaitAdvance(p3.playersMovement.arrived);
			p3.join();
			p4.start();
			Thread.sleep(1000);
			phaser.awaitAdvance(p4.playersMovement.arrived);
			p4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		GUI mainGui = new GUI(goalkeeper, ballInstance);
		return true;
	}
	public static void main(String[] args) {
		Goalkeeper goalkeeper = new Goalkeeper();
		FootballBall ballInstance = new FootballBall();
		PlayersMovement playersMovement = new PlayersMovement(goalkeeper,ballInstance);
		GoalkeeperGame game = new GoalkeeperGame();
		game.playGame(goalkeeper,ballInstance,playersMovement);
	}
}
