package ThirdLab.App.Monsters;

public class Monster {
    public String name;
    public String id = "Enemy";
    public String info;
    public int hp;
    public int accuracy;

    public void lookMonster(){
        System.out.println(name);
        System.out.println("Точность: " + accuracy);
        System.out.println("Hp: " + hp);
    }

}
