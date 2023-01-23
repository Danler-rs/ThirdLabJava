package ThirdLab.App;

import java.util.SortedMap;

public class Combat {
    public void attack(String[] words){
        //Проходим по комнатам
        for (int i = 0; i < Game_Objects.rooms.size(); i++){
            //Если комната совпала с той где находится гг
            if (Game_Objects.rooms.get(i).number == Game_Objects.hero.inRoom){
                //Проходимся по монстрам
                for (int y = 0; y < Game_Objects.rooms.get(i).monsters.size(); y++){

                    //Если монстр совпал по id с 2 словом
                    if (Game_Objects.rooms.get(i).monsters.get(y).id.equalsIgnoreCase(words[1])){

                        //Устанавливаем точность
                        int monsterHit = Game_Objects.randNum.returnRandomNumber(100);
                        monsterHit = monsterHit + (Game_Objects.rooms.get(i).monsters.get(y).accuracy / 2);


                        if (monsterHit > 50){
                            //Устанавливаем и наносим герою урон
                            int monsterDamage = Game_Objects.randNum.returnRandomNumber(10);
                            Game_Objects.hero.hp = Game_Objects.hero.hp - monsterDamage;
                            System.out.println(Game_Objects.rooms.get(i).monsters.get(y).name + " нанёс вам " + monsterDamage + " ед. урона");
                        }else {
                            System.out.println(Game_Objects.rooms.get(i).monsters.get(y).name + " промахнулся!");
                        }

                        //Устанавливаем точность
                        int heroHit = Game_Objects.randNum.returnRandomNumber(100);
                        heroHit = monsterHit + (Game_Objects.rooms.get(i).monsters.get(y).accuracy/2);


                        if (heroHit > 50){

                            //Устанавливаем и наносим монстру урон
                            int heroDamage = Game_Objects.randNum.returnRandomNumber(10);
                            Game_Objects.rooms.get(i).monsters.get(y).hp = Game_Objects.rooms.get(i).monsters.get(y).hp - heroDamage;
                            System.out.println("Вы нанесли " + heroDamage + " " + Game_Objects.rooms.get(i).monsters.get(i).name);
                            if (Game_Objects.rooms.get(i).monsters.get(y).hp <= 0){
                                monsterDeath(i,y);
                            }
                        }else {
                            System.out.println("Вы промахнулись!");
                        }



                    }

                }
            }
        }
    }

    private void monsterDeath(int i, int y) {
        System.out.println(Game_Objects.rooms.get(i).monsters.get(y).name + " умер");
        Game_Objects.rooms.get(i).monsters.remove(y);
    }
}
