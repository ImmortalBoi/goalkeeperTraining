package goalkeeperTraining;

import java.util.Random;

public class FootballBall {
    Random r = new Random();
    Double velocity[] = new Double[2];
    int size[] = {10,10};
    int position[] = {600,500};

    public void generateVelocity() {
//        velocity[0] = 1.350*(r.nextDouble()*2-1); //Equation used is: Goal width/2 - Keeper width/2 * 30m/s, where 30 m/s is the average speed of a ball in a penalty
//        velocity[1] = 0.600*r.nextDouble(); 		  //Same equation but with
        velocity[0] = 0.0;
        velocity[1] = 0.0;
    }

    public void changePosition() {
        for (int i = 0; i < position.length; i++) {
            position[i] += velocity[i];
        }
    }

}
