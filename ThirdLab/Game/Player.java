package ThirdLab.Game;

public class Player extends Character {
    public int numAtkUpgrades, numDefUpgrades;

    int gold, restsLefts, pots;

    public String[] atkUpgrades = {"Сила", "Энергия Ци", "Воля", "Священные силы"};
    public String[] defUpgrades = {"Прочные кости", "Каменная кожа", "Чешуйчатая броня", "Аура"};



    public Player(String name) {
        super(name, 100, 0);
        this.numAtkUpgrades = 0;
        this.numDefUpgrades = 0;
        this.gold = 5;
        this.restsLefts = 1;
        this.pots = 0;

        //При создании даём выбор особенности
        chooseTrait();

    }

    public void chooseTrait() {
        GameLogic.clearConsole();
        GameLogic.printHeading("Выбери особенность персонажа: ");
        System.out.println("(1) " + atkUpgrades[numAtkUpgrades]);
        System.out.println("(2) " + defUpgrades[numDefUpgrades]);
        //Получаем выбор
        int input = GameLogic.readInt("-> ", 2);
        GameLogic.clearConsole();
        //Разбираем выбор
        if (input == 1){
            GameLogic.printHeading("Вы выбрали " + atkUpgrades[numAtkUpgrades] + "!");
            numAtkUpgrades++;
        }else {
            GameLogic.printHeading("Вы выбрали " + defUpgrades[numDefUpgrades] + "!");
            numDefUpgrades++;
        }
        GameLogic.anythingToContinue();
    }




    @Override
    public int attack() {
        return (int) (Math.random()*(xp/4 + numAtkUpgrades*3+3) + xp/10 + numAtkUpgrades * 2 + numDefUpgrades + 1);
    }

    @Override
    public int defend() {
        return (int) (Math.random() * (xp/4 + numDefUpgrades*3+3) + xp/10 + numDefUpgrades * 2 + numAtkUpgrades +1);
    }
}
