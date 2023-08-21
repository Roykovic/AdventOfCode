package nl.roykovic.aoc._2015.day14_reindeerrace;

public class Reindeer {
    private final String name;
    private final int speed;
    private final int speedTime;
    private final int restTime;

    private int score;

    public Reindeer(String name, int speed, int speedTime, int restTime) {
        this.name = name;
        this.speed = speed;
        this.speedTime = speedTime;
        this.restTime = restTime;
    }

    public String getName() {
        return name;
    }

    public int getCycleTime(){
        return speedTime + restTime;
    }

    public int getCycleDistance(){
        return speed * speedTime;
    }

    public int getScore() {
        return score;
    }

    public void giveScore() {
        this.score++;
    }

    public int getDistanceTraveledInTime(int seconds){
        int distance = 0;

        int wholeCycles = seconds / getCycleTime();

        distance += (wholeCycles * getCycleDistance());

        //This is how many seconds are left after the last completed cycle
        int secondsLeft = seconds % getCycleTime();

        //if there is more time left than the time we can run, only run for speedtime. Else just run the entire secondsleft
        distance += Math.min(speedTime, secondsLeft) * speed;

        return distance;
    }
}
