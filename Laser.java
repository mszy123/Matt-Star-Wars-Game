
public class Laser extends Entity implements Scrollable {
    
    //Location of image file to be drawn for an Avoid
    private static final String AVOID_IMAGE_FILE = "assets/red_laser.png";
    //Dimensions of the Avoid    
    private static final int LASER_WIDTH = 50;
    private static final int LASER_HEIGHT = 10;
    //Speed that the avoid moves each time the game scrolls
    private static final int LASER_SCROLL_SPEED = -5; 

    //number of ticks between each laser shot
    private static final int LASER_DELAY = 20;

    public Laser(){
        this(0, 0);        
    }

    public Laser(int x, int y){
        super(x, y, LASER_WIDTH, LASER_HEIGHT, AVOID_IMAGE_FILE);  
    }

    public int getScrollSpeed(){
        return LASER_SCROLL_SPEED;
    }

    public static int getLaserDelay(){
        return LASER_DELAY;
    }
    
    //Move the avoid left by the scroll speed
    public void scroll(){
        setX(getX() - LASER_SCROLL_SPEED);
    }

}
