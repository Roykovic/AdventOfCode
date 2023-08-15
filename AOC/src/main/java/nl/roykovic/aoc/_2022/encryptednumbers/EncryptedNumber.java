package nl.roykovic.aoc._2022.encryptednumbers;

public class EncryptedNumber {
    private long value;
    private int index;

    private boolean swapped = false;

    public EncryptedNumber(long value, int index) {
        this.value = value;
        this.index = index;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isSwapped() {
        return swapped;
    }

    public void setSwapped(boolean swapped) {
        this.swapped = swapped;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
