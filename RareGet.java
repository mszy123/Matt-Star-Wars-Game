//A RareGet is a special kind of Get that spawns more infrequently than the regular Get
//When consumed, RareGets restores the Player's HP in addition to awarding points
//Otherwise, behaves the same as a regular Get
public class RareGet extends Get{
    
    //Location of image file to be drawn for a RareGet
    protected static final String RAREGET_IMAGE_FILE = "assets/rare_get.gif";
    
    public RareGet(){
        this(0, 0);        
    }
    
    public RareGet(int x, int y){
        super(x, y, RAREGET_IMAGE_FILE);  
    }

    //getPointValue() is in parent since gets and rare gets have same point value
    

    //Colliding with an RareGet increases players HP by 1
    public int getDamageValue(){
        return 1;
    }
    
}
