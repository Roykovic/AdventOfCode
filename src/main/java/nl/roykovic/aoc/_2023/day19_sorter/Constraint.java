package nl.roykovic.aoc._2023.day19_sorter;

import java.util.Optional;

public class Constraint {
    private final Character property;
    private final Character operator;
    private final Integer number;
    private final String followup;

    public Constraint(Character property, Character operator, Integer number, String followup) {
        this.property = property;
        this.operator = operator;
        this.number = number;
        this.followup = followup;
    }

    public Optional<String> next(int otherNumber){
        Boolean passes;

        if(operator == '>'){
            passes = otherNumber > number;
        }
        else {
            passes = otherNumber < number;
        }
        Boolean finalPasses = passes;
        return Optional.of(followup).filter(it -> finalPasses);
    }

    public Character getOperator() {
        return operator;
    }

    public Integer getNumber() {
        return number;
    }

    public Character getProperty() {
        return property;
    }

    public String getFollowup() {
        return followup;
    }
}
