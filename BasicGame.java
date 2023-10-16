import java.awt.*;
import java.awt.event.*;
import java.util.*;

//A Simple version of the scrolling game, featuring Avoids, Gets, and RareGets
//Players must reach a score threshold to win
//If player runs out of HP (via too many Avoid collisions) they lose
public class BasicGame extends ScrollingGameEngine {
    
    //Dimensions of game window
    protected static final int DEFAULT_WIDTH = 900;
    protected static final int DEFAULT_HEIGHT = 600;  
    
    //Starting Player coordinates
    protected static final int STARTING_PLAYER_X = 0;
    protected static final int STARTING_PLAYER_Y = 100;
    
    //Score needed to win the game
    protected static final int SCORE_TO_WIN = 300;
    
    //Maximum that the game speed can be increased to
    //(a percentage, ex: a value of 300 = 300% speed, or 3x regular speed)
    protected static final int MAX_GAME_SPEED = 300;
    //Interval that the speed changes when pressing speed up/down keys
    protected static final int SPEED_CHANGE = 20;    
    
    protected static final String INTRO_SPLASH_FILE = "assets/splash.gif";        
    //Key pressed to advance past the splash screen
    public static final int ADVANCE_SPLASH_KEY = KeyEvent.VK_ENTER;
    
    //Interval that Entities get spawned in the game window
    //ie: once every how many ticks does the game attempt to spawn new Entities
    protected static final int SPAWN_INTERVAL = 45; 
    
    
    //A Random object for all your random number generation needs!
    protected static final Random rand = new Random();
    
    
    
    
    
    //Player's current score
    protected int score;
    
    //Stores a reference to game's Player object for quick reference
    //(This Player will also be in the displayList)
    protected Player player; 
    

    //the numbers range of random nums that will spawn each entity
    // ex. if random num > 50 and < 90 --> spawn get

