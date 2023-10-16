public class MyPlayer extends Player {
    
    //Location of image file to be drawn for a Player
    protected static final String PLAYER_IMAGE_FILE = "assets/mandalorian1.gif";
    protected static final String PLAYER_LIGHTSABER_IMAGE_FILE = "assets/mandalorian_saber.gif";

    //Dimensions of the Player
    protected static final int PLAYER_WIDTH = 85;
    protected static final int PLAYER_HEIGHT = 115;

    private boolean hasLightsaber = false;

    //number of ticks you can hold the lightsaber for
    private static final int LIGHTSABER_TIME = 800; 


    public MyPlayer(){
        super();        
    }

    public MyPlayer(int x, int y){
        super(x, y, PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_IMAGE_FILE);
    }

    public boolean ifLightsaber(){
        return hasLightsaber;
    }

    public void gainedLightsaber(){
        setImage(PLAYER_LIGHTSABER_IMAGE_FILE);
        hasLightsaber = true;
    }

    public void lostLightsaber(){
        setImage(PLAYER_IMAGE_FILE);
        hasLightsaber = false;
    }

    public void setLightsaber(boolean hasLightsaber){
        this.hasLightsaber = hasLightsaber;
    }

    public int getLightsaberTime(){
        return LIGHTSABER_TIME;
    }


    
}
