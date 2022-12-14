package nl.roykovic.aoc._2022.monkey;

import org.apache.commons.lang3.math.NumberUtils;
import java.util.ArrayList;
import java.util.List;

public class Monkey {
    List<Long> items;
    private String operation;
    private String test;

    private Monkey testTrue;
    private Monkey testFalse;

    private int testTrueNumber;
    private int testFalseNumber;

    private Long activity = 0L;

    private Long M;

    private int divider;


//    The key to this is a modular arithmetic concept.
//    First, you need to multiply all the monkeys' mod values together. Let's call this M.
//    Now, after every operation you do to an item worry level, after doing the computation, mod that value with M.
//          If you're adding, add then take that result and mod with M.
//          If you're multiplying, multiply and then mod with M.
//          This will keep your worry values small.
//
//    Everything else is the same. We are exploiting the property that for all integers k: (a mod km) mod m = a mod m
//
//    Beter goed gejat dan slecht verzonnen https://www.reddit.com/r/adventofcode/comments/zihouc/comment/izrimjo/?utm_source=share&utm_medium=web2x&context=3

    private Long inspect(Long old){
        activity++;
        String actualOperation = operation.split("=")[1];
        actualOperation = actualOperation.replace("old", old.toString());

        Long outcome;

        String[] actualOperationParts = actualOperation.trim().split(" ");

        Long firstNumber = NumberUtils.toLong(actualOperationParts[0]);
        Long secondNumber = NumberUtils.toLong(actualOperationParts[2]);
        String operator = actualOperationParts[1];

        outcome = switch (operator) {
            case "+" -> firstNumber + secondNumber;
            case "-" -> firstNumber - secondNumber;
            case "*" -> firstNumber * secondNumber;
            case "/" -> firstNumber / secondNumber;
            default -> throw new IllegalArgumentException(operator + " is not a valid operation");
        };

       return outcome % M;
    }

    private Monkey getCatcher(Long item){
        boolean testResult;
        if(test.contains("divisible")){
            testResult = item % divider == 0;
        }
        else{
            throw new IllegalArgumentException();
        }

        return testResult? testTrue: testFalse;
    }

    public void act(int worryLevelDecrease){
        for(Long item: items){
            item = inspect(item);   //inspect the item and do operation
            item = item / worryLevelDecrease;          //monkey gets bored, so worry level /3

            Monkey catcher = getCatcher(item);  //get the catcher from te test operation
            List<Long> catchList = catcher.getItems();
            catchList.add(item);                //add item to catchers list
        }
        items = new ArrayList<>();  //all items are now thrown, so make this list empty
    }

    //region Getters and setters
    public List<Long> getItems() {
        return items;
    }

    public void setItems(List<Long> items) {
        this.items = items;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public int getTestTrueNumber() {
        return testTrueNumber;
    }

    public void setTestTrueNumber(int testTrueNumber) {
        this.testTrueNumber = testTrueNumber;
    }

    public int getTestFalseNumber() {
        return testFalseNumber;
    }

    public void setTestFalseNumber(int testFalseNumber) {
        this.testFalseNumber = testFalseNumber;
    }

    public void setTestTrue(Monkey testTrue) {
        this.testTrue = testTrue;
    }

    public void setTestFalse(Monkey testFalse) {
        this.testFalse = testFalse;
    }

    public Long getActivity() {
        return activity;
    }

    public void setM(Long m) {
        M = m;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    //endregion
}
