package goalkeeperTraining;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import javax.swing.*;


public class PlayersMovement{
    Goalkeeper goalkeeper;
    FootballBall ballInstance;
    boolean movementOne = true;
    boolean movementTwo = false;
    int arrived;
    int thScore;
    boolean scored;
    PlayersMovement(Goalkeeper gk,FootballBall fb){
        goalkeeper = gk;
        ballInstance = fb;
    }
    Random rnd = new Random();
    int gk[] = {rnd.nextInt(1000-600)+600,350};
    int fb[] = {rnd.nextInt(1000-600)+600,rnd.nextInt(450-350)+350};
    public void movement(Goalkeeper goalkeeper, FootballBall ballInstance, Timer timer) {
        ballInstance.generateVelocity();
        goalkeeper.generateVelocity();
        if (movementOne) {
            goalkeeper.changePosition(gk);
            ballInstance.changePosition(fb);

            System.out.println("GoalKeeper Position: " + goalkeeper.position[0] + "," + goalkeeper.position[1]);
            System.out.println("Ball Position: " + ballInstance.position[0] + "," + ballInstance.position[1]);
            System.out.println("GK: " + gk[0] + "," + gk[1]);
            System.out.println("FB: " + fb[0] + ',' + fb[1]);

            boolean a = (goalkeeper.isInRange(gk, new int[]{4, 4}, 0) && goalkeeper.isInRange(gk, new int[]{4, 4}, 1));
            boolean b = (ballInstance.isInRange(fb, new int[]{5, 5}, 0) && ballInstance.isInRange(fb, new int[]{5, 5}, 1));

            System.out.println(a);
            System.out.println(b);

            if ((a && b)) {
                System.out.println("GK x = "+goalkeeper.position[0]);
                System.out.println("GK y = "+goalkeeper.position[1]);
                System.out.println("FB x = "+ballInstance.position[0]);
                System.out.println("FB y = "+ballInstance.position[1]);
                scored = isScored();
                System.out.println("Entered1");
                movementOne = false;
                movementTwo = true;
            }
        }
        if (movementTwo) {
            goalkeeper.changePosition(new int[]{720, 350});
            ballInstance.changePosition(new int[]{700, 600});
            boolean a = (goalkeeper.isInRange(new int[]{720, 350}, new int[]{4, 4}, 0) && goalkeeper.isInRange(new int[]{720, 350}, new int[]{4, 4}, 1));
            boolean b = (ballInstance.isInRange(new int[]{700, 600}, new int[]{5, 5}, 0) && ballInstance.isInRange(new int[]{700, 600}, new int[]{5, 5}, 1));
            if (a && b) {
                int temp[] = {rnd.nextInt(1000 - 600) + 600, 350};
                int temp2[] = {rnd.nextInt(1000 - 600) + 600, rnd.nextInt(450 - 350) + 350};
                gk = temp;
                fb = temp2;
                movementOne = true;
                movementTwo = false;
                try {
                    arrived = GoalkeeperGame.barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                timer.stop();
            }
        }
        System.out.println("Current Movement is :" + ((movementOne==true && movementTwo==false)?"Movement One":"Movement two"));
    }
 public boolean isScored(){
     boolean a = (goalkeeper.isInRange(ballInstance.position,goalkeeper.size, 0) && goalkeeper.isInRange(ballInstance.position, goalkeeper.size, 1));
     boolean b = (ballInstance.isInRange(goalkeeper.position, ballInstance.size, 0) && ballInstance.isInRange(goalkeeper.position, ballInstance.size, 1));
     if (a && b){
         return false;
     }
     return true;
    }
}

