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
    int gk[] = {rnd.nextInt(700 - 100) + 100,250};
    int fb[] = {rnd.nextInt(700- 100) + 100,rnd.nextInt(350 - 250) + 250};
    int fbstart[] = {rnd.nextInt(800- 100) + 100,rnd.nextInt(450 - 250) + 250};
    int reportFlag = 0;
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
                if(isScored()==true){
                    reportFlag = 1;
                }
                else{
                    reportFlag = 2;
                }
                System.out.println("Entered1");
                movementOne = false;
                movementTwo = true;
            }
        }
        if (movementTwo) {
//            goalkeeper.position = new int[]{400,250};
//            ballInstance.position = new int[]{400,250};
            goalkeeper.changePosition(new int[]{400, 250});
//            ballInstance.changePosition(new int[]{430, 500});
            ballInstance.position = new int[]{430, 500};
            boolean a = (goalkeeper.isInRange(new int[]{400, 250}, new int[]{4, 4}, 0) && goalkeeper.isInRange(new int[]{400, 250}, new int[]{4, 4}, 1));
            boolean b = (ballInstance.isInRange(new int[]{430, 500}, new int[]{5, 5}, 0) && ballInstance.isInRange(new int[]{430, 500}, new int[]{5, 5}, 1));
            if (a && b) {
                int temp[] = {rnd.nextInt(800 - 100) + 100, 250};
                int temp2[] = {rnd.nextInt(800 - 100)+ 100, rnd.nextInt(450 - 250) + 250};
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
         if(isScored() == true){
             reportFlag = 1;
         }
     }
     public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        e.setKeyCode(0);
         if (key == KeyEvent.VK_RIGHT){
            this.ballInstance.position[0]+= 5;
        }
        if (key == KeyEvent.VK_LEFT){
            this.ballInstance.position[0]-= 5;
        }
        if (key == KeyEvent.VK_SPACE) {
            this.ballInstance.velocity = new Double[]{6.0, 150.0};
            this.ballInstance.changePosition(new int[]{ballInstance.position[0],100});
        }
     }
     public boolean isScored(){
         boolean isInGoal = (ballInstance.isInRange(new int[]{450,100},new int[]{350,150},0)) &&  (ballInstance.isInRange(new int[]{450,300},new int[]{350,150},1));
         boolean isBlocked = (ballInstance.isInRange(new int[]{goalkeeper.position[0]+goalkeeper.size[0]/2,goalkeeper.position[1]+goalkeeper.size[1]/2},new int[]{goalkeeper.size[0]/2,goalkeeper.size[1]/2},0))&&(ballInstance.isInRange(new int[]{goalkeeper.position[0]+goalkeeper.size[0]/2,goalkeeper.position[1]+goalkeeper.size[1]/2},new int[]{goalkeeper.size[0]/2,goalkeeper.size[1]/2},1));
         System.out.println("Is in goal "+isInGoal);
         if (isInGoal && !isBlocked){
             return true;
         }
         return false;
    }
}

