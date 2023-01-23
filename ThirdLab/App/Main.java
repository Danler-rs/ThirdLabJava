package ThirdLab.App;

import java.util.Arrays;

public class Main {
    static Game_Logic game_logic = new Game_Logic();

    public static void main(String[] args) {
        //Создаем врагов и предметы
        Game_Objects.initMonstersArray();
        Game_Objects.initItemArray();

        while (true){
            gameLoop();
        }
    }
    public static void gameLoop(){
        game_logic.waitForCommand();
    }
}
