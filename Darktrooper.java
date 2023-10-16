public class Darktrooper extends Avoid implements Shootable{
    //Location of image file to be drawn for a RareGet
    protected static final String DARKTROOPER_IMAGE_FILE = "assets/darktrooper.gif";

    //Dimensions of the Avoid    
    private static final int DARKTROOPER_WIDTH = 65;
    private static final int DARKTROOPER_HEIGHT = 115;

    //Speed that the avoid moves each time the game scrolls
    private static final int DARKTROOPER_SCROLL_SPEED = 3;
    
    public Darktrooper(){
        this(0, 0);        
    }
    
    public Darktrooper(int x, int y){
        super(x, y, DARKTROOPER_WIDTH, DARKTROOPER_HEIGHT, DARKTROOPER_IMAGE_FILE);  
    }

    @Override
    //Move the avoid left by the scroll speed
    public void scroll(){
        setX(getX() - DARKTROOPER_SCROLL_SPEED);
    }

    public int getPointsValue(){
        return 2;
    }
}
