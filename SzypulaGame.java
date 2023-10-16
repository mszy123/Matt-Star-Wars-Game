import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.event.*;
import java.awt.*;



public class SzypulaGame extends BasicGame {
    
    
    protected static final String INTRO_SPLASH_FILE = "assets/mandalorian_background.png";        
    protected static final String BACKGROUND_IMAGE_FILE = "assets/mandalorian_game_background.png";        
    protected static final String POSTGAME_IMAGE_FILE = "assets/postgame_back.png";        



    protected static final String SPLASH_MUSIC_FILE = "assets/starwars_theme.wav";
    protected static final String REGULAR_MUSIC_FILE = "assets/mandalorian_theme.wav";

    public static final int SHOOT_LASER_KEY = KeyEvent.VK_SPACE;//.... spacebar

    private Clip splashClip;
    private int lastLaserTick = 0;   
    private int lastLightsaberTick = 0;  

    MyPlayer player;


    protected static final int SCORE_TO_WIN = 100;

    //pixel that if stepped on by enemy would cause player to lose
    private int pixelToLose = 70;

    long startTime;



    //the numbers range of random nums that will spawn each entity
    // ex. if random num > 60 and < 80 --> spawn darktrooper
    public static final int STORMTROOPER_MAX_CHANCE = 60;
    public static final int DARKTROOPER_MAX_CHANCE = 80;
    public static final int LIGHTSABER_MAX_CHANCE = 90;
    public static final int MEDICAL_KIT_MAX_CHANCE = 100;


    
    


    public SzypulaGame(){
        super();
        setTitleText("Szypula Scrolling Game!");
    }

    @Override
    protected void pregame(){
        this.setBackgroundColor(Color.BLACK);
        this.setBackgroundImage(BACKGROUND_IMAGE_FILE);
        player = new MyPlayer(STARTING_PLAYER_X, STARTING_PLAYER_Y); //
        super.player = player;
        displayList.add(player); 
        score = 0;
        super.setSplashImage(INTRO_SPLASH_FILE); 
        playSplashMusic();
    }

