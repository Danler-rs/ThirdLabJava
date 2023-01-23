package ThirdLab.App;

import ThirdLab.App.Items.Item;
import ThirdLab.App.Monsters.Monster;

import java.util.ArrayList;
import java.util.List;

public class Room {
    int number;
    int portal;
    String name;
    List<String> info = new ArrayList<>();
    List<String> exits = new ArrayList<>();
    List<Monster> monsters = new ArrayList<>();

    ArrayList<Item> items = new ArrayList<>();

    public Room(int number) {
        this.number = number;
    }
}
