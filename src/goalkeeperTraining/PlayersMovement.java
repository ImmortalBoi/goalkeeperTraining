package goalkeeperTraining;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import javax.swing.*;


public class PlayersMovement {
    Goalkeeper goalkeeper;
    FootballBall ballInstance;
    boolean movementOne = true;
    boolean movementTwo = false;
    int arrived;
    int thScore;
    boolean scored;
    boolean a;
    boolean b;
    PlayersMovement(Goalkeeper gk,FootballBall fb){
        goalkeeper = gk;
        ballInstance = fb;
    }
    Random rnd = new Random();
    int gk[] = {rnd.nextInt(800 - 200) + 200,250};
    int fb[] = {rnd.nextInt(800- 200) + 200,rnd.nextInt(450 - 250) + 250};
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

            a = (goalkeeper.isInRange(gk, new int[]{4, 4}, 0) && goalkeeper.isInRange(gk, new int[]{4, 4}, 1));
            b = (ballInstance.isInRange(fb, new int[]{5, 5}, 0) && ballInstance.isInRange(fb, new int[]{5, 5}, 1));

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
            goalkeeper.changePosition(new int[]{400, 250});
            ballInstance.changePosition(new int[]{430, 500});
            boolean a = (goalkeeper.isInRange(new int[]{400, 250}, new int[]{4, 4}, 0) && goalkeeper.isInRange(new int[]{400, 250}, new int[]{4, 4}, 1));
            boolean b = (ballInstance.isInRange(new int[]{430, 500}, new int[]{5, 5}, 0) && ballInstance.isInRange(new int[]{430, 500}, new int[]{5, 5}, 1));
            if (a && b) {
                int temp[] = {rnd.nextInt(800 - 200) + 200, 250};
                int temp2[] = {rnd.nextInt(800 - 200)+ 200, rnd.nextInt(450 - 250) + 250};
                gk = temp;
                fb = temp2;
                movementOne = true;
                movementTwo = false;
                try {
                    GoalkeeperGame.barrier.await();
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
     public void singleMovement(){
         ballInstance.generateVelocity();
         goalkeeper.generateVelocity();
         if (movementOne){
             goalkeeper.changePosition(new int[]{100, 250});
             boolean a = (goalkeeper.isInRange(new int[]{100, 250}, new int[]{4, 4}, 0) && goalkeeper.isInRange(new int[]{100, 250}, new int[]{4, 4}, 1));
             if (a){
                 movementOne = false;
                 movementTwo = true;
             }
         }
         if (movementTwo){
             goalkeeper.changePosition(new int[]{680, 250});
             boolean a = (goalkeeper.isInRange(new int[]{680, 250}, new int[]{4, 4}, 0) && goalkeeper.isInRange(new int[]{680, 250}, new int[]{4, 4}, 1));
             if (a){
                 movementOne = true;
                 movementTwo = false;
             }
         }
     }
     public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_RIGHT){
            this.ballInstance.position[0]+= 2;
        }
        else if (key == KeyEvent.VK_LEFT){
            this.ballInstance.position[0]-= 2;
        }
        else if (key == KeyEvent.VK_SPACE) {
            this.ballInstance.changePosition(new int[]{ballInstance.position[0],450});
        }
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