    public static  final int AVOID_MAX_CHANCE = 50;
    public static  final int GET_MAX_CHANCE = 90;
    public static  final int RARE_GET_MAX_CHANCE = 100;

    
    
    
    public BasicGame(){
        this(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }
    
    public BasicGame(int gameWidth, int gameHeight){
        super(gameWidth, gameHeight);
    }
    
    
    //Performs all of the initialization operations that need to be done before the game starts
    protected void pregame(){
        this.setBackgroundColor(Color.BLACK);
        player = new Player(STARTING_PLAYER_X, STARTING_PLAYER_Y);
        displayList.add(player); 
        score = 0;
        super.setSplashImage(INTRO_SPLASH_FILE); 
    }
    
    
    //Called on each game tick
    protected void updateGame(){
        //scroll all scrollable Entities on the game board
        scrollEntities();   
        //Spawn new entities only at a certain interval
        if (ticksElapsed % SPAWN_INTERVAL == 0){            
            spawnEntities();
        }
        //removes entities from displayList as soon as they go offscreen - like demo Game
        garbageCollectOffscreenEntities(); 
        //System.out.println(player.getY());
        for(int i = 0; i < displayList.size(); i++){
            Entity e = displayList.get(i);
            if(e instanceof Consumable){
                if(e.isCollidingWith(player)){
                    handlePlayerCollision((Consumable)e);
                    displayList.remove(e);
                    i--;
                }
            }
        }
        
        //Update the title text on the top of the window
        setTitleText("HP: " + player.getHP() + ", Score: " + score);        
    }
    
    
    protected void scrollEntities(){
        if(!isPaused){
            for (int i = 0; i < displayList.size(); i++){
                if(displayList.get(i) instanceof Scrollable){
                    Scrollable ent = (Scrollable)displayList.get(i); 
                    ent.scroll();
                    
                }
            } 
        }
    }
    
    
    //Handles "garbage collection" of the displayList
    //Removes entities from the displayList that have scrolled offscreen
    //(i.e. will no longer need to be drawn in the game window).
    protected void garbageCollectOffscreenEntities(){
        for(int i = 0; i < displayList.size(); i++){
            Entity e = displayList.get(i);
            if(e instanceof Scrollable){ 
                if(e.getX() + e.getWidth() < 0){ //if right edge is past 0, it is removed
                    displayList.remove(e);
                    i--; // Decrement i to account for the shift caused by removing an entity
                }
            }
        }
       
    }
    
    
    //Called whenever it has been determined that the Player collided with a consumable
    protected void handlePlayerCollision(Consumable collidedWith){ 
        player.setHP(player.getHP()+collidedWith.getDamageValue());
        score += collidedWith.getPointsValue();
    }


    protected void setEntityYCoordinates(Entity[] entities){
        boolean hasOverlap = true;
        while(hasOverlap){
            hasOverlap = false;
            
            //get random Y positions for entities
            for(int i = 0; i < entities.length; i++){
                int newY = rand.nextInt(0,getWindowHeight()-entities[i].getHeight());
                entities[i].setY(newY);
            }
            //check for overlap with existing entities
            for(int i = 0; i < entities.length; i++) {
                Entity newEnt = entities[i];

                for(int j = 0; j < entities.length; j++) {
                    if(i != j){
                        Entity existingEnt = entities[j];

                        if(newEnt.isCollidingWith(existingEnt)) {
                            hasOverlap = true;
                            break;
                        }
                    }
                }
                if(hasOverlap) {
                    break;
                }
            }
        }
    }
    
    
    //Spawn new Entities on the right edge of the game board
    protected void spawnEntities(){
        int numEntities = rand.nextInt(0,3)+1;
        int spawnX = getWindowWidth();
        Entity[] entities = new Entity[numEntities];
        for(int i = 0; i < numEntities; i++){
            int entityType = rand.nextInt(0,RARE_GET_MAX_CHANCE+1);
            if(entityType <= AVOID_MAX_CHANCE){ // add avoid
                entities[i] = new Avoid(spawnX,getWindowWidth());
            }
            if(entityType > AVOID_MAX_CHANCE && entityType <= GET_MAX_CHANCE){ // add get
                entities[i] = new Get(spawnX,getWindowWidth());
            }
            if(entityType > GET_MAX_CHANCE && entityType <= RARE_GET_MAX_CHANCE){ //add rare get
                entities[i] = new RareGet(spawnX,getWindowWidth());
            }
        }
        //check for overlap with each other
        setEntityYCoordinates(entities);
        for(Entity e: entities){
            displayList.add(e);
        }
    }




            
    
    
    
    //Called once the game is over, performs any end-of-game operations
    protected void postgame(){
        if (player.getHP() == 0){
            super.setTitleText("GAME OVER! - You Lose!");
        }
        else{
            super.setTitleText("GAME OVER! - You Won!");
        }
    }
    
    
    //Determines if the game is over or not
    //Game can be over due to either a win or lose state
    protected boolean isGameOver(){
        if(player.getHP() == 0 || score >= SCORE_TO_WIN){
            return true;
        }
        return false;
       
    }


    private void checkMovementKey(int key){
        if(key == UP_KEY && (player.getY()-player.getMovementSpeed()) > 0){ //checks if next movement will cause player to go offscreen
            player.setY(player.getY()-player.getMovementSpeed());
            return;
        }
        if(key == DOWN_KEY && (player.getY()+player.getMovementSpeed()) < getWindowHeight() - player.getHeight()){
            player.setY(player.getY()+player.getMovementSpeed());
            return;
        }
        if(key == RIGHT_KEY && (player.getX()+player.getMovementSpeed()) < getWindowWidth() - player.getHeight()){
            player.setX(player.getX()+player.getMovementSpeed());
            return;
        }
        if(key == LEFT_KEY && (player.getX()) > 0){
            player.setX(player.getX()-player.getMovementSpeed());
            return;
        }
    }

    
    //Reacts to a single key press on the keyboard
    protected void reactToKey(int key){
        
        setDebugText("Key Pressed!: " + KeyEvent.getKeyText(key) + ",  DisplayList size: " + displayList.size());
        
        //if a splash screen is active, only react to the "advance splash" key... nothing else!
        if (getSplashImage() != null){
            if (key == ADVANCE_SPLASH_KEY)
                super.setSplashImage(null);
            
            return;
        }

        // only move player if game is not paused and game is still going
        if(!isPaused && !isGameOver()){
            checkMovementKey(key);
        }
    
    
        if(key == KEY_PAUSE_GAME){ //p key to pause
            if(isPaused){
                isPaused = false;
            }
            else{
                isPaused = true;
            }
        }

        if(key == SPEED_DOWN_KEY && getGameSpeed() > 25){
            setGameSpeed(getGameSpeed()-25);
        }
        if(key == SPEED_UP_KEY && getGameSpeed() <= 175){
            setGameSpeed(getGameSpeed()+25);

        }
        
    }    
    
    
    //Handles reacting to a single mouse click in the game window
    protected MouseEvent reactToMouseClick(MouseEvent click){
        if (click != null){ //ensure a mouse click occurred
            int clickX = click.getX();
            int clickY = click.getY();
            setDebugText("Click at: " + clickX + ", " + clickY);
        }
        return click;//returns the mouse event for any child classes overriding this method
    }


    
    
    
    
}
