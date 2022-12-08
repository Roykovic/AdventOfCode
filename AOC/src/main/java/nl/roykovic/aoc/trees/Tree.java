package nl.roykovic.aoc.trees;

public class Tree {
    private final int height;
    private boolean visible;

    public Tree(int height) {
        this.height = height;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
}
