package nl.roykovic.aoc._2016.day10_bots;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Bot implements Receiver{
    Integer number;
    Integer low;
    Integer high;

    Integer lowOutput;
    Integer highOutput;

    Receiver lowReceiver;
    Receiver highReceiver;

    public Bot(int number, Integer low, Integer high, Integer lowOutput, Integer highOutput) {
        this.number = number;
        this.low = low;
        this.high = high;
        this.lowOutput = lowOutput;
        this.highOutput = highOutput;
    }

    List<Integer> chips = new ArrayList<>();

    public Bot proceed(int lowToLookFor, int highToLookFor, boolean stop){
        if(this.chips.size() != 2){
            return null;
        }

        int lowChip = chips.stream().mapToInt(it -> it).min().orElseThrow();
        int highChip = chips.stream().mapToInt(it -> it).max().orElseThrow();

        if(stop && lowChip == lowToLookFor && highChip == highToLookFor){
            return this;
        }

        lowReceiver.accept(lowChip);
        highReceiver.accept(highChip);

        this.chips = new ArrayList<>();
        return null;
    }

    public void accept(int chip){
        this.chips.add(chip);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getLow() {
        return low;
    }

    public void setLow(int low) {
        this.low = low;
    }

    public int getHigh() {
        return high;
    }

    public void setHigh(int high) {
        this.high = high;
    }

    public List<Integer> getChips() {
        return chips;
    }

    public void setChips(List<Integer> chips) {
        this.chips = chips;
    }

    public Receiver getLowReceiver() {
        return lowReceiver;
    }

    public void setLowReceiver(Receiver lowReceiver) {
        this.lowReceiver = lowReceiver;
    }

    public Receiver getHighReceiver() {
        return highReceiver;
    }

    public void setHighReceiver(Receiver highReceiver) {
        this.highReceiver = highReceiver;
    }
}
