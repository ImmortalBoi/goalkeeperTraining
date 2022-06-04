package goalkeeperTraining;


import java.util.Random;

public class FootballBall {
    Random r = new Random();
    Double velocity[] = new Double[2];
    int size[] = {53,53};
    int position[] = {700,600};

    public void generateVelocity() {
        velocity[0] = 5.0;
        velocity[1] = 5.0;
    }

    public void changePosition(int[] endingPos) { //change into better movement using the line equation
        int[] range = {5,5};
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
