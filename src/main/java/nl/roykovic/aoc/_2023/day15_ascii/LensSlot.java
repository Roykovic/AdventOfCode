package nl.roykovic.aoc._2023.day15_ascii;

public class LensSlot {
    private String label;
    private int vocalLength;

    public LensSlot(String label, int vocalLength) {
        this.label = label;
        this.vocalLength = vocalLength;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getVocalLength() {
        return vocalLength;
    }

    public void setVocalLength(int vocalLength) {
        this.vocalLength = vocalLength;
    }
}
