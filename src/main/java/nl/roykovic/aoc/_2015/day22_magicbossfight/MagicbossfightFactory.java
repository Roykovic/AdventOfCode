package nl.roykovic.aoc._2015.day22_magicbossfight;

import nl.roykovic.aoc._2015.day21_bossfight.BossfightFactory;
import nl.roykovic.aoc._2015.day21_bossfight.ShopItem;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class MagicbossfightFactory {
    public int generate(Stream<String> input) {
        var boss = new BossfightFactory().generateBoss(input);

        return 0;
    }

    public List<Object> generateAttacks(){
        List<Object> attacks = new ArrayList<>();
        attacks.add(new MagicAttack("Magic Missile", 53,4,0,0,0,0));
        attacks.add(new MagicAttack("Drain",73,2,2,0,0,0));
        attacks.add(new MagicAttack("Shield",113,0,0,7,0,6));
        attacks.add(new MagicAttack("Poison",173,3,0,0,0,6));
        attacks.add(new MagicAttack("Recharge",229,0,0,0,101,5));

        return attacks;
    }
}
