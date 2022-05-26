package goalkeeperTraining;


import javax.swing.*;

class MultithreadingDemo extends Thread {
    public void run()
    {
        try {
            // Displaying the thread that is running
            System.out.println(
                "Thread " + Thread.currentThread().getId()
                + " is running");
        }
        catch (Exception e) {
            // Throwing an exception
            System.out.println("Exception is caught");
        }
    }
}
public class GoalkeeperGame {
	Double goalSize[] = {100.0,50.0};
	public static void main(String[] args) {
//		int n = 4;
//		for (int i = 0; i < n; i++) {
//			MultithreadingDemo objectDemo = new MultithreadingDemo();
//			objectDemo.start();
//		}
		GoalkeeperGame game = new GoalkeeperGame();
		Goalkeeper goalkeeper = new Goalkeeper();
		FootballBall ballInstance = new FootballBall();
		
		game.playGame(goalkeeper,ballInstance);
	}
	private Boolean playGame(Goalkeeper goalkeeper,FootballBall ballInstance) {
//		goalkeeper.generateVelocity();
//		System.out.println("goalKeeper Vx: "+goalkeeper.velocity[0]+"\ngoalkeeper Vy: "+goalkeeper.velocity[1]);
//		Timer t = new Timer(10, new GUI.Animation.moveListener());
		GUI mainGui = new GUI(goalkeeper, ballInstance);

		return true;
	}
}
