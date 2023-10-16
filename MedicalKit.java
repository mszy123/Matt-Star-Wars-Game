public class MedicalKit extends Get {
    //Location of image file to be drawn for a RareGet
    protected static final String MEDKIT_IMAGE_FILE = "assets/medical_kit.png";

    protected static final int MEDKIT_WIDTH = 50;
    protected static final int MEDKIT_HEIGHT = 50;

    protected static final int MEDKIT_SCROLL_SPEED = 3;

    
    public MedicalKit(){
        this(0, 0);        
    }
        
    public MedicalKit(int x, int y){
        super(x, y, MEDKIT_WIDTH, MEDKIT_HEIGHT, MEDKIT_IMAGE_FILE);  
    }

    @Override
    public int getPointsValue() {
        return 0;
    }
    

    //Colliding with an lightsaver increases players HP by 1
    public int getDamageValue(){
        return 1;
    }

    @Override
    public int getScrollSpeed(){
        return MEDKIT_SCROLL_SPEED;
    }

    //Move the Get left by its scroll speed
    public void scroll(){
        setX(getX() - MEDKIT_SCROLL_SPEED);
    }
}

