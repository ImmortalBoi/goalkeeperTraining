package goalkeeperTraining;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
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
	public static CyclicBarrier barrier = new CyclicBarrier(2);
	MultithreadingDemo p1;
	MultithreadingDemo p2;
	MultithreadingDemo p3;
	MultithreadingDemo p4;
	public static void main(String[] args) {
		Goalkeeper goalkeeper = new Goalkeeper();
		FootballBall ballInstance = new FootballBall();
		GoalkeeperGame game = new GoalkeeperGame();
		GUI mainGui = new GUI(goalkeeper, ballInstance);
		game.playGame(goalkeeper,ballInstance,mainGui);
	}
	public void terminateThreads(){
		p1.interrupt();
		p2.interrupt();
		p3.interrupt();
		p4.interrupt();
	}
	public Boolean playGame(Goalkeeper goalkeeper,FootballBall ballInstance, GUI mainGui) {
		p1 = new MultithreadingDemo("Player 1",goalkeeper,ballInstance, mainGui);
		p2 = new MultithreadingDemo("Player 2",goalkeeper,ballInstance, mainGui);
		p3 = new MultithreadingDemo("Player 3",goalkeeper,ballInstance, mainGui);
		p4 = new MultithreadingDemo("Player 4",goalkeeper,ballInstance, mainGui);

		try {
			p1.start();
			barrier.await();
			p1.interrupt();
			p2.start();
			barrier.await();
			p2.interrupt();
			p3.start();
			barrier.await();
			p3.interrupt();
			p4.start();
			barrier.await();
			p4.interrupt();
			return true;
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			e.printStackTrace();
		}

		return true;
	}
	

}
