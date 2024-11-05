package nl.roykovic.aoc._2015.day23_turingmachine.operations;

public abstract class TuringOperation {

    public Character register;
    public int jump = 1;
    public int jump(Double val){
        if(condition(val)){
            return jump;
        }
        return 1;
    }

    public boolean condition(Double val){
        return true;
    }

    public Double AddMultiplier(Double val){
        return val;
    }

}
