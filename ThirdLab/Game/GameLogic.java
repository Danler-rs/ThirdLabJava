package ThirdLab.Game;

import java.util.Scanner;

public class GameLogic {
    static Scanner scanner = new Scanner(System.in);

    static Player player;
    public static boolean isRunning;

    //Случайные встречи
    public static String[] encounters = {"Битва", "Битва", "Битва", "Отдых", "Отдых"};
    public static String[] enemies = {"Огр", "Огр", "Гоблин", "Гоблин", "Элементаль"};




    //История
    public static int place = 0, act= 1;
    public static String[] places = {"Комната с врагом", "Комната с лутом", "Комната с событием"};

    //Метод для получения от пользователя выбора
    public static int readInt(String tip, int userChoices){
        int input;
        do {
            System.out.println(tip);
            try {
                input = Integer.parseInt(scanner.next());
            } catch (Exception e){
                input = -1;
                System.out.println("Пожалуйста, введите число");
            }
        }while (input < 1 || input > userChoices);

        return input;
    }

    //Чистит консоль
    public  static  void clearConsole(){
        for (int i = 0; i < 100; i++){
            System.out.println();
        }
    }


    //Метод печатает разделитель n-ной длины
    public static void printSeparator(int n){
        for (int i = 0; i < n; i++){
            System.out.print("-");
        }
        System.out.println();
    }


    //Метод печатает заголовок
    public static void printHeading(String title){
        printSeparator(30);
        System.out.println(title);
        printSeparator(30);
    }

    public static void anythingToContinue(){
        System.out.println("\nВведите что-нибудь для продолжения...");
        scanner.next();
    }

    public static void startGame(){
        boolean nameSet = false;
        String name;
        clearConsole();
        printSeparator(40);
        printSeparator(30);
        System.out.println("СПУСК В КАТАКОМБЫ");
        System.out.println("ТЕКСТОВАЯ ИГРА");
        printSeparator(30);
        printSeparator(40);
        anythingToContinue();

        //Получаем имя игрока
        do {
            clearConsole();
            printHeading("Как вас зовут?");
            name = scanner.next();
            //Спрашиваем верно ли имя?
            clearConsole();
            printHeading("Ваше имя " + name + ".\nВсё правильно?");
            System.out.println("(1) Да!");
            System.out.println("(2) Нет, я бы хотел поменять мое имя.");
            int input = readInt("-> ", 2);
            if (input == 1){
                nameSet = true;
            }
        }while (!nameSet);


        //Создаём объект Игрок с именем
        player = new Player(name);

        //Устанавливаем isRunning тру, чтобы цикл игры продолжался
        isRunning = true;

        //Начинаем цикл игры
        gameLoop();
    }

    private static void checkAct() {
        if (player.xp >= 10 && act == 1){
            act =2;
            place =1;
            Story.printIntro();
            player.chooseTrait();
            enemies[0] = "Гоблин";
            enemies[1] = "Огр";
            enemies[2] = "Элементаль";
            enemies[3] = "Бандит";
            enemies[4] = "Волк";

            encounters[0] = "Битва";
            encounters[1] = "Битва";
            encounters[2] = "Битва";
            encounters[3] = "Отдых";
            encounters[4] = "Магаз";
            player.hp = player.maxHp;
        }

    }

    private static void randomEncounter() {
        int encounter = (int) (Math.random() * encounters.length);

        if (encounters[encounter].equals("Битва")){
            randomBattle();
        } else if (encounters[encounter].equals("Отдых")) {
            takeRest();
        }else {
            shop();
        }
    }

    public static void shop(){
        clearConsole();
        printHeading("Вы встретили странного незнакомца.\nОн предлагает вам что-то:");
        int price = (int) (Math.random()*(10 + player.pots*3) + 10 + player.pots);
        System.out.println("- Магическое зелье: " + price + " золото(а)");
        printSeparator(20);
        //Спрашиваем хочет ли игрок купить?
        System.out.println("Хочешь купить одно?\n(1) Да\n(2) Нет, спасибо.");
        int input = readInt("-> ", 2);
        //проверка
        if (input == 1){
            clearConsole();
            //проверка золота
            if (player.gold >= price){
                printHeading("Вы купили магическое зелье за " + price + " золота");
                player.pots++;
                player.gold -= price;
            }else {
                printHeading("У вас не достаточно золота для покупки!");
            }
            anythingToContinue();
        }
    }

    public static void takeRest(){
        clearConsole();
        if (player.restsLefts >= 1){
            printHeading("Вы хотите отдохнуть? (" + player.restsLefts + " возможностей(и) отдохнуть осталось).");
            System.out.println("(1) Да\n(2) Нет");
            int input = readInt("-> ", 2);
            if(input ==1){
                //Отдыхаем
                clearConsole();
                if (player.hp < player.maxHp){
                    int hpRestored = (int) (Math.random()* (player.xp/4 + 1) + 10);
                    player.hp += hpRestored;
                    if (player.hp > player.maxHp){
                        player.hp = player.maxHp;
                    }
                    System.out.println("Вы отдохнули и восстановили " + hpRestored + " очков здоровья");
                    System.out.println("Теперь у вас " + player.hp + "/" + player.maxHp + " очков здоровья");
                    player.restsLefts--;
                } else {
                    System.out.println("У вас полнове здоровье. Вам  не нужен отдых");
                }
                anythingToContinue();
            } else if (input == 2){
                System.out.println("Вы решили не отдыхать");
                anythingToContinue();
            }
        }
    }


