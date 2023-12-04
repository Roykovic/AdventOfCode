package nl.roykovic.aoc._2022.day20_encryptednumbers;

@SuppressWarnings("ClassCanBeRecord")
public class EncryptedNumber {
    private final long value;

    public EncryptedNumber(long value) {
        this.value = value;
    }

    public long getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
