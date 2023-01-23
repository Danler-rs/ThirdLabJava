package ThirdLab.App.Items;

public class Iron_Dagger extends Item{
    int accuracy = 20;
    int damage = 7;
    public Iron_Dagger(){
        id = "Железный_Кинжал";
        name = "Железный кинжал";
        info = "Железный кинжал валяется неподалёку\nУрон: " + damage + "\nТочность: " + accuracy;
        isWearable = true;
        wearLocation = "wield"; //пока просто владеем, потом в руке, на голове и тд
    }

}
