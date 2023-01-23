package ThirdLab.Game;

public abstract class Character {
    //Атрибуты всех персонажей
    public String name;
    public int maxHp, hp, xp;

    public Character(String name, int maxHp, int xp) {
        this.name = name;
        this.maxHp = maxHp;
        this.xp = xp;
        this.hp = maxHp;
    }

    //Методы всех персов
    public abstract int attack();
    public abstract int defend();
}
