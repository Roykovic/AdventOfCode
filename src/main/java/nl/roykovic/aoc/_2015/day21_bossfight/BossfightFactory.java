package nl.roykovic.aoc._2015.day21_bossfight;

import nl.roykovic.aoc._2022.day5_supplystacks.Crane;
import nl.roykovic.aoc._2022.day5_supplystacks.CraneInstruction;
import nl.roykovic.aoc._2022.day5_supplystacks.CrateStack;

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

    public Player calculateWinner(Player you, Player boss, int damageModifier, int armorModifier){
        int yourDamageDealt = Integer.max(1,(you.getDamage() + damageModifier)- boss.getArmor());
        int bossDamageDealt =  Integer.max(1,boss.getDamage() - (you.getArmor()+armorModifier));

        int bossTurnsNeeded = you.getHitPoints()/bossDamageDealt;
        int yourTurnsNeeded =  boss.getHitPoints()/yourDamageDealt;

        System.out.println(bossTurnsNeeded);
        System.out.println(yourTurnsNeeded);

        return yourTurnsNeeded > bossTurnsNeeded?boss: you;
    }

    public Player calculateWinner(Player you, Player boss){
        return calculateWinner(you, boss, 0,0);
    }
}