    protected void playSplashMusic() {
        try {
            File splashMusicFile = new File(SPLASH_MUSIC_FILE);
            splashClip = AudioSystem.getClip();
            splashClip.open(AudioSystem.getAudioInputStream(splashMusicFile));
            splashClip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void playRegularMusic() {
        try {
            File splashMusicFile = new File(REGULAR_MUSIC_FILE);
            Clip regClip = AudioSystem.getClip();
            regClip.open(AudioSystem.getAudioInputStream(splashMusicFile));
            regClip.loop(Clip.LOOP_CONTINUOUSLY);
        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }


    protected boolean stopSplashMusic() {
        try {
            splashClip.stop();
            return true;
        } 
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    protected void reactToKey(int key){
        
        //if a splash screen is active, only react to the "advance splash" key... nothing else!
        if (getSplashImage() != null){
            if (key == ADVANCE_SPLASH_KEY){
                if(stopSplashMusic()){ //prevent music overlapping
                    playRegularMusic();
                }
                startTime = System.currentTimeMillis();
            }
        }

        if(!isPaused && key == SHOOT_LASER_KEY && !player.ifLightsaber() && ticksElapsed > lastLaserTick + Laser.getLaserDelay()){
            displayList.add(new Laser(player.getX()+60, player.getY()+40));
            lastLaserTick = ticksElapsed;
        }

        super.reactToKey(key);
    }

    @Override
    //Handles "garbage collection" of the displayList
    //Removes entities from the displayList that have scrolled offscreen
    //(i.e. will no longer need to be drawn in the game window).
    protected void garbageCollectOffscreenEntities(){
        for(int i = 0; i < displayList.size(); i++){
            Entity e = displayList.get(i);
            if(e instanceof Entity){ 
                if(e.getX() + e.getWidth() < 0 || e.getX() > DEFAULT_WIDTH){ //if right edge is past 0, it is removed
                    displayList.remove(e);
                    i--; // Decrement i to account for the shift caused by removing an entity
                }
            }
        }
       
    }


    protected void updateGame() {

        //scroll all scrollable Entities on the game board
        scrollEntities();   
        //Spawn new entities only at a certain interval
        if (ticksElapsed % SPAWN_INTERVAL == 0){            
            spawnEntities();
        }
        //removes entities from displayList as soon as they go offscreen - like demo Game
        garbageCollectOffscreenEntities(); 

        Queue<Entity> laserList = new LinkedList<>();
        for (int i = 0; i < displayList.size(); i++) {
            Entity e = displayList.get(i);
            if (e instanceof Laser) {
                laserList.add(e);
            }
            if (e instanceof TimerBar){
                ((TimerBar)e).newTick(lastLightsaberTick - ticksElapsed);
                if(e.getWidth() == 0){
                    displayList.remove(e);
                    i--;
                }
            }
            if(e instanceof Consumable){
                if(e.isCollidingWith(player)){ 
                    handlePlayerCollision((Consumable)e);
                    displayList.remove(e);
                    i--;
                }
            }
        }
    
        for (int i = 0; i < displayList.size(); i++) {
            Entity e = displayList.get(i);
            if (e instanceof Avoid) {
                Queue<Entity> laserCopy = new LinkedList<>(laserList); // create a copy of laserList
                while (!laserCopy.isEmpty()) {
                    Entity laser = laserCopy.poll();
                    if (e instanceof Shootable && e.isCollidingWith(laser)) {
                        handleEnemyDestroyed((Shootable)e);
                        displayList.remove(e);
                        displayList.remove(laser);
                        i-=2;
                        break; // exit loop after collision is detected
                    }
                }
            }
        }
        if(ticksElapsed == lastLightsaberTick){
            player.lostLightsaber();
        }
        //super.updateGame();
        //Update the title text on the top of the window
        setTitleText("HP: " + player.getHP() + ", Score: " + score);
    }

    @Override
    //Spawn new Entities on the right edge of the game board
    protected void spawnEntities(){ 
        int numEntities = rand.nextInt(0,2)+1;
        int spawnX = getWindowWidth();
        Entity[] entities = new Entity[numEntities];
        for(int i = 0; i < numEntities; i++){
            int entityType = rand.nextInt(0,MEDICAL_KIT_MAX_CHANCE+1);
            if(entityType <= STORMTROOPER_MAX_CHANCE){ // add stormtrooper
                entities[i] = new Stormtrooper(spawnX,getWindowWidth());
            }
             if( entityType > STORMTROOPER_MAX_CHANCE && entityType <= DARKTROOPER_MAX_CHANCE){ // add darktrooper
                 entities[i] = new Darktrooper(spawnX,getWindowWidth());
             }
            if(entityType > DARKTROOPER_MAX_CHANCE && entityType <= LIGHTSABER_MAX_CHANCE){ //add lightsaber
                entities[i] = new Lightsaber(spawnX,getWindowWidth());
            }
            if(entityType > LIGHTSABER_MAX_CHANCE && entityType <= MEDICAL_KIT_MAX_CHANCE){ //add medical kit
                entities[i] = new MedicalKit(spawnX,getWindowWidth());
            }
        }
        //check for overlap with each other
        super.setEntityYCoordinates(entities);
        for(Entity e: entities){
            displayList.add(e);
        }
    }

    @Override
    //Called whenever it has been determined that the Player collided with a consumable
    protected void handlePlayerCollision(Consumable collidedWith){
        if(player.ifLightsaber() && collidedWith instanceof Shootable){
            handleEnemyDestroyed((Shootable)collidedWith);
            return;
        }
        if(!((MyPlayer)player).ifLightsaber()){
            player.setHP(player.getHP()+collidedWith.getDamageValue());
        } 
        else if(collidedWith instanceof Get){
            player.setHP(player.getHP()+collidedWith.getDamageValue());
        }

        if(collidedWith instanceof Lightsaber && !player.ifLightsaber()){
            lastLightsaberTick = ticksElapsed + player.getLightsaberTime();
            player.gainedLightsaber();
            TimerBar saberTime = new TimerBar(player.getLightsaberTime());
            displayList.add(saberTime);
        }
        
        score += collidedWith.getPointsValue();
    }

    protected void handleEnemyDestroyed(Shootable shot){
        score += shot.getPointsValue();
    }


    private boolean checkIfPastLine(){
        for(Entity ent:displayList){
            if(ent instanceof Avoid && ent.getX() < pixelToLose){
                return true;
            }
        }
        return false;
    }

    //Determines if the game is over or not
    //Game can be over due to either a win or lose state
    protected boolean isGameOver(){

        if(player.getHP() == 0 || score >= SCORE_TO_WIN || checkIfPastLine()){
            return true;
        }
        return false;
       
    }

    //Called once the game is over, performs any end-of-game operations
    protected void postgame(){
        if (player.getHP() == 0 || checkIfPastLine()){
            super.setTitleText("GAME OVER! - You Lose!");
        }
        else{
            super.setTitleText("GAME OVER! - You Won!");
        }
        long endTime = System.currentTimeMillis() - startTime;
        int elapsedSeconds = (int) (endTime / 1000);
        wait(1000);// Wait for 1 second (1000 milliseconds)
        displayList.clear();
        this.setBackgroundImage(POSTGAME_IMAGE_FILE);
        String strTime = String.valueOf(elapsedSeconds);
        for(int x = 250, i = 0; i < strTime.length(); i++, x += 35){
            displayList.add(new TimeText(x, Integer.parseInt(strTime.charAt(i)+"")));
            strTime = strTime.substring(i, strTime.length());
        }


    }

    public static void wait(int time){
        try {
            Thread.sleep(time); 
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }

    
    
}
