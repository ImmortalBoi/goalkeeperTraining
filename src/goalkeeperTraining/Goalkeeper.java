package goalkeeperTraining;


import java.util.Random;

public class Goalkeeper {
	Random r = new Random();
	Double velocity[] = new Double[2];
	int size[] = {10,30};
	int position[] = {720,350};

	public void generateVelocity() {
		velocity[0] = 2.0; //x
		velocity[1] = 2.0; //y
	}
	
	public void changePosition(int[] endingPos) {
		int[] range = {2,2};
		if(!isInRange(endingPos,range,0)){
			System.out.println("XStart: " + this.position[0]+", XEnd: "+endingPos[0]);
			System.out.println("ENTERED LOOP");
			if (this.position[0] > endingPos[0]) {
				velocity[0] = -velocity[0];
			}
			this.position[0] += velocity[0];
		}
		if(!isInRange(endingPos,range,1)){
			System.out.println("XStart: " + this.position[1]+", XEnd: "+endingPos[1]);
			System.out.println("ENTERED LOOP");
			if (this.position[1] > endingPos[1]) {
				velocity[1] = -velocity[1];
			}
			this.position[1] += velocity[1];
		}
	}

	public boolean isInRange(int[] endingPos,int[] range,int xyFlag){

		if(!(this.position[xyFlag] > endingPos[xyFlag]-range[xyFlag] && this.position[xyFlag] < endingPos[xyFlag]+range[xyFlag])){
			return false;
		}
		return true;
	}
}
