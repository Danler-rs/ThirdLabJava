package ThirdLab.App;

import java.util.Arrays;
import java.util.Scanner;

import ThirdLab.App.Items.Item;
import ThirdLab.App.Monsters.*;

public class Game_Logic {
    static Scanner scanner = new Scanner(System.in);
    static RandNum randNum = new RandNum();


    public Game_Logic(){
        //Game_Objects.rooms.get(0).exits.add("Выход в комнату номер: " + ((randNum.returnRandomNumber(3)) + 1));
        FactoryRooms.createLabirint();
        /*
        Game_Objects.rooms.add(new Room(1));
        Game_Objects.rooms.get(0).name = "Библиотека";
        */
        /*
        Game_Objects.rooms.get(0).info.add("Описание 1");
        Game_Objects.rooms.get(0).info.add("Описание 2");
        Game_Objects.rooms.get(0).info.add("Описание 3");
        Game_Objects.rooms.get(0).info.add("Описание 4");
        Game_Objects.rooms.get(0).exits.add("Юг 2");
        Game_Objects.rooms.get(0).exits.add("Север 3");

         */
    }

    public void waitForCommand(){
        if (Game_Objects.hero.inRoom==-1){
            printIntro();
            createCharacter();
        }else {
            //Что можем сделать в комнате
            printAvailableCommands();
            printSeparator(20);
            Game_Objects.printNumberOfRoom();

            printSeparator(30);
            System.out.println("Текущие выходы");
            Game_Objects.printExitsInRoom();
            printSeparator(30);
            Game_Objects.printWhatCanYouCreate();

            System.out.println("Выберите действие:");
            String command = scanner.nextLine();
            clearConsole();

            String[] words = command.split(" ");
            processCommand(words);
        }
    }

    private void createCharacter() {
        System.out.println("Добро пожаловать в игру. Как вас зовут?");

        Game_Objects.hero.name = scanner.next();
        Game_Objects.hero.hp = 100;
        Game_Objects.hero.accuracy = 75;
        Game_Objects.hero.inRoom = 0;
    }

    private void processCommand(String[] words) {
        if (words[0].equalsIgnoreCase("Осмотреться") || words[0].equalsIgnoreCase("Осмотреть")){
            look(words);
        }
        if (words[0].equalsIgnoreCase("Призвать")){
            summon(words);
        }

        if (words[0].equalsIgnoreCase("Создать")){
            createItem(words);
        }

        if (words[0].equalsIgnoreCase("Взять")){
            get(words);
        }

        if (words[0].equalsIgnoreCase("Одеть")){
            Game_Objects.hero.wear(words);
        }

        if (words[0].equalsIgnoreCase("Снять")){
            Game_Objects.hero.removeItem(words);
        }

        if (words[0].equalsIgnoreCase("Идти")){
            move(words);
        }

    }

    private void summon(String[] words) {
        if (words.length == 1){
            System.out.println("Кого хотите призвать?");
        }
        if (words.length == 2){

            //Проходим по всему списку монстров
            for (int i = 0; i < Game_Objects.MonstersDataBase.size(); i++ ){
                //Создаем местного монстра и помещаем в него монстра из списка
                Monster localMonster = (Monster) Game_Objects.MonstersDataBase.get(i);

                //Если id монстра совпадает с тем, что ввел пользователь то смотрим все комнаты и сравниваем с комнатой где находится гг.
                //Если совпал номер то создаем локального монстра в той же комнате, где и есть гг
                if (localMonster.id.equalsIgnoreCase(words[1])){
                    for (int y = 0; y < Game_Objects.rooms.size(); y++){
                        if (Game_Objects.rooms.get(y).number == Game_Objects.hero.inRoom){
                            Game_Objects.rooms.get(y).monsters.add(localMonster);
                            //Печатаем последнего добавленного монстра ( -1 для последнего)
                            System.out.println("Вы призвали " + Game_Objects.rooms.get(y).monsters
                                    .get(Game_Objects.rooms.get(y).monsters.size()-1).name);
                        }
                    }
                }
            }
        }
    }

    private void look(String[] words) {
        //Если ввели только одно слово
        if (words.length == 1){
            printSeparator(20);
            for (int i = 0; i < Game_Objects.rooms.size(); i++){

                //Проверяем наличие игрока в комнате и показываем название комнаты
                if (Game_Objects.rooms.get(i).number == Game_Objects.hero.inRoom){
                    System.out.println("Вы сейчас находитесь в комнате под названием: " + Game_Objects.rooms.get(i).name);
                    System.out.println("Номер комнаты: " + Game_Objects.rooms.get(i).number);
                    //Печатаем описание
                    for (int y = 0; y < Game_Objects.rooms.get(i).info.size(); y++){
                        System.out.println(Game_Objects.rooms.get(i).info.get(y));
                    }

                    //Печатаем выходы
                    System.out.println("\nВыходы:");
                    Game_Objects.printExitsInRoom();

                    //Печатаем описание всех монстров в комнате
                    for (int y = 0; y < Game_Objects.rooms.get(i).monsters.size(); y++){
                        System.out.println(Game_Objects.rooms.get(i).monsters.get(y).info);
                    }

                    //Печатаем описание всех предметов в комнате
                    for (int y = 0; y < Game_Objects.rooms.get(i).items.size(); y++){
                        System.out.println(Game_Objects.rooms.get(i).items.get(y).info);
                    }

                    printSeparator(20);
                }
            }
        }
        //если ввели два слова
        if (words.length == 2){

            //если второе слово == "себя", то осматриваем себя.
            if (words[1].equalsIgnoreCase("себя")){
                printSeparator(20);
                Game_Objects.hero.lookSelf();
                Game_Objects.hero.equipment();
                printSeparator(20);
            }

            //Проходим по всем комнатам
            for (int y = 0; y < Game_Objects.rooms.size(); y++){
                //Если комнаната совпала с комнатой героя
                //И второе слово совпало с id монстра в комнате
                //Печатается инфа о монстре
                if (Game_Objects.rooms.get(y).number == Game_Objects.hero.inRoom){
                    for (int i = 0; i < Game_Objects.rooms.get(y).monsters.size(); i++){
                        if (words[1].equalsIgnoreCase(Game_Objects.rooms.get(y).monsters.get(i).id)){
                            Game_Objects.rooms.get(y).monsters.get(i).lookMonster();
                        }
                    }
                }
            }


        }
    }

