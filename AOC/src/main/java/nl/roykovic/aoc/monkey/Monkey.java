package nl.roykovic.aoc.monkey;

import org.apache.commons.lang3.math.NumberUtils;
import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Engine;

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

    private Long inspect(Long old){
        String actualOperation = operation.split("=")[1];
        actualOperation = actualOperation.replace("old", old.toString());

        Engine engine = Engine.newBuilder()
                .option("engine.WarnInterpreterOnly", "false")
                .build();
        Context ctx = Context.newBuilder("js").engine(engine).build();
        return Long.parseLong(ctx.eval("js",actualOperation).toString());
    }

    private Monkey getCatcher(Long item){
        boolean testResult;
        if(test.contains("divisible")){
            int divideBy = NumberUtils.toInt(test.split(" ")[2]);

            testResult = item % divideBy == 0;
        }
        else{
            throw new IllegalArgumentException();
        }

        return testResult? testTrue: testFalse;
    }

    public void act(){
        for(Long item: items){
            item = inspect(item);   //inspect the item and do operation
            item = item/3;          //monkey gets bored, so worry level /3

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
    //endregion
}
