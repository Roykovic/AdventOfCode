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
