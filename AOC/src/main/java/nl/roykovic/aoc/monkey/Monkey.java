package nl.roykovic.aoc.monkey;

import org.apache.commons.lang3.math.NumberUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Monkey {
    List<BigInteger> items;
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


//    private BigInteger inspect(BigInteger old){
//        activity++;
//        String actualOperation = operation.split("=")[1];
//        actualOperation = actualOperation.replace("old", old.toString());
//
//        Engine engine = Engine.newBuilder()
//                .option("engine.WarnInterpreterOnly", "false")
//                .build();
//        Context ctx = Context.newBuilder("js").engine(engine).build();
//
//         BigDecimal outcome;
//        try {
//            outcome = new BigDecimal(ctx.eval("js", actualOperation).toString()); //this fixes parsing of exponential notation
//        }
//        catch (NumberFormatException e){
//            System.out.printf(ctx.eval("js", actualOperation).toString());
//            throw e;
//        }
//
//        return outcome.toBigInteger().mod(BigInteger.valueOf(M));
//    }

    private BigInteger inspect(BigInteger old){
        activity++;
        String actualOperation = operation.split("=")[1];
        actualOperation = actualOperation.replace("old", old.toString());

        BigInteger outcome;

        String[] actualOperationParts = actualOperation.trim().split(" ");

        BigInteger firstNumber = new BigInteger(actualOperationParts[0]);
        BigInteger secondNumber = new BigInteger(actualOperationParts[2]);
        String operator = actualOperationParts[1];

        outcome = switch (operator) {
            case "+" -> firstNumber.add(secondNumber);
            case "-" -> firstNumber.subtract(secondNumber);
            case "*" -> firstNumber.multiply(secondNumber);
            case "/" -> firstNumber.divide(secondNumber);
            default -> throw new IllegalArgumentException(operator + " is not a valid operation");
        };

       return outcome.mod(BigInteger.valueOf(M));
    }

    private Monkey getCatcher(BigInteger item){
        boolean testResult;
        if(test.contains("divisible")){
            testResult = item.mod(BigInteger.valueOf(divider)) == BigInteger.ZERO;
        }
        else{
            throw new IllegalArgumentException();
        }

        return testResult? testTrue: testFalse;
    }

    public void act(int worryLevelDecrease){
        for(BigInteger item: items){
            item = inspect(item);   //inspect the item and do operation
            item = item.divide(BigInteger.valueOf(worryLevelDecrease));          //monkey gets bored, so worry level /3

            Monkey catcher = getCatcher(item);  //get the catcher from te test operation
            List<BigInteger> catchList = catcher.getItems();
            catchList.add(item);                //add item to catchers list
        }
        items = new ArrayList<>();  //all items are now thrown, so make this list empty
    }

    //region Getters and setters
    public List<BigInteger> getItems() {
        return items;
    }

    public void setItems(List<BigInteger> items) {
        this.items = items;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getTest() {
        return test;
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

    public Monkey getTestTrue() {
        return testTrue;
    }

    public void setTestTrue(Monkey testTrue) {
        this.testTrue = testTrue;
    }

    public Monkey getTestFalse() {
        return testFalse;
    }

    public void setTestFalse(Monkey testFalse) {
        this.testFalse = testFalse;
    }

    public Long getActivity() {
        return activity;
    }

    public Long getM() {
        return M;
    }

    public void setM(Long m) {
        M = m;
    }

    public int getDivider() {
        return divider;
    }

    public void setDivider(int divider) {
        this.divider = divider;
    }

    //endregion
}
