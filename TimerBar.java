public class TimerBar extends Entity {
    
//Location of image file to be drawn for an Avoid
private static final String GREEN_BAR_IMAGE_FILE = "assets/timer_green.png";
private static final String YELLOW_BAR_IMAGE_FILE = "assets/timer_yellow.png";
private static final String RED_BAR_IMAGE_FILE = "assets/timer_red.png";

//Dimensions of the Avoid    
private static final int TIMER_WIDTH = 100;
private static final int TIMER_HEIGHT = 20;
private int timeToRun;

public TimerBar(int timeToRun){
    super(10, 10, TIMER_WIDTH, TIMER_HEIGHT, GREEN_BAR_IMAGE_FILE);  
    this.timeToRun = timeToRun;
}

//decrements time of statusbar each tick
public void newTick(int ticksLeft){
    if(ticksLeft <= 0){
        //setWidth(0);
        return;
    }

    //gets new width of bar each tick
    int newWidth = (int)(((double)ticksLeft/timeToRun) * TIMER_WIDTH);

    if((double)ticksLeft/timeToRun < .50 && (double)ticksLeft/timeToRun > .45){ //don't want to change image forever, but need range since we might not have exactly 50%
        setImageName(YELLOW_BAR_IMAGE_FILE);
    }
    if((double)ticksLeft/timeToRun < .25 && (double)ticksLeft/timeToRun > .20){ //don't want to change image forever, but need range since we might not have exactly 25%
        setImageName(RED_BAR_IMAGE_FILE);
    }

    setWidth(newWidth);


}





}