    public void createItem(String[] words){
        if (words.length == 1){
            System.out.println("Что именно вы хотите создать?");
        }
        if (words.length == 2){
            //Проходим по всей базе данных предметов
            for (int i =0; i < Game_Objects.ItemDataBase.size(); i++){

                //Создаем местный предмет
                Item localItem = (Item) Game_Objects.ItemDataBase.get(i);
                //Если id местного предмета совпал с вторым словом
                if (localItem.id.equalsIgnoreCase(words[1])){

                    //То проходим по всем комнатам и если комната совпала с текущей где находится гг
                    //То создаем предмет
                    for (int y = 0; y < Game_Objects.rooms.size(); y++ ){
                        if (Game_Objects.rooms.get(y).number == Game_Objects.hero.inRoom){
                            Game_Objects.rooms.get(y).items.add(localItem);
                            System.out.println("Вы создали " + Game_Objects.rooms.get(y).items
                                    .get(Game_Objects.rooms.get(y).items.size()- 1).name);
                        }
                    }
                }
            }
        }
    }


    public void get(String[] words){
        if (words.length == 1){
            System.out.println("Что именно вы хотите взять?");
        }
        if (words.length ==2 ){
            //Проходим по базе данных предметов
            for (int i = 0; i < Game_Objects.ItemDataBase.size(); i++){

                //Проходим по всем комнатам
                for (int y = 0; y < Game_Objects.rooms.size(); y++){
                    //Если комната совпала с текущей где находится гг
                    if (Game_Objects.rooms.get(y).number == Game_Objects.hero.inRoom){

                        //То проходимся по всем предметам в комнате
                        for (int z = 0; z < Game_Objects.rooms.get(y).items.size(); z++){
                            //Если второе слово из команды совпало с id предмета
                            if (words[1].equalsIgnoreCase(Game_Objects.rooms.get(y).items.get(z).id)){

                                //То создаем местный предмет и помещаем в него предмет из комнаты
                                //И добавляем к игроку, после удаляя его из комнаты
                                Item localItem = Game_Objects.rooms.get(y).items.get(z);

                                Game_Objects.hero.items.add(localItem);
                                System.out.println("Вы подобрали " + localItem.name);
                                Game_Objects.rooms.get(y).items.remove(z);
                                break;
                            }
                        }
                    }
                }
            }
        }
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

    public static void printIntro(){
        printSeparator(30);
        System.out.println("ПРЕДЫСТОРИЯ");
        printSeparator(30);
        System.out.println("Ты последний выживший член королевской семьи после атаки сил зла на твой замок.");
        System.out.println("Умирая, король поручил тебе исправить ошибки и отправил в катакомбы под замком\nчтобы отправить через портал в прошлое и изменить судьбу королевства");
        System.out.println("Теперь, нужно лишь добраться до портала и не помереть по поти к нему");
    }


    public static void printAvailableCommands(){
        System.out.println("Вы можете:");
        System.out.println("(1) Осмотреться");
        System.out.println("(2) Осмотерть себя");
        System.out.println("(3) Призвать (название монстра в И.П)");
        System.out.println("(4) Создать (название предмета в И.П)");
        System.out.println("(5) Взять предмет, если таковой имеется в комнате");
        System.out.println("(6) Одеть (название предмета в И.П)");
        System.out.println("(7) Снять (название предмета в И.П)");
        System.out.println("(8) Идти (выход + номер (пример: идти Винная 2))");
    }


    public void move(String[] words){
        if (words.length == 1){
            System.out.println("Идти куда?");
        }

        if (words.length > 1){
            for (int i = 0; i < Game_Objects.rooms.size(); i++){
                System.out.println(i+ " " + Game_Objects.rooms.get(i).number + " "+ Game_Objects.rooms.get(i).exits);
                /*if (Game_Objects.rooms.get(i).number == Game_Objects.hero.inRoom){
                    System.out.println("sdfsd");
                    System.out.println(Game_Objects.rooms.get(i).exits);
                    for (int y = 0; y < Game_Objects.rooms.get(i).exits.size(); y++){
                        System.out.println("start");
                    }
                }

                 */
            }
        }
    }


}
