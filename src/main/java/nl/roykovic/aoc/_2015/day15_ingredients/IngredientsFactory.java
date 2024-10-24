package nl.roykovic.aoc._2015.day15_ingredients;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
public class IngredientsFactory {
    public List<Ingredient> generate(Stream<String> input) {
        var ingredients = input.map(it -> {
            Ingredient ingredient = new Ingredient();

            var splitted = it.split(":");
            ingredient.setName(splitted[0]);

            var propertiesString = splitted[1];
            String regex = "-?\\d+";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(propertiesString);

            matcher.find();
            ingredient.setCapacity(Integer.parseInt(matcher.group()));
            matcher.find();
            ingredient.setDurability(Integer.parseInt(matcher.group()));
            matcher.find();
            ingredient.setFlavor(Integer.parseInt(matcher.group()));
            matcher.find();
            ingredient.setTexture(Integer.parseInt(matcher.group()));
            matcher.find();
            ingredient.setCalories(Integer.parseInt(matcher.group()));

            return ingredient;
        }).toList();

        return ingredients;
    }

    public int calculateBestCookieScore(int teaspoons, int maxCalories, List<Ingredient> output){

        List<int[]> combinations = findAllCombinations(teaspoons, output.size());
        int max = 0;
        for(var combination : combinations){

            int capacity =0;
            int durability =0;
            int flavor = 0;
            int texture =0;
            int calories = 0;

            for(int i = 0; i< combination.length; i++){
                capacity += combination[i] * output.get(i).getCapacity();
                durability += combination[i] * output.get(i).getDurability();
                flavor += combination[i] * output.get(i).getFlavor();
                texture += combination[i] * output.get(i).getTexture();
                calories += combination[i] * output.get(i).getCalories();
            }

            int result = Integer.max(0,capacity) * Integer.max(0,durability) *  Integer.max(0,flavor) *  Integer.max(0,texture);

            if(result > max && (maxCalories == Integer.MAX_VALUE || calories == maxCalories)){
                max = result;
            }
        }

        return max;
    }

    private List<int[]> findAllCombinations(int target, int numbers) {
        List<int[]> results = new ArrayList<>();
        generateCombinations(results, new int[numbers], target, 0);
        return results;
    }

    private void generateCombinations(List<int[]> results, int[] combination, int target, int index) {
        if (index == combination.length - 1) {
            combination[index] = target;
            results.add(combination.clone());
            return;
        }

        for (int i = 1; i <= target; i++) {
            combination[index] = i;
            generateCombinations(results, combination, target - i, index + 1);
        }
    }
}
