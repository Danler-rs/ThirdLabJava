package ThirdLab.App;

import java.util.Random;

public class RandNum {
    public int returnRandomNumber(int x){
        Random rand = new Random();
        int y = rand.nextInt(x);
        if (y == 0){
            y++;
        }
        return y;
    }

}
