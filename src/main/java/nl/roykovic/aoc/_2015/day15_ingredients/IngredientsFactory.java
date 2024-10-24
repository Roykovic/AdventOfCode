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
}
