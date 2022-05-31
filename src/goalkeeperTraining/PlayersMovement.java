package goalkeeperTraining;

import java.util.Random;

public class PlayersMovement {
    boolean movementOne = true;
    boolean movementTwo = false;
    Random rnd = new Random();
    int gk[] = {rnd.nextInt(600,900),350};
    int fb[] = {rnd.nextInt(600,900),rnd.nextInt(350,450)};
    public void movement(Goalkeeper goalkeeper,FootballBall ballInstance){
        if (movementOne){
            goalkeeper.changePosition(gk);
            if(goalkeeper.isInRange(gk, new int[]{2, 2}, 0) && goalkeeper.isInRange(gk, new int[]{2, 2}, 1)){
                movementOne = false;
                movementTwo = true;
            }
        }
        if (movementTwo){
            ballInstance.changePosition(fb);
            if(ballInstance.isInRange(fb, new int[]{2, 2}, 0) && ballInstance.isInRange(fb, new int[]{2, 2}, 1)){
                movementOne = true;
                movementTwo = false;
            }
        }
    }
}
