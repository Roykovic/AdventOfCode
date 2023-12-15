package nl.roykovic.aoc._2022.day8_trees;

public class Tree {
    private final int height;
    private boolean visible;

    private final int x;
    private final int y;

    private int scenicScore;

    public Tree(int height, int x, int y) {
        this.height = height;
        this.x = x;
        this.y = y;
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getScenicScore() {
        return scenicScore;
    }

    public void setScenicScore(int scenicScore) {
        this.scenicScore = scenicScore;
    }
}
