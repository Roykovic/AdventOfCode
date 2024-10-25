package nl.roykovic.aoc._2015.day21_bossfight;

import nl.roykovic.aoc._2022.day5_supplystacks.Crane;
import nl.roykovic.aoc._2022.day5_supplystacks.CraneInstruction;
import nl.roykovic.aoc._2022.day5_supplystacks.CrateStack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class BossfightFactory {
    public Player generateBoss(Stream<String> input) {
        return input.map(line -> Integer.parseInt(line.replaceAll("[\\D]", ""))).collect(Collector.of(
                () -> new int[3],
                (arr, value) -> {
                    for (int i = 0; i < arr.length; i++) {
                        if (arr[i] == 0) {
                            arr[i] = value;
                            break;
                        }
                    }
                },
                (arr1, arr2) -> arr1,
                arr -> new Player(arr[0], arr[1], arr[2])
        ));
    }

    public List<List<Object>> generateItems(){
        List<Object> weapons = new ArrayList<>();
        weapons.add(new ShopItem(8,4,0));
        weapons.add(new ShopItem(10,5,0));
        weapons.add(new ShopItem(25,6,0));
        weapons.add(new ShopItem(40,7,0));
        weapons.add(new ShopItem(74,8,0));

        List<Object> armor = new ArrayList<>();
        armor.add(new ShopItem(0,0,0));
        armor.add(new ShopItem(13,0,1));
        armor.add(new ShopItem(31,0,2));
        armor.add(new ShopItem(53,0,3));
        armor.add(new ShopItem(75,0,4));
        armor.add(new ShopItem(102,0,5));

        List<Object> rings = new ArrayList<>();
        rings.add(new ShopItem(0,0,0));
        rings.add(new ShopItem(25,1,0));
        rings.add(new ShopItem(50,2,0));
        rings.add(new ShopItem(100,3,0));
        rings.add(new ShopItem(20,0,1));
        rings.add(new ShopItem(40,0,2));
        rings.add(new ShopItem(80,0,3));

        return List.of(weapons, armor, rings, rings);
    }

    public Player calculateWinner(Player you, Player boss){
        int yourDamageDealt = Integer.max(1,you.getDamage()- boss.getArmor());
        int bossDamageDealt =  Integer.max(1,boss.getDamage() - you.getArmor());

        int bossTurnsNeeded = (int)Math.ceil((double)you.getHitPoints()/bossDamageDealt);
        int yourTurnsNeeded =  (int)Math.ceil((double)boss.getHitPoints()/yourDamageDealt);

        return yourTurnsNeeded > bossTurnsNeeded?boss: you;
    }
}
