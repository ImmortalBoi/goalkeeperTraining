package goalkeeperTraining;


import java.util.concurrent.Phaser;

class MultithreadingDemo extends Thread {
	Goalkeeper goalkeeper;
	FootballBall ballInstance;
	GUI gui;
	PlayersMovement playersMovement;
	
	MultithreadingDemo(String name,Goalkeeper gk,FootballBall bi,GUI gui){
		super(name);
		goalkeeper = gk;
		ballInstance = bi;
		this.playersMovement = new PlayersMovement(gk,bi);
		this.gui = gui;
		
	}
	
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
					Thread.currentThread().getName()
                + " is Shooting");
            gui.generateAnimation();
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
public class GoalkeeperGame {
	static Phaser phaser = new Phaser(1);
	
	public static void main(String[] args) {
		Goalkeeper goalkeeper = new Goalkeeper();
		FootballBall ballInstance = new FootballBall();
		GoalkeeperGame game = new GoalkeeperGame();
		GUI mainGui = new GUI(goalkeeper, ballInstance);
		game.playGame(goalkeeper,ballInstance,mainGui);
	}
	
	public Boolean playGame(Goalkeeper goalkeeper,FootballBall ballInstance, GUI mainGui) {
		MultithreadingDemo p1 = new MultithreadingDemo("Player 1",goalkeeper,ballInstance, mainGui);
		MultithreadingDemo p2 = new MultithreadingDemo("Player 2",goalkeeper,ballInstance, mainGui);
		MultithreadingDemo p3 = new MultithreadingDemo("Player 3",goalkeeper,ballInstance, mainGui);
		MultithreadingDemo p4 = new MultithreadingDemo("Player 4",goalkeeper,ballInstance, mainGui);

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
		
		return true;
	}
	

}
