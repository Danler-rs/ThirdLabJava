package ThirdLab.App;

import ThirdLab.App.Items.Item;

import java.util.ArrayList;

public class Hero{
    String name;
    int maxHp, xp, hp;
    int accuracy;
    int inRoom = -1;

    ArrayList<Item> items = new ArrayList<>();
    ArrayList<Item> wornItems = new ArrayList<>();




    public int attack() {
        return 0;
    }
    public int defend() {
        return 0;
    }
    public void lookSelf(){
        System.out.println("hp: " + hp);
        System.out.println("accuracy: " + accuracy + "%");


    }

    public void removeItem(String[] words){
        //Проходим по всем носимым предметам
        for (int i = 0; i < wornItems.size(); i++){
            //Если носимый предмет совпал по id со 2 словом
            //То убираем его в инвентарь
            if (wornItems.get(i).id.equalsIgnoreCase(words[1])){
                System.out.println("Вы сняли " + wornItems.get(i).id);
                items.add(wornItems.get(i));
                wornItems.remove(i);
            }
        }
    }

    //Показать что носим
    public void equipment(){
        System.out.println("При себе у меня есть: ");
        for (int i = 0; i < wornItems.size(); i++){
            System.out.println(wornItems.get(i).name + ":" + wornItems.get(i).wearLocation);
        }

    }

    public void wear(String[] words){
        //Если ничего не носим
        if (wornItems.size() == 0){
            //Проходим по всем предметам в "инвенторе"
            for (int i = 0; i < items.size(); i++){
                //Если второе слово совпало с тем что в инвенторе и его можно носить
                //То мы добавляем его в носимые вещи и убираем из "инвенторя"
                if (words[1].equalsIgnoreCase(items.get(i).id) && items.get(i).isWearable){
                    wornItems.add(items.get(i));
                    System.out.println("Вы надели " + items.get(i).name);
                    items.remove(i);
                    break;
                }
            }
            //Если встретили вещь в "носимых"
        }else {
            boolean isWearing = false;
            //Проходим по всем носимым предметам и предметам в инвенторе
            for (int i = 0; i < wornItems.size(); i++){
                for (int z = 0; z < items.size(); z++){
                    //Если по id и по месту ношения предметы совпали то пишем что надеть не можем
                    if (words[1].equalsIgnoreCase(items.get(z).id)&& items.get(z).isWearable){
                        if (items.get(z).wearLocation.equalsIgnoreCase(wornItems.get(i).wearLocation)){
                            System.out.println("Вы уже что-то носите на этом месте");
                            isWearing = true;
                        }
                    }
                }
                //Иначе надеваем предмет из инвенторя
                if (!isWearing){
                    for (int y = 0; y < items.size(); y++){
                        if (words[1].equalsIgnoreCase(items.get(y).id) && items.get(y).isWearable){
                            wornItems.add(items.get(y));
                            System.out.println("Вы надели " + items.get(y).name);
                            items.remove(y);
                            break;
                        }
                    }

                }
            }

        }
    }

}
