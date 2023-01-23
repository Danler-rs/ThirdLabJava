package ThirdLab.App;

import ThirdLab.App.Items.Golden_Ring;
import ThirdLab.App.Items.Iron_Dagger;
import ThirdLab.App.Items.Item;
import ThirdLab.App.Monsters.Monster;
import ThirdLab.App.Monsters.Rat;
import ThirdLab.App.Monsters.Troll;

import java.util.ArrayList;
import java.util.List;

public class Game_Objects {
    static Hero hero = new Hero();
    static ArrayList<Room> rooms = new ArrayList<>();

    static List<Object> MonstersDataBase = new ArrayList<>();
    static List<Object> ItemDataBase = new ArrayList<>();
    static Combat combat = new Combat();
    static RandNum randNum = new RandNum();

    public static void initMonstersArray(){
        MonstersDataBase.add(new Monster());
        MonstersDataBase.add(new Troll());
        MonstersDataBase.add(new Rat());
    }

    public static void initItemArray(){
        ItemDataBase.add(new Item());
        ItemDataBase.add(new Iron_Dagger());
        ItemDataBase.add(new Golden_Ring());
    }

    public static void printNumberOfRoom(){
        String curRooms = "Комната: ";
        for (int i = 0; i < rooms.size(); i++){
            System.out.println(curRooms + rooms.get(i).number);
        }
    }

    public static void printExitsInRoom(){
        int curNumberOfRoom = Game_Objects.hero.inRoom;
        System.out.println(rooms.get(curNumberOfRoom).exits);

    }

    public static void printWhatCanYouCreate(){
        System.out.println("Вы можете призвать этих существ");
        System.out.println("Крыса");
        System.out.println("Троль");


        Game_Logic.printSeparator(30);
        System.out.println("Вы можете создать эти предметы");
        System.out.println("Железный_кинжал");
        System.out.println("Золотое_кольцо");

        Game_Logic.printSeparator(30);
    }

}
