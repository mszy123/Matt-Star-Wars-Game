public class Lightsaber extends Get {
    //Location of image file to be drawn for a RareGet
    protected static final String LIGHTSABER_IMAGE_FILE = "assets/darksaber.png";

    protected static final int LIGHTSABER_WIDTH = 75;
    protected static final int LIGHTSABER_HEIGHT = 75;

    protected static final int LIGHTSABER_SCROLL_SPEED = 2;

    //TODO: how do i change the image without modifying the regular Get
    
    public Lightsaber(){
        this(0, 0);        
    }
        
    public Lightsaber(int x, int y){
        super(x, y, LIGHTSABER_WIDTH, LIGHTSABER_HEIGHT, LIGHTSABER_IMAGE_FILE);  
    }

    @Override
    public int getPointsValue() {
        return 0;
    }
    

    //Colliding with an lightsaver increases players HP by 0
    public int getDamageValue(){
        return 0;
    }

    @Override
    public int getScrollSpeed(){
        return LIGHTSABER_SCROLL_SPEED;
    }

    //Move the Get left by its scroll speed
    public void scroll(){
        setX(getX() - LIGHTSABER_SCROLL_SPEED);
    }
}