    //Метод для продолжения игры
    public static void continueJourney(){
        checkAct();
        if (act != 4){
            randomEncounter();
        }
    }



    //Печатает инфу о персонаже игрока
    public static void characterInfo(){
        clearConsole();
        printHeading("ИНОФРМАЦИЯ О ПЕРСОНАЖЕ");
        System.out.println(player.name + "\tHP: " + player.hp + "/"+ player.maxHp);
        printSeparator(20);
        System.out.println("XP: " + player.xp + "\nЗолото: " + player.gold);
        printSeparator(20);
        System.out.println("Зелья: " + player.pots);
        printSeparator(20);

        //Печатаем выбранные особенности
        if (player.numAtkUpgrades > 0){
            System.out.println("Атакующие особенности: " + player.atkUpgrades[player.numAtkUpgrades-1]);
            printSeparator(20);
        }
        if (player.numDefUpgrades > 0){
            System.out.println("Защитные особенности: " + player.defUpgrades[player.numDefUpgrades-1]);
        }
        anythingToContinue();
    }


    //Печатает меню
    public static void printMenu(){
        clearConsole();
        printHeading(places[place]);
        System.out.println("Выберите действие: ");
        printSeparator(20);
        System.out.println("(1) Продолжить путешествие");
        System.out.println("(2) Информация персонажа");
        System.out.println("(3) Выйти из игры");
    }

    //создание битвы
    public static void randomBattle(){
        clearConsole();
        printHeading("Вы встретили злое сущесство! Вам придется сражаться с ним!");
        anythingToContinue();
        battle(new Enemy(enemies[(int) (Math.random()*enemies.length)], player.xp));

    }

    private static void battle(Enemy enemy) {
        while (true){
            clearConsole();
            printHeading(enemy.name + "\nHP: " + enemy.hp + "/" + enemy.maxHp);
            printHeading(player.name + "\nHP: " + player.hp + "/" + player.maxHp);
            System.out.println("Выберите действие: ");
            printSeparator(20);
            System.out.println("(1) Битва\n(2) Использовать Зелье\n(3) Убежать");
            int input = readInt("-> ", 3);
            //реакция от выбора игрока
            if (input == 1){
                //Битва
                //Высчитываем дмг и принятый дмг
                int dmg = player.attack() - enemy.defend();
                int dmgTook = enemy.attack() - player.defend();
                //Проверяем что дмг не минусовой
                if(dmgTook < 0){
                    //добавляем дмг если игрок хорошо дефается
                    dmg -= dmgTook/2;
                    dmgTook = 0;
                }
                if (dmg < 0){
                    dmg = 0;
                }
                //наносим урон двоим
                player.hp -= dmgTook;
                enemy.hp -= dmg;
                //выводим инфо о битве
                clearConsole();
                printHeading("БИТВА");
                System.out.println("Вы нанесли " + dmg + " урона врагу " + enemy.name);
                printSeparator(15);
                System.out.println(enemy.name + " нанёс " + dmgTook + " урона вам");
                anythingToContinue();
                //проверяем жив ли герой
                if (player.hp <= 0){
                    playerDied();
                    break;
                } else if (enemy.hp <= 0) {
                    //герой победил
                    clearConsole();
                    printHeading("Вы победили " + enemy.name);
                    //увеличиваем опыт
                    player.xp += enemy.xp;
                    System.out.println("Вы получили " + enemy.xp + " XP!");
                    anythingToContinue();
                    boolean addRest = (Math.random()*5 + 1 <= 2.25);
                    int goldEarned = (int) (Math.random()*enemy.xp);
                    if (addRest){
                        player.restsLefts++;
                        System.out.println("Вы получили шанс на дополнительный отдых!");
                    }
                    if (goldEarned > 0){
                        player.gold += goldEarned;
                        System.out.println("Вы собрали " + goldEarned + " золота с монста");
                    }
                    anythingToContinue();
                    break;
                }


            } else if (input == 2) {
                //Используем зелья
                clearConsole();
                if(player.pots > 0 && player.hp < player.maxHp){
                    printHeading("Хотите ли вы использовать зелье? (" + player.pots + " left).");
                    System.out.println("(1) Да\n(2) Нет");
                    input = readInt("-> ", 2);
                    if (input ==1){
                        player.hp = player.maxHp;
                        clearConsole();
                        printHeading("Вы выпили магическое зелье. Оно полностью восстановило вам здоровье!");
                        anythingToContinue();
                    }

                }else{
                    printHeading("У вас нет зелий или у вас полное здоровье");
                    anythingToContinue();
                }

            }else {
                //Убегаем
                clearConsole();
                //шансы на побег
                if (Math.random()*10 + 1 <= 3.5){
                    printHeading("Вы сбежали");
                    anythingToContinue();
                    break;
                }else {
                    printHeading("Сбежать не удалось");
                    int dmgTook = enemy.attack();
                    System.out.println("Вы получили " + dmgTook + " урона!");
                    player.hp -= dmgTook;
                    //проверяем жив ли герой
                    if (player.hp <= 0){
                        playerDied();
                    }
                }
            }
        }
    }

    private static void playerDied() {
        clearConsole();
        printHeading("Вы умерли");
        printHeading("Игра закончилась");
        isRunning = false;
    }

    private static void gameLoop() {
        while (isRunning){
            printMenu();
            int input = readInt("-> ", 3);
            if (input==1){
                continueJourney();
            } else if (input == 2) {
                characterInfo();
            } else {
                isRunning = false;
            }
        }
    }


}
