//Gets are entities that the player wants to collide with, as they increase
//their score.
public class Get extends Entity implements Consumable, Scrollable {
    
    //Location of image file to be drawn for a Get
    protected static final String GET_IMAGE_FILE = "assets/get.gif";
    //Dimensions of the Get  
    protected static final int GET_WIDTH = 75; //TODO: any way to change this directly
    protected static final int GET_HEIGHT = 75;
    //Speed that the Get moves (in pixels) each time the game scrolls
    protected static final int GET_SCROLL_SPEED = 5; //TODO: better to have set in this or get in custom 
    //Amount of points received when player collides with a Get
    protected static final int GET_POINT_VALUE = 20;
    
    
    public Get(){
        this(0, 0);        
    }
    
    public Get(int x, int y){
        super(x, y, GET_WIDTH, GET_HEIGHT, GET_IMAGE_FILE);  
    }
    
    public Get(int x, int y, String imageFileName){
        super(x, y, GET_WIDTH, GET_HEIGHT, imageFileName);
    }

    public Get(int x, int y, int width, int height, String imageFileName){
        super(x, y, width, height, imageFileName);
    }
    
    public int getScrollSpeed(){
        return GET_SCROLL_SPEED;
    }
    
    //Move the Get left by its scroll speed
    public void scroll(){
        setX(getX() - GET_SCROLL_SPEED);
    }
    
    //Colliding with a Get increases the player's score by the specified amount
    public int getPointsValue(){
        return GET_POINT_VALUE;
    }
    
    //Colliding with a Get does not affect the player's HP
    public int getDamageValue(){
        return 0;
    }
    
}
