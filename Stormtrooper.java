public class Stormtrooper extends Avoid implements Shootable{
    //Location of image file to be drawn for a RareGet
    protected static final String STORMTROOPER_IMAGE_FILE = "assets/stormtrooper.gif";

    //Dimensions of the Avoid    
    private static final int STORMTROOPER_WIDTH = 65;
    private static final int STORMTROOPER_HEIGHT = 115;

    //Speed that the avoid moves each time the game scrolls
    private static final int STORMTROOPER_SCROLL_SPEED = 2;
    
    public Stormtrooper(){
        this(0, 0);        
    }
    
    public Stormtrooper(int x, int y){
        super(x, y, STORMTROOPER_WIDTH, STORMTROOPER_HEIGHT, STORMTROOPER_IMAGE_FILE);  
    }

    @Override
    //Move the avoid left by the scroll speed
    public void scroll(){
        setX(getX() - STORMTROOPER_SCROLL_SPEED);
    }

    public int getPointsValue(){
        return 1;
    }
}
