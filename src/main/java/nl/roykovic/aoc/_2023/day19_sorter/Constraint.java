package nl.roykovic.aoc._2023.day19_sorter;

public class Constraint {
    private final String property;
    private final char operator;
    private final int number;

    private final String followup;

    public Constraint(String property, char operator, int number, String followup) {
        this.property = property;
        this.operator = operator;
        this.number = number;
        this.followup = followup;
    }

    public boolean passes(int otherNumber){
        if(operator == '>'){
            return otherNumber > number;
        }
        return otherNumber < number;
    }

    public String getProperty() {
        return property;
    }

    public String getFollowup() {
        return followup;
    }
}
