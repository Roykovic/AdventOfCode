package nl.roykovic.aoc._2015.day21_bossfight;

import nl.roykovic.aoc.utils.FileReaderService;
import nl.roykovic.aoc.utils.Utils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class BossfightFactoryTest {
    @ParameterizedTest
    @CsvSource({
//            "BossfightTestInput.txt,true,-1",
            "BossfightInput.txt,false,-1",
    })
    public void test(String filename, boolean test, int expected) {
        var input = FileReaderService.streamLinesFromFile(2015, filename, test);
        var factory = new BossfightFactory();
        var boss = factory.generateBoss(input);

        Player you = new Player(100,11,8);

        assertEquals(factory.calculateWinner(you, boss), you);
    }

    @ParameterizedTest
    @CsvSource({
            "BossfightInput.txt,false,-1",
    })
    public void testMinimumModifier(String filename, boolean test, int expected) {
        var bossInput = FileReaderService.streamLinesFromFile(2015, filename, test);
        var factory = new BossfightFactory();
        var boss = factory.generateBoss(bossInput);

        var items = factory.generateItems();

        var combos = Utils.cartesianProduct(items, 0);

        var outcomes = combos.filter(it -> (it.get(2) != it.get(3)) || ((ShopItem)it.get(2)).getCost() == 0)
                .map(it -> {
                    ShopItem weapon = (ShopItem)it.get(0);
                    ShopItem armor = (ShopItem)it.get(1);
                    ShopItem ring1 = (ShopItem)it.get(2);
                    ShopItem ring2 = (ShopItem)it.get(3);

                    int cost = weapon.getCost() + armor.getCost() + ring1.getCost() + ring2.getCost();
                    int damage = weapon.getDamage() + armor.getDamage() + ring1.getDamage() + ring2.getDamage();
                    int armorStat = weapon.getArmor() + armor.getArmor() + ring1.getArmor() + ring2.getArmor();

                    return new ShopItem(cost, damage, armorStat);
                })
                .map(shopItem -> {
                    int cost = shopItem.getCost();

                    Player player = new Player(100, shopItem.getDamage(), shopItem.getArmor());

                    return Map.entry(cost, player);
                })
                .map(costMap -> Map.entry(costMap.getKey(),factory.calculateWinner(costMap.getValue(), boss) == costMap.getValue())
                ).toList();

            var winningOutcomes = outcomes.stream().filter(Map.Entry::getValue).map(Map.Entry::getKey).sorted().toList();

            assertEquals(78,winningOutcomes.get(0));

            var losingOutcomes = outcomes.stream().filter(it -> !it.getValue()).map(Map.Entry::getKey).sorted().toList();

            assertEquals(148,losingOutcomes.get(losingOutcomes.size()-1));

        System.out.println("bla");

    }
}