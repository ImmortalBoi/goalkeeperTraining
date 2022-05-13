package goalkeeperTraining;

import java.util.Random;

public class Goalkeeper {
	Random r = new Random();
	Double velocity[] = new Double[2];
	Double size[] = {10.0,30.0};
	Double position[] = {0.0,0.0};
	
	public void generateVelocity() {
		velocity[0] = 135.0*(r.nextDouble()*2-1); //Equation used is: Goal width/2 - Keeper width/2 * 30m/s, where 30 m/s is the average speed of a ball in a penalty
		velocity[1] = 60.0*r.nextDouble(); 		  //Same equation but with height
	}
	
	public void changePosition() {
		for (int i = 0; i < position.length; i++) {
			position[i] += velocity[i];
		}
	}
}
