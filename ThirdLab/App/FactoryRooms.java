package ThirdLab.App;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Random;

public class FactoryRooms{

    static List<String> exits = new ArrayList<>();
    static Set<String> names = new HashSet<>();
    static String[] namesForRoom = new String[] {"Кладовая", "Винная", "Оружейная", "Склад", "Тренировочная", "Библиотека", "Сад"};

    static Random random = new Random();


    static int maxRoomsCreated = 7;
    static int hereWillBeExit;


    public static void createLabirint(){




        for (int i = 0; i < maxRoomsCreated; i++){
            Game_Objects.rooms.add(new Room(i));
            Game_Objects.rooms.get(i).name = namesForRoom[i];
            names.add(namesForRoom[i]);
        }


        //Определяем портал в рандомную комнату, кроме входа
        do {
            hereWillBeExit = random.nextInt(Game_Objects.rooms.size()-1);
        } while (hereWillBeExit > 1);
        for (int i = 1; i < Game_Objects.rooms.size(); i++){
            if (hereWillBeExit == Game_Objects.rooms.get(i).number){
                Game_Objects.rooms.get(i).portal = 1;
            }
        }

        // Соединяем комнаты выходами
        for (int i = 0; i < Game_Objects.rooms.size(); i++) {
            createRandomExitsInRoom(i);
        }



    }


    public static void createRandomExitsInRoom(int roomNumber){

            /*
            int nameOfRandomRoom;
            do {
                nameOfRandomRoom = random.nextInt(7);
            } while (nameOfRandomRoom  Game_Objects.rooms.get(roomNumber).number);
            Game_Objects.rooms.get(roomNumber).exits.add("Выход в комнату " + Game_Objects.rooms.get(nameOfRandomRoom).name);

            boolean isContains;
            if (Game_Objects.rooms.get(nameOfRandomRoom).exits.contains("Выход в комнату номер: "+ Game_Objects.rooms.get(roomNumber).number)){
                isContains = true;
            } else {
                isContains = false;
                Game_Objects.rooms.get(nameOfRandomRoom).exits.add("Выход в комнату номер: "+ Game_Objects.rooms.get(roomNumber).number);
            }


            /*
            int nameOfRandomRoom;
            do {
                nameOfRandomRoom = getRandomNumber();
            } while (nameOfRandomRoom != roomNumber);
            Game_Objects.rooms.get(roomNumber).exits.add("Выход в комнату номер: " + Game_Objects.rooms.get(nameOfRandomRoom).number);

            //Добавляем в созданной комнате выход обратно, проверяя нет ли в той конмате выхода к текущей для исключения 2 выходов в одну и ту же комнату


             */



        }











}
