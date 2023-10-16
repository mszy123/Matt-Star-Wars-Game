// Text that is used to show time on postgame screen

import java.util.HashMap;
import java.util.Map;

public class TimeText extends Entity {
    //Location of image file to be drawn for an the text shown on postgame screen

    private static final int TEXT_WIDTH = 50;
    private static final int TEXT_HEIGHT = 50;
    public static final Map<Integer, String> NUM_IMAGE_FILES = new HashMap<Integer, String>() {{ //had to look up how to do this
        put(0, "assets/numbers/0.png");
        put(1, "assets/numbers/1.png");
        put(2, "assets/numbers/2.png");
        put(3, "assets/numbers/3.png");
        put(4, "assets/numbers/4.png");
        put(5, "assets/numbers/5.png");
        put(6, "assets/numbers/6.png");
        put(7, "assets/numbers/7.png");
        put(8, "assets/numbers/8.png");
        put(9, "assets/numbers/9.png");
    }};


    

    public TimeText(int x, int num){
        super(x, 60, TEXT_WIDTH, TEXT_HEIGHT, NUM_IMAGE_FILES.get(num));
    }

    


}

